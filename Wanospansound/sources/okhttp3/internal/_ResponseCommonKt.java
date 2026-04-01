package okhttp3.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Source;

/* JADX INFO: compiled from: -ResponseCommon.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0002\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e\u001a\u0012\u0010\u0013\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015\u001a\u0014\u0010\u0016\u001a\u00020\u0011*\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u001a\n\u0010\u0018\u001a\u00020\f*\u00020\u0002\u001a\u0012\u0010\u0019\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b\u001a \u0010\u001c\u001a\u0004\u0018\u00010\u000e*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000eH\u0007\u001a\u001a\u0010\u001c\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e\u001a\u0018\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u001e\u001a\u00020\u0011*\u00020\u00112\u0006\u0010 \u001a\u00020!\u001a\u0012\u0010\"\u001a\u00020\u0011*\u00020\u00112\u0006\u0010#\u001a\u00020\u000e\u001a\u0014\u0010$\u001a\u00020\u0011*\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010\u0002\u001a\n\u0010&\u001a\u00020\u0011*\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u0015*\u00020\u00022\u0006\u0010(\u001a\u00020)\u001a\u0014\u0010*\u001a\u00020\u0011*\u00020\u00112\b\u0010+\u001a\u0004\u0018\u00010\u0002\u001a\u0012\u0010,\u001a\u00020\u0011*\u00020\u00112\u0006\u0010-\u001a\u00020.\u001a\u0012\u0010/\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u00100\u001a\u00020\u0011*\u00020\u00112\u0006\u00101\u001a\u000202\u001a\n\u00103\u001a\u00020\u000e*\u00020\u0002\u001a\u0018\u00104\u001a\u00020\u0011*\u00020\u00112\f\u00105\u001a\b\u0012\u0004\u0012\u00020!06\u001a\n\u00107\u001a\u00020\u0002*\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u00068"}, d2 = {"commonCacheControl", "Lokhttp3/CacheControl;", "Lokhttp3/Response;", "getCommonCacheControl", "(Lokhttp3/Response;)Lokhttp3/CacheControl;", "commonIsRedirect", "", "getCommonIsRedirect", "(Lokhttp3/Response;)Z", "commonIsSuccessful", "getCommonIsSuccessful", "checkSupportResponse", "", "name", "", "response", "commonAddHeader", "Lokhttp3/Response$Builder;", "value", "commonBody", TtmlNode.TAG_BODY, "Lokhttp3/ResponseBody;", "commonCacheResponse", "cacheResponse", "commonClose", "commonCode", "code", "", "commonHeader", "defaultValue", "commonHeaders", "", "headers", "Lokhttp3/Headers;", "commonMessage", "message", "commonNetworkResponse", "networkResponse", "commonNewBuilder", "commonPeekBody", "byteCount", "", "commonPriorResponse", "priorResponse", "commonProtocol", "protocol", "Lokhttp3/Protocol;", "commonRemoveHeader", "commonRequest", "request", "Lokhttp3/Request;", "commonToString", "commonTrailers", "trailersFn", "Lkotlin/Function0;", "stripBody", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class _ResponseCommonKt {
    public static final Response stripBody(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        return response.newBuilder().body(new UnreadableResponseBody(response.body().getMediaType(), response.body().getContentLength())).build();
    }

    public static final boolean getCommonIsSuccessful(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        int iCode = response.code();
        return 200 <= iCode && iCode < 300;
    }

    public static final List<String> commonHeaders(Response response, String name) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        return response.headers().values(name);
    }

    public static final String commonHeader(Response response, String name, String str) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        String str2 = response.headers().get(name);
        return str2 == null ? str : str2;
    }

    public static final ResponseBody commonPeekBody(Response response, long j) throws IOException {
        Intrinsics.checkNotNullParameter(response, "<this>");
        BufferedSource bufferedSourcePeek = response.body().getSource().peek();
        Buffer buffer = new Buffer();
        bufferedSourcePeek.request(j);
        buffer.write((Source) bufferedSourcePeek, Math.min(j, bufferedSourcePeek.getBuffer().size()));
        return ResponseBody.INSTANCE.create(buffer, response.body().getMediaType(), buffer.size());
    }

    public static final Response.Builder commonNewBuilder(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        return new Response.Builder(response);
    }

    public static final boolean getCommonIsRedirect(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        int iCode = response.code();
        if (iCode != 307 && iCode != 308) {
            switch (iCode) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static final CacheControl getCommonCacheControl(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        CacheControl lazyCacheControl = response.getLazyCacheControl();
        if (lazyCacheControl != null) {
            return lazyCacheControl;
        }
        CacheControl cacheControl = CacheControl.INSTANCE.parse(response.headers());
        response.setLazyCacheControl$okhttp(cacheControl);
        return cacheControl;
    }

    public static final void commonClose(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        response.body().close();
    }

    public static final String commonToString(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        return "Response{protocol=" + response.protocol() + ", code=" + response.code() + ", message=" + response.message() + ", url=" + response.request().url() + '}';
    }

    public static final Response.Builder commonRequest(Response.Builder builder, Request request) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(request, "request");
        builder.setRequest$okhttp(request);
        return builder;
    }

    public static final Response.Builder commonProtocol(Response.Builder builder, Protocol protocol) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        builder.setProtocol$okhttp(protocol);
        return builder;
    }

    public static final Response.Builder commonCode(Response.Builder builder, int i) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.setCode$okhttp(i);
        return builder;
    }

    public static final Response.Builder commonMessage(Response.Builder builder, String message) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        builder.setMessage$okhttp(message);
        return builder;
    }

    public static final Response.Builder commonHeader(Response.Builder builder, String name, String value) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        builder.getHeaders().set(name, value);
        return builder;
    }

    public static final Response.Builder commonAddHeader(Response.Builder builder, String name, String value) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        builder.getHeaders().add(name, value);
        return builder;
    }

    public static final Response.Builder commonRemoveHeader(Response.Builder builder, String name) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        builder.getHeaders().removeAll(name);
        return builder;
    }

    public static final Response.Builder commonHeaders(Response.Builder builder, Headers headers) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(headers, "headers");
        builder.setHeaders$okhttp(headers.newBuilder());
        return builder;
    }

    public static final Response.Builder commonTrailers(Response.Builder builder, Function0<Headers> trailersFn) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(trailersFn, "trailersFn");
        builder.setTrailersFn$okhttp(trailersFn);
        return builder;
    }

    public static final Response.Builder commonBody(Response.Builder builder, ResponseBody body) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(body, "body");
        builder.setBody$okhttp(body);
        return builder;
    }

    public static final Response.Builder commonNetworkResponse(Response.Builder builder, Response response) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        checkSupportResponse("networkResponse", response);
        builder.setNetworkResponse$okhttp(response);
        return builder;
    }

    public static final Response.Builder commonCacheResponse(Response.Builder builder, Response response) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        checkSupportResponse("cacheResponse", response);
        builder.setCacheResponse$okhttp(response);
        return builder;
    }

    private static final void checkSupportResponse(String str, Response response) {
        if (response != null) {
            if (!(response.networkResponse() == null)) {
                throw new IllegalArgumentException((str + ".networkResponse != null").toString());
            }
            if (!(response.cacheResponse() == null)) {
                throw new IllegalArgumentException((str + ".cacheResponse != null").toString());
            }
            if (!(response.priorResponse() == null)) {
                throw new IllegalArgumentException((str + ".priorResponse != null").toString());
            }
        }
    }

    public static final Response.Builder commonPriorResponse(Response.Builder builder, Response response) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.setPriorResponse$okhttp(response);
        return builder;
    }
}
