package com.demo.utils;

import com.demo.entity.TableView.UserAllInfo;
import com.demo.entity.UserAllInfoEntity;

public class CurrentUser {
    private static UserAllInfo userAllInfo;

    public CurrentUser(UserAllInfoEntity userinfo) {
        this.userAllInfo = new UserAllInfo(userinfo);
    }

    public static UserAllInfo getUserAllInfo() {
        return userAllInfo;
    }
}
