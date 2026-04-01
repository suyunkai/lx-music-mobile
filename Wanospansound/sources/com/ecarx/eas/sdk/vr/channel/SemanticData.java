package com.ecarx.eas.sdk.vr.channel;

/* JADX INFO: loaded from: classes2.dex */
public class SemanticData {
    private String albumName;
    private String artistName;
    private String channelName;
    private String detailsIntent;
    private String errorCode;
    private String errorMsg;
    private String mediaCtrl;
    private String mediaSource;
    private String mediaType;
    private String mobileMusicName;
    private int modeType = -1;
    private String originInfo;
    private String recogSlotTag;
    private int semantic;
    private String subTypeName;
    private long timeSlot;
    private String titleName;
    private String tunerFrequency;
    private String typeName;
    private String videoName;

    public String getMobileMusicName() {
        return this.mobileMusicName;
    }

    public void setMobileMusicName(String str) {
        this.mobileMusicName = str;
    }

    public String getRecogSlotTag() {
        return this.recogSlotTag;
    }

    public void setRecogSlotTag(String str) {
        this.recogSlotTag = str;
    }

    public int getSemantic() {
        return this.semantic;
    }

    public void setSemantic(int i) {
        this.semantic = i;
    }

    public String getMediaSource() {
        return this.mediaSource;
    }

    public void setMediaSource(String str) {
        this.mediaSource = str;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(String str) {
        this.mediaType = str;
    }

    public String getTitleName() {
        return this.titleName;
    }

    public void setTitleName(String str) {
        this.titleName = str;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public String getMediaCtrl() {
        return this.mediaCtrl;
    }

    public void setMediaCtrl(String str) {
        this.mediaCtrl = str;
    }

    public String getTunerFrequency() {
        return this.tunerFrequency;
    }

    public void setTunerFrequency(String str) {
        this.tunerFrequency = str;
    }

    public String getSubTypeName() {
        return this.subTypeName;
    }

    public void setSubTypeName(String str) {
        this.subTypeName = str;
    }

    public int getModeType() {
        return this.modeType;
    }

    public void setModeType(int i) {
        this.modeType = i;
    }

    public String getOriginInfo() {
        return this.originInfo;
    }

    public void setOriginInfo(String str) {
        this.originInfo = str;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }

    public String getVideoName() {
        return this.videoName;
    }

    public void setVideoName(String str) {
        this.videoName = str;
    }

    public long getTimeSlot() {
        return this.timeSlot;
    }

    public void setTimeSlot(long j) {
        this.timeSlot = j;
    }

    public String getDetailsIntent() {
        return this.detailsIntent;
    }

    public void setDetailsIntent(String str) {
        this.detailsIntent = str;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String str) {
        this.channelName = str;
    }

    public String toString() {
        return "SemanticData{semantic=" + this.semantic + ", mediaSource='" + this.mediaSource + "', mediaType='" + this.mediaType + "', titleName='" + this.titleName + "', artistName='" + this.artistName + "', albumName='" + this.albumName + "', typeName='" + this.typeName + "', mediaCtrl='" + this.mediaCtrl + "', tunerFrequency='" + this.tunerFrequency + "', subTypeName='" + this.subTypeName + "', modeType=" + this.modeType + ", originInfo='" + this.originInfo + "', errorCode='" + this.errorCode + "', errorMsg='" + this.errorMsg + "', detailsIntent='" + this.detailsIntent + "', recogSlotTag='" + this.recogSlotTag + "', mobileMusicName='" + this.mobileMusicName + "', videoName='" + this.videoName + "', timeSlot=" + this.timeSlot + ", channelName='" + this.channelName + "'}";
    }
}
