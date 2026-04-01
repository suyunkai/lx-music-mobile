package com.ecarx.eas.sdk.mediacenter;

import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public class RecommendInfoBean extends RecommendInfo {
    private String artist;
    private Uri artwork;
    private String id;
    private int recommendType;
    private String textDes;
    private String title;

    @Override // com.ecarx.eas.sdk.mediacenter.RecommendInfo, com.ecarx.eas.sdk.mediacenter.AbstractRecommendInfo
    public int getRecommendType() {
        return this.recommendType;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.RecommendInfo, com.ecarx.eas.sdk.mediacenter.AbstractRecommendInfo
    public String getId() {
        return this.id;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.RecommendInfo, com.ecarx.eas.sdk.mediacenter.AbstractRecommendInfo
    public String getTitle() {
        return this.title;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.RecommendInfo, com.ecarx.eas.sdk.mediacenter.AbstractRecommendInfo
    public String getArtist() {
        return this.artist;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.RecommendInfo, com.ecarx.eas.sdk.mediacenter.AbstractRecommendInfo
    public Uri getArtwork() {
        return this.artwork;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.RecommendInfo, com.ecarx.eas.sdk.mediacenter.AbstractRecommendInfo
    public String getTextDescription() {
        return this.textDes;
    }

    public void setRecommendType(int i) {
        this.recommendType = i;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public void setArtwork(Uri uri) {
        this.artwork = uri;
    }

    public void setTextDes(String str) {
        this.textDes = str;
    }
}
