package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class User {

    /**
     * 主键
     */
    private int id;

    /**
     * 账号（工号）
     */
    private String job_num;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 登录次数
     */
    private int login_num;

    /**
     * 用户信息id
     */
    private int userinfo_id;

    /**
     * 角色表id
     */
    private int roleId;

    @Tolerate
    public User(){}
}
