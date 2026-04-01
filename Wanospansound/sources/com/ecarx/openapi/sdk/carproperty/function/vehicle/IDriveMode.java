package com.ecarx.openapi.sdk.carproperty.function.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IDriveMode {
    public static final int DM_FUNC_DRIVE_MODE_SELECT = 570491136;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DriveModeSelection {
        public static final int DRIVE_MODE_ECO_PLUS = 570491156;
        public static final int DRIVE_MODE_SELECTION_AWD = 570491150;
        public static final int DRIVE_MODE_SELECTION_COMFORT = 570491138;
        public static final int DRIVE_MODE_SELECTION_CUSTOM = 570491200;
        public static final int DRIVE_MODE_SELECTION_DYNAMIC = 570491139;
        public static final int DRIVE_MODE_SELECTION_ECO = 570491137;
        public static final int DRIVE_MODE_SELECTION_ECO_HEV_PHEV = 570491152;
        public static final int DRIVE_MODE_SELECTION_HDC = 570491141;
        public static final int DRIVE_MODE_SELECTION_HYBRID = 570491143;
        public static final int DRIVE_MODE_SELECTION_MUD = 570491146;
        public static final int DRIVE_MODE_SELECTION_NORMAL = 570491153;
        public static final int DRIVE_MODE_SELECTION_OFFROAD = 570491155;
        public static final int DRIVE_MODE_SELECTION_PHEV = 570491148;
        public static final int DRIVE_MODE_SELECTION_POWER = 570491144;
        public static final int DRIVE_MODE_SELECTION_PURE = 570491142;
        public static final int DRIVE_MODE_SELECTION_ROCK = 570491147;
        public static final int DRIVE_MODE_SELECTION_SAND = 570491149;
        public static final int DRIVE_MODE_SELECTION_SAVE = 570491151;
        public static final int DRIVE_MODE_SELECTION_SNOW = 570491145;
        public static final int DRIVE_MODE_SELECTION_UNKNOWN = 255;
        public static final int DRIVE_MODE_SELECTION_XC = 570491140;
        public static final int DRIVE_MODE_SELECTION_eAWD = 570491154;
        public static final int DRIVE_MODE_SPORT_PLUS = 570491157;
    }
}
