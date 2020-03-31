package com.demo.entity;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class Book {

    /**
     * 主键
     */
    private int id;

    /**
     * 编号
     */
    private String book_num;

    /**
     *书名
     */
    private String book_name;

    /**
     *出版社
     */
    private String publishing_house;

    /**
     *出版年限
     */
    private String publication_year;

    /**
     *价格
     */
    private double price;

    /**
     *数量
     */
    private int number;


    public Book(int id, String book_num, String book_name, String publishing_house, String publication_year, double price, int number) {
        this.id = id;
        this.book_num = book_num;
        this.book_name = book_name;
        this.publishing_house = publishing_house;
        this.publication_year = publication_year;
        this.price = price;
        this.number = number;
    }
}
