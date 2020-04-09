package com.demo.utils;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GUIEventer {

    public static void setEventerFor(final PieChart pieChart, final Label label) {
        for (final PieChart.Data data: pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {


                @Override
                public void handle(MouseEvent e) {
                    label.setTextFill(Color.DARKORANGE);
                    label.setStyle("-fx-font: 24 arial;");
                    label.setTranslateX(e.getSceneX());
                    label.setTranslateY(e.getSceneY());
                    label.setText("数量：" + Double.valueOf(data.getPieValue()).intValue());
                }
            });
        }
    }


}
