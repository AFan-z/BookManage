package com.demo.controller.borrow;

import com.demo.entity.TableView.BorrowInfo;
import com.demo.service.BorrowService;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

import javax.xml.crypto.Data;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class EditBorrowController implements Initializable {
    @FXML
    private ComboBox<String> isReturn;
    @FXML
    private DatePicker returnTime;
    @FXML
    private TextField renewNum;

    private BorrowService borrowService = ServiceFactory.getBorrowServiceInstance();

    public void exitBorrow(ActionEvent actionEvent) throws ParseException {
        borrowService.exitBorrow(isReturn, returnTime, renewNum);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (! url.toString().contains(ResourcesConfig.EDIT_BORROW_FXML))     return;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        BorrowInfo borrowInfo = borrowService.getBorrowInfo();
        renewNum.setText(borrowInfo.getRenew_num() + "");
        isReturn.getSelectionModel().select(borrowInfo.getIsReturn());
        Date return_time = dateFormat.parse(borrowInfo.getReturn_time());
        returnTime.setValue(return_time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
