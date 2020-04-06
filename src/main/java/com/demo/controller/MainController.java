package com.demo.controller;

import com.demo.App;
import com.demo.service.MainService;
import com.demo.utils.CurrentUser;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private MainService mainService = ServiceFactory.getMainServiceInstance();

    @FXML
    private Hyperlink userName;

    @FXML
    private ImageView userAvatar;


    @FXML
    private StackPane mainContainer;

    /**
     * 退出登录
     * @param actionEvent
     */
    public void logout(ActionEvent actionEvent) {
        new App().showLoginView();
        //关闭主页
        Stage stage = (Stage) userAvatar.getScene().getWindow();
        stage.close();
    }


    /**
     * 图书列表
     * @param actionEvent
     * @throws Exception
     */
    public void listBook(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.BOOK_FXML, mainContainer);
    }

    public void listBookAnalysis(ActionEvent actionEvent) {
    }

    /**
     * 用户列表
     * @param actionEvent
     * @throws Exception
     */
    public void listUser(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.USER_LIST_FXML, mainContainer);
    }


    public void listReaderAnalysis(ActionEvent actionEvent) {
    }

    /**
     * 获取用户个人信息
     * @param actionEvent
     * @throws Exception
     */
    public void listPersonal(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.USER_FXML, mainContainer);
    }
    public void personal(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.USER_FXML, mainContainer);
    }


    /**
     * 借阅信息
     * @param actionEvent
     * @throws Exception
     */
    public void borrowInfo(ActionEvent actionEvent) throws Exception {
        String fxml = CurrentUser.getUserAllInfo().getRole_name().equals("BORROWER") ? ResourcesConfig.BORROWER_BOOK_FXML : ResourcesConfig.BORROW_FXML;
        mainService.switchView(fxml, mainContainer);
    }

    /**
     * 操作日志信息
     * @param actionEvent
     * @throws Exception
     */
    public void listOperation(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.OPERATION_FXML, mainContainer);
    }

    /**
     * 个人操作日志
     * @param actionEvent
     */
    public void listPersonalOperation(ActionEvent actionEvent) {

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //开启一个UI线程,设置头像
        mainService.setAvatar(userAvatar, userName);
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

        //将借阅信息设置为默认页
        String fxml = CurrentUser.getUserAllInfo().getRole_name().equals("BORROWER") ? ResourcesConfig.BORROWER_BOOK_FXML : ResourcesConfig.BORROW_FXML;
        mainService.switchView(fxml, mainContainer);

    }



}
