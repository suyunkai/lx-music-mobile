package ecarx.xsf.mediacenter.control;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaController extends IInterface {
    void onControllerChanged(String str) throws RemoteException;

    void updateCurrentProgress(long j) throws RemoteException;

    void updateErrorMsg(int i, String str) throws RemoteException;

    void updateMediaContentTypeList(List<IMediaContentType> list) throws RemoteException;

    void updatePlaybackInfo(IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException;

    void updatePlaylist(int i, List<IMedia> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaController {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.control.IMediaController";
        static final int TRANSACTION_onControllerChanged = 1;
        static final int TRANSACTION_updateCurrentProgress = 5;
        static final int TRANSACTION_updateErrorMsg = 4;
        static final int TRANSACTION_updateMediaContentTypeList = 6;
        static final int TRANSACTION_updatePlaybackInfo = 3;
        static final int TRANSACTION_updatePlaylist = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaController)) {
                return (IMediaController) iInterfaceQueryLocalInterface;
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
                    onControllerChanged(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePlaylist(parcel.readInt(), parcel.createTypedArrayList(IMedia.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePlaybackInfo(IMusicPlaybackInfo.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateErrorMsg(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCurrentProgress(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMediaContentTypeList(parcel.createTypedArrayList(IMediaContentType.CREATOR));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IMediaController {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaController f710a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f711b;

            a(IBinder iBinder) {
                this.f711b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f711b;
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaController
            public final void onControllerChanged(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.f711b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onControllerChanged(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaController
            public final void updatePlaylist(int i, List<IMedia> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (!this.f711b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePlaylist(i, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaController
            public final void updatePlaybackInfo(IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMusicPlaybackInfo != null ? iMusicPlaybackInfo.asBinder() : null);
                    if (!this.f711b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePlaybackInfo(iMusicPlaybackInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaController
            public final void updateErrorMsg(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f711b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateErrorMsg(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaController
            public final void updateCurrentProgress(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.f711b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCurrentProgress(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaController
            public final void updateMediaContentTypeList(List<IMediaContentType> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (!this.f711b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMediaContentTypeList(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaController iMediaController) {
            if (a.f710a != null || iMediaController == null) {
                return false;
            }
            a.f710a = iMediaController;
            return true;
        }

        public static IMediaController getDefaultImpl() {
            return a.f710a;
        }
    }
}
