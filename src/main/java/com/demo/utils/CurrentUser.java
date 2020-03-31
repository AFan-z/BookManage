package com.demo.utils;

import com.demo.entity.Role;
import com.demo.entity.TableView.UserAllInfo;
import com.demo.entity.User;
import com.demo.entity.Userinfo;

public class CurrentUser {
    private static UserAllInfo userAllInfo;

    public CurrentUser(User user, Userinfo userinfo,Role role) {
        this.userAllInfo = new UserAllInfo(user,userinfo, role);
    }

    public static UserAllInfo getUserAllInfo() {
        return userAllInfo;
    }
}
