package com.ecarx.eas.sdk.mediacenter;

/* JADX INFO: loaded from: classes2.dex */
public class VideoPlaybackInfo extends AbstractVideoPlaybackInfo {
    @Override // com.ecarx.eas.sdk.mediacenter.AbstractVideoPlaybackInfo
    public int getPlaybackStatus() {
        return 0;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractVideoPlaybackInfo
    public boolean isCollected() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractVideoPlaybackInfo
    public boolean isDownloaded() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractVideoPlaybackInfo
    public boolean isSupportCollect() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractVideoPlaybackInfo
    public boolean isSupportDownload() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractVideoPlaybackInfo
    public boolean isSupportVrCtrlPlayStatus() {
        return true;
    }
}
