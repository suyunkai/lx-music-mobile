package ecarx.xsf.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaCenterClientToken extends IInterface {

    public static abstract class Stub extends Binder implements IMediaCenterClientToken {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.IMediaCenterClientToken";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaCenterClientToken asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaCenterClientToken)) {
                return (IMediaCenterClientToken) iInterfaceQueryLocalInterface;
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

        static class a implements IMediaCenterClientToken {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaCenterClientToken f682a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f683b;

            a(IBinder iBinder) {
                this.f683b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f683b;
            }
        }

        public static boolean setDefaultImpl(IMediaCenterClientToken iMediaCenterClientToken) {
            if (a.f682a != null || iMediaCenterClientToken == null) {
                return false;
            }
            a.f682a = iMediaCenterClientToken;
            return true;
        }

        public static IMediaCenterClientToken getDefaultImpl() {
            return a.f682a;
        }
    }
}
