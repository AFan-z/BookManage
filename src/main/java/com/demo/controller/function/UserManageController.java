package com.demo.controller.function;

import com.demo.controller.MainController;
import com.demo.service.MainService;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;

public class UserManageController {
    private MainService mainService = ServiceFactory.getMainServiceInstance();

    /**
     * 用户列表
     * @param actionEvent
     * @throws Exception
     */
    public void listUser(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.USER_LIST_FXML, MainController.mainToOtherContainer);
    }

    /**
     * 用户列表-统计分析
     * @param actionEvent
     * @throws Exception
     */
    public void listReaderAnalysis(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.CHART_USERINFO_FXML, MainController.mainToOtherContainer);
    }
}
