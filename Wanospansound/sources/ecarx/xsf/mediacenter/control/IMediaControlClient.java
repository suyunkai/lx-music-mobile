package ecarx.xsf.mediacenter.control;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaControlClient extends IInterface {
    List<IMediaContentType> getMediaContentTypeList() throws RemoteException;

    void onControlledChanged(String str) throws RemoteException;

    boolean onPause(int i) throws RemoteException;

    boolean onPauseNow() throws RemoteException;

    boolean onPlay(int i, String str) throws RemoteException;

    boolean onPlayByContent(int i, String str) throws RemoteException;

    boolean onPlayByMediaId(int i, String str) throws RemoteException;

    boolean onResumeNow() throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaControlClient {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.control.IMediaControlClient";
        static final int TRANSACTION_getMediaContentTypeList = 2;
        static final int TRANSACTION_onControlledChanged = 1;
        static final int TRANSACTION_onPause = 5;
        static final int TRANSACTION_onPauseNow = 7;
        static final int TRANSACTION_onPlay = 3;
        static final int TRANSACTION_onPlayByContent = 4;
        static final int TRANSACTION_onPlayByMediaId = 8;
        static final int TRANSACTION_onResumeNow = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaControlClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaControlClient)) {
                return (IMediaControlClient) iInterfaceQueryLocalInterface;
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
                    onControlledChanged(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<IMediaContentType> mediaContentTypeList = getMediaContentTypeList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(mediaContentTypeList);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOnPlay = onPlay(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zOnPlay ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOnPlayByContent = onPlayByContent(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zOnPlayByContent ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOnPause = onPause(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zOnPause ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOnResumeNow = onResumeNow();
                    parcel2.writeNoException();
                    parcel2.writeInt(zOnResumeNow ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOnPauseNow = onPauseNow();
                    parcel2.writeNoException();
                    parcel2.writeInt(zOnPauseNow ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOnPlayByMediaId = onPlayByMediaId(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zOnPlayByMediaId ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IMediaControlClient {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaControlClient f704a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f705b;

            a(IBinder iBinder) {
                this.f705b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f705b;
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
            public final void onControlledChanged(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.f705b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onControlledChanged(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
            public final List<IMediaContentType> getMediaContentTypeList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f705b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaContentTypeList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(IMediaContentType.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
            public final boolean onPlay(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f705b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onPlay(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
            public final boolean onPlayByContent(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f705b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onPlayByContent(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
            public final boolean onPause(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.f705b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onPause(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
            public final boolean onResumeNow() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f705b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onResumeNow();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
            public final boolean onPauseNow() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f705b.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onPauseNow();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
            public final boolean onPlayByMediaId(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f705b.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onPlayByMediaId(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaControlClient iMediaControlClient) {
            if (a.f704a != null || iMediaControlClient == null) {
                return false;
            }
            a.f704a = iMediaControlClient;
            return true;
        }

        public static IMediaControlClient getDefaultImpl() {
            return a.f704a;
        }
    }
}
