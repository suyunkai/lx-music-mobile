package cz.msebera.android.httpclient.conn;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface ConnectionReleaseTrigger {
    void abortConnection() throws IOException;

    void releaseConnection() throws IOException;
}
