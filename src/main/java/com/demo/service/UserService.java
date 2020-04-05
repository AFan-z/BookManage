package com.demo.service;

import com.demo.entity.TableView.RoleInfo;
import com.demo.entity.TableView.UserAllInfo;
import com.demo.utils.Operate;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.util.List;

public interface UserService {

    void addButtonToTableView(String text, String theme, TableColumn<UserAllInfo, UserAllInfo> col, Operate operate);

    List<UserAllInfo> getUserList();

    void getUserInfo(Label job_num, Label password, Label name, Label gender, Label employment_year, Label phone, Label email, Label login_time, Label last_login_time, Label login_num);

    void newUserStage(String fxml) throws Exception;

    void addUser(TextField job_num, TextField password, TextField name, TextField gender, DatePicker employment_year, TextField phone, TextField email, int roleId);

    void editUser(TextField job_num, TextField password, TextField name, TextField gender, DatePicker employment_year, TextField phone, TextField email, int roleId);

    List<RoleInfo> getRoleList();

    void editPersonal(TextField password, TextField name, TextField gender, TextField email, TextField phone);
}
