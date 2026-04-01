package com.ecarx.eas.sdk.vehicle.v3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.a;
import com.ecarx.eas.sdk.vehicle.v3.a.j;
import com.ecarx.eas.sdk.vehicle.v3.b;
import com.ecarx.eas.sdk.vehicle.v3.b.a.a;
import com.ecarx.eas.sdk.vehicle.v3.b.b.b.a;
import com.ecarx.eas.sdk.vehicle.v3.b.d.a;
import com.ecarx.eas.sdk.vehicle.v3.b.e.a;
import com.ecarx.eas.sdk.vehicle.v3.b.f.a;

/* JADX INFO: loaded from: classes2.dex */
public interface e extends IInterface {
    b a() throws RemoteException;

    j b() throws RemoteException;

    com.ecarx.eas.sdk.vehicle.v3.a c() throws RemoteException;

    com.ecarx.eas.sdk.vehicle.v3.b.f.a d() throws RemoteException;

    com.ecarx.eas.sdk.vehicle.v3.b.b.b.a e() throws RemoteException;

    com.ecarx.eas.sdk.vehicle.v3.b.d.a f() throws RemoteException;

    com.ecarx.eas.sdk.vehicle.v3.b.e.a g() throws RemoteException;

    com.ecarx.eas.sdk.vehicle.v3.b.a.a h() throws RemoteException;

    public static abstract class a extends Binder implements e {
        public static e i() {
            return null;
        }

        public static e a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.IVehicle");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof e)) {
                return (e) iInterfaceQueryLocalInterface;
            }
            return new C0058a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.IVehicle");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IVehicle");
                    b bVarA = a();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(bVarA != null ? bVarA.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IVehicle");
                    j jVarB = b();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(jVarB != null ? jVarB.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IVehicle");
                    com.ecarx.eas.sdk.vehicle.v3.a aVarC = c();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(aVarC != null ? aVarC.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IVehicle");
                    com.ecarx.eas.sdk.vehicle.v3.b.f.a aVarD = d();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(aVarD != null ? aVarD.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IVehicle");
                    com.ecarx.eas.sdk.vehicle.v3.b.b.b.a aVarE = e();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(aVarE != null ? aVarE.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IVehicle");
                    com.ecarx.eas.sdk.vehicle.v3.b.d.a aVarF = f();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(aVarF != null ? aVarF.asBinder() : null);
                    return true;
                case 7:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IVehicle");
                    com.ecarx.eas.sdk.vehicle.v3.b.e.a aVarG = g();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(aVarG != null ? aVarG.asBinder() : null);
                    return true;
                case 8:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IVehicle");
                    com.ecarx.eas.sdk.vehicle.v3.b.a.a aVarH = h();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(aVarH != null ? aVarH.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.e$a$a, reason: collision with other inner class name */
        static class C0058a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f353a;

            C0058a(IBinder iBinder) {
                this.f353a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f353a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.e
            public final b a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IVehicle");
                    if (!this.f353a.transact(1, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().a();
                    }
                    parcelObtain2.readException();
                    return b.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.e
            public final j b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IVehicle");
                    if (!this.f353a.transact(2, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().b();
                    }
                    parcelObtain2.readException();
                    return j.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.e
            public final com.ecarx.eas.sdk.vehicle.v3.a c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IVehicle");
                    if (!this.f353a.transact(3, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().c();
                    }
                    parcelObtain2.readException();
                    return a.AbstractBinderC0021a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.e
            public final com.ecarx.eas.sdk.vehicle.v3.b.f.a d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IVehicle");
                    if (!this.f353a.transact(4, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().d();
                    }
                    parcelObtain2.readException();
                    return a.AbstractBinderC0053a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.e
            public final com.ecarx.eas.sdk.vehicle.v3.b.b.b.a e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IVehicle");
                    if (!this.f353a.transact(5, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().e();
                    }
                    parcelObtain2.readException();
                    return a.AbstractBinderC0043a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.e
            public final com.ecarx.eas.sdk.vehicle.v3.b.d.a f() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IVehicle");
                    if (!this.f353a.transact(6, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().f();
                    }
                    parcelObtain2.readException();
                    return a.AbstractBinderC0049a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.e
            public final com.ecarx.eas.sdk.vehicle.v3.b.e.a g() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IVehicle");
                    if (!this.f353a.transact(7, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().g();
                    }
                    parcelObtain2.readException();
                    return a.AbstractBinderC0051a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.e
            public final com.ecarx.eas.sdk.vehicle.v3.b.a.a h() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IVehicle");
                    this.f353a.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return a.AbstractBinderC0037a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
