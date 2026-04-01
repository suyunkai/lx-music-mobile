package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface d extends IInterface {
    int a() throws RemoteException;

    int b() throws RemoteException;

    long c() throws RemoteException;

    long d() throws RemoteException;

    long e() throws RemoteException;

    boolean f() throws RemoteException;

    boolean g() throws RemoteException;

    public static abstract class a extends Binder implements d {
        public static d h() {
            return null;
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.IChargeState");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof d)) {
                return (d) iInterfaceQueryLocalInterface;
            }
            return new C0027a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.newenergy.IChargeState");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    int iA = a();
                    parcel2.writeNoException();
                    parcel2.writeInt(iA);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    int iB = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    long jC = c();
                    parcel2.writeNoException();
                    parcel2.writeLong(jC);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    long jD = d();
                    parcel2.writeNoException();
                    parcel2.writeLong(jD);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    long jE = e();
                    parcel2.writeNoException();
                    parcel2.writeLong(jE);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    boolean zF = f();
                    parcel2.writeNoException();
                    parcel2.writeInt(zF ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    boolean zG = g();
                    parcel2.writeNoException();
                    parcel2.writeInt(zG ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.d$a$a, reason: collision with other inner class name */
        static class C0027a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f329a;

            C0027a(IBinder iBinder) {
                this.f329a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f329a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.d
            public final int a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    if (!this.f329a.transact(1, parcelObtain, parcelObtain2, 0) && a.h() != null) {
                        return a.h().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.d
            public final int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    if (!this.f329a.transact(2, parcelObtain, parcelObtain2, 0) && a.h() != null) {
                        return a.h().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.d
            public final long c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    if (!this.f329a.transact(3, parcelObtain, parcelObtain2, 0) && a.h() != null) {
                        return a.h().c();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.d
            public final long d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    if (!this.f329a.transact(4, parcelObtain, parcelObtain2, 0) && a.h() != null) {
                        return a.h().d();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.d
            public final long e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    if (!this.f329a.transact(5, parcelObtain, parcelObtain2, 0) && a.h() != null) {
                        return a.h().e();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.d
            public final boolean f() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    if (!this.f329a.transact(6, parcelObtain, parcelObtain2, 0) && a.h() != null) {
                        return a.h().f();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.d
            public final boolean g() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeState");
                    if (!this.f329a.transact(7, parcelObtain, parcelObtain2, 0) && a.h() != null) {
                        return a.h().g();
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
