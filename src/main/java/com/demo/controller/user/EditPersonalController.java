package com.demo.controller.user;

import com.demo.entity.TableView.UserAllInfo;
import com.demo.service.UserService;
import com.demo.utils.CurrentUser;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPersonalController implements Initializable {
    @FXML
    private TextField password;
    @FXML
    private TextField name;
    @FXML
    private TextField gender;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;

    private UserService userService = ServiceFactory.getUserServiceInstance();

    public void editPersonal(ActionEvent actionEvent) {
        userService.editPersonal(password,name,gender,email,phone);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserAllInfo userAllInfo = CurrentUser.getUserAllInfo();
        password.setText(userAllInfo.getPassword());
        name.setText(userAllInfo.getName());
        gender.setText(userAllInfo.getGender());
        email.setText(userAllInfo.getEmail());
        phone.setText(userAllInfo.getPhone());
    }
}
