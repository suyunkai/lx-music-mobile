package com.ecarx.eas.sdk.vehicle.v3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {
    void a(int i, int i2) throws RemoteException;

    public static abstract class a extends Binder implements c {
        public static c a() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.IDashboardHintObserver");
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.IDashboardHintObserver");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof c)) {
                return (c) iInterfaceQueryLocalInterface;
            }
            return new C0057a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.IDashboardHintObserver");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboardHintObserver");
            a(parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.c$a$a, reason: collision with other inner class name */
        static class C0057a implements c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f352a;

            C0057a(IBinder iBinder) {
                this.f352a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f352a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.c
            public final void a(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboardHintObserver");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.f352a.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(i, i2);
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
