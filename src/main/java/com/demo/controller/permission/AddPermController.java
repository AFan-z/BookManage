package com.demo.controller.permission;

import com.demo.entity.TableView.BookInfo;
import com.demo.entity.TableView.PermissionInfo;
import com.demo.entity.TableView.RoleInfo;
import com.demo.service.RoleAndPermissionService;
import com.demo.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPermController implements Initializable {
    @FXML
    private ComboBox<RoleInfo> RoleID;
    @FXML
    private ComboBox<PermissionInfo> PermID;
    private ObservableList<RoleInfo> roleDate = FXCollections.observableArrayList();
    private ObservableList<PermissionInfo> permDate = FXCollections.observableArrayList();
    private int roleId;
    private int permId;

    private RoleAndPermissionService roleAndPermissionService = ServiceFactory.getRoleAndPermissionServiceInstance();

    public void addBorrow(ActionEvent actionEvent) {
        roleAndPermissionService.addRoleAndPerm(roleId, permId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleDate.addAll(roleAndPermissionService.getRoleList());
        RoleID.setItems(roleDate);
        RoleID.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            roleId = newValue.getId();
        });

        permDate.addAll(roleAndPermissionService.getPermList());
        PermID.setItems(permDate);
        PermID.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            permId = newValue.getId();
        });
    }
}
