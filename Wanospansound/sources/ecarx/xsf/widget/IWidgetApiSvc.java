package ecarx.xsf.widget;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IContent;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMediaLists;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.IRecommend;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IWidgetApiSvc extends IInterface {
    void updateCollectMsg(int i, String str) throws RemoteException;

    void updateMediaContent(List<IContent> list) throws RemoteException;

    void updateMediaList(int i, int i2, List<IMedia> list) throws RemoteException;

    void updateMultiMediaList(IMediaLists iMediaLists) throws RemoteException;

    void updateMusicPlayInfo(IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException;

    void updateProgress(long j) throws RemoteException;

    void updateRecommendInfo(IRecommend iRecommend) throws RemoteException;

    public static abstract class Stub extends Binder implements IWidgetApiSvc {
        private static final String DESCRIPTOR = "ecarx.xsf.widget.IWidgetApiSvc";
        static final int TRANSACTION_updateCollectMsg = 7;
        static final int TRANSACTION_updateMediaContent = 6;
        static final int TRANSACTION_updateMediaList = 3;
        static final int TRANSACTION_updateMultiMediaList = 5;
        static final int TRANSACTION_updateMusicPlayInfo = 1;
        static final int TRANSACTION_updateProgress = 2;
        static final int TRANSACTION_updateRecommendInfo = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWidgetApiSvc asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWidgetApiSvc)) {
                return (IWidgetApiSvc) iInterfaceQueryLocalInterface;
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
                    updateMusicPlayInfo(IMusicPlaybackInfo.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateProgress(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMediaList(parcel.readInt(), parcel.readInt(), parcel.createTypedArrayList(IMedia.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateRecommendInfo(IRecommend.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMultiMediaList(parcel.readInt() != 0 ? IMediaLists.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMediaContent(parcel.createTypedArrayList(IContent.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCollectMsg(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IWidgetApiSvc {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IWidgetApiSvc f824a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f825b;

            a(IBinder iBinder) {
                this.f825b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f825b;
            }

            @Override // ecarx.xsf.widget.IWidgetApiSvc
            public final void updateMusicPlayInfo(IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMusicPlaybackInfo != null ? iMusicPlaybackInfo.asBinder() : null);
                    if (!this.f825b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMusicPlayInfo(iMusicPlaybackInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.widget.IWidgetApiSvc
            public final void updateProgress(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.f825b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateProgress(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.widget.IWidgetApiSvc
            public final void updateMediaList(int i, int i2, List<IMedia> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeTypedList(list);
                    if (!this.f825b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMediaList(i, i2, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.widget.IWidgetApiSvc
            public final void updateRecommendInfo(IRecommend iRecommend) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRecommend != null ? iRecommend.asBinder() : null);
                    if (!this.f825b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateRecommendInfo(iRecommend);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.widget.IWidgetApiSvc
            public final void updateMultiMediaList(IMediaLists iMediaLists) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iMediaLists != null) {
                        parcelObtain.writeInt(1);
                        iMediaLists.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f825b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMultiMediaList(iMediaLists);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.widget.IWidgetApiSvc
            public final void updateMediaContent(List<IContent> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (!this.f825b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMediaContent(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.widget.IWidgetApiSvc
            public final void updateCollectMsg(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f825b.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCollectMsg(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWidgetApiSvc iWidgetApiSvc) {
            if (a.f824a != null || iWidgetApiSvc == null) {
                return false;
            }
            a.f824a = iWidgetApiSvc;
            return true;
        }

        public static IWidgetApiSvc getDefaultImpl() {
            return a.f824a;
        }
    }
}
