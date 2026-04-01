package com.wanos.commonlibrary.event;

/* JADX INFO: loaded from: classes3.dex */
public class LoginOrLogoutEvent {
    private boolean isLogin;

    public LoginOrLogoutEvent(boolean z) {
        this.isLogin = z;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public void setLogin(boolean z) {
        this.isLogin = z;
    }
}
