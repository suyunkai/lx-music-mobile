package com.wanos.media.juyihall.utils;

/* JADX INFO: loaded from: classes3.dex */
public class Utils {
    public static boolean equalsId(long j, long j2) {
        return j2 == j;
    }

    public static long setFormatId(long j) {
        return j;
    }

    public static String getFormatId(long j) {
        return String.valueOf(j);
    }

    public static long setFormatId(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return -1L;
        }
    }
}
