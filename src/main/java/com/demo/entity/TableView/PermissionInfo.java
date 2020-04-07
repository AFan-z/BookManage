package com.demo.entity.TableView;

import com.demo.entity.Permission;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PermissionInfo {
    /**
     * 权限id
     */
    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    /**
     * 权限名
     */
    private SimpleStringProperty permissionName = new SimpleStringProperty("");

    /**
     * 权限信息
     */
    private SimpleStringProperty permissionInfo = new SimpleStringProperty("");

    public PermissionInfo(Permission permission){
        setId(permission.getId());
        setPermissionName(permission.getPermissionName());
        setPermissionInfo(permission.getPermissionInfo());
    }

    public PermissionInfo(){}

    @Override
    public String toString() {
        return id.get() +"-"+ permissionName.get();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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
