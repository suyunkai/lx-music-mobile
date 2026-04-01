package ecarx.xsf.mediacenter.vr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IMusicIntentObserver extends IInterface {
    boolean handleCtrlApp(int i) throws RemoteException;

    boolean handleCtrlMediaClient(int i, int i2) throws RemoteException;

    boolean handlePlayMusic(QMusicResult qMusicResult) throws RemoteException;

    boolean handleSearchMusic(QMusicResult qMusicResult) throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicIntentObserver {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.vr.IMusicIntentObserver";
        static final int TRANSACTION_handleCtrlApp = 3;
        static final int TRANSACTION_handleCtrlMediaClient = 4;
        static final int TRANSACTION_handlePlayMusic = 2;
        static final int TRANSACTION_handleSearchMusic = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMusicIntentObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMusicIntentObserver)) {
                return (IMusicIntentObserver) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zHandleSearchMusic = handleSearchMusic(parcel.readInt() != 0 ? QMusicResult.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(zHandleSearchMusic ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zHandlePlayMusic = handlePlayMusic(parcel.readInt() != 0 ? QMusicResult.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(zHandlePlayMusic ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zHandleCtrlApp = handleCtrlApp(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(zHandleCtrlApp ? 1 : 0);
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
            boolean zHandleCtrlMediaClient = handleCtrlMediaClient(parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeInt(zHandleCtrlMediaClient ? 1 : 0);
            return true;
        }

        static class a implements IMusicIntentObserver {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMusicIntentObserver f808a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f809b;

            a(IBinder iBinder) {
                this.f809b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f809b;
            }

            @Override // ecarx.xsf.mediacenter.vr.IMusicIntentObserver
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
                    if (!this.f809b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handleSearchMusic(qMusicResult);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.vr.IMusicIntentObserver
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
                    if (!this.f809b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePlayMusic(qMusicResult);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.vr.IMusicIntentObserver
            public final boolean handleCtrlApp(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.f809b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handleCtrlApp(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.vr.IMusicIntentObserver
            public final boolean handleCtrlMediaClient(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.f809b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handleCtrlMediaClient(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicIntentObserver iMusicIntentObserver) {
            if (a.f808a != null || iMusicIntentObserver == null) {
                return false;
            }
            a.f808a = iMusicIntentObserver;
            return true;
        }

        public static IMusicIntentObserver getDefaultImpl() {
            return a.f808a;
        }
    }
}
