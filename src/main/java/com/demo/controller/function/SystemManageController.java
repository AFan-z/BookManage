package com.demo.controller.function;

import com.demo.controller.MainController;
import com.demo.service.MainService;
import com.demo.utils.ResourcesConfig;
import com.demo.utils.ServiceFactory;
import javafx.event.ActionEvent;

public class SystemManageController {
    private MainService mainService = ServiceFactory.getMainServiceInstance();

    /**
     * 操作日志信息
     * @param actionEvent
     * @throws Exception
     */
    public void listOperation(ActionEvent actionEvent) {
        try {
            mainService.switchView(ResourcesConfig.OPERATION_FXML, MainController.mainToOtherContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 权限管理
     * @param actionEvent
     */
    public void listPermission(ActionEvent actionEvent) {
        try {
            mainService.switchView(ResourcesConfig.ROLE_AND_PERMISSION_FXML,MainController.mainToOtherContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
