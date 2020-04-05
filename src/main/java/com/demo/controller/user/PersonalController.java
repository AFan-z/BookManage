package com.demo.controller.user;

import com.demo.service.UserService;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalController implements Initializable {

    @FXML
    private Label job_num;
    @FXML
    private Label password;
    @FXML
    private Label name;
    @FXML
    private Label gender;
    @FXML
    private Label employment_year;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label login_num;

    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService.getUserInfo(job_num,password,name,gender,employment_year,phone,email,login_num);
    }


    public void editPersonInfo(ActionEvent actionEvent) throws Exception {
        userService.newUserStage(ResourcesConfig.EDIT_PERSONAL_FXML);
    }

    public void refreshUser(ActionEvent actionEvent) {
        userService.getUserInfo(job_num,password,name,gender,employment_year,phone,email,login_num);
    }
}
