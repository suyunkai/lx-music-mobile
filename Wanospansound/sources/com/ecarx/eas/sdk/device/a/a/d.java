package com.ecarx.eas.sdk.device.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface d extends IInterface {
    void a(int i) throws RemoteException;

    void a(int i, int i2) throws RemoteException;

    public static abstract class a extends Binder implements d {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "ecarx.xsf.gkuiservice.policy.IFunPolicyListener");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("ecarx.xsf.gkuiservice.policy.IFunPolicyListener");
                a(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString("ecarx.xsf.gkuiservice.policy.IFunPolicyListener");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("ecarx.xsf.gkuiservice.policy.IFunPolicyListener");
            a(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.a.d$a$a, reason: collision with other inner class name */
        static class C0015a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f59a;

            C0015a(IBinder iBinder) {
                this.f59a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f59a;
            }

            @Override // com.ecarx.eas.sdk.device.a.a.d
            public final void a(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("ecarx.xsf.gkuiservice.policy.IFunPolicyListener");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.f59a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.a.d
            public final void a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("ecarx.xsf.gkuiservice.policy.IFunPolicyListener");
                    parcelObtain.writeInt(i);
                    this.f59a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
