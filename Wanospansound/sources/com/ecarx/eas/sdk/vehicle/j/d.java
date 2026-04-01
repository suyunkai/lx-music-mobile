package com.ecarx.eas.sdk.vehicle.j;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcu;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcuCallback;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState;
import com.ecarx.eas.sdk.vehicle.api.tcu.Tribool;
import com.ecarx.eas.sdk.vehicle.v3.b.f.b;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d implements ITcu {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.v3.b.f.a f313a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f314b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map<ITcuCallback, a> f315c = new HashMap();

    public final void a(com.ecarx.eas.sdk.vehicle.v3.e eVar) {
        if (eVar != null) {
            try {
                this.f313a = eVar.d();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcu
    public ITcuState getTcuState() {
        try {
            return new e(this.f313a.a());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcu
    public void setTcuCallback(ITcuCallback iTcuCallback) {
        if (this.f315c.get(iTcuCallback) == null) {
            a aVar = new a(iTcuCallback);
            try {
                this.f313a.a(aVar);
                this.f315c.put(iTcuCallback, aVar);
                Log.d(this.f314b, "Has attach TcuCallback");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcu
    public void unsetTcuCallback(ITcuCallback iTcuCallback) {
        a aVar = this.f315c.get(iTcuCallback);
        if (aVar != null) {
            try {
                this.f313a.b(aVar);
                this.f315c.remove(iTcuCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ITcuCallback f316a;

        public a(ITcuCallback iTcuCallback) {
            this.f316a = iTcuCallback;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.f.b
        public final void a(com.ecarx.eas.sdk.vehicle.v3.b.f.c cVar) throws RemoteException {
            this.f316a.onTcuChanged(new b(cVar));
        }
    }

    static class b implements ITcuState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private com.ecarx.eas.sdk.vehicle.v3.b.f.c f317a;

        public b(com.ecarx.eas.sdk.vehicle.v3.b.f.c cVar) {
            this.f317a = cVar;
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
        public final int getGearSelectorPosition() {
            try {
                return this.f317a.a();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
        public final int getGear() {
            try {
                return this.f317a.b();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
        public final Tribool isManualShiftMode() {
            int iC;
            try {
                iC = this.f317a.c();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (iC == 0) {
                return Tribool.indeterminate;
            }
            if (iC == 1) {
                return Tribool.yes;
            }
            if (iC == 2) {
                return Tribool.no;
            }
            return Tribool.indeterminate;
        }
    }
}
