package com.demo.controller.book;

import com.demo.entity.analysis.pubYearAls;
import com.demo.entity.analysis.pubHouseAls;
import com.demo.mapper.analysis.bookAnalysis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import org.yu.myorm.core.dynproxy.MapperInvoHander;

import java.lang.reflect.Proxy;
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

//    private BookService bookService = ServiceFactory.getBookServiceInstance();

    private bookAnalysis bookAnalysis = (bookAnalysis) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{com.demo.mapper.analysis.bookAnalysis.class},new MapperInvoHander());


    @FXML
    private PieChart pieChart;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLineChart();
        initPieChart();

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
        for (int i=0; i< analysisList.size(); i++) {
            pubYearAls pa = analysisList.get(i);
            String xValue = pa.getPublicationYear();
            Long yValue = pa.getCount();
            dataList.add(new XYChart.Data<String, Long>(xValue, yValue));
        }
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
}
