package com.ecarx.eas.sdk.device.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.device.a.a;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {
    int a() throws RemoteException;

    boolean a(com.ecarx.eas.sdk.device.a.a aVar) throws RemoteException;

    int b() throws RemoteException;

    boolean b(com.ecarx.eas.sdk.device.a.a aVar) throws RemoteException;

    public static abstract class a extends Binder implements b {
        public static b c() {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.IDayNightMode");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.IDayNightMode");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof b)) {
                return (b) iInterfaceQueryLocalInterface;
            }
            return new C0017a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.IDayNightMode");
                int iA = a();
                parcel2.writeNoException();
                parcel2.writeInt(iA);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.IDayNightMode");
                int iB = b();
                parcel2.writeNoException();
                parcel2.writeInt(iB);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.IDayNightMode");
                boolean zA = a(a.AbstractBinderC0009a.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zA ? 1 : 0);
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.IDayNightMode");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.IDayNightMode");
            boolean zB = b(a.AbstractBinderC0009a.a(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeInt(zB ? 1 : 0);
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.b$a$a, reason: collision with other inner class name */
        private static class C0017a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f61a;

            C0017a(IBinder iBinder) {
                this.f61a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f61a;
            }

            @Override // com.ecarx.eas.sdk.device.a.b
            public final int a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDayNightMode");
                    if (!this.f61a.transact(1, parcelObtain, parcelObtain2, 0) && a.c() != null) {
                        return a.c().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.b
            public final int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDayNightMode");
                    if (!this.f61a.transact(2, parcelObtain, parcelObtain2, 0) && a.c() != null) {
                        return a.c().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.b
            public final boolean a(com.ecarx.eas.sdk.device.a.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDayNightMode");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f61a.transact(3, parcelObtain, parcelObtain2, 0) && a.c() != null) {
                        return a.c().a(aVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.b
            public final boolean b(com.ecarx.eas.sdk.device.a.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDayNightMode");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f61a.transact(4, parcelObtain, parcelObtain2, 0) && a.c() != null) {
                        return a.c().b(aVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
