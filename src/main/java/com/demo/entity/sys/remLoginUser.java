package com.demo.entity.sys;

public class remLoginUser {

    private static String usernameStr;
    private static String passwdStr;
    private static boolean isRem;


    public static String getUsernameStr() {
        return usernameStr;
    }

    public static void setUsernameStr(String usernameStr) {
        remLoginUser.usernameStr = usernameStr;
    }

    public static String getPasswdStr() {
        return passwdStr;
    }

    public static void setPasswdStr(String passwdStr) {
        remLoginUser.passwdStr = passwdStr;
    }

    public static boolean getIsRem() {
        return isRem;
    }

    public static void setIsRem(boolean isRem) {
        remLoginUser.isRem = isRem;
    }




}
