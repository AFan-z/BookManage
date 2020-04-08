package com.demo.controller.user;

import com.demo.entity.TableView.OperationInfo;
import com.demo.service.OperationService;
import com.demo.utils.CurrentUser;
import com.demo.utils.ExcelExport;
import com.demo.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalOperationController implements Initializable {

/*    @FXML
    private TextField keywordsField;*/
    @FXML
    private TableView<OperationInfo> operationTable;

    private ObservableList<OperationInfo> operationDate = FXCollections.observableArrayList();

    private OperationService operationService = ServiceFactory.getOperationServiceInstance();

    public void export(ActionEvent actionEvent) {
        String fileName = "D:\\personalOperations.xlsx";

        try {
            ExcelExport.exportExcel(operationService.getOperationListByJobNum(CurrentUser.getUserAllInfo().getJob_num()), fileName);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示信息");
            alert.setHeaderText("成功");
            alert.setContentText("个人操作日志数据已导出!请到D盘根目录查看!");
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示信息");
            alert.setHeaderText("失败");
            alert.setContentText("失败，无法导出最新数据");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

/*    public void search(ActionEvent actionEvent) {
        operationTable.getItems().removeAll(operationDate);
        operationDate.addAll(operationService.getOperationListByJobNum("%"+keywordsField.getText()+"%"));
        operationTable.setItems(operationDate);
    }*/


    //表格初始化方法
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        //operationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //将实体集合作为参数，调用显示数据的方法
        operationDate.addAll(operationService.getOperationListByJobNum(CurrentUser.getUserAllInfo().getJob_num()));
        operationTable.setItems(operationDate);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }
}
