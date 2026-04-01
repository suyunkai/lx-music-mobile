package com.wanos.commonlibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes3.dex */
public class DateUtil {
    public static long timeToLong(String str) {
        Long lValueOf = 0L;
        try {
            lValueOf = Long.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lValueOf.longValue();
    }
}
