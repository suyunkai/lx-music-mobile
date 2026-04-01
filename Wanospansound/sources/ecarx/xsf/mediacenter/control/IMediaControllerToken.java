package ecarx.xsf.mediacenter.control;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaControllerToken extends IInterface {

    public static abstract class Stub extends Binder implements IMediaControllerToken {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.control.IMediaControllerToken";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaControllerToken asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaControllerToken)) {
                return (IMediaControllerToken) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IMediaControllerToken {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaControllerToken f714a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f715b;

            a(IBinder iBinder) {
                this.f715b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f715b;
            }
        }

        public static boolean setDefaultImpl(IMediaControllerToken iMediaControllerToken) {
            if (a.f714a != null || iMediaControllerToken == null) {
                return false;
            }
            a.f714a = iMediaControllerToken;
            return true;
        }

        public static IMediaControllerToken getDefaultImpl() {
            return a.f714a;
        }
    }
}
