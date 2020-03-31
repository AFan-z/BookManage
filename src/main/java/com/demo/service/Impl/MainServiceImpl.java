package com.demo.service.Impl;

import com.demo.service.MainService;
import com.demo.utils.CurrentUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MainServiceImpl implements MainService {
    @Override
    public void setAvatar(ImageView imageView, Hyperlink hyperlink) {

        Image image = new Image(CurrentUser.getUserAllInfo().getAvatar());

        imageView.setImage(image);
        //将头像显示为圆形
        Circle circle = new Circle();
        circle.setCenterX(20.0);
        circle.setCenterY(20.0);
        circle.setRadius(20.0);
        imageView.setClip(circle);

        //显示管理员姓名
        hyperlink.setText(CurrentUser.getUserAllInfo().getName());
    }

    @Override
    public void switchView(String fileName, Pane pane) throws Exception {
        //清空原有内容
        pane.getChildren().clear();
        AnchorPane anchorPane = new FXMLLoader(getClass().getResource(fileName)).load();
        pane.getChildren().add(anchorPane);
    }
}
