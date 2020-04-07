package com.demo.controller.permission;

import com.demo.entity.TableView.OperationInfo;
import com.demo.entity.TableView.RoleAndPermissionInfo;
import com.demo.entity.TableView.UserAllInfo;
import com.demo.service.RoleAndPermissionService;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import com.demo.utils.enumeration.Operate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RoleAndPermissionController implements Initializable {
    @FXML
    private TextField keywordsField;
    @FXML
    private TableView<RoleAndPermissionInfo> roleAndPermTable;

    private ObservableList<RoleAndPermissionInfo> roleAndPermissionDate = FXCollections.observableArrayList();

    //表格中的修改列
    private TableColumn<RoleAndPermissionInfo, RoleAndPermissionInfo> editCol = new TableColumn<>("操作");
    //表格中的删除列
    private TableColumn<RoleAndPermissionInfo, RoleAndPermissionInfo> delCol = new TableColumn<>("操作");


    private RoleAndPermissionService roleAndPermissionService = ServiceFactory.getRoleAndPermissionServiceInstance();
    public void newUserStage(ActionEvent actionEvent) throws Exception {
        roleAndPermissionService.newUserStage(ResourcesConfig.ADD_ROLE_AND_PERMISSION_FXML, roleAndPermissionDate, roleAndPermTable);
    }

    public void search(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(keywordsField.getText());
            roleAndPermTable.getItems().removeAll(roleAndPermissionDate);
            //将实体集合作为参数，调用显示数据的方法
            roleAndPermissionDate.addAll(roleAndPermissionService.getRoleAndPermListByRoleId(id));
            roleAndPermTable.setItems(roleAndPermissionDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //表格初始化方法
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        roleAndPermTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //将实体集合作为参数，调用显示数据的方法
        roleAndPermissionDate.addAll(roleAndPermissionService.getRoleAndPermList());
        roleAndPermTable.setItems(roleAndPermissionDate);

        //添加修改按钮
        //roleAndPermissionService.addButtonToTableView("修改","blue-theme", editCol, Operate.UPDATE, roleAndPermissionDate, roleAndPermTable);
        //roleAndPermTable.getColumns().add(editCol);

        //添加删除按钮
        roleAndPermissionService.addButtonToTableView("删除", "warning-theme", delCol, Operate.DELETE, roleAndPermissionDate, roleAndPermTable);
        roleAndPermTable.getColumns().add(delCol);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }
}
