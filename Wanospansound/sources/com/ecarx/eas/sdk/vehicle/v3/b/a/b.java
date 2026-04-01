package com.ecarx.eas.sdk.vehicle.v3.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {
    void a(int i) throws RemoteException;

    public static abstract class a extends Binder implements b {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.ecarx.sdk.openapi.vehicle.car.audio.ICaeSwitchChangeCallback");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICaeSwitchChangeCallback");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof b)) {
                return (b) iInterfaceQueryLocalInterface;
            }
            return new C0039a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.ecarx.sdk.openapi.vehicle.car.audio.ICaeSwitchChangeCallback");
                a(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString("com.ecarx.sdk.openapi.vehicle.car.audio.ICaeSwitchChangeCallback");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.v3.b.a.b$a$a, reason: collision with other inner class name */
        static class C0039a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f340a;

            C0039a(IBinder iBinder) {
                this.f340a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f340a;
            }

            @Override // com.ecarx.eas.sdk.vehicle.v3.b.a.b
            public final void a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ecarx.sdk.openapi.vehicle.car.audio.ICaeSwitchChangeCallback");
                    parcelObtain.writeInt(i);
                    this.f340a.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }
    }
}
