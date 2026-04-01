package com.ecarx.eas.sdk.vehicle.f;

import android.os.IInterface;
import android.os.RemoteException;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI;
import com.ecarx.eas.sdk.vehicle.api.bcm.IBcm;
import com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo;
import com.ecarx.eas.sdk.vehicle.api.ems.IEms;
import com.ecarx.eas.sdk.vehicle.api.havc.IHvacState;
import com.ecarx.eas.sdk.vehicle.api.safebelt.ISafeBeltInfo;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensor;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcu;
import com.ecarx.eas.sdk.vehicle.api.tpms.ITireState;

/* JADX INFO: loaded from: classes2.dex */
public final class B extends A implements InternalVehicleAPI {
    private static Singleton<B> h = new Singleton<B>() { // from class: com.ecarx.eas.sdk.vehicle.f.B.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ B create() {
            return new B();
        }
    };
    private volatile com.ecarx.eas.sdk.vehicle.j.d f;
    private volatile z g;

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
    public final ITireState getTireState(int i) {
        return null;
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.IApi
    public final /* synthetic */ void init(IInterface iInterface) {
        com.ecarx.eas.sdk.vehicle.v3.e eVar = (com.ecarx.eas.sdk.vehicle.v3.e) iInterface;
        super.init(eVar);
        if (isNotAlive()) {
            return;
        }
        if (this.f205a != null) {
            this.f205a.a(eVar);
        }
        if (this.f206b != null) {
            this.f206b.a(eVar);
        }
        if (this.e != null) {
            this.e.a(eVar);
        }
        if (this.f207c != null) {
            this.f207c.a(eVar);
        }
        if (this.g != null) {
            this.g.a(eVar);
        } else {
            this.g = (z) getSensor();
        }
        if (this.f != null) {
            this.f.a(eVar);
        }
        if (this.f208d != null) {
            this.f208d.a(eVar);
        }
    }

    public static B b() {
        return h.get();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ITcu getTcu() {
        if (this.f != null) {
            return this.f;
        }
        synchronized (this) {
            if (this.f != null) {
                return this.f;
            }
            try {
                com.ecarx.eas.sdk.vehicle.v3.b.f.a aVarD = ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).d();
                this.f = aVarD == null ? null : new com.ecarx.eas.sdk.vehicle.j.f(aVarD);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this.f;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ISafeBeltInfo getSafeBeltInfo() {
        return new com.ecarx.eas.sdk.vehicle.h.a();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.InternalVehicleAPI
    public final ISensor getSensor() {
        if (this.g != null) {
            return this.g;
        }
        synchronized (this) {
            if (this.g != null) {
                return this.g;
            }
            try {
                com.ecarx.eas.sdk.vehicle.v3.b.b.b.a aVarE = ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).e();
                this.g = aVarE == null ? null : new com.ecarx.eas.sdk.vehicle.i.d(aVarE);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return this.g;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.A, com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public final ICarInfo getCarInfo() {
        if (this.f207c == null) {
            synchronized (this) {
                if (this.f207c == null) {
                    try {
                        this.f207c = new com.ecarx.eas.sdk.vehicle.b.b(((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).c());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.f207c;
    }
}
