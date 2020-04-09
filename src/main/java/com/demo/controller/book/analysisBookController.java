package com.demo.controller.book;

import com.demo.entity.analysis.pubHouseAls;
import com.demo.entity.analysis.pubYearAls;
import com.demo.entity.analysis.typeBookAls;
import com.demo.mapper.analysis.bookAnalysis;
import com.demo.utils.AlyServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class analysisBookController implements Initializable {
    @FXML
    private LineChart xyLineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private bookAnalysis bookAnalysis = AlyServiceFactory.getBookAnalysisService();


    @FXML
    private PieChart pieChart;

    @FXML
    private PieChart typePieChart;



    public void refresh() {
        xyLineChart.getData().clear();
        pieChart.getData().clear();
        typePieChart.getData().clear();
        initialize(null, null);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLineChart();
        initPieChart();
        initTypePieChart();
    }

    private void initLineChart() {
        xyLineChart.setTitle("馆藏年份统计");
        xAxis.setLabel("出版年份");
        yAxis.setLabel("馆藏数量");
        yAxis.setTickUnit(20);
//        yAxis.setTickLabelRotation(90);
        xyLineChart.computeAreaInScreen();
        xyLineChart.setLegendSide(Side.RIGHT);

        List<pubYearAls> analysisList = bookAnalysis.select();

        XYChart.Series<String, Long> series = new XYChart.Series<>();
        series.setName("馆藏数量");
        ObservableList<XYChart.Data<String, Long>> dataList = series.getData();
        analysisList.stream().forEach(pa -> {
            String xValue = pa.getPublicationYear();
            Long yValue = pa.getCount();
            dataList.add(new XYChart.Data<>(xValue, yValue));
        });
        xyLineChart.getData().add(series);
    }


    private void initPieChart(){
        pieChart.setClockwise(true);
        List<pubHouseAls> pubHouseAls = bookAnalysis.selectPub();

        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
        for (pubHouseAls pub : pubHouseAls) {
            dataList.add(new PieChart.Data(pub.getPublishingHouse(), pub.getCount()));
        }
        pieChart.setData(dataList);
    }

    private void initTypePieChart(){
        typePieChart.setClockwise(true);
        List<typeBookAls> typeBookAlsList = bookAnalysis.selectTypePie();

        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
        for (typeBookAls typeb : typeBookAlsList) {
            dataList.add(new PieChart.Data(typeb.getTypeName(), typeb.getCount()));
        }
        typePieChart.setData(dataList);
    }
}
