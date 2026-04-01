package com.wanos.mediacenter;

import com.ecarx.eas.sdk.mediacenter.MediaInfo;
import com.ecarx.eas.sdk.mediacenter.MediaListInfo;
import com.wanos.commonlibrary.bean.MediaInfoBean;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class WanosMediaListInfo extends MediaListInfo {
    private final MediaInfoBean mediaInfoBean;

    @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
    public int getMediaListType() {
        return 6;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
    public int getSourceType() {
        return 6;
    }

    public WanosMediaListInfo(MediaInfoBean mediaInfoBean) {
        this.mediaInfoBean = mediaInfoBean;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
    public List<MediaInfo> getMediaList() {
        ArrayList arrayList = new ArrayList();
        MediaInfoBean mediaInfoBean = new MediaInfoBean();
        mediaInfoBean.setTitle(this.mediaInfoBean.getTitle());
        mediaInfoBean.setAppIcon(this.mediaInfoBean.getAppIcon());
        mediaInfoBean.setUuid(this.mediaInfoBean.getUuid());
        mediaInfoBean.setAppName(this.mediaInfoBean.getAppName());
        mediaInfoBean.setAppIcon(this.mediaInfoBean.getAppIcon());
        mediaInfoBean.setArtist(this.mediaInfoBean.getArtist());
        mediaInfoBean.setAlbum(this.mediaInfoBean.getAlbum());
        mediaInfoBean.setArtwork(this.mediaInfoBean.getArtwork());
        arrayList.add(new WanosMediaInfo(mediaInfoBean));
        return arrayList;
    }
}
