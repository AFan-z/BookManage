package com.demo.entity.TableView;

import com.demo.entity.Book;
import com.demo.entity.Borrow;
import com.demo.entity.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;


public class BorrowInfo {

    /**
     * 用户id
     */
    private SimpleIntegerProperty user_id = new SimpleIntegerProperty();

    /**
     * 账号（工号）
     */
    private SimpleStringProperty job_num = new SimpleStringProperty("");

    /**
     * 图书id
     */
    private SimpleIntegerProperty book_id = new SimpleIntegerProperty();

    /**
     * 图书编号
     */
    private SimpleStringProperty book_num = new SimpleStringProperty("");

    /**
     *书名
     */
    private SimpleStringProperty book_name = new SimpleStringProperty("");


    /**
     * 是否归还
     */
    private SimpleStringProperty isReturn = new SimpleStringProperty("");

    /**
     * 归还时间（30天）
     */
    private SimpleStringProperty return_time = new SimpleStringProperty("");

    /**
     * 续借次数
     */
    private SimpleIntegerProperty renew_num = new SimpleIntegerProperty();

    public BorrowInfo(User user, Book book, Borrow borrow){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        setUser_id(borrow.getUser_id());
        setJob_num(user.getJob_num());
        setBook_id(borrow.getBook_id());
        setBook_num(book.getBook_num());
        setBook_name(book.getBook_name());
        if(borrow.getIsReturn() == 0){
            setIsReturn("否");
        }else setIsReturn("是");
        setReturn_time(dateFormat.format(borrow.getReturn_time()));
        setRenew_num(borrow.getRenew_num());
    }

    public int getUser_id() {
        return user_id.get();
    }

    public SimpleIntegerProperty user_idProperty() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id.set(user_id);
    }

    public String getJob_num() {
        return job_num.get();
    }

    public SimpleStringProperty job_numProperty() {
        return job_num;
    }

    public void setJob_num(String job_num) {
        this.job_num.set(job_num);
    }

    public int getBook_id() {
        return book_id.get();
    }

    public SimpleIntegerProperty book_idProperty() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id.set(book_id);
    }

    public String getBook_num() {
        return book_num.get();
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

    public String getIsReturn() {
        return isReturn.get();
    }

    public SimpleStringProperty isReturnProperty() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn.set(isReturn);
    }

    public String getReturn_time() {
        return return_time.get();
    }

    public SimpleStringProperty return_timeProperty() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time.set(return_time);
    }

    public int getRenew_num() {
        return renew_num.get();
    }

    public SimpleIntegerProperty renew_numProperty() {
        return renew_num;
    }

    public void setRenew_num(int renew_num) {
        this.renew_num.set(renew_num);
    }
}
