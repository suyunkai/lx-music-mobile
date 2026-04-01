package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    float a() throws RemoteException;

    float b() throws RemoteException;

    float c() throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0023a extends Binder implements a {
        public static a d() {
            return null;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IAvgEnergyInfo");
                float fA = a();
                parcel2.writeNoException();
                parcel2.writeFloat(fA);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IAvgEnergyInfo");
                float fB = b();
                parcel2.writeNoException();
                parcel2.writeFloat(fB);
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.newenergy.IAvgEnergyInfo");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IAvgEnergyInfo");
            float fC = c();
            parcel2.writeNoException();
            parcel2.writeFloat(fC);
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.a$a$a, reason: collision with other inner class name */
        static class C0024a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f326a;

            C0024a(IBinder iBinder) {
                this.f326a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f326a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.a
            public final float a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IAvgEnergyInfo");
                    if (!this.f326a.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0023a.d() != null) {
                        return AbstractBinderC0023a.d().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.a
            public final float b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IAvgEnergyInfo");
                    if (!this.f326a.transact(2, parcelObtain, parcelObtain2, 0) && AbstractBinderC0023a.d() != null) {
                        return AbstractBinderC0023a.d().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.a
            public final float c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IAvgEnergyInfo");
                    if (!this.f326a.transact(3, parcelObtain, parcelObtain2, 0) && AbstractBinderC0023a.d() != null) {
                        return AbstractBinderC0023a.d().c();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
