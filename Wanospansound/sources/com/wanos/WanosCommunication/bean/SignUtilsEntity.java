package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class SignUtilsEntity {
    private static SignUtilsEntity INSTANCE = new SignUtilsEntity();
    public static final String KEY_DEVICE_TOKEN = "_device_token_v2";
    public static final String SP_NAME = "_SignInfo";
    private DeviceTokenEntity deviceTokenEntity;

    public static SignUtilsEntity getInstance() {
        return INSTANCE;
    }

    public synchronized void setDeviceTokenEntity(DeviceTokenEntity deviceTokenEntity) {
        this.deviceTokenEntity = deviceTokenEntity;
    }

    public synchronized DeviceTokenEntity getDeviceTokenEntity() {
        return this.deviceTokenEntity;
    }
}
