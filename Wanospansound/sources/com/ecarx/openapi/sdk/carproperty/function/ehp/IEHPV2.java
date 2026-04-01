package com.ecarx.openapi.sdk.carproperty.function.ehp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IEHPV2 {
    public static final int EHP_V2_HZN_DATA_MESSAGE = 560988500;
    public static final int EHP_V2_HZN_EDGE_MESSAGE = 560988497;
    public static final int EHP_V2_HZN_POSITION_LR_MESSAGE = 560988501;
    public static final int EHP_V2_HZN_POSITION_MESSAGE = 560988495;
    public static final int EHP_V2_HZN_PROFLONG_LR_MESSAGE = 560988502;
    public static final int EHP_V2_HZN_PROFLONG_MESSAGE = 560988499;
    public static final int EHP_V2_HZN_PROFSHORT_MESSAGE = 560988498;
    public static final int EHP_V2_HZN_SEGMENT_MESSAGE = 560988496;
    public static final int EHP_V2_REQUEST_STATUS = 557842775;
    public static final int EHP_V2_STATUS = 557842766;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EHPV2RequestStatus {
        public static final int REQUEST_LONG_MSG_START = 1;
        public static final int REQUEST_LONG_MSG_STOP = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EHPV2Status {
        public static final int EHP_STATUS_NOT_RUNNING = 2;
        public static final int EHP_STATUS_NOT_SUPPORT = 1;
        public static final int EHP_STATUS_RUNNING = 3;
        public static final int EHP_STATUS_UNKNOWN = 0;
    }
}
