package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.IDriveMode;

/* JADX INFO: loaded from: classes2.dex */
public abstract class p implements IDriveMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.v3.b.e.a f232a;

    public p() {
        getClass().getSimpleName();
    }

    public final void a(com.ecarx.eas.sdk.vehicle.v3.e eVar) {
        if (eVar != null) {
            try {
                this.f232a = eVar.g();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final FunctionStatus a() {
        int iA;
        try {
            iA = this.f232a.a();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (iA == 0) {
            return FunctionStatus.active;
        }
        if (iA == 1) {
            return FunctionStatus.notactive;
        }
        if (iA == 2) {
            return FunctionStatus.notavailable;
        }
        if (iA == 3) {
            return FunctionStatus.error;
        }
        return FunctionStatus.error;
    }

    public final int b() {
        try {
            return this.f232a.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }
}
