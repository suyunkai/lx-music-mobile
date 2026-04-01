package com.ecarx.openapi.sdk.carproperty.function.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IAudio {
    public static final int SETTING_FUNC_AUDIO_FOCUS_CAN_BE_OCCUPIED = 772082944;
    public static final int SETTING_FUNC_AUDIO_KTV_MODE = 772082432;
    public static final int SETTING_FUNC_AUDIO_NAVI_MIX_MODE = 772014336;
    public static final int SETTING_FUNC_AUDIO_WARNING_RING_STATE = 772083200;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioFocusHolderStatus {
        public static final int BCALL_HOLD = 2;
        public static final int ECALL_HOLD = 1;
        public static final int ICALL_HOLD = 3;
        public static final int ICC_HOLD = 5;
        public static final int OTHER = 7;
        public static final int TELEPHONE = 6;
        public static final int VR_HOLD = 4;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NaviVoiceMixMode {
        public static final int NAVI_VOICE_MIX_AUTO = 772014337;
        public static final int NAVI_VOICE_MIX_DIRECTLY = 772014338;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WarningRingState {
        public static final int WARNING_OFF = 0;
        public static final int WARNING_ON_A = 772083201;
        public static final int WARNING_ON_B = 772083202;
        public static final int WARNING_ON_C = 772083203;
    }
}
