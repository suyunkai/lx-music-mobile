package com.wanos.careditproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes3.dex */
public class StorageUtils {
    private static String keyAudioInputDev = "AudioInputDevice";
    private static String keyBeatNum = "beatNum";
    private static String keyBeatPlay = "beatPlay";
    private static String keyBeatSpeed = "beatSpeed";
    private static String keyBeatSwitch = "beatSwitch";
    private static String keyBeatTotal = "beatTotal";
    private static String storageName = "carEdit";
    private static StorageUtils storageUtils;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private int audioInputDeviceId = -1;
    private boolean beatSwitch = false;
    private boolean beatPlay = false;
    private int beatSpeed = 120;
    private int beatNum = 2;
    private int beatTotal = 4;

    public static StorageUtils getInstance() {
        if (storageUtils == null) {
            storageUtils = new StorageUtils();
        }
        return storageUtils;
    }

    public void init(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(storageName, 0);
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
        this.audioInputDeviceId = this.sharedPreferences.getInt(keyAudioInputDev, 1);
        this.beatSwitch = this.sharedPreferences.getBoolean(keyBeatSwitch, false);
        this.beatPlay = this.sharedPreferences.getBoolean(keyBeatPlay, false);
        this.beatSpeed = this.sharedPreferences.getInt(keyBeatSpeed, 120);
        this.beatNum = this.sharedPreferences.getInt(keyBeatNum, 2);
        this.beatTotal = this.sharedPreferences.getInt(keyBeatTotal, 4);
    }

    public void setAudioInputDevice(int i) {
        this.audioInputDeviceId = i;
        this.editor.putInt(keyAudioInputDev, i);
        this.editor.commit();
    }

    public int getAudioInputDevice() {
        return this.audioInputDeviceId;
    }

    public boolean getBeatSwitch() {
        return this.beatSwitch;
    }

    public void setBeatSwitch(boolean z) {
        this.beatSwitch = z;
        this.editor.putBoolean(keyBeatSwitch, z);
        this.editor.commit();
    }

    public boolean getBeatPlay() {
        return this.beatPlay;
    }

    public void setBeatPlay(boolean z) {
        this.beatPlay = z;
        this.editor.putBoolean(keyBeatPlay, z);
        this.editor.commit();
    }

    public int getBeatSpeed() {
        return this.beatSpeed;
    }

    public void setBeatSpeed(int i) {
        this.beatSpeed = i;
        this.editor.putInt(keyBeatSpeed, i);
        this.editor.commit();
    }

    public int getBeatNum() {
        return this.beatNum;
    }

    public void setBeatNum(int i) {
        this.beatNum = i;
        this.editor.putInt(keyBeatNum, i);
        this.editor.commit();
    }

    public int getBeatTotal() {
        return this.beatTotal;
    }

    public void setBeatTotal(int i) {
        this.beatTotal = i;
        this.editor.putInt(keyBeatTotal, i);
        this.editor.commit();
    }
}
