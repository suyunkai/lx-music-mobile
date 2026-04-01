package com.ecarx.eas.framework.sdk.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessage;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IEASFrameworkService extends IInterface {
    void a(String[] strArr) throws RemoteException;

    boolean a(f fVar) throws RemoteException;

    EASFrameworkRetMessage asyncBinderCall(EASFrameworkMessage eASFrameworkMessage, IBinder iBinder) throws RemoteException;

    EASFrameworkRetMessage asyncCall(EASFrameworkMessage eASFrameworkMessage, IEASFrameworkCallback iEASFrameworkCallback) throws RemoteException;

    EASFrameworkRetMessage call(EASFrameworkMessage eASFrameworkMessage) throws RemoteException;

    List<String> getAvailableEASServices() throws RemoteException;

    List<String> getAvailableServices() throws RemoteException;

    IBinder getService(int i, int i2, String str, String str2) throws RemoteException;
}
