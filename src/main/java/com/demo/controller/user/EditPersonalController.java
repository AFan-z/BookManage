package com.demo.controller.user;

import com.demo.service.UserService;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditPersonalController {
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
}
