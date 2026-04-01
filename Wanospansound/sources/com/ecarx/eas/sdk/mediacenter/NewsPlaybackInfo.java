package com.ecarx.eas.sdk.mediacenter;

/* JADX INFO: loaded from: classes2.dex */
public class NewsPlaybackInfo extends AbstractNewsPlaybackInfo {
    @Override // com.ecarx.eas.sdk.mediacenter.AbstractNewsPlaybackInfo
    public int getPlaybackStatus() {
        return 0;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractNewsPlaybackInfo
    public boolean isCollected() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractNewsPlaybackInfo
    public boolean isDownloaded() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractNewsPlaybackInfo
    public boolean isSupportCollect() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractNewsPlaybackInfo
    public boolean isSupportDownload() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.AbstractNewsPlaybackInfo
    public boolean isSupportVrCtrlPlayStatus() {
        return true;
    }
}
