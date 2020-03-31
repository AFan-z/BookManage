package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Role {
    private int id;
    private String role_name;
    private String role_info;

    @Tolerate
    public Role(){}
}
