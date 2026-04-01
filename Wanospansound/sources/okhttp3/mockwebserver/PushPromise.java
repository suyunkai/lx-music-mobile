package okhttp3.mockwebserver;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;

/* JADX INFO: compiled from: PushPromise.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\rJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u000eJ\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u000fJ\r\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\b\u0010R\u0013\u0010\u0005\u001a\u00020\u00068\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0013\u0010\u0007\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\f¨\u0006\u0011"}, d2 = {"Lokhttp3/mockwebserver/PushPromise;", "", "method", "", "path", "headers", "Lokhttp3/Headers;", "response", "Lokhttp3/mockwebserver/MockResponse;", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/Headers;Lokhttp3/mockwebserver/MockResponse;)V", "()Lokhttp3/Headers;", "()Ljava/lang/String;", "()Lokhttp3/mockwebserver/MockResponse;", "-deprecated_headers", "-deprecated_method", "-deprecated_path", "-deprecated_response", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PushPromise {
    private final Headers headers;
    private final String method;
    private final String path;
    private final MockResponse response;

    public PushPromise(String method, String path, Headers headers, MockResponse response) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(response, "response");
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.response = response;
    }

    public final String method() {
        return this.method;
    }

    public final String path() {
        return this.path;
    }

    public final Headers headers() {
        return this.headers;
    }

    public final MockResponse response() {
        return this.response;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "method", imports = {}))
    /* JADX INFO: renamed from: -deprecated_method, reason: not valid java name and from getter */
    public final String getMethod() {
        return this.method;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "path", imports = {}))
    /* JADX INFO: renamed from: -deprecated_path, reason: not valid java name and from getter */
    public final String getPath() {
        return this.path;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
    /* JADX INFO: renamed from: -deprecated_headers, reason: not valid java name and from getter */
    public final Headers getHeaders() {
        return this.headers;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "response", imports = {}))
    /* JADX INFO: renamed from: -deprecated_response, reason: not valid java name and from getter */
    public final MockResponse getResponse() {
        return this.response;
    }
}
