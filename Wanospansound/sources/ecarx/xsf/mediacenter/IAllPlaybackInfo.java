package ecarx.xsf.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.INewsPlaybackInfo;
import ecarx.xsf.mediacenter.IVideoPlaybackInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface IAllPlaybackInfo extends IInterface {
    int getClientType() throws RemoteException;

    IMusicPlaybackInfo getMusicPlaybackInfo() throws RemoteException;

    INewsPlaybackInfo getNewsPlaybackInfo() throws RemoteException;

    IVideoPlaybackInfo getVideoPlaybackInfo() throws RemoteException;

    public static abstract class Stub extends Binder implements IAllPlaybackInfo {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.IAllPlaybackInfo";
        static final int TRANSACTION_getClientType = 1;
        static final int TRANSACTION_getMusicPlaybackInfo = 2;
        static final int TRANSACTION_getNewsPlaybackInfo = 4;
        static final int TRANSACTION_getVideoPlaybackInfo = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAllPlaybackInfo asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAllPlaybackInfo)) {
                return (IAllPlaybackInfo) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int clientType = getClientType();
                parcel2.writeNoException();
                parcel2.writeInt(clientType);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                IMusicPlaybackInfo musicPlaybackInfo = getMusicPlaybackInfo();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(musicPlaybackInfo != null ? musicPlaybackInfo.asBinder() : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                IVideoPlaybackInfo videoPlaybackInfo = getVideoPlaybackInfo();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(videoPlaybackInfo != null ? videoPlaybackInfo.asBinder() : null);
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            INewsPlaybackInfo newsPlaybackInfo = getNewsPlaybackInfo();
            parcel2.writeNoException();
            parcel2.writeStrongBinder(newsPlaybackInfo != null ? newsPlaybackInfo.asBinder() : null);
            return true;
        }

        static class a implements IAllPlaybackInfo {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IAllPlaybackInfo f680a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f681b;

            a(IBinder iBinder) {
                this.f681b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f681b;
            }

            @Override // ecarx.xsf.mediacenter.IAllPlaybackInfo
            public final int getClientType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f681b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getClientType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IAllPlaybackInfo
            public final IMusicPlaybackInfo getMusicPlaybackInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f681b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicPlaybackInfo();
                    }
                    parcelObtain2.readException();
                    return IMusicPlaybackInfo.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IAllPlaybackInfo
            public final IVideoPlaybackInfo getVideoPlaybackInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f681b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVideoPlaybackInfo();
                    }
                    parcelObtain2.readException();
                    return IVideoPlaybackInfo.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IAllPlaybackInfo
            public final INewsPlaybackInfo getNewsPlaybackInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f681b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNewsPlaybackInfo();
                    }
                    parcelObtain2.readException();
                    return INewsPlaybackInfo.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAllPlaybackInfo iAllPlaybackInfo) {
            if (a.f680a != null || iAllPlaybackInfo == null) {
                return false;
            }
            a.f680a = iAllPlaybackInfo;
            return true;
        }

        public static IAllPlaybackInfo getDefaultImpl() {
            return a.f680a;
        }
    }
}
