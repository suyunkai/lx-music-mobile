package com.wanos.commonlibrary.utils;

import android.app.Application;
import android.content.SharedPreferences;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;

/* JADX INFO: loaded from: classes3.dex */
public class SPUser {
    private static final String SP_FILE_NAME = "sp_file";
    public static SPUser instance;
    private static SharedPreferences mSp;

    private SPUser() {
        Application app = Utils.getApp();
        if (mSp == null) {
            mSp = app.getSharedPreferences(SP_FILE_NAME, 0);
        }
    }

    public static SPUser getInstance() {
        if (instance == null) {
            synchronized (SPUser.class) {
                if (instance == null) {
                    instance = new SPUser();
                }
            }
        }
        return instance;
    }

    public void putBoolean(String str, boolean z) {
        SharedPreferences.Editor editorEdit = mSp.edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    public boolean getBoolean(String str) {
        return mSp.getBoolean(str, false);
    }

    public void putInt(String str, int i) {
        SharedPreferences.Editor editorEdit = mSp.edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    public int getInt(String str) {
        return mSp.getInt(str, 0);
    }

    public void putString(String str, String str2) {
        SharedPreferences.Editor editorEdit = mSp.edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    public String getString(String str) {
        return mSp.getString(str, null);
    }

    public static void setObject(String str, Object obj) {
        String json = new Gson().toJson(obj);
        SharedPreferences.Editor editorEdit = mSp.edit();
        editorEdit.putString(str, json);
        editorEdit.apply();
    }

    public static <T> T getObject(String str, Class<T> cls) {
        return (T) new Gson().fromJson(mSp.getString(str, null), (Class) cls);
    }

    public boolean isObjectChanged(String str, Object obj) {
        if (str != null && obj != null) {
            String string = mSp.getString(str, null);
            String json = new Gson().toJson(obj);
            if (string != null && json != null) {
                return string.equals(json);
            }
        }
        return false;
    }

    public void deleteData(String str) {
        SharedPreferences.Editor editorEdit = mSp.edit();
        editorEdit.remove(str);
        editorEdit.apply();
    }

    public void clearData() {
        SharedPreferences.Editor editorEdit = mSp.edit();
        editorEdit.clear();
        editorEdit.apply();
    }
}
