package com.wanos.groove;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.wanos.groove.IGrooveDataListener;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IGrooveDataInterface extends IInterface {

    public static class Default implements IGrooveDataInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public void doPlay() throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public List<MediaInfo> getCurrentList() throws RemoteException {
            return null;
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public LyricInfo getCurrentLyric() throws RemoteException {
            return null;
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public MediaInfo getCurrentMediaInfo() throws RemoteException {
            return null;
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public int getCurrentPlayMode() throws RemoteException {
            return 0;
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public void getMediaPlayer() throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public void next() throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public void pause() throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public void playAreaContentData() throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public void pre() throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public void registerCallback(IGrooveDataListener iGrooveDataListener) throws RemoteException {
        }

        @Override // com.wanos.groove.IGrooveDataInterface
        public void unregisterCallback(IGrooveDataListener iGrooveDataListener) throws RemoteException {
        }
    }

    void doPlay() throws RemoteException;

    List<MediaInfo> getCurrentList() throws RemoteException;

    LyricInfo getCurrentLyric() throws RemoteException;

    MediaInfo getCurrentMediaInfo() throws RemoteException;

    int getCurrentPlayMode() throws RemoteException;

    void getMediaPlayer() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void playAreaContentData() throws RemoteException;

    void pre() throws RemoteException;

    void registerCallback(IGrooveDataListener iGrooveDataListener) throws RemoteException;

    void unregisterCallback(IGrooveDataListener iGrooveDataListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IGrooveDataInterface {
        private static final String DESCRIPTOR = "com.wanos.groove.IGrooveDataInterface";
        static final int TRANSACTION_doPlay = 6;
        static final int TRANSACTION_getCurrentList = 5;
        static final int TRANSACTION_getCurrentLyric = 12;
        static final int TRANSACTION_getCurrentMediaInfo = 4;
        static final int TRANSACTION_getCurrentPlayMode = 11;
        static final int TRANSACTION_getMediaPlayer = 3;
        static final int TRANSACTION_next = 9;
        static final int TRANSACTION_pause = 10;
        static final int TRANSACTION_playAreaContentData = 7;
        static final int TRANSACTION_pre = 8;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unregisterCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGrooveDataInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGrooveDataInterface)) {
                return (IGrooveDataInterface) iInterfaceQueryLocalInterface;
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
                    registerCallback(IGrooveDataListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(IGrooveDataListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getMediaPlayer();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    MediaInfo currentMediaInfo = getCurrentMediaInfo();
                    parcel2.writeNoException();
                    if (currentMediaInfo != null) {
                        parcel2.writeInt(1);
                        currentMediaInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<MediaInfo> currentList = getCurrentList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(currentList);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    doPlay();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    playAreaContentData();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    pre();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    next();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    pause();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentPlayMode = getCurrentPlayMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentPlayMode);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    LyricInfo currentLyric = getCurrentLyric();
                    parcel2.writeNoException();
                    if (currentLyric != null) {
                        parcel2.writeInt(1);
                        currentLyric.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IGrooveDataInterface {
            public static IGrooveDataInterface sDefaultImpl;
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

            @Override // com.wanos.groove.IGrooveDataInterface
            public void registerCallback(IGrooveDataListener iGrooveDataListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGrooveDataListener != null ? iGrooveDataListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(iGrooveDataListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public void unregisterCallback(IGrooveDataListener iGrooveDataListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGrooveDataListener != null ? iGrooveDataListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCallback(iGrooveDataListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public void getMediaPlayer() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getMediaPlayer();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public MediaInfo getCurrentMediaInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentMediaInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MediaInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public List<MediaInfo> getCurrentList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(MediaInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public void doPlay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().doPlay();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public void playAreaContentData() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playAreaContentData();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public void pre() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pre();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public void next() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().next();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public void pause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pause();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public int getCurrentPlayMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPlayMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.wanos.groove.IGrooveDataInterface
            public LyricInfo getCurrentLyric() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentLyric();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? LyricInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGrooveDataInterface iGrooveDataInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGrooveDataInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGrooveDataInterface;
            return true;
        }

        public static IGrooveDataInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
