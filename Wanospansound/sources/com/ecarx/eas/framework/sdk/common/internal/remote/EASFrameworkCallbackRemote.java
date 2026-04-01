package com.ecarx.eas.framework.sdk.common.internal.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.sdk.openapi.msg.EASFrameworkRemoteCallbackMessage;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EASFrameworkCallbackRemote extends Binder implements IEASFrameworkCallbackRemote {
    static final String DESCRIPTOR = "com.ecarx.eas.framework.sdk.IEASCoreCallbackRemote";
    static final int TRANSACTION_onCall = 1;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    public EASFrameworkCallbackRemote() {
        attachInterface(this, DESCRIPTOR);
    }

    public static IEASFrameworkCallbackRemote asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
        if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IEASFrameworkCallbackRemote)) {
            return (IEASFrameworkCallbackRemote) iInterfaceQueryLocalInterface;
        }
        return new a(iBinder);
    }

    @Override // android.os.Binder
    protected boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            parcel.enforceInterface(DESCRIPTOR);
            onCall(parcel.readInt() != 0 ? EASFrameworkRemoteCallbackMessage.CREATOR.createFromParcel(parcel) : null);
            return true;
        }
        if (i == 1598968902) {
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
        return super.onTransact(i, parcel, parcel2, i2);
    }

    private static class a implements IEASFrameworkCallbackRemote {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IBinder f38a;

        a(IBinder iBinder) {
            this.f38a = iBinder;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.f38a;
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.remote.IEASFrameworkCallbackRemote
        public final void onCall(EASFrameworkRemoteCallbackMessage eASFrameworkRemoteCallbackMessage) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(EASFrameworkCallbackRemote.DESCRIPTOR);
                if (eASFrameworkRemoteCallbackMessage != null) {
                    parcelObtain.writeInt(1);
                    eASFrameworkRemoteCallbackMessage.writeToParcel(parcelObtain, 0);
                } else {
                    parcelObtain.writeInt(0);
                }
                this.f38a.transact(1, parcelObtain, null, 1);
            } finally {
                parcelObtain.recycle();
            }
        }
    }
}
