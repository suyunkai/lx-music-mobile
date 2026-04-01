package com.wanos.util;

import com.wanos.play.AudioLayout;
import com.wanos.zero.IVolumeMateData;

/* JADX INFO: loaded from: classes3.dex */
public class WanosPlayChannelLayoutUtil {
    public static void init(AudioLayout audioLayout, IVolumeMateData iVolumeMateData) {
        Constant.setAudioLayout(audioLayout);
        Constant.setVolumeConfig(iVolumeMateData);
    }
}
