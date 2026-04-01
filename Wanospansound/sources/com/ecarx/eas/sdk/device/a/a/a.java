package com.ecarx.eas.sdk.device.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.device.a.a.b;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    b a() throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0011a extends Binder implements a {
        public static a b() {
            return null;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("ecarx.xsf.gkuiservice.policy.IDrivePolicy");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0012a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("ecarx.xsf.gkuiservice.policy.IDrivePolicy");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("ecarx.xsf.gkuiservice.policy.IDrivePolicy");
            b bVarA = a();
            parcel2.writeNoException();
            parcel2.writeStrongBinder(bVarA != null ? bVarA.asBinder() : null);
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.a.a$a$a, reason: collision with other inner class name */
        private static class C0012a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f56a;

            C0012a(IBinder iBinder) {
                this.f56a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f56a;
            }

            @Override // com.ecarx.eas.sdk.device.a.a.a
            public final b a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("ecarx.xsf.gkuiservice.policy.IDrivePolicy");
                    if (!this.f56a.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0011a.b() != null) {
                        return AbstractBinderC0011a.b().a();
                    }
                    parcelObtain2.readException();
                    return b.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
