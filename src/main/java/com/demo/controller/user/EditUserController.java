package com.demo.controller.user;

import com.demo.entity.TableView.RoleInfo;
import com.demo.entity.TableView.UserAllInfo;
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
import lombok.SneakyThrows;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
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

    public void editUser(ActionEvent actionEvent) throws ParseException {
        userService.editUser(job_num, password, name, gender, employment_year, phone, email, roleId);
    }


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleData.addAll(userService.getRoleList());
        roleInfo.setItems(roleData);
        roleInfo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            roleId = newValue.getId();
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        UserAllInfo userAllInfo = userService.getUserAllInfo();
        job_num.setText(userAllInfo.getJob_num());
        password.setText(userAllInfo.getPassword());
        name.setText(userAllInfo.getName());
        gender.setText(userAllInfo.getGender());
        phone.setText(userAllInfo.getPhone());
        email.setText(userAllInfo.getEmail());
        roleInfo.getSelectionModel().select(userAllInfo.getRole_id() -1);

        Date date = dateFormat.parse(userAllInfo.getEmployment_year());
        employment_year.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
