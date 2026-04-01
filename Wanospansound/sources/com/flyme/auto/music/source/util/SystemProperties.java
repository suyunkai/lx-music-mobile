package com.flyme.auto.music.source.util;

/* JADX INFO: loaded from: classes2.dex */
public class SystemProperties {
    private static final String ANDROID_SYS_CLASS = "android.os.SystemProperties";
    private static final String FRONT_ROW = "1";
    private static final String SYSTEM_BOOT = "ro.boot.dhu";
    private static final String TAG = "SystemProperties";
    private boolean isFrontDhu = true;

    public static boolean isFrontDhu() {
        try {
            String str = (String) Class.forName(ANDROID_SYS_CLASS).getDeclaredMethod("get", String.class, String.class).invoke(null, SYSTEM_BOOT, "");
            MusicSourceLog.i(TAG, "initProperty called get = " + str);
            return "1".equals(str);
        } catch (Throwable th) {
            th.printStackTrace();
            MusicSourceLog.w(TAG, "cannot initProperty: " + th.getMessage());
            return true;
        }
    }
}
