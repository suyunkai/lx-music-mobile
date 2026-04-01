package com.ecarx.eas.sdk.vehicle.api.bcm;

/* JADX INFO: loaded from: classes2.dex */
public interface IBcm {
    public static final int WIPER_GEAR_HIGH = 3;
    public static final int WIPER_GEAR_INTERMITTENT = 4;
    public static final int WIPER_GEAR_LOW = 2;
    public static final int WIPER_GEAR_OFF = 0;
    public static final int WIPER_GEAR_ON = 1;
    public static final int WIPER_STATUS_HIGH_SPEED = 3;
    public static final int WIPER_STATUS_LOW_SPEED = 2;
    public static final int WIPER_STATUS_NORMAL_SPEED = 1;
    public static final int WIPER_STATUS_STATIONARY = 0;
    public static final int WIPER_TYPE_REAR_WIPER = 1;
    public static final int WIPER_TYPE_WINDSCREEN_WIPER = 0;

    IDoorAndWindow getDoorAndWindowState();

    ILighting getLightingState();

    int getWiperGear(int i);

    int getWiperStatus(int i);
}
