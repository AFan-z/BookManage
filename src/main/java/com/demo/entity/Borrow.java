package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class Borrow {

    /**
     * 用户id
     */
    private int userId;

    /**
     * 图书id
     */
    private int bookId;

    /**
     * 是否归还
     */
    private int isReturn;

    /**
     * 归还时间（30天）
     */
    private Date returnTime;

    /**
     * 续借时间
     */
    private Date renewTime;

    /**
     * 续借次数
     */
    private int renewNum;



    @Tolerate
    public Borrow(){}
}
