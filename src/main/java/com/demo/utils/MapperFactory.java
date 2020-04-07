package com.demo.utils;

import com.demo.mapper.*;
import org.yu.myorm.core.dynproxy.MapperInvoHander;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 持久层类工厂
 */
public class MapperFactory {
    private static InvocationHandler invocationHandler = new MapperInvoHander();

    public static BookMapper getBookMapperInstance(){
        //return new BookMapperImpl();
        return (BookMapper) Proxy.newProxyInstance(BookMapper.class.getClassLoader(), new Class[]{BookMapper.class}, invocationHandler);
    }

    public static BorrowMapper getBorrowMapperInstance(){
        return (BorrowMapper) Proxy.newProxyInstance(BorrowMapper.class.getClassLoader(), new Class[]{BorrowMapper.class}, invocationHandler);
        //return new BorrowMapperImpl();
    }

    public static RoleMapper getRoleMapperInstance(){
        //return new RoleMapperImpl();
        return (RoleMapper) Proxy.newProxyInstance(RoleMapper.class.getClassLoader(), new Class[]{RoleMapper.class}, invocationHandler);

    }

    public static UserMapper getUserMapperInstance(){
        //return new UserMapperImpl();
        return (UserMapper) Proxy.newProxyInstance(UserMapper.class.getClassLoader(), new Class[]{UserMapper.class}, invocationHandler);
    }

    public static OperationMapper getOperationMapperInstance(){
        //return new OperationMapperImpl();
        return (OperationMapper) Proxy.newProxyInstance(OperationMapper.class.getClassLoader(), new Class[]{OperationMapper.class}, invocationHandler);
    }

    public static RoleAndPermissionMapper getRoleAndPermissionMapperInstance(){
        //return new RoleAndPermissionMapperImpl();
        return (RoleAndPermissionMapper) Proxy.newProxyInstance(RoleAndPermissionMapper.class.getClassLoader(), new Class[]{RoleAndPermissionMapper.class}, invocationHandler);
    }
}
