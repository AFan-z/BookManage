package com.demo.controller.user;

import com.demo.entity.analysis.personalBorrowAls;
import com.demo.entity.analysis.personalOpeAls;
import com.demo.mapper.analysis.personalAnalysis;
import com.demo.utils.AlyServiceFactory;
import com.demo.utils.CurrentUser;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class analysisPersonalController implements Initializable {
    @FXML
    private LineChart xyLineChart;
    @FXML
    private CategoryAxis xAxisLineChart;
    @FXML
    private NumberAxis yAxisLineChart;
    @FXML
    private BarChart xyBarChart;
    @FXML
    private CategoryAxis xAxisBarChart;
    @FXML
    private NumberAxis yAxisBarChart;

    private int currentUserId = CurrentUser.getUserAllInfo().getId();

    private personalAnalysis personalAnalysis = AlyServiceFactory.getPersonalAnalysisService();

    private void initLineChart() {
        xyLineChart.setTitle("个人当天操作次数统计");
        xAxisLineChart.setLabel("时间(24小时制)");
        yAxisLineChart.setLabel("操作次数");
        yAxisLineChart.setTickUnit(20);

        xyLineChart.setLegendSide(Side.RIGHT);
        List<personalOpeAls> analysisList = personalAnalysis.select(currentUserId);
        XYChart.Series<String, Long> series = new XYChart.Series<>();
        series.setName("操作次数");
        ObservableList<XYChart.Data<String, Long>> dataList = series.getData();
        analysisList.stream().forEach(pa -> {
            String xValue = pa.getHours();
            Long yValue = pa.getNum();
            dataList.add(new XYChart.Data<>(xValue, yValue));
        });
        xyLineChart.getData().add(series);
    }

    private void initBarChart(){

        xyBarChart.setTitle("个人借阅书籍的类别统计");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLineChart();
        initBarChart();
    }
}
