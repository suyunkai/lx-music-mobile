package com.ecarx.eas.framework.sdk.common.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.ecarx.sdk.openapi.msg.EASFrameworkCallbackMessage;

/* JADX INFO: loaded from: classes2.dex */
public interface IEASFrameworkCallback extends IInterface {
    void onCall(EASFrameworkCallbackMessage eASFrameworkCallbackMessage) throws RemoteException;
}
