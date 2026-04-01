package com.wanos.commonlibrary.utils;

/* JADX INFO: loaded from: classes3.dex */
public class ClickUtils {
    private static final long DIFF = 2000;
    private static final long lastButtonId = -1;
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = lastClickTime;
        long j2 = jCurrentTimeMillis - j;
        if (j > 0 && j2 < 2000) {
            return true;
        }
        lastClickTime = System.currentTimeMillis();
        return false;
    }
}
