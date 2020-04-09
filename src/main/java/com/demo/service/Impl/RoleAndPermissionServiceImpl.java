package com.demo.service.Impl;

import com.demo.entity.*;
import com.demo.entity.TableView.*;
import com.demo.mapper.OperationMapper;
import com.demo.mapper.RoleAndPermissionMapper;
import com.demo.mapper.RoleMapper;
import com.demo.service.RoleAndPermissionService;
import com.demo.utils.ComponentUtil;
import com.demo.utils.CurrentUser;
import com.demo.utils.MapperFactory;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.enumeration.Operate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.yaml.snakeyaml.error.YAMLException;
import org.yu.myorm.core.Exception.NoSuchDataInDBException;
import org.yu.myorm.core.handleErr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleAndPermissionServiceImpl implements RoleAndPermissionService {

    private static ObservableList<RoleAndPermissionInfo> roleAndPermissionDate;
    private static TableView<RoleAndPermissionInfo> roleAndPermissionInfoTable;
    private static RoleAndPermissionInfo roleAndPermissionInfo;
    private RoleAndPermissionMapper roleAndPermissionMapper = MapperFactory.getRoleAndPermissionMapperInstance();
    private OperationMapper operationMapper = MapperFactory.getOperationMapperInstance();
    private RoleMapper roleMapper = MapperFactory.getRoleMapperInstance();


    @Override
    public void addButtonToTableView(String text, String theme, TableColumn<RoleAndPermissionInfo, RoleAndPermissionInfo> col, Operate operate, ObservableList<RoleAndPermissionInfo> data, TableView<RoleAndPermissionInfo> table) {
        //操作列的相关设置
        col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        col.setCellFactory(param -> new TableCell<RoleAndPermissionInfo, RoleAndPermissionInfo>() {
            //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
            private final Button editButton = ComponentUtil.getButton(text, theme);

            @Override
            protected void updateItem(RoleAndPermissionInfo perm, boolean empty) {
                super.updateItem(perm, empty);
                if (perm == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                editButton.setOnAction(event ->{
                    roleAndPermissionInfo = perm;
                    switch (operate){
                        case ADD://增
                            break;
                        case DELETE://删
                            deleteRoleAndPerm(perm.getRoleId(), perm.getPermissionId());
                            data.remove(perm);
                            break;
                        case UPDATE://改
                            try {
                                roleAndPermissionDate = data;
                                roleAndPermissionInfoTable = table;
                                //newUserStage(ResourcesConfig.EDIT_USER_FXML);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case SELECT://查
                            break;
                    }
                });
            }
        });
    }

    @Override
    public void newUserStage(String fxml) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(ResourcesConfig.CSS_STYLE);
        stage.setTitle(ResourcesConfig.BOOK_MANAGE_TITLE);
        stage.getIcons().add(new Image(ResourcesConfig.IMG_LOGO));
        //界面大小不可变
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void newUserStage(String fxml, ObservableList<RoleAndPermissionInfo> data, TableView<RoleAndPermissionInfo> table) throws Exception {
        roleAndPermissionDate = data;
        roleAndPermissionInfoTable = table;
        newUserStage(fxml);
    }

    @Override
    public List<RoleAndPermissionInfo> getRoleAndPermList() {
        List<RoleAndPermissionInfo> roleAndPermissionInfos = new ArrayList<>();

        try {
            List<RoleAndPermission> entities = roleAndPermissionMapper.select();
            for (RoleAndPermission entity : entities){
                RoleAndPermissionInfo roleAndPermissionInfo = new RoleAndPermissionInfo(entity);
                roleAndPermissionInfos.add(roleAndPermissionInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, "No Such Data In DB!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return roleAndPermissionInfos;
    }

    @Override
    public List<RoleAndPermissionInfo> getRoleAndPermListByRoleId(int roleId) {
        List<RoleAndPermissionInfo> roleAndPermissionInfos = new ArrayList<>();

        try {
            List<RoleAndPermission> entities = roleAndPermissionMapper.select(roleId);
            for (RoleAndPermission entity : entities){
                RoleAndPermissionInfo roleAndPermissionInfo = new RoleAndPermissionInfo(entity);
                roleAndPermissionInfos.add(roleAndPermissionInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, "No Such Data In DB!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return roleAndPermissionInfos;
    }

    @Override
    public boolean addRoleAndPerm(int roleId, int permId) {
        boolean flag = false;
        if (roleId == 0 || permId == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示信息");
            alert.setHeaderText("请补全信息！！！");
            alert.showAndWait();
            return flag;
        }
        try {
            boolean b = roleAndPermissionMapper.insert(roleId, permId);
            if (b){
                //操作日志
                Operation operation = Operation.builder()
                        .operationInfo("添加角色权限信息, 角色ID号："+ roleId + ",权限ID号为：" + permId)
                        .operationTime(new Date())
                        .operationUser(CurrentUser.getUserAllInfo().getId())
                        .build();
                operationMapper.insert(operation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("成功");
                alert.setContentText("添加角色权限信息成功！！！");
                alert.showAndWait();
                roleAndPermissionInfoTable.getItems().removeAll(roleAndPermissionDate);
                roleAndPermissionDate.addAll(getRoleAndPermList());
                roleAndPermissionInfoTable.setItems(roleAndPermissionDate);
                flag = true;
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("失败");
                alert.setContentText("添加角色权限信息失败！！！");
                alert.showAndWait();
            }
        } catch (NoSuchDataInDBException dbe) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("失败");
            alert.setContentText("权限信息已存在，请更改！！！");
            alert.showAndWait();
            handleErr.printErr(dbe, "No Such Data In DB!", false);
        } catch (Exception e3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("失败");
            alert.setContentText("权限信息已存在，请更改！！！");
            alert.showAndWait();
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return flag;
    }

    @Override
    public boolean deleteRoleAndPerm(int roleId, int permId) {
        boolean flag = false;
        try {
            boolean b = roleAndPermissionMapper.delete(roleId, permId);
            if (b){
                //操作日志
                Operation operation = Operation.builder()
                        .operationInfo("删除借阅信息, 角色ID号："+ roleId + ",权限ID号为：" + permId)
                        .operationTime(new Date())
                        .operationUser(CurrentUser.getUserAllInfo().getId())
                        .build();
                operationMapper.insert(operation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("成功");
                alert.setContentText("删除角色权限信息成功！！！");
                alert.showAndWait();
                flag = true;
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("失败");
                alert.setContentText("添加角色权限信息失败！！！");
                alert.showAndWait();
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, "No Such Data In DB!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return flag;
    }

    @Override
    public List<RoleInfo> getRoleList() {
        List<RoleInfo> roleList = new ArrayList<>();
        try {
            List<Role> roles = roleMapper.selectRole();
            for (Role role : roles) {
                RoleInfo roleInfo = new RoleInfo(role);
                roleList.add(roleInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, "No Such Data In DB!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return roleList;
    }

    @Override
    public List<PermissionInfo> getPermList() {
        List<PermissionInfo> permList = new ArrayList<>();
        try {
            List<Permission> perms = roleMapper.selectPerm();
            for (Permission perm : perms) {
                PermissionInfo permInfo = new PermissionInfo(perm);
                permList.add(permInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, "No Such Data In DB!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return permList;
    }
}
