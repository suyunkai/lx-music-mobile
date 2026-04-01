package com.wanos.media.util;

import android.util.Log;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes3.dex */
public class TimerUtil {
    public static boolean isTimeOut(long end) {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = end * 1000;
        Log.d("超时轮询中->过期时间", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(j)));
        long j2 = j - jCurrentTimeMillis;
        long j3 = j2 / 1000;
        Log.d("轮询剩余秒数 == ", (j3 / 60) + "分钟  |或|  " + j3 + "秒");
        return j2 <= 0;
    }
}
