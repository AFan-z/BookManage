package com.demo.controller;


import com.demo.entity.sys.remLoginUser;
import com.demo.service.LoginService;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private LoginService loginService = ServiceFactory.getLoginServiceInstance();

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox isRemembered;

    /**
     * 登录事件
     *
     * @param actionEvent
     */
    public void loginButtonClick(ActionEvent actionEvent) throws Exception {

        if (isRemembered.isSelected()){
           remLoginUser.setUsernameStr(username.getText());
           remLoginUser.setPasswdStr(password.getText());
           remLoginUser.setIsRem(isRemembered.isSelected());
        }
        if (loginService.checkReturn(username.getText(), password.getText())) {
            loginService.goToMain();
            //关闭登录页
            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("失败");
            alert.setContentText("账号或密码错误，登录失败!");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (remLoginUser.getIsRem()) {
            isRemembered.setSelected(remLoginUser.getIsRem());
            username.setText(remLoginUser.getUsernameStr());
            password.setText(remLoginUser.getPasswdStr());
        }
    }

}

