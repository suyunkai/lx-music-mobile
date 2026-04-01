package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class AudioSourceBaseBean {
    private String AudioType;
    private String Avatar;
    private String ID;
    private String Name;
    private boolean NeedVip;
    private String Path;
    private String Size;
    private String TagID;

    public String getID() {
        return this.ID;
    }

    public void setID(String str) {
        this.ID = str;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public String getAudioType() {
        return this.AudioType;
    }

    public void setAudioType(String str) {
        this.AudioType = str;
    }

    public String getPath() {
        return this.Path;
    }

    public void setPath(String str) {
        this.Path = str;
    }

    public String getSize() {
        return this.Size;
    }

    public void setSize(String str) {
        this.Size = str;
    }

    public boolean isNeedVip() {
        return this.NeedVip;
    }

    public void setNeedVip(boolean z) {
        this.NeedVip = z;
    }

    public String getTagID() {
        return this.TagID;
    }

    public void setTagID(String str) {
        this.TagID = str;
    }

    public String getAvatar() {
        return this.Avatar;
    }

    public void setAvatar(String str) {
        this.Avatar = str;
    }
}
