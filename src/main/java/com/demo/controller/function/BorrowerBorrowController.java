package com.demo.controller.function;

import com.demo.controller.MainController;
import com.demo.service.MainService;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;

public class BorrowerBorrowController {
    private MainService mainService = ServiceFactory.getMainServiceInstance();

    /**
     * 个人借阅信息
     * @param actionEvent
     * @throws Exception
     */
    public void borrowInfo(ActionEvent actionEvent) {
        try{
            mainService.switchView(ResourcesConfig.BORROWER_BOOK_FXML, MainController.mainToOtherContainer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void borrowInfoAnalysis(ActionEvent actionEvent) {

    }
}
