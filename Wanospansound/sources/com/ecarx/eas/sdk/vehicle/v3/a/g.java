package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.a.h;
import com.ecarx.eas.sdk.vehicle.v3.a.i;

/* JADX INFO: loaded from: classes2.dex */
public interface g extends IInterface {
    h a() throws RemoteException;

    void a(int i) throws RemoteException;

    void a(i iVar) throws RemoteException;

    void a(boolean z) throws RemoteException;

    int b() throws RemoteException;

    void b(int i) throws RemoteException;

    void b(i iVar) throws RemoteException;

    void b(boolean z) throws RemoteException;

    int c() throws RemoteException;

    int d() throws RemoteException;

    public static abstract class a extends Binder implements g {
        public static g e() {
            return null;
        }

        public static g a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof g)) {
                return (g) iInterfaceQueryLocalInterface;
            }
            return new C0030a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.newenergy.IEpt");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    h hVarA = a();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(hVarA != null ? hVarA.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    int iB = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    int iC = c();
                    parcel2.writeNoException();
                    parcel2.writeInt(iC);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    int iD = d();
                    parcel2.writeNoException();
                    parcel2.writeInt(iD);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    a(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    b(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    b(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    a(i.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.IEpt");
                    b(i.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.g$a$a, reason: collision with other inner class name */
        static class C0030a implements g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f332a;

            C0030a(IBinder iBinder) {
                this.f332a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f332a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final h a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    if (!this.f332a.transact(1, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        return a.e().a();
                    }
                    parcelObtain2.readException();
                    return h.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final void a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    parcelObtain.writeInt(i);
                    if (!this.f332a.transact(2, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        a.e().a(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    if (!this.f332a.transact(3, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        return a.e().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final int c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    if (!this.f332a.transact(4, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        return a.e().c();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final int d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    if (!this.f332a.transact(5, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        return a.e().d();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final void a(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.f332a.transact(6, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        a.e().a(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final void b(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    parcelObtain.writeInt(i);
                    if (!this.f332a.transact(7, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        a.e().b(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final void b(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.f332a.transact(8, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        a.e().b(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final void a(i iVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    parcelObtain.writeStrongBinder(iVar != null ? iVar.asBinder() : null);
                    if (!this.f332a.transact(9, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        a.e().a(iVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.g
            public final void b(i iVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.IEpt");
                    parcelObtain.writeStrongBinder(iVar != null ? iVar.asBinder() : null);
                    if (!this.f332a.transact(10, parcelObtain, parcelObtain2, 0) && a.e() != null) {
                        a.e().b(iVar);
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
