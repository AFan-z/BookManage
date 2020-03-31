package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;
@Data
@Builder
public class Userinfo {

    /**
     * 主键
     */
    private int id;

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
    private Date employment_year;

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

    @Tolerate
    public Userinfo(){}
}
