package com.ecarx.eas.sdk.mediacenter;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface IVideoClient {
    IVideoPlaybackInfo getVideoPlaybackInfo();

    boolean onForward();

    boolean onNext();

    boolean onPause();

    boolean onPlay();

    boolean onPrevious();

    boolean onRewind();
}
