package com.wanos.sdk.audio;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public interface IWanosAudioConfig {

    public interface IWanosAudioPlayStateListener {
        void onAudioPlayState(WanosPlayState wanosPlayState);
    }

    public enum WanosIntegratedState {
        WANOS_DECODER("wanos-decoder"),
        WANOS_DECODER_UPMIX("wanos-decoder-upmix"),
        WANOS_DECODER_UPMIX_TUNING("wanos-decoder-upmix-tuning"),
        WANOS_DECODER_UNINTEGRATE("unknow");

        private String labelName;

        WanosIntegratedState(String str) {
            this.labelName = str;
        }

        public String getLabelName() {
            return this.labelName;
        }
    }

    public enum WanosPlayState {
        PLAY_STATE_WANOS_SOURCE_PLAYING,
        PLAY_STATE_WANOS_VIRTUAL_SOURCE_PLAYING,
        PLAY_STATE_OTHER
    }

    void addWanosAudioPlayStateListener(IWanosAudioPlayStateListener iWanosAudioPlayStateListener);

    String getDeviceChannelId(Context context);

    WanosIntegratedState getDeviceIntegratedState(Context context);

    String getDeviceManuFacturer(Context context);

    String getDeviceToken(Context context);

    String getDeviceWanosDecoderVersion(Context context);

    int getWanosAudioOutputChannelNum(Context context);

    boolean isWanosSourcePlaying();

    boolean isWanosVirtualSourcePlaying();

    void removeWanosAudioPlayStateListener(IWanosAudioPlayStateListener iWanosAudioPlayStateListener);
}
