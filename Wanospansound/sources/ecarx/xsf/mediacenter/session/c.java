package ecarx.xsf.mediacenter.session;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.xsf.mediacenter.IExCallback;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends IExCallback.Stub implements Future<IExContent> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CountDownLatch f740a = new CountDownLatch(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IExContent f741b;

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return false;
    }

    @Override // com.ecarx.eas.xsf.mediacenter.IExCallback
    public final String onAction(int i, String str, String str2, IBinder iBinder) throws RemoteException {
        return null;
    }

    @Override // com.ecarx.eas.xsf.mediacenter.IExCallback
    public final IExContent onExAction(int i, String str, String str2, IExContent iExContent, IBinder iBinder) throws RemoteException {
        Log.d("ExContentGetter", "onExAction:" + i + "," + str + "," + iExContent + "," + iBinder);
        if (i == 257) {
            this.f741b = iExContent;
            this.f740a.countDown();
            return null;
        }
        if (i != 258) {
            return null;
        }
        this.f741b = iExContent;
        this.f740a.countDown();
        return null;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f740a.getCount() == 0;
    }

    @Override // java.util.concurrent.Future
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final IExContent get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        this.f740a.await(j, timeUnit);
        return this.f741b;
    }

    @Override // java.util.concurrent.Future
    public final /* synthetic */ IExContent get() throws ExecutionException, InterruptedException {
        this.f740a.await();
        return this.f741b;
    }
}
