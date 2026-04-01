package com.ecarx.eas.sdk.vehicle.v3.b.b.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {
    void a(int i, float f) throws RemoteException;

    void a(int i, int i2) throws RemoteException;

    void b(int i, int i2) throws RemoteException;

    public static abstract class a extends Binder implements b {
        public static b b() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.vehicle.car.sensor.ISensorListener");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensorListener");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof b)) {
                return (b) iInterfaceQueryLocalInterface;
            }
            return new C0045a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensorListener");
                a(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensorListener");
                a(parcel.readInt(), parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensorListener");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensorListener");
            b(parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.b.b.b$a$a, reason: collision with other inner class name */
        static class C0045a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f344a;

            C0045a(IBinder iBinder) {
                this.f344a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f344a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
            public final void a(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensorListener");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.f344a.transact(1, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        a.b().a(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
            public final void a(int i, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensorListener");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    if (!this.f344a.transact(2, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        a.b().a(i, f);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
            public final void b(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensorListener");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.f344a.transact(3, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        a.b().b(i, i2);
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
