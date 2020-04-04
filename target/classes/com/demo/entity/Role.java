package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Role {
    private int id;
    private String roleName;
    private String roleInfo;

    @Tolerate
    public Role(){}
}
