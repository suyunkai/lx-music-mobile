package com.wanos.mediacenter.utils;

import android.content.SharedPreferences;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.mediacenter.bean.ProgressInfo;

/* JADX INFO: loaded from: classes3.dex */
public class MediaSharedPreferUtils {
    private static final String AUDIO_BOOK_DOUBLE_SPEED_KEY = "audio_book_double_speed_key";
    private static final String AUDIO_BOOK_DOUBLE_SPEED_RECORD = "audio_book_double_speed_record";
    private static final String IS_ADVANCED_DEV = "is_advanced_dev";
    private static final String IS_ADVANCED_DEV_KEY = "is_advanced_dev_key";
    private static final String MEDIA_INFO_KEY = "media_info_key";
    private static final String MEDIA_INFO_RECORD = "media_info_record";
    private static final String MEDIA_MODE_AUDIO_BOOK_KEY = "media_mode_audio_book_key";
    private static final String MEDIA_MODE_MUSIC_KEY = "media_mode_music_key";
    private static final String MEDIA_MODE_RECORD = "media_mode_record";
    private static final String MEDIA_PROGRESS_KEY = "media_progress_key";
    private static final String MEDIA_PROGRESS_RECORD = "media_progress_record";

    public static void putMediainfo(Object obj) {
        putObject(Utils.getApp().getSharedPreferences(MEDIA_INFO_RECORD, 0), MEDIA_INFO_KEY, obj);
    }

    public static MediaInfo getMediainfo() {
        return (MediaInfo) getObject(Utils.getApp().getSharedPreferences(MEDIA_INFO_RECORD, 0), MEDIA_INFO_KEY, MediaInfo.class);
    }

    public static void putProgress(ProgressInfo progressInfo) {
        putObject(Utils.getApp().getSharedPreferences(MEDIA_PROGRESS_RECORD, 0), MEDIA_PROGRESS_KEY, progressInfo);
    }

    public static ProgressInfo getProgress() {
        return (ProgressInfo) getObject(Utils.getApp().getSharedPreferences(MEDIA_PROGRESS_RECORD, 0), MEDIA_PROGRESS_KEY, ProgressInfo.class);
    }

    private static void putObject(SharedPreferences sharedPreferences, String str, Object obj) {
        String json = new Gson().toJson(obj);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(str, json);
        editorEdit.apply();
    }

    public static <T> T getObject(SharedPreferences sharedPreferences, String str, Class<T> cls) {
        return (T) new Gson().fromJson(sharedPreferences.getString(str, null), (Class) cls);
    }

    public static void putMusicMode(MediaPlayerEnum.PlayMode playMode) {
        putInt(Utils.getApp().getSharedPreferences(MEDIA_MODE_RECORD, 0), MEDIA_MODE_MUSIC_KEY, playMode.ordinal());
    }

    public static MediaPlayerEnum.PlayMode getMusicMode() {
        return MediaPlayerEnum.PlayMode.intToEnum(getInt(Utils.getApp().getSharedPreferences(MEDIA_MODE_RECORD, 0), MEDIA_MODE_MUSIC_KEY, MediaPlayerEnum.PlayMode.listloopplay.ordinal()));
    }

    public static void putAudioBookMode(MediaPlayerEnum.PlayMode playMode) {
        putInt(Utils.getApp().getSharedPreferences(MEDIA_MODE_RECORD, 0), MEDIA_MODE_AUDIO_BOOK_KEY, playMode.ordinal());
    }

    public static MediaPlayerEnum.PlayMode getAudioBookMode() {
        return MediaPlayerEnum.PlayMode.intToEnum(getInt(Utils.getApp().getSharedPreferences(MEDIA_MODE_RECORD, 0), MEDIA_MODE_AUDIO_BOOK_KEY, MediaPlayerEnum.PlayMode.listloopplay.ordinal()));
    }

    public static void putInt(SharedPreferences sharedPreferences, String str, int i) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    public static int getInt(SharedPreferences sharedPreferences, String str) {
        return getInt(sharedPreferences, str, -1);
    }

    public static int getInt(SharedPreferences sharedPreferences, String str, int i) {
        return sharedPreferences.getInt(str, i);
    }

    public static float getFloat(SharedPreferences sharedPreferences, String str, float f) {
        return sharedPreferences.getFloat(str, f);
    }

    public static void putFloat(SharedPreferences sharedPreferences, String str, float f) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putFloat(str, f);
        editorEdit.apply();
    }

    public static boolean getBoolean(SharedPreferences sharedPreferences, String str, boolean z) {
        return sharedPreferences.getBoolean(str, z);
    }

    public static void putBoolean(SharedPreferences sharedPreferences, String str, boolean z) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    public boolean isObjectChanged(String str, Object obj) {
        if (str != null && obj != null) {
            String string = Utils.getApp().getSharedPreferences(MEDIA_INFO_RECORD, 0).getString(str, null);
            String json = new Gson().toJson(obj);
            if (string != null && json != null && string.equals(json)) {
                return true;
            }
        }
        return false;
    }

    public static void clearMediainfo() {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(MEDIA_INFO_RECORD, 0).edit();
        if (editorEdit != null) {
            editorEdit.clear();
        }
        editorEdit.commit();
    }

    public static void clearProgress() {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(MEDIA_PROGRESS_RECORD, 0).edit();
        if (editorEdit != null) {
            editorEdit.clear();
        }
        editorEdit.commit();
    }

    public static void clearMediaModeRecord() {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(MEDIA_MODE_RECORD, 0).edit();
        if (editorEdit != null) {
            editorEdit.clear();
        }
        editorEdit.commit();
    }

    public static void putDoubleSpeed(float f) {
        putFloat(Utils.getApp().getSharedPreferences(AUDIO_BOOK_DOUBLE_SPEED_RECORD, 0), AUDIO_BOOK_DOUBLE_SPEED_KEY, f);
    }

    public static float getDoubleSpeed() {
        return getFloat(Utils.getApp().getSharedPreferences(AUDIO_BOOK_DOUBLE_SPEED_RECORD, 0), AUDIO_BOOK_DOUBLE_SPEED_KEY, 1.0f);
    }

    public static void putIsAdvancedDev(boolean z) {
        putBoolean(Utils.getApp().getSharedPreferences(IS_ADVANCED_DEV, 0), IS_ADVANCED_DEV_KEY, z);
    }

    public static boolean getIsAdvancedDev() {
        return getBoolean(Utils.getApp().getSharedPreferences(IS_ADVANCED_DEV, 0), IS_ADVANCED_DEV_KEY, false);
    }

    public static void clearDoubleSpeed() {
        SharedPreferences.Editor editorEdit = Utils.getApp().getSharedPreferences(AUDIO_BOOK_DOUBLE_SPEED_RECORD, 0).edit();
        if (editorEdit != null) {
            editorEdit.clear();
        }
        editorEdit.commit();
    }
}
