package com.wanos.media.entity;

/* JADX INFO: loaded from: classes3.dex */
public class AudioInfoEntity {
    private String audioFilePath;
    private String audioIconBackgroundColor;
    private String audioIconUrl;
    private String audioIconUrlDefault;
    private String audioName;
    private String audioUrl;
    private long id;
    private boolean isVocal = false;
    private int playId;
    private float volume;
    private float x;
    private float y;
    private float z;

    public int getPlayId() {
        return this.playId;
    }

    public void setPlayId(int i) {
        this.playId = i;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float f) {
        this.z = f;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setVolume(float f) {
        this.volume = f;
    }

    public String getAudioFilePath() {
        return this.audioFilePath;
    }

    public void setAudioFilePath(String str) {
        this.audioFilePath = str;
    }

    public String getAudioUrl() {
        return this.audioUrl;
    }

    public void setAudioUrl(String str) {
        this.audioUrl = str;
    }

    public String getAudioName() {
        return this.audioName;
    }

    public void setAudioName(String str) {
        this.audioName = str;
    }

    public String getAudioIconUrlDefault() {
        return this.audioIconUrlDefault;
    }

    public void setAudioIconUrlDefault(String str) {
        this.audioIconUrlDefault = str;
    }

    public String getAudioIconUrl() {
        return this.audioIconUrl;
    }

    public void setAudioIconUrl(String str) {
        this.audioIconUrl = str;
    }

    public String getAudioIconBackgroundColor() {
        return this.audioIconBackgroundColor;
    }

    public boolean isVocal() {
        return this.isVocal;
    }

    public void setVocal(boolean z) {
        this.isVocal = z;
    }

    public void setAudioIconBackgroundColor(String str) {
        this.audioIconBackgroundColor = str;
    }

    public boolean equals(com.wanos.WanosCommunication.bean.AudioInfoEntity audioInfoEntity, boolean z) {
        if (audioInfoEntity != null && getId() == audioInfoEntity.getId()) {
            return !z ? getVolume() == ((float) audioInfoEntity.getVolume()) : getX() == audioInfoEntity.getX() && getY() == audioInfoEntity.getY() && getZ() == audioInfoEntity.getZ() && getVolume() == ((float) audioInfoEntity.getVolume());
        }
        return false;
    }

    public String toString() {
        return "AudioInfoEntity{id=" + this.id + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", volume=" + this.volume + ", audioFilePath='" + this.audioFilePath + "', audioUrl='" + this.audioUrl + "', audioName='" + this.audioName + "', audioIconUrl='" + this.audioIconUrl + "', audioIconBackgroundColor='" + this.audioIconBackgroundColor + "', playId=" + this.playId + '}';
    }
}
