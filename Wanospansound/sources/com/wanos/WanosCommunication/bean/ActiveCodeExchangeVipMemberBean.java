package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class ActiveCodeExchangeVipMemberBean {
    long endTime;
    boolean exchangeResult;
    long startTime;
    long vipDuration;

    public boolean isExchangeResult() {
        return this.exchangeResult;
    }

    public void setExchangeResult(boolean z) {
        this.exchangeResult = z;
    }

    public long getVipDuration() {
        return this.vipDuration;
    }

    public void setVipDuration(long j) {
        this.vipDuration = j;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }
}
