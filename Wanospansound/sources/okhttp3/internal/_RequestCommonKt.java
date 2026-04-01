package okhttp3.internal;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import cz.msebera.android.httpclient.client.methods.HttpPatch;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;

/* JADX INFO: compiled from: -RequestCommon.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u001a\u0010\u0003\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\t\u001a\u0012\u0010\u0007\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\n\u001a\u00020\b\u001a\u0014\u0010\u000b\u001a\u00020\u0004*\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r\u001a\n\u0010\u000e\u001a\u00020\u0004*\u00020\u0004\u001a\n\u0010\u000f\u001a\u00020\u0004*\u00020\u0004\u001a\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0001*\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u001a\u0010\u0010\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012*\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u0012\u0010\u0011\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014\u001a\u001c\u0010\u0015\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r\u001a\n\u0010\u0017\u001a\u00020\u0004*\u00020\t\u001a\u0012\u0010\u0018\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u0019\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u001a\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001\u001a1\u0010\u001c\u001a\u00020\u0004\"\b\b\u0000\u0010\u001d*\u00020\u001e*\u00020\u00042\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001d0 2\b\u0010!\u001a\u0004\u0018\u0001H\u001d¢\u0006\u0002\u0010\"¨\u0006#"}, d2 = {"canonicalUrl", "", "url", "commonAddHeader", "Lokhttp3/Request$Builder;", "name", "value", "commonCacheControl", "Lokhttp3/CacheControl;", "Lokhttp3/Request;", "cacheControl", "commonDelete", TtmlNode.TAG_BODY, "Lokhttp3/RequestBody;", "commonGet", "commonHead", "commonHeader", "commonHeaders", "", "headers", "Lokhttp3/Headers;", "commonMethod", "method", "commonNewBuilder", "commonPatch", "commonPost", "commonPut", "commonRemoveHeader", "commonTag", ExifInterface.GPS_DIRECTION_TRUE, "", "type", "Lkotlin/reflect/KClass;", "tag", "(Lokhttp3/Request$Builder;Lkotlin/reflect/KClass;Ljava/lang/Object;)Lokhttp3/Request$Builder;", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class _RequestCommonKt {
    public static final String commonHeader(Request request, String name) {
        Intrinsics.checkNotNullParameter(request, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        return request.headers().get(name);
    }

    public static final List<String> commonHeaders(Request request, String name) {
        Intrinsics.checkNotNullParameter(request, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        return request.headers().values(name);
    }

    public static final Request.Builder commonNewBuilder(Request request) {
        Intrinsics.checkNotNullParameter(request, "<this>");
        return new Request.Builder(request);
    }

    public static final CacheControl commonCacheControl(Request request) {
        Intrinsics.checkNotNullParameter(request, "<this>");
        CacheControl lazyCacheControl = request.getLazyCacheControl();
        if (lazyCacheControl != null) {
            return lazyCacheControl;
        }
        CacheControl cacheControl = CacheControl.INSTANCE.parse(request.headers());
        request.setLazyCacheControl$okhttp(cacheControl);
        return cacheControl;
    }

    public static final String canonicalUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        if (StringsKt.startsWith(url, "ws:", true)) {
            StringBuilder sb = new StringBuilder("http:");
            String strSubstring = url.substring(3);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
            return sb.append(strSubstring).toString();
        }
        if (!StringsKt.startsWith(url, "wss:", true)) {
            return url;
        }
        StringBuilder sb2 = new StringBuilder("https:");
        String strSubstring2 = url.substring(4);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
        return sb2.append(strSubstring2).toString();
    }

    public static final Request.Builder commonHeader(Request.Builder builder, String name, String value) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        builder.getHeaders().set(name, value);
        return builder;
    }

    public static final Request.Builder commonAddHeader(Request.Builder builder, String name, String value) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        builder.getHeaders().add(name, value);
        return builder;
    }

    public static final Request.Builder commonRemoveHeader(Request.Builder builder, String name) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        builder.getHeaders().removeAll(name);
        return builder;
    }

    public static final Request.Builder commonHeaders(Request.Builder builder, Headers headers) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(headers, "headers");
        builder.setHeaders$okhttp(headers.newBuilder());
        return builder;
    }

    public static final Request.Builder commonCacheControl(Request.Builder builder, CacheControl cacheControl) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(cacheControl, "cacheControl");
        String string = cacheControl.toString();
        return string.length() == 0 ? builder.removeHeader("Cache-Control") : builder.header("Cache-Control", string);
    }

    public static final Request.Builder commonGet(Request.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return builder.method("GET", null);
    }

    public static final Request.Builder commonHead(Request.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return builder.method("HEAD", null);
    }

    public static final Request.Builder commonPost(Request.Builder builder, RequestBody body) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(body, "body");
        return builder.method(HttpPost.METHOD_NAME, body);
    }

    public static final Request.Builder commonDelete(Request.Builder builder, RequestBody requestBody) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return builder.method("DELETE", requestBody);
    }

    public static final Request.Builder commonPut(Request.Builder builder, RequestBody body) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(body, "body");
        return builder.method("PUT", body);
    }

    public static final Request.Builder commonPatch(Request.Builder builder, RequestBody body) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(body, "body");
        return builder.method(HttpPatch.METHOD_NAME, body);
    }

    public static final Request.Builder commonMethod(Request.Builder builder, String method, RequestBody requestBody) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(method, "method");
        if (!(method.length() > 0)) {
            throw new IllegalArgumentException("method.isEmpty() == true".toString());
        }
        if (requestBody == null) {
            if (!(true ^ HttpMethod.requiresRequestBody(method))) {
                throw new IllegalArgumentException(("method " + method + " must have a request body.").toString());
            }
        } else if (!HttpMethod.permitsRequestBody(method)) {
            throw new IllegalArgumentException(("method " + method + " must not have a request body.").toString());
        }
        builder.setMethod$okhttp(method);
        builder.setBody$okhttp(requestBody);
        return builder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Request.Builder commonTag(Request.Builder builder, KClass<T> type, T t) {
        LinkedHashMap linkedHashMapAsMutableMap;
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        if (t == 0) {
            if (!builder.getTags$okhttp().isEmpty()) {
                Map<KClass<?>, Object> tags$okhttp = builder.getTags$okhttp();
                Intrinsics.checkNotNull(tags$okhttp, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.reflect.KClass<*>, kotlin.Any>");
                TypeIntrinsics.asMutableMap(tags$okhttp).remove(type);
            }
        } else {
            if (builder.getTags$okhttp().isEmpty()) {
                linkedHashMapAsMutableMap = new LinkedHashMap();
                builder.setTags$okhttp(linkedHashMapAsMutableMap);
            } else {
                Map<KClass<?>, Object> tags$okhttp2 = builder.getTags$okhttp();
                Intrinsics.checkNotNull(tags$okhttp2, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.reflect.KClass<*>, kotlin.Any>");
                linkedHashMapAsMutableMap = TypeIntrinsics.asMutableMap(tags$okhttp2);
            }
            linkedHashMapAsMutableMap.put(type, t);
        }
        return builder;
    }
}
