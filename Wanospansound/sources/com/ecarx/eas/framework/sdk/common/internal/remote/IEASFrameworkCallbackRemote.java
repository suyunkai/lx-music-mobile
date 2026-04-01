package com.ecarx.eas.framework.sdk.common.internal.remote;

import android.os.IInterface;
import android.os.RemoteException;
import com.ecarx.sdk.openapi.msg.EASFrameworkRemoteCallbackMessage;

/* JADX INFO: loaded from: classes2.dex */
public interface IEASFrameworkCallbackRemote extends IInterface {
    void onCall(EASFrameworkRemoteCallbackMessage eASFrameworkRemoteCallbackMessage) throws RemoteException;
}
