package com.demo.utils;

import com.demo.mapper.BookMapper;
import com.demo.mapper.BorrowMapper;
import com.demo.mapper.RoleMapper;
import com.demo.mapper.UserMapper;
import com.demo.mock.BookMapperImpl;
import com.demo.mock.BorrowMapperImpl;
import com.demo.mock.RoleMapperImpl;
import com.demo.mock.UserMapperImpl;

/**
 * 持久层类工厂
 */
public class MapperFactory {

    public static BookMapper getBookMapperInstance(){
        return new BookMapperImpl();
    }

    public static BorrowMapper getBorrowMapperInstance(){
        return new BorrowMapperImpl();
    }

    public static RoleMapper getRoleMapperInstance(){
        return new RoleMapperImpl();
    }

    public static UserMapper getUserMapperInstance(){
        return new UserMapperImpl();
    }
}
