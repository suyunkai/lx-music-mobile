package com.wanos.mediacenter;

import android.net.Uri;
import com.ecarx.eas.sdk.mediacenter.MediaInfo;
import com.wanos.commonlibrary.bean.MediaInfoBean;

/* JADX INFO: loaded from: classes3.dex */
public final class WanosMediaInfo extends MediaInfo {
    private static final String TAG = "wanos:[MediaInfo]";
    private final MediaInfoBean mediaInfoBean;

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public int getPlayingMediaListType() {
        return 0;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public int getSourceType() {
        return 6;
    }

    public WanosMediaInfo(MediaInfoBean mediaInfoBean) {
        this.mediaInfoBean = mediaInfoBean;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public String getTitle() {
        return this.mediaInfoBean.getTitle();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public String getArtist() {
        return this.mediaInfoBean.getArtist();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public String getAlbum() {
        return this.mediaInfoBean.getAlbum();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public long getDuration() {
        return this.mediaInfoBean.getDuration();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public Uri getArtwork() {
        return this.mediaInfoBean.getArtwork();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public String getMediaId() {
        return this.mediaInfoBean.getUuid();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public String getUuid() {
        return this.mediaInfoBean.getUuid();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public String getRadioStationName() {
        return this.mediaInfoBean.getAppName();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public String getPlayingMediaListId() {
        return this.mediaInfoBean.getUuid();
    }
}
