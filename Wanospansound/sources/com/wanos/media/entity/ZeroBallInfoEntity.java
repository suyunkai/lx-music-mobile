package com.wanos.media.entity;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroBallInfoEntity {
    private final String audioIcon;
    private final String audioIcon_default;
    private final String audioName;
    private float audioVolume;
    private final int ballId;
    private final boolean isSupportDelete;

    public ZeroBallInfoEntity(AudioInfoEntity audioInfoEntity) {
        if (audioInfoEntity == null) {
            throw new NullPointerException("添加音源错误：AudioInfoEntity == NULL");
        }
        this.ballId = audioInfoEntity.getPlayId();
        this.audioIcon = audioInfoEntity.getAudioIconUrl();
        this.audioIcon_default = audioInfoEntity.getAudioIconUrlDefault();
        this.audioName = audioInfoEntity.getAudioName();
        this.audioVolume = audioInfoEntity.getVolume();
        this.isSupportDelete = !audioInfoEntity.isVocal();
    }

    public int getBallId() {
        return this.ballId;
    }

    public String getAudioName() {
        return this.audioName;
    }

    public String getAudioIcon() {
        return this.audioIcon;
    }

    public String getAudioIcon_default() {
        return this.audioIcon_default;
    }

    public float getAudioVolume() {
        return this.audioVolume;
    }

    public boolean isSupportDelete() {
        return this.isSupportDelete;
    }

    public VolumeModifyEntity getVolumeModifyEntity() {
        return new VolumeModifyEntity(getBallId(), getAudioName(), getAudioIcon_default(), getAudioVolume());
    }

    public void setAudioVolume(float f) {
        this.audioVolume = f;
    }

    public String toString() {
        return "ZeroBallInfoEntity{ballId=" + this.ballId + ", audioName='" + this.audioName + "', audioIcon='" + this.audioIcon + "', audioIcon_default='" + this.audioIcon_default + "', audioVolume=" + this.audioVolume + ", isSupportDelete=" + this.isSupportDelete + '}';
    }
}
