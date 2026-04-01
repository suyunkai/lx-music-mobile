package com.ecarx.eas.sdk.mediacenter;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface INewsClient {
    INewsPlaybackInfo getNewsPlaybackInfo();

    boolean onNext();

    boolean onPause();

    boolean onPlay();

    boolean onPrevious();
}
