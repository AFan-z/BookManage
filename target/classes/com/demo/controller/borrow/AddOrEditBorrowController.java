package com.demo.controller.borrow;

import com.demo.service.BorrowService;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.ParseException;

public class AddOrEditBorrowController {
    @FXML
    private TextField jobNum;
    @FXML
    private TextField bookNum;
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

    public void addBorrow(ActionEvent actionEvent) {
        borrowService.addBorrow(jobNum, bookNum);
    }
}
