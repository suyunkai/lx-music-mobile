package com.ecarx.eas.framework.sdk.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.sdk.openapi.msg.EASFrameworkCallbackMessage;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EASFrameworkCallback extends Binder implements IEASFrameworkCallback {
    static final String DESCRIPTOR = "com.ecarx.eas.framework.sdk.IEASFrameworkCallback";
    static final int TRANSACTION_onCall = 1;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    public EASFrameworkCallback() {
        attachInterface(this, DESCRIPTOR);
    }

    public static IEASFrameworkCallback asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
        if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IEASFrameworkCallback)) {
            return (IEASFrameworkCallback) iInterfaceQueryLocalInterface;
        }
        return new a(iBinder);
    }

    @Override // android.os.Binder
    protected boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            parcel.enforceInterface(DESCRIPTOR);
            onCall(parcel.readInt() != 0 ? EASFrameworkCallbackMessage.CREATOR.createFromParcel(parcel) : null);
            return true;
        }
        if (i == 1598968902) {
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
        return super.onTransact(i, parcel, parcel2, i2);
    }

    private static class a implements IEASFrameworkCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IBinder f23a;

        a(IBinder iBinder) {
            this.f23a = iBinder;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.f23a;
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkCallback
        public final void onCall(EASFrameworkCallbackMessage eASFrameworkCallbackMessage) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(EASFrameworkCallback.DESCRIPTOR);
                if (eASFrameworkCallbackMessage != null) {
                    parcelObtain.writeInt(1);
                    eASFrameworkCallbackMessage.writeToParcel(parcelObtain, 0);
                } else {
                    parcelObtain.writeInt(0);
                }
                this.f23a.transact(1, parcelObtain, null, 1);
            } finally {
                parcelObtain.recycle();
            }
        }
    }
}
