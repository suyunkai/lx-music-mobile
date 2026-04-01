package com.ecarx.eas.framework.sdk.common.internal.remote;

import android.os.IInterface;
import android.os.RemoteException;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessageRemote;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessageRemote;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IEASFrameworkRemoteService extends IInterface {
    void a(String[] strArr) throws RemoteException;

    boolean a(d dVar) throws RemoteException;

    EASFrameworkRetMessageRemote asyncCall(EASFrameworkMessageRemote eASFrameworkMessageRemote, IEASFrameworkCallbackRemote iEASFrameworkCallbackRemote) throws RemoteException;

    List<String> getRemoteServices() throws RemoteException;

    EASFrameworkRetMessageRemote registerListener(EASFrameworkMessageRemote eASFrameworkMessageRemote, IEASFrameworkCallbackRemote iEASFrameworkCallbackRemote) throws RemoteException;

    EASFrameworkRetMessageRemote unregisterListener(EASFrameworkMessageRemote eASFrameworkMessageRemote, String str) throws RemoteException;
}
