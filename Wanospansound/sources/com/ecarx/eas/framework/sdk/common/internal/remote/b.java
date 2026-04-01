package com.ecarx.eas.framework.sdk.common.internal.remote;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.wrappers.PackageManagerWrapper;

/* JADX INFO: loaded from: classes2.dex */
final class b extends com.ecarx.eas.framework.sdk.common.internal.a<IEASFrameworkRemoteService> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private d f41b;

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final String a() {
        return "com.ecarx.easframework.intent.action.EASFRAMEWORKREMOTE";
    }

    public b(Context context, PackageManagerWrapper packageManagerWrapper, EASFrameworkApiClient.b bVar) {
        super(context, packageManagerWrapper, bVar);
        this.f41b = new a() { // from class: com.ecarx.eas.framework.sdk.common.internal.remote.b.1
            @Override // com.ecarx.eas.framework.sdk.common.internal.remote.d
            public final void a(String str) throws RemoteException {
                if (b.this.f25a == null) {
                    return;
                }
                b.this.f25a.a(str);
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.remote.d
            public final void a(String str, int i) throws RemoteException {
                if (b.this.f25a == null) {
                    return;
                }
                b.this.f25a.b(str);
            }
        };
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
        this.f25a.c();
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    public final ClientType j() {
        return ClientType.EASFramework;
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final void l() {
        try {
            f().a(this.f41b);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.a
    protected final /* bridge */ /* synthetic */ IInterface a(IBinder iBinder) {
        return c.a(iBinder);
    }
}
