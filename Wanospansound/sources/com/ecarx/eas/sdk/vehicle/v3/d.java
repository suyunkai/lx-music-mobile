package com.ecarx.eas.sdk.vehicle.v3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface d extends IInterface {
    void a(int i, int i2) throws RemoteException;

    public static abstract class a extends Binder implements d {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.havc.IFunctionValueWatcher");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.havc.IFunctionValueWatcher");
                parcel.readInt();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.havc.IFunctionValueWatcher");
                int i3 = parcel.readInt();
                parcel.readInt();
                a(i3, parcel.readInt());
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.havc.IFunctionValueWatcher");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.havc.IFunctionValueWatcher");
            parcel.readInt();
            parcel.readInt();
            parcel.readInt();
            return true;
        }
    }
}
