package com.wanos.util;

import com.wanos.play.AudioLayout;
import com.wanos.zero.IVolumeMateData;
import com.wanos.zero.S73_Volume;

/* JADX INFO: loaded from: classes3.dex */
public class Constant {
    public static int BUFFERSIZE = 1024;
    private static AudioLayout audioLayout = AudioLayout.WANOS_CH_512;
    public static String carBrand = "CarBrandZhiji";
    public static int carCompanyId = 999;
    public static String carModel = "s1000";
    private static int channelNum = 2;
    private static final int channelNumCar = 8;
    private static final int channelNumXiaomi = 2;
    private static IVolumeMateData mVolumeConfig;

    public static boolean isXiaomi() {
        return false;
    }

    public static AudioLayout getAudioLayout() {
        return audioLayout;
    }

    public static void setAudioLayout(AudioLayout audioLayout2) {
        audioLayout = audioLayout2;
    }

    public static int getPlayerLayout() {
        return audioLayout.getAudioFormat();
    }

    public static void setVolumeConfig(IVolumeMateData iVolumeMateData) {
        mVolumeConfig = iVolumeMateData;
    }

    public static IVolumeMateData getVolumeConfig() {
        if (mVolumeConfig == null) {
            mVolumeConfig = new S73_Volume();
        }
        return mVolumeConfig;
    }

    public static String getPlayerLayoutName() {
        return audioLayout.getName();
    }

    public static int getPlayerChannelNum() {
        return audioLayout.getChannelNum();
    }
}
