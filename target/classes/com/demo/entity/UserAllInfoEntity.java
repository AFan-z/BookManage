package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class UserAllInfoEntity {

    /**
     * 主键
     */
    private int id;

    /**
     * 账号（工号）
     */
    private String jobNum;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 登录次数
     */
    private int loginNum;

    /**
     * 用户信息id
     */
    private int userinfoId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 入职年份
     */
    private Date employmentYear;

    /**
     * 电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

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

    @Tolerate
    public UserAllInfoEntity(){}
}
