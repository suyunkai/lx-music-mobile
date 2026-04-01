package com.wanos.groove.utils;

import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public abstract class GrooveRemote {

    public interface RemoteFunction<V> {
        V call() throws RemoteException;
    }

    public interface RemoteVoidFunction {
        void call() throws RemoteException;
    }

    public static <V> V exec(RemoteFunction<V> remoteFunction) {
        try {
            return remoteFunction.call();
        } catch (Exception e) {
            Log.e("IPC服务异常:", e.toString());
            return null;
        }
    }

    public static void tryExec(RemoteVoidFunction remoteVoidFunction) {
        try {
            remoteVoidFunction.call();
        } catch (RemoteException e) {
            Log.e("IPC服务异常:", e.toString());
        }
    }
}
