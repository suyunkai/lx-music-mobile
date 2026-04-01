package com.ecarx.eas.sdk.vehicle.f;

import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IMileageInfo;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IWarningInfo;
import com.ecarx.eas.sdk.vehicle.v3.c;
import com.ecarx.openapi.protobuf.vehicle.ECARXAdaptVehicle;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class s implements IDashboard {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected IEASFrameworkService f239a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<IDashboard.IDashboardHintObserver, a> f240b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<IDashboard.IVehicleSpeedObserver, com.ecarx.eas.sdk.vehicle.c.d> f241c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<IDashboard.IVehicleACCStatusObserver, com.ecarx.eas.sdk.vehicle.c.c> f242d = new HashMap<>();

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public IWarningInfo[] getWarningInfo() {
        return new IWarningInfo[0];
    }

    public final void a(IEASFrameworkService iEASFrameworkService) {
        this.f239a = iEASFrameworkService;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getFuelLevel() {
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null) {
            return 0;
        }
        return (int) EASCallUtils.callDouble(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getFuelLevel", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getFullFuelLevel() {
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null) {
            return 0;
        }
        return (int) EASCallUtils.callDouble(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getFullFuelLevel", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public double getAverageFuelConsumptionLevel() {
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null) {
            return 0.0d;
        }
        return EASCallUtils.callDouble(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getAverageFuelConsumptionLevel", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public double getInstantaneousFuelConsumptionLevel() {
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null) {
            return 0.0d;
        }
        return EASCallUtils.callDouble(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getInstantaneousFuelConsumptionLevel", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getVehicleSpeed() {
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null) {
            return 0;
        }
        return (int) EASCallUtils.callDouble(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getVehicleSpeed", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public double getVehicleSpeedHighPrecision() {
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null) {
            return 0.0d;
        }
        return EASCallUtils.callDouble(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getVehicleSpeed", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public IMileageInfo geMileageInfo() {
        byte[] bArrCall;
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null || (bArrCall = EASCallUtils.call(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getMileageInfo", null, null)) == null) {
            return null;
        }
        try {
            ECARXAdaptVehicle.MileageInfo from = ECARXAdaptVehicle.MileageInfo.parseFrom(bArrCall);
            if (from == null) {
                return null;
            }
            return new com.ecarx.eas.sdk.vehicle.v3.IMileageInfo(from.totalMileage, from.tripMileage, from.tripDuration, from.enduranceMileage, from.nextMaintenanceMileage);
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public IWarningInfo getWarningInformation() {
        byte[] bArrCall;
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null || (bArrCall = EASCallUtils.call(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getWarningInfo", null, null)) == null) {
            return null;
        }
        try {
            ECARXAdaptVehicle.WarningInfo from = ECARXAdaptVehicle.WarningInfo.parseFrom(bArrCall);
            if (from == null) {
                return null;
            }
            return new com.ecarx.eas.sdk.vehicle.v3.IWarningInfo(from.warningId, from.warningPriority);
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getHandBrakeStatus() {
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null) {
            return 0;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getHandBrakeStatus", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public boolean attachDashboardHintChangeObserver(IDashboard.IDashboardHintObserver iDashboardHintObserver) {
        boolean zCallBoolean = false;
        if (this.f239a != null && iDashboardHintObserver != null && this.f240b.get(iDashboardHintObserver) == null) {
            a aVar = new a(iDashboardHintObserver);
            zCallBoolean = EASCallUtils.callBoolean(this.f239a, IServiceManager.SERVICE_VEHICLE, "Dashboard", "attachDashboardHintChangeObserver", null, aVar);
            if (zCallBoolean) {
                this.f240b.put(iDashboardHintObserver, aVar);
            }
        }
        return zCallBoolean;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public void detachDashboardHintChangeObserver(IDashboard.IDashboardHintObserver iDashboardHintObserver) {
        a aVar;
        if (iDashboardHintObserver == null || this.f239a == null || (aVar = this.f240b.get(iDashboardHintObserver)) == null) {
            return;
        }
        EASCallUtils.call(this.f239a, IServiceManager.SERVICE_VEHICLE, "Dashboard", "detachDashboardHintChangeObserver", null, aVar);
        this.f240b.remove(iDashboardHintObserver);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getAccStatus() {
        IEASFrameworkService iEASFrameworkService = this.f239a;
        if (iEASFrameworkService == null) {
            return 0;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Dashboard", "getAccStatus", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public boolean attachVehicleACCStatusObserver(IDashboard.IVehicleACCStatusObserver iVehicleACCStatusObserver) {
        boolean zCallBoolean = false;
        if (this.f239a != null && iVehicleACCStatusObserver != null && this.f242d.get(iVehicleACCStatusObserver) == null) {
            com.ecarx.eas.sdk.vehicle.c.c cVar = new com.ecarx.eas.sdk.vehicle.c.c(iVehicleACCStatusObserver);
            zCallBoolean = EASCallUtils.callBoolean(this.f239a, IServiceManager.SERVICE_VEHICLE, "Dashboard", "attachVehicleACCStatusObserver", null, cVar);
            if (zCallBoolean) {
                this.f242d.put(iVehicleACCStatusObserver, cVar);
            }
        }
        return zCallBoolean;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public void detachVehicleACCStatusObserver(IDashboard.IVehicleACCStatusObserver iVehicleACCStatusObserver) {
        com.ecarx.eas.sdk.vehicle.c.c cVar;
        if (this.f239a == null || iVehicleACCStatusObserver == null || (cVar = this.f242d.get(iVehicleACCStatusObserver)) == null) {
            return;
        }
        EASCallUtils.call(this.f239a, IServiceManager.SERVICE_VEHICLE, "Dashboard", "detachVehicleACCStatusObserver", null, cVar);
        this.f242d.remove(iVehicleACCStatusObserver);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public boolean attachVehicleSpeedObserver(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver, double[] dArr, int i, int i2) {
        if (this.f239a == null || iVehicleSpeedObserver == null || this.f241c.get(iVehicleSpeedObserver) != null) {
            return false;
        }
        com.ecarx.eas.sdk.vehicle.c.d dVar = new com.ecarx.eas.sdk.vehicle.c.d(iVehicleSpeedObserver);
        ECARXAdaptVehicle.VehicleSpeedObserverInfo vehicleSpeedObserverInfo = new ECARXAdaptVehicle.VehicleSpeedObserverInfo();
        vehicleSpeedObserverInfo.duration = i2;
        vehicleSpeedObserverInfo.speeds = dArr;
        vehicleSpeedObserverInfo.trend = i;
        boolean zCallBoolean = EASCallUtils.callBoolean(this.f239a, IServiceManager.SERVICE_VEHICLE, "Dashboard", "attachVehicleSpeedObserver2", MessageNano.toByteArray(vehicleSpeedObserverInfo), dVar);
        if (zCallBoolean) {
            this.f241c.put(iVehicleSpeedObserver, dVar);
        }
        return zCallBoolean;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public boolean attachVehicleSpeedObserver(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver) {
        boolean zCallBoolean = false;
        if (this.f239a != null && iVehicleSpeedObserver != null && this.f241c.get(iVehicleSpeedObserver) == null) {
            com.ecarx.eas.sdk.vehicle.c.d dVar = new com.ecarx.eas.sdk.vehicle.c.d(iVehicleSpeedObserver);
            zCallBoolean = EASCallUtils.callBoolean(this.f239a, IServiceManager.SERVICE_VEHICLE, "Dashboard", "attachVehicleSpeedObserver1", null, dVar);
            if (zCallBoolean) {
                this.f241c.put(iVehicleSpeedObserver, dVar);
            }
        }
        return zCallBoolean;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public void detachVehicleSpeedObserver(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver) {
        if (this.f239a == null || iVehicleSpeedObserver == null) {
            return;
        }
        com.ecarx.eas.sdk.vehicle.c.d dVar = this.f241c.get(iVehicleSpeedObserver);
        if (iVehicleSpeedObserver != null) {
            EASCallUtils.call(this.f239a, IServiceManager.SERVICE_VEHICLE, "Dashboard", "detachVehicleSpeedObserver", null, dVar);
            this.f241c.remove(iVehicleSpeedObserver);
        }
    }

    static class a extends c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final IDashboard.IDashboardHintObserver f243a;

        a(IDashboard.IDashboardHintObserver iDashboardHintObserver) {
            this.f243a = iDashboardHintObserver;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.c
        public final void a(int i, int i2) {
            this.f243a.onDashboardHintChanged(i, i2);
        }
    }
}
