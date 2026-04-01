package com.ecarx.eas.sdk.vehicle.v3.b.b.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.b.b.b.b;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    int a(int i) throws RemoteException;

    void a(b bVar) throws RemoteException;

    boolean a(b bVar, int i) throws RemoteException;

    boolean a(b bVar, int i, int i2) throws RemoteException;

    int b(int i) throws RemoteException;

    float c(int i) throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.b.b.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0043a extends Binder implements a {
        public static a a() {
            return null;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0044a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    int iA = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iA);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    int iB = b(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    float fC = c(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(fC);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    boolean zA = a(b.a.a(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    boolean zA2 = a(b.a.a(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA2 ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    a(b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.b.b.a$a$a, reason: collision with other inner class name */
        static class C0044a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f343a;

            C0044a(IBinder iBinder) {
                this.f343a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f343a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.a
            public final int a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    parcelObtain.writeInt(i);
                    if (!this.f343a.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0043a.a() != null) {
                        return AbstractBinderC0043a.a().a(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.a
            public final int b(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    parcelObtain.writeInt(i);
                    if (!this.f343a.transact(2, parcelObtain, parcelObtain2, 0) && AbstractBinderC0043a.a() != null) {
                        return AbstractBinderC0043a.a().b(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.a
            public final float c(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    parcelObtain.writeInt(i);
                    if (!this.f343a.transact(3, parcelObtain, parcelObtain2, 0) && AbstractBinderC0043a.a() != null) {
                        return AbstractBinderC0043a.a().c(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.a
            public final boolean a(b bVar, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.f343a.transact(4, parcelObtain, parcelObtain2, 0) && AbstractBinderC0043a.a() != null) {
                        return AbstractBinderC0043a.a().a(bVar, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.a
            public final boolean a(b bVar, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.f343a.transact(5, parcelObtain, parcelObtain2, 0) && AbstractBinderC0043a.a() != null) {
                        return AbstractBinderC0043a.a().a(bVar, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.a
            public final void a(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.sensor.ISensor");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f343a.transact(6, parcelObtain, parcelObtain2, 0) && AbstractBinderC0043a.a() != null) {
                        AbstractBinderC0043a.a().a(bVar);
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
