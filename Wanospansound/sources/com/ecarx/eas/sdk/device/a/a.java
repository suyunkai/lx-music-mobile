package com.ecarx.eas.sdk.device.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    void a(int i) throws RemoteException;

    void b(int i) throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0009a extends Binder implements a {
        public static a a() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC0009a() {
            attachInterface(this, "com.ecarx.sdk.openapi.IDayNightChangeCallBack");
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.IDayNightChangeCallBack");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0010a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.IDayNightChangeCallBack");
                a(parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.IDayNightChangeCallBack");
                b(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.IDayNightChangeCallBack");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.a$a$a, reason: collision with other inner class name */
        private static class C0010a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f55a;

            C0010a(IBinder iBinder) {
                this.f55a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f55a;
            }

            @Override // com.ecarx.eas.sdk.device.a.a
            public final void a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDayNightChangeCallBack");
                    parcelObtain.writeInt(i);
                    if (this.f55a.transact(1, parcelObtain, null, 1) || AbstractBinderC0009a.a() == null) {
                        return;
                    }
                    AbstractBinderC0009a.a().a(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.a
            public final void b(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDayNightChangeCallBack");
                    parcelObtain.writeInt(i);
                    if (this.f55a.transact(2, parcelObtain, null, 1) || AbstractBinderC0009a.a() == null) {
                        return;
                    }
                    AbstractBinderC0009a.a().b(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }
    }
}
