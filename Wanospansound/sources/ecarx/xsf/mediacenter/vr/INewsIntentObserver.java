package ecarx.xsf.mediacenter.vr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface INewsIntentObserver extends IInterface {
    boolean handlePlayNews(QNewsResult qNewsResult) throws RemoteException;

    public static abstract class Stub extends Binder implements INewsIntentObserver {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.vr.INewsIntentObserver";
        static final int TRANSACTION_handlePlayNews = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INewsIntentObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INewsIntentObserver)) {
                return (INewsIntentObserver) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean zHandlePlayNews = handlePlayNews(parcel.readInt() != 0 ? QNewsResult.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            parcel2.writeInt(zHandlePlayNews ? 1 : 0);
            return true;
        }

        static class a implements INewsIntentObserver {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static INewsIntentObserver f810a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f811b;

            a(IBinder iBinder) {
                this.f811b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f811b;
            }

            @Override // ecarx.xsf.mediacenter.vr.INewsIntentObserver
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
                    if (!this.f811b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePlayNews(qNewsResult);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INewsIntentObserver iNewsIntentObserver) {
            if (a.f810a != null || iNewsIntentObserver == null) {
                return false;
            }
            a.f810a = iNewsIntentObserver;
            return true;
        }

        public static INewsIntentObserver getDefaultImpl() {
            return a.f810a;
        }
    }
}
