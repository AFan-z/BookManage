package com.demo.controller.function;

import com.demo.controller.MainController;
import com.demo.service.MainService;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;

public class PersonalCenterController {
    private MainService mainService = ServiceFactory.getMainServiceInstance();

    /**
     * 获取用户个人信息
     * @param actionEvent
     * @throws Exception
     */
    public void listPersonalInfo(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.USER_FXML, MainController.mainToOtherContainer);
    }

    /**
     * 个人操作日志
     * @param actionEvent
     */
    public void listPersonalOperation(ActionEvent actionEvent) throws Exception {
        mainService.switchView(ResourcesConfig.PERSONAL_OPERATION_FXML, MainController.mainToOtherContainer);
    }
}
