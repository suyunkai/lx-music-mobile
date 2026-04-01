package com.wanos.sdk;

import com.wanos.sdk.audio.IWanosAudioConfig;
import com.wanos.sdk.audio.IWanosAudioUpmix;
import com.wanos.sdk.effect.IWanosAudioEffect;

/* JADX INFO: loaded from: classes3.dex */
public interface IWanosAudio {
    IWanosAudioConfig getWanosAudioConfig();

    IWanosAudioEffect getWanosAudioEffect();

    IWanosAudioUpmix getWanosAudioUpmix();
}
