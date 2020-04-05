package com.demo.mock;

import com.demo.entity.User;
import com.demo.entity.UserAllInfoEntity;
import com.demo.entity.Userinfo;
import com.demo.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserMapperImpl implements UserMapper {

    private List<UserAllInfoEntity> userAllInfoEntities = new ArrayList<>();

    private UserAllInfoEntity entity1 = UserAllInfoEntity.builder()
            .id(1).jobNum("2020032301").password("system").createTime(new Date())
            .loginNum(0).userinfoId(1).name("张一二").gender("男").employmentYear(new Date()).email("13231465@qq.com")
            .avatar("/image/avatar/1.jpg").phone("123131232131").roleInfo("系统管理员").roleName("SYSTEM_ADMIN").build();
    private UserAllInfoEntity entity2 = UserAllInfoEntity.builder()
            .id(2).jobNum("2020032302").password("book").createTime(new Date())
            .loginNum(0).userinfoId(2).name("成成成").gender("女").employmentYear(new Date()).email("13231465@qq.com")
            .avatar("/image/avatar/3.jpg").phone("123131232131").roleInfo("图书管理员").roleName("BOOK_ADMIN").build();
    private UserAllInfoEntity entity3 = UserAllInfoEntity.builder()
            .id(3).jobNum("2020032303").password("borrower").createTime(new Date())
            .loginNum(0).userinfoId(3).name("王物流").gender("男").employmentYear(new Date()).email("13231465@qq.com")
            .avatar("/image/avatar/5.jpg").phone("123131232131").roleInfo("借阅者").roleName("BORROWER").build();

    {
        userAllInfoEntities.add(entity1);
        userAllInfoEntities.add(entity2);
        userAllInfoEntities.add(entity3);
    }

    @Override
    public List<UserAllInfoEntity> select() {
        return userAllInfoEntities;
    }

    @Override
    public UserAllInfoEntity selectByJobNum(String job_num) {
        return null;
    }

    @Override
    public int select(String job_num, String password) {
        for (UserAllInfoEntity entity : userAllInfoEntities){
            if (entity.getJobNum().equals(job_num) && entity.getPassword().equals(password)){
                return entity.getId();
            }
        }
        return 0;
    }

    @Override
    public int select(String job_num) {
        for (UserAllInfoEntity entity : userAllInfoEntities){
            if (entity.getJobNum().equals(job_num)){
                return entity.getId();
            }
        }
        return 0;
    }

    @Override
    public UserAllInfoEntity select(int id) {
        for (UserAllInfoEntity entity : userAllInfoEntities){
            if (entity.getId() == id){
                return entity;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {

        Iterator<UserAllInfoEntity> it = userAllInfoEntities.iterator();

        while (it.hasNext()){
            if (it.next().getId() == id){
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(User user) {
        for (UserAllInfoEntity entity : userAllInfoEntities){
            if (entity.getUserinfoId() == user.getUserinfo_id()){
                entity.setId(userAllInfoEntities.get(userAllInfoEntities.size()-1).getId() +1);
                entity.setJobNum(user.getJob_num());
                entity.setPassword(user.getPassword());
                entity.setCreateTime(user.getCreate_time());
                entity.setLoginNum(0);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(Userinfo userinfo) {
        UserAllInfoEntity entity = UserAllInfoEntity.builder().userinfoId(userAllInfoEntities.get(userAllInfoEntities.size()-1).getUserinfoId() + 1)
                .name(userinfo.getName()).gender(userinfo.getGender()).roleName("SYSTEM_ADMIN").roleInfo("系统管理员")
                .employmentYear(userinfo.getEmployment_year()).avatar(userinfo.getAvatar()).email(userinfo.getEmail()).build();
        userAllInfoEntities.add(entity);
        return true;
    }

    @Override
    public int select(String name, String phone, String email) {
        for (UserAllInfoEntity entity : userAllInfoEntities){
            if (entity.getName().equals(name) && entity.getEmail().equals(email) && entity.getPhone().equals(phone)){
                return entity.getUserinfoId();
            }
        }
        return 0;
    }

    @Override
    public boolean update(String job_num, String password, int id) {
        return false;
    }

    @Override
    public boolean update(String name, String gender, Date employment_year, String phone, String email, int role_id, int id) {
        return false;
    }

    @Override
    public boolean update(String name, String gender, String phone, String email, int id) {
        return false;
    }

    @Override
    public boolean update(int login_num, int id) {
        return false;
    }
}
