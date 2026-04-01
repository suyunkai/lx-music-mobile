package com.ecarx.eas.sdk.device.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.sdk.device.a.a.c;
import com.ecarx.eas.sdk.device.a.a.d;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {
    int a(int i) throws RemoteException;

    c a(int i, d dVar) throws RemoteException;

    void a(c cVar) throws RemoteException;

    public static abstract class a extends Binder implements b {
        public static b a() {
            return null;
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("ecarx.xsf.gkuiservice.policy.IFunPolicy");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof b)) {
                return (b) iInterfaceQueryLocalInterface;
            }
            return new C0013a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            d c0015a;
            if (i == 1) {
                parcel.enforceInterface("ecarx.xsf.gkuiservice.policy.IFunPolicy");
                int iA = a(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(iA);
                return true;
            }
            if (i != 2) {
                if (i != 3) {
                    if (i == 1598968902) {
                        parcel2.writeString("ecarx.xsf.gkuiservice.policy.IFunPolicy");
                        return true;
                    }
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel.enforceInterface("ecarx.xsf.gkuiservice.policy.IFunPolicy");
                a(c.a.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            parcel.enforceInterface("ecarx.xsf.gkuiservice.policy.IFunPolicy");
            int i3 = parcel.readInt();
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                c0015a = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("ecarx.xsf.gkuiservice.policy.IFunPolicyListener");
                if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof d)) {
                    c0015a = (d) iInterfaceQueryLocalInterface;
                } else {
                    c0015a = new d.a.C0015a(strongBinder);
                }
            }
            c cVarA = a(i3, c0015a);
            parcel2.writeNoException();
            parcel2.writeStrongBinder(cVarA != null ? cVarA.asBinder() : null);
            return true;
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.a.b$a$a, reason: collision with other inner class name */
        private static class C0013a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f57a;

            C0013a(IBinder iBinder) {
                this.f57a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f57a;
            }

            @Override // com.ecarx.eas.sdk.device.a.a.b
            public final int a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("ecarx.xsf.gkuiservice.policy.IFunPolicy");
                    parcelObtain.writeInt(i);
                    if (!this.f57a.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        return a.a().a(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.a.b
            public final c a(int i, d dVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("ecarx.xsf.gkuiservice.policy.IFunPolicy");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.f57a.transact(2, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        return a.a().a(i, dVar);
                    }
                    parcelObtain2.readException();
                    return c.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.sdk.device.a.a.b
            public final void a(c cVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("ecarx.xsf.gkuiservice.policy.IFunPolicy");
                    parcelObtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    if (!this.f57a.transact(3, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(cVar);
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
