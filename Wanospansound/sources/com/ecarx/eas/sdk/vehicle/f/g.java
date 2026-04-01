package com.ecarx.eas.sdk.vehicle.f;

import android.os.IInterface;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI;
import com.ecarx.eas.sdk.vehicle.api.bcm.IBcm;
import com.ecarx.eas.sdk.vehicle.api.ems.IEms;
import com.ecarx.eas.sdk.vehicle.api.havc.IHvacState;
import com.ecarx.eas.sdk.vehicle.api.safebelt.ISafeBeltInfo;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensor;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcu;
import com.ecarx.eas.sdk.vehicle.api.tpms.ITireState;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends x implements InternalVehicleAPI {
    private static Singleton<g> i = new Singleton<g>() { // from class: com.ecarx.eas.sdk.vehicle.f.g.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ g create() {
            return new g();
        }
    };
    private com.ecarx.eas.sdk.vehicle.j.c g;
    private v h;

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final IBcm getBcm() {
        return null;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final IEms getEngine() {
        return null;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final IHvacState getHvacState() {
        return null;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ITireState getTireState(int i2) {
        return null;
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.IApi
    public final /* synthetic */ void init(IInterface iInterface) {
        IEASFrameworkService iEASFrameworkService = (IEASFrameworkService) iInterface;
        super.init(iEASFrameworkService);
        if (this.f257a == null) {
            this.f257a = new com.ecarx.eas.sdk.vehicle.c.b(iEASFrameworkService);
        } else {
            this.f257a.a(iEASFrameworkService);
        }
        if (this.f258b == null) {
            this.f258b = new com.ecarx.eas.sdk.vehicle.g.e(iEASFrameworkService);
        } else {
            this.f258b.a(iEASFrameworkService);
        }
        if (this.f259c == null) {
            this.f259c = new com.ecarx.eas.sdk.vehicle.b.c(iEASFrameworkService);
        } else {
            this.f259c.a(iEASFrameworkService);
        }
        com.ecarx.eas.sdk.vehicle.j.c cVar = this.g;
        if (cVar == null) {
            this.g = new com.ecarx.eas.sdk.vehicle.j.b(iEASFrameworkService);
        } else {
            cVar.a(iEASFrameworkService);
        }
        v vVar = this.h;
        if (vVar == null) {
            this.h = new com.ecarx.eas.sdk.vehicle.i.a(iEASFrameworkService);
        } else {
            vVar.a(iEASFrameworkService);
        }
        if (this.f260d == null) {
            this.f260d = new com.ecarx.eas.sdk.vehicle.d.b(iEASFrameworkService);
        } else {
            this.f260d.a(iEASFrameworkService);
        }
        if (this.e == null) {
            this.e = new com.ecarx.eas.sdk.vehicle.e.b(iEASFrameworkService);
        } else {
            this.e.a(iEASFrameworkService);
        }
        if (this.f == null) {
            this.f = new com.ecarx.eas.sdk.vehicle.a.b(this, iEASFrameworkService);
        } else {
            this.f.a(iEASFrameworkService);
        }
    }

    public static g b() {
        return i.get();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ITcu getTcu() {
        return this.g;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ISafeBeltInfo getSafeBeltInfo() {
        return new com.ecarx.eas.sdk.vehicle.h.a();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ISensor getSensor() {
        return this.h;
    }
}
