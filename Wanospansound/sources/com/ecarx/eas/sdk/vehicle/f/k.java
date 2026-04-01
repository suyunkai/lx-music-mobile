package com.ecarx.eas.sdk.vehicle.f;

import android.os.IInterface;
import android.util.Log;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.VersionInfo;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k<T extends IInterface> extends IApi<T> implements com.ecarx.eas.sdk.vehicle.api.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ServiceVersionInfo f218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ClientType f219b;

    @Override // com.ecarx.eas.sdk.vehicle.api.a
    public final void a(ClientType clientType, ServiceVersionInfo serviceVersionInfo) {
        this.f219b = clientType;
        this.f218a = serviceVersionInfo;
        Log.i("IApiProxy", "holdServiceVersion mClientType: " + this.f219b + ", mServiceInfo: " + this.f218a);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.a
    public final boolean a(ClientType clientType, int i) {
        boolean z;
        ServiceVersionInfo serviceVersionInfo;
        if (this.mService == null || this.f219b != clientType || (serviceVersionInfo = this.f218a) == null || serviceVersionInfo.versionInfos == null || this.f218a.versionInfos.size() <= 0) {
            z = false;
        } else {
            int i2 = this.f219b == ClientType.OpenAPI ? 3 : 4;
            for (VersionInfo versionInfo : this.f218a.versionInfos) {
                if (i2 == versionInfo.type && versionInfo.version >= i) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        Log.d("IApiProxy", "isServiceSupported type: " + clientType + ", version: " + i + ", mClientType: " + this.f219b + ", supported: " + z);
        return z;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.a
    public final /* bridge */ /* synthetic */ Object a() {
        return this.mService;
    }
}
