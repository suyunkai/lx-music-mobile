package com.ecarx.eas.sdk.vehicle.api;

import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface a<T> {
    T a();

    void a(ClientType clientType, ServiceVersionInfo serviceVersionInfo);

    boolean a(ClientType clientType, int i);
}
