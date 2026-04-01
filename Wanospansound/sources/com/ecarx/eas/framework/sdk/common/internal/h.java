package com.ecarx.eas.framework.sdk.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.wrappers.PackageManagerWrapper;

/* JADX INFO: loaded from: classes2.dex */
class h extends a<IServicePool> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f34b;

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final String a() {
        return "ecarx.intent.action.OpenAPIService";
    }

    public h(Context context, PackageManagerWrapper packageManagerWrapper, EASFrameworkApiClient.b bVar) {
        super(context, packageManagerWrapper, bVar);
        this.f34b = new c() { // from class: com.ecarx.eas.framework.sdk.common.internal.h.1
            @Override // com.ecarx.eas.framework.sdk.common.internal.g
            public final void a(String str) throws RemoteException {
                if (h.this.f25a == null) {
                    return;
                }
                h.this.f25a.a(str);
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.g
            public final void a(String str, int i) throws RemoteException {
                if (h.this.f25a == null) {
                    return;
                }
                h.this.f25a.b(str);
            }
        };
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final void g() {
        this.f25a.a();
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final void l() {
        try {
            f().addRemoteServiceCallback(this.f34b);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final void h() {
        this.f25a.b();
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final void i() {
        this.f25a.c();
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    public final ClientType j() {
        return ClientType.OpenAPI;
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final /* bridge */ /* synthetic */ IInterface a(IBinder iBinder) {
        return j.a(iBinder);
    }
}
