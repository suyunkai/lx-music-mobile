package com.wanos.careditproject.utils;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class WanosStringUtils {
    private static final Pattern CHINESE_CHAR_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");

    public static boolean isContainOnlyCnLetterOrDigit(String str) {
        return Pattern.compile("^[a-zA-Z0-9_\\p{Script=Han}]+$").matcher(str).matches();
    }

    public static boolean containsChineseCharacter(String str) {
        return CHINESE_CHAR_PATTERN.matcher(str).find();
    }
}
