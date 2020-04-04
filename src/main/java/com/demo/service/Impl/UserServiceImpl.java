package com.demo.service.Impl;

import com.demo.entity.Role;
import com.demo.entity.TableView.RoleInfo;
import com.demo.entity.TableView.UserAllInfo;
import com.demo.entity.User;
import com.demo.entity.UserAllInfoEntity;
import com.demo.entity.Userinfo;
import com.demo.mapper.RoleMapper;
import com.demo.mapper.UserMapper;
import com.demo.service.UserService;
import com.demo.utils.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.yaml.snakeyaml.error.YAMLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserAllInfo userAllInfo;
    private UserMapper userMapper = MapperFactory.getUserMapperInstance();
    private RoleMapper roleMapper = MapperFactory.getRoleMapperInstance();


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

        try {
            List<UserAllInfoEntity> userAllInfoEntities = userMapper.select();
            for (UserAllInfoEntity entity : userAllInfoEntities){
                UserAllInfo userAllInfo = new UserAllInfo(entity);
                userList.add(userAllInfo);
            }

        } catch (YAMLException e2) {
            //handleErr.printErr(e2, "LOAD OBJECT FROM YAML FAILED!", false);
        } catch (Exception e3) {
            //handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

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
    public void addUser(TextField job_num, TextField password, TextField name, TextField gender, DatePicker employment_year, TextField phone, TextField email, int roleId) {

        //TODO 调用数据库，将数据存入数据库中
        System.out.println(job_num);
        System.out.println(job_num.getText());
        if(roleId == 0 || job_num.getText().equals("") || password.getText().equals("") || name.getText().equals("") || gender.getText().equals("") || employment_year.getValue().toString().equals("") || phone.getText().equals("") || email.getText().equals("")){
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
    public void editUser(TextField job_num, TextField password, TextField name, TextField gender, DatePicker employment_year, TextField phone, TextField email, int roleId) {
        //TODO 调用数据库，将修改数据数据存入数据库中

        //userAllInfo,为所要修改的用户之前信息

        System.out.println(userAllInfo.getEmail());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setContentText("角色id" + roleId + "修改之前的id" + userAllInfo.getId());
        alert.showAndWait();
    }


    @Override
    public List<RoleInfo> getRoleList(){
        List<RoleInfo> roleList = new ArrayList<>();
        List<Role> roles = roleMapper.select();
        for (Role role : roles){
            RoleInfo roleInfo = new RoleInfo(role);
            roleList.add(roleInfo);
        }
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
