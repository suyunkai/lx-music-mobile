package com.ecarx.eas.sdk.vehicle.v3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.b.b.a.a;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    int a(int i) throws RemoteException;

    com.ecarx.eas.sdk.vehicle.v3.b.b.a.a a() throws RemoteException;

    float b(int i) throws RemoteException;

    String c(int i) throws RemoteException;

    int d(int i) throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0021a extends Binder implements a {
        public static a b() {
            return null;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.ICarInfo");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0022a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.ICarInfo");
                int iA = a(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(iA);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.ICarInfo");
                float fB = b(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeFloat(fB);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.ICarInfo");
                String strC = c(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeString(strC);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.ICarInfo");
                com.ecarx.eas.sdk.vehicle.v3.b.b.a.a aVarA = a();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(aVarA != null ? aVarA.asBinder() : null);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.ICarInfo");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.ICarInfo");
            int iD = d(parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeInt(iD);
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a$a$a, reason: collision with other inner class name */
        static class C0022a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f325a;

            C0022a(IBinder iBinder) {
                this.f325a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f325a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a
            public final int a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.ICarInfo");
                    parcelObtain.writeInt(i);
                    if (!this.f325a.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0021a.b() != null) {
                        return AbstractBinderC0021a.b().a(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a
            public final float b(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.ICarInfo");
                    parcelObtain.writeInt(i);
                    if (!this.f325a.transact(2, parcelObtain, parcelObtain2, 0) && AbstractBinderC0021a.b() != null) {
                        return AbstractBinderC0021a.b().b(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a
            public final String c(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.ICarInfo");
                    parcelObtain.writeInt(i);
                    if (!this.f325a.transact(3, parcelObtain, parcelObtain2, 0) && AbstractBinderC0021a.b() != null) {
                        return AbstractBinderC0021a.b().c(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a
            public final com.ecarx.eas.sdk.vehicle.v3.b.b.a.a a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.ICarInfo");
                    if (!this.f325a.transact(4, parcelObtain, parcelObtain2, 0) && AbstractBinderC0021a.b() != null) {
                        return AbstractBinderC0021a.b().a();
                    }
                    parcelObtain2.readException();
                    return a.AbstractBinderC0040a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a
            public final int d(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.ICarInfo");
                    parcelObtain.writeInt(i);
                    if (!this.f325a.transact(5, parcelObtain, parcelObtain2, 0) && AbstractBinderC0021a.b() != null) {
                        return AbstractBinderC0021a.b().d(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
