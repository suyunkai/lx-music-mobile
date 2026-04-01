package com.ecarx.eas.sdk.vehicle.api.carinfo;

import android.view.Display;
import com.ecarx.eas.sdk.vehicle.api.carinfo.audio.CarAudioManager;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarInfo {
    public static final int DISPLAY_TYPE_DIM = -2147483647;
    public static final int DISPLAY_TYPE_HUD = -2147483646;
    public static final int FLT_INFO_EV_BATTERY_CAPACITY = 2097664;
    public static final int FLT_INFO_FUEL_CAPACITY = 2097408;
    public static final int FUEL_TYPE_BIODIESEL = 1048837;
    public static final int FUEL_TYPE_CNG = 1048840;
    public static final int FUEL_TYPE_DIESEL_1 = 1048835;
    public static final int FUEL_TYPE_DIESEL_2 = 1048836;
    public static final int FUEL_TYPE_E85 = 1048838;
    public static final int FUEL_TYPE_ELECTRIC = 1048842;
    public static final int FUEL_TYPE_HYDROGEN = 1048843;
    public static final int FUEL_TYPE_LEADED = 1048834;
    public static final int FUEL_TYPE_LNG = 1048841;
    public static final int FUEL_TYPE_LPG = 1048839;
    public static final int FUEL_TYPE_UNKNOWN = 1049087;
    public static final int FUEL_TYPE_UNLEADED = 1048833;
    public static final int INFO_TYPE_FLT = 2097152;
    public static final int INFO_TYPE_INT = 1048576;
    public static final int INFO_TYPE_STR = 3145728;
    public static final int INT_INFO_FUEL_TYPES = 1048832;
    public static final int INT_INFO_VEHICLE_TYPES = 1049088;
    public static final int VEHICLE_TYPE_BEV = 1049096;
    public static final int VEHICLE_TYPE_EREV = 1049092;
    public static final int VEHICLE_TYPE_FCEV = 1049093;
    public static final int VEHICLE_TYPE_FCV = 1049094;
    public static final int VEHICLE_TYPE_FUEL = 1049089;
    public static final int VEHICLE_TYPE_HEV = 1049090;
    public static final int VEHICLE_TYPE_MHEV = 1049095;
    public static final int VEHICLE_TYPE_PHEV = 1049091;
    public static final int VEHICLE_TYPE_UNKNOWN = 1049343;
    public static final int ZONE_ALL = Integer.MIN_VALUE;
    public static final int ZONE_ROOF_TOP = 268435456;
    public static final int ZONE_ROW_1_ALL = 8;
    public static final int ZONE_ROW_1_CENTER = 2;
    public static final int ZONE_ROW_1_LEFT = 1;
    public static final int ZONE_ROW_1_RIGHT = 4;
    public static final int ZONE_ROW_2_ALL = 128;
    public static final int ZONE_ROW_2_CENTER = 32;
    public static final int ZONE_ROW_2_LEFT = 16;
    public static final int ZONE_ROW_2_RIGHT = 64;
    public static final int ZONE_ROW_3_ALL = 2048;
    public static final int ZONE_ROW_3_CENTER = 512;
    public static final int ZONE_ROW_3_LEFT = 256;
    public static final int ZONE_ROW_3_RIGHT = 1024;
    public static final int ZONE_ROW_4_ALL = 32768;
    public static final int ZONE_ROW_4_CENTER = 8192;
    public static final int ZONE_ROW_4_LEFT = 4096;
    public static final int ZONE_ROW_4_RIGHT = 16384;

    CarAudioManager getCarAudioManager();

    int getCarConfig(int i);

    int getCarInfoByArray(int i, int i2);

    float getCarInfoFloat(int i);

    int getCarInfoInt(int i);

    String getCarInfoString(int i);

    Display getDisplay(int i);
}
