package com.demo.controller.user;

import com.demo.entity.analysis.personalBorrowAls;
import com.demo.entity.analysis.pubHouseAls;
import com.demo.entity.analysis.typeBookAls;
import com.demo.mapper.analysis.personalAnalysis;
import com.demo.utils.AlyServiceFactory;
import com.demo.utils.CurrentUser;
import com.demo.utils.GUIEventer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class analysisPersonalBorrowController implements Initializable {

    @FXML
    private BarChart xyBarChart;
    @FXML
    private CategoryAxis xAxisBarChart;
    @FXML
    private NumberAxis yAxisBarChart;
    @FXML
    private PieChart pieChart;
    @FXML
    private PieChart typePieChart;

    @FXML
    private Label label;


    private int currentUserId = CurrentUser.getUserAllInfo().getId();

    private personalAnalysis personalAnalysis = AlyServiceFactory.getPersonalAnalysisService();

    public void refresh() {
        xyBarChart.getData().clear();
        pieChart.getData().clear();
        typePieChart.getData().clear();
        initialize(null, null);
    }

    private void initBarChart(){

        xyBarChart.setTitle("借阅书籍的类别统计");
        xAxisBarChart.setLabel("图书类别");
        yAxisBarChart.setLabel("图书数量");
        yAxisBarChart.setTickUnit(20);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("本年度统计数据");

        List<personalBorrowAls> personalBorrowAlsList = personalAnalysis.selectBorrowType(currentUserId);
        ObservableList<XYChart.Data<String, Number>> dataList = series.getData();
        personalBorrowAlsList.stream().forEach(pb -> {
            String xValue = pb.getTypeName();
            Number yValue = pb.getCount();
            dataList.add(new XYChart.Data<>(xValue, yValue));
        });
        xyBarChart.getData().add(series);
    }


    private void initPieChart(){
        pieChart.setClockwise(true);
        List<pubHouseAls> pubHouseAls = personalAnalysis.selectPub(currentUserId);

        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
        for (pubHouseAls pub : pubHouseAls) {
            dataList.add(new PieChart.Data(pub.getPublishingHouse(), pub.getCount()));
        }
        pieChart.setData(dataList);
        GUIEventer.setEventerFor(pieChart, label);
    }

    private void initTypePieChart(){
        typePieChart.setClockwise(true);
        List<typeBookAls> typeBookAlsList = personalAnalysis.selectTypePie(currentUserId);

        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
        for (typeBookAls typeb : typeBookAlsList) {
            dataList.add(new PieChart.Data(typeb.getTypeName(), typeb.getCount()));
        }
        typePieChart.setData(dataList);
        GUIEventer.setEventerFor(typePieChart, label);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initBarChart();
        initPieChart();
        initTypePieChart();
    }
}
