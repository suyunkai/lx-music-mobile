package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class SoundCollectionEntity {
    private String imagePath;
    private boolean isSelect;

    public SoundCollectionEntity(boolean z, String str) {
        this.isSelect = z;
        this.imagePath = str;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }
}
