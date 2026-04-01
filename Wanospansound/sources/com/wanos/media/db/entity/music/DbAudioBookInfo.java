package com.wanos.media.db.entity.music;

/* JADX INFO: loaded from: classes3.dex */
public class DbAudioBookInfo {
    protected String avatar;
    protected int canPreview;
    private long dbId;
    private long dbUpadteTime;
    protected long duration;
    protected long id;
    protected int index;
    protected String introduction;
    protected int isCollect;
    protected int isPay;
    protected int isVip;
    protected String name;
    protected String path;
    protected long progress;
    protected long radioId;
    protected String radioName;
    protected String speakAvatar;
    protected int speakId;
    protected String speakName;
    protected String statusMsg;

    public long getDbId() {
        return this.dbId;
    }

    public void setDbId(long dbId) {
        this.dbId = dbId;
    }

    public long getDbUpadteTime() {
        return this.dbUpadteTime;
    }

    public void setDbUpadteTime(long dbUpadteTime) {
        this.dbUpadteTime = dbUpadteTime;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getIsVip() {
        return this.isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public int getIsPay() {
        return this.isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public int getCanPreview() {
        return this.canPreview;
    }

    public void setCanPreview(int canPreview) {
        this.canPreview = canPreview;
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

    public String getStatusMsg() {
        return this.statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public long getRadioId() {
        return this.radioId;
    }

    public void setRadioId(long radioId) {
        this.radioId = radioId;
    }

    public String getRadioName() {
        return this.radioName;
    }

    public void setRadioName(String radioName) {
        this.radioName = radioName;
    }

    public int getSpeakId() {
        return this.speakId;
    }

    public void setSpeakId(int speakId) {
        this.speakId = speakId;
    }

    public String getSpeakName() {
        return this.speakName;
    }

    public void setSpeakName(String speakName) {
        this.speakName = speakName;
    }

    public long getProgress() {
        return this.progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public String getSpeakAvatar() {
        return this.speakAvatar;
    }

    public void setSpeakAvatar(String speakAvatar) {
        this.speakAvatar = speakAvatar;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIsCollect() {
        return this.isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
