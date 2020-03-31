package com.demo.service.Impl;

import com.demo.entity.Book;
import com.demo.entity.TableView.BookInfo;
import com.demo.service.BookService;
import com.demo.utils.ComponentUtil;
import com.demo.utils.Operate;
import com.demo.utils.ResourcesConfig;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {


    //获取所要修改或删除的图书信息
    private static BookInfo bookInfo;

    @Override
    public void addButtonToTableView(String text, String theme, TableColumn<BookInfo, BookInfo> col, Operate operate) {

        //操作列的相关设置
        col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        col.setCellFactory(param -> new TableCell<BookInfo, BookInfo>() {
            //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
            private final Button editButton = ComponentUtil.getButton(text, theme);

            @Override
            protected void updateItem(BookInfo book, boolean empty) {
                super.updateItem(book, empty);
                if (book == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(editButton);
                editButton.setOnAction(event ->{
                    bookInfo = book;

                    switch (operate){
                        case ADD://增
                            break;
                        case DELETE:// TODO 删
//                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                            alert.setTitle("提示");
//                            alert.setContentText("！！！！");
//                            alert.showAndWait();
                            break;
                        case UPDATE:// TODO 改
                            try {
                                newBookStage(ResourcesConfig.EDIT_BOOK_FXML);
                                System.out.println(book.getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case SELECT: //查
                            break;
                    }
                });
            }
        });
    }

    @Override
    public List<BookInfo> getBookList() {

        //TODO 图书集合，存放数据库图书表各种查询的结果
            List<BookInfo> bookInfoList = new ArrayList<>();

            //假数据
            Book book1 = new Book(1,"sa123456789","测试1","机械工业出版","2020",44.21,10);
            Book book2 = new Book(2,"5402451dsf","测试2","机械工业出版","2018",4.21,88);

            BookInfo bookInfo1 = new BookInfo(book1);
            BookInfo bookInfo2 = new BookInfo(book2);

            bookInfoList.add(bookInfo1);
            bookInfoList.add(bookInfo2);
            bookInfoList.add(bookInfo1);
            bookInfoList.add(bookInfo2);
            bookInfoList.add(bookInfo1);
            bookInfoList.add(bookInfo2);
            bookInfoList.add(bookInfo2);
            bookInfoList.add(bookInfo1);
            bookInfoList.add(bookInfo1);
            bookInfoList.add(bookInfo2);

        return bookInfoList;
    }

    @Override
    public void newBookStage(String fxml) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(ResourcesConfig.CSS_STYLE);
        stage.setTitle(ResourcesConfig.BOOK_MANAGE_TITLE);
        stage.getIcons().add(new Image(ResourcesConfig.IMG_LOGO));
        //界面大小不可变
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void addBook(TextField bookNum, TextField bookName, TextField publishingHouse, TextField publicationYear, TextField price, TextField number) {
        //TODO 调用数据库添加新增数据

        System.out.println(bookNum.getText());

        //弹窗
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("新增图书成功!");
        alert.showAndWait();
        Stage stage = (Stage) bookName.getScene().getWindow();
        stage.close();
    }


    @Override
    public void editBook(TextField bookNum, TextField bookName, TextField publishingHouse, TextField publicationYear, TextField price, TextField number) {
        //TODO 调用数据库添加修改数据
        System.out.println(bookInfo.getId());

        System.out.println(bookNum.getText());


        //弹窗
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("修改图书成功!");
        alert.showAndWait();
        Stage stage = (Stage) bookName.getScene().getWindow();
        stage.close();
    }

}
