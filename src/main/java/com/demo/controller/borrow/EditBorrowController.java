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

import java.net.URL;
import java.text.ParseException;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (! url.toString().contains(ResourcesConfig.EDIT_BORROW_FXML))     return;

        BorrowInfo borrowInfo = borrowService.getBorrowInfo();
        renewNum.setText(borrowInfo.getRenew_num() + "");

    }
}
