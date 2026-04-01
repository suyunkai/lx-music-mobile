package cz.msebera.android.httpclient.conn;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface ClientConnectionRequest {
    void abortRequest();

    ManagedClientConnection getConnection(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException;
}
