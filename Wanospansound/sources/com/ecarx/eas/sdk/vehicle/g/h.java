package com.ecarx.eas.sdk.vehicle.g;

import android.os.RemoteException;
import android.util.Log;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState;
import com.ecarx.eas.sdk.vehicle.v3.a.i;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class h implements IEpt {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.ecarx.eas.sdk.vehicle.v3.a.g f291b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g f292c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f290a = CmcdData.Factory.STREAMING_FORMAT_HLS;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<IEpt.IEptStateListener, com.ecarx.eas.sdk.vehicle.v3.a.i> f293d = new HashMap<>();

    public h(com.ecarx.eas.sdk.vehicle.v3.a.g gVar) {
        this.f291b = gVar;
        try {
            this.f292c = new g(this.f291b.a());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public IEptState getEptState() {
        return this.f292c;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void setSOCPointLevel(int i) {
        try {
            this.f291b.a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public int getSOCPointLevelMax() {
        try {
            return this.f291b.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public int getSOCPointLevelMin() {
        try {
            return this.f291b.c();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public int getSOCPointLevelStep() {
        try {
            return this.f291b.d();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void setAvasDisable(boolean z) {
        try {
            this.f291b.a(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void setAvasVolumeType(int i) {
        try {
            this.f291b.b(i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void setInfrequentChargingMode(boolean z) {
        try {
            this.f291b.b(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void registerStateListener(IEpt.IEptStateListener iEptStateListener) {
        if (this.f293d.get(iEptStateListener) == null) {
            a aVar = new a(iEptStateListener);
            try {
                this.f291b.a(aVar);
                this.f293d.put(iEptStateListener, aVar);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d(this.f290a, "Has register IEptStateListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void unregisterStateListener(IEpt.IEptStateListener iEptStateListener) {
        com.ecarx.eas.sdk.vehicle.v3.a.i iVar = this.f293d.get(iEptStateListener);
        if (iVar != null) {
            try {
                this.f291b.b(iVar);
                this.f293d.remove(iEptStateListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    static class a extends i.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IEpt.IEptStateListener f294a;

        public a(IEpt.IEptStateListener iEptStateListener) {
            this.f294a = iEptStateListener;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.i
        public final void a(com.ecarx.eas.sdk.vehicle.v3.a.h hVar) throws RemoteException {
            this.f294a.onEptStateChanged(new g(hVar));
        }
    }
}
