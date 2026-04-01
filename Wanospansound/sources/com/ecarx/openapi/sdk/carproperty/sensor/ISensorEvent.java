package com.ecarx.openapi.sdk.carproperty.sensor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ISensorEvent {
    public static final int SENSOR_EVENT_UNKNOWN = -1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface JoyLimitState {
        public static final int JOY_LIMIT_STATE_OFF = 4195841;
        public static final int JOY_LIMIT_STATE_ON = 4195842;
        public static final int JOY_LIMIT_STATE_UNKNOWN = -1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WarningState {
        public static final int WARNING_LEVEL_1 = 3145730;
        public static final int WARNING_LEVEL_2 = 3145731;
        public static final int WARNING_OFF = 3145728;
        public static final int WARNING_ON = 3145729;
    }
}
