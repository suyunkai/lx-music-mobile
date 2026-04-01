package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface f extends IInterface {
    int a() throws RemoteException;

    int b() throws RemoteException;

    long c() throws RemoteException;

    int d() throws RemoteException;

    int e() throws RemoteException;

    float f() throws RemoteException;

    public static abstract class a extends Binder implements f {
        public static f g() {
            return null;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    int iA = a();
                    parcel2.writeNoException();
                    parcel2.writeInt(iA);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    int iB = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    long jC = c();
                    parcel2.writeNoException();
                    parcel2.writeLong(jC);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    int iD = d();
                    parcel2.writeNoException();
                    parcel2.writeInt(iD);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    int iE = e();
                    parcel2.writeNoException();
                    parcel2.writeInt(iE);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    float f = f();
                    parcel2.writeNoException();
                    parcel2.writeFloat(f);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.f$a$a, reason: collision with other inner class name */
        static class C0029a implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f331a;

            C0029a(IBinder iBinder) {
                this.f331a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f331a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.f
            public final int a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    if (!this.f331a.transact(1, parcelObtain, parcelObtain2, 0) && a.g() != null) {
                        return a.g().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.f
            public final int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    if (!this.f331a.transact(2, parcelObtain, parcelObtain2, 0) && a.g() != null) {
                        return a.g().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.f
            public final long c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    if (!this.f331a.transact(3, parcelObtain, parcelObtain2, 0) && a.g() != null) {
                        return a.g().c();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.f
            public final int d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    if (!this.f331a.transact(4, parcelObtain, parcelObtain2, 0) && a.g() != null) {
                        return a.g().d();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.f
            public final int e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    if (!this.f331a.transact(5, parcelObtain, parcelObtain2, 0) && a.g() != null) {
                        return a.g().e();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.f
            public final float f() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                    if (!this.f331a.transact(6, parcelObtain, parcelObtain2, 0) && a.g() != null) {
                        return a.g().f();
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
