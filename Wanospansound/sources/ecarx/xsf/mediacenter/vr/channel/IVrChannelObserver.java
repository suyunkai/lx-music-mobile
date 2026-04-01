package ecarx.xsf.mediacenter.vr.channel;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IVrChannelObserver extends IInterface {
    void handleVrChannelSemantic(int i, int i2, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IVrChannelObserver {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.vr.channel.IVrChannelObserver";
        static final int TRANSACTION_handleVrChannelSemantic = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrChannelObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IVrChannelObserver)) {
                return (IVrChannelObserver) iInterfaceQueryLocalInterface;
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
            handleVrChannelSemantic(parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        static class a implements IVrChannelObserver {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IVrChannelObserver f822a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f823b;

            a(IBinder iBinder) {
                this.f823b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f823b;
            }

            @Override // ecarx.xsf.mediacenter.vr.channel.IVrChannelObserver
            public final void handleVrChannelSemantic(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.f823b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleVrChannelSemantic(i, i2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVrChannelObserver iVrChannelObserver) {
            if (a.f822a != null || iVrChannelObserver == null) {
                return false;
            }
            a.f822a = iVrChannelObserver;
            return true;
        }

        public static IVrChannelObserver getDefaultImpl() {
            return a.f822a;
        }
    }
}
