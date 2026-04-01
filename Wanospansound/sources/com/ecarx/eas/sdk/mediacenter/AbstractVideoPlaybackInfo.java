package com.ecarx.eas.sdk.mediacenter;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractVideoPlaybackInfo {
    public static final int PLAYBACK_STATUS_INTERRUPT = 2;
    public static final int PLAYBACK_STATUS_PAUSED = 0;
    public static final int PLAYBACK_STATUS_PLAYING = 1;
    public static final int PLAYBACK_STATUS_PREPARE = 3;

    public abstract int getPlaybackStatus();

    public abstract boolean isCollected();

    public abstract boolean isDownloaded();

    public abstract boolean isSupportCollect();

    public abstract boolean isSupportDownload();

    public abstract boolean isSupportVrCtrlPlayStatus();
}
