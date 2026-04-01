package com.ecarx.eas.sdk.vehicle.v3.b.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.v3.b.b.a.b;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {
    int a(String str) throws RemoteException;

    void a(String str, int i, int i2) throws RemoteException;

    boolean a(int i) throws RemoteException;

    boolean a(int i, int i2) throws RemoteException;

    boolean a(b bVar) throws RemoteException;

    int b(String str) throws RemoteException;

    boolean b(b bVar) throws RemoteException;

    int c(String str) throws RemoteException;

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.b.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0040a extends Binder implements a {
        public static a a() {
            return null;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0041a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    int iA = a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iA);
                    return true;
                case 2:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    int iB = b(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 3:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    int iC = c(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iC);
                    return true;
                case 4:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    a(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    boolean zA = a(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    boolean zA2 = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zA2 ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    boolean zA3 = a(b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zA3 ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    boolean zB = b(b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zB ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.b.a.a$a$a, reason: collision with other inner class name */
        static class C0041a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f341a;

            C0041a(IBinder iBinder) {
                this.f341a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f341a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.a
            public final int a(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    parcelObtain.writeString(str);
                    if (!this.f341a.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0040a.a() != null) {
                        return AbstractBinderC0040a.a().a(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.a
            public final int b(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    parcelObtain.writeString(str);
                    if (!this.f341a.transact(2, parcelObtain, parcelObtain2, 0) && AbstractBinderC0040a.a() != null) {
                        return AbstractBinderC0040a.a().b(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.a
            public final int c(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    parcelObtain.writeString(str);
                    if (!this.f341a.transact(3, parcelObtain, parcelObtain2, 0) && AbstractBinderC0040a.a() != null) {
                        return AbstractBinderC0040a.a().c(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.a
            public final void a(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.f341a.transact(4, parcelObtain, parcelObtain2, 0) && AbstractBinderC0040a.a() != null) {
                        AbstractBinderC0040a.a().a(str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.a
            public final boolean a(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.f341a.transact(5, parcelObtain, parcelObtain2, 0) && AbstractBinderC0040a.a() != null) {
                        return AbstractBinderC0040a.a().a(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.a
            public final boolean a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    parcelObtain.writeInt(i);
                    if (!this.f341a.transact(6, parcelObtain, parcelObtain2, 0) && AbstractBinderC0040a.a() != null) {
                        return AbstractBinderC0040a.a().a(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.a
            public final boolean a(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f341a.transact(7, parcelObtain, parcelObtain2, 0) && AbstractBinderC0040a.a() != null) {
                        return AbstractBinderC0040a.a().a(bVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.a
            public final boolean b(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.ICarAudioManager");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f341a.transact(8, parcelObtain, parcelObtain2, 0) && AbstractBinderC0040a.a() != null) {
                        return AbstractBinderC0040a.a().b(bVar);
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
