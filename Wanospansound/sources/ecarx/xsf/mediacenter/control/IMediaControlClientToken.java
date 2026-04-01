package ecarx.xsf.mediacenter.control;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaControlClientToken extends IInterface {

    public static abstract class Stub extends Binder implements IMediaControlClientToken {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.control.IMediaControlClientToken";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaControlClientToken asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaControlClientToken)) {
                return (IMediaControlClientToken) iInterfaceQueryLocalInterface;
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

        static class a implements IMediaControlClientToken {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaControlClientToken f708a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f709b;

            a(IBinder iBinder) {
                this.f709b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f709b;
            }
        }

        public static boolean setDefaultImpl(IMediaControlClientToken iMediaControlClientToken) {
            if (a.f708a != null || iMediaControlClientToken == null) {
                return false;
            }
            a.f708a = iMediaControlClientToken;
            return true;
        }

        public static IMediaControlClientToken getDefaultImpl() {
            return a.f708a;
        }
    }
}
