package cz.msebera.android.httpclient.client.cache;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface HttpCacheUpdateCallback {
    HttpCacheEntry update(HttpCacheEntry httpCacheEntry) throws IOException;
}
