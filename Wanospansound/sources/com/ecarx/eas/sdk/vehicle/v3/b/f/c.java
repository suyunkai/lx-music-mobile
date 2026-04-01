package com.ecarx.eas.sdk.vehicle.v3.b.f;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {
    int a() throws RemoteException;

    int b() throws RemoteException;

    int c() throws RemoteException;

    public static abstract class a extends Binder implements c {
        public static c d() {
            return null;
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcuState");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof c)) {
                return (c) iInterfaceQueryLocalInterface;
            }
            return new C0056a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcuState");
                int iA = a();
                parcel2.writeNoException();
                parcel2.writeInt(iA);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcuState");
                int iB = b();
                parcel2.writeNoException();
                parcel2.writeInt(iB);
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.vehicle.tcu.ITcuState");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcuState");
            int iC = c();
            parcel2.writeNoException();
            parcel2.writeInt(iC);
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.f.c$a$a, reason: collision with other inner class name */
        static class C0056a implements c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f351a;

            C0056a(IBinder iBinder) {
                this.f351a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f351a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.f.c
            public final int a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.tcu.ITcuState");
                    if (!this.f351a.transact(1, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.f.c
            public final int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.tcu.ITcuState");
                    if (!this.f351a.transact(2, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.f.c
            public final int c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.tcu.ITcuState");
                    if (!this.f351a.transact(3, parcelObtain, parcelObtain2, 0) && a.d() != null) {
                        return a.d().c();
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
