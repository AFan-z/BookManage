package com.demo.controller.function;

import com.demo.controller.MainController;
import com.demo.service.MainService;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;

public class BookManageController {
    private MainService mainService = ServiceFactory.getMainServiceInstance();

    /**
     * 图书列表
     * @param actionEvent
     * @throws Exception
     */
    public void listBook(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.BOOK_FXML, MainController.mainToOtherContainer);
    }

    public void listBookAnalysis(ActionEvent actionEvent) throws Exception {
        mainService.switchView("/fxml/chart/bookInfoChart.fxml", MainController.mainToOtherContainer);
    }
}
