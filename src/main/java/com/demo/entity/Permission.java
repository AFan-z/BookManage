package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Permission {

    /**
     * 权限id
     */
    private int id;

    /**
     * 权限名
     */
    private String permissionName;

    /**
     * 权限信息
     */
    private String permissionInfo;

    @Tolerate
    public Permission(){}
}
