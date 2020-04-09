package com.demo.controller.user;

import com.demo.entity.analysis.personalOpeAls;
import com.demo.entity.analysis.pubYearAls;
import com.demo.mapper.analysis.bookAnalysis;
import com.demo.mapper.analysis.personalAnalysis;
import com.demo.utils.CurrentUser;
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

    private personalAnalysis personalAnalysis = (personalAnalysis) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{com.demo.mapper.analysis.personalAnalysis.class},new MapperInvoHander());

    private void initLineChart() {
        xyLineChart.setTitle("个人当天操作次数统计");
        xAxisLineChart.setLabel("时间(24小时制)");
        yAxisLineChart.setLabel("操作次数");
        yAxisLineChart.setTickUnit(20);
        xyLineChart.computeAreaInScreen();
        xyLineChart.setLegendSide(Side.RIGHT);
        List<personalOpeAls> analysisList = personalAnalysis.select(CurrentUser.getUserAllInfo().getId());
        XYChart.Series<String, Long> series = new XYChart.Series<>();
        series.setName("操作次数");
        ObservableList<XYChart.Data<String, Long>> dataList = series.getData();
        for (int i=0; i< analysisList.size(); i++) {
            personalOpeAls pa = analysisList.get(i);
            String xValue = pa.getHours();
            Long yValue = pa.getNum();
            dataList.add(new XYChart.Data<String, Long>(xValue, yValue));
        }
        xyLineChart.getData().add(series);
    }

    private void initBarChart(){

        xyBarChart.setTitle("根据类别统计柱形图");
        xAxisBarChart.setLabel("图书类别");
        yAxisBarChart.setLabel("图书数量");
        XYChart.Series<String, Long> series = new XYChart.Series<>();
        series.setName("2018年统计数据");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLineChart();
        initBarChart();
    }
}
