package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class SoundInsertEntity {
    private int code;
    private String msg;
    private int soundId;

    public SoundInsertEntity(int i, String str, int i2) {
        this.code = i;
        this.msg = str;
        this.soundId = i2;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getSoundId() {
        return this.soundId;
    }
}
