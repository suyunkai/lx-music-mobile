package com.ecarx.eas.sdk.vehicle.api.bcm;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ILighting {
    public static final int DEVICE_CORNERING_LAMPS = 6;
    public static final int DEVICE_DAYTIME_RUNNING_LAMPS = 9;
    public static final int DEVICE_DIM_DIP_LAMPS = 10;
    public static final int DEVICE_DIPPED_BEAM = 1;
    public static final int DEVICE_DRIVING_LAMPS = 3;
    public static final int DEVICE_FRONT_FOG_LAMPS = 4;
    public static final int DEVICE_FRONT_POSITION_LAMPS = 8;
    public static final int DEVICE_HAZARD_FLASHERS = 15;
    public static final int DEVICE_MAIN_BEAM = 2;
    public static final int DEVICE_REAR_FOG_LAMPS = 5;
    public static final int DEVICE_REAR_POSITION_LAMPS = 12;
    public static final int DEVICE_REVERSING_LAMPS = 14;
    public static final int DEVICE_SIDE_MARKER_LIGHTS = 11;
    public static final int DEVICE_SPOT_LIGHTS = 7;
    public static final int DEVICE_STOP_LAMPS = 13;

    @Documented
    @Retention(RetentionPolicy.CLASS)
    public @interface LightingAndSignallingDevice {
    }

    public interface LightingStatusListener {
        void onLightingAndSignallingDeviceStatus(int i, boolean z);
    }

    boolean isLightingOrSignallingDeviceOn(int i);

    void setLightingAndSignallingDeviceStatusListener(LightingStatusListener lightingStatusListener, int[] iArr);
}
