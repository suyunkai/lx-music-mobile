package com.ecarx.openapi.sdk.carproperty.function.hev;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ICharging {
    public static final int CHARGE_FUNC_CHARGING_DISCHARGING_STATE = 606100480;
    public static final int CHARGE_FUNC_CHARGING_PLUG_STATE = 605225472;
    public static final int CHARGE_FUNC_CHARGING_PLUG_TYPE = 605225216;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChargingDisChargingState {
        public static final int ACCHRGNFLTVEHSIDE = 606100519;
        public static final int AC_CHARGING = 606100482;
        public static final int AC_CHARGINGSUSPEND = 606100517;
        public static final int AC_CHRGNFLTCHRGRSIDE = 606100500;
        public static final int BOOKING = 606100486;
        public static final int BOOSTCHARGING = 606100520;
        public static final int BOOSTCHARGINGFLT = 606100521;
        public static final int CHARGING_CMPL = 606100484;
        public static final int CHARGING_END = 606100483;
        public static final int CHARING_FALUT = 606100497;
        public static final int DC_CHARGING = 606100501;
        public static final int DC_CHARGINGEND = 606100518;
        public static final int DC_CHRGNFLTCHRGRSIDECOMFLT = 606100515;
        public static final int DC_CHRGNFLTCHRGRSIDECONFLT = 606100512;
        public static final int DC_CHRGNFLTCHRGRSIDEEMGYFLT = 606100514;
        public static final int DC_CHRGNFLTCHRGRSIDEHWFLT = 606100513;
        public static final int DC_CHRGNFLTCHRGRSIDETEMPFLT = 606100505;
        public static final int DC_CHRGNFLTVEHSIDE = 606100504;
        public static final int DEFAULT = 2;
        public static final int DISCHARING = 606100488;
        public static final int DISCHARING_CMPL = 606100496;
        public static final int DISCHARING_END = 606100489;
        public static final int DISCHARING_FALUT = 606100498;
        public static final int HEATING = 606100485;
        public static final int NONE = 606100529;
        public static final int NO_CHARGING = 606100481;
        public static final int NO_DISCHARING = 606100487;
        public static final int SUPERCHARGING = 606100516;
        public static final int WIRELESSCHARGING = 606100528;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChargingPlugState {
        public static final int CHARGE_PLUG_STATE_CHARGING = 605225474;
        public static final int CHARGE_PLUG_STATE_CHARGING_PAUSE = 605225483;
        public static final int CHARGE_PLUG_STATE_COMPLETED = 605225475;
        public static final int CHARGE_PLUG_STATE_CONNECTED = 605225481;
        public static final int CHARGE_PLUG_STATE_DISCHARGING = 605225478;
        public static final int CHARGE_PLUG_STATE_DISCHARGING_COMPLETED = 605225479;
        public static final int CHARGE_PLUG_STATE_DISCHARGING_PAUSE = 605225484;
        public static final int CHARGE_PLUG_STATE_DISCONNECTED = 605225482;
        public static final int CHARGE_PLUG_STATE_ERROR = 605225477;
        public static final int CHARGE_PLUG_STATE_HEATING = 605225480;
        public static final int CHARGE_PLUG_STATE_MULTI = 605225476;
        public static final int CHARGE_PLUG_STATE_PREPARED = 605225473;
        public static final int CHARGE_PLUG_STATE_UNKNOWN = 255;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChargingPlugType {
        public static final int CHARGE_PLUG_AC = 605225217;
        public static final int CHARGE_PLUG_DC = 605225218;
        public static final int CHARGE_PLUG_DISCHARGE = 605225219;
        public static final int CHARGE_PLUG_UNKNOWN = 255;
    }
}
