package com.ecarx.openapi.sdk.carproperty.function.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IADAS {
    public static final int SETTING_FUNC_CAR_HORIZON_CONTROL = 671549184;
    public static final int SETTING_FUNC_CAR_VERTICAL_CONTROL = 671549440;
    public static final int SETTING_FUNC_COLLISION_WARN_STATUS = 671550720;
    public static final int SETTING_FUNC_DRIVE_NZP_STATUS = 671549696;
    public static final int SETTING_FUNC_DRIVE_PILOT_ACC_LCC_SWITCH = 671550208;
    public static final int SETTING_FUNC_DRIVE_PILOT_ALARM_INFO = 671549952;
    public static final int SETTING_FUNC_DRIVE_PILOT_STATUS = 671548672;
    public static final int SETTING_FUNC_LANE_CHANGE_STATUS = 671550464;
    public static final int SETTING_FUNC_MAX_CRUISING_SPEED = 671548928;
    public static final int SETTING_FUNC_NZP_PAY_ACTIVE = 671748352;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarSpeedDisplayStatus {
        public static final int CAR_SPEED_HORIZON_DISPLAY_STATUS_ACTIVE = 4;
        public static final int CAR_SPEED_HORIZON_DISPLAY_STATUS_FAILURE_DISPLAY = 6;
        public static final int CAR_SPEED_HORIZON_DISPLAY_STATUS_NO_DISPLAY = 1;
        public static final int CAR_SPEED_HORIZON_DISPLAY_STATUS_OFF_DISPLAY = 2;
        public static final int CAR_SPEED_HORIZON_DISPLAY_STATUS_OVERRIDE = 5;
        public static final int CAR_SPEED_HORIZON_DISPLAY_STATUS_STANDBY = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NZPChangeLaneStatus {
        public static final int LANE_LEFT_CANCEL = 9;
        public static final int LANE_LEFT_CHANGING = 7;
        public static final int LANE_LEFT_FAILED = 3;
        public static final int LANE_LEFT_INVALID = 5;
        public static final int LANE_LEFT_SUCCESS = 1;
        public static final int LANE_NORMAL = 0;
        public static final int LANE_RIGHT_CANCEL = 16;
        public static final int LANE_RIGHT_CHANGING = 8;
        public static final int LANE_RIGHT_FAILED = 4;
        public static final int LANE_RIGHT_INVALID = 6;
        public static final int LANE_RIGHT_SUCCESS = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NZPCollisionWarning {
        public static final int NORMAL = 0;
        public static final int RED = 2;
        public static final int YELLOW = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NZPStatus {
        public static final int NZP_STATUS_STATUS_ENTER = 1;
        public static final int NZP_STATUS_STATUS_EXIT = 2;
        public static final int NZP_STATUS_STATUS_FAULT = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NZPType {
        public static final int NZP_ACC_LCC = 3;
        public static final int NZP_SMART_PILOT = 2;
        public static final int NZP_UNKNOWN = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NzpPayActive {
        public static final int PAID = 671748353;
        public static final int UNPAID = 671748352;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PilotAlarmInfoMessage {
        public static final int LEVEL1_ABOUT_TO_CHANGE_LANE_TO_THE_LEFT = 12;
        public static final int LEVEL1_ABOUT_TO_CHANGE_LANE_TO_THE_RIGHT = 11;
        public static final int LEVEL1_AI_ASSISTED_DRIVING_IS_ON = 1;
        public static final int LEVEL1_HAS_BEEN_DOWNGRADED_TO_AI_ASSISTED_DRIVING = 18;
        public static final int LEVEL1_HAS_BEEN_DOWNGRADED_TO_NZP_INTELLIGENT_PILOT = 37;
        public static final int LEVEL1_HAS_BEEN_UPGRADED_TO_AI_ASSISTED_DRIVING = 19;
        public static final int LEVEL1_HAS_BEEN_UPGRADED_TO_NZP_AUTONOMOUS_NAVIGATION = 20;
        public static final int LEVEL1_LANE_CHANGE_COMPLETED = 9;
        public static final int LEVEL1_LANE_CHANGE_CONDITIONS_ARE_NOT_MET = 36;
        public static final int LEVEL1_LANE_CHANGE_HAS_BEEN_CANCELLED = 35;
        public static final int LEVEL1_LANE_CHANGE_IN_PROGRESS = 7;
        public static final int LEVEL1_LANE_CHANGE_IS_CANCELLED = 34;
        public static final int LEVEL1_LANE_CHANGING_IN_PROGRESS_PLEASE_PAY_ATTENTION = 8;
        public static final int LEVEL1_LCC_FUNCTION_ON = 42;
        public static final int LEVEL1_LCC_IS_RUNNING = 33;
        public static final int LEVEL1_NZP_AUTONOMOUS_PILOTING_HAS_BEEN_ACTIVATED = 2;
        public static final int LEVEL1_NZP_AUTONOMOUS_PILOT_IS_IN_OPERATION = 3;
        public static final int LEVEL1_PLEASE_HOLD_THE_STEERING_WHEEL = 15;
        public static final int LEVEL1_PLEASE_HOLD_THE_STEERING_WHEEL_PRESS_THE_CANCEL = 16;
        public static final int LEVEL1_PLEASE_LOOK_AT_THE_ROAD_AHEAD = 17;
        public static final int LEVEL1_PLEASE_OPERATE_THE_STEERING_LEVER_CHANGE_THE_LEFT = 14;
        public static final int LEVEL1_PLEASE_OPERATE_THE_STEERING_LEVER_CHANGE_THE_RIGHT = 13;
        public static final int LEVEL1_THE_FUNCTION_CANNOT_BE_ACTIVATED = 10;
        public static final int LEVEL1_THIS_LANE_CHANGE_FAILED = 6;
        public static final int LEVEL1_ZCA_IS_RUNNING = 4;
        public static final int LEVEL1_ZEEKR_PILOT_EXITED = 5;
        public static final int LEVEL2_ABOUT_TO_LEAVE_THE_WORKABLE_AREA = 22;
        public static final int LEVEL2_DOWNGRADED_TO_MANUAL_DRIVING_PLEASE_HOLD_THE_STEERING_WHEEL = 26;
        public static final int LEVEL2_HAS_BEEN_DOWNGRADED_TO_A_MANUAL_DRIVING_CONDITION = 27;
        public static final int LEVEL2_HAS_BEEN_DOWNGRADED_TO_LCC = 39;
        public static final int LEVEL2_HAS_BEEN_DOWNGRADED_TO_MANUAL_AUXILIARY_DRIVING_CONDITION = 25;
        public static final int LEVEL2_LANE_CHANGED_FAILED_LEFT = 43;
        public static final int LEVEL2_LANE_CHANGED_FAILED_RIGHT = 44;
        public static final int LEVEL2_PILOT_FUNCTION_EXIT_FAILURE_TO_TAKE_OVER = 21;
        public static final int LEVEL2_PLEASE_HOLD_THE_STEERING_WHEEL = 23;
        public static final int LEVEL2_PLEASE_LOOK_AT_THE_ROAD_AHEAD = 24;
        public static final int LEVEL2_ZEEKR_PILOT_FUNCTION_EXIT = 38;
        public static final int LEVEL3_HAS_BEEN_DOWNGRADED_TO_MANUAL_DRIVING_CONDITION = 32;
        public static final int LEVEL3_PLEASE_HOLD_THE_STEERING_WHEEL = 30;
        public static final int LEVEL3_PLEASE_LOOK_AT_THE_ROAD_AHEAD = 31;
        public static final int LEVEL3_PLEASE_TAKE_OVER_THE_VEHICLE_IMMEDIATELY = 29;
        public static final int LEVEL3_PLEASE_TAKE_OVER_THE_VEHICLE_IMMEDIATELY_LCC = 41;
        public static final int LEVEL3_PLEASE_TAKE_OVER_THE_VEHICLE_IN_THE_SAFE_PARKING = 28;
        public static final int LEVEL3_ZEEKR_PILOT_FUNCTION_EXIT = 40;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PilotDrivingStatus {
        public static final int PILOT_DRIVING_STATUS_ACC = 2;
        public static final int PILOT_DRIVING_STATUS_AI_ASSIT = 4;
        public static final int PILOT_DRIVING_STATUS_MANUAL = 1;
        public static final int PILOT_DRIVING_STATUS_NZP = 5;
        public static final int PILOT_DRIVING_STATUS_UNKNOWN = 0;
        public static final int PILOT_DRIVING_STATUS_ZCA = 3;
    }
}
