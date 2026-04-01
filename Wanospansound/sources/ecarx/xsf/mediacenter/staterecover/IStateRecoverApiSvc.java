package ecarx.xsf.mediacenter.staterecover;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IMediaCenterClientToken;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener;

/* JADX INFO: loaded from: classes3.dex */
public interface IStateRecoverApiSvc extends IInterface {
    IRecoveryMediaMetaInfo getRecoveryMediaList(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException;

    IMusicPlaybackInfo getRecoveryMusicPlaybackInfo(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException;

    void onMusicRecoveryComplete(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException;

    boolean registerMusicRecoveryIntent(IMediaCenterClientToken iMediaCenterClientToken, int i, String str) throws RemoteException;

    boolean setMusicRecoveryCallback(IMediaCenterClientToken iMediaCenterClientToken, IMusicRecoveryListener iMusicRecoveryListener) throws RemoteException;

    boolean unRegisterMusicRecoveryIntent(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException;

    public static abstract class Stub extends Binder implements IStateRecoverApiSvc {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.staterecover.IStateRecoverApiSvc";
        static final int TRANSACTION_getRecoveryMediaList = 3;
        static final int TRANSACTION_getRecoveryMusicPlaybackInfo = 4;
        static final int TRANSACTION_onMusicRecoveryComplete = 6;
        static final int TRANSACTION_registerMusicRecoveryIntent = 1;
        static final int TRANSACTION_setMusicRecoveryCallback = 5;
        static final int TRANSACTION_unRegisterMusicRecoveryIntent = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStateRecoverApiSvc asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IStateRecoverApiSvc)) {
                return (IStateRecoverApiSvc) iInterfaceQueryLocalInterface;
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
                    boolean zRegisterMusicRecoveryIntent = registerMusicRecoveryIntent(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterMusicRecoveryIntent ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnRegisterMusicRecoveryIntent = unRegisterMusicRecoveryIntent(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnRegisterMusicRecoveryIntent ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    IRecoveryMediaMetaInfo recoveryMediaList = getRecoveryMediaList(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (recoveryMediaList != null) {
                        parcel2.writeInt(1);
                        recoveryMediaList.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMusicPlaybackInfo recoveryMusicPlaybackInfo = getRecoveryMusicPlaybackInfo(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(recoveryMusicPlaybackInfo != null ? recoveryMusicPlaybackInfo.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean musicRecoveryCallback = setMusicRecoveryCallback(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), IMusicRecoveryListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(musicRecoveryCallback ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMusicRecoveryComplete(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IStateRecoverApiSvc {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IStateRecoverApiSvc f806a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f807b;

            a(IBinder iBinder) {
                this.f807b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f807b;
            }

            @Override // ecarx.xsf.mediacenter.staterecover.IStateRecoverApiSvc
            public final boolean registerMusicRecoveryIntent(IMediaCenterClientToken iMediaCenterClientToken, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f807b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMusicRecoveryIntent(iMediaCenterClientToken, i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.staterecover.IStateRecoverApiSvc
            public final boolean unRegisterMusicRecoveryIntent(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (!this.f807b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unRegisterMusicRecoveryIntent(iMediaCenterClientToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.staterecover.IStateRecoverApiSvc
            public final IRecoveryMediaMetaInfo getRecoveryMediaList(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (!this.f807b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecoveryMediaList(iMediaCenterClientToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IRecoveryMediaMetaInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.staterecover.IStateRecoverApiSvc
            public final IMusicPlaybackInfo getRecoveryMusicPlaybackInfo(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (!this.f807b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecoveryMusicPlaybackInfo(iMediaCenterClientToken);
                    }
                    parcelObtain2.readException();
                    return IMusicPlaybackInfo.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.staterecover.IStateRecoverApiSvc
            public final boolean setMusicRecoveryCallback(IMediaCenterClientToken iMediaCenterClientToken, IMusicRecoveryListener iMusicRecoveryListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeStrongBinder(iMusicRecoveryListener != null ? iMusicRecoveryListener.asBinder() : null);
                    if (!this.f807b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMusicRecoveryCallback(iMediaCenterClientToken, iMusicRecoveryListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.staterecover.IStateRecoverApiSvc
            public final void onMusicRecoveryComplete(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (!this.f807b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMusicRecoveryComplete(iMediaCenterClientToken);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStateRecoverApiSvc iStateRecoverApiSvc) {
            if (a.f806a != null || iStateRecoverApiSvc == null) {
                return false;
            }
            a.f806a = iStateRecoverApiSvc;
            return true;
        }

        public static IStateRecoverApiSvc getDefaultImpl() {
            return a.f806a;
        }
    }
}
