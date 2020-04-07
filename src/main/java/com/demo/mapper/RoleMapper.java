package com.demo.mapper;

import com.demo.entity.Permission;
import com.demo.entity.Role;
import org.yu.myorm.core.dynproxy.SQL;
//import org.yu.myorm.core.dynproxy.SQL;

import java.util.List;

public interface RoleMapper {
    @SQL("SELECT * FROM role")
    List<Role> selectRole();

    @SQL("SELECT * FROM permission")
    List<Permission> selectPerm();
}
