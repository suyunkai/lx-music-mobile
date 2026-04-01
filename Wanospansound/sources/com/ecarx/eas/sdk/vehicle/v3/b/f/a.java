package com.ecarx.eas.sdk.vehicle.v3.b.f;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.b.f.b;
import com.ecarx.eas.sdk.vehicle.v3.b.f.c;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    c a() throws RemoteException;

    void a(b bVar) throws RemoteException;

    void b(b bVar) throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.f.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0053a extends Binder implements a {
        public static a b() {
            return null;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcu");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0054a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcu");
                c cVarA = a();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(cVarA != null ? cVarA.asBinder() : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcu");
                a(b.a.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString("com.ecarx.sdk.openapi.vehicle.tcu.ITcu");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.tcu.ITcu");
            b(b.a.a(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.f.a$a$a, reason: collision with other inner class name */
        static class C0054a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f349a;

            C0054a(IBinder iBinder) {
                this.f349a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f349a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.f.a
            public final c a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.tcu.ITcu");
                    if (!this.f349a.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0053a.b() != null) {
                        return AbstractBinderC0053a.b().a();
                    }
                    parcelObtain2.readException();
                    return c.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.f.a
            public final void a(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.tcu.ITcu");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f349a.transact(2, parcelObtain, parcelObtain2, 0) && AbstractBinderC0053a.b() != null) {
                        AbstractBinderC0053a.b().a(bVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.f.a
            public final void b(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.tcu.ITcu");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f349a.transact(3, parcelObtain, parcelObtain2, 0) && AbstractBinderC0053a.b() != null) {
                        AbstractBinderC0053a.b().b(bVar);
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
