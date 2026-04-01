package com.ecarx.eas.sdk.vehicle.api.newenergy;

/* JADX INFO: loaded from: classes2.dex */
public interface IEptState {
    public static final int AVAS_VOLUME_HIGH = 3;
    public static final int AVAS_VOLUME_LOW = 1;
    public static final int AVAS_VOLUME_MIDDLE = 2;
    public static final int EPT_ACTUAL_MODE_DRIVE_CHARGING = 7;
    public static final int EPT_ACTUAL_MODE_IDLE_CHARGING = 5;
    public static final int EPT_ACTUAL_MODE_NO_DISPLAY = 1;
    public static final int EPT_ACTUAL_MODE_PARALLEL_DRIVE = 4;
    public static final int EPT_ACTUAL_MODE_PURE_ELECTRIC_DRIVE = 2;
    public static final int EPT_ACTUAL_MODE_PURE_ENGINE_DRIVE = 3;
    public static final int EPT_ACTUAL_MODE_REGEN_DRIVE = 8;
    public static final int EPT_ACTUAL_MODE_SMART_CHARGING = 6;

    int getAvasVolumeType();

    int getEptActualMode();

    int getSOCPointLevel();

    boolean isAvasDisable();

    boolean isInfrequentChargingModeOn();
}
