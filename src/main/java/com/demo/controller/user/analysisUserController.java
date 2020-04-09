package com.demo.controller.user;

import com.demo.entity.analysis.userNewAls;
import com.demo.entity.analysis.userPieAls;
import com.demo.mapper.analysis.userAnalysis;
import com.demo.utils.AlyServiceFactory;
import com.demo.utils.enumeration.Roles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import org.yu.myorm.core.Exception.NoSuchDataInDBException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class analysisUserController implements Initializable {
    @FXML
    private LineChart xyLineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private userAnalysis userAnalysis = AlyServiceFactory.getUserAnalysisService();


    @FXML
    private PieChart pieChart;


    public void refresh() {
        xyLineChart.getData().clear();
        pieChart.getData().clear();
        initialize(null, null);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initLineChart();
        } catch (NoSuchDataInDBException dbe) {
            dbe.printStackTrace();
        }
        initPieChart();

    }

    private void initLineChart() {
        xyLineChart.setTitle("新增注册用户分析");
        xAxis.setLabel("年份");
        yAxis.setLabel("新增用户数量");
        yAxis.setTickUnit(20);

        List<userNewAls> userNewAlsList = userAnalysis.selectNewUser();

        XYChart.Series<String, Number> aSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> bSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> cSeries = new XYChart.Series<>();
        aSeries.setName("系统管理员");
        bSeries.setName("图书管理员");
        cSeries.setName("借阅者");
        for (userNewAls ua: userNewAlsList) {
            String roleName = ua.getRoleName();
            if (roleName.equals(Roles.SYSTEM_ADMIN.value())) {
                aSeries.getData().add(new XYChart.Data<>(String.valueOf(ua.getYear()), ua.getCount()+0.00));
            } else if (roleName.equals(Roles.BOOK_ADMIN.value())) {
                bSeries.getData().add(new XYChart.Data<>(String.valueOf(ua.getYear()), ua.getCount()+0.02));
            } else if (roleName.equals(Roles.BORROWER.value())) {
                cSeries.getData().add(new XYChart.Data<>(String.valueOf(ua.getYear()), ua.getCount()+0.01));
            }
        }
        xyLineChart.getData().addAll(aSeries, bSeries, cSeries);
    }


    private void initPieChart(){
        pieChart.setClockwise(true);
        List<userPieAls> userPieAlsList = userAnalysis.selectPercent();

        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
        for (userPieAls pub : userPieAlsList) {
            dataList.add(new PieChart.Data(pub.getRoleName(), pub.getCount()));
        }
        pieChart.setData(dataList);

    }
}
