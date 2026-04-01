package com.ecarx.eas.sdk.mediacenter;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractNewsClient {
    public static final int TYPE_COLLECTION_IMAGE = 2;
    public static final int TYPE_COLLECTION_MUSIC = 0;
    public static final int TYPE_COLLECTION_NEWS = 4;
    public static final int TYPE_COLLECTION_RADIO = 3;
    public static final int TYPE_COLLECTION_UNKNOWN = -1;
    public static final int TYPE_COLLECTION_VIDEO = 1;
    public static final int TYPE_DOWNLOAD_IMAGE = 2;
    public static final int TYPE_DOWNLOAD_MUSIC = 0;
    public static final int TYPE_DOWNLOAD_NEWS = 4;
    public static final int TYPE_DOWNLOAD_RADIO = 3;
    public static final int TYPE_DOWNLOAD_UNKNOWN = -1;
    public static final int TYPE_DOWNLOAD_VIDEO = 1;

    public abstract NewsPlaybackInfo getNewsPlaybackInfo();

    public abstract boolean onCollect(int i, boolean z);

    public abstract boolean onDownload(int i, boolean z);

    public abstract boolean onExit();

    public abstract void onMediaCenterFocusChanged(String str);

    public abstract boolean onNext();

    public abstract boolean onPause();

    public abstract boolean onPlay();

    public abstract boolean onPrevious();

    public abstract boolean onReplay();
}
