package com.ecarx.eas.sdk.vehicle.v3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.b.c.a;
import com.ecarx.eas.sdk.vehicle.v3.b.c.b;
import com.ecarx.eas.sdk.vehicle.v3.c;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {
    double a() throws RemoteException;

    boolean a(com.ecarx.eas.sdk.vehicle.v3.b.c.a aVar) throws RemoteException;

    boolean a(com.ecarx.eas.sdk.vehicle.v3.b.c.b bVar) throws RemoteException;

    boolean a(com.ecarx.eas.sdk.vehicle.v3.b.c.b bVar, double[] dArr, int i, int i2) throws RemoteException;

    boolean a(c cVar) throws RemoteException;

    double b() throws RemoteException;

    void b(com.ecarx.eas.sdk.vehicle.v3.b.c.a aVar) throws RemoteException;

    void b(com.ecarx.eas.sdk.vehicle.v3.b.c.b bVar) throws RemoteException;

    void b(c cVar) throws RemoteException;

    double c() throws RemoteException;

    double d() throws RemoteException;

    double e() throws RemoteException;

    IMileageInfo f() throws RemoteException;

    IWarningInfo g() throws RemoteException;

    int h() throws RemoteException;

    int i() throws RemoteException;

    public static abstract class a extends Binder implements b {
        public static b j() {
            return null;
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.IDashboard");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof b)) {
                return (b) iInterfaceQueryLocalInterface;
            }
            return new C0036a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.IDashboard");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    double dA = a();
                    parcel2.writeNoException();
                    parcel2.writeDouble(dA);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    double dB = b();
                    parcel2.writeNoException();
                    parcel2.writeDouble(dB);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    double dC = c();
                    parcel2.writeNoException();
                    parcel2.writeDouble(dC);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    double d2 = d();
                    parcel2.writeNoException();
                    parcel2.writeDouble(d2);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    double dE = e();
                    parcel2.writeNoException();
                    parcel2.writeDouble(dE);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    IMileageInfo iMileageInfoF = f();
                    parcel2.writeNoException();
                    if (iMileageInfoF != null) {
                        parcel2.writeInt(1);
                        iMileageInfoF.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    IWarningInfo iWarningInfoG = g();
                    parcel2.writeNoException();
                    if (iWarningInfoG != null) {
                        parcel2.writeInt(1);
                        iWarningInfoG.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    int iH = h();
                    parcel2.writeNoException();
                    parcel2.writeInt(iH);
                    return true;
                case 9:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    boolean zA = a(c.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    b(c.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    boolean zA2 = a(b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zA2 ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    boolean zA3 = a(b.a.a(parcel.readStrongBinder()), parcel.createDoubleArray(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA3 ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    b(b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    int i3 = i();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 15:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    boolean zA4 = a(a.AbstractBinderC0046a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zA4 ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDashboard");
                    b(a.AbstractBinderC0046a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b$a$a, reason: collision with other inner class name */
        static class C0036a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f338a;

            C0036a(IBinder iBinder) {
                this.f338a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f338a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final double a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    if (!this.f338a.transact(1, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readDouble();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final double b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    if (!this.f338a.transact(2, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readDouble();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final double c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    if (!this.f338a.transact(3, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().c();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readDouble();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final double d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    if (!this.f338a.transact(4, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().d();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readDouble();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final double e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    if (!this.f338a.transact(5, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().e();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readDouble();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final IMileageInfo f() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    if (!this.f338a.transact(6, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().f();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IMileageInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final IWarningInfo g() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    if (!this.f338a.transact(7, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().g();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IWarningInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final int h() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    if (!this.f338a.transact(8, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().h();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final boolean a(c cVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    parcelObtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    if (!this.f338a.transact(9, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().a(cVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final void b(c cVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    parcelObtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    if (!this.f338a.transact(10, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        a.j().b(cVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final boolean a(com.ecarx.eas.sdk.vehicle.v3.b.c.b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f338a.transact(11, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().a(bVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final boolean a(com.ecarx.eas.sdk.vehicle.v3.b.c.b bVar, double[] dArr, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    parcelObtain.writeDoubleArray(dArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.f338a.transact(12, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().a(bVar, dArr, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final void b(com.ecarx.eas.sdk.vehicle.v3.b.c.b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f338a.transact(13, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        a.j().b(bVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final int i() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    if (!this.f338a.transact(14, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().i();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final boolean a(com.ecarx.eas.sdk.vehicle.v3.b.c.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f338a.transact(15, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        return a.j().a(aVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b
            public final void b(com.ecarx.eas.sdk.vehicle.v3.b.c.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDashboard");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f338a.transact(16, parcelObtain, parcelObtain2, 0) && a.j() != null) {
                        a.j().b(aVar);
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
