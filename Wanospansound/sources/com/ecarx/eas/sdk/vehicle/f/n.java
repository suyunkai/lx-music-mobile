package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IMileageInfo;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IWarningInfo;
import com.ecarx.eas.sdk.vehicle.v3.c;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class n implements IDashboard {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.v3.b f226a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f227b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<IDashboard.IDashboardHintObserver, com.ecarx.eas.sdk.vehicle.v3.c> f228c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<IDashboard.IVehicleSpeedObserver, com.ecarx.eas.sdk.vehicle.v3.b.c.b> f229d = new HashMap<>();
    private HashMap<IDashboard.IVehicleACCStatusObserver, com.ecarx.eas.sdk.vehicle.v3.b.c.a> e = new HashMap<>();

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public IWarningInfo[] getWarningInfo() {
        return new IWarningInfo[0];
    }

    public final void a(com.ecarx.eas.sdk.vehicle.v3.e eVar) {
        if (eVar != null) {
            try {
                this.f226a = eVar.a();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getFuelLevel() {
        try {
            return (int) this.f226a.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getFullFuelLevel() {
        try {
            return (int) this.f226a.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public double getAverageFuelConsumptionLevel() {
        try {
            return this.f226a.c();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public double getInstantaneousFuelConsumptionLevel() {
        try {
            return this.f226a.d();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getVehicleSpeed() {
        try {
            return (int) this.f226a.e();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public double getVehicleSpeedHighPrecision() {
        try {
            return this.f226a.e();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public IMileageInfo geMileageInfo() {
        try {
            return this.f226a.f();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public IWarningInfo getWarningInformation() {
        Log.d("Dashboard", "getWarningInfo()");
        try {
            return this.f226a.g();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getHandBrakeStatus() {
        try {
            return this.f226a.h();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public boolean attachDashboardHintChangeObserver(IDashboard.IDashboardHintObserver iDashboardHintObserver) {
        if (this.f228c.get(iDashboardHintObserver) == null) {
            a aVar = new a(iDashboardHintObserver);
            try {
                boolean zA = this.f226a.a(aVar);
                if (zA) {
                    this.f228c.put(iDashboardHintObserver, aVar);
                }
                return zA;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        Log.d(this.f227b, "Has attach IDashboardHintObserver");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public void detachDashboardHintChangeObserver(IDashboard.IDashboardHintObserver iDashboardHintObserver) {
        com.ecarx.eas.sdk.vehicle.v3.c cVar = this.f228c.get(iDashboardHintObserver);
        if (cVar != null) {
            try {
                this.f226a.b(cVar);
                this.f228c.remove(iDashboardHintObserver);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public int getAccStatus() {
        try {
            return this.f226a.i();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public boolean attachVehicleACCStatusObserver(IDashboard.IVehicleACCStatusObserver iVehicleACCStatusObserver) {
        if (this.e.get(iVehicleACCStatusObserver) != null) {
            return true;
        }
        com.ecarx.eas.sdk.vehicle.c.c cVar = new com.ecarx.eas.sdk.vehicle.c.c(iVehicleACCStatusObserver);
        try {
            boolean zA = this.f226a.a(cVar);
            if (zA) {
                this.e.put(iVehicleACCStatusObserver, cVar);
                Log.d(this.f227b, "Has attachVehicleACCStatusObserver");
            }
            return zA;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public void detachVehicleACCStatusObserver(IDashboard.IVehicleACCStatusObserver iVehicleACCStatusObserver) {
        com.ecarx.eas.sdk.vehicle.v3.b.c.a aVar = this.e.get(iVehicleACCStatusObserver);
        if (aVar != null) {
            try {
                this.e.remove(iVehicleACCStatusObserver);
                this.f226a.b(aVar);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public boolean attachVehicleSpeedObserver(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver, double[] dArr, int i, int i2) {
        if (this.f229d.get(iVehicleSpeedObserver) == null) {
            com.ecarx.eas.sdk.vehicle.c.d dVar = new com.ecarx.eas.sdk.vehicle.c.d(iVehicleSpeedObserver);
            try {
                boolean zA = this.f226a.a(dVar, dArr, i, i2);
                if (zA) {
                    this.f229d.put(iVehicleSpeedObserver, dVar);
                }
                return zA;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d(this.f227b, "Has attachVehicleSpeedObserver");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public boolean attachVehicleSpeedObserver(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver) {
        if (this.f229d.get(iVehicleSpeedObserver) == null) {
            com.ecarx.eas.sdk.vehicle.c.d dVar = new com.ecarx.eas.sdk.vehicle.c.d(iVehicleSpeedObserver);
            try {
                boolean zA = this.f226a.a(dVar);
                if (zA) {
                    this.f229d.put(iVehicleSpeedObserver, dVar);
                }
                return zA;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d(this.f227b, "Has attachVehicleSpeedObserver");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard
    public void detachVehicleSpeedObserver(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver) {
        com.ecarx.eas.sdk.vehicle.v3.b.c.b bVar = this.f229d.get(iVehicleSpeedObserver);
        if (bVar != null) {
            try {
                this.f226a.b(bVar);
                this.f229d.remove(iVehicleSpeedObserver);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    static class a extends c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final IDashboard.IDashboardHintObserver f230a;

        a(IDashboard.IDashboardHintObserver iDashboardHintObserver) {
            this.f230a = iDashboardHintObserver;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.c
        public final void a(int i, int i2) {
            this.f230a.onDashboardHintChanged(i, i2);
        }
    }
}
