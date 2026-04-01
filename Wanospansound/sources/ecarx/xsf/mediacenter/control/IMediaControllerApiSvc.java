package ecarx.xsf.mediacenter.control;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import ecarx.xsf.mediacenter.control.IMediaController;
import ecarx.xsf.mediacenter.control.IMediaControllerToken;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaControllerApiSvc extends IInterface {
    List<IMediaContentType> getMediaContentTypeList(IMediaControllerToken iMediaControllerToken) throws RemoteException;

    List<IMedia> getMediaList(IMediaControllerToken iMediaControllerToken) throws RemoteException;

    IMusicPlaybackInfo getMusicPlaybackInfo(IMediaControllerToken iMediaControllerToken) throws RemoteException;

    int getSourceType(IMediaControllerToken iMediaControllerToken) throws RemoteException;

    boolean pause(IMediaControllerToken iMediaControllerToken) throws RemoteException;

    boolean play(IMediaControllerToken iMediaControllerToken, int i, String str) throws RemoteException;

    boolean playCtlPlay(IMediaControllerToken iMediaControllerToken, int i, String str) throws RemoteException;

    boolean playCtrlPause(IMediaControllerToken iMediaControllerToken, int i) throws RemoteException;

    boolean playCtrlPlayByContent(IMediaControllerToken iMediaControllerToken, int i, String str) throws RemoteException;

    IMediaControllerToken register(String str, IMediaController iMediaController) throws RemoteException;

    boolean requestControl(IMediaControllerToken iMediaControllerToken) throws RemoteException;

    boolean resume(IMediaControllerToken iMediaControllerToken) throws RemoteException;

    boolean unregister(IMediaControllerToken iMediaControllerToken) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaControllerApiSvc {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.control.IMediaControllerApiSvc";
        static final int TRANSACTION_getMediaContentTypeList = 4;
        static final int TRANSACTION_getMediaList = 12;
        static final int TRANSACTION_getMusicPlaybackInfo = 13;
        static final int TRANSACTION_getSourceType = 11;
        static final int TRANSACTION_pause = 9;
        static final int TRANSACTION_play = 10;
        static final int TRANSACTION_playCtlPlay = 5;
        static final int TRANSACTION_playCtrlPause = 7;
        static final int TRANSACTION_playCtrlPlayByContent = 6;
        static final int TRANSACTION_register = 1;
        static final int TRANSACTION_requestControl = 3;
        static final int TRANSACTION_resume = 8;
        static final int TRANSACTION_unregister = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaControllerApiSvc asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaControllerApiSvc)) {
                return (IMediaControllerApiSvc) iInterfaceQueryLocalInterface;
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
                    IMediaControllerToken iMediaControllerTokenRegister = register(parcel.readString(), IMediaController.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iMediaControllerTokenRegister != null ? iMediaControllerTokenRegister.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregister = unregister(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregister ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestControl = requestControl(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestControl ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<IMediaContentType> mediaContentTypeList = getMediaContentTypeList(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeTypedList(mediaContentTypeList);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPlayCtlPlay = playCtlPlay(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPlayCtlPlay ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPlayCtrlPlayByContent = playCtrlPlayByContent(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPlayCtrlPlayByContent ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPlayCtrlPause = playCtrlPause(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPlayCtrlPause ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zResume = resume(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zResume ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPause = pause(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zPause ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPlay = play(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPlay ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sourceType = getSourceType(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(sourceType);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<IMedia> mediaList = getMediaList(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeTypedList(mediaList);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMusicPlaybackInfo musicPlaybackInfo = getMusicPlaybackInfo(IMediaControllerToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(musicPlaybackInfo != null ? musicPlaybackInfo.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IMediaControllerApiSvc {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaControllerApiSvc f712a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f713b;

            a(IBinder iBinder) {
                this.f713b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f713b;
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final IMediaControllerToken register(String str, IMediaController iMediaController) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    if (!this.f713b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().register(str, iMediaController);
                    }
                    parcelObtain2.readException();
                    return IMediaControllerToken.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final boolean unregister(IMediaControllerToken iMediaControllerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    if (!this.f713b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregister(iMediaControllerToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final boolean requestControl(IMediaControllerToken iMediaControllerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    if (!this.f713b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestControl(iMediaControllerToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final List<IMediaContentType> getMediaContentTypeList(IMediaControllerToken iMediaControllerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    if (!this.f713b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaContentTypeList(iMediaControllerToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(IMediaContentType.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final boolean playCtlPlay(IMediaControllerToken iMediaControllerToken, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f713b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtlPlay(iMediaControllerToken, i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final boolean playCtrlPlayByContent(IMediaControllerToken iMediaControllerToken, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f713b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPlayByContent(iMediaControllerToken, i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final boolean playCtrlPause(IMediaControllerToken iMediaControllerToken, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.f713b.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPause(iMediaControllerToken, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final boolean resume(IMediaControllerToken iMediaControllerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    if (!this.f713b.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resume(iMediaControllerToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final boolean pause(IMediaControllerToken iMediaControllerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    if (!this.f713b.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pause(iMediaControllerToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final boolean play(IMediaControllerToken iMediaControllerToken, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f713b.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().play(iMediaControllerToken, i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final int getSourceType(IMediaControllerToken iMediaControllerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    if (!this.f713b.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceType(iMediaControllerToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final List<IMedia> getMediaList(IMediaControllerToken iMediaControllerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    if (!this.f713b.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaList(iMediaControllerToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(IMedia.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControllerApiSvc
            public final IMusicPlaybackInfo getMusicPlaybackInfo(IMediaControllerToken iMediaControllerToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerToken != null ? iMediaControllerToken.asBinder() : null);
                    if (!this.f713b.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicPlaybackInfo(iMediaControllerToken);
                    }
                    parcelObtain2.readException();
                    return IMusicPlaybackInfo.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaControllerApiSvc iMediaControllerApiSvc) {
            if (a.f712a != null || iMediaControllerApiSvc == null) {
                return false;
            }
            a.f712a = iMediaControllerApiSvc;
            return true;
        }

        public static IMediaControllerApiSvc getDefaultImpl() {
            return a.f712a;
        }
    }
}
