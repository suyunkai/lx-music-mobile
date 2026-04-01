package com.ecarx.eas.sdk.device.a;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.device.a.b;
import com.ecarx.eas.sdk.device.a.d;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {
    String a() throws RemoteException;

    String a(String str) throws RemoteException;

    void a(d dVar) throws RemoteException;

    int b(String str) throws RemoteException;

    String b() throws RemoteException;

    void b(d dVar) throws RemoteException;

    String c() throws RemoteException;

    boolean c(String str) throws RemoteException;

    long d(String str) throws RemoteException;

    String d() throws RemoteException;

    double e(String str) throws RemoteException;

    String e() throws RemoteException;

    String f() throws RemoteException;

    String g() throws RemoteException;

    String h() throws RemoteException;

    b i() throws RemoteException;

    List<ComponentName> j() throws RemoteException;

    int k() throws RemoteException;

    String l() throws RemoteException;

    String m() throws RemoteException;

    String n() throws RemoteException;

    String o() throws RemoteException;

    IBinder p() throws RemoteException;

    String q() throws RemoteException;

    public static abstract class a extends Binder implements c {
        public static c r() {
            return null;
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.IDevice");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof c)) {
                return (c) iInterfaceQueryLocalInterface;
            }
            return new C0018a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.IDevice");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strA = a();
                    parcel2.writeNoException();
                    parcel2.writeString(strA);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strB = b();
                    parcel2.writeNoException();
                    parcel2.writeString(strB);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strC = c();
                    parcel2.writeNoException();
                    parcel2.writeString(strC);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strD = d();
                    parcel2.writeNoException();
                    parcel2.writeString(strD);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strA2 = a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(strA2);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    int iB = b(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 7:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    boolean zC = c(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zC ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    long jD = d(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(jD);
                    return true;
                case 9:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    double dE = e(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeDouble(dE);
                    return true;
                case 10:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strE = e();
                    parcel2.writeNoException();
                    parcel2.writeString(strE);
                    return true;
                case 11:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strF = f();
                    parcel2.writeNoException();
                    parcel2.writeString(strF);
                    return true;
                case 12:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strG = g();
                    parcel2.writeNoException();
                    parcel2.writeString(strG);
                    return true;
                case 13:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strH = h();
                    parcel2.writeNoException();
                    parcel2.writeString(strH);
                    return true;
                case 14:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    b bVarI = i();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(bVarI != null ? bVarI.asBinder() : null);
                    return true;
                case 15:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    List<ComponentName> listJ = j();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(listJ);
                    return true;
                case 16:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    int iK = k();
                    parcel2.writeNoException();
                    parcel2.writeInt(iK);
                    return true;
                case 17:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strL = l();
                    parcel2.writeNoException();
                    parcel2.writeString(strL);
                    return true;
                case 18:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strM = m();
                    parcel2.writeNoException();
                    parcel2.writeString(strM);
                    return true;
                case 19:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strN = n();
                    parcel2.writeNoException();
                    parcel2.writeString(strN);
                    return true;
                case 20:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    a(d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    b(d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    IBinder iBinderP = p();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iBinderP);
                    return true;
                case 23:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strQ = q();
                    parcel2.writeNoException();
                    parcel2.writeString(strQ);
                    return true;
                case 24:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.IDevice");
                    String strO = o();
                    parcel2.writeNoException();
                    parcel2.writeString(strO);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.c$a$a, reason: collision with other inner class name */
        private static class C0018a implements c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f62a;

            C0018a(IBinder iBinder) {
                this.f62a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f62a;
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(1, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().a();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(2, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().b();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(3, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().c();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(4, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().d();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String a(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    parcelObtain.writeString(str);
                    if (!this.f62a.transact(5, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().a(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final int b(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    parcelObtain.writeString(str);
                    if (!this.f62a.transact(6, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().b(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final boolean c(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    parcelObtain.writeString(str);
                    if (!this.f62a.transact(7, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().c(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final long d(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    parcelObtain.writeString(str);
                    if (!this.f62a.transact(8, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().d(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final double e(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    parcelObtain.writeString(str);
                    if (!this.f62a.transact(9, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().e(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readDouble();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(10, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().e();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String f() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(11, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().f();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String g() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(12, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().g();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String h() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(13, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().h();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final b i() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(14, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().i();
                    }
                    parcelObtain2.readException();
                    return b.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final List<ComponentName> j() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(15, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().j();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ComponentName.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final int k() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(16, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().k();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String l() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(17, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().l();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String m() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(18, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().m();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String n() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(19, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().n();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final void a(d dVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.f62a.transact(20, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        a.r().a(dVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final void b(d dVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.f62a.transact(21, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        a.r().b(dVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String o() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(24, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().o();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final IBinder p() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    if (!this.f62a.transact(22, parcelObtain, parcelObtain2, 0) && a.r() != null) {
                        return a.r().p();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.c
            public final String q() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.IDevice");
                    this.f62a.transact(23, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
