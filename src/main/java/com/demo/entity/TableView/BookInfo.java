package com.demo.entity.TableView;

import com.demo.entity.Book;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookInfo {

    /**
     * 主键
     */
    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    /**
     * 编号
     */
    private SimpleStringProperty book_num = new SimpleStringProperty("");

    /**
     *书名
     */
    private SimpleStringProperty book_name = new SimpleStringProperty("");

    /**
     *出版社
     */
    private SimpleStringProperty publishing_house = new SimpleStringProperty("");

    /**
     *出版年限
     */
    private SimpleStringProperty publication_year = new SimpleStringProperty("");

    /**
     *价格
     */
    private SimpleDoubleProperty price = new SimpleDoubleProperty();

    /**
     *数量
     */
    private SimpleIntegerProperty number = new SimpleIntegerProperty();


    public BookInfo() {
    }

    public BookInfo(Book book){
        setId(book.getId());
        setBook_num(book.getBookNum());
        setBook_name(book.getBookName());
        setPublishing_house(book.getPublishingHouse());
        setPublication_year(book.getPublicationYear());
        setPrice(book.getPrice());
        setNumber(book.getNumber());
    }

    @Override
    public String toString() {
        return book_num.get() + "  " + book_name.get();
    }

    public String getBook_num() {
        return book_num.get();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleStringProperty book_numProperty() {
        return book_num;
    }

    public void setBook_num(String book_num) {
        this.book_num.set(book_num);
    }

    public String getBook_name() {
        return book_name.get();
    }

    public SimpleStringProperty book_nameProperty() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name.set(book_name);
    }

    public String getPublishing_house() {
        return publishing_house.get();
    }

    public SimpleStringProperty publishing_houseProperty() {
        return publishing_house;
    }

    public void setPublishing_house(String publishing_house) {
        this.publishing_house.set(publishing_house);
    }

    public String getPublication_year() {
        return publication_year.get();
    }

    public SimpleStringProperty publication_yearProperty() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year.set(publication_year);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }
}
