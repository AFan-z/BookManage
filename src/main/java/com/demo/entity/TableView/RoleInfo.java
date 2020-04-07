package com.demo.entity.TableView;

import com.demo.entity.Role;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RoleInfo {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty role_name = new SimpleStringProperty("");
    private final SimpleStringProperty role_info = new SimpleStringProperty("");

    public RoleInfo(Role role){
        setId(role.getId());
        setRole_name(role.getRoleName());
        setRole_info(role.getRoleInfo());
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

    public String getRole_name() {
        return role_name.get();
    }

    public SimpleStringProperty role_nameProperty() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name.set(role_name);
    }

    public String getRole_info() {
        return role_info.get();
    }

    public SimpleStringProperty role_infoProperty() {
        return role_info;
    }

    public void setRole_info(String role_info) {
        this.role_info.set(role_info);
    }

    @Override
    public String toString() {
        return id.get() +"-"+ role_info.get();
    }
}
