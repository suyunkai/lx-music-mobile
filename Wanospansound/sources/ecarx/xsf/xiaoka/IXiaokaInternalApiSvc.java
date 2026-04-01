package ecarx.xsf.xiaoka;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IAllPlaybackInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface IXiaokaInternalApiSvc extends IInterface {
    void onMediaCenterMediaPlayingStatus(boolean z) throws RemoteException;

    void onMediaCenterPlayingMediaInfo(IAllPlaybackInfo iAllPlaybackInfo) throws RemoteException;

    void onSourceTypeListChanged(int[] iArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IXiaokaInternalApiSvc {
        private static final String DESCRIPTOR = "ecarx.xsf.xiaoka.IXiaokaInternalApiSvc";
        static final int TRANSACTION_onMediaCenterMediaPlayingStatus = 1;
        static final int TRANSACTION_onMediaCenterPlayingMediaInfo = 2;
        static final int TRANSACTION_onSourceTypeListChanged = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXiaokaInternalApiSvc asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IXiaokaInternalApiSvc)) {
                return (IXiaokaInternalApiSvc) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onMediaCenterMediaPlayingStatus(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onMediaCenterPlayingMediaInfo(IAllPlaybackInfo.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onSourceTypeListChanged(parcel.createIntArray());
            parcel2.writeNoException();
            return true;
        }

        static class a implements IXiaokaInternalApiSvc {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IXiaokaInternalApiSvc f835a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f836b;

            a(IBinder iBinder) {
                this.f836b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f836b;
            }

            @Override // ecarx.xsf.xiaoka.IXiaokaInternalApiSvc
            public final void onMediaCenterMediaPlayingStatus(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.f836b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaCenterMediaPlayingStatus(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.xiaoka.IXiaokaInternalApiSvc
            public final void onMediaCenterPlayingMediaInfo(IAllPlaybackInfo iAllPlaybackInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAllPlaybackInfo != null ? iAllPlaybackInfo.asBinder() : null);
                    if (!this.f836b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaCenterPlayingMediaInfo(iAllPlaybackInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.xiaoka.IXiaokaInternalApiSvc
            public final void onSourceTypeListChanged(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.f836b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSourceTypeListChanged(iArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IXiaokaInternalApiSvc iXiaokaInternalApiSvc) {
            if (a.f835a != null || iXiaokaInternalApiSvc == null) {
                return false;
            }
            a.f835a = iXiaokaInternalApiSvc;
            return true;
        }

        public static IXiaokaInternalApiSvc getDefaultImpl() {
            return a.f835a;
        }
    }
}
