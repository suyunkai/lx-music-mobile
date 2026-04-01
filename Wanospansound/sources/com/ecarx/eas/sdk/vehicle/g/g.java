package com.ecarx.eas.sdk.vehicle.g;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState;

/* JADX INFO: loaded from: classes2.dex */
public final class g implements IEptState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ecarx.eas.sdk.vehicle.v3.a.h f289a;

    public g(com.ecarx.eas.sdk.vehicle.v3.a.h hVar) {
        this.f289a = hVar;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final int getEptActualMode() {
        try {
            return this.f289a.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final int getSOCPointLevel() {
        try {
            return this.f289a.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final boolean isAvasDisable() {
        try {
            return this.f289a.c();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final int getAvasVolumeType() {
        try {
            return this.f289a.d();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final boolean isInfrequentChargingModeOn() {
        try {
            return this.f289a.e();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
