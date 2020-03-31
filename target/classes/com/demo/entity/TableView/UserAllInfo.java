package com.demo.entity.TableView;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.entity.Userinfo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;


public class UserAllInfo {
    /**
     * 主键
     */
    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    /**
     * 账号（工号）
     */
    private SimpleStringProperty job_num = new SimpleStringProperty("");

    /**
     * 密码
     */
    private SimpleStringProperty password = new SimpleStringProperty("");

    /**
     * 创建时间
     */
    private SimpleStringProperty create_time = new SimpleStringProperty("");

    /**
     * 登录时间
     */
    private SimpleStringProperty login_time = new SimpleStringProperty("");

    /**
     * 上次登录时间
     */
    private SimpleStringProperty last_login_time = new SimpleStringProperty("");

    /**
     * 登录次数
     */
    private SimpleIntegerProperty login_num = new SimpleIntegerProperty();

    /**
     * 用户信息id
     */
    //private SimpleIntegerProperty userinfo_id = new SimpleIntegerProperty();

    /**
     * 姓名
     */
    private SimpleStringProperty name = new SimpleStringProperty("");

    /**
     * 性别
     */
    private SimpleStringProperty gender = new SimpleStringProperty("");

    /**
     * 入职年份
     */
    private SimpleStringProperty employment_year = new SimpleStringProperty("");

    /**
     * 电话
     */
    private SimpleStringProperty phone = new SimpleStringProperty("");

    /**
     * 电子邮箱
     */
    private SimpleStringProperty email = new SimpleStringProperty("");

    /**
     * 头像
     */
    private SimpleStringProperty avatar = new SimpleStringProperty("");

    /**
     * 角色名
     */
    private SimpleStringProperty role_name = new SimpleStringProperty("");

    /**
     * 角色信息
     */
    private SimpleStringProperty role_info = new SimpleStringProperty("");


    public UserAllInfo(User user, Userinfo userinfo, Role role){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        setId(user.getId());
        setJob_num(user.getJob_num());
        setPassword(user.getPassword());
        setCreate_time(dateFormat.format(user.getCreate_time()));
        setLogin_time(dateFormat.format(user.getLogin_time()));
        setLast_login_time(dateFormat.format(user.getLast_login_time()));
        setLogin_num(user.getLogin_num());
        //setUserinfo_id(user.getUserinfo_id());
        setName(userinfo.getName());
        setGender(userinfo.getGender());
        setEmployment_year(dateFormat.format(userinfo.getEmployment_year()));
        setPhone(userinfo.getPhone());
        setEmail(userinfo.getEmail());
        setAvatar(userinfo.getAvatar());
        setRole_name(role.getRole_name());
        setRole_info(role.getRole_info());
    }

    public UserAllInfo(){

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getJob_num() {
        return job_num.get();
    }

    public SimpleStringProperty job_numProperty() {
        return job_num;
    }

    public void setJob_num(String job_num) {
        this.job_num.set(job_num);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getCreate_time() {
        return create_time.get();
    }

    public SimpleStringProperty create_timeProperty() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time.set(create_time);
    }

    public String getLogin_time() {
        return login_time.get();
    }

    public SimpleStringProperty login_timeProperty() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time.set(login_time);
    }

    public String getLast_login_time() {
        return last_login_time.get();
    }

    public SimpleStringProperty last_login_timeProperty() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time.set(last_login_time);
    }

    public int getLogin_num() {
        return login_num.get();
    }

    public SimpleIntegerProperty login_numProperty() {
        return login_num;
    }

    public void setLogin_num(int login_num) {
        this.login_num.set(login_num);
    }

//    public int getUserinfo_id() {
//        return userinfo_id.get();
//    }
//
//    public SimpleIntegerProperty userinfo_idProperty() {
//        return userinfo_id;
//    }
//
//    public void setUserinfo_id(int userinfo_id) {
//        this.userinfo_id.set(userinfo_id);
//    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getEmployment_year() {
        return employment_year.get();
    }

    public SimpleStringProperty employment_yearProperty() {
        return employment_year;
    }

    public void setEmployment_year(String employment_year) {
        this.employment_year.set(employment_year);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getRole_info() {
        return role_info.get();
    }

    public SimpleStringProperty role_infoProperty() {
        return role_info;
    }

    public void setRole_info(String role_info) {
        this.role_info.set(role_info);
    }

    public String getAvatar() {
        return avatar.get();
    }

    public SimpleStringProperty avatarProperty() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar.set(avatar);
    }

    public String getRole_name() {
        return role_name.get();
    }

    public SimpleStringProperty role_nameProperty() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name.set(role_name);
    }
}
