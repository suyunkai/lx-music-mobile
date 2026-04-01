package com.ecarx.openapi.sdk.carproperty.function.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IDayMode {
    public static final int SETTING_FUNC_DAYMODE_SETTING = 538247424;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DayModeState {
        public static final int DAYMODE_SETTING_AUTO = 538247427;
        public static final int DAYMODE_SETTING_DAY = 538247425;
        public static final int DAYMODE_SETTING_NIGHT = 538247426;
        public static final int DAYMODE_SETTING_OFF = 0;
    }
}
