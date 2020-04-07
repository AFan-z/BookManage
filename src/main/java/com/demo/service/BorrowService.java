package com.demo.service;

import com.demo.entity.TableView.BorrowInfo;
import com.demo.utils.enumeration.Operate;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.text.ParseException;
import java.util.List;

public interface BorrowService {

    void addButtonToTableView(String text, String theme, TableColumn<BorrowInfo, BorrowInfo> col, Operate operate, ObservableList<BorrowInfo> borrowData, TableView<BorrowInfo> borrowTable);

    /**
     * 获得所有人的借阅信息
     * @return
     * @throws ParseException
     */
    List<BorrowInfo> getBorrowList();

    /**
     * 获得个人的借阅信息
     * @return
     */
    List<BorrowInfo> getBorrowPersonList() throws ParseException;

    List<BorrowInfo> selectBorrowByJobNum(String jobNum);

    List<BorrowInfo> selectBorrowByBookNum(String bookNum);

    void newBorrowStage(String fxml) throws Exception;

    void newBorrowStage(String fxml, ObservableList<BorrowInfo> borrowData, TableView<BorrowInfo> borrowTable) throws Exception;


    boolean exitBorrow(ComboBox<String> isReturn, DatePicker returnTime, TextField renewNum) throws ParseException;

    boolean addBorrow(int userId, int bookId) throws ParseException;

    boolean deleteBorrow();
}
