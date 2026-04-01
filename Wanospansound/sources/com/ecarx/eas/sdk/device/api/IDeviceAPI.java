package com.ecarx.eas.sdk.device.api;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceAPI {
    public static final String OPERATOR_NAME_UNKNOWN = "";
    public static final int OPERATOR_UNKNOWN = 255;

    IDayNightMode getDayNightMode();

    IDeviceState getDeviceState();

    String getOpenIHUID();

    String getOpenVIN();

    int getOperatorCode();

    String getOperatorName();

    String getSupplierCode();

    @Deprecated
    String getVehicleType();

    String getVehicleTypeConfig();
}
