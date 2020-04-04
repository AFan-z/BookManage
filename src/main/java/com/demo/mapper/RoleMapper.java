package com.demo.mapper;

import com.demo.entity.Role;

import java.util.List;

public interface RoleMapper {
//    @SQL("SELECT * FROM role")
    List<Role> select();
}
