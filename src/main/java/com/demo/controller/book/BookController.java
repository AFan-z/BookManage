package com.demo.controller.book;

import com.demo.entity.TableView.BookInfo;
import com.demo.service.BookService;
import com.demo.utils.ExcelExport;
import com.demo.utils.enumeration.Operate;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookController implements Initializable {


    private BookService bookService = ServiceFactory.getBookServiceInstance();

    //布局文件中的表格视图对象，用来显示数据库中读取的所有图书信息
    @FXML
    private TableView<BookInfo> bookTable;

    //表格中的编辑列
    private TableColumn<BookInfo, BookInfo> editCol = new TableColumn<>("操作");
    //表格中的删除列
    private TableColumn<BookInfo, BookInfo> delCol = new TableColumn<>("操作");

    //布局文件中的输入文本框对象，用来输入搜索关键词
    @FXML
    private TextField keywordsField;

    //图书模型数据集合，可以实时相应数据变化，无需刷新
    private ObservableList<BookInfo> bookInfoData = FXCollections.observableArrayList();


    /**
     * 新增图书按钮
     * @param actionEvent
     * @throws IOException
     */
    public void newBookStage(ActionEvent actionEvent) throws Exception {
        bookService.newBookStage(ResourcesConfig.ADD_BOOK_FXML, bookInfoData, bookTable);
    }

    public void export(ActionEvent actionEvent) {
        ExcelExport.exportBook(bookService.getBookList());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("成功");
        alert.setHeaderText("图书数据已导出!请到D盘根目录查看!");
        alert.showAndWait();
    }

    public void search(ActionEvent actionEvent) {
        if (!keywordsField.getText().equals("")) {
            bookTable.getItems().removeAll(bookInfoData);
            showBookData(bookService.selectBookByBookNum(keywordsField.getText()));
        }
    }




    //表格初始化方法
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        //bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //调用查询所有图书的方法，
        //将实体集合作为参数，调用显示数据的方法，可以在界面的表格中显示图书模型集合的值
        showBookData(bookService.getBookList());

        //添加修改按钮
        bookService.addButtonToTableView("修改","blue-theme", editCol, Operate.UPDATE, bookInfoData, bookTable);
        //将编辑列加入图书表格
        bookTable.getColumns().add(editCol);

        //添加删除按钮
        bookService.addButtonToTableView("删除", "warning-theme", delCol, Operate.DELETE, bookInfoData, bookTable);
        bookTable.getColumns().add(delCol);
    }


    //显示图书表格数据的方法
    private void showBookData(List<BookInfo> bookInfoList) {
        bookInfoData.addAll(bookInfoList);
        bookTable.setItems(bookInfoData);
    }

    //初始化方法，通过调用对图书表格和列表下拉框的两个封装方法，实现数据初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }
}
