package com.ecarx.eas.sdk.device;

import android.content.ComponentName;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.sdk.device.api.IDayNightMode;
import com.ecarx.eas.sdk.device.api.IDeviceState;
import com.ecarx.eas.sdk.device.api.InternalDeviceAPI;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class c extends IApi<com.ecarx.eas.sdk.device.a.c> implements InternalDeviceAPI {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Singleton<c> f69b = new Singleton<c>() { // from class: com.ecarx.eas.sdk.device.c.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ c create() {
            return new c();
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f70a;

    c() {
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.IApi
    public final /* synthetic */ void init(IInterface iInterface) {
        b bVar;
        super.init((com.ecarx.eas.sdk.device.a.c) iInterface);
        if (!this.mAliveFlag.get() || (bVar = this.f70a) == null) {
            return;
        }
        try {
            bVar.a(((com.ecarx.eas.sdk.device.a.c) this.mService).i());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static InternalDeviceAPI a() {
        return f69b.get();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getIHUID() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).a();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getIHUID", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getVIN() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).b();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getVIN", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getDVRID() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).c();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getVIN", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getXDSN() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).d();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getXDSN", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getStringValue(String str) {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).a(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getStringValue", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final int getIntValue(String str) {
        if (!isAlive()) {
            return -1;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).b(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getIntValue", e);
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final boolean getBooleanValue(String str) {
        if (!isAlive()) {
            return false;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).c(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getBooleanValue", e);
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final long getLongValue(String str) {
        if (!isAlive()) {
            return -1L;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).d(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getLongValue", e);
            return -1L;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final double getDoubleValue(String str) {
        if (!isAlive()) {
            return -1.0d;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).e(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getDoubleValue", e);
            return -1.0d;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getICCID() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).e();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getICCID", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getProjectCode() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).g();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getProjectCode", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final IDayNightMode getDayNightMode() {
        if (!isAlive()) {
            return null;
        }
        if (this.f70a == null) {
            try {
                this.f70a = new b(((com.ecarx.eas.sdk.device.a.c) this.mService).i());
            } catch (RemoteException e) {
                Log.e("DeviceAPI", "Fail to create OpenDayNightModeWrapper", e);
                e.printStackTrace();
            }
        }
        return this.f70a;
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getOpenIHUID() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).m();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getOpenIHUID", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getOpenVIN() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).n();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getOpenVIN", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getVehicleType() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).f();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getVehicleType", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final int getOperatorCode() {
        if (!isAlive()) {
            return -1;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).k();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getVehicleType", e);
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getOperatorName() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).l();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getVehicleType", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final IDeviceState getDeviceState() {
        return g.a(EASFrameworkApiClient.getInstance().getAppContext());
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI, com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getSupplierCode() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).h();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getSupplierCode", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getVehicleTypeConfig() {
        if (!isAlive()) {
            return null;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).q();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getSupplierCode", e);
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final List<ComponentName> getSupportedComponents() {
        if (!isAlive()) {
            return Collections.emptyList();
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.c) this.mService).j();
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", "Fail to getSupplierCode", e);
            return Collections.emptyList();
        }
    }
}
