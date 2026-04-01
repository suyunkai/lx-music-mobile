package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.a.l;

/* JADX INFO: loaded from: classes2.dex */
public interface k extends IInterface {
    int a() throws RemoteException;

    void a(int i) throws RemoteException;

    void a(l lVar) throws RemoteException;

    void b(l lVar) throws RemoteException;

    public static abstract class a extends Binder implements k {
        public static k b() {
            return null;
        }

        public static k a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.IPHEV");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof k)) {
                return (k) iInterfaceQueryLocalInterface;
            }
            return new C0034a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IPHEV");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IPHEV");
                int iA = a();
                parcel2.writeNoException();
                parcel2.writeInt(iA);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IPHEV");
                a(l.a.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.newenergy.IPHEV");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IPHEV");
            b(l.a.a(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.k$a$a, reason: collision with other inner class name */
        static class C0034a implements k {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f336a;

            C0034a(IBinder iBinder) {
                this.f336a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f336a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.k
            public final void a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IPHEV");
                    parcelObtain.writeInt(i);
                    if (!this.f336a.transact(1, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        a.b().a(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.k
            public final int a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IPHEV");
                    if (!this.f336a.transact(2, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        return a.b().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.k
            public final void a(l lVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IPHEV");
                    parcelObtain.writeStrongBinder(lVar != null ? lVar.asBinder() : null);
                    if (!this.f336a.transact(3, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        a.b().a(lVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.k
            public final void b(l lVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IPHEV");
                    parcelObtain.writeStrongBinder(lVar != null ? lVar.asBinder() : null);
                    if (!this.f336a.transact(4, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        a.b().b(lVar);
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
