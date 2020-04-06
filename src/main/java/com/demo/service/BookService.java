package com.demo.service;

import com.demo.entity.TableView.BookInfo;
import com.demo.utils.enumeration.Operate;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public interface BookService {

    /**
     * 向图书表中添加操作按钮
     *
     * @param text    按钮名称
     * @param theme   按钮对呀的css样式
     * @param editCol 对应的表格
     * @param operate 选择操作类型
     */
    void addButtonToTableView(String text, String theme, TableColumn<BookInfo, BookInfo> editCol, Operate operate, ObservableList<BookInfo> bookInfoData, TableView<BookInfo> bookTable);

    /**
     * 返回图书列表
     *
     * @return
     */
    List<BookInfo> getBookList();

    List<BookInfo> selectBookByBookNum(String bookNum);

    void newBookStage(String fxml) throws Exception;

    void newBookStage(String fxml, ObservableList<BookInfo> bookInfoData, TableView<BookInfo> bookTable) throws Exception;

    boolean addBook(TextField bookNum, TextField bookName, TextField publishingHouse, TextField publicationYear, TextField price, TextField number);
    boolean editBook(TextField bookNum, TextField bookName, TextField publishingHouse, TextField publicationYear, TextField price, TextField number);
    boolean deleteBook();


    }
