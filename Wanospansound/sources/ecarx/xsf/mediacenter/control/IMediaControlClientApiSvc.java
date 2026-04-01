package ecarx.xsf.mediacenter.control;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.control.IMediaControlClient;
import ecarx.xsf.mediacenter.control.IMediaControlClientToken;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaControlClientApiSvc extends IInterface {
    IMediaControlClientToken register(String str, IMediaControlClient iMediaControlClient) throws RemoteException;

    boolean requestControlled(IMediaControlClientToken iMediaControlClientToken) throws RemoteException;

    boolean unregister(IMediaControlClientToken iMediaControlClientToken) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaControlClientApiSvc {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.control.IMediaControlClientApiSvc";
        static final int TRANSACTION_register = 1;
        static final int TRANSACTION_requestControlled = 3;
        static final int TRANSACTION_unregister = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaControlClientApiSvc asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaControlClientApiSvc)) {
                return (IMediaControlClientApiSvc) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                IMediaControlClientToken iMediaControlClientTokenRegister = register(parcel.readString(), IMediaControlClient.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(iMediaControlClientTokenRegister != null ? iMediaControlClientTokenRegister.asBinder() : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zUnregister = unregister(IMediaControlClientToken.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zUnregister ? 1 : 0);
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
            boolean zRequestControlled = requestControlled(IMediaControlClientToken.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeInt(zRequestControlled ? 1 : 0);
            return true;
        }

        static class a implements IMediaControlClientApiSvc {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaControlClientApiSvc f706a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f707b;

            a(IBinder iBinder) {
                this.f707b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f707b;
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClientApiSvc
            public final IMediaControlClientToken register(String str, IMediaControlClient iMediaControlClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iMediaControlClient != null ? iMediaControlClient.asBinder() : null);
                    if (!this.f707b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().register(str, iMediaControlClient);
                    }
                    parcelObtain2.readException();
                    return IMediaControlClientToken.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClientApiSvc
            public final boolean unregister(IMediaControlClientToken iMediaControlClientToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControlClientToken != null ? iMediaControlClientToken.asBinder() : null);
                    if (!this.f707b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregister(iMediaControlClientToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.control.IMediaControlClientApiSvc
            public final boolean requestControlled(IMediaControlClientToken iMediaControlClientToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControlClientToken != null ? iMediaControlClientToken.asBinder() : null);
                    if (!this.f707b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestControlled(iMediaControlClientToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaControlClientApiSvc iMediaControlClientApiSvc) {
            if (a.f706a != null || iMediaControlClientApiSvc == null) {
                return false;
            }
            a.f706a = iMediaControlClientApiSvc;
            return true;
        }

        public static IMediaControlClientApiSvc getDefaultImpl() {
            return a.f706a;
        }
    }
}
