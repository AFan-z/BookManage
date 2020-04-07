package com.demo.entity.TableView;

import com.demo.entity.RoleAndPermission;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RoleAndPermissionInfo {

    /**
     * 角色id
     */
    private SimpleIntegerProperty roleId = new SimpleIntegerProperty();

    /**
     * 角色名
     */
    private SimpleStringProperty roleName = new SimpleStringProperty("");

    /**
     * 角色信息
     */
    private SimpleStringProperty roleInfo = new SimpleStringProperty("");

    /**
     * 权限id
     */
    private SimpleIntegerProperty permissionId = new SimpleIntegerProperty();

    /**
     * 权限名
     */
    private SimpleStringProperty permissionName = new SimpleStringProperty("");

    /**
     * 权限信息
     */
    private SimpleStringProperty permissionInfo = new SimpleStringProperty("");


    public RoleAndPermissionInfo(RoleAndPermission roleAndPermission){
        setRoleId(roleAndPermission.getRoleId());
        setRoleName(roleAndPermission.getRoleName());
        setRoleInfo(roleAndPermission.getRoleInfo());
        setPermissionId(roleAndPermission.getPermissionId());
        setPermissionName(roleAndPermission.getPermissionName());
        setPermissionInfo(roleAndPermission.getPermissionInfo());
    }

    public RoleAndPermissionInfo(){}

    public int getRoleId() {
        return roleId.get();
    }

    public SimpleIntegerProperty roleIdProperty() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId.set(roleId);
    }

    public String getRoleName() {
        return roleName.get();
    }

    public SimpleStringProperty roleNameProperty() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName.set(roleName);
    }

    public String getRoleInfo() {
        return roleInfo.get();
    }

    public SimpleStringProperty roleInfoProperty() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo.set(roleInfo);
    }

    public int getPermissionId() {
        return permissionId.get();
    }

    public SimpleIntegerProperty permissionIdProperty() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId.set(permissionId);
    }

    public String getPermissionName() {
        return permissionName.get();
    }

    public SimpleStringProperty permissionNameProperty() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName.set(permissionName);
    }

    public String getPermissionInfo() {
        return permissionInfo.get();
    }

    public SimpleStringProperty permissionInfoProperty() {
        return permissionInfo;
    }

    public void setPermissionInfo(String permissionInfo) {
        this.permissionInfo.set(permissionInfo);
    }
}
