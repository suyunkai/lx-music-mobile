package ecarx.xsf.mediacenter;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IRecommend extends IInterface {
    String getArtist() throws RemoteException;

    Uri getArtwork() throws RemoteException;

    String getId() throws RemoteException;

    int getRecommendType() throws RemoteException;

    String getTextDescription() throws RemoteException;

    String getTitle() throws RemoteException;

    public static abstract class Stub extends Binder implements IRecommend {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.IRecommend";
        static final int TRANSACTION_getArtist = 4;
        static final int TRANSACTION_getArtwork = 5;
        static final int TRANSACTION_getId = 2;
        static final int TRANSACTION_getRecommendType = 1;
        static final int TRANSACTION_getTextDescription = 6;
        static final int TRANSACTION_getTitle = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecommend asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRecommend)) {
                return (IRecommend) iInterfaceQueryLocalInterface;
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
                    int recommendType = getRecommendType();
                    parcel2.writeNoException();
                    parcel2.writeInt(recommendType);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String id = getId();
                    parcel2.writeNoException();
                    parcel2.writeString(id);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String title = getTitle();
                    parcel2.writeNoException();
                    parcel2.writeString(title);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String artist = getArtist();
                    parcel2.writeNoException();
                    parcel2.writeString(artist);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    Uri artwork = getArtwork();
                    parcel2.writeNoException();
                    if (artwork != null) {
                        parcel2.writeInt(1);
                        artwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String textDescription = getTextDescription();
                    parcel2.writeNoException();
                    parcel2.writeString(textDescription);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IRecommend {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IRecommend f698a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f699b;

            a(IBinder iBinder) {
                this.f699b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f699b;
            }

            @Override // ecarx.xsf.mediacenter.IRecommend
            public final int getRecommendType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f699b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecommendType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IRecommend
            public final String getId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f699b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IRecommend
            public final String getTitle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f699b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTitle();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IRecommend
            public final String getArtist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f699b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getArtist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IRecommend
            public final Uri getArtwork() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f699b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getArtwork();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IRecommend
            public final String getTextDescription() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f699b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTextDescription();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecommend iRecommend) {
            if (a.f698a != null || iRecommend == null) {
                return false;
            }
            a.f698a = iRecommend;
            return true;
        }

        public static IRecommend getDefaultImpl() {
            return a.f698a;
        }
    }
}
