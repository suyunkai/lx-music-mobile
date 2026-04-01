package com.wanos.commonlibrary.utils;

/* JADX INFO: loaded from: classes3.dex */
public class SystemAndServerTimeSynchUtil {
    public static long systemAndServerTimeCz;

    public static long getSytemTrueTime() {
        return System.currentTimeMillis() - systemAndServerTimeCz;
    }
}
