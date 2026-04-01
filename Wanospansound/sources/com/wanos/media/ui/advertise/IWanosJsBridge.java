package com.wanos.media.ui.advertise;

import android.webkit.JavascriptInterface;

/* JADX INFO: loaded from: classes3.dex */
public interface IWanosJsBridge {
    @JavascriptInterface
    void collect(long id, int isCollect);

    void collectCall(long id, int isCollect);

    @JavascriptInterface
    void openGroupPage(int type, long groupId);

    @JavascriptInterface
    void openPayVipPage(int type);

    @JavascriptInterface
    void openPlayPage(String bean, long groupId);

    @JavascriptInterface
    void playGroup(int type, long groupId, String beanInfo);

    @JavascriptInterface
    void sendH5Object(String content);

    void setNightMode(int nightMode);

    void setPlayState(long groupId, long id, int state, int type);

    @JavascriptInterface
    String testjs(int a2, String b2);

    @JavascriptInterface
    void updataToken(String token);
}
