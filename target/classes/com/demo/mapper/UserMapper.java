package com.demo.mapper;

import com.demo.entity.UserAllInfoEntity;

import java.util.List;

public interface UserMapper {
//    @SQL("SELECT user.id, job_num, password, create_time, login_num, userinfo_id," +
//            "name, gender, employment_year, phone, email, avatar, role_name, role_info " +
//            "FROM user, userinfo, role WHERE user.userinfo_id = userinfo.id AND userinfo.role_id = role.id")
    List<UserAllInfoEntity> select();

//    @SQL("SELECT id FROM user WHERE job_num = ? AND password = ?")
    int select(String job_num, String password);

//    @SQL("SELECT id FROM user WHERE job_num = ?")
    int select(String job_num);


//    @SQL("SELECT user.id, job_num, password, create_time, login_num, userinfo_id," +
//            "name, gender, employment_year, phone, email, avatar, role_name, role_info " +
//            "FROM user, userinfo, role WHERE user.userinfo_id = userinfo.id AND userinfo.role_id = role.id " +
//            "AND user.id = ?")
    UserAllInfoEntity select(int id);
}
