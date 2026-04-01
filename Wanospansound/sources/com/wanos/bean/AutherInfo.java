package com.wanos.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class AutherInfo implements Serializable {
    private String avatar;
    private String userId;
    private String userName;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }
}
