package com.ecarx.eas.sdk.vehicle.g;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.f.y;

/* JADX INFO: loaded from: classes2.dex */
public final class i extends y {
    public i(com.ecarx.eas.sdk.vehicle.v3.a.j jVar) {
        this.f261a = jVar;
        try {
            this.f262b = new h(this.f261a.c());
            this.f263c = new b(this.f261a.d());
            this.f264d = new j(this.f261a.e());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
