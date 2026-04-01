package com.ecarx.eas.sdk.vehicle.v3.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.b.a.b;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    int a() throws RemoteException;

    boolean a(int i) throws RemoteException;

    boolean a(b bVar) throws RemoteException;

    int b() throws RemoteException;

    boolean b(b bVar) throws RemoteException;

    int c() throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0037a extends Binder implements a {
        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0038a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    int iA = a();
                    parcel2.writeNoException();
                    parcel2.writeInt(iA);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    int iB = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    boolean zA = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    boolean zA2 = a(b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zA2 ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    boolean zB = b(b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zB ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    int iC = c();
                    parcel2.writeNoException();
                    parcel2.writeInt(iC);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.a.a$a$a, reason: collision with other inner class name */
        static class C0038a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f339a;

            C0038a(IBinder iBinder) {
                this.f339a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f339a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.a.a
            public final int a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    this.f339a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.a.a
            public final int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    this.f339a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.a.a
            public final boolean a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    parcelObtain.writeInt(i);
                    this.f339a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.a.a
            public final boolean a(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.f339a.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.a.a
            public final boolean b(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.f339a.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.a.a
            public final int c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.IAudio");
                    this.f339a.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
