package okhttp3.mockwebserver;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import cz.msebera.android.httpclient.message.TokenParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.http2.Settings;
import okhttp3.mockwebserver.internal.duplex.DuplexResponseBody;
import okio.Buffer;

/* JADX INFO: compiled from: MockResponse.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b'\u0018\u0000 j2\u00020\u0001:\u0001jB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010A\u001a\u00020\u00002\u0006\u0010B\u001a\u000200J\u0016\u0010A\u001a\u00020\u00002\u0006\u0010C\u001a\u0002002\u0006\u0010\u000e\u001a\u00020DJ\u0016\u0010E\u001a\u00020\u00002\u0006\u0010C\u001a\u0002002\u0006\u0010\u000e\u001a\u00020DJ\u0006\u0010F\u001a\u00020\u0000J\b\u0010G\u001a\u00020\u0000H\u0016J\b\u0010H\u001a\u0004\u0018\u00010\u0004J\u000e\u0010I\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\r\u0010\u0011\u001a\u00020\u000fH\u0007¢\u0006\u0002\bKJ\u000e\u0010L\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\r\u0010\u001a\u001a\u00020\u0018H\u0007¢\u0006\u0002\bMJ\r\u0010-\u001a\u00020+H\u0007¢\u0006\u0002\bNJ\r\u00102\u001a\u000200H\u0007¢\u0006\u0002\bOJ\u000e\u0010P\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\r\u0010;\u001a\u00020\u000fH\u0007¢\u0006\u0002\bQJ\u000e\u0010R\u001a\u00020\u00002\u0006\u0010C\u001a\u000200J\u000e\u0010S\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u000200J\u000e\u0010S\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010S\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0016\u0010T\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\u0016\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u0002002\u0006\u0010W\u001a\u00020\u0018J\u0016\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\u0018J\u0016\u0010X\u001a\u00020\u00002\u0006\u0010C\u001a\u0002002\u0006\u0010\u000e\u001a\u00020DJ\u000e\u0010Y\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fJ\u0016\u0010Z\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\u000e\u0010[\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010\\\u001a\u00020\u00002\u0006\u0010]\u001a\u00020\u0018J\u000e\u0010^\u001a\u00020\u00002\u0006\u0010,\u001a\u00020+J\u000e\u0010_\u001a\u00020\u00002\u0006\u00101\u001a\u000200J\u000e\u0010`\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u000fJ\u001e\u0010a\u001a\u00020\u00002\u0006\u0010b\u001a\u00020\u00062\u0006\u0010c\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\bJ\b\u0010d\u001a\u000200H\u0016J\u000e\u0010e\u001a\u00020\u00002\u0006\u0010f\u001a\u00020\"J\u000e\u0010g\u001a\u00020\u00002\u0006\u0010(\u001a\u00020'J\u000e\u0010h\u001a\u00020\u00002\u0006\u0010i\u001a\u00020=R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8F@GX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0010\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\u0018@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u0019\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0$8F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001e\u0010(\u001a\u00020'2\u0006\u0010\t\u001a\u00020'@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R$\u0010,\u001a\u00020+2\u0006\u0010\t\u001a\u00020+@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b,\u0010/R$\u00101\u001a\u0002002\u0006\u0010\t\u001a\u000200@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b1\u00104R\u001e\u00105\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u000e\u00108\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010:\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8F@GX\u0086\u000e¢\u0006\f\u001a\u0004\b;\u0010\u0012\"\u0004\b:\u0010\u0013R\u000e\u0010<\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010>\u001a\u0004\u0018\u00010=2\b\u0010\t\u001a\u0004\u0018\u00010=@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@¨\u0006k"}, d2 = {"Lokhttp3/mockwebserver/MockResponse;", "", "()V", TtmlNode.TAG_BODY, "Lokio/Buffer;", "bodyDelayAmount", "", "bodyDelayUnit", "Ljava/util/concurrent/TimeUnit;", "<set-?>", "Lokhttp3/mockwebserver/internal/duplex/DuplexResponseBody;", "duplexResponseBody", "getDuplexResponseBody", "()Lokhttp3/mockwebserver/internal/duplex/DuplexResponseBody;", "value", "Lokhttp3/Headers;", "headers", "getHeaders", "()Lokhttp3/Headers;", "(Lokhttp3/Headers;)V", "headersBuilder", "Lokhttp3/Headers$Builder;", "headersDelayAmount", "headersDelayUnit", "", "http2ErrorCode", "getHttp2ErrorCode", "()I", "(I)V", "isDuplex", "", "()Z", "promises", "", "Lokhttp3/mockwebserver/PushPromise;", "pushPromises", "", "getPushPromises", "()Ljava/util/List;", "Lokhttp3/internal/http2/Settings;", "settings", "getSettings", "()Lokhttp3/internal/http2/Settings;", "Lokhttp3/mockwebserver/SocketPolicy;", "socketPolicy", "getSocketPolicy", "()Lokhttp3/mockwebserver/SocketPolicy;", "(Lokhttp3/mockwebserver/SocketPolicy;)V", "", "status", "getStatus", "()Ljava/lang/String;", "(Ljava/lang/String;)V", "throttleBytesPerPeriod", "getThrottleBytesPerPeriod", "()J", "throttlePeriodAmount", "throttlePeriodUnit", "trailers", "getTrailers", "trailersBuilder", "Lokhttp3/WebSocketListener;", "webSocketListener", "getWebSocketListener", "()Lokhttp3/WebSocketListener;", "addHeader", "header", "name", "", "addHeaderLenient", "clearHeaders", "clone", "getBody", "getBodyDelay", "unit", "-deprecated_getHeaders", "getHeadersDelay", "-deprecated_getHttp2ErrorCode", "-deprecated_getSocketPolicy", "-deprecated_getStatus", "getThrottlePeriod", "-deprecated_getTrailers", "removeHeader", "setBody", "setBodyDelay", "delay", "setChunkedBody", "maxChunkSize", "setHeader", "setHeaders", "setHeadersDelay", "setHttp2ErrorCode", "setResponseCode", "code", "setSocketPolicy", "setStatus", "setTrailers", "throttleBody", "bytesPerPeriod", TypedValues.CycleType.S_WAVE_PERIOD, "toString", "withPush", "promise", "withSettings", "withWebSocketUpgrade", "listener", "Companion", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MockResponse implements Cloneable {
    private static final String CHUNKED_BODY_HEADER = "Transfer-encoding: chunked";
    private Buffer body;
    private long bodyDelayAmount;
    private DuplexResponseBody duplexResponseBody;
    private long headersDelayAmount;
    private WebSocketListener webSocketListener;
    private String status = "";
    private Headers.Builder headersBuilder = new Headers.Builder();
    private Headers.Builder trailersBuilder = new Headers.Builder();
    private long throttleBytesPerPeriod = Long.MAX_VALUE;
    private long throttlePeriodAmount = 1;
    private TimeUnit throttlePeriodUnit = TimeUnit.SECONDS;
    private SocketPolicy socketPolicy = SocketPolicy.KEEP_OPEN;
    private int http2ErrorCode = -1;
    private TimeUnit bodyDelayUnit = TimeUnit.MILLISECONDS;
    private TimeUnit headersDelayUnit = TimeUnit.MILLISECONDS;
    private List<PushPromise> promises = new ArrayList();
    private Settings settings = new Settings();

    public MockResponse() {
        setResponseCode(200);
        setHeader("Content-Length", 0L);
    }

    public final String getStatus() {
        return this.status;
    }

    public final void status(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.status = str;
    }

    public final Headers getHeaders() {
        return this.headersBuilder.build();
    }

    public final void headers(Headers value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.headersBuilder = value.newBuilder();
    }

    public final Headers getTrailers() {
        return this.trailersBuilder.build();
    }

    public final void trailers(Headers value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.trailersBuilder = value.newBuilder();
    }

    public final long getThrottleBytesPerPeriod() {
        return this.throttleBytesPerPeriod;
    }

    public final SocketPolicy getSocketPolicy() {
        return this.socketPolicy;
    }

    public final void socketPolicy(SocketPolicy socketPolicy) {
        Intrinsics.checkNotNullParameter(socketPolicy, "<set-?>");
        this.socketPolicy = socketPolicy;
    }

    public final int getHttp2ErrorCode() {
        return this.http2ErrorCode;
    }

    public final void http2ErrorCode(int i) {
        this.http2ErrorCode = i;
    }

    public final Settings getSettings() {
        return this.settings;
    }

    public final WebSocketListener getWebSocketListener() {
        return this.webSocketListener;
    }

    public final DuplexResponseBody getDuplexResponseBody() {
        return this.duplexResponseBody;
    }

    public final boolean isDuplex() {
        return this.duplexResponseBody != null;
    }

    public final List<PushPromise> getPushPromises() {
        return this.promises;
    }

    public MockResponse clone() {
        MockResponse mockResponse = (MockResponse) super.clone();
        mockResponse.headersBuilder = this.headersBuilder.build().newBuilder();
        mockResponse.promises = CollectionsKt.toMutableList((Collection) this.promises);
        return mockResponse;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "status", imports = {}))
    /* JADX INFO: renamed from: -deprecated_getStatus, reason: not valid java name and from getter */
    public final String getStatus() {
        return this.status;
    }

    public final MockResponse setStatus(String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        status(status);
        return this;
    }

    public final MockResponse setResponseCode(int code) {
        String str;
        if (100 <= code && code < 200) {
            str = "Informational";
        } else {
            if (200 <= code && code < 300) {
                str = "OK";
            } else {
                if (300 <= code && code < 400) {
                    str = "Redirection";
                } else {
                    if (400 <= code && code < 500) {
                        str = "Client Error";
                    } else {
                        str = 500 <= code && code < 600 ? "Server Error" : "Mock Response";
                    }
                }
            }
        }
        status("HTTP/1.1 " + code + TokenParser.SP + str);
        return this;
    }

    public final MockResponse clearHeaders() {
        this.headersBuilder = new Headers.Builder();
        return this;
    }

    public final MockResponse addHeader(String header) {
        Intrinsics.checkNotNullParameter(header, "header");
        this.headersBuilder.add(header);
        return this;
    }

    public final MockResponse addHeader(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.headersBuilder.add(name, value.toString());
        return this;
    }

    public final MockResponse addHeaderLenient(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Internal.addHeaderLenient(this.headersBuilder, name, value.toString());
        return this;
    }

    public final MockResponse setHeader(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        removeHeader(name);
        addHeader(name, value);
        return this;
    }

    public final MockResponse removeHeader(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.headersBuilder.removeAll(name);
        return this;
    }

    public final Buffer getBody() {
        Buffer buffer = this.body;
        if (buffer == null) {
            return null;
        }
        return buffer.clone();
    }

    public final MockResponse setBody(Buffer body) {
        Intrinsics.checkNotNullParameter(body, "body");
        setHeader("Content-Length", Long.valueOf(body.size()));
        this.body = body.clone();
        return this;
    }

    public final MockResponse setBody(String body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return setBody(new Buffer().writeUtf8(body));
    }

    public final MockResponse setBody(DuplexResponseBody duplexResponseBody) {
        Intrinsics.checkNotNullParameter(duplexResponseBody, "duplexResponseBody");
        this.duplexResponseBody = duplexResponseBody;
        return this;
    }

    public final MockResponse setChunkedBody(Buffer body, int maxChunkSize) {
        Intrinsics.checkNotNullParameter(body, "body");
        removeHeader("Content-Length");
        this.headersBuilder.add(CHUNKED_BODY_HEADER);
        Buffer buffer = new Buffer();
        while (!body.exhausted()) {
            long jMin = Math.min(body.size(), maxChunkSize);
            buffer.writeHexadecimalUnsignedLong(jMin);
            buffer.writeUtf8("\r\n");
            buffer.write(body, jMin);
            buffer.writeUtf8("\r\n");
        }
        buffer.writeUtf8("0\r\n");
        this.body = buffer;
        return this;
    }

    public final MockResponse setChunkedBody(String body, int maxChunkSize) {
        Intrinsics.checkNotNullParameter(body, "body");
        return setChunkedBody(new Buffer().writeUtf8(body), maxChunkSize);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
    /* JADX INFO: renamed from: -deprecated_getHeaders, reason: not valid java name */
    public final Headers m2428deprecated_getHeaders() {
        return getHeaders();
    }

    public final MockResponse setHeaders(Headers headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        headers(headers);
        return this;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "trailers", imports = {}))
    /* JADX INFO: renamed from: -deprecated_getTrailers, reason: not valid java name */
    public final Headers m2432deprecated_getTrailers() {
        return getTrailers();
    }

    public final MockResponse setTrailers(Headers trailers) {
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        trailers(trailers);
        return this;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "socketPolicy", imports = {}))
    /* JADX INFO: renamed from: -deprecated_getSocketPolicy, reason: not valid java name and from getter */
    public final SocketPolicy getSocketPolicy() {
        return this.socketPolicy;
    }

    public final MockResponse setSocketPolicy(SocketPolicy socketPolicy) {
        Intrinsics.checkNotNullParameter(socketPolicy, "socketPolicy");
        socketPolicy(socketPolicy);
        return this;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "http2ErrorCode", imports = {}))
    /* JADX INFO: renamed from: -deprecated_getHttp2ErrorCode, reason: not valid java name and from getter */
    public final int getHttp2ErrorCode() {
        return this.http2ErrorCode;
    }

    public final MockResponse setHttp2ErrorCode(int http2ErrorCode) {
        http2ErrorCode(http2ErrorCode);
        return this;
    }

    public final MockResponse throttleBody(long bytesPerPeriod, long period, TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.throttleBytesPerPeriod = bytesPerPeriod;
        this.throttlePeriodAmount = period;
        this.throttlePeriodUnit = unit;
        return this;
    }

    public final long getThrottlePeriod(TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return unit.convert(this.throttlePeriodAmount, this.throttlePeriodUnit);
    }

    public final MockResponse setBodyDelay(long delay, TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.bodyDelayAmount = delay;
        this.bodyDelayUnit = unit;
        return this;
    }

    public final long getBodyDelay(TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return unit.convert(this.bodyDelayAmount, this.bodyDelayUnit);
    }

    public final MockResponse setHeadersDelay(long delay, TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.headersDelayAmount = delay;
        this.headersDelayUnit = unit;
        return this;
    }

    public final long getHeadersDelay(TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return unit.convert(this.headersDelayAmount, this.headersDelayUnit);
    }

    public final MockResponse withPush(PushPromise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        this.promises.add(promise);
        return this;
    }

    public final MockResponse withSettings(Settings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.settings = settings;
        return this;
    }

    public final MockResponse withWebSocketUpgrade(WebSocketListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        status("HTTP/1.1 101 Switching Protocols");
        setHeader("Connection", "Upgrade");
        setHeader("Upgrade", "websocket");
        this.body = null;
        this.webSocketListener = listener;
        return this;
    }

    public String toString() {
        return this.status;
    }
}
