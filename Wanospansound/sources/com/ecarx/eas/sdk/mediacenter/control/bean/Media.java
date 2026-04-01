package com.ecarx.eas.sdk.mediacenter.control.bean;

import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public class Media {
    private String album;
    private int albumIndex;
    private Uri artWork;
    private String artist;
    private String author;
    private String categoryStr;
    private int collected;
    private String composer;
    private String description;
    private long duration;
    private Uri lyric;
    private String lyricContent;
    private String mediaCp;
    private String mediaId;
    private Uri mediaPath;
    private String playingMediaListId;
    private int playingMediaListType;
    private int positionInQueue;
    private String radioFrequency;
    private String radioStationName;
    private String rating;
    private int sourceType;
    private String subCategoryStr;
    private String subtitle;
    private int supportCollect;
    private String targetType;
    private String title;
    private String uuid;
    private int vip;
    private String year;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String str) {
        this.album = str;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public String getComposer() {
        return this.composer;
    }

    public void setComposer(String str) {
        this.composer = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String str) {
        this.year = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public int getPositionInQueue() {
        return this.positionInQueue;
    }

    public void setPositionInQueue(int i) {
        this.positionInQueue = i;
    }

    public int getAlbumIndex() {
        return this.albumIndex;
    }

    public void setAlbumIndex(int i) {
        this.albumIndex = i;
    }

    public String getCategoryStr() {
        return this.categoryStr;
    }

    public void setCategoryStr(String str) {
        this.categoryStr = str;
    }

    public String getSubCategoryStr() {
        return this.subCategoryStr;
    }

    public void setSubCategoryStr(String str) {
        this.subCategoryStr = str;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public String getMediaCp() {
        return this.mediaCp;
    }

    public void setMediaCp(String str) {
        this.mediaCp = str;
    }

    public String getTargetType() {
        return this.targetType;
    }

    public void setTargetType(String str) {
        this.targetType = str;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public Uri getMediaPath() {
        return this.mediaPath;
    }

    public void setMediaPath(Uri uri) {
        this.mediaPath = uri;
    }

    public String getLyricContent() {
        return this.lyricContent;
    }

    public void setLyricContent(String str) {
        this.lyricContent = str;
    }

    public Uri getLyric() {
        return this.lyric;
    }

    public void setLyric(Uri uri) {
        this.lyric = uri;
    }

    public Uri getArtWork() {
        return this.artWork;
    }

    public void setArtWork(Uri uri) {
        this.artWork = uri;
    }

    public String getRadioFrequency() {
        return this.radioFrequency;
    }

    public void setRadioFrequency(String str) {
        this.radioFrequency = str;
    }

    public String getRadioStationName() {
        return this.radioStationName;
    }

    public void setRadioStationName(String str) {
        this.radioStationName = str;
    }

    public String getPlayingMediaListId() {
        return this.playingMediaListId;
    }

    public void setPlayingMediaListId(String str) {
        this.playingMediaListId = str;
    }

    public int getVip() {
        return this.vip;
    }

    public void setVip(int i) {
        this.vip = i;
    }

    public int getPlayingMediaListType() {
        return this.playingMediaListType;
    }

    public void setPlayingMediaListType(int i) {
        this.playingMediaListType = i;
    }

    public int getSupportCollect() {
        return this.supportCollect;
    }

    public void setSupportCollect(int i) {
        this.supportCollect = i;
    }

    public int getCollected() {
        return this.collected;
    }

    public void setCollected(int i) {
        this.collected = i;
    }
}
