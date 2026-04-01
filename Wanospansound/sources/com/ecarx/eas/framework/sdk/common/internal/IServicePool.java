package com.ecarx.eas.framework.sdk.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IServicePool extends IInterface {
    boolean addRemoteServiceCallback(g gVar) throws RemoteException;

    List<String> getAvailableServices() throws RemoteException;

    IBinder getService(int i, int i2, String str, String str2) throws RemoteException;

    boolean removeRemoteServiceCallback(g gVar) throws RemoteException;
}
