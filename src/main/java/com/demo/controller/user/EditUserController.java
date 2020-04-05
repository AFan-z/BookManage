package com.demo.controller.user;

import com.demo.entity.TableView.RoleInfo;
import com.demo.service.UserService;
import com.demo.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {
    @FXML
    private TextField job_num;
    @FXML
    private TextField password;
    @FXML
    private TextField name;
    @FXML
    private TextField gender;
    @FXML
    private DatePicker employment_year;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<RoleInfo> roleInfo;
    private int roleId;

    private UserService userService = ServiceFactory.getUserServiceInstance();
    private ObservableList<RoleInfo> roleData = FXCollections.observableArrayList();

    public void editUser(ActionEvent actionEvent) {
        userService.editUser(job_num, password, name, gender, employment_year, phone, email, roleId);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleData.addAll(userService.getRoleList());
        roleInfo.setItems(roleData);
        roleInfo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            roleId = newValue.getId();
        });
    }
}
