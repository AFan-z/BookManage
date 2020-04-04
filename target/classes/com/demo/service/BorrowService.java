package com.demo.service;

import com.demo.entity.TableView.BorrowInfo;
import com.demo.entity.TableView.UserAllInfo;
import com.demo.utils.Operate;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.util.List;

public interface BorrowService {

    void addButtonToTableView(String text, String theme, TableColumn<BorrowInfo, BorrowInfo> col, Operate operate);

    /**
     * 获得所有人的借阅信息
     * @return
     * @throws ParseException
     */
    List<BorrowInfo> getBorrowList() throws ParseException;

    /**
     * 获得个人的借阅信息
     * @return
     */
    List<BorrowInfo> getBorrowPersonList() throws ParseException;

    void newBorrowStage(String fxml) throws Exception;

    boolean exitBorrow(ComboBox<String> isReturn, DatePicker returnTime, TextField renewNum) throws ParseException;

    boolean addBorrow(TextField jobNum, TextField bookNum) throws ParseException;
}
