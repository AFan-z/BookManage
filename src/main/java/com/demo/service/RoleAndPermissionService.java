package com.demo.service;

import com.demo.entity.TableView.PermissionInfo;
import com.demo.entity.TableView.RoleAndPermissionInfo;
import com.demo.entity.TableView.RoleInfo;
import com.demo.entity.TableView.UserAllInfo;
import com.demo.utils.enumeration.Operate;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public interface RoleAndPermissionService {

    void addButtonToTableView(String text, String theme, TableColumn<RoleAndPermissionInfo, RoleAndPermissionInfo> col, Operate operate, ObservableList<RoleAndPermissionInfo> data, TableView<RoleAndPermissionInfo> table);

    void newUserStage(String fxml) throws Exception;

    void newUserStage(String fxml, ObservableList<RoleAndPermissionInfo> data, TableView<RoleAndPermissionInfo> table) throws Exception;

    List<RoleAndPermissionInfo> getRoleAndPermList();

    List<RoleAndPermissionInfo> getRoleAndPermListByRoleId(int roleId);

    boolean addRoleAndPerm(int roleId, int permId);

    boolean deleteRoleAndPerm(int roleId, int permId);

    List<RoleInfo> getRoleList();

    List<PermissionInfo> getPermList();
}
