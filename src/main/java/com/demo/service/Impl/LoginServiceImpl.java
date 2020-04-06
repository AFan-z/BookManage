package com.demo.service.Impl;

import com.demo.entity.Operation;
import com.demo.mapper.OperationMapper;
import com.demo.mapper.UserMapper;
import com.demo.service.LoginService;
import com.demo.utils.CurrentUser;
import com.demo.utils.MapperFactory;
import com.demo.utils.ResourcesConfig;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.yaml.snakeyaml.error.YAMLException;
import org.yu.myorm.core.Exception.NoSuchDataInDBException;
import org.yu.myorm.core.handleErr;

import java.util.Date;


public class LoginServiceImpl implements LoginService {

    private UserMapper userMapper = MapperFactory.getUserMapperInstance();
    private OperationMapper operationMapper = MapperFactory.getOperationMapperInstance();

    @Override
    public boolean checkReturn(String account, String password) {
        boolean flag = false;
        try {
            int id = userMapper.select(account, password);
            userMapper.update(userMapper.select(id).getLoginNum() + 1, id);
            new CurrentUser(userMapper.select(id));
            //操作日志
            Operation operation = Operation.builder()
                    .operationInfo("登录操作，第" + CurrentUser.getUserAllInfo().getLogin_num() + "次登录系统" )
                    .operationTime(new Date())
                    .operationUser(CurrentUser.getUserAllInfo().getId())
                    .build();
            operationMapper.insert(operation);
            flag = true;
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return flag;
    }

    @Override
    public boolean goToMain() {
        boolean flag = false;
        try {
            Stage mainStage = new Stage();

            //判断角色信息
            FXMLLoader fxmlLoader = new FXMLLoader(
                    CurrentUser.getUserAllInfo().getRole_name().equals("SYSTEM_ADMIN") ?
                                          getClass().getResource(ResourcesConfig.MAIN_FXML) :
                         (
                             CurrentUser.getUserAllInfo().getRole_name().equals("BOOK_ADMIN") ?
                               getClass().getResource(ResourcesConfig.BOOK_MAIN_FXML) :
                                getClass().getResource(ResourcesConfig.BORROWER_MAIN_FXML)
                         ));

            BorderPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(ResourcesConfig.CSS_STYLE);
            mainStage.setTitle(ResourcesConfig.BOOK_MANAGE_TITLE);
            mainStage.setMaximized(true);
            mainStage.setScene(scene);
            mainStage.getIcons().add(new Image(ResourcesConfig.IMG_LOGO));

            //窗口关闭事件，退出系统
            mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.exit(0);
                }
            });

            mainStage.show();
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}
