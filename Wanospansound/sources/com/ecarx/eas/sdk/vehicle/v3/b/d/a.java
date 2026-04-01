package com.ecarx.eas.sdk.vehicle.v3.b.d;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    boolean a(int i) throws RemoteException;

    boolean a(int i, float f) throws RemoteException;

    float b(int i) throws RemoteException;

    float c(int i) throws RemoteException;

    float d(int i) throws RemoteException;

    float e(int i) throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.d.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0049a extends Binder implements a {
        public static a a() {
            return null;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0050a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    boolean zA = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    boolean zA2 = a(parcel.readInt(), parcel.readFloat());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA2 ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    float fB = b(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(fB);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    float fC = c(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(fC);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    float fD = d(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(fD);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    float fE = e(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(fE);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.d.a$a$a, reason: collision with other inner class name */
        static class C0050a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f347a;

            C0050a(IBinder iBinder) {
                this.f347a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f347a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.d.a
            public final boolean a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    parcelObtain.writeInt(i);
                    if (!this.f347a.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0049a.a() != null) {
                        return AbstractBinderC0049a.a().a(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.d.a
            public final boolean a(int i, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    if (!this.f347a.transact(2, parcelObtain, parcelObtain2, 0) && AbstractBinderC0049a.a() != null) {
                        return AbstractBinderC0049a.a().a(i, f);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.d.a
            public final float b(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    parcelObtain.writeInt(i);
                    if (!this.f347a.transact(3, parcelObtain, parcelObtain2, 0) && AbstractBinderC0049a.a() != null) {
                        return AbstractBinderC0049a.a().b(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.d.a
            public final float c(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    parcelObtain.writeInt(i);
                    if (!this.f347a.transact(4, parcelObtain, parcelObtain2, 0) && AbstractBinderC0049a.a() != null) {
                        return AbstractBinderC0049a.a().c(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.d.a
            public final float d(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    parcelObtain.writeInt(i);
                    if (!this.f347a.transact(5, parcelObtain, parcelObtain2, 0) && AbstractBinderC0049a.a() != null) {
                        return AbstractBinderC0049a.a().d(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.d.a
            public final float e(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.display.IDisplay");
                    parcelObtain.writeInt(i);
                    if (!this.f347a.transact(6, parcelObtain, parcelObtain2, 0) && AbstractBinderC0049a.a() != null) {
                        return AbstractBinderC0049a.a().e(i);
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
