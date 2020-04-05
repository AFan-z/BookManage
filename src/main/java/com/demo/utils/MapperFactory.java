package com.demo.utils;

import com.demo.mapper.BookMapper;
import com.demo.mapper.BorrowMapper;
import com.demo.mapper.RoleMapper;
import com.demo.mapper.UserMapper;
import com.demo.mock.BookMapperImpl;
import com.demo.mock.BorrowMapperImpl;
import com.demo.mock.RoleMapperImpl;
import com.demo.mock.UserMapperImpl;
//import org.yu.myorm.core.dynproxy.MapperInvoHander;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 持久层类工厂
 */
public class MapperFactory {
    //private static InvocationHandler invocationHandler = new MapperInvoHander();
    public static BookMapper getBookMapperInstance(){
        return new BookMapperImpl();
        //return (BookMapper) Proxy.newProxyInstance(BookMapper.class.getClassLoader(), new Class[]{BookMapper.class}, invocationHandler);
    }

    public static BorrowMapper getBorrowMapperInstance(){
        //return (BorrowMapper) Proxy.newProxyInstance(BorrowMapper.class.getClassLoader(), new Class[]{BorrowMapper.class}, invocationHandler);
        return new BorrowMapperImpl();
    }

    public static RoleMapper getRoleMapperInstance(){
        return new RoleMapperImpl();
        //return (RoleMapper) Proxy.newProxyInstance(RoleMapper.class.getClassLoader(), new Class[]{RoleMapper.class}, invocationHandler);

    }

    public static UserMapper getUserMapperInstance(){
        return new UserMapperImpl();
        //return (UserMapper) Proxy.newProxyInstance(UserMapper.class.getClassLoader(), new Class[]{UserMapper.class}, invocationHandler);
    }
}
