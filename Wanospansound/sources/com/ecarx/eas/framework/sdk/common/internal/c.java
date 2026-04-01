package com.ecarx.eas.framework.sdk.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
abstract class c extends Binder implements g {
    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    c() {
    }

    @Override // android.os.Binder
    protected boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            parcel.enforceInterface("com.ecarx.sdk.openapi.RemoteConnectionCallback");
            String string = parcel.readString();
            parcel.readStrongBinder();
            a(string);
            parcel2.writeNoException();
            return true;
        }
        if (i != 2) {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.RemoteConnectionCallback");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel.enforceInterface("com.ecarx.sdk.openapi.RemoteConnectionCallback");
        a(parcel.readString(), parcel.readInt());
        parcel2.writeNoException();
        return true;
    }
}
