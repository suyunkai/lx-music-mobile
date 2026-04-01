package com.wanos.commonlibrary.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.wanos.commonlibrary.bean.UserInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SharedPreferUtils {
    private static final String IS_FIRST_GUIDE = "IS_FIRST_GUIDE";
    private static final String LOCAL_ALARM_PATH = "LOCAL_ALARM_PATH";
    private static final String MEDIA_INFO_KEY = "media_info_key";
    private static final String MEDIA_INFO_RECORD = "media_info_record";
    private static final String MEDIA_MODE_AUDIO_BOOK_KEY = "media_mode_audio_book_key";
    private static final String MEDIA_MODE_MUSIC_KEY = "media_mode_music_key";
    private static final String MEDIA_MODE_RECORD = "media_mode_record";
    private static final String MEDIA_PROGRESS_KEY = "media_progress_key";
    private static final String MEDIA_PROGRESS_RECORD = "media_progress_record";
    private static final String SEARCH_RECORD = "search_record";
    private static final String SERVICE_PROTOCOL = "service_protocol";
    private static final String TAG = "wanos:[SharedPreferUtils]";
    private static final String USER_INFO = "user_info";

    public static void savePersonalInfo(UserInfo userInfo) {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(USER_INFO, 0).edit();
        editorEdit.putString("userInfo", userInfo == null ? "" : new Gson().toJson(userInfo));
        editorEdit.commit();
    }

    public static UserInfo getPersonalInfo() {
        String string = Utils.getApp().getSharedPreferences(USER_INFO, 0).getString("userInfo", "");
        if (TextUtils.isEmpty(string)) {
            return new UserInfo();
        }
        return (UserInfo) new Gson().fromJson(string, UserInfo.class);
    }

    public static void clearPersonalInfo() {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(USER_INFO, 0).edit();
        if (editorEdit != null) {
            editorEdit.clear();
            editorEdit.commit();
        }
    }

    public static void setFirstGuide(boolean z) {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(IS_FIRST_GUIDE, 0).edit();
        editorEdit.putBoolean(IS_FIRST_GUIDE, z);
        editorEdit.commit();
    }

    public static boolean isFirstGuide() {
        return Utils.getApp().getSharedPreferences(IS_FIRST_GUIDE, 0).getBoolean(IS_FIRST_GUIDE, true);
    }

    public static void saveSearchRecord(String str) {
        SharedPreferences sharedPreferences = Utils.getApp().getSharedPreferences(SEARCH_RECORD, 0);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(sharedPreferences.getString(SEARCH_RECORD, "").split("->")));
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        if (arrayList.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= arrayList.size()) {
                    break;
                }
                if (str.equals(arrayList.get(i))) {
                    arrayList.remove(i);
                    break;
                }
                i++;
            }
            arrayList.add(0, str);
            if (arrayList.size() > 10) {
                arrayList.remove(arrayList.size() - 1);
            }
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                sb.append(((String) arrayList.get(i2)) + "->");
            }
            editorEdit.putString(SEARCH_RECORD, sb.toString());
            editorEdit.commit();
            return;
        }
        editorEdit.putString(SEARCH_RECORD, str + "->");
        editorEdit.commit();
    }

    public static List<String> getSearchRecord() {
        ArrayList arrayList = new ArrayList(Arrays.asList(Utils.getApp().getSharedPreferences(SEARCH_RECORD, 0).getString(SEARCH_RECORD, "").split("->")));
        if (arrayList.size() == 1 && ((String) arrayList.get(0)).equals("")) {
            arrayList.clear();
        }
        return arrayList;
    }

    public static void clearSearchRecord() {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(SEARCH_RECORD, 0).edit();
        if (editorEdit != null) {
            editorEdit.clear();
        }
        editorEdit.commit();
    }

    public static void saveProtocolVersion(int i, int i2) {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(SERVICE_PROTOCOL, 0).edit();
        Log.i(TAG, "协议版本保存 ：协议类型=" + i + "  //  协议版本=" + i2);
        editorEdit.putInt(i + "", i2);
        editorEdit.commit();
    }

    public static void saveProtocolContent(int i, String str) {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(SERVICE_PROTOCOL, 0).edit();
        Log.i(TAG, "协议版本保存 ：协议类型=" + i);
        editorEdit.putString(i + "-", str);
        editorEdit.commit();
    }

    public static int getProtocolVersion(int i) {
        return Utils.getApp().getSharedPreferences(SERVICE_PROTOCOL, 0).getInt(i + "", 0);
    }

    public static String getProtocolContent(int i) {
        return Utils.getApp().getSharedPreferences(SERVICE_PROTOCOL, 0).getString(i + "-", "");
    }

    public static void saveLocalAlarmPath(String str) {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(LOCAL_ALARM_PATH, 0).edit();
        editorEdit.putString(LOCAL_ALARM_PATH, str);
        editorEdit.apply();
    }

    public static String getLocalAlarmPath() {
        return Utils.getApp().getSharedPreferences(LOCAL_ALARM_PATH, 0).getString(LOCAL_ALARM_PATH, null);
    }
}
