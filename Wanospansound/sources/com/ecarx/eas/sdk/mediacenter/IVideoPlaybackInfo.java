package com.ecarx.eas.sdk.mediacenter;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface IVideoPlaybackInfo {
    public static final int PLAYBACK_STATUS_INTERRUPT = 2;
    public static final int PLAYBACK_STATUS_PAUSED = 0;
    public static final int PLAYBACK_STATUS_PLAYING = 1;
    public static final int PLAYBACK_STATUS_PREPARE = 3;

    int getPlaybackStatus();
}
