package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class ServiceProtocolBean {
    private String content;
    private boolean isUpdate;
    private int version;

    public boolean isUpdate() {
        return this.isUpdate;
    }

    public void setUpdate(boolean z) {
        this.isUpdate = z;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
