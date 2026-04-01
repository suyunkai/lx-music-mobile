package cz.msebera.android.httpclient.client.methods;

import java.net.URI;

/* JADX INFO: loaded from: classes3.dex */
public class HttpGet extends HttpRequestBase {
    public static final String METHOD_NAME = "GET";

    @Override // cz.msebera.android.httpclient.client.methods.HttpRequestBase, cz.msebera.android.httpclient.client.methods.HttpUriRequest
    public String getMethod() {
        return "GET";
    }

    public HttpGet() {
    }

    public HttpGet(URI uri) {
        setURI(uri);
    }

    public HttpGet(String str) {
        setURI(URI.create(str));
    }
}
