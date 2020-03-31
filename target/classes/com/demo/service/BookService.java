package com.demo.service;

import com.demo.entity.TableView.BookInfo;
import com.demo.utils.Operate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;
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
    void addButtonToTableView(String text, String theme, TableColumn<BookInfo, BookInfo> editCol, Operate operate);

    /**
     * 返回图书列表
     *
     * @return
     */
    List<BookInfo> getBookList();

    void newBookStage(String fxml) throws Exception;
    void addBook(TextField bookNum, TextField bookName, TextField publishingHouse, TextField publicationYear, TextField price, TextField number);
    void editBook(TextField bookNum, TextField bookName, TextField publishingHouse, TextField publicationYear, TextField price, TextField number);


    }
