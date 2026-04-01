package com.wanos.commonlibrary.event;

import com.wanos.commonlibrary.bean.UserInfo;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfoChangeEvent {
    private UserInfo userInfo;

    public UserInfoChangeEvent(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
