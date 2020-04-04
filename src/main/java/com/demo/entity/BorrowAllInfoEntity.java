package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class BorrowAllInfoEntity {


    /**
     * 用户id
     */
    private int userId;

    /**
     * 账号（工号）
     */
    private String jobNum;

    /**
     * 图书id
     */
    private int bookId;

    /**
     * 图书编号
     */
    private String bookNum;

    /**
     *书名
     */
    private String bookName;


    /**
     * 是否归还
     */
    private int isReturn;

    /**
     * 归还时间（30天）
     */
    private Date returnTime;

    /**
     * 续借次数
     */
    private int renewNum;

    @Tolerate
    public BorrowAllInfoEntity(){}
}
