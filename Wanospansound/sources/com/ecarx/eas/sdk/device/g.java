package com.ecarx.eas.sdk.device;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.IServicePool;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.device.a.a.a;
import com.ecarx.eas.sdk.device.a.a.e;
import com.ecarx.eas.sdk.device.api.IDeviceState;
import com.ecarx.eas.sdk.device.api.IDrivingJoyLimit;
import com.ecarx.eas.sdk.device.api.JoyLimitListener;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class g implements IDeviceState {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile g f77c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f78a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile IDrivingJoyLimit f79b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile String f80d;
    private AtomicBoolean e = new AtomicBoolean(false);
    private ECarXApiClient.Callback f = new ECarXApiClient.Callback(this) { // from class: com.ecarx.eas.sdk.device.g.1
        @Override // com.ecarx.eas.sdk.ECarXApiClient.Callback
        public final void onAPIReady(boolean z) {
            Log.d("DeviceStateProxy", String.format(">> onAPIReady(%s) <<", String.valueOf(z)));
        }
    };
    private IDrivingJoyLimit g = new IDrivingJoyLimit() { // from class: com.ecarx.eas.sdk.device.g.3
        @Override // com.ecarx.eas.sdk.device.api.IDrivingJoyLimit
        public final int getState(int i) {
            if (g.this.f79b != null) {
                return g.this.f79b.getState(i);
            }
            Log.e("DeviceStateProxy", String.format(">> IDrivingJoyLimit getState(%d) and Proxy is NULL<<", Integer.valueOf(i)));
            return -1;
        }

        @Override // com.ecarx.eas.sdk.device.api.IDrivingJoyLimit
        public final Object registerListener(int i, JoyLimitListener joyLimitListener) {
            if (g.this.f79b != null) {
                return g.this.f79b.registerListener(i, joyLimitListener);
            }
            Log.e("DeviceStateProxy", ">> IDrivingJoyLimit registerListener <<");
            return null;
        }

        @Override // com.ecarx.eas.sdk.device.api.IDrivingJoyLimit
        public final void unRegisterListener(Object obj) {
            if (g.this.f79b != null) {
                g.this.f79b.unRegisterListener(obj);
            } else {
                Log.e("DeviceStateProxy", ">> IDrivingJoyLimit unRegisterListener <<");
            }
        }
    };

    public static g a(Context context) {
        if (f77c == null) {
            synchronized (g.class) {
                if (f77c == null) {
                    f77c = new g(context);
                }
            }
        }
        return f77c;
    }

    private g(Context context) {
        this.f78a = context.getApplicationContext();
    }

    public final void a(ClientType clientType, String str) {
        try {
            if (this.e.get()) {
                return;
            }
            this.f80d = str;
            EASFrameworkApiClient.getInstance().init(this.f78a, this.f, clientType == ClientType.OpenAPI ? IServiceManager.SERVICE_GKUI : IServiceManager.SERVICE_DRIVE_POLICY, new EASFrameworkApiClient.IServiceConnectionCallback() { // from class: com.ecarx.eas.sdk.device.g.2
                @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
                public final boolean onConnected(String str2, ClientType clientType2, boolean z) {
                    if (z) {
                        g.a(g.this, clientType2);
                    }
                    g.this.e.set(false);
                    return g.this.f79b != null;
                }

                @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
                public final boolean onConnected(String str2, ClientType clientType2, ServiceVersionInfo serviceVersionInfo, boolean z) {
                    if (z) {
                        g.a(g.this, clientType2);
                    }
                    g.this.e.set(false);
                    return g.this.f79b != null;
                }
            });
            this.e.set(true);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.g$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f83a;

        static {
            int[] iArr = new int[ClientType.values().length];
            f83a = iArr;
            try {
                iArr[ClientType.OpenAPI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f83a[ClientType.EASFramework.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceState
    public IDrivingJoyLimit getDrivingJoyLimit() {
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void a(g gVar, ClientType clientType) {
        IServicePool serviceManager;
        IBinder service;
        gVar.f79b = k.a(clientType);
        int i = AnonymousClass4.f83a[clientType.ordinal()];
        IEASFrameworkService eASServiceManager = null;
        IBinder iBinderA = null;
        if (i != 1) {
            if (i != 2) {
                return;
            }
            try {
                eASServiceManager = EASFrameworkApiClient.getInstance().getEASServiceManager();
            } catch (DeadObjectException e) {
                e.printStackTrace();
            }
            if (eASServiceManager == null) {
                Log.e("DeviceStateProxy", String.format(">> EASFrameworkService is NULL <<", new Object[0]));
                return;
            } else {
                if (gVar.f79b == null || !(gVar.f79b instanceof IApi)) {
                    return;
                }
                ((IApi) gVar.f79b).init(eASServiceManager);
                return;
            }
        }
        try {
            serviceManager = EASFrameworkApiClient.getInstance().getServiceManager();
        } catch (DeadObjectException e2) {
            e2.printStackTrace();
            serviceManager = null;
        }
        if (serviceManager == null) {
            Log.e("DeviceStateProxy", String.format(">> OpenAPIService getServicePool is NULL <<", new Object[0]));
            return;
        }
        try {
            service = serviceManager.getService(Process.myPid(), Process.myUid(), gVar.f80d, IServiceManager.SERVICE_GKUI);
        } catch (RemoteException e3) {
            e3.printStackTrace();
            service = null;
        }
        if (service == null) {
            Log.e("DeviceStateProxy", String.format(">> OpenAPIService GKUIService binder is NULL <<", new Object[0]));
            return;
        }
        com.ecarx.eas.sdk.device.a.a.e eVarA = e.a.a(service);
        if (eVarA == null) {
            ((IApi) gVar.f79b).init(null);
            return;
        }
        try {
            iBinderA = eVarA.a(300);
        } catch (RemoteException e4) {
            e4.printStackTrace();
        }
        if (iBinderA == null) {
            Log.e("DeviceStateProxy", String.format(">> OpenAPIService GKUIService DrivePolicy binder is NULL <<", new Object[0]));
        }
        ((IApi) gVar.f79b).init(a.AbstractBinderC0011a.a(iBinderA));
    }
}
