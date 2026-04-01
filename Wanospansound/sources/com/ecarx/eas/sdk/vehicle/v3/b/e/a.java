package com.ecarx.eas.sdk.vehicle.v3.b.e;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    int a() throws RemoteException;

    int b() throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.e.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0051a extends Binder implements a {
        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.drivemode.IDriveMode");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0052a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.drivemode.IDriveMode");
                int iA = a();
                parcel2.writeNoException();
                parcel2.writeInt(iA);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.vehicle.drivemode.IDriveMode");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.drivemode.IDriveMode");
            int iB = b();
            parcel2.writeNoException();
            parcel2.writeInt(iB);
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.e.a$a$a, reason: collision with other inner class name */
        static class C0052a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f348a;

            C0052a(IBinder iBinder) {
                this.f348a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f348a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.e.a
            public final int a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.drivemode.IDriveMode");
                    this.f348a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.e.a
            public final int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.drivemode.IDriveMode");
                    this.f348a.transact(2, parcelObtain, parcelObtain2, 0);
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
