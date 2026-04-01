package com.wanos.commonlibrary.bean;

import android.net.Uri;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;

/* JADX INFO: loaded from: classes3.dex */
public class MediaInfoBean {
    private String Uuid;
    private String album;
    private String appIcon;
    private String appName;
    private String artist;
    private Uri artwork;
    private long duration;
    private boolean isCollection;
    public MediaPlayerEnum.MediaType mediaType;
    private String packageName;
    private int radioMode;
    private String title;

    public String getUuid() {
        return this.Uuid;
    }

    public void setUuid(String str) {
        this.Uuid = str;
    }

    public String getAlbum() {
        String str = this.album;
        return str == null ? "nihao11" : str;
    }

    public void setAlbum(String str) {
        this.album = str;
    }

    public void setAppIcon(String str) {
        this.appIcon = str;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public Uri getArtwork() {
        return this.artwork;
    }

    public void setArtwork(Uri uri) {
        this.artwork = uri;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public boolean isCollection() {
        return this.isCollection;
    }

    public void setCollection(boolean z) {
        this.isCollection = z;
    }

    public MediaPlayerEnum.MediaType getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(MediaPlayerEnum.MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getAppIcon() {
        return this.appIcon;
    }

    public final int getRadioMode() {
        return this.radioMode;
    }

    public final void setRadioMode(int i) {
        this.radioMode = i;
    }
}
