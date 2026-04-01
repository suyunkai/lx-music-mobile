package com.wanos.media.ui.advertise;

/* JADX INFO: loaded from: classes3.dex */
public class WebViewCommunicationBase {
    public static final String ACTION_LOGIN = "ACTION_LOGIN";
    public static final String ACTION_WEB_INIT_SUCCESS = "ACTION_WEB_INIT_SUCCESS";
    public static final String ACTION_WECHAT_LOGIN_CODE = "ACTION_WECHAT_LOGIN_CODE";
    public static final String ACTION_WECHAT_URL = "ACTION_WECHAT_URL";
    String action;
    String para;

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPara() {
        return this.para;
    }

    public void setPara(String para) {
        this.para = para;
    }
}
