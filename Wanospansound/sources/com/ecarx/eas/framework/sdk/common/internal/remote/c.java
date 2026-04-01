package com.ecarx.eas.framework.sdk.common.internal.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessageRemote;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessageRemote;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class c extends Binder implements IInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f43a = "com.ecarx.eas.framework.sdk.EASFrameworkServiceRemote";

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    public static IEASFrameworkRemoteService a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(f43a);
        if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IEASFrameworkRemoteService)) {
            return (IEASFrameworkRemoteService) iInterfaceQueryLocalInterface;
        }
        return new a(iBinder);
    }

    private static class a implements IEASFrameworkRemoteService {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IBinder f44a;

        public a(IBinder iBinder) {
            this.f44a = iBinder;
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.remote.IEASFrameworkRemoteService
        public final void a(String[] strArr) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(c.f43a);
                parcelObtain.writeStringArray(strArr);
                this.f44a.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.remote.IEASFrameworkRemoteService
        public final boolean a(d dVar) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(c.f43a);
                parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                this.f44a.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.remote.IEASFrameworkRemoteService
        public final EASFrameworkRetMessageRemote asyncCall(EASFrameworkMessageRemote eASFrameworkMessageRemote, IEASFrameworkCallbackRemote iEASFrameworkCallbackRemote) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(c.f43a);
                if (eASFrameworkMessageRemote != null) {
                    parcelObtain.writeInt(1);
                    eASFrameworkMessageRemote.writeToParcel(parcelObtain, 0);
                } else {
                    parcelObtain.writeInt(0);
                }
                parcelObtain.writeStrongBinder(iEASFrameworkCallbackRemote != null ? iEASFrameworkCallbackRemote.asBinder() : null);
                this.f44a.transact(5, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0 ? EASFrameworkRetMessageRemote.CREATOR.createFromParcel(parcelObtain2) : null;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.remote.IEASFrameworkRemoteService
        public final List<String> getRemoteServices() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(c.f43a);
                this.f44a.transact(4, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.createStringArrayList();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.remote.IEASFrameworkRemoteService
        public final EASFrameworkRetMessageRemote registerListener(EASFrameworkMessageRemote eASFrameworkMessageRemote, IEASFrameworkCallbackRemote iEASFrameworkCallbackRemote) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(c.f43a);
                if (eASFrameworkMessageRemote != null) {
                    parcelObtain.writeInt(1);
                    eASFrameworkMessageRemote.writeToParcel(parcelObtain, 0);
                } else {
                    parcelObtain.writeInt(0);
                }
                parcelObtain.writeStrongBinder(iEASFrameworkCallbackRemote != null ? iEASFrameworkCallbackRemote.asBinder() : null);
                this.f44a.transact(6, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0 ? EASFrameworkRetMessageRemote.CREATOR.createFromParcel(parcelObtain2) : null;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.remote.IEASFrameworkRemoteService
        public final EASFrameworkRetMessageRemote unregisterListener(EASFrameworkMessageRemote eASFrameworkMessageRemote, String str) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(c.f43a);
                if (eASFrameworkMessageRemote != null) {
                    parcelObtain.writeInt(1);
                    eASFrameworkMessageRemote.writeToParcel(parcelObtain, 0);
                } else {
                    parcelObtain.writeInt(0);
                }
                parcelObtain.writeString(str);
                this.f44a.transact(7, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0 ? EASFrameworkRetMessageRemote.CREATOR.createFromParcel(parcelObtain2) : null;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.f44a;
        }
    }
}
