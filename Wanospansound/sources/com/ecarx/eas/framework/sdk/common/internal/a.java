package com.ecarx.eas.framework.sdk.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ServiceInfo;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.wrappers.PackageManagerWrapper;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a<T extends IInterface> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected volatile EASFrameworkApiClient.b f25a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile Context f26b;
    private PackageManagerWrapper e;
    private volatile T f;
    private Handler h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private AtomicInteger f28d = new AtomicInteger(0);
    private ServiceConnection g = new ServiceConnection() { // from class: com.ecarx.eas.framework.sdk.common.internal.a.1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("EASFrameworkClient", "<<<<<<onServiceConnected>>>>>> hashCode " + a.this.hashCode());
            Log.d("EASFrameworkClient", "onServiceConnected ComponentName = " + componentName);
            if (a.this.f28d.get() == 5) {
                return;
            }
            a.this.f28d.set(2);
            a aVar = a.this;
            aVar.f = aVar.a(iBinder);
            a.this.g();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            Log.e("EASFrameworkClient", "<<<<<<onServiceDisconnected>>>>>> ");
            Log.d("EASFrameworkClient", "onServiceDisconnected ComponentName = " + componentName);
            if (a.this.f28d.get() == 5) {
                return;
            }
            a.this.f28d.set(3);
            a.this.f = null;
            a.this.h();
            a.b(a.this);
        }

        @Override // android.content.ServiceConnection
        public final void onBindingDied(ComponentName componentName) {
            Log.e("EASFrameworkClient", "<<<<<<onBindingDied>>>>>> ");
            Log.d("EASFrameworkClient", "onBindingDied ComponentName = " + componentName);
            if (a.this.f28d.get() == 5) {
                return;
            }
            a.this.f28d.set(4);
            a.this.f = null;
            a.this.i();
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f27c = new Object();

    protected abstract T a(IBinder iBinder);

    protected abstract String a();

    protected abstract void g();

    protected abstract void h();

    protected abstract void i();

    public abstract ClientType j();

    protected abstract void l();

    public a(Context context, PackageManagerWrapper packageManagerWrapper, EASFrameworkApiClient.b bVar) {
        this.f26b = (Context) i.a(context, "Context must not be null");
        this.e = (PackageManagerWrapper) i.a(packageManagerWrapper, "wrapper must not be null");
        this.f25a = bVar;
        HandlerThread handlerThread = new HandlerThread("BaseEASFrameworkClient", 10);
        handlerThread.start();
        this.h = new Handler(handlerThread.getLooper());
    }

    public final boolean b() {
        Intent intent = new Intent(a());
        intent.setPackage("com.ecarx.sdk.openapi");
        ServiceInfo serviceInfoFindService = this.e.findService(intent);
        if (serviceInfoFindService == null) {
            Log.e("EASFrameworkClient", String.format(">> package=%s, action=%s service not found!!!<<", "com.ecarx.sdk.openapi", a()));
            return false;
        }
        intent.setComponent(new ComponentName(serviceInfoFindService.packageName, serviceInfoFindService.name));
        this.f28d.set(1);
        return a(intent);
    }

    private boolean a(Intent intent) {
        Log.e("EASFrameworkClient", "connect system service start context>>>:" + com.ecarx.eas.framework.sdk.common.wrappers.a.a(this.f26b));
        if (com.ecarx.eas.framework.sdk.common.wrappers.a.a(this.f26b)) {
            try {
                Method method = this.f26b.getClass().getMethod("bindServiceAsUser", Intent.class, ServiceConnection.class, Integer.TYPE, Handler.class, UserHandle.class);
                if (method != null) {
                    Object objInvoke = method.invoke(this.f26b, intent, this.g, 1, this.h, Process.myUserHandle());
                    Log.e("EASFrameworkClient", "connect system service result >>>:" + objInvoke);
                    return Boolean.parseBoolean(objInvoke.toString());
                }
                return b(intent);
            } catch (Exception e) {
                Log.e("EASFrameworkClient", "connect system service fail >>>:" + e.getMessage());
                return b(intent);
            }
        }
        return b(intent);
    }

    private boolean b(Intent intent) {
        boolean zBindService = this.f26b.bindService(intent, this.g, 1);
        if (!zBindService) {
            Object[] objArr = new Object[1];
            objArr[0] = intent.getComponent() != null ? intent.getComponent().flattenToShortString() : "com.ecarx.sdk.openapi/" + a();
            Log.e("EASFrameworkClient", String.format(">> %s is permission <<", objArr));
        }
        return zBindService;
    }

    public final boolean c() {
        return this.f28d.get() == 2;
    }

    public final boolean d() {
        return this.f28d.get() == 1;
    }

    public final boolean e() {
        return this.f28d.get() == 0 || this.f28d.get() == 3 || this.f28d.get() == 4 || this.f28d.get() == 5;
    }

    public final T f() throws DeadObjectException {
        T t;
        synchronized (this.f27c) {
            if (this.f28d.get() == 4) {
                throw new DeadObjectException();
            }
            if (c()) {
                if (this.f != null) {
                    t = this.f;
                } else {
                    throw new IllegalStateException("Client is connected but service is null");
                }
            } else {
                throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called!");
            }
        }
        return t;
    }

    public final void k() {
        this.f28d.set(5);
        this.h = null;
        this.f25a = null;
        this.e = null;
        this.f26b = null;
    }

    static /* synthetic */ void b(a aVar) {
        if (aVar.f26b != null) {
            aVar.f26b.unbindService(aVar.g);
        }
    }
}
