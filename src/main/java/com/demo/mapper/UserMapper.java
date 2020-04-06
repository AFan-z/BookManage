package com.demo.mapper;

import com.demo.entity.User;
import com.demo.entity.UserAllInfoEntity;
import com.demo.entity.Userinfo;
import org.yu.myorm.core.dynproxy.SQL;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    @SQL("SELECT user.id, job_num, password, create_time, login_num, userinfo_id," +
            "name, gender, employment_year, phone, email, avatar, role_id, role_name, role_info " +
            "FROM user, userinfo, role " +
            "WHERE user.userinfo_id = userinfo.id AND userinfo.role_id = role.id")
    List<UserAllInfoEntity> select();

    @SQL("SELECT user.id, job_num, password, create_time, login_num, userinfo_id," +
            "name, gender, employment_year, phone, email, avatar, role_id, role_name, role_info " +
            "FROM user, userinfo, role WHERE user.userinfo_id = userinfo.id AND userinfo.role_id = role.id AND user.job_num = ?")
    UserAllInfoEntity selectByJobNum(String job_num);


    @SQL("SELECT id FROM user WHERE job_num = ? AND password = ? limit 1")
    int select(String job_num, String password);

    @SQL("SELECT id FROM user WHERE job_num = ? limit 1")
    int select(String job_num);

    @SQL("SELECT user.id, job_num, password, create_time, login_num, userinfo_id," +
            "name, gender, employment_year, phone, email, avatar, role_id, role_name, role_info " +
            "FROM user, userinfo, role " +
            "WHERE user.userinfo_id = userinfo.id AND userinfo.role_id = role.id AND user.id = ?")
    UserAllInfoEntity select(int id);

    @SQL("DELETE FROM user WHERE id = ?" )
    boolean delete(int id);

    @SQL("INSERT INTO user VALUES (?E)")
    boolean insert(User user);

    @SQL("INSERT INTO userinfo VALUES (?E)")
    boolean insert(Userinfo userinfo);

     @SQL("SELECT id FROM userinfo WHERE name = ? AND phone = ? AND email = ? limit 1")
    int select(String name, String phone, String email);

    @SQL("UPDATE user SET job_num = ?, password = ? WHERE id = ?")
    boolean update(String job_num, String password, int id);

    @SQL("UPDATE userinfo SET name = ?, gender = ?, employment_year = ?, phone = ?, email = ?, role_id = ? WHERE id = ?")
    boolean update(String name, String gender, Date employment_year, String phone, String email, int role_id, int id);

    @SQL("UPDATE userinfo SET name = ?, gender = ?, phone = ?, email = ? WHERE id = ?")
    boolean update(String name, String gender, String phone, String email, int id);

    @SQL("UPDATE user SET login_num = ? WHERE id = ?")
    boolean update(int login_num, int id);
}
