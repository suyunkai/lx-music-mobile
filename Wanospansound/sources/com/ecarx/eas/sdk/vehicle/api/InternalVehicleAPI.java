package com.ecarx.eas.sdk.vehicle.api;

import com.ecarx.eas.sdk.vehicle.api.bcm.IBcm;
import com.ecarx.eas.sdk.vehicle.api.ems.IEms;
import com.ecarx.eas.sdk.vehicle.api.havc.IHvacState;
import com.ecarx.eas.sdk.vehicle.api.safebelt.ISafeBeltInfo;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensor;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcu;
import com.ecarx.eas.sdk.vehicle.api.tpms.ITireState;

/* JADX INFO: loaded from: classes2.dex */
public interface InternalVehicleAPI extends IVehicleAPI {
    IBcm getBcm();

    IEms getEngine();

    IHvacState getHvacState();

    ISafeBeltInfo getSafeBeltInfo();

    ISensor getSensor();

    ITcu getTcu();

    ITireState getTireState(int i);
}
