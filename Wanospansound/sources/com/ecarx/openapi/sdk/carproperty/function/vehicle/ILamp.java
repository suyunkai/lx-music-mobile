package com.ecarx.openapi.sdk.carproperty.function.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ILamp {
    public static final int SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL = 537136640;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ExteriorLightMode {
        public static final int LAMP_EXTERIOR_LIGHT_CONTROL_AHBC = 537136644;
        public static final int LAMP_EXTERIOR_LIGHT_CONTROL_AUTOMATIC = 537136643;
        public static final int LAMP_EXTERIOR_LIGHT_CONTROL_LOWBEAM = 537136642;
        public static final int LAMP_EXTERIOR_LIGHT_CONTROL_OFF = 0;
        public static final int LAMP_EXTERIOR_LIGHT_CONTROL_POS_LIGHT = 537136641;
    }
}
