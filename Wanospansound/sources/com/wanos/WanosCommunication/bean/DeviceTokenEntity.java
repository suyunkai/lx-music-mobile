package com.wanos.WanosCommunication.bean;

import com.wanos.commonlibrary.utils.SystemAndServerTimeSynchUtil;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceTokenEntity {
    private String deviceToken;
    private long expireTime;

    public String getDeviceToken() {
        return this.deviceToken;
    }

    public boolean isExpire() {
        return SystemAndServerTimeSynchUtil.getSytemTrueTime() / 1000 > this.expireTime - 60;
    }

    public long getExpireTime() {
        return this.expireTime;
    }
}
