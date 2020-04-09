package com.demo.controller.book;

import com.demo.entity.TableView.BookInfo;
import com.demo.entity.TableView.BookTypeInfo;
import com.demo.entity.TableView.RoleInfo;
import com.demo.service.BookService;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;

public class AddOrEditBookController  implements Initializable {

    @FXML
    private ComboBox<BookTypeInfo> bookType;
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

    private ObservableList<BookTypeInfo> bookTypeData = FXCollections.observableArrayList();
    private int typeId;
    private BookService bookService = ServiceFactory.getBookServiceInstance();

    /**
     * 新增确认键
     * @param actionEvent
     */
    public void addBook(ActionEvent actionEvent) {
        bookService.addBook(bookNum, bookName, publishingHouse, publicationYear, price, number, typeId);
    }

    /**
     * 修改确认键
     * @param actionEvent
     */
    public void editBook(ActionEvent actionEvent) {
        bookService.editBook(bookNum, bookName, publishingHouse, publicationYear, price, number, typeId);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 图书信息 - 修改： 预填入目标书籍的数据，便于用户修改
        if (url.toString().contains(ResourcesConfig.EDIT_BOOK_FXML)) {
            bookTypeData.addAll(bookService.getBookTypeList());
            bookType.setItems(bookTypeData);
            bookType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                typeId = newValue.getId();
            });
            BookInfo bookInfo = bookService.getBookInfo();
            bookNum.setText(bookInfo.getBook_num());
            bookName.setText(bookInfo.getBook_name());
            publishingHouse.setText(bookInfo.getPublishing_house());
            publicationYear.setText(bookInfo.getPublication_year());
            price.setText(String.valueOf(bookInfo.getPrice()));
            number.setText(String.valueOf(bookInfo.getNumber()));
        }else{
            bookTypeData.addAll(bookService.getBookTypeList());
            bookType.setItems(bookTypeData);
            bookType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                typeId = newValue.getId();
            });
        }
    }
}
