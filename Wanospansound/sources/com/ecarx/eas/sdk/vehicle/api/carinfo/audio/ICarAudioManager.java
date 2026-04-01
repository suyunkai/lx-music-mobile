package com.ecarx.eas.sdk.vehicle.api.carinfo.audio;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarAudioManager {
    public static final int AUDIO_ZONE_BLUETOOTH = 1;
    public static final int AUDIO_ZONE_NORMAL = 0;
    public static final int ERROR = -1;
    public static final String MEDIA_USAGE_STRING = "media_usage";
    public static final String NAVI_USAGE_STRING = "navi_usage";
    public static final String PHONE_USAGE_STRING = "phone_usage";
    public static final String VOICE_USAGE_STRING = "voice_usage";

    int getUsageMaxVolume(String str);

    int getUsageMinVolume(String str);

    int getUsageVolume(String str);

    boolean registerCarVolumeChangeCallback(ICarVolumeChangeCallback iCarVolumeChangeCallback);

    boolean unRegisterCarVolumeChangeCallback(ICarVolumeChangeCallback iCarVolumeChangeCallback);
}
