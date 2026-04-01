package com.wanos.groove;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IGrooveDataListener extends IInterface {

    public static class Default implements IGrooveDataListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void getLyricChanged(LyricInfo lyricInfo) throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void lyricChanged(LyricLine lyricLine) throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void onIndexChanged(MediaInfo mediaInfo) throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void onListAdded() throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void onListChanged() throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void onPlayModeChanged(int i) throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataListener
        public void playState(int i) throws RemoteException {
        }
    }

    void getLyricChanged(LyricInfo lyricInfo) throws RemoteException;

    void lyricChanged(LyricLine lyricLine) throws RemoteException;

    void onIndexChanged(MediaInfo mediaInfo) throws RemoteException;

    void onListAdded() throws RemoteException;

    void onListChanged() throws RemoteException;

    void onPlayModeChanged(int i) throws RemoteException;

    void playState(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IGrooveDataListener {
        private static final String DESCRIPTOR = "com.wanos.groove.IGrooveDataListener";
        static final int TRANSACTION_getLyricChanged = 2;
        static final int TRANSACTION_lyricChanged = 1;
        static final int TRANSACTION_onIndexChanged = 3;
        static final int TRANSACTION_onListAdded = 5;
        static final int TRANSACTION_onListChanged = 4;
        static final int TRANSACTION_onPlayModeChanged = 7;
        static final int TRANSACTION_playState = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGrooveDataListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGrooveDataListener)) {
                return (IGrooveDataListener) iInterfaceQueryLocalInterface;
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
                    lyricChanged(parcel.readInt() != 0 ? LyricLine.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    getLyricChanged(parcel.readInt() != 0 ? LyricInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onIndexChanged(parcel.readInt() != 0 ? MediaInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onListChanged();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onListAdded();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    playState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPlayModeChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IGrooveDataListener {
            public static IGrooveDataListener sDefaultImpl;
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

            @Override // com.wanos.groove.IGrooveDataListener
            public void lyricChanged(LyricLine lyricLine) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lyricLine != null) {
                        parcelObtain.writeInt(1);
                        lyricLine.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().lyricChanged(lyricLine);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataListener
            public void getLyricChanged(LyricInfo lyricInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lyricInfo != null) {
                        parcelObtain.writeInt(1);
                        lyricInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getLyricChanged(lyricInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataListener
            public void onIndexChanged(MediaInfo mediaInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (mediaInfo != null) {
                        parcelObtain.writeInt(1);
                        mediaInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onIndexChanged(mediaInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataListener
            public void onListChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onListChanged();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataListener
            public void onListAdded() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onListAdded();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataListener
            public void playState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playState(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataListener
            public void onPlayModeChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayModeChanged(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGrooveDataListener iGrooveDataListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGrooveDataListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGrooveDataListener;
            return true;
        }

        public static IGrooveDataListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
