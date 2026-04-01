package com.ecarx.eas.sdk.device;

import android.content.ComponentName;
import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.IServicePool;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.VersionInfo;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.eas.sdk.device.a.c;
import com.ecarx.eas.sdk.device.api.DeviceAPI;
import com.ecarx.eas.sdk.device.api.IDayNightMode;
import com.ecarx.eas.sdk.device.api.IDeviceState;
import com.ecarx.eas.sdk.device.api.InternalDeviceAPI;
import com.ecarx.eas.sdk.device.d;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends DeviceAPI {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Singleton<f> f72d = new Singleton<f>() { // from class: com.ecarx.eas.sdk.device.f.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ f create() {
            return new f();
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile InternalDeviceAPI f73a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile String f74b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile int f75c;
    private EASFrameworkApiClient.IServiceConnectionCallback e = new EASFrameworkApiClient.IServiceConnectionCallback() { // from class: com.ecarx.eas.sdk.device.f.2
        @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
        public final boolean onConnected(String str, ClientType clientType, boolean z) {
            Object[] objArr = new Object[3];
            if (TextUtils.isEmpty(str)) {
                str = "空";
            }
            objArr[0] = str;
            objArr[1] = clientType != null ? clientType.name() : "空";
            objArr[2] = String.valueOf(z);
            Log.e("DeviceAPI", String.format("onConnected(service = %s, clientType = %s, isConnected = %s)", objArr));
            if (!z) {
                return f.this.f73a != null;
            }
            f.a(f.this, clientType);
            return f.this.f73a != null;
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
        public final boolean onConnected(String str, ClientType clientType, ServiceVersionInfo serviceVersionInfo, boolean z) {
            Object[] objArr = new Object[4];
            if (TextUtils.isEmpty(str)) {
                str = "空";
            }
            objArr[0] = str;
            objArr[1] = clientType == null ? "空" : clientType.name();
            objArr[2] = serviceVersionInfo != null ? serviceVersionInfo.toString() : "空";
            objArr[3] = String.valueOf(z);
            Log.e("DeviceAPI", String.format("onConnected(service = %s, clientType = %s, serviceVersionInfo= %s, isConnected = %s)", objArr));
            if (!z) {
                return f.this.f73a != null;
            }
            f fVar = f.this;
            f.a(fVar, clientType, f.a(fVar, serviceVersionInfo));
            return f.this.f73a != null;
        }
    };

    public static f a() {
        return f72d.get();
    }

    @Override // com.ecarx.eas.framework.sdk.ECarXAPIBase
    public final void init(Context context, ECarXApiClient.Callback callback) {
        super.init(context, callback);
        this.f74b = context.getPackageName();
        try {
            EASFrameworkApiClient.getInstance().init(context, callback, "device", this.e);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
        }
    }

    private void b() {
        IServicePool serviceManager;
        IBinder service = null;
        try {
            serviceManager = EASFrameworkApiClient.getInstance().getServiceManager();
        } catch (DeadObjectException e) {
            e.printStackTrace();
            serviceManager = null;
        }
        if (serviceManager == null) {
            Log.e("DeviceAPI", String.format(">> OpenAPIService getServicePool is NULL <<", new Object[0]));
            return;
        }
        try {
            service = serviceManager.getService(Process.myPid(), Process.myUid(), this.f74b, "device");
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        if (service == null) {
            Log.e("DeviceAPI", String.format(">> OpenAPIService device binder is NULL <<", new Object[0]));
            return;
        }
        com.ecarx.eas.sdk.device.a.c cVarA = c.a.a(service);
        if (this.f73a != null && (this.f73a instanceof IApi)) {
            ((IApi) this.f73a).init(cVarA);
        }
        g.a(EASFrameworkApiClient.getInstance().getAppContext()).a(ClientType.OpenAPI, this.f74b);
    }

    private void c() {
        IEASFrameworkService eASServiceManager;
        try {
            eASServiceManager = EASFrameworkApiClient.getInstance().getEASServiceManager();
        } catch (DeadObjectException e) {
            e.printStackTrace();
            eASServiceManager = null;
        }
        if (eASServiceManager == null) {
            Log.e("DeviceAPI", String.format(">> EASFrameworkService is NULL <<", new Object[0]));
            return;
        }
        if (this.f73a != null && (this.f73a instanceof IApi)) {
            ((IApi) this.f73a).init(eASServiceManager);
        }
        g.a(EASFrameworkApiClient.getInstance().getAppContext()).a(ClientType.EASFramework, this.f74b);
    }

    @Override // com.ecarx.eas.framework.sdk.ECarXAPIBase
    public final void release() {
        super.release();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final IDayNightMode getDayNightMode() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getDayNightMode();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getOpenIHUID() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getOpenIHUID();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getOpenVIN() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getOpenVIN();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getVehicleType() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getVehicleType();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final int getOperatorCode() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return -1;
        }
        return this.f73a.getOperatorCode();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getOperatorName() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getOperatorName();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final IDeviceState getDeviceState() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getDeviceState();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getIHUID() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getIHUID();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getVIN() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getVIN();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getDVRID() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getDVRID();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getXDSN() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getXDSN();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getStringValue(String str) {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getStringValue(str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final int getIntValue(String str) {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return -1;
        }
        return this.f73a.getIntValue(str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final boolean getBooleanValue(String str) {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return false;
        }
        return this.f73a.getBooleanValue(str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final long getLongValue(String str) {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return -1L;
        }
        return this.f73a.getLongValue(str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final double getDoubleValue(String str) {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return -1.0d;
        }
        return this.f73a.getDoubleValue(str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getICCID() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getICCID();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getProjectCode() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getProjectCode();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI, com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getSupplierCode() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getSupplierCode();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getVehicleTypeConfig() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        if (this.f75c < 2) {
            Log.e("DeviceAPI", "service version<2  version=" + this.f75c);
            return null;
        }
        return this.f73a.getVehicleTypeConfig();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final List<ComponentName> getSupportedComponents() {
        if (this.f73a == null) {
            Log.e("DeviceAPI", ">> please wait for device init success!!! <<");
            return null;
        }
        return this.f73a.getSupportedComponents();
    }

    static /* synthetic */ void a(f fVar, ClientType clientType) {
        InternalDeviceAPI internalDeviceAPIA;
        IEASFrameworkService eASServiceManager;
        ClientType clientType2 = ClientType.OpenAPI;
        if (clientType == ClientType.EASFramework) {
            clientType2 = ClientType.EASFramework;
        }
        if (d.AnonymousClass1.f71a[clientType2.ordinal()] == 1) {
            internalDeviceAPIA = c.a();
        } else {
            internalDeviceAPIA = h.a();
        }
        fVar.f73a = internalDeviceAPIA;
        if (clientType == ClientType.OpenAPI) {
            fVar.b();
            return;
        }
        if (clientType == ClientType.EASFramework && clientType2 == ClientType.EASFramework) {
            fVar.c();
            return;
        }
        if (clientType == ClientType.EASFramework && clientType2 == ClientType.OpenAPI) {
            IBinder service = null;
            try {
                eASServiceManager = EASFrameworkApiClient.getInstance().getEASServiceManager();
            } catch (DeadObjectException e) {
                e.printStackTrace();
                eASServiceManager = null;
            }
            if (eASServiceManager == null) {
                Log.e("DeviceAPI", String.format(">> EASFrameworkService is NULL <<", new Object[0]));
                return;
            }
            try {
                service = eASServiceManager.getService(Process.myPid(), Process.myUid(), fVar.f74b, "device");
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            if (service == null) {
                Log.e("DeviceAPI", String.format(">> EASFrameworkService device binder is NULL <<", new Object[0]));
                return;
            }
            com.ecarx.eas.sdk.device.a.c cVarA = c.a.a(service);
            if (fVar.f73a != null && (fVar.f73a instanceof IApi)) {
                ((IApi) fVar.f73a).init(cVarA);
            }
            g.a(EASFrameworkApiClient.getInstance().getAppContext()).a(ClientType.EASFramework, fVar.f74b);
        }
    }

    static /* synthetic */ int a(f fVar, ServiceVersionInfo serviceVersionInfo) {
        int i = 1;
        if (serviceVersionInfo == null) {
            return 1;
        }
        List<VersionInfo> list = serviceVersionInfo.versionInfos;
        if (list != null && !list.isEmpty()) {
            int i2 = -1;
            for (VersionInfo versionInfo : list) {
                if (versionInfo.type > i) {
                    i = versionInfo.type;
                    i2 = versionInfo.version;
                }
            }
            fVar.f75c = i2;
        }
        return i;
    }

    static /* synthetic */ void a(f fVar, ClientType clientType, int i) {
        InternalDeviceAPI internalDeviceAPIA;
        if (i == 1 || i == 3) {
            internalDeviceAPIA = c.a();
        } else {
            internalDeviceAPIA = h.a();
        }
        fVar.f73a = internalDeviceAPIA;
        if (i == 1 || i == 3) {
            fVar.b();
        } else {
            fVar.c();
        }
    }
}
