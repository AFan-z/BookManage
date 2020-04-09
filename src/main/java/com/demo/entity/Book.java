package com.demo.entity;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Book {

    /**
     * 主键
     */
    private int id;

    /**
     * 图书类型ID
     */
    private int typeId;

    /**
     * 图书类型
     */
    private String typeName;

    /**
     * 编号
     */
    private String bookNum;

    /**
     *书名
     */
    private String bookName;

    /**
     *出版社
     */
    private String publishingHouse;

    /**
     *出版年限
     */
    private String publicationYear;

    /**
     *价格
     */
    private double price;

    /**
     *数量
     */
    private int number;


    @Tolerate
    public Book(){}
}
