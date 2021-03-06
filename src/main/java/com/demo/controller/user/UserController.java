package com.demo.controller.user;

import com.demo.entity.TableView.UserAllInfo;
import com.demo.service.UserService;
import com.demo.utils.ExcelExport;
import com.demo.utils.enumeration.Operate;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    private UserService userService = ServiceFactory.getUserServiceInstance();

    @FXML
    private TextField keywordsField;

    @FXML
    private TableView<UserAllInfo> userTable;
    private ObservableList<UserAllInfo> userData = FXCollections.observableArrayList();
    //表格中的修改列
    private TableColumn<UserAllInfo, UserAllInfo> editCol = new TableColumn<>("操作");
    //表格中的删除列
    private TableColumn<UserAllInfo, UserAllInfo> delCol = new TableColumn<>("操作");


    public void newUserStage(ActionEvent actionEvent) throws Exception {
        userService.newUserStage(ResourcesConfig.ADD_USER_FXML, userData, userTable);
    }

    public void export(ActionEvent actionEvent) {
        String fileName = "D:\\users.xlsx";

        try {
            ExcelExport.exportExcel(userService.getUserList(), fileName);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示信息");
            alert.setHeaderText("成功");
            alert.setContentText("用户信息数据已导出!请到D盘根目录查看!");
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示信息");
            alert.setHeaderText("失败");
            alert.setContentText("失败，无法导出最新数据");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void search(ActionEvent actionEvent) {
        userTable.getItems().removeAll(userData);
        showUserData(userService.selectUserByJobNum("%"+keywordsField.getText()+"%"));
    }


    public void refresh(ActionEvent actionEvent) {
        userTable.getItems().removeAll(userData);
        showUserData(userService.getUserList());
    }

    //表格初始化方法
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        //userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //将实体集合作为参数，调用显示数据的方法
        showUserData(userService.getUserList());

        //添加修改按钮
        userService.addButtonToTableView("修改","blue-theme", editCol, Operate.UPDATE, userData, userTable);
        userTable.getColumns().add(editCol);

        //添加删除按钮
        userService.addButtonToTableView("删除", "warning-theme", delCol, Operate.DELETE, userData, userTable);
        userTable.getColumns().add(delCol);
    }


    private void showUserData(List<UserAllInfo> userList) {
        userData.addAll(userList);
        userTable.setItems(userData);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }

}
