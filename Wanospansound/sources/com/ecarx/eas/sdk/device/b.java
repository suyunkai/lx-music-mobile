package com.ecarx.eas.sdk.device;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.device.a.a;
import com.ecarx.eas.sdk.device.api.IDayNightMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements IDayNightMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ecarx.eas.sdk.device.a.b f64a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<IDayNightMode.IDayNightChangeCallBack> f65b = Collections.synchronizedSet(new HashSet());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AtomicBoolean f66c = new AtomicBoolean(false);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.ecarx.eas.sdk.device.a.a f67d = new a.AbstractBinderC0009a() { // from class: com.ecarx.eas.sdk.device.b.1
        @Override // com.ecarx.eas.sdk.device.a.a
        public final void a(int i) throws RemoteException {
            if (b.this.f65b.isEmpty()) {
                return;
            }
            Iterator it = b.this.f65b.iterator();
            while (it.hasNext()) {
                ((IDayNightMode.IDayNightChangeCallBack) it.next()).onDayNightModeChange(i);
                Log.i("DayNightMode", String.format(">> onDayNightModeChange(%d)", Integer.valueOf(i)));
            }
        }

        @Override // com.ecarx.eas.sdk.device.a.a
        public final void b(int i) throws RemoteException {
            if (b.this.f65b.isEmpty()) {
                return;
            }
            Iterator it = b.this.f65b.iterator();
            while (it.hasNext()) {
                ((IDayNightMode.IDayNightChangeCallBack) it.next()).onDayNightChanged(i);
                Log.i("DayNightMode", String.format(">> onDayNightChanged(%d)", Integer.valueOf(i)));
            }
        }
    };

    public b(com.ecarx.eas.sdk.device.a.b bVar) {
        this.f64a = bVar;
    }

    public final void a() {
        if (this.f66c.get()) {
            try {
                if (this.f64a.a(this.f67d)) {
                    return;
                }
                this.f66c.set(false);
            } catch (RemoteException e) {
                e.printStackTrace();
                this.f66c.set(false);
            }
        }
    }

    public final void a(com.ecarx.eas.sdk.device.a.b bVar) {
        this.f64a = bVar;
        a();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDayNightMode
    public final int getDayNightMode() {
        com.ecarx.eas.sdk.device.a.b bVar = this.f64a;
        if (bVar == null) {
            return -1;
        }
        try {
            return bVar.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DayNightMode", "Fail to get DayNightMode.", e);
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDayNightMode
    public final int getDayNight() {
        com.ecarx.eas.sdk.device.a.b bVar = this.f64a;
        if (bVar == null) {
            return -1;
        }
        try {
            return bVar.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DayNightMode", "Fail to get DayNight.", e);
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDayNightMode
    public final boolean registerDayNightModeCallBack(IDayNightMode.IDayNightChangeCallBack iDayNightChangeCallBack) {
        boolean zA;
        if (this.f64a == null) {
            return false;
        }
        if (this.f66c.get()) {
            zA = true;
        } else {
            try {
                zA = this.f64a.a(this.f67d);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e("DayNightMode", ">> registerDayNightModeCallBack Fail!!! <<");
                zA = false;
            }
            this.f66c.compareAndSet(false, zA);
        }
        return zA ? this.f65b.add(iDayNightChangeCallBack) : zA;
    }

    @Override // com.ecarx.eas.sdk.device.api.IDayNightMode
    public final boolean unregisterDayNigntModeCallBack(IDayNightMode.IDayNightChangeCallBack iDayNightChangeCallBack) {
        boolean zRemove = this.f65b.remove(iDayNightChangeCallBack);
        if (!this.f65b.isEmpty()) {
            return zRemove;
        }
        try {
            if (this.f64a.b(this.f67d)) {
                this.f66c.set(false);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return zRemove;
    }
}
