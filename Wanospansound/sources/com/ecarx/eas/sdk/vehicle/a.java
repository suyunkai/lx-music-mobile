package com.ecarx.eas.sdk.vehicle;

import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.sdk.vehicle.api.VehicleAPI;
import com.ecarx.eas.sdk.vehicle.api.bcm.IBcm;
import com.ecarx.eas.sdk.vehicle.api.ems.IEms;
import com.ecarx.eas.sdk.vehicle.api.havc.IHvacState;
import com.ecarx.eas.sdk.vehicle.api.safebelt.ISafeBeltInfo;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensor;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcu;
import com.ecarx.eas.sdk.vehicle.api.tpms.ITireState;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends VehicleAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Singleton<a> f189a = new Singleton<a>() { // from class: com.ecarx.eas.sdk.vehicle.a.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ a create() {
            return new a();
        }
    };

    public static a a() {
        return f189a.get();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final IBcm getBcm() {
        if (this.mApi == null) {
            return null;
        }
        return this.mApi.getBcm();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final IEms getEngine() {
        if (this.mApi == null) {
            return null;
        }
        return this.mApi.getEngine();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ITcu getTcu() {
        if (this.mApi == null) {
            return null;
        }
        return this.mApi.getTcu();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ITireState getTireState(int i) {
        if (this.mApi == null) {
            return null;
        }
        return this.mApi.getTireState(i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final IHvacState getHvacState() {
        if (this.mApi == null) {
            return null;
        }
        return this.mApi.getHvacState();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ISafeBeltInfo getSafeBeltInfo() {
        if (this.mApi == null) {
            return null;
        }
        return this.mApi.getSafeBeltInfo();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ISensor getSensor() {
        if (this.mApi == null) {
            return null;
        }
        return this.mApi.getSensor();
    }
}
