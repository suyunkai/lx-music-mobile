package com.ecarx.openapi.sdk.carproperty.info;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarInfo {
    public static final int CONFIG_INFO_CAR_CONFIG_FIRST = 8392705;
    public static final int DRIVE_MODE_AWD = 1049603;
    public static final int DRIVE_MODE_FRONT = 1049601;
    public static final int DRIVE_MODE_REAR = 1049602;
    public static final int DRIVE_MODE_UNKNOWN = 1049855;
    public static final int FLT_INFO_MAX_LIMITED_SPEED = 2098176;
    public static final int FLT_INFO_VEHICLE_WEIGHT = 2097920;
    public static final int INT_INFO_DRIVE_MODE = 1049600;
    public static final int MAP_INFO_EV_SLOPE_DOWN_ENERGY_COEFFICIENT = 9437696;
    public static final int MAP_INFO_EV_SLOPE_RISE_ENERGY_COEFFICIENT = 9437440;
    public static final int MAP_INFO_EV_SPEED_DOWN_ENERGY_COEFFICIENT = 9438208;
    public static final int MAP_INFO_EV_SPEED_RELATE_WEIGHT = 9438464;
    public static final int MAP_INFO_EV_SPEED_RISE_ENERGY_COEFFICIENT = 9437952;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DriveModes {
    }
}
