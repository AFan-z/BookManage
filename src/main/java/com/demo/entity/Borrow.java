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
    private int user_id;

    /**
     * 图书id
     */
    private int book_id;

    /**
     * 是否归还
     */
    private int isReturn;

    /**
     * 归还时间（30天）
     */
    private Date return_time;

    /**
     * 续借次数
     */
    private int renew_num;

    @Tolerate
    public Borrow(){}
}
