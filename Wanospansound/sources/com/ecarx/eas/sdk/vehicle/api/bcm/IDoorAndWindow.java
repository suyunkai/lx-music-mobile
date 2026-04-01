package com.ecarx.eas.sdk.vehicle.api.bcm;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IDoorAndWindow {
    public static final int FRONT_LEFT_DOOR = 1;
    public static final int FRONT_LEFT_WINDOW = 1;
    public static final int FRONT_RIGHT_DOOR = 2;
    public static final int FRONT_RIGHT_WINDOW = 2;
    public static final int REAR_LEFT_DOOR = 3;
    public static final int REAR_LEFT_WINDOW = 3;
    public static final int REAR_RIGHT_DOOR = 4;
    public static final int REAR_RIGHT_WINDOW = 4;

    public interface DoorStatusListener {
        void onDoorLockStatus(int i, boolean z);

        void onDoorOpenStatus(int i, boolean z);
    }

    @Documented
    @Retention(RetentionPolicy.CLASS)
    public @interface DoorType {
    }

    public interface WindowStatusListener {
        void onWindowOpenStatus(int i, boolean z);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface WindowType {
    }

    boolean isDoorLocked(int i);

    boolean isDoorOpened(int i);

    boolean isWindowOpened(int i);

    void setDoorStatusListener(DoorStatusListener doorStatusListener, int[] iArr);

    void setWindowStatusListener(WindowStatusListener windowStatusListener, int[] iArr);
}
