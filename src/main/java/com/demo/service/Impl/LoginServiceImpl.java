package com.demo.service.Impl;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.entity.Userinfo;
import com.demo.service.LoginService;
import com.demo.utils.CurrentUser;
import com.demo.utils.ResourcesConfig;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Date;

public class LoginServiceImpl implements LoginService {

    @Override
    public boolean checkReturn(String account, String password) {
        boolean flag = false;

        //假数据
        User user = User.builder()
                .id(1)
                .job_num("2018102146")
                .password("123456")
                .create_time(new Date())
                .login_time(new Date())
                .last_login_time(new Date())
                .login_num(6)
                .userinfo_id(1)
                .build();
        Userinfo userinfo = Userinfo.builder()
                .id(1)
                .name("测试用户")
                .gender("男")
                .employment_year(new Date())
                .phone("1313131313")
                .email("2130643@qq.com")
                .avatar("/image/avatar/1.jpg")
                .build();

        Role role1 = Role.builder().id(1).role_name("SYSTEM_ADMIN").role_info("系统管理员").build();
        Role role2 = Role.builder().id(2).role_name("BOOK_ADMIN").role_info("图书管理员").build();
        Role role3 = Role.builder().id(3).role_name("BORROWER").role_info("借阅者").build();

        //TODO 假数据，这里调用数据库数据
        if ("2018102146".equals(account) && "book".equals(password)) {
            //TODO 从数据库获得设置User和Userinfo数据来设置当前用户信息
            new CurrentUser(user,userinfo,role2);
            flag = true;
        }

        if ("2018102146".equals(account) && "system".equals(password)) {
            //TODO 从数据库获得设置User和Userinfo数据来设置当前用户信息
            new CurrentUser(user,userinfo,role1);
            flag = true;
        }
        if ("2018102146".equals(account) && "borrower".equals(password)) {
            //TODO 从数据库获得设置User和Userinfo数据来设置当前用户信息
            new CurrentUser(user,userinfo,role3);
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean goToMain() {
        boolean flag = false;
        try {
            Stage mainStage = new Stage();

            //判断角色信息
            FXMLLoader fxmlLoader = CurrentUser.getUserAllInfo().getRole_name().equals("SYSTEM_ADMIN") ?
                        new FXMLLoader(getClass().getResource(ResourcesConfig.MAIN_FXML)) : (
                        CurrentUser.getUserAllInfo().getRole_name().equals("BOOK_ADMIN") ?
                                new FXMLLoader(getClass().getResource(ResourcesConfig.BOOK_MAIN_FXML)) :
                                new FXMLLoader(getClass().getResource(ResourcesConfig.BORROWER_MAIN_FXML)));

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
