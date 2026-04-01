package com.ecarx.eas.framework.sdk.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.wrappers.PackageManagerWrapper;

/* JADX INFO: loaded from: classes2.dex */
final class d extends a<IEASFrameworkService> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f30b;

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final String a() {
        return "com.ecarx.easframework.intent.action.EASFRAMEWORK";
    }

    public d(Context context, PackageManagerWrapper packageManagerWrapper, EASFrameworkApiClient.b bVar) {
        super(context, packageManagerWrapper, bVar);
        this.f30b = new b() { // from class: com.ecarx.eas.framework.sdk.common.internal.d.1
            @Override // com.ecarx.eas.framework.sdk.common.internal.f
            public final void a(String str) throws RemoteException {
                if (d.this.f25a == null) {
                    return;
                }
                d.this.f25a.a(str);
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.f
            public final void a(String str, int i) throws RemoteException {
                if (d.this.f25a == null) {
                    return;
                }
                d.this.f25a.b(str);
            }
        };
        Log.e(Constant.TAG, "EASFrameworkClient()");
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final void g() {
        this.f25a.a();
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final void h() {
        this.f25a.b();
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final void i() {
        if (this.f25a != null) {
            this.f25a.c();
        }
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    public final ClientType j() {
        return ClientType.EASFramework;
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final void l() {
        try {
            f().a(this.f30b);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final /* bridge */ /* synthetic */ IInterface a(IBinder iBinder) {
        return e.a(iBinder);
    }
}
