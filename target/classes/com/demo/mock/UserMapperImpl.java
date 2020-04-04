package com.demo.mock;

import com.demo.entity.UserAllInfoEntity;
import com.demo.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMapperImpl implements UserMapper {

    private List<UserAllInfoEntity> userAllInfoEntities = new ArrayList<>();

    private UserAllInfoEntity entity1 = UserAllInfoEntity.builder()
            .id(1).jobNum("2020032301").password("system").createTime(new Date())
            .loginNum(0).userinfoId(1).name("张一二").gender("").employmentYear(new Date()).email("13231465@qq.com")
            .avatar("/image/avatar/1.jpg").phone("123131232131").roleInfo("系统管理员").roleName("SYSTEM_ADMIN").build();
    private UserAllInfoEntity entity2 = UserAllInfoEntity.builder()
            .id(2).jobNum("2020032302").password("book").createTime(new Date())
            .loginNum(0).userinfoId(2).name("成成成").gender("").employmentYear(new Date()).email("13231465@qq.com")
            .avatar("/image/avatar/3.jpg").phone("123131232131").roleInfo("图书管理员").roleName("BOOK_ADMIN").build();
    private UserAllInfoEntity entity3 = UserAllInfoEntity.builder()
            .id(3).jobNum("2020032303").password("borrower").createTime(new Date())
            .loginNum(0).userinfoId(3).name("王物流").gender("").employmentYear(new Date()).email("13231465@qq.com")
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
}
