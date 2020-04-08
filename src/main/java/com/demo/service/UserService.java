package com.demo.service;

        import com.demo.entity.TableView.RoleInfo;
        import com.demo.entity.TableView.UserAllInfo;
        import com.demo.utils.enumeration.Operate;
        import javafx.collections.ObservableList;
        import javafx.scene.control.*;

        import java.text.ParseException;
        import java.util.List;

public interface UserService {

    void addButtonToTableView(String text, String theme, TableColumn<UserAllInfo, UserAllInfo> col, Operate operate, ObservableList<UserAllInfo> userData, TableView<UserAllInfo> userTable);

    List<UserAllInfo> getUserList();

    List<UserAllInfo> selectUserByJobNum(String jobNum);

    void getUserInfo(Label job_num, Label password, Label name, Label gender, Label employment_year, Label phone, Label email, Label login_num);

    void newUserStage(String fxml) throws Exception;

    void newUserStage(String fxml, ObservableList<UserAllInfo> userData, TableView<UserAllInfo> userTable) throws Exception;

    boolean addUser(TextField job_num, TextField password, TextField name, TextField gender, DatePicker employment_year, TextField phone, TextField email, int roleId) throws ParseException;

    boolean editUser(TextField job_num, TextField password, TextField name, TextField gender, DatePicker employment_year, TextField phone, TextField email, int roleId) throws ParseException;

    List<RoleInfo> getRoleList();

    boolean editPersonal(TextField password, TextField name, TextField gender, TextField email, TextField phone);

    boolean delete();

    UserAllInfo getUserAllInfo();
}
