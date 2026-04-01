package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.a.b;
import com.ecarx.eas.sdk.vehicle.v3.a.c;
import com.ecarx.eas.sdk.vehicle.v3.a.g;
import com.ecarx.eas.sdk.vehicle.v3.a.k;

/* JADX INFO: loaded from: classes2.dex */
public interface j extends IInterface {
    int a() throws RemoteException;

    boolean a(b bVar) throws RemoteException;

    int b() throws RemoteException;

    void b(b bVar) throws RemoteException;

    g c() throws RemoteException;

    c d() throws RemoteException;

    k e() throws RemoteException;

    public static abstract class a extends Binder implements j {
        public static j f() {
            return null;
        }

        public static j a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.INewEnergy");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof j)) {
                return (j) iInterfaceQueryLocalInterface;
            }
            return new C0033a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    int iA = a();
                    parcel2.writeNoException();
                    parcel2.writeInt(iA);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    int iB = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    boolean zA = a(b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    b(b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    g gVarC = c();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(gVarC != null ? gVarC.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    c cVarD = d();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(cVarD != null ? cVarD.asBinder() : null);
                    return true;
                case 7:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    k kVarE = e();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(kVarE != null ? kVarE.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.j$a$a, reason: collision with other inner class name */
        static class C0033a implements j {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f335a;

            C0033a(IBinder iBinder) {
                this.f335a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f335a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.j
            public final int a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    if (!this.f335a.transact(1, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.j
            public final int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    if (!this.f335a.transact(2, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.j
            public final boolean a(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f335a.transact(3, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().a(bVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.j
            public final void b(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f335a.transact(4, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        a.f().b(bVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.j
            public final g c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    if (!this.f335a.transact(5, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().c();
                    }
                    parcelObtain2.readException();
                    return g.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.j
            public final c d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    if (!this.f335a.transact(6, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().d();
                    }
                    parcelObtain2.readException();
                    return c.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.j
            public final k e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.INewEnergy");
                    if (!this.f335a.transact(7, parcelObtain, parcelObtain2, 0) && a.f() != null) {
                        return a.f().e();
                    }
                    parcelObtain2.readException();
                    return k.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
