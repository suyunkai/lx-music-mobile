package com.ecarx.eas.framework.sdk.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class j extends Binder implements IServicePool {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f36a = "com.ecarx.sdk.openapi.IServicePool";

    @Override // com.ecarx.eas.framework.sdk.common.internal.IServicePool
    public final boolean addRemoteServiceCallback(g gVar) throws RemoteException {
        return false;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.IServicePool
    public final List<String> getAvailableServices() throws RemoteException {
        return null;
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.IServicePool
    public final IBinder getService(int i, int i2, String str, String str2) throws RemoteException {
        return null;
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.IServicePool
    public final boolean removeRemoteServiceCallback(g gVar) throws RemoteException {
        return false;
    }

    public static IServicePool a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(f36a);
        if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IServicePool)) {
            return (IServicePool) iInterfaceQueryLocalInterface;
        }
        return new a(iBinder);
    }

    private static class a implements IServicePool {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IBinder f37a;

        public a(IBinder iBinder) {
            this.f37a = iBinder;
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IServicePool
        public final List<String> getAvailableServices() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(j.f36a);
                this.f37a.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.createStringArrayList();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IServicePool
        public final IBinder getService(int i, int i2, String str, String str2) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(j.f36a);
                parcelObtain.writeInt(i);
                parcelObtain.writeInt(i2);
                parcelObtain.writeString(str);
                parcelObtain.writeString(str2);
                this.f37a.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readStrongBinder();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IServicePool
        public final boolean addRemoteServiceCallback(g gVar) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(j.f36a);
                parcelObtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                this.f37a.transact(3, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IServicePool
        public final boolean removeRemoteServiceCallback(g gVar) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(j.f36a);
                parcelObtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                this.f37a.transact(4, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.f37a;
        }
    }
}
