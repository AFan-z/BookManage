package com.demo.mock;

import com.demo.entity.Role;
import com.demo.mapper.RoleMapper;

import java.util.ArrayList;
import java.util.List;

public class RoleMapperImpl implements RoleMapper {

    private List<Role> roles = new ArrayList<>();

    private Role role1 = Role.builder().id(1).roleName("SYSTEM_ADMIN").roleInfo("系统管理员").build();
    private Role role2 = Role.builder().id(2).roleName("BOOK_ADMIN").roleInfo("图书管理员").build();
    private Role role3 = Role.builder().id(3).roleName("BORROWER").roleInfo("借阅者").build();

    {
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
    }

    @Override
    public List<Role> select() {
        return roles;
    }
}
