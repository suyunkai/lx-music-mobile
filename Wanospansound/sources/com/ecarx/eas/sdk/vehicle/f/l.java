package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.audio.IAudio;
import com.ecarx.eas.sdk.vehicle.api.audio.ICaeSwitchChangeCallback;
import com.ecarx.eas.sdk.vehicle.v3.b.a.b;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class l implements IAudio {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.v3.b.a.a f220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f221b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<ICaeSwitchChangeCallback, a> f222c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.ecarx.eas.sdk.vehicle.api.a f223d;

    public l(com.ecarx.eas.sdk.vehicle.api.a aVar) {
        this.f223d = aVar;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public FunctionStatus isCaeSwitchSupported() {
        com.ecarx.eas.sdk.vehicle.v3.b.a.a aVar;
        try {
            aVar = this.f220a;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (aVar == null) {
            return FunctionStatus.error;
        }
        int iA = aVar.a();
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

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public int getCaeSwitchValue() {
        try {
            com.ecarx.eas.sdk.vehicle.v3.b.a.a aVar = this.f220a;
            if (aVar == null) {
                return Integer.MIN_VALUE;
            }
            return aVar.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public boolean setCaeSwitchValue(int i) {
        try {
            com.ecarx.eas.sdk.vehicle.v3.b.a.a aVar = this.f220a;
            if (aVar == null) {
                return false;
            }
            return aVar.a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public boolean registerCaeSwitchValueWatcher(ICaeSwitchChangeCallback iCaeSwitchChangeCallback) {
        if (this.f220a != null && iCaeSwitchChangeCallback != null) {
            try {
                if (this.f222c == null) {
                    this.f222c = new HashMap<>();
                }
                if (this.f222c.get(iCaeSwitchChangeCallback) != null) {
                    return true;
                }
                a aVar = new a(iCaeSwitchChangeCallback);
                boolean zA = this.f220a.a(aVar);
                if (zA) {
                    this.f222c.put(iCaeSwitchChangeCallback, aVar);
                    Log.d(this.f221b, "Has register ICaeSwitchChangeWrapper");
                }
                return zA;
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.d(this.f221b, "Has register ICaeSwitchChangeWrapper RemoteException ：" + e.toString());
            }
        }
        return false;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public boolean unregisterCaeSwitchValueWatcher(ICaeSwitchChangeCallback iCaeSwitchChangeCallback) {
        HashMap<ICaeSwitchChangeCallback, a> map;
        a aVar;
        if (this.f220a != null && iCaeSwitchChangeCallback != null && (map = this.f222c) != null && (aVar = map.get(iCaeSwitchChangeCallback)) != null) {
            try {
                this.f222c.remove(iCaeSwitchChangeCallback);
                return this.f220a.b(aVar);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public int getAudioProvider() {
        com.ecarx.eas.sdk.vehicle.api.a aVar = this.f223d;
        if (aVar != null && aVar.a(ClientType.OpenAPI, 6)) {
            try {
                com.ecarx.eas.sdk.vehicle.v3.b.a.a aVar2 = this.f220a;
                if (aVar2 == null) {
                    return Integer.MIN_VALUE;
                }
                return aVar2.c();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return Integer.MIN_VALUE;
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ICaeSwitchChangeCallback f224a;

        public a(ICaeSwitchChangeCallback iCaeSwitchChangeCallback) {
            this.f224a = iCaeSwitchChangeCallback;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.a.b
        public final void a(int i) throws RemoteException {
            ICaeSwitchChangeCallback iCaeSwitchChangeCallback = this.f224a;
            if (iCaeSwitchChangeCallback != null) {
                iCaeSwitchChangeCallback.onCaeSwitchChanged(i);
            }
        }
    }
}
