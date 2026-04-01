package com.ecarx.eas.sdk.vehicle.v3.b.f;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.b.f.c;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {
    void a(c cVar) throws RemoteException;

    public static abstract class a extends Binder implements b {
        public static b a() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.vehicle.tcu.ITcuCallback");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcuCallback");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof b)) {
                return (b) iInterfaceQueryLocalInterface;
            }
            return new C0055a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.vehicle.tcu.ITcuCallback");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcuCallback");
            a(c.a.a(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.f.b$a$a, reason: collision with other inner class name */
        static class C0055a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f350a;

            C0055a(IBinder iBinder) {
                this.f350a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f350a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.f.b
            public final void a(c cVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.tcu.ITcuCallback");
                    parcelObtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    if (!this.f350a.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(cVar);
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
