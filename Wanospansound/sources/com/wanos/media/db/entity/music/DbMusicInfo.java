package com.wanos.media.db.entity.music;

/* JADX INFO: loaded from: classes3.dex */
public class DbMusicInfo {
    private String avatar;
    private long dbUpadteTime;
    private long freeEndTime;
    private long id;
    private boolean isFree;
    private boolean isVipAuth;
    private boolean likeStatus;
    private String lrcPath;
    private String name;
    private String path;
    private long previewEndTime;
    private long previewStartTime;
    private String singer;
    private float timeLen;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public float getTimeLen() {
        return this.timeLen;
    }

    public void setTimeLen(float timeLen) {
        this.timeLen = timeLen;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getPath() {
        return this.path;
    }

    public String getLrcPath() {
        return this.lrcPath;
    }

    public void setLrcPath(String lrcPath) {
        this.lrcPath = lrcPath;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSinger() {
        return this.singer;
    }

    public boolean isLikeStatus() {
        return this.likeStatus;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public long getDbUpadteTime() {
        return this.dbUpadteTime;
    }

    public void setDbUpadteTime(long dbUpadteTime) {
        this.dbUpadteTime = dbUpadteTime;
    }

    public boolean isVipAuth() {
        return this.isVipAuth;
    }

    public void setVipAuth(boolean vipAuth) {
        this.isVipAuth = vipAuth;
    }

    public long getPreviewStartTime() {
        return this.previewStartTime;
    }

    public void setPreviewStartTime(long previewStartTime) {
        this.previewStartTime = previewStartTime;
    }

    public long getPreviewEndTime() {
        return this.previewEndTime;
    }

    public void setPreviewEndTime(long previewEndTime) {
        this.previewEndTime = previewEndTime;
    }

    public boolean isFree() {
        return this.isFree;
    }

    public void setFree(boolean free) {
        this.isFree = free;
    }

    public long getFreeEndTime() {
        return this.freeEndTime;
    }

    public void setFreeEndTime(long freeEndTime) {
        this.freeEndTime = freeEndTime;
    }
}
