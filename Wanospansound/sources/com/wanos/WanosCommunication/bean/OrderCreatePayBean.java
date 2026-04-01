package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class OrderCreatePayBean {
    private long expireTime;
    private String payUrl;

    public String getPayUrl() {
        return this.payUrl;
    }

    public void setPayUrl(String str) {
        this.payUrl = str;
    }

    public long getExpireTime() {
        return this.expireTime / 1000;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }
}
