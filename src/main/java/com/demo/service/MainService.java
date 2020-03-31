package com.demo.service;

import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public interface MainService {
    /**
     * 设置主页右上角头像和用户名
     * @param imageView
     * @param hyperlink
     */
    void setAvatar(ImageView imageView, Hyperlink hyperlink);

    void switchView(String fileName, Pane pane) throws Exception;
}
