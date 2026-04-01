package com.wanos.commonlibrary.utils;

import android.text.TextUtils;
import com.wanos.commonlibrary.bean.UserInfo;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfoUtil {
    private static UserInfo userInfo;

    public static String getToken() {
        if (userInfo == null) {
            userInfo = SharedPreferUtils.getPersonalInfo();
        }
        if (TextUtils.isEmpty(userInfo.getToken())) {
            return null;
        }
        return userInfo.getToken();
    }

    public static boolean isLogin() {
        if (userInfo == null) {
            userInfo = SharedPreferUtils.getPersonalInfo();
        }
        return !TextUtils.isEmpty(userInfo.getToken());
    }

    public static boolean isVipAndUnexpired() {
        if (userInfo == null) {
            userInfo = SharedPreferUtils.getPersonalInfo();
        }
        return userInfo.isVip() && userInfo.getVipEndTime() * 1000 > SystemAndServerTimeSynchUtil.getSytemTrueTime();
    }

    public static void saveUserInfo(UserInfo userInfo2) {
        SharedPreferUtils.savePersonalInfo(userInfo2);
        userInfo = userInfo2;
    }

    public static UserInfo getUserInfo() {
        if (userInfo == null) {
            userInfo = SharedPreferUtils.getPersonalInfo();
        }
        return userInfo;
    }

    public static void logout() {
        SharedPreferUtils.clearPersonalInfo();
        userInfo = null;
    }
}
