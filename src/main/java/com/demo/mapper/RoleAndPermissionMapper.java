package com.demo.mapper;


import com.demo.entity.RoleAndPermission;
import org.yu.myorm.core.dynproxy.SQL;

import java.util.List;

public interface RoleAndPermissionMapper {

    @SQL("SELECT role_id, role_info, role_name, permission_id, permission_name, permission_info FROM role,role_and_permission,permission " +
            "WHERE role_and_permission.permission_id = permission.id " +
            "AND role.id = role_and_permission.role_id")
    List<RoleAndPermission> select();

    @SQL("SELECT role_id, role_info, role_name, permission_id, permission_name, permission_info FROM role,role_and_permission,permission " +
            "WHERE role_and_permission.permission_id = permission.id " +
            "AND role.id = role_and_permission.role_id " +
            "AND role_id = ?")
    List<RoleAndPermission> select(int role_id);

    @SQL("INSERT INTO role_and_permission VALUES (?,?)")
    boolean insert(int role_id, int permission_id);

    @SQL("DELETE FROM role_and_permission WHERE role_id = ? AND permission_id = ?")
    boolean delete(int role_id, int permission_id);
}
