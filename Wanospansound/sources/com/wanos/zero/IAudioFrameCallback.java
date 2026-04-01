package com.wanos.zero;

/* JADX INFO: loaded from: classes3.dex */
public interface IAudioFrameCallback {
    void onAudioFrame(float[][] fArr, int i);

    default void onAudioPlayerCompleteState(int i, boolean z) {
    }
}
