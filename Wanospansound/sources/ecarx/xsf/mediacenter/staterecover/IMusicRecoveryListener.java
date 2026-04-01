package ecarx.xsf.mediacenter.staterecover;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface IMusicRecoveryListener extends IInterface {
    void onGetMediaList(IRecoveryMediaMetaInfo iRecoveryMediaMetaInfo) throws RemoteException;

    void onGetMusicPlaybackInfo(IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicRecoveryListener {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener";
        static final int TRANSACTION_onGetMediaList = 1;
        static final int TRANSACTION_onGetMusicPlaybackInfo = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMusicRecoveryListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMusicRecoveryListener)) {
                return (IMusicRecoveryListener) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onGetMediaList(parcel.readInt() != 0 ? IRecoveryMediaMetaInfo.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onGetMusicPlaybackInfo(IMusicPlaybackInfo.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        static class a implements IMusicRecoveryListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMusicRecoveryListener f804a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f805b;

            a(IBinder iBinder) {
                this.f805b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f805b;
            }

            @Override // ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener
            public final void onGetMediaList(IRecoveryMediaMetaInfo iRecoveryMediaMetaInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iRecoveryMediaMetaInfo != null) {
                        parcelObtain.writeInt(1);
                        iRecoveryMediaMetaInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f805b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetMediaList(iRecoveryMediaMetaInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener
            public final void onGetMusicPlaybackInfo(IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMusicPlaybackInfo != null ? iMusicPlaybackInfo.asBinder() : null);
                    if (!this.f805b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetMusicPlaybackInfo(iMusicPlaybackInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicRecoveryListener iMusicRecoveryListener) {
            if (a.f804a != null || iMusicRecoveryListener == null) {
                return false;
            }
            a.f804a = iMusicRecoveryListener;
            return true;
        }

        public static IMusicRecoveryListener getDefaultImpl() {
            return a.f804a;
        }
    }
}
