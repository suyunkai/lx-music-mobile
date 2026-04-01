package com.blankj.utilcode.util;

import android.media.AudioManager;

/* JADX INFO: loaded from: classes.dex */
public class VolumeUtils {
    public static int getVolume(int i) {
        return ((AudioManager) Utils.getApp().getSystemService("audio")).getStreamVolume(i);
    }

    public static void setVolume(int i, int i2, int i3) {
        try {
            ((AudioManager) Utils.getApp().getSystemService("audio")).setStreamVolume(i, i2, i3);
        } catch (SecurityException unused) {
        }
    }

    public static int getMaxVolume(int i) {
        return ((AudioManager) Utils.getApp().getSystemService("audio")).getStreamMaxVolume(i);
    }

    public static int getMinVolume(int i) {
        return ((AudioManager) Utils.getApp().getSystemService("audio")).getStreamMinVolume(i);
    }
}
