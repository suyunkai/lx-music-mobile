package ecarx.xsf.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IAllPlaybackInfo;
import ecarx.xsf.mediacenter.vr.QMusicResult;
import ecarx.xsf.mediacenter.vr.QNewsResult;
import ecarx.xsf.mediacenter.vr.QRadioResult;
import ecarx.xsf.xiaoka.IXiaokaInternalApiSvc;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaCenterInternalApiSvc extends IInterface {
    IAllPlaybackInfo getAllPlaybackInfo() throws RemoteException;

    boolean handleCtrlApp(int i, int i2) throws RemoteException;

    boolean handlePlayMusic(QMusicResult qMusicResult) throws RemoteException;

    boolean handlePlayNews(QNewsResult qNewsResult) throws RemoteException;

    boolean handlePlayRadio(QRadioResult qRadioResult) throws RemoteException;

    boolean handleSearchMusic(QMusicResult qMusicResult) throws RemoteException;

    boolean handleSearchNews(QNewsResult qNewsResult) throws RemoteException;

    boolean handleSearchRadio(QRadioResult qRadioResult) throws RemoteException;

    boolean hasPlayingMedia() throws RemoteException;

    int playCtrlCollect(int i, boolean z) throws RemoteException;

    int playCtrlDownload(int i, boolean z) throws RemoteException;

    int playCtrlFastForward() throws RemoteException;

    int playCtrlNext() throws RemoteException;

    int playCtrlPause() throws RemoteException;

    int playCtrlPlay() throws RemoteException;

    int playCtrlPlayType(int i) throws RemoteException;

    int playCtrlPrevious() throws RemoteException;

    int playCtrlQuality(int i) throws RemoteException;

    int playCtrlReplay() throws RemoteException;

    int playCtrlRewind() throws RemoteException;

    int playCtrlStop() throws RemoteException;

    void setXiaokaInternalApiSvc(IXiaokaInternalApiSvc iXiaokaInternalApiSvc) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaCenterInternalApiSvc {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc";
        static final int TRANSACTION_getAllPlaybackInfo = 3;
        static final int TRANSACTION_handleCtrlApp = 21;
        static final int TRANSACTION_handlePlayMusic = 18;
        static final int TRANSACTION_handlePlayNews = 20;
        static final int TRANSACTION_handlePlayRadio = 19;
        static final int TRANSACTION_handleSearchMusic = 15;
        static final int TRANSACTION_handleSearchNews = 17;
        static final int TRANSACTION_handleSearchRadio = 16;
        static final int TRANSACTION_hasPlayingMedia = 1;
        static final int TRANSACTION_playCtrlCollect = 13;
        static final int TRANSACTION_playCtrlDownload = 14;
        static final int TRANSACTION_playCtrlFastForward = 7;
        static final int TRANSACTION_playCtrlNext = 9;
        static final int TRANSACTION_playCtrlPause = 5;
        static final int TRANSACTION_playCtrlPlay = 4;
        static final int TRANSACTION_playCtrlPlayType = 11;
        static final int TRANSACTION_playCtrlPrevious = 10;
        static final int TRANSACTION_playCtrlQuality = 22;
        static final int TRANSACTION_playCtrlReplay = 12;
        static final int TRANSACTION_playCtrlRewind = 8;
        static final int TRANSACTION_playCtrlStop = 6;
        static final int TRANSACTION_setXiaokaInternalApiSvc = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaCenterInternalApiSvc asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaCenterInternalApiSvc)) {
                return (IMediaCenterInternalApiSvc) iInterfaceQueryLocalInterface;
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
                    boolean zHasPlayingMedia = hasPlayingMedia();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasPlayingMedia ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    setXiaokaInternalApiSvc(IXiaokaInternalApiSvc.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    IAllPlaybackInfo allPlaybackInfo = getAllPlaybackInfo();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(allPlaybackInfo != null ? allPlaybackInfo.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlPlay = playCtrlPlay();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlPlay);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlPause = playCtrlPause();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlPause);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlStop = playCtrlStop();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlStop);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlFastForward = playCtrlFastForward();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlFastForward);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlRewind = playCtrlRewind();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlRewind);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlNext = playCtrlNext();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlNext);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlPrevious = playCtrlPrevious();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlPrevious);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlPlayType = playCtrlPlayType(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlPlayType);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlReplay = playCtrlReplay();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlReplay);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlCollect = playCtrlCollect(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlCollect);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlDownload = playCtrlDownload(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlDownload);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandleSearchMusic = handleSearchMusic(parcel.readInt() != 0 ? QMusicResult.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandleSearchMusic ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandleSearchRadio = handleSearchRadio(parcel.readInt() != 0 ? QRadioResult.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandleSearchRadio ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandleSearchNews = handleSearchNews(parcel.readInt() != 0 ? QNewsResult.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandleSearchNews ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandlePlayMusic = handlePlayMusic(parcel.readInt() != 0 ? QMusicResult.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandlePlayMusic ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandlePlayRadio = handlePlayRadio(parcel.readInt() != 0 ? QRadioResult.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandlePlayRadio ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandlePlayNews = handlePlayNews(parcel.readInt() != 0 ? QNewsResult.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandlePlayNews ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHandleCtrlApp = handleCtrlApp(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHandleCtrlApp ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlQuality = playCtrlQuality(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlQuality);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IMediaCenterInternalApiSvc {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaCenterInternalApiSvc f684a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f685b;

            a(IBinder iBinder) {
                this.f685b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f685b;
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final boolean hasPlayingMedia() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasPlayingMedia();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final void setXiaokaInternalApiSvc(IXiaokaInternalApiSvc iXiaokaInternalApiSvc) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iXiaokaInternalApiSvc != null ? iXiaokaInternalApiSvc.asBinder() : null);
                    if (!this.f685b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setXiaokaInternalApiSvc(iXiaokaInternalApiSvc);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final IAllPlaybackInfo getAllPlaybackInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPlaybackInfo();
                    }
                    parcelObtain2.readException();
                    return IAllPlaybackInfo.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlPlay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPlay();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlPause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPause();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlStop() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlStop();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlFastForward() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlFastForward();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlRewind() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlRewind();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlNext() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlNext();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlPrevious() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPrevious();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlPlayType(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.f685b.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPlayType(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlReplay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f685b.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlReplay();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlCollect(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.f685b.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlCollect(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlDownload(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.f685b.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlDownload(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final boolean handleSearchMusic(QMusicResult qMusicResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (qMusicResult != null) {
                        parcelObtain.writeInt(1);
                        qMusicResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f685b.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handleSearchMusic(qMusicResult);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final boolean handleSearchRadio(QRadioResult qRadioResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (qRadioResult != null) {
                        parcelObtain.writeInt(1);
                        qRadioResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f685b.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handleSearchRadio(qRadioResult);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final boolean handleSearchNews(QNewsResult qNewsResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (qNewsResult != null) {
                        parcelObtain.writeInt(1);
                        qNewsResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f685b.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handleSearchNews(qNewsResult);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final boolean handlePlayMusic(QMusicResult qMusicResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (qMusicResult != null) {
                        parcelObtain.writeInt(1);
                        qMusicResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f685b.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePlayMusic(qMusicResult);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final boolean handlePlayRadio(QRadioResult qRadioResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (qRadioResult != null) {
                        parcelObtain.writeInt(1);
                        qRadioResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f685b.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePlayRadio(qRadioResult);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final boolean handlePlayNews(QNewsResult qNewsResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (qNewsResult != null) {
                        parcelObtain.writeInt(1);
                        qNewsResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f685b.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePlayNews(qNewsResult);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final boolean handleCtrlApp(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.f685b.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handleCtrlApp(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc
            public final int playCtrlQuality(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.f685b.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlQuality(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaCenterInternalApiSvc iMediaCenterInternalApiSvc) {
            if (a.f684a != null || iMediaCenterInternalApiSvc == null) {
                return false;
            }
            a.f684a = iMediaCenterInternalApiSvc;
            return true;
        }

        public static IMediaCenterInternalApiSvc getDefaultImpl() {
            return a.f684a;
        }
    }
}
