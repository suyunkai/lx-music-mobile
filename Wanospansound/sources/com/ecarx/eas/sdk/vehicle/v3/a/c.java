package com.ecarx.eas.sdk.vehicle.v3.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.a.d;
import com.ecarx.eas.sdk.vehicle.v3.a.e;

/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {
    d a() throws RemoteException;

    void a(e eVar) throws RemoteException;

    boolean a(long j) throws RemoteException;

    boolean a(long j, long j2) throws RemoteException;

    void b(e eVar) throws RemoteException;

    boolean b() throws RemoteException;

    boolean b(long j) throws RemoteException;

    boolean b(long j, long j2) throws RemoteException;

    boolean c() throws RemoteException;

    boolean c(long j) throws RemoteException;

    void d() throws RemoteException;

    long e() throws RemoteException;

    long f() throws RemoteException;

    long g() throws RemoteException;

    long h() throws RemoteException;

    public static abstract class a extends Binder implements c {
        public static c i() {
            return null;
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof c)) {
                return (c) iInterfaceQueryLocalInterface;
            }
            return new C0026a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.newenergy.ICharge");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    d dVarA = a();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(dVarA != null ? dVarA.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    boolean zA = a(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    boolean zA2 = a(parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA2 ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    boolean zB = b(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zB ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    boolean zB2 = b(parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zB2 ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    boolean zB3 = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(zB3 ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    boolean zC = c(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zC ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    boolean zC2 = c();
                    parcel2.writeNoException();
                    parcel2.writeInt(zC2 ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    d();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    long jE = e();
                    parcel2.writeNoException();
                    parcel2.writeLong(jE);
                    return true;
                case 11:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    long jF = f();
                    parcel2.writeNoException();
                    parcel2.writeLong(jF);
                    return true;
                case 12:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    long jG = g();
                    parcel2.writeNoException();
                    parcel2.writeLong(jG);
                    return true;
                case 13:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    long jH = h();
                    parcel2.writeNoException();
                    parcel2.writeLong(jH);
                    return true;
                case 14:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    a(e.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.newenergy.ICharge");
                    b(e.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.a.c$a$a, reason: collision with other inner class name */
        static class C0026a implements c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f328a;

            C0026a(IBinder iBinder) {
                this.f328a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f328a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final d a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    if (!this.f328a.transact(1, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().a();
                    }
                    parcelObtain2.readException();
                    return d.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final boolean a(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    parcelObtain.writeLong(j);
                    if (!this.f328a.transact(2, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().a(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final boolean a(long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.f328a.transact(3, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().a(j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final boolean b(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    parcelObtain.writeLong(j);
                    if (!this.f328a.transact(4, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().b(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final boolean b(long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.f328a.transact(5, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().b(j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final boolean b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    if (!this.f328a.transact(6, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final boolean c(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    parcelObtain.writeLong(j);
                    if (!this.f328a.transact(7, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().c(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final boolean c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    if (!this.f328a.transact(8, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().c();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final void d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    if (!this.f328a.transact(9, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        a.i().d();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final long e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    if (!this.f328a.transact(10, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().e();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final long f() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    if (!this.f328a.transact(11, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().f();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final long g() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    if (!this.f328a.transact(12, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().g();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final long h() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    if (!this.f328a.transact(13, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        return a.i().h();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final void a(e eVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    parcelObtain.writeStrongBinder(eVar != null ? eVar.asBinder() : null);
                    if (!this.f328a.transact(14, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        a.i().a(eVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.a.c
            public final void b(e eVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.newenergy.ICharge");
                    parcelObtain.writeStrongBinder(eVar != null ? eVar.asBinder() : null);
                    if (!this.f328a.transact(15, parcelObtain, parcelObtain2, 0) && a.i() != null) {
                        a.i().b(eVar);
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
