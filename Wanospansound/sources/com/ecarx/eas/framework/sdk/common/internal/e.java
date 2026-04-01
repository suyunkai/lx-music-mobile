package com.ecarx.eas.framework.sdk.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessage;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class e extends Binder implements IInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f32a = "com.ecarx.eas.framework.sdk.IEASFrameworkService";

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    public static IEASFrameworkService a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(f32a);
        if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IEASFrameworkService)) {
            return (IEASFrameworkService) iInterfaceQueryLocalInterface;
        }
        return new a(iBinder);
    }

    private static class a implements IEASFrameworkService {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IBinder f33a;

        public a(IBinder iBinder) {
            this.f33a = iBinder;
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService
        public final void a(String[] strArr) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(e.f32a);
                parcelObtain.writeStringArray(strArr);
                this.f33a.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService
        public final boolean a(f fVar) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(e.f32a);
                parcelObtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                this.f33a.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService
        public final EASFrameworkRetMessage call(EASFrameworkMessage eASFrameworkMessage) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(e.f32a);
                if (eASFrameworkMessage != null) {
                    parcelObtain.writeInt(1);
                    eASFrameworkMessage.writeToParcel(parcelObtain, 0);
                } else {
                    parcelObtain.writeInt(0);
                }
                this.f33a.transact(4, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0 ? EASFrameworkRetMessage.CREATOR.createFromParcel(parcelObtain2) : null;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService
        public final EASFrameworkRetMessage asyncCall(EASFrameworkMessage eASFrameworkMessage, IEASFrameworkCallback iEASFrameworkCallback) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(e.f32a);
                if (eASFrameworkMessage != null) {
                    parcelObtain.writeInt(1);
                    eASFrameworkMessage.writeToParcel(parcelObtain, 0);
                } else {
                    parcelObtain.writeInt(0);
                }
                parcelObtain.writeStrongBinder(iEASFrameworkCallback != null ? iEASFrameworkCallback.asBinder() : null);
                this.f33a.transact(5, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0 ? EASFrameworkRetMessage.CREATOR.createFromParcel(parcelObtain2) : null;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService
        public final EASFrameworkRetMessage asyncBinderCall(EASFrameworkMessage eASFrameworkMessage, IBinder iBinder) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(e.f32a);
                if (eASFrameworkMessage != null) {
                    parcelObtain.writeInt(1);
                    eASFrameworkMessage.writeToParcel(parcelObtain, 0);
                } else {
                    parcelObtain.writeInt(0);
                }
                parcelObtain.writeStrongBinder(iBinder);
                this.f33a.transact(6, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0 ? EASFrameworkRetMessage.CREATOR.createFromParcel(parcelObtain2) : null;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService
        public final List<String> getAvailableServices() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(e.f32a);
                this.f33a.transact(7, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.createStringArrayList();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService
        public final IBinder getService(int i, int i2, String str, String str2) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(e.f32a);
                parcelObtain.writeInt(i);
                parcelObtain.writeInt(i2);
                parcelObtain.writeString(str);
                parcelObtain.writeString(str2);
                this.f33a.transact(8, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readStrongBinder();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService
        public final List<String> getAvailableEASServices() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(e.f32a);
                this.f33a.transact(9, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.createStringArrayList();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.f33a;
        }
    }
}
