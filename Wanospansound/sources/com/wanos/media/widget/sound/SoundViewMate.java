package com.wanos.media.widget.sound;

import com.wanos.media.entity.VolumeModifyEntity;

/* JADX INFO: loaded from: classes3.dex */
class SoundViewMate {
    private String audioName;
    private int autoAngle;
    private boolean autoMove;
    private float autoSpeed;
    private boolean editState;
    private String iconBg;
    private String iconImageUrl;
    private int id;
    private long soundId;
    private boolean userTouch = false;
    private float volume;
    private float x;
    private float y;
    private float z;

    SoundViewMate() {
    }

    public boolean isUserTouch() {
        return this.userTouch;
    }

    public void setUserTouch(boolean z) {
        if (this.userTouch != z) {
            this.userTouch = z;
        }
    }

    public String getAudioName() {
        return this.audioName;
    }

    public void setAudioName(String str) {
        this.audioName = str;
    }

    public long getSoundId() {
        return this.soundId;
    }

    public void setSoundId(long j) {
        this.soundId = j;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
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

    public int getAutoAngle() {
        return this.autoAngle;
    }

    public void setAutoAngle(int i) {
        this.autoAngle = i;
    }

    public float getAutoSpeed() {
        return this.autoSpeed;
    }

    public void setAutoSpeed(float f) {
        this.autoSpeed = f;
    }

    public boolean isEditState() {
        return this.editState;
    }

    public void setEditState(boolean z) {
        this.editState = z;
    }

    public boolean isAutoMove() {
        return this.autoMove;
    }

    public void setAutoMove(boolean z) {
        this.autoMove = z;
    }

    public String getIconImageUrl() {
        return this.iconImageUrl;
    }

    public void setIconImageUrl(String str) {
        this.iconImageUrl = str;
    }

    public String getIconBg() {
        return this.iconBg;
    }

    public void setIconBg(String str) {
        this.iconBg = str;
    }

    public VolumeModifyEntity getVolumeModifyEntity() {
        return new VolumeModifyEntity(getId(), getAudioName(), getIconImageUrl(), getVolume());
    }

    public String toString() {
        return "SoundViewMate{soundId=" + this.soundId + ", id=" + this.id + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", volume=" + this.volume + ", autoAngle=" + this.autoAngle + ", autoSpeed=" + this.autoSpeed + ", editState=" + this.editState + ", autoMove=" + this.autoMove + ", iconImageUrl='" + this.iconImageUrl + "', iconBg='" + this.iconBg + "'}";
    }
}
