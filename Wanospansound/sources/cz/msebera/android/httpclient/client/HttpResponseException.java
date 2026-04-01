package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.util.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public class HttpResponseException extends ClientProtocolException {
    private static final long serialVersionUID = -7186627969477257933L;
    private final String reasonPhrase;
    private final int statusCode;

    public HttpResponseException(int i, String str) {
        super(String.format("status code: %d".concat(TextUtils.isBlank(str) ? "" : ", reason phrase: %s"), Integer.valueOf(i), str));
        this.statusCode = i;
        this.reasonPhrase = str;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
