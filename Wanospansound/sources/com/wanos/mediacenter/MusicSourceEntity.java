package com.wanos.mediacenter;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes3.dex */
public class MusicSourceEntity {
    private int cpId;
    private Bitmap icon;
    private String imageUrl;
    private String name;
    private String packageName;
    private String sourceType;

    public MusicSourceEntity(String str, String str2, String str3, Bitmap bitmap, int i, String str4) {
        this.name = str;
        this.packageName = str2;
        this.imageUrl = str3;
        this.icon = bitmap;
        this.cpId = i;
        this.sourceType = str4;
    }

    public MusicSourceEntity(String str, String str2, String str3, String str4) {
        this.cpId = -2;
        this.name = str;
        this.packageName = str2;
        this.imageUrl = str3;
        this.sourceType = str4;
    }

    public MusicSourceEntity(String str, String str2, String str3, int i, String str4) {
        this.name = str;
        this.packageName = str2;
        this.imageUrl = str3;
        this.cpId = i;
        this.sourceType = str4;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public Bitmap getIcon() {
        return this.icon;
    }

    public void setIcon(Bitmap bitmap) {
        this.icon = bitmap;
    }

    public int getCpId() {
        return this.cpId;
    }

    public void setCpId(int i) {
        this.cpId = i;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String str) {
        this.sourceType = str;
    }
}
