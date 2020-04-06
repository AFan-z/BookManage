package com.demo.controller.borrow;

import com.demo.entity.TableView.BookInfo;
import com.demo.entity.TableView.RoleInfo;
import com.demo.entity.TableView.UserAllInfo;
import com.demo.service.BookService;
import com.demo.service.BorrowService;
import com.demo.service.UserService;
import com.demo.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;


import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AddBorrowController implements Initializable {

    @FXML
    private ComboBox<BookInfo> bookNum;
    @FXML
    private ComboBox<UserAllInfo> jobNum;
    private ObservableList<BookInfo> bookDate = FXCollections.observableArrayList();
    private ObservableList<UserAllInfo> userDate = FXCollections.observableArrayList();
    private int bookId;
    private int userId;

    private BorrowService borrowService = ServiceFactory.getBorrowServiceInstance();
    private UserService userService = ServiceFactory.getUserServiceInstance();
    private BookService bookService = ServiceFactory.getBookServiceInstance();

    public void addBorrow(ActionEvent actionEvent) throws ParseException {
        borrowService.addBorrow(userId, bookId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookDate.addAll(bookService.getBookList());
        bookNum.setItems(bookDate);
        bookNum.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            bookId = newValue.getId();
        });
        userDate.addAll(userService.getUserList());
        jobNum.setItems(userDate);
        jobNum.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            userId = newValue.getId();
        });
    }
}
