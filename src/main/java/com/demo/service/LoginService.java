package com.demo.service;

public interface LoginService {

    /**
     * 验证用户密码,假数据
     *
     * @param account 账号
     * @param password 密码
     * @return
     */
    boolean checkReturn(String account, String password);


    boolean goToMain();
}
