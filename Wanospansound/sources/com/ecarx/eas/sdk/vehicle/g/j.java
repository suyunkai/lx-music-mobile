package com.ecarx.eas.sdk.vehicle.g;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV;
import com.ecarx.eas.sdk.vehicle.v3.a.k;
import com.ecarx.eas.sdk.vehicle.v3.a.l;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class j implements IPHEV {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private k f296b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f295a = "j";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<IPHEV.IPHEVListener, l> f297c = new HashMap<>();

    public j(k kVar) {
        this.f296b = kVar;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV
    public void setUpdateFrequencyUnit(int i) {
        try {
            this.f296b.a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV
    public int getUpdateFrequencyUnit() {
        try {
            return this.f296b.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV
    public void registerPHEVListener(IPHEV.IPHEVListener iPHEVListener) {
        if (this.f297c.get(iPHEVListener) == null) {
            c cVar = new c(iPHEVListener);
            try {
                this.f296b.a(cVar);
                this.f297c.put(iPHEVListener, cVar);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d(this.f295a, "Has register IPHEVListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV
    public void unregisterPHEVListener(IPHEV.IPHEVListener iPHEVListener) {
        l lVar = this.f297c.get(iPHEVListener);
        if (lVar != null) {
            try {
                this.f296b.b(lVar);
                this.f297c.remove(iPHEVListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    static class c extends l.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IPHEV.IPHEVListener f300a;

        public c(IPHEV.IPHEVListener iPHEVListener) {
            this.f300a = iPHEVListener;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.l
        public final void a(com.ecarx.eas.sdk.vehicle.v3.a.a aVar) throws RemoteException {
            this.f300a.onAvgEnergyInfoUpdate(new a(aVar));
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.l
        public final void a(com.ecarx.eas.sdk.vehicle.v3.a.f fVar) throws RemoteException {
            this.f300a.onDrivingInfoUpdate(new b(fVar));
        }
    }

    static class a implements IPHEV.IAvgEnergyInfo {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private com.ecarx.eas.sdk.vehicle.v3.a.a f298a;

        public a(com.ecarx.eas.sdk.vehicle.v3.a.a aVar) {
            this.f298a = aVar;
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IAvgEnergyInfo
        public final float getAvgFuelConsumption() {
            try {
                return this.f298a.a();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IAvgEnergyInfo
        public final float getAvgEleConsumption() {
            try {
                return this.f298a.b();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IAvgEnergyInfo
        public final float getAvgEnergyFeedback() {
            try {
                return this.f298a.c();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }
    }

    static class b implements IPHEV.IDrivingInfo {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private com.ecarx.eas.sdk.vehicle.v3.a.f f299a;

        public b(com.ecarx.eas.sdk.vehicle.v3.a.f fVar) {
            this.f299a = fVar;
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final int getDrivingDistanceInCurrentDay() {
            try {
                return this.f299a.a();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final int getCurrentTripDistance() {
            try {
                return this.f299a.b();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final long getCurrentTripDuration() {
            try {
                return this.f299a.c();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0L;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final int getEnduranceMileageByFuel() {
            try {
                return this.f299a.d();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final int getEnduranceMileageByEle() {
            try {
                return this.f299a.e();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final float getEleEnduranceMileagePercentage() {
            try {
                return this.f299a.f();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }
    }
}
