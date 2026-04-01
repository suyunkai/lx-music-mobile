package com.ecarx.eas.sdk.device.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface d extends IInterface {
    void a(String str, int i) throws RemoteException;

    public static abstract class a extends Binder implements d {
        public static d a() {
            return null;
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.IEcarxIdWatcher");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof d)) {
                return (d) iInterfaceQueryLocalInterface;
            }
            return new C0019a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.IEcarxIdWatcher");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.IEcarxIdWatcher");
            a(parcel.readString(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.d$a$a, reason: collision with other inner class name */
        private static class C0019a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f63a;

            C0019a(IBinder iBinder) {
                this.f63a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f63a;
            }

            @Override // com.ecarx.eas.sdk.device.a.d
            public final void a(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IEcarxIdWatcher");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.f63a.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(str, i);
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
