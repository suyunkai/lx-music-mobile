package com.wanos.util;

import android.media.AudioManager;
import android.util.Log;
import com.wanos.commonlibrary.mediaCenter.AudioConfig;
import com.wanos.commonlibrary.utils.CommonUtils;

/* JADX INFO: loaded from: classes3.dex */
public class WanosEffectManager {
    private static final String TAG = "WanosEffectManager";
    private static AudioManager manager;

    public static void wanOSOnOn() {
        toggleWanosEffect(true);
    }

    public static void wanOSOnOff() {
        toggleWanosEffect(false);
    }

    private static void toggleWanosEffect(boolean z) {
        if (manager == null) {
            manager = (AudioManager) CommonUtils.getApplication().getSystemService("audio");
        }
        if (manager == null || !AudioConfig.isNeedConfigAudioPar) {
            return;
        }
        Log.i(TAG, "eff value 2:" + z);
        manager.setParameters(String.format("wanos_effect=(%s)", Integer.valueOf(z ? 1 : 0)));
    }
}
