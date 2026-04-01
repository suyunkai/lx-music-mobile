package com.ecarx.eas.sdk.device.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {

    public static abstract class a extends Binder implements c {
        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("ecarx.xsf.gkuiservice.policy.IFunPolicyClientToken");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof c)) {
                return (c) iInterfaceQueryLocalInterface;
            }
            return new C0014a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("ecarx.xsf.gkuiservice.policy.IFunPolicyClientToken");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.a.a.c$a$a, reason: collision with other inner class name */
        private static class C0014a implements c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f58a;

            C0014a(IBinder iBinder) {
                this.f58a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f58a;
            }
        }
    }
}
