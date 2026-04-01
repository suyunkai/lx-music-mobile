package com.wanos.careditproject.utils;

import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes3.dex */
public class EncodeChinese {
    private static boolean isChinese(char c2) {
        return c2 >= 19968 && c2 <= 40869;
    }

    public static String convertChineseToUTF8(String str) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (isChinese(cCharAt)) {
                for (byte b2 : String.valueOf(cCharAt).getBytes("UTF-8")) {
                    sb.append("%").append(Integer.toHexString(b2 & 255).toUpperCase());
                }
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }
}
