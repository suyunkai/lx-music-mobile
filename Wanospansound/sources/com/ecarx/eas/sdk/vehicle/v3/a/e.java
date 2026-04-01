package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.a.d;

/* JADX INFO: loaded from: classes2.dex */
public interface e extends IInterface {
    void a(int i) throws RemoteException;

    void a(d dVar) throws RemoteException;

    void b(int i) throws RemoteException;

    public static abstract class a extends Binder implements e {
        public static e a() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.newenergy.IChargeStateListener");
        }

        public static e a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.IChargeStateListener");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof e)) {
                return (e) iInterfaceQueryLocalInterface;
            }
            return new C0028a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeStateListener");
                a(d.a.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeStateListener");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.newenergy.IChargeStateListener");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IChargeStateListener");
            b(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.e$a$a, reason: collision with other inner class name */
        static class C0028a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f330a;

            C0028a(IBinder iBinder) {
                this.f330a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f330a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.e
            public final void a(d dVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeStateListener");
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.f330a.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(dVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.e
            public final void a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeStateListener");
                    parcelObtain.writeInt(i);
                    if (!this.f330a.transact(2, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.e
            public final void b(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IChargeStateListener");
                    parcelObtain.writeInt(i);
                    if (!this.f330a.transact(3, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().b(i);
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
