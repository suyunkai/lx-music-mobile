package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.a.h;

/* JADX INFO: loaded from: classes2.dex */
public interface i extends IInterface {
    void a(h hVar) throws RemoteException;

    public static abstract class a extends Binder implements i {
        public static i a() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.newenergy.IEptStateListener");
        }

        public static i a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.IEptStateListener");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof i)) {
                return (i) iInterfaceQueryLocalInterface;
            }
            return new C0032a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.newenergy.IEptStateListener");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEptStateListener");
            a(h.a.a(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.i$a$a, reason: collision with other inner class name */
        static class C0032a implements i {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f334a;

            C0032a(IBinder iBinder) {
                this.f334a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f334a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.i
            public final void a(h hVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEptStateListener");
                    parcelObtain.writeStrongBinder(hVar != null ? hVar.asBinder() : null);
                    if (!this.f334a.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(hVar);
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
