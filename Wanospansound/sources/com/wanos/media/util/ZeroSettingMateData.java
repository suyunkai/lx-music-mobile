package com.wanos.media.util;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroSettingMateData {
    public static final int CHAIR_ALL = 0;
    public static final int CHAIR_MAIN = 1;
    public static final int CHAIR_SUB = 2;
    private static final String KEY_ALARM = "zero_alarm";
    private static final String KEY_CHAIR = "zero_chair";
    private static final String KEY_DOOR = "zero_door";
    private static final String KEY_KT = "zero_kt";
    private static final String KEY_KT_F = "zero_kt_f";
    private static final String KEY_KT_I = "zero_kt_i";
    private static final String KEY_LIGHTING = "zero_lighting";
    public static final String KEY_NOW_DATE = "zero_album_date";
    private static final String KEY_SEAT = "zero_seat";
    private static final String SP_NAME = "ZeroSettingMateData";
    private static final String TAG = "ZeroSettingMateData";

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChairPosition {
    }

    public static void insertChair(int i) {
        SPUtils.getInstance("ZeroSettingMateData").put(KEY_CHAIR, i, true);
    }

    public static int findChair() {
        return SPUtils.getInstance("ZeroSettingMateData").getInt(KEY_CHAIR, 1);
    }

    public static void setSeatState(boolean z) {
        SPUtils.getInstance("ZeroSettingMateData").put(KEY_SEAT, z, true);
    }

    public static void setDoorState(boolean z) {
        SPUtils.getInstance("ZeroSettingMateData").put(KEY_DOOR, z, true);
    }

    public static void setLightingState(boolean z) {
        SPUtils.getInstance("ZeroSettingMateData").put(KEY_LIGHTING, z, true);
    }

    public static boolean getSeatState() {
        return SPUtils.getInstance("ZeroSettingMateData").getBoolean(KEY_SEAT, false);
    }

    public static boolean getDoorState() {
        return SPUtils.getInstance("ZeroSettingMateData").getBoolean(KEY_DOOR, false);
    }

    public static boolean getLightingState() {
        return SPUtils.getInstance("ZeroSettingMateData").getBoolean(KEY_LIGHTING, false);
    }

    public static void setKTState(boolean z, String str, String str2) {
        SPUtils.getInstance("ZeroSettingMateData").put(KEY_KT, z, true);
        SPUtils.getInstance("ZeroSettingMateData").put(KEY_KT_I, str, true);
        SPUtils.getInstance("ZeroSettingMateData").put(KEY_KT_F, str2, true);
    }

    public static boolean getKTState() {
        return SPUtils.getInstance("ZeroSettingMateData").getBoolean(KEY_KT, false);
    }

    public static String getKTI() {
        return SPUtils.getInstance("ZeroSettingMateData").getString(KEY_KT_I, "23");
    }

    public static String getKTF() {
        return SPUtils.getInstance("ZeroSettingMateData").getString(KEY_KT_F, "5");
    }

    public static void insertAlarmPath(String str) {
        SPUtils.getInstance("ZeroSettingMateData").put(KEY_ALARM, str, true);
    }

    public static String findAlarmPath() {
        return SPUtils.getInstance("ZeroSettingMateData").getString(KEY_ALARM, "");
    }

    public static void setNowDate() {
        String nowDate = getNowDate();
        ZeroLogcatTools.d("ZeroSettingMateData", "当前日期=" + nowDate);
        SPUtils.getInstance("ZeroSettingMateData").put(KEY_NOW_DATE, nowDate);
    }

    public static boolean isRefreshAlbumData() {
        return !getNowDate().equals(SPUtils.getInstance("ZeroSettingMateData").getString(KEY_NOW_DATE));
    }

    private static String getNowDate() {
        return TimeUtils.getSafeDateFormat("yyyy-MM-dd").format(TimeUtils.getNowDate());
    }
}
