package com.demo.service.Impl;

import com.demo.entity.Book;
import com.demo.entity.TableView.BookInfo;
import com.demo.mapper.BookMapper;
import com.demo.service.BookService;
import com.demo.utils.ComponentUtil;
import com.demo.utils.MapperFactory;
import com.demo.utils.enumeration.Operate;
import com.demo.utils.ResourcesConfig;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.yaml.snakeyaml.error.YAMLException;
import org.yu.myorm.core.Exception.NoSuchDataInDBException;
import org.yu.myorm.core.handleErr;
//import org.yu.myorm.core.Exception.NoSuchDataInDBException;
//import org.yu.myorm.core.handleErr;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private static ObservableList<BookInfo> bookDataInfo;
    private static TableView<BookInfo> bookTableInfo;
    //获取所要修改或删除的图书信息
    private static BookInfo bookInfo;
    private BookMapper bookMapper = MapperFactory.getBookMapperInstance();

    @Override
    public void addButtonToTableView(String text, String theme, TableColumn<BookInfo, BookInfo> col, Operate operate,
                                     ObservableList<BookInfo> bookInfoData, TableView<BookInfo> bookTable) {

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
                        case DELETE:
                            deleteBook();
                            bookInfoData.remove(book);
                            break;
                        case UPDATE:
                            try {
                                bookDataInfo = bookInfoData;
                                bookTableInfo =bookTable;
                                newBookStage(ResourcesConfig.EDIT_BOOK_FXML);
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

            List<BookInfo> bookInfoList = new ArrayList<>();
            try {
                List<Book> bookLists = bookMapper.select();

                for (Book book : bookLists){
                    BookInfo bookInfo = new BookInfo(book);
                    bookInfoList.add(bookInfo);
                }
            } catch (NoSuchDataInDBException dbe) {
                handleErr.printErr(dbe, dbe.getMessage(), false);
            }catch (YAMLException e2) {
                handleErr.printErr(e2, "LOAD OBJECT FROM YAML FAILED!", false);
            } catch (Exception e3) {
                handleErr.printErr(e3, "EXCEPTION!!!", true);
            }
        return bookInfoList;
    }

    @Override
    public List<BookInfo> selectBookByBookNum(String bookNum) {
        List<BookInfo> bookInfoList = new ArrayList<>();
        try {
             Book book = bookMapper.select(bookMapper.select(bookNum));
             BookInfo bookInfo = new BookInfo(book);
             bookInfoList.add(bookInfo);

        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        }catch (YAMLException e2) {
            handleErr.printErr(e2, "LOAD OBJECT FROM YAML FAILED!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
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
    public void newBookStage(String fxml, ObservableList<BookInfo> bookInfoData, TableView<BookInfo> bookTable) throws Exception {
        bookDataInfo = bookInfoData;
        bookTableInfo = bookTable;
        newBookStage(fxml);
    }

    @Override
    public boolean addBook(TextField bookNum, TextField bookName, TextField publishingHouse, TextField publicationYear, TextField price, TextField number) {

        boolean flag = false;

        if(bookNum.getText().equals("") || bookName.getText().equals("") || publishingHouse.getText().equals("") || publicationYear.getText().equals("") || price.getText().equals("") || number.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示信息");
            alert.setHeaderText("请补全信息！！！");
            alert.showAndWait();
            return flag;
        }

        Book book = Book.builder()
                .bookNum(bookNum.getText())
                .bookName(bookName.getText())
                .publishingHouse(publishingHouse.getText())
                .publicationYear(publicationYear.getText())
                .price(Double.valueOf(price.getText()))
                .number(Integer.parseInt(number.getText()))
                .build();

        try {
            boolean b = bookMapper.insert(book);
            if (b){
                //弹窗
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("新增图书成功!!!");
                alert.showAndWait();
                Stage stage = (Stage) bookName.getScene().getWindow();
                stage.close();
                bookTableInfo.getItems().removeAll(bookDataInfo);
                bookDataInfo.addAll(getBookList());
                bookTableInfo.setItems(bookDataInfo);
            }else {
                //弹窗
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("新增图书失败!!!");
                alert.showAndWait();
                Stage stage = (Stage) bookName.getScene().getWindow();
                stage.close();
            }
            flag = b;
        } catch (NoSuchDataInDBException dbe) {
         handleErr.printErr(dbe, dbe.getMessage(), false);
        }catch (YAMLException e2) {
            handleErr.printErr(e2, "LOAD OBJECT FROM YAML FAILED!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return false;
    }


    @Override
    public boolean editBook(TextField bookNum, TextField bookName, TextField publishingHouse, TextField publicationYear, TextField price, TextField number) {
        boolean flag = false;
        if(bookNum.getText().equals("")){
            bookNum.setText(bookInfo.getBook_num());
        }
        if(bookName.getText().equals("")){
            bookName.setText(bookInfo.getBook_name());
        }
        if(publishingHouse.getText().equals("")){
            publishingHouse.setText(bookInfo.getPublishing_house());
        }
        if(publicationYear.getText().equals("")){
            publicationYear.setText(bookInfo.getPublication_year());
        }
        if(price.getText().equals("")){
            price.setText(bookInfo.getPrice()+"");
        }
        if(number.getText().equals("")){
            number.setText(bookInfo.getNumber()+"");
        }

        try {

            boolean b = bookMapper.update(bookNum.getText(), bookName.getText(), publishingHouse.getText(),
                    publicationYear.getText(),Double.valueOf(price.getText()), Integer.parseInt(number.getText()), bookInfo.getId());
            if (b) {
                //弹窗
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("修改图书成功！！！");
                alert.showAndWait();
                Stage stage = (Stage) bookName.getScene().getWindow();
                stage.close();
                bookTableInfo.getItems().removeAll(bookDataInfo);
                bookDataInfo.addAll(getBookList());
                bookTableInfo.setItems(bookDataInfo);
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("修改图书失败！！！");
                alert.showAndWait();
                Stage stage = (Stage) bookName.getScene().getWindow();
                stage.close();
            }
            flag = b;
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        }catch (YAMLException e2) {
            handleErr.printErr(e2, "LOAD OBJECT FROM YAML FAILED!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return flag;
    }

    @Override
    public boolean deleteBook() {
        boolean flag = false;
        try {
            boolean b = bookMapper.delete(bookInfo.getId());
            if (b) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("删除图书成功!!!");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("删除图书失败!!!");
                alert.showAndWait();
            }
            flag = b;
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        }catch (YAMLException e2) {
            handleErr.printErr(e2, "LOAD OBJECT FROM YAML FAILED!", false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return flag;
    }
}
