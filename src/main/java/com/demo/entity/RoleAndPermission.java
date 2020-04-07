package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class RoleAndPermission {

    /**
     * 角色id
     */
    private int roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色信息
     */
    private String roleInfo;

    /**
     * 权限id
     */
    private int permissionId;

    /**
     * 权限名
     */
    private String permissionName;

    /**
     * 权限信息
     */
    private String permissionInfo;

    @Tolerate
    public RoleAndPermission(){}
}
