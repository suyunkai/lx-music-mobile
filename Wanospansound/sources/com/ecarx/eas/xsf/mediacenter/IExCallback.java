package com.ecarx.eas.xsf.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IExCallback extends IInterface {
    String onAction(int i, String str, String str2, IBinder iBinder) throws RemoteException;

    IExContent onExAction(int i, String str, String str2, IExContent iExContent, IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements IExCallback {
        private static final String DESCRIPTOR = "com.ecarx.eas.xsf.mediacenter.IExCallback";
        static final int TRANSACTION_onAction = 1;
        static final int TRANSACTION_onExAction = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IExCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IExCallback)) {
                return (IExCallback) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String strOnAction = onAction(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readStrongBinder());
                parcel2.writeNoException();
                parcel2.writeString(strOnAction);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            IExContent iExContentOnExAction = onExAction(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? IExContent.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder());
            parcel2.writeNoException();
            if (iExContentOnExAction != null) {
                parcel2.writeInt(1);
                iExContentOnExAction.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        static class a implements IExCallback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IExCallback f354a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f355b;

            a(IBinder iBinder) {
                this.f355b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f355b;
            }

            @Override // com.ecarx.eas.xsf.mediacenter.IExCallback
            public final String onAction(int i, String str, String str2, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.f355b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onAction(i, str, str2, iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.eas.xsf.mediacenter.IExCallback
            public final IExContent onExAction(int i, String str, String str2, IExContent iExContent, IBinder iBinder) throws RemoteException {
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
                    if (!this.f355b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onExAction(i, str, str2, iExContent, iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IExContent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IExCallback iExCallback) {
            if (a.f354a != null || iExCallback == null) {
                return false;
            }
            a.f354a = iExCallback;
            return true;
        }

        public static IExCallback getDefaultImpl() {
            return a.f354a;
        }
    }
}
