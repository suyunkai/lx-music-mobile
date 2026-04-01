package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.a.a;
import com.ecarx.eas.sdk.vehicle.v3.a.f;

/* JADX INFO: loaded from: classes2.dex */
public interface l extends IInterface {
    void a(com.ecarx.eas.sdk.vehicle.v3.a.a aVar) throws RemoteException;

    void a(f fVar) throws RemoteException;

    public static abstract class a extends Binder implements l {
        public static l a() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.newenergy.IPHEVListener");
        }

        public static l a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.IPHEVListener");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof l)) {
                return (l) iInterfaceQueryLocalInterface;
            }
            return new C0035a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            com.ecarx.eas.sdk.vehicle.v3.a.a c0024a = null;
            f c0029a = null;
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IPHEVListener");
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.IAvgEnergyInfo");
                    if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof com.ecarx.eas.sdk.vehicle.v3.a.a)) {
                        c0024a = (com.ecarx.eas.sdk.vehicle.v3.a.a) iInterfaceQueryLocalInterface;
                    } else {
                        c0024a = new a.AbstractBinderC0023a.C0024a(strongBinder);
                    }
                }
                a(c0024a);
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.newenergy.IPHEVListener");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IPHEVListener");
            IBinder strongBinder2 = parcel.readStrongBinder();
            if (strongBinder2 != null) {
                IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.IDrivingInfo");
                if (iInterfaceQueryLocalInterface2 != null && (iInterfaceQueryLocalInterface2 instanceof f)) {
                    c0029a = (f) iInterfaceQueryLocalInterface2;
                } else {
                    c0029a = new f.a.C0029a(strongBinder2);
                }
            }
            a(c0029a);
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.l$a$a, reason: collision with other inner class name */
        static class C0035a implements l {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f337a;

            C0035a(IBinder iBinder) {
                this.f337a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f337a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.l
            public final void a(com.ecarx.eas.sdk.vehicle.v3.a.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IPHEVListener");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f337a.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(aVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.l
            public final void a(f fVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IPHEVListener");
                    parcelObtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                    if (!this.f337a.transact(2, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(fVar);
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
