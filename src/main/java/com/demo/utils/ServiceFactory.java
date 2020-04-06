package com.demo.utils;


import com.demo.service.*;
import com.demo.service.Impl.*;

/**
 * 业务逻辑类工厂
 */
public class ServiceFactory {
    public static BookService getBookServiceInstance() {
        return new BookServiceImpl();
    }

    public static LoginService getLoginServiceInstance() {
        return new LoginServiceImpl();
    }

    public static MainService getMainServiceInstance() {
        return new MainServiceImpl();
    }

    public static UserService getUserServiceInstance() {
        return new UserServiceImpl();
    }

    public static BorrowService getBorrowServiceInstance() {
        return new BorrowServiceImpl();
    }

    public static OperationService getOperationServiceInstance() {
        return new OperationServiceImpl();
    }
}
