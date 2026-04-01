package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class AudioInfoEntity {
    private String audioFilePath;
    private String audioIconBackgroundColor;
    private String audioIconUrl;
    private String audioName;
    private String audioUrl;
    private long id;
    private int playId;
    private int volume;
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

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int i) {
        this.volume = i;
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

    public String getAudioIconUrl() {
        return this.audioIconUrl;
    }

    public void setAudioIconUrl(String str) {
        this.audioIconUrl = str;
    }

    public String getAudioIconBackgroundColor() {
        return this.audioIconBackgroundColor;
    }

    public void setAudioIconBackgroundColor(String str) {
        this.audioIconBackgroundColor = str;
    }

    public AudioInfoEntity copy() {
        AudioInfoEntity audioInfoEntity = new AudioInfoEntity();
        audioInfoEntity.setX(getX());
        audioInfoEntity.setY(getY());
        audioInfoEntity.setZ(getZ());
        audioInfoEntity.setVolume(getVolume());
        audioInfoEntity.setAudioName(getAudioName());
        audioInfoEntity.setAudioUrl(getAudioUrl());
        audioInfoEntity.setAudioFilePath(getAudioFilePath());
        audioInfoEntity.setAudioIconUrl(getAudioIconUrl());
        audioInfoEntity.setAudioIconBackgroundColor(getAudioIconBackgroundColor());
        audioInfoEntity.setId(getId());
        return audioInfoEntity;
    }

    public boolean equals(AudioInfoEntity audioInfoEntity, boolean z) {
        if (audioInfoEntity != null && getId() == audioInfoEntity.getId()) {
            return z ? getX() == audioInfoEntity.getX() && getY() == audioInfoEntity.getY() && getZ() == audioInfoEntity.getZ() && getVolume() == audioInfoEntity.getVolume() : getVolume() == audioInfoEntity.getVolume();
        }
        return false;
    }

    public String toString() {
        return "AudioInfoEntity{id=" + this.id + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", volume=" + this.volume + ", audioFilePath='" + this.audioFilePath + "', audioUrl='" + this.audioUrl + "', audioName='" + this.audioName + "', audioIconUrl='" + this.audioIconUrl + "', audioIconBackgroundColor='" + this.audioIconBackgroundColor + "', playId=" + this.playId + '}';
    }
}
