package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface h extends IInterface {
    int a() throws RemoteException;

    int b() throws RemoteException;

    boolean c() throws RemoteException;

    int d() throws RemoteException;

    boolean e() throws RemoteException;

    public static abstract class a extends Binder implements h {
        public static h f() {
            return null;
        }

        public static h a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.IEptState");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof h)) {
                return (h) iInterfaceQueryLocalInterface;
            }
            return new C0031a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEptState");
                int iA = a();
                parcel2.writeNoException();
                parcel2.writeInt(iA);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEptState");
                int iB = b();
                parcel2.writeNoException();
                parcel2.writeInt(iB);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEptState");
                boolean zC = c();
                parcel2.writeNoException();
                parcel2.writeInt(zC ? 1 : 0);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEptState");
                int iD = d();
                parcel2.writeNoException();
                parcel2.writeInt(iD);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.newenergy.IEptState");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEptState");
            boolean zE = e();
            parcel2.writeNoException();
            parcel2.writeInt(zE ? 1 : 0);
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.h$a$a, reason: collision with other inner class name */
        static class C0031a implements h {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f333a;

            C0031a(IBinder iBinder) {
                this.f333a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f333a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.h
            public final int a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEptState");
                    if (!this.f333a.transact(1, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.h
            public final int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEptState");
                    if (!this.f333a.transact(2, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.h
            public final boolean c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEptState");
                    if (!this.f333a.transact(3, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().c();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.h
            public final int d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEptState");
                    if (!this.f333a.transact(4, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().d();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.h
            public final boolean e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEptState");
                    if (!this.f333a.transact(5, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().e();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
