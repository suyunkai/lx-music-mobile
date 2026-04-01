package com.flyme.auto.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ICore extends IInterface {

    public static class Default implements ICore {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.flyme.auto.sdk.ICore
        public IBinder getCoreService(String str) throws RemoteException {
            return null;
        }
    }

    IBinder getCoreService(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ICore {
        private static final String DESCRIPTOR = "com.flyme.auto.sdk.ICore";
        static final int TRANSACTION_getCoreService = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICore asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICore)) {
                return (ICore) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            IBinder coreService = getCoreService(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeStrongBinder(coreService);
            return true;
        }

        private static class Proxy implements ICore {
            public static ICore sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.flyme.auto.sdk.ICore
            public IBinder getCoreService(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCoreService(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICore iCore) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCore == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCore;
            return true;
        }

        public static ICore getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
