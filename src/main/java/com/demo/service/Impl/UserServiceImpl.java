package com.demo.service.Impl;

import com.demo.entity.*;
import com.demo.entity.TableView.RoleInfo;
import com.demo.entity.TableView.UserAllInfo;
import com.demo.mapper.OperationMapper;
import com.demo.mapper.RoleMapper;
import com.demo.mapper.UserMapper;
import com.demo.service.UserService;
import com.demo.utils.*;
import com.demo.utils.enumeration.Operate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.yu.myorm.core.Exception.NoSuchDataInDBException;
import org.yu.myorm.core.handleErr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class UserServiceImpl implements UserService {

    private static ObservableList<UserAllInfo> userDataInfo;
    private static TableView<UserAllInfo> userTableInfo;
    private static UserAllInfo userAllInfo;
    private UserMapper userMapper = MapperFactory.getUserMapperInstance();
    private RoleMapper roleMapper = MapperFactory.getRoleMapperInstance();
    private OperationMapper operationMapper = MapperFactory.getOperationMapperInstance();

    @Override
    public void addButtonToTableView(String text, String theme, TableColumn<UserAllInfo, UserAllInfo> col, Operate operate, ObservableList<UserAllInfo> userData, TableView<UserAllInfo> userTable) {

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
                            delete();
                            userData.remove(user);
                            break;
                        case UPDATE://改
                            try {
                                userDataInfo = userData;
                                userTableInfo = userTable;
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
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return userList;
    }

    @Override
    public List<UserAllInfo> selectUserByJobNum(String jobNum) {
        List<UserAllInfo> userList = new ArrayList<>();

        try {
            List<UserAllInfoEntity> entitys = userMapper.selectByJobNum(jobNum);
            for (UserAllInfoEntity entity : entitys) {
                UserAllInfo userAllInfo = new UserAllInfo(entity);
                userList.add(userAllInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }

        return userList;
    }

    @Override
    public void getUserInfo(Label job_num, Label password, Label name,
                            Label gender, Label employment_year, Label phone,
                            Label email, Label login_num) {
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
    public void newUserStage(String fxml, ObservableList<UserAllInfo> userData, TableView<UserAllInfo> userTable) throws Exception {
        userDataInfo = userData;
        userTableInfo = userTable;
        newUserStage(fxml);
    }

    @Override
    public boolean addUser(TextField job_num, TextField password, TextField name, TextField gender, DatePicker employment_year, TextField phone, TextField email, int roleId) throws ParseException {
        boolean flag = false;
        if(roleId == 0 || job_num.getText().equals("") || password.getText().equals("") || name.getText().equals("") || gender.getText().equals("") || employment_year.getValue() == null || phone.getText().equals("") || email.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示信息");
            alert.setHeaderText("请补全信息！！！");
            alert.showAndWait();
            return flag;
        }

        try {
            Userinfo userinfo = Userinfo.builder().name(name.getText()).gender(gender.getText())
                    .employment_year(editDate(employment_year.getValue().toString())).phone(phone.getText())
                    .email(email.getText()).avatar("/image/avatar/" + (new Random().nextInt(7) + 1) + ".jpg").roleId(roleId).build();

            boolean b = userMapper.insert(userinfo);
            if (b){
                User user = User.builder().job_num(job_num.getText()).password(password.getText()).create_time(new Date())
                        .userinfo_id(userMapper.select(name.getText(), phone.getText(), email.getText())).build();
                boolean b1 = userMapper.insert(user);
                if (b1){
                    //操作日志
                    Operation operation = Operation.builder()
                            .operationInfo("新增用户信息，增加的用户工号：" +  job_num.getText())
                            .operationTime(new Date())
                            .operationUser(CurrentUser.getUserAllInfo().getId())
                            .build();
                    operationMapper.insert(operation);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText("成功");
                    alert.setContentText("新增用户添加数据成功！！！");
                    alert.showAndWait();
                    Stage stage = (Stage) job_num.getScene().getWindow();
                    stage.close();
                    userTableInfo.getItems().removeAll(userDataInfo);
                    userDataInfo.addAll(getUserList());
                    userTableInfo.setItems(userDataInfo);
                    flag = true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText("失败");
                    alert.setContentText("新增用户添加数据成功！！！");
                    alert.showAndWait();
                    Stage stage = (Stage) job_num.getScene().getWindow();
                    stage.close();

                }
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return flag;
    }

    @Override
    public boolean editUser(TextField job_num, TextField password, TextField name, TextField gender, DatePicker employment_year, TextField phone, TextField email, int roleId) throws ParseException {

        boolean flag = false;

        if (job_num.getText().equals("")){
            job_num.setText(userAllInfo.getJob_num());
        }
        if (password.getText().equals("")){
            password.setText(userAllInfo.getPassword());
        }

        if (gender.getText().equals("")){
            gender.setText(userAllInfo.getGender());
        }

        if (employment_year.getValue() == null){
            String dataStr[] = userAllInfo.getEmployment_year().split(" ");
            employment_year.setValue(LocalDate.parse(dataStr[0]));
        }

        if (phone.getText().equals("")){
            phone.setText(userAllInfo.getPhone());
        }

        if (email.getText().equals("")){
            email.setText(userAllInfo.getEmail());
        }

        if (roleId == 0){
            roleId = userAllInfo.getRole_id();
        }
        //userAllInfo,为所要修改的用户之前信息
        try {
            boolean b1 = userMapper.update(job_num.getText(), password.getText(), userAllInfo.getId());
            boolean b2 = userMapper.update(name.getText(), gender.getText(), editDate(employment_year.getValue().toString()),
                    phone.getText(),email.getText(),roleId, userAllInfo.getUserinfo_id());
            if (b1 && b2){
                //操作日志
                Operation operation = Operation.builder()
                        .operationInfo("修改用户信息，修改的用户工号：" + userAllInfo.getJob_num() + "用户ID:" + userAllInfo.getId())
                        .operationTime(new Date())
                        .operationUser(CurrentUser.getUserAllInfo().getId())
                        .build();
                operationMapper.insert(operation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("成功");
                alert.setContentText("修改用户信息成功！！！");
                alert.showAndWait();
                Stage stage = (Stage) job_num.getScene().getWindow();
                stage.close();
                userTableInfo.getItems().removeAll(userDataInfo);
                userDataInfo.addAll(getUserList());
                userTableInfo.setItems(userDataInfo);
                flag = true;
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("失败");
                alert.setContentText("修改用户信息失败！！！");
                alert.showAndWait();
                Stage stage = (Stage) job_num.getScene().getWindow();
                stage.close();
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return flag;
    }


    @Override
    public List<RoleInfo> getRoleList(){
        List<RoleInfo> roleList = new ArrayList<>();
        try {
            List<Role> roles = roleMapper.selectRole();
            for (Role role : roles) {
                RoleInfo roleInfo = new RoleInfo(role);
                roleList.add(roleInfo);
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return roleList;
    }

    @Override
    public boolean editPersonal(TextField password, TextField name, TextField gender, TextField email, TextField phone) {

        boolean flag = false;

        if (password.getText().equals("")){
            password.setText(CurrentUser.getUserAllInfo().getPassword());
        }

        if (name.getText().equals("")){
            name.setText(CurrentUser.getUserAllInfo().getName());
        }

        if (gender.getText().equals("")){
            gender.setText(CurrentUser.getUserAllInfo().getGender());
        }

        if (phone.getText().equals("")){
            phone.setText(CurrentUser.getUserAllInfo().getPhone());
        }

        if (email.getText().equals("")){
            email.setText(CurrentUser.getUserAllInfo().getEmail());
        }

        try {
            boolean b1 = userMapper.update(CurrentUser.getUserAllInfo().getJob_num(), password.getText(), CurrentUser.getUserAllInfo().getId());
            boolean b2 = userMapper.update(name.getText(), gender.getText(), phone.getText(), email.getText(), CurrentUser.getUserAllInfo().getUserinfo_id());

            if (b1 && b2){
                //操作日志
                Operation operation = Operation.builder()
                        .operationInfo("修改个人信息，修改的用户工号：" + CurrentUser.getUserAllInfo().getJob_num() + "用户ID:" + CurrentUser.getUserAllInfo().getId())
                        .operationTime(new Date())
                        .operationUser(CurrentUser.getUserAllInfo().getId())
                        .build();
                operationMapper.insert(operation);
                new CurrentUser(userMapper.select(CurrentUser.getUserAllInfo().getId()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("成功");
                alert.setContentText("修改用户信息成功！！！");
                alert.showAndWait();
                Stage stage = (Stage) password.getScene().getWindow();
                stage.close();
                flag = true;
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("失败");
                alert.setContentText("修改用户信息失败！！！");
                alert.showAndWait();
                Stage stage = (Stage) password.getScene().getWindow();
                stage.close();
            }
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return flag;
    }

    @Override
    public boolean delete() {
        boolean flag = false;
        try {
            boolean b = userMapper.delete(userAllInfo.getId());
            if (b) {
                //操作日志
                Operation operation = Operation.builder()
                        .operationInfo("删除用户信息，被删除的用户工号：" + userAllInfo.getJob_num() + "用户ID:" + userAllInfo.getId())
                        .operationTime(new Date())
                        .operationUser(CurrentUser.getUserAllInfo().getId())
                        .build();
                operationMapper.insert(operation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("成功");
                alert.setHeaderText("删除用户成功!!!");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示信息");
                alert.setHeaderText("失败");
                alert.setHeaderText("删除用户失败!!!");
                alert.showAndWait();
            }
            flag = b;
        } catch (NoSuchDataInDBException dbe) {
            handleErr.printErr(dbe, dbe.getMessage(), false);
        } catch (Exception e3) {
            handleErr.printErr(e3, "EXCEPTION!!!", true);
        }
        return flag;
    }

    @Override
    public UserAllInfo getUserAllInfo() {
        return this.userAllInfo;
    }

    private Date editDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String newdate = date + " 00:00:00";
        Date newDate = format.parse(newdate);
        return newDate;
    }
}
