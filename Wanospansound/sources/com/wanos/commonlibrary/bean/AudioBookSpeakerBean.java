package com.wanos.commonlibrary.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookSpeakerBean implements Serializable {
    private String avatar;
    private int id;
    private String name;

    public AudioBookSpeakerBean(int i, String str, String str2) {
        this.id = i;
        this.name = str;
        this.avatar = str2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }
}
