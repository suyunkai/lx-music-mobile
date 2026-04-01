package com.wanos.careditproject.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public class DateUtils {
    public static String convertLongToDate(long j) {
        return new SimpleDateFormat("yyyy.MM.dd").format(new Date(j));
    }

    public static String convertFloatToMinSec(float f) {
        return new SimpleDateFormat("mm:ss").format(new Date((long) (f * 1000.0f)));
    }

    public static String convertLongToMinSec(long j) {
        return new SimpleDateFormat("mm:ss").format(new Date(j));
    }
}
