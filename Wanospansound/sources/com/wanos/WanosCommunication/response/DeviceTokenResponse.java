package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.bean.DeviceTokenEntity;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceTokenResponse {
    private int code;
    private DeviceTokenEntity data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public DeviceTokenEntity getData() {
        return this.data;
    }
}
