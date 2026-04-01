package ecarx.xsf.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface INewsPlaybackInfo extends IInterface {
    int getPlaybackStatus() throws RemoteException;

    boolean isCollected() throws RemoteException;

    boolean isDownloaded() throws RemoteException;

    boolean isSupportCollect() throws RemoteException;

    boolean isSupportDownload() throws RemoteException;

    boolean isSupportVrCtrlPlayStatus() throws RemoteException;

    public static abstract class Stub extends Binder implements INewsPlaybackInfo {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.INewsPlaybackInfo";
        static final int TRANSACTION_getPlaybackStatus = 1;
        static final int TRANSACTION_isCollected = 3;
        static final int TRANSACTION_isDownloaded = 5;
        static final int TRANSACTION_isSupportCollect = 2;
        static final int TRANSACTION_isSupportDownload = 4;
        static final int TRANSACTION_isSupportVrCtrlPlayStatus = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INewsPlaybackInfo asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INewsPlaybackInfo)) {
                return (INewsPlaybackInfo) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
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
                    int playbackStatus = getPlaybackStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(playbackStatus);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSupportCollect = isSupportCollect();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSupportCollect ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCollected = isCollected();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCollected ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSupportDownload = isSupportDownload();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSupportDownload ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDownloaded = isDownloaded();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDownloaded ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSupportVrCtrlPlayStatus = isSupportVrCtrlPlayStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSupportVrCtrlPlayStatus ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements INewsPlaybackInfo {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static INewsPlaybackInfo f696a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f697b;

            a(IBinder iBinder) {
                this.f697b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f697b;
            }

            @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
            public final int getPlaybackStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f697b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlaybackStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
            public final boolean isSupportCollect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f697b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupportCollect();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
            public final boolean isCollected() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f697b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCollected();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
            public final boolean isSupportDownload() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f697b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupportDownload();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
            public final boolean isDownloaded() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f697b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDownloaded();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
            public final boolean isSupportVrCtrlPlayStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f697b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupportVrCtrlPlayStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INewsPlaybackInfo iNewsPlaybackInfo) {
            if (a.f696a != null || iNewsPlaybackInfo == null) {
                return false;
            }
            a.f696a = iNewsPlaybackInfo;
            return true;
        }

        public static INewsPlaybackInfo getDefaultImpl() {
            return a.f696a;
        }
    }
}
