package com.demo.controller.book;

import com.demo.service.BookService;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddOrEditBookController {

    @FXML
    private TextField bookNum;
    @FXML
    private TextField bookName;
    @FXML
    private TextField publishingHouse;
    @FXML
    private TextField publicationYear;
    @FXML
    private TextField price;
    @FXML
    private TextField number;

    private BookService bookService = ServiceFactory.getBookServiceInstance();

    /**
     * 新增确认键
     * @param actionEvent
     */
    public void addBook(ActionEvent actionEvent) {
        bookService.addBook(bookNum, bookName, publishingHouse, publicationYear, price, number);
    }

    /**
     * 修改确认键
     * @param actionEvent
     */
    public void exitBook(ActionEvent actionEvent) {
        bookService.editBook(bookNum, bookName, publishingHouse, publicationYear, price, number);
    }
}
