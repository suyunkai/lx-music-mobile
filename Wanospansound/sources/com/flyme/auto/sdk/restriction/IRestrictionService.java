package com.flyme.auto.sdk.restriction;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.flyme.auto.sdk.restriction.IOnRestrictionStateChangedListener;

/* JADX INFO: loaded from: classes2.dex */
public interface IRestrictionService extends IInterface {

    public static class Default implements IRestrictionService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.flyme.auto.sdk.restriction.IRestrictionService
        public int getRestrictionState() throws RemoteException {
            return 0;
        }

        @Override // com.flyme.auto.sdk.restriction.IRestrictionService
        public boolean isPermittedPkg(String str) throws RemoteException {
            return false;
        }

        @Override // com.flyme.auto.sdk.restriction.IRestrictionService
        public void registerListener(IOnRestrictionStateChangedListener iOnRestrictionStateChangedListener) throws RemoteException {
        }

        @Override // com.flyme.auto.sdk.restriction.IRestrictionService
        public boolean shouldRestricted(String str) throws RemoteException {
            return false;
        }

        @Override // com.flyme.auto.sdk.restriction.IRestrictionService
        public boolean shouldRestrictedByDisplayId(String str, int i) throws RemoteException {
            return false;
        }

        @Override // com.flyme.auto.sdk.restriction.IRestrictionService
        public void showToast(String str) throws RemoteException {
        }

        @Override // com.flyme.auto.sdk.restriction.IRestrictionService
        public void showToastByDisplayId(String str, int i) throws RemoteException {
        }

        @Override // com.flyme.auto.sdk.restriction.IRestrictionService
        public void unregisterListener(IOnRestrictionStateChangedListener iOnRestrictionStateChangedListener) throws RemoteException {
        }
    }

    int getRestrictionState() throws RemoteException;

    boolean isPermittedPkg(String str) throws RemoteException;

    void registerListener(IOnRestrictionStateChangedListener iOnRestrictionStateChangedListener) throws RemoteException;

    boolean shouldRestricted(String str) throws RemoteException;

    boolean shouldRestrictedByDisplayId(String str, int i) throws RemoteException;

    void showToast(String str) throws RemoteException;

    void showToastByDisplayId(String str, int i) throws RemoteException;

    void unregisterListener(IOnRestrictionStateChangedListener iOnRestrictionStateChangedListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IRestrictionService {
        private static final String DESCRIPTOR = "com.flyme.auto.sdk.restriction.IRestrictionService";
        static final int TRANSACTION_getRestrictionState = 1;
        static final int TRANSACTION_isPermittedPkg = 2;
        static final int TRANSACTION_registerListener = 4;
        static final int TRANSACTION_shouldRestricted = 3;
        static final int TRANSACTION_shouldRestrictedByDisplayId = 8;
        static final int TRANSACTION_showToast = 6;
        static final int TRANSACTION_showToastByDisplayId = 7;
        static final int TRANSACTION_unregisterListener = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRestrictionService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRestrictionService)) {
                return (IRestrictionService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int restrictionState = getRestrictionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(restrictionState);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPermittedPkg = isPermittedPkg(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPermittedPkg ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldRestricted = shouldRestricted(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldRestricted ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(IOnRestrictionStateChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IOnRestrictionStateChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    showToast(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    showToastByDisplayId(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldRestrictedByDisplayId = shouldRestrictedByDisplayId(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldRestrictedByDisplayId ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRestrictionService {
            public static IRestrictionService sDefaultImpl;
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

            @Override // com.flyme.auto.sdk.restriction.IRestrictionService
            public int getRestrictionState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRestrictionState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.flyme.auto.sdk.restriction.IRestrictionService
            public boolean isPermittedPkg(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPermittedPkg(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.flyme.auto.sdk.restriction.IRestrictionService
            public boolean shouldRestricted(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldRestricted(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.flyme.auto.sdk.restriction.IRestrictionService
            public void registerListener(IOnRestrictionStateChangedListener iOnRestrictionStateChangedListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOnRestrictionStateChangedListener != null ? iOnRestrictionStateChangedListener.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iOnRestrictionStateChangedListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.flyme.auto.sdk.restriction.IRestrictionService
            public void unregisterListener(IOnRestrictionStateChangedListener iOnRestrictionStateChangedListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOnRestrictionStateChangedListener != null ? iOnRestrictionStateChangedListener.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iOnRestrictionStateChangedListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.flyme.auto.sdk.restriction.IRestrictionService
            public void showToast(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showToast(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.flyme.auto.sdk.restriction.IRestrictionService
            public void showToastByDisplayId(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showToastByDisplayId(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.flyme.auto.sdk.restriction.IRestrictionService
            public boolean shouldRestrictedByDisplayId(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldRestrictedByDisplayId(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRestrictionService iRestrictionService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRestrictionService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRestrictionService;
            return true;
        }

        public static IRestrictionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
