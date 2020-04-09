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
    public void listBook(ActionEvent actionEvent) {
        try {
            mainService.switchView(ResourcesConfig.BOOK_FXML, MainController.mainToOtherContainer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 图书列表-统计分析
     * @param actionEvent
     * @throws Exception
     */
    public void listBookAnalysis(ActionEvent actionEvent) {
        try {
            mainService.switchView(ResourcesConfig.CHART_BOOKINFO_FXML, MainController.mainToOtherContainer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
