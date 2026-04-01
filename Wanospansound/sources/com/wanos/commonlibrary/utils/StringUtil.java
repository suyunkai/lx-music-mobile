package com.wanos.commonlibrary.utils;

import com.alibaba.android.arouter.utils.Consts;

/* JADX INFO: loaded from: classes3.dex */
public class StringUtil {
    private static boolean isHasZeroStr(String str) {
        return str != null && str.length() > 2 && str.indexOf(Consts.DOT) > 0 && str.lastIndexOf("0") == str.length() - 1;
    }

    public static String getIntOrFloatStr(String str) {
        int iIndexOf;
        int iLastIndexOf;
        String strSubstring;
        if (str == null || str.length() <= 2 || (iIndexOf = str.indexOf(Consts.DOT)) <= 0 || (iLastIndexOf = str.lastIndexOf("0")) != str.length() - 1) {
            return str;
        }
        if (iIndexOf == str.length() - 2) {
            strSubstring = str.substring(0, iIndexOf);
        } else {
            strSubstring = str.substring(0, iLastIndexOf);
        }
        return isHasZeroStr(strSubstring) ? getIntOrFloatStr(strSubstring) : strSubstring;
    }
}
