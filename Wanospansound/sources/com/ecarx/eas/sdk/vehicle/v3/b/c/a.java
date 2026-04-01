package com.ecarx.eas.sdk.vehicle.v3.b.c;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    void a(int i) throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.c.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0046a extends Binder implements a {
        public static a a() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC0046a() {
            attachInterface(this, "com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleACCStatusObserver");
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleACCStatusObserver");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0047a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleACCStatusObserver");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleACCStatusObserver");
            a(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.c.a$a$a, reason: collision with other inner class name */
        static class C0047a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f345a;

            C0047a(IBinder iBinder) {
                this.f345a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f345a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.c.a
            public final void a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.dashboard.IVehicleACCStatusObserver");
                    parcelObtain.writeInt(i);
                    if (!this.f345a.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0046a.a() != null) {
                        AbstractBinderC0046a.a().a(i);
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
