package com.ecarx.eas.sdk.vehicle.g;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV;
import com.ecarx.eas.sdk.vehicle.v3.a.l;
import com.ecarx.openapi.protobuf.ECARXCommon;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class f implements IPHEV {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IEASFrameworkService f284b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f283a = "f";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<IPHEV.IPHEVListener, c> f285c = new HashMap<>();

    public f(IEASFrameworkService iEASFrameworkService) {
        this.f284b = iEASFrameworkService;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV
    public void setUpdateFrequencyUnit(int i) {
        if (this.f284b == null) {
            return;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        EASCallUtils.call(this.f284b, IServiceManager.SERVICE_VEHICLE, "NewEnergy", "setUpdateFrequencyUnit", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV
    public int getUpdateFrequencyUnit() {
        IEASFrameworkService iEASFrameworkService = this.f284b;
        if (iEASFrameworkService == null) {
            return -1;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergy", "getUpdateFrequencyUnit", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV
    public void registerPHEVListener(IPHEV.IPHEVListener iPHEVListener) {
        if (this.f284b == null || iPHEVListener == null) {
            return;
        }
        if (this.f285c.get(iPHEVListener) == null) {
            c cVar = new c(iPHEVListener);
            EASCallUtils.call(this.f284b, IServiceManager.SERVICE_VEHICLE, "NewEnergy", "registerPHEVListener", null, cVar);
            this.f285c.put(iPHEVListener, cVar);
        }
        Log.d(this.f283a, "Has register IPHEVListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV
    public void unregisterPHEVListener(IPHEV.IPHEVListener iPHEVListener) {
        c cVar;
        if (this.f284b == null || iPHEVListener == null || (cVar = this.f285c.get(iPHEVListener)) == null) {
            return;
        }
        EASCallUtils.call(this.f284b, IServiceManager.SERVICE_VEHICLE, "NewEnergy", "unregisterPHEVListener", null, cVar);
        this.f285c.remove(iPHEVListener);
    }

    static class c extends l.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IPHEV.IPHEVListener f288a;

        public c(IPHEV.IPHEVListener iPHEVListener) {
            this.f288a = iPHEVListener;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.l
        public final void a(com.ecarx.eas.sdk.vehicle.v3.a.a aVar) throws RemoteException {
            this.f288a.onAvgEnergyInfoUpdate(new a(aVar));
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.l
        public final void a(com.ecarx.eas.sdk.vehicle.v3.a.f fVar) throws RemoteException {
            this.f288a.onDrivingInfoUpdate(new b(fVar));
        }
    }

    static class a implements IPHEV.IAvgEnergyInfo {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private com.ecarx.eas.sdk.vehicle.v3.a.a f286a;

        public a(com.ecarx.eas.sdk.vehicle.v3.a.a aVar) {
            this.f286a = aVar;
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IAvgEnergyInfo
        public final float getAvgFuelConsumption() {
            try {
                return this.f286a.a();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IAvgEnergyInfo
        public final float getAvgEleConsumption() {
            try {
                return this.f286a.b();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IAvgEnergyInfo
        public final float getAvgEnergyFeedback() {
            try {
                return this.f286a.c();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }
    }

    static class b implements IPHEV.IDrivingInfo {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private com.ecarx.eas.sdk.vehicle.v3.a.f f287a;

        public b(com.ecarx.eas.sdk.vehicle.v3.a.f fVar) {
            this.f287a = fVar;
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final int getDrivingDistanceInCurrentDay() {
            try {
                return this.f287a.a();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final int getCurrentTripDistance() {
            try {
                return this.f287a.b();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final long getCurrentTripDuration() {
            try {
                return this.f287a.c();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0L;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final int getEnduranceMileageByFuel() {
            try {
                return this.f287a.d();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final int getEnduranceMileageByEle() {
            try {
                return this.f287a.e();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV.IDrivingInfo
        public final float getEleEnduranceMileagePercentage() {
            try {
                return this.f287a.f();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }
    }
}
