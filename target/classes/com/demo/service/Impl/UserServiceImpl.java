package com.demo.service.Impl;

import com.demo.entity.Role;
import com.demo.entity.TableView.RoleInfo;
import com.demo.entity.TableView.UserAllInfo;
import com.demo.entity.User;
import com.demo.entity.Userinfo;
import com.demo.service.UserService;
import com.demo.utils.ComponentUtil;
import com.demo.utils.CurrentUser;
import com.demo.utils.Operate;
import com.demo.utils.ResourcesConfig;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserAllInfo userAllInfo;
    @Override
    public void addButtonToTableView(String text, String theme, TableColumn<UserAllInfo, UserAllInfo> col, Operate operate) {

        //操作列的相关设置
        col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        col.setCellFactory(param -> new TableCell<UserAllInfo, UserAllInfo>() {
            //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
            private final Button editButton = ComponentUtil.getButton(text, theme);

            @Override
            protected void updateItem(UserAllInfo user, boolean empty) {
                super.updateItem(user, empty);
                if (user == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                editButton.setOnAction(event ->{
                    userAllInfo = user;
                    switch (operate){
                        case ADD://增
                            break;
                        case DELETE://删
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("提示");
                            alert.setContentText("！！！！");
                            alert.showAndWait();
                            break;
                        case UPDATE://改
                            try {
                                newUserStage(ResourcesConfig.EDIT_USER_FXML);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case SELECT://查
                            break;
                    }
                });
            }
        });
    }

    @Override
    public List<UserAllInfo> getUserList() {
        List<UserAllInfo> userList = new ArrayList<>();

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

        Role role = Role.builder().role_info("系统管理员").build();

        UserAllInfo userAllInfo = new UserAllInfo(user,userinfo,role);

        userList.add(userAllInfo);
        userList.add(userAllInfo);
        userList.add(userAllInfo);
        userList.add(userAllInfo);
        userList.add(userAllInfo);
        userList.add(userAllInfo);
        userList.add(userAllInfo);
        userList.add(userAllInfo);
        userList.add(userAllInfo);

        return userList;
    }

    @Override
    public void getUserInfo(Label job_num, Label password, Label name,
                            Label gender, Label employment_year, Label phone,
                            Label email, Label login_time, Label last_login_time, Label login_num) {
        job_num.setText(CurrentUser.getUserAllInfo().getJob_num());
        password.setText(CurrentUser.getUserAllInfo().getPassword());
        name.setText(CurrentUser.getUserAllInfo().getName());
        gender.setText(CurrentUser.getUserAllInfo().getGender());
        employment_year.setText(CurrentUser.getUserAllInfo().getEmployment_year());
        phone.setText(CurrentUser.getUserAllInfo().getPhone());
        email.setText(CurrentUser.getUserAllInfo().getEmail());
        login_time.setText(CurrentUser.getUserAllInfo().getLogin_time());
        last_login_time.setText(CurrentUser.getUserAllInfo().getLast_login_time());
        login_num.setText(CurrentUser.getUserAllInfo().getLogin_num() + "");

    }

    @Override
    public void newUserStage(String fxml) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(ResourcesConfig.CSS_STYLE);
        stage.setTitle(ResourcesConfig.BOOK_MANAGE_TITLE);
        stage.getIcons().add(new Image(ResourcesConfig.IMG_LOGO));
        //界面大小不可变
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void addUser(TextField job_num, TextField password, TextField name, TextField gender, TextField employment_year, TextField phone, TextField email, int roleId) {

        //TODO 调用数据库，将数据存入数据库中
        System.out.println(job_num);
        System.out.println(job_num.getText());
        if(roleId == 0 || job_num.getText().equals("") || password.getText().equals("") || name.getText().equals("") || gender.getText().equals("") || employment_year.getText().equals("") || phone.getText().equals("") || email.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示信息");
            alert.setHeaderText("请补全信息！！！");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setContentText("角色id" + roleId);
        alert.showAndWait();

    }

    @Override
    public void editUser(TextField job_num, TextField password, TextField name, TextField gender, TextField employment_year, TextField phone, TextField email, int roleId) {
        //TODO 调用数据库，将修改数据数据存入数据库中

        //userAllInfo,为所要修改的用户之前信息

        System.out.println(userAllInfo.getEmail());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setContentText("角色id" + roleId + "修改之前的id" + userAllInfo.getId());
        alert.showAndWait();
    }


    //假数据
    @Override
    public List<RoleInfo> getRoleList(){
        List<RoleInfo> roleList = new ArrayList<>();

        Role role1 = Role.builder().id(1).role_name("system_admin").role_info("系统管理员").build();
        Role role2 = Role.builder().id(2).role_name("book_admin").role_info("图书管理员").build();
        Role role3 = Role.builder().id(3).role_name("user").role_info("用户").build();

        RoleInfo roleInfo1 = new RoleInfo(role1);
        RoleInfo roleInfo2 = new RoleInfo(role2);
        RoleInfo roleInfo3 = new RoleInfo(role3);

        roleList.add(roleInfo1);
        roleList.add(roleInfo2);
        roleList.add(roleInfo3);
        return roleList;
    }

    @Override
    public void editPersonal(TextField password, TextField name, TextField gender, TextField email, TextField phone) {
        //TODO 修改当前用户的个人信息
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setContentText("id:"+ CurrentUser.getUserAllInfo().getId() + ",密码：" + password.getText());
        alert.showAndWait();

        //TODO 更新当前用户信息
        //new CurrentUser(user,userinfo);
    }

}
