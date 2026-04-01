package ecarx.xsf.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.widget.IWidgetApiSvc;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaCenterWidgetApiSvc extends IInterface {
    void ctrlPauseMediaList(int i) throws RemoteException;

    void ctrlPlayMediaList(int i) throws RemoteException;

    List<IContent> getContentList() throws RemoteException;

    IExContent getExData(int i, String str, String str2, IExContent iExContent, IBinder iBinder) throws RemoteException;

    int getMediaListSourceType() throws RemoteException;

    int getMediaListType() throws RemoteException;

    IMediaLists getMultiMediaList(int[] iArr) throws RemoteException;

    IMusicPlaybackInfo getMusicPlaybackInfo() throws RemoteException;

    List<IMedia> getPlayList() throws RemoteException;

    int playCtrlCancelRecommend() throws RemoteException;

    int playCtrlCollect(int i, boolean z) throws RemoteException;

    int playCtrlNext() throws RemoteException;

    int playCtrlPause() throws RemoteException;

    int playCtrlPlay() throws RemoteException;

    int playCtrlPlayRecommend() throws RemoteException;

    int playCtrlPrevious() throws RemoteException;

    int selectListMediaPlay(int i, int i2, String str) throws RemoteException;

    int selectMediaPlay(int i, String str) throws RemoteException;

    void setWidgetApiSvc(IWidgetApiSvc iWidgetApiSvc) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaCenterWidgetApiSvc {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc";
        static final int TRANSACTION_ctrlPauseMediaList = 18;
        static final int TRANSACTION_ctrlPlayMediaList = 17;
        static final int TRANSACTION_getContentList = 15;
        static final int TRANSACTION_getExData = 19;
        static final int TRANSACTION_getMediaListSourceType = 4;
        static final int TRANSACTION_getMediaListType = 3;
        static final int TRANSACTION_getMultiMediaList = 16;
        static final int TRANSACTION_getMusicPlaybackInfo = 2;
        static final int TRANSACTION_getPlayList = 5;
        static final int TRANSACTION_playCtrlCancelRecommend = 11;
        static final int TRANSACTION_playCtrlCollect = 13;
        static final int TRANSACTION_playCtrlNext = 8;
        static final int TRANSACTION_playCtrlPause = 7;
        static final int TRANSACTION_playCtrlPlay = 6;
        static final int TRANSACTION_playCtrlPlayRecommend = 10;
        static final int TRANSACTION_playCtrlPrevious = 9;
        static final int TRANSACTION_selectListMediaPlay = 14;
        static final int TRANSACTION_selectMediaPlay = 12;
        static final int TRANSACTION_setWidgetApiSvc = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaCenterWidgetApiSvc asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaCenterWidgetApiSvc)) {
                return (IMediaCenterWidgetApiSvc) iInterfaceQueryLocalInterface;
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
                    setWidgetApiSvc(IWidgetApiSvc.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMusicPlaybackInfo musicPlaybackInfo = getMusicPlaybackInfo();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(musicPlaybackInfo != null ? musicPlaybackInfo.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mediaListType = getMediaListType();
                    parcel2.writeNoException();
                    parcel2.writeInt(mediaListType);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mediaListSourceType = getMediaListSourceType();
                    parcel2.writeNoException();
                    parcel2.writeInt(mediaListSourceType);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<IMedia> playList = getPlayList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(playList);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlPlay = playCtrlPlay();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlPlay);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlPause = playCtrlPause();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlPause);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlNext = playCtrlNext();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlNext);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlPrevious = playCtrlPrevious();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlPrevious);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlPlayRecommend = playCtrlPlayRecommend();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlPlayRecommend);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlCancelRecommend = playCtrlCancelRecommend();
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlCancelRecommend);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSelectMediaPlay = selectMediaPlay(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSelectMediaPlay);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPlayCtrlCollect = playCtrlCollect(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iPlayCtrlCollect);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSelectListMediaPlay = selectListMediaPlay(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSelectListMediaPlay);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<IContent> contentList = getContentList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(contentList);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMediaLists multiMediaList = getMultiMediaList(parcel.createIntArray());
                    parcel2.writeNoException();
                    if (multiMediaList != null) {
                        parcel2.writeInt(1);
                        multiMediaList.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    ctrlPlayMediaList(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    ctrlPauseMediaList(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    IExContent exData = getExData(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? IExContent.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder());
                    parcel2.writeNoException();
                    if (exData != null) {
                        parcel2.writeInt(1);
                        exData.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IMediaCenterWidgetApiSvc {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaCenterWidgetApiSvc f688a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f689b;

            a(IBinder iBinder) {
                this.f689b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f689b;
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final void setWidgetApiSvc(IWidgetApiSvc iWidgetApiSvc) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWidgetApiSvc != null ? iWidgetApiSvc.asBinder() : null);
                    if (!this.f689b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWidgetApiSvc(iWidgetApiSvc);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final IMusicPlaybackInfo getMusicPlaybackInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicPlaybackInfo();
                    }
                    parcelObtain2.readException();
                    return IMusicPlaybackInfo.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int getMediaListType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaListType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int getMediaListSourceType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaListSourceType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final List<IMedia> getPlayList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlayList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(IMedia.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int playCtrlPlay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPlay();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int playCtrlPause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPause();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int playCtrlNext() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlNext();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int playCtrlPrevious() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPrevious();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int playCtrlPlayRecommend() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlPlayRecommend();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int playCtrlCancelRecommend() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlCancelRecommend();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int selectMediaPlay(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f689b.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().selectMediaPlay(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int playCtrlCollect(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.f689b.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playCtrlCollect(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final int selectListMediaPlay(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.f689b.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().selectListMediaPlay(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final List<IContent> getContentList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f689b.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContentList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(IContent.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final IMediaLists getMultiMediaList(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.f689b.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMultiMediaList(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IMediaLists.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final void ctrlPlayMediaList(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.f689b.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ctrlPlayMediaList(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final void ctrlPauseMediaList(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.f689b.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ctrlPauseMediaList(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc
            public final IExContent getExData(int i, String str, String str2, IExContent iExContent, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (iExContent != null) {
                        parcelObtain.writeInt(1);
                        iExContent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.f689b.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getExData(i, str, str2, iExContent, iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IExContent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc) {
            if (a.f688a != null || iMediaCenterWidgetApiSvc == null) {
                return false;
            }
            a.f688a = iMediaCenterWidgetApiSvc;
            return true;
        }

        public static IMediaCenterWidgetApiSvc getDefaultImpl() {
            return a.f688a;
        }
    }
}
