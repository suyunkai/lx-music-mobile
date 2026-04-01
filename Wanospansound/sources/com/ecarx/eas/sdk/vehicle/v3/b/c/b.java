package com.ecarx.eas.sdk.vehicle.v3.b.c;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {
    void a(double d2) throws RemoteException;

    void a(double d2, int i) throws RemoteException;

    void a(int i) throws RemoteException;

    public static abstract class a extends Binder implements b {
        public static b a() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleSpeedObserver");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleSpeedObserver");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof b)) {
                return (b) iInterfaceQueryLocalInterface;
            }
            return new C0048a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleSpeedObserver");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleSpeedObserver");
                a(parcel.readDouble());
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleSpeedObserver");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleSpeedObserver");
            a(parcel.readDouble(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.c.b$a$a, reason: collision with other inner class name */
        static class C0048a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f346a;

            C0048a(IBinder iBinder) {
                this.f346a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f346a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.c.b
            public final void a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleSpeedObserver");
                    parcelObtain.writeInt(i);
                    if (!this.f346a.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.c.b
            public final void a(double d2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleSpeedObserver");
                    parcelObtain.writeDouble(d2);
                    if (!this.f346a.transact(2, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(d2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.c.b
            public final void a(double d2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleSpeedObserver");
                    parcelObtain.writeDouble(d2);
                    parcelObtain.writeInt(i);
                    if (!this.f346a.transact(3, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(d2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
