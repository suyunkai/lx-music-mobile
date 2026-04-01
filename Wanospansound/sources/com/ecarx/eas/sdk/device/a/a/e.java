package com.ecarx.eas.sdk.device.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface e extends IInterface {
    IBinder a(int i) throws RemoteException;

    public static abstract class a extends Binder implements e {
        public static e a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("ecarx.xsf.gkuiservice.IServiceManager");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof e)) {
                return (e) iInterfaceQueryLocalInterface;
            }
            return new C0016a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("ecarx.xsf.gkuiservice.IServiceManager");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("ecarx.xsf.gkuiservice.IServiceManager");
            IBinder iBinderA = a(parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeStrongBinder(iBinderA);
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.a.e$a$a, reason: collision with other inner class name */
        private static class C0016a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f60a;

            C0016a(IBinder iBinder) {
                this.f60a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f60a;
            }

            @Override // com.ecarx.eas.sdk.device.a.a.e
            public final IBinder a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("ecarx.xsf.gkuiservice.IServiceManager");
                    parcelObtain.writeInt(i);
                    this.f60a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
