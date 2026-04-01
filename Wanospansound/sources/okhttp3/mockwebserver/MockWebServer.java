package okhttp3.mockwebserver;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.ecarx.eas.sdk.IServiceManager;
import com.google.common.net.HttpHeaders;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.message.TokenParser;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.duplex.MwsDuplexAccess;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.ws.WebSocketExtensions;
import okhttp3.internal.ws.WebSocketProtocol;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.internal.duplex.DuplexResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Timeout;
import org.junit.rules.ExternalResource;

/* JADX INFO: compiled from: MockWebServer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u007f2\u00020\u00012\u00020\u0002:\u0007\u007f\u0080\u0001\u0081\u0001\u0082\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010G\u001a\u00020HH\u0002J\b\u0010I\u001a\u00020HH\u0014J\b\u0010J\u001a\u00020HH\u0014J\b\u0010K\u001a\u00020HH\u0016J\u0018\u0010L\u001a\u00020H2\u0006\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\u001cH\u0002J\u000e\u0010O\u001a\u00020H2\u0006\u0010P\u001a\u00020QJ\r\u0010\"\u001a\u00020\rH\u0007¢\u0006\u0002\bRJ\r\u00103\u001a\u00020\rH\u0007¢\u0006\u0002\bSJ0\u0010T\u001a\u00020H2\u0006\u0010N\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u0002062\u0006\u0010P\u001a\u00020QH\u0002J\u0006\u0010Z\u001a\u00020HJ\u0010\u0010[\u001a\u00020H2\u0006\u0010\\\u001a\u00020\u001cH\u0002J\u0013\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,H\u0007¢\u0006\u0002\b]J\u0010\u0010^\u001a\u00020H2\u0006\u0010U\u001a\u00020VH\u0002J(\u0010_\u001a\u0002062\u0006\u0010N\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010M\u001a\u00020\rH\u0002J\u0006\u0010`\u001a\u00020HJ\u0006\u0010a\u001a\u00020HJ\u0010\u0010b\u001a\u00020H2\u0006\u0010\\\u001a\u00020\u001cH\u0002J\u0015\u0010\n\u001a\u00020H2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\bcJ\u0015\u0010)\u001a\u00020H2\u0006\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\bdJ\u001b\u00100\u001a\u00020H2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,H\u0007¢\u0006\u0002\b]J\u0015\u0010=\u001a\u00020H2\u0006\u0010:\u001a\u000209H\u0007¢\u0006\u0002\beJ\u0006\u0010f\u001a\u00020HJ\u0010\u0010g\u001a\u00020H2\u0006\u0010h\u001a\u00020\u0007H\u0002J\u0016\u0010i\u001a\u00020H2\u0006\u0010j\u001a\u00020k2\u0006\u0010!\u001a\u00020\rJ\u0010\u0010i\u001a\u00020H2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0012\u0010i\u001a\u00020H2\b\b\u0002\u0010!\u001a\u00020\rH\u0007J\u0006\u0010l\u001a\u000206J\u0018\u0010l\u001a\u0004\u0018\u0001062\u0006\u0010m\u001a\u00020\u00072\u0006\u0010n\u001a\u00020oJ8\u0010p\u001a\u00020H2\u0006\u0010q\u001a\u00020Q2\u0006\u0010N\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010r\u001a\u00020\u00072\u0006\u0010s\u001a\u00020&H\u0002J\u0006\u0010t\u001a\u00020uJ\b\u0010v\u001a\u00020\u0015H\u0016J\u000e\u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020\u0015J\u0016\u0010z\u001a\u00020H2\u0006\u0010?\u001a\u00020@2\u0006\u0010F\u001a\u00020&J\u0018\u0010{\u001a\u00020H2\u0006\u0010W\u001a\u00020X2\u0006\u0010|\u001a\u00020}H\u0002J \u0010~\u001a\u00020H2\u0006\u0010N\u001a\u00020\u001c2\u0006\u0010W\u001a\u00020X2\u0006\u0010P\u001a\u00020QH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u001a\u001a&\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u001c0\u001c \u001d*\u0012\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u001c0\u001c\u0018\u00010\u001e0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u001f\u001a&\u0012\f\u0012\n \u001d*\u0004\u0018\u00010 0  \u001d*\u0012\u0012\f\u0012\n \u001d*\u0004\u0018\u00010 0 \u0018\u00010\u001e0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010!\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R2\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0011\u00102\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b3\u0010#R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020605X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010:\u001a\u0004\u0018\u0001092\b\u0010+\u001a\u0004\u0018\u0001098F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020CX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0083\u0001"}, d2 = {"Lokhttp3/mockwebserver/MockWebServer;", "Lorg/junit/rules/ExternalResource;", "Ljava/io/Closeable;", "()V", "atomicRequestCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "bodyLimit", "", "getBodyLimit", "()J", "setBodyLimit", "(J)V", "clientAuth", "", "dispatcher", "Lokhttp3/mockwebserver/Dispatcher;", "getDispatcher", "()Lokhttp3/mockwebserver/Dispatcher;", "setDispatcher", "(Lokhttp3/mockwebserver/Dispatcher;)V", "hostName", "", "getHostName", "()Ljava/lang/String;", "inetSocketAddress", "Ljava/net/InetSocketAddress;", "openClientSockets", "", "Ljava/net/Socket;", "kotlin.jvm.PlatformType", "", "openConnections", "Lokhttp3/internal/http2/Http2Connection;", "port", "getPort", "()I", "portField", "protocolNegotiationEnabled", "", "getProtocolNegotiationEnabled", "()Z", "setProtocolNegotiationEnabled", "(Z)V", "value", "", "Lokhttp3/Protocol;", "protocols", "()Ljava/util/List;", "setProtocols", "(Ljava/util/List;)V", "requestCount", "getRequestCount", "requestQueue", "Ljava/util/concurrent/LinkedBlockingQueue;", "Lokhttp3/mockwebserver/RecordedRequest;", "serverSocket", "Ljava/net/ServerSocket;", "Ljavax/net/ServerSocketFactory;", "serverSocketFactory", "getServerSocketFactory", "()Ljavax/net/ServerSocketFactory;", "setServerSocketFactory", "(Ljavax/net/ServerSocketFactory;)V", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "started", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunnerBackend", "Lokhttp3/internal/concurrent/TaskRunner$RealBackend;", "tunnelProxy", "acceptConnections", "", TtmlNode.ANNOTATION_POSITION_AFTER, TtmlNode.ANNOTATION_POSITION_BEFORE, "close", "dispatchBookkeepingRequest", "sequenceNumber", "socket", "enqueue", "response", "Lokhttp3/mockwebserver/MockResponse;", "-deprecated_port", "-deprecated_requestCount", "handleWebSocketUpgrade", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "request", "noClientAuth", "processHandshakeFailure", "raw", "-deprecated_protocols", "readEmptyLine", "readRequest", "requestClientAuth", "requireClientAuth", "serveConnection", "-deprecated_bodyLimit", "-deprecated_protocolNegotiationEnabled", "-deprecated_serverSocketFactory", "shutdown", "sleepIfDelayed", "delayMs", TtmlNode.START, "inetAddress", "Ljava/net/InetAddress;", "takeRequest", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "throttledTransfer", IServiceManager.SERVICE_POLICY, "byteCount", "isRequest", "toProxyAddress", "Ljava/net/Proxy;", "toString", "url", "Lokhttp3/HttpUrl;", "path", "useHttps", "writeHeaders", "headers", "Lokhttp3/Headers;", "writeHttpResponse", "Companion", "Http2SocketHandler", "SocketHandler", "TruncatingBuffer", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MockWebServer extends ExternalResource implements Closeable {
    private static final int CLIENT_AUTH_NONE = 0;
    private static final int CLIENT_AUTH_REQUESTED = 1;
    private static final int CLIENT_AUTH_REQUIRED = 2;
    private static final MockWebServer$Companion$UNTRUSTED_TRUST_MANAGER$1 UNTRUSTED_TRUST_MANAGER;
    private static final Logger logger;
    private final AtomicInteger atomicRequestCount;
    private long bodyLimit;
    private int clientAuth;
    private Dispatcher dispatcher;
    private InetSocketAddress inetSocketAddress;
    private final Set<Socket> openClientSockets;
    private final Set<Http2Connection> openConnections;
    private int portField;
    private boolean protocolNegotiationEnabled;
    private List<? extends Protocol> protocols;
    private final LinkedBlockingQueue<RecordedRequest> requestQueue;
    private ServerSocket serverSocket;
    private ServerSocketFactory serverSocketFactory;
    private SSLSocketFactory sslSocketFactory;
    private boolean started;
    private final TaskRunner taskRunner;
    private final TaskRunner.RealBackend taskRunnerBackend;
    private boolean tunnelProxy;

    public final void start() throws IOException {
        start$default(this, 0, 1, null);
    }

    public MockWebServer() {
        TaskRunner.RealBackend realBackend = new TaskRunner.RealBackend(Util.threadFactory("MockWebServer TaskRunner", false));
        this.taskRunnerBackend = realBackend;
        this.taskRunner = new TaskRunner(realBackend);
        this.requestQueue = new LinkedBlockingQueue<>();
        this.openClientSockets = Collections.newSetFromMap(new ConcurrentHashMap());
        this.openConnections = Collections.newSetFromMap(new ConcurrentHashMap());
        this.atomicRequestCount = new AtomicInteger();
        this.bodyLimit = Long.MAX_VALUE;
        this.dispatcher = new QueueDispatcher();
        this.portField = -1;
        this.protocolNegotiationEnabled = true;
        this.protocols = Util.immutableListOf(new Protocol[]{Protocol.HTTP_2, Protocol.HTTP_1_1});
    }

    public final int getRequestCount() {
        return this.atomicRequestCount.get();
    }

    public final long getBodyLimit() {
        return this.bodyLimit;
    }

    public final void setBodyLimit(long j) {
        this.bodyLimit = j;
    }

    public final ServerSocketFactory getServerSocketFactory() {
        if (this.serverSocketFactory == null && this.started) {
            this.serverSocketFactory = ServerSocketFactory.getDefault();
        }
        return this.serverSocketFactory;
    }

    public final void setServerSocketFactory(ServerSocketFactory serverSocketFactory) {
        if (!(!this.started)) {
            throw new IllegalStateException("serverSocketFactory must not be set after start()".toString());
        }
        this.serverSocketFactory = serverSocketFactory;
    }

    public final Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    public final void setDispatcher(Dispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(dispatcher, "<set-?>");
        this.dispatcher = dispatcher;
    }

    public final int getPort() {
        before();
        return this.portField;
    }

    public final String getHostName() {
        before();
        InetSocketAddress inetSocketAddress = this.inetSocketAddress;
        Intrinsics.checkNotNull(inetSocketAddress);
        String canonicalHostName = inetSocketAddress.getAddress().getCanonicalHostName();
        Intrinsics.checkNotNullExpressionValue(canonicalHostName, "inetSocketAddress!!.address.canonicalHostName");
        return canonicalHostName;
    }

    public final boolean getProtocolNegotiationEnabled() {
        return this.protocolNegotiationEnabled;
    }

    public final void setProtocolNegotiationEnabled(boolean z) {
        this.protocolNegotiationEnabled = z;
    }

    public final List<Protocol> protocols() {
        return this.protocols;
    }

    public final void setProtocols(List<? extends Protocol> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        List<? extends Protocol> immutableList = Util.toImmutableList(value);
        if (!(!immutableList.contains(Protocol.H2_PRIOR_KNOWLEDGE) || immutableList.size() == 1)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("protocols containing h2_prior_knowledge cannot use other protocols: ", immutableList).toString());
        }
        if (!(immutableList.contains(Protocol.HTTP_1_1) || immutableList.contains(Protocol.H2_PRIOR_KNOWLEDGE))) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("protocols doesn't contain http/1.1: ", immutableList).toString());
        }
        if (!(!immutableList.contains(null))) {
            throw new IllegalArgumentException("protocols must not contain null".toString());
        }
        this.protocols = immutableList;
    }

    @Override // org.junit.rules.ExternalResource
    protected synchronized void before() {
        if (this.started) {
            return;
        }
        try {
            start$default(this, 0, 1, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "port", imports = {}))
    /* JADX INFO: renamed from: -deprecated_port, reason: not valid java name */
    public final int m2435deprecated_port() {
        return getPort();
    }

    public final Proxy toProxyAddress() {
        before();
        InetSocketAddress inetSocketAddress = this.inetSocketAddress;
        Intrinsics.checkNotNull(inetSocketAddress);
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(inetSocketAddress.getAddress().getCanonicalHostName(), getPort()));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "run { this.serverSocketFactory = serverSocketFactory }", imports = {}))
    /* JADX INFO: renamed from: -deprecated_serverSocketFactory, reason: not valid java name */
    public final void m2440deprecated_serverSocketFactory(ServerSocketFactory serverSocketFactory) {
        Intrinsics.checkNotNullParameter(serverSocketFactory, "serverSocketFactory");
        setServerSocketFactory(serverSocketFactory);
    }

    public final HttpUrl url(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        HttpUrl httpUrlResolve = new HttpUrl.Builder().scheme(this.sslSocketFactory != null ? "https" : HttpHost.DEFAULT_SCHEME_NAME).host(getHostName()).port(getPort()).build().resolve(path);
        Intrinsics.checkNotNull(httpUrlResolve);
        return httpUrlResolve;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "run { this.bodyLimit = bodyLimit }", imports = {}))
    /* JADX INFO: renamed from: -deprecated_bodyLimit, reason: not valid java name */
    public final void m2434deprecated_bodyLimit(long bodyLimit) {
        setBodyLimit(bodyLimit);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "run { this.protocolNegotiationEnabled = protocolNegotiationEnabled }", imports = {}))
    /* JADX INFO: renamed from: -deprecated_protocolNegotiationEnabled, reason: not valid java name */
    public final void m2436deprecated_protocolNegotiationEnabled(boolean protocolNegotiationEnabled) {
        setProtocolNegotiationEnabled(protocolNegotiationEnabled);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "run { this.protocols = protocols }", imports = {}))
    /* JADX INFO: renamed from: -deprecated_protocols, reason: not valid java name */
    public final void m2438deprecated_protocols(List<? extends Protocol> protocols) {
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        setProtocols(protocols);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "protocols", imports = {}))
    /* JADX INFO: renamed from: -deprecated_protocols, reason: not valid java name */
    public final List<Protocol> m2437deprecated_protocols() {
        return this.protocols;
    }

    public final void useHttps(SSLSocketFactory sslSocketFactory, boolean tunnelProxy) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        this.sslSocketFactory = sslSocketFactory;
        this.tunnelProxy = tunnelProxy;
    }

    public final void noClientAuth() {
        this.clientAuth = 0;
    }

    public final void requestClientAuth() {
        this.clientAuth = 1;
    }

    public final void requireClientAuth() {
        this.clientAuth = 2;
    }

    public final RecordedRequest takeRequest() throws InterruptedException {
        RecordedRequest recordedRequestTake = this.requestQueue.take();
        Intrinsics.checkNotNullExpressionValue(recordedRequestTake, "requestQueue.take()");
        return recordedRequestTake;
    }

    public final RecordedRequest takeRequest(long timeout, TimeUnit unit) throws InterruptedException {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return this.requestQueue.poll(timeout, unit);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "requestCount", imports = {}))
    /* JADX INFO: renamed from: -deprecated_requestCount, reason: not valid java name */
    public final int m2439deprecated_requestCount() {
        return getRequestCount();
    }

    public final void enqueue(MockResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        ((QueueDispatcher) this.dispatcher).enqueueResponse(response.clone());
    }

    public static /* synthetic */ void start$default(MockWebServer mockWebServer, int i, int i2, Object obj) throws IOException {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        mockWebServer.start(i);
    }

    public final void start(int port) throws IOException {
        InetAddress byName = InetAddress.getByName("localhost");
        Intrinsics.checkNotNullExpressionValue(byName, "getByName(\"localhost\")");
        start(byName, port);
    }

    public final void start(InetAddress inetAddress, int port) throws IOException {
        Intrinsics.checkNotNullParameter(inetAddress, "inetAddress");
        start(new InetSocketAddress(inetAddress, port));
    }

    private final synchronized void start(InetSocketAddress inetSocketAddress) throws IOException {
        boolean z = true;
        if (!(!this.started)) {
            throw new IllegalArgumentException("start() already called".toString());
        }
        this.started = true;
        this.inetSocketAddress = inetSocketAddress;
        ServerSocketFactory serverSocketFactory = getServerSocketFactory();
        Intrinsics.checkNotNull(serverSocketFactory);
        ServerSocket serverSocketCreateServerSocket = serverSocketFactory.createServerSocket();
        this.serverSocket = serverSocketCreateServerSocket;
        Intrinsics.checkNotNull(serverSocketCreateServerSocket);
        final boolean z2 = false;
        if (inetSocketAddress.getPort() == 0) {
            z = false;
        }
        serverSocketCreateServerSocket.setReuseAddress(z);
        ServerSocket serverSocket = this.serverSocket;
        Intrinsics.checkNotNull(serverSocket);
        serverSocket.bind(inetSocketAddress, 50);
        ServerSocket serverSocket2 = this.serverSocket;
        Intrinsics.checkNotNull(serverSocket2);
        this.portField = serverSocket2.getLocalPort();
        TaskQueue taskQueueNewQueue = this.taskRunner.newQueue();
        final String strStringPlus = Intrinsics.stringPlus("MockWebServer ", Integer.valueOf(this.portField));
        taskQueueNewQueue.schedule(new Task(strStringPlus, z2, this) { // from class: okhttp3.mockwebserver.MockWebServer$start$$inlined$execute$default$1
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ String $name;
            final /* synthetic */ MockWebServer this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(strStringPlus, z2);
                this.$name = strStringPlus;
                this.$cancelable = z2;
                this.this$0 = this;
            }

            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                try {
                    MockWebServer.logger.fine(this.this$0 + " starting to accept connections");
                    this.this$0.acceptConnections();
                } catch (Throwable th) {
                    MockWebServer.logger.log(Level.WARNING, this.this$0 + " failed unexpectedly", th);
                }
                ServerSocket serverSocket3 = this.this$0.serverSocket;
                if (serverSocket3 != null) {
                    Util.closeQuietly(serverSocket3);
                }
                Iterator it = this.this$0.openClientSockets.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    Intrinsics.checkNotNullExpressionValue(next, "openClientSocket.next()");
                    Util.closeQuietly((Socket) next);
                    it.remove();
                }
                Iterator it2 = this.this$0.openConnections.iterator();
                while (it2.hasNext()) {
                    Object next2 = it2.next();
                    Intrinsics.checkNotNullExpressionValue(next2, "httpConnection.next()");
                    Util.closeQuietly((Closeable) next2);
                    it2.remove();
                }
                this.this$0.getDispatcher().shutdown();
                return -1L;
            }
        }, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void acceptConnections() throws Exception {
        while (true) {
            try {
                ServerSocket serverSocket = this.serverSocket;
                Intrinsics.checkNotNull(serverSocket);
                Socket socketAccept = serverSocket.accept();
                Intrinsics.checkNotNullExpressionValue(socketAccept, "serverSocket!!.accept()");
                if (this.dispatcher.peek().getSocketPolicy() == SocketPolicy.DISCONNECT_AT_START) {
                    dispatchBookkeepingRequest(0, socketAccept);
                    socketAccept.close();
                } else {
                    this.openClientSockets.add(socketAccept);
                    serveConnection(socketAccept);
                }
            } catch (SocketException e) {
                logger.fine(this + " done accepting connections: " + ((Object) e.getMessage()));
                return;
            }
        }
    }

    public final synchronized void shutdown() throws IOException {
        if (this.started) {
            ServerSocket serverSocket = this.serverSocket;
            if (!(serverSocket != null)) {
                throw new IllegalArgumentException("shutdown() before start()".toString());
            }
            Intrinsics.checkNotNull(serverSocket);
            serverSocket.close();
            Iterator<TaskQueue> it = this.taskRunner.activeQueues().iterator();
            while (it.hasNext()) {
                if (!it.next().idleLatch().await(5L, TimeUnit.SECONDS)) {
                    throw new IOException("Gave up waiting for queue to shut down");
                }
            }
            this.taskRunnerBackend.shutdown();
        }
    }

    @Override // org.junit.rules.ExternalResource
    protected synchronized void after() {
        try {
            shutdown();
        } catch (IOException e) {
            logger.log(Level.WARNING, "MockWebServer shutdown failed", (Throwable) e);
        }
    }

    private final void serveConnection(final Socket raw) {
        TaskQueue taskQueueNewQueue = this.taskRunner.newQueue();
        final String strStringPlus = Intrinsics.stringPlus("MockWebServer ", raw.getRemoteSocketAddress());
        final boolean z = false;
        taskQueueNewQueue.schedule(new Task(strStringPlus, z, this, raw) { // from class: okhttp3.mockwebserver.MockWebServer$serveConnection$$inlined$execute$default$1
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ String $name;
            final /* synthetic */ Socket $raw$inlined;
            final /* synthetic */ MockWebServer this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(strStringPlus, z);
                this.$name = strStringPlus;
                this.$cancelable = z;
                this.this$0 = this;
                this.$raw$inlined = raw;
            }

            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                try {
                    new MockWebServer.SocketHandler(this.this$0, this.$raw$inlined).handle();
                    return -1L;
                } catch (IOException e) {
                    MockWebServer.logger.fine(this.this$0 + " connection from " + this.$raw$inlined.getInetAddress() + " failed: " + e);
                    return -1L;
                } catch (Exception e2) {
                    MockWebServer.logger.log(Level.SEVERE, this.this$0 + " connection from " + this.$raw$inlined.getInetAddress() + " crashed", (Throwable) e2);
                    return -1L;
                }
            }
        }, 0L);
    }

    /* JADX INFO: compiled from: MockWebServer.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0006\u0010\t\u001a\u00020\bJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/mockwebserver/MockWebServer$SocketHandler;", "", "raw", "Ljava/net/Socket;", "(Lokhttp3/mockwebserver/MockWebServer;Ljava/net/Socket;)V", "sequenceNumber", "", "createTunnel", "", "handle", "processOneRequest", "", "socket", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class SocketHandler {
        private final Socket raw;
        private int sequenceNumber;
        final /* synthetic */ MockWebServer this$0;

        /* JADX INFO: compiled from: MockWebServer.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[SocketPolicy.values().length];
                iArr[SocketPolicy.DISCONNECT_AT_END.ordinal()] = 1;
                iArr[SocketPolicy.SHUTDOWN_INPUT_AT_END.ordinal()] = 2;
                iArr[SocketPolicy.SHUTDOWN_OUTPUT_AT_END.ordinal()] = 3;
                iArr[SocketPolicy.SHUTDOWN_SERVER_AFTER_RESPONSE.ordinal()] = 4;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public SocketHandler(MockWebServer this$0, Socket raw) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(raw, "raw");
            this.this$0 = this$0;
            this.raw = raw;
        }

        public final void handle() throws Exception {
            Socket socketCreateSocket;
            SocketPolicy socketPolicy = this.this$0.getDispatcher().peek().getSocketPolicy();
            Protocol protocol = Protocol.HTTP_1_1;
            if (this.this$0.sslSocketFactory != null) {
                if (this.this$0.tunnelProxy) {
                    createTunnel();
                }
                if (socketPolicy == SocketPolicy.FAIL_HANDSHAKE) {
                    this.this$0.dispatchBookkeepingRequest(this.sequenceNumber, this.raw);
                    this.this$0.processHandshakeFailure(this.raw);
                    return;
                }
                SSLSocketFactory sSLSocketFactory = this.this$0.sslSocketFactory;
                Intrinsics.checkNotNull(sSLSocketFactory);
                Socket socket = this.raw;
                socketCreateSocket = sSLSocketFactory.createSocket(socket, socket.getInetAddress().getHostAddress(), this.raw.getPort(), true);
                Intrinsics.checkNotNullExpressionValue(socketCreateSocket, "sslSocketFactory!!.creat…          raw.port, true)");
                SSLSocket sSLSocket = (SSLSocket) socketCreateSocket;
                sSLSocket.setUseClientMode(false);
                if (this.this$0.clientAuth != 2) {
                    if (this.this$0.clientAuth == 1) {
                        sSLSocket.setWantClientAuth(true);
                    }
                } else {
                    sSLSocket.setNeedClientAuth(true);
                }
                this.this$0.openClientSockets.add(socketCreateSocket);
                if (this.this$0.getProtocolNegotiationEnabled()) {
                    Platform.INSTANCE.get().configureTlsExtensions(sSLSocket, null, this.this$0.protocols());
                }
                sSLSocket.startHandshake();
                if (this.this$0.getProtocolNegotiationEnabled()) {
                    String selectedProtocol = Platform.INSTANCE.get().getSelectedProtocol(sSLSocket);
                    protocol = selectedProtocol != null ? Protocol.INSTANCE.get(selectedProtocol) : Protocol.HTTP_1_1;
                    Platform.INSTANCE.get().afterHandshake(sSLSocket);
                }
                this.this$0.openClientSockets.remove(this.raw);
            } else if (this.this$0.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
                socketCreateSocket = this.raw;
                protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            } else {
                socketCreateSocket = this.raw;
            }
            if (socketPolicy == SocketPolicy.STALL_SOCKET_AT_START) {
                this.this$0.dispatchBookkeepingRequest(this.sequenceNumber, socketCreateSocket);
                return;
            }
            if (protocol == Protocol.HTTP_2 || protocol == Protocol.H2_PRIOR_KNOWLEDGE) {
                Http2Connection http2ConnectionBuild = Http2Connection.Builder.socket$default(new Http2Connection.Builder(false, this.this$0.taskRunner), socketCreateSocket, null, null, null, 14, null).listener(new Http2SocketHandler(this.this$0, socketCreateSocket, protocol)).build();
                Http2Connection.start$default(http2ConnectionBuild, false, this.this$0.taskRunner, 1, null);
                this.this$0.openConnections.add(http2ConnectionBuild);
                this.this$0.openClientSockets.remove(socketCreateSocket);
                return;
            }
            if (protocol != Protocol.HTTP_1_1) {
                throw new AssertionError();
            }
            while (processOneRequest(socketCreateSocket, Okio.buffer(Okio.source(socketCreateSocket)), Okio.buffer(Okio.sink(socketCreateSocket)))) {
            }
            if (this.sequenceNumber == 0) {
                MockWebServer.logger.warning(this.this$0 + " connection from " + this.raw.getInetAddress() + " didn't make a request");
            }
            socketCreateSocket.close();
            this.this$0.openClientSockets.remove(socketCreateSocket);
        }

        private final void createTunnel() throws InterruptedException, IOException {
            SocketPolicy socketPolicy;
            BufferedSource bufferedSourceBuffer = Okio.buffer(Okio.source(this.raw));
            BufferedSink bufferedSinkBuffer = Okio.buffer(Okio.sink(this.raw));
            do {
                socketPolicy = this.this$0.getDispatcher().peek().getSocketPolicy();
                if (!processOneRequest(this.raw, bufferedSourceBuffer, bufferedSinkBuffer)) {
                    throw new IllegalStateException("Tunnel without any CONNECT!".toString());
                }
            } while (socketPolicy != SocketPolicy.UPGRADE_TO_SSL_AT_END);
        }

        private final boolean processOneRequest(Socket socket, BufferedSource source, BufferedSink sink) throws InterruptedException, IOException {
            boolean z;
            if (source.exhausted()) {
                return false;
            }
            RecordedRequest request = this.this$0.readRequest(socket, source, sink, this.sequenceNumber);
            this.this$0.atomicRequestCount.incrementAndGet();
            this.this$0.requestQueue.add(request);
            if (request.getFailure() != null) {
                return false;
            }
            MockResponse mockResponseDispatch = this.this$0.getDispatcher().dispatch(request);
            if (mockResponseDispatch.getSocketPolicy() == SocketPolicy.DISCONNECT_AFTER_REQUEST) {
                socket.close();
                return false;
            }
            if (mockResponseDispatch.getSocketPolicy() == SocketPolicy.NO_RESPONSE) {
                if (source.exhausted()) {
                    return false;
                }
                throw new ProtocolException("unexpected data");
            }
            boolean z2 = StringsKt.equals("Upgrade", request.getHeader("Connection"), true) && StringsKt.equals("websocket", request.getHeader("Upgrade"), true);
            boolean z3 = mockResponseDispatch.getWebSocketListener() != null;
            if (!z2 || !z3) {
                this.this$0.writeHttpResponse(socket, sink, mockResponseDispatch);
                z = true;
            } else {
                this.this$0.handleWebSocketUpgrade(socket, source, sink, request, mockResponseDispatch);
                z = false;
            }
            if (MockWebServer.logger.isLoggable(Level.FINE)) {
                MockWebServer.logger.fine(this.this$0 + " received request: " + request + " and responded: " + mockResponseDispatch);
            }
            int i = WhenMappings.$EnumSwitchMapping$0[mockResponseDispatch.getSocketPolicy().ordinal()];
            if (i == 1) {
                socket.close();
                return false;
            }
            if (i == 2) {
                socket.shutdownInput();
            } else if (i == 3) {
                socket.shutdownOutput();
            } else if (i == 4) {
                this.this$0.shutdown();
            }
            this.sequenceNumber++;
            return z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processHandshakeFailure(Socket raw) throws Exception {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, new TrustManager[]{UNTRUSTED_TRUST_MANAGER}, new SecureRandom());
        Socket socketCreateSocket = sSLContext.getSocketFactory().createSocket(raw, raw.getInetAddress().getHostAddress(), raw.getPort(), true);
        if (socketCreateSocket == null) {
            throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.SSLSocket");
        }
        SSLSocket sSLSocket = (SSLSocket) socketCreateSocket;
        try {
            sSLSocket.startHandshake();
            throw new AssertionError();
        } catch (IOException unused) {
            sSLSocket.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dispatchBookkeepingRequest(int sequenceNumber, Socket socket) throws InterruptedException {
        RecordedRequest recordedRequest = new RecordedRequest("", Headers.INSTANCE.of(new String[0]), CollectionsKt.emptyList(), 0L, new Buffer(), sequenceNumber, socket, null, 128, null);
        this.atomicRequestCount.incrementAndGet();
        this.requestQueue.add(recordedRequest);
        this.dispatcher.dispatch(recordedRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RecordedRequest readRequest(Socket socket, BufferedSource source, BufferedSink sink, int sequenceNumber) throws InterruptedException, IOException {
        String utf8LineStrict;
        IOException iOException;
        String str;
        boolean z;
        BufferedSource bufferedSource = source;
        BufferedSink bufferedSink = sink;
        Headers.Builder builder = new Headers.Builder();
        TruncatingBuffer truncatingBuffer = new TruncatingBuffer(this.bodyLimit);
        ArrayList arrayList = new ArrayList();
        try {
            utf8LineStrict = source.readUtf8LineStrict();
        } catch (IOException e) {
            e = e;
            utf8LineStrict = "";
        }
        try {
        } catch (IOException e2) {
            e = e2;
            iOException = e;
            str = utf8LineStrict;
        }
        if (utf8LineStrict.length() == 0) {
            throw new ProtocolException("no request because the stream is exhausted");
        }
        long j = -1;
        long j2 = -1;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            String utf8LineStrict2 = source.readUtf8LineStrict();
            if (utf8LineStrict2.length() == 0) {
                break;
            }
            Internal.addHeaderLenient(builder, utf8LineStrict2);
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = utf8LineStrict2.toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (j2 == j && StringsKt.startsWith$default(lowerCase, "content-length:", false, 2, (Object) null)) {
                String strSubstring = utf8LineStrict2.substring(15);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                j2 = Long.parseLong(StringsKt.trim((CharSequence) strSubstring).toString());
            }
            if (StringsKt.startsWith$default(lowerCase, "transfer-encoding:", false, 2, (Object) null)) {
                String strSubstring2 = lowerCase.substring(18);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
                if (Intrinsics.areEqual(StringsKt.trim((CharSequence) strSubstring2).toString(), HTTP.CHUNK_CODING)) {
                    z3 = true;
                }
            }
            if (StringsKt.startsWith$default(lowerCase, "expect:", false, 2, (Object) null)) {
                String strSubstring3 = lowerCase.substring(7);
                Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String).substring(startIndex)");
                if (StringsKt.equals(StringsKt.trim((CharSequence) strSubstring3).toString(), HTTP.EXPECT_CONTINUE, true)) {
                    bufferedSource = source;
                    bufferedSink = sink;
                    z2 = true;
                }
                j = -1;
            }
            bufferedSource = source;
            bufferedSink = sink;
            j = -1;
        }
        SocketPolicy socketPolicy = this.dispatcher.peek().getSocketPolicy();
        if ((z2 && socketPolicy == SocketPolicy.EXPECT_CONTINUE) || socketPolicy == SocketPolicy.CONTINUE_ALWAYS) {
            bufferedSink.writeUtf8("HTTP/1.1 100 Continue\r\n");
            bufferedSink.writeUtf8("Content-Length: 0\r\n");
            bufferedSink.writeUtf8("\r\n");
            sink.flush();
        }
        MockResponse mockResponsePeek = this.dispatcher.peek();
        if (j2 != j) {
            z = j2 > 0;
            throttledTransfer(mockResponsePeek, socket, source, Okio.buffer(truncatingBuffer), j2, true);
        } else if (z3) {
            while (true) {
                int i = Integer.parseInt(StringsKt.trim((CharSequence) source.readUtf8LineStrict()).toString(), CharsKt.checkRadix(16));
                if (i == 0) {
                    break;
                }
                arrayList.add(Integer.valueOf(i));
                throttledTransfer(mockResponsePeek, socket, source, Okio.buffer(truncatingBuffer), i, true);
                readEmptyLine(bufferedSource);
            }
            readEmptyLine(bufferedSource);
            z = true;
        } else {
            z = false;
        }
        if (!(!z || HttpMethod.permitsRequestBody(StringsKt.substringBefore$default(utf8LineStrict, TokenParser.SP, (String) null, 2, (Object) null)))) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Request must not have a body: ", utf8LineStrict).toString());
        }
        str = utf8LineStrict;
        iOException = null;
        return new RecordedRequest(str, builder.build(), arrayList, truncatingBuffer.getReceivedByteCount(), truncatingBuffer.getBuffer(), sequenceNumber, socket, iOException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleWebSocketUpgrade(Socket socket, final BufferedSource source, final BufferedSink sink, RecordedRequest request, MockResponse response) throws InterruptedException, IOException {
        String header = request.getHeader(HttpHeaders.SEC_WEBSOCKET_KEY);
        WebSocketProtocol webSocketProtocol = WebSocketProtocol.INSTANCE;
        Intrinsics.checkNotNull(header);
        response.setHeader(HttpHeaders.SEC_WEBSOCKET_ACCEPT, webSocketProtocol.acceptHeader(header));
        writeHttpResponse(socket, sink, response);
        Request requestBuild = new Request.Builder().url((request.getTlsVersion() != null ? "https" : HttpHost.DEFAULT_SCHEME_NAME) + "://" + ((Object) request.getHeader("Host")) + '/').headers(request.getHeaders()).build();
        List listSplit$default = StringsKt.split$default((CharSequence) response.getStatus(), new char[]{TokenParser.SP}, false, 3, 2, (Object) null);
        Response responseBuild = new Response.Builder().code(Integer.parseInt((String) listSplit$default.get(1))).message((String) listSplit$default.get(2)).headers(response.getHeaders()).request(requestBuild).protocol(Protocol.HTTP_1_1).build();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        RealWebSocket.Streams streams = new RealWebSocket.Streams(sink, countDownLatch) { // from class: okhttp3.mockwebserver.MockWebServer$handleWebSocketUpgrade$streams$1
            final /* synthetic */ CountDownLatch $connectionClose;
            final /* synthetic */ BufferedSink $sink;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(false, this.$source, sink);
                this.$sink = sink;
                this.$connectionClose = countDownLatch;
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                this.$connectionClose.countDown();
            }
        };
        TaskRunner taskRunner = this.taskRunner;
        WebSocketListener webSocketListener = response.getWebSocketListener();
        Intrinsics.checkNotNull(webSocketListener);
        RealWebSocket realWebSocket = new RealWebSocket(taskRunner, requestBuild, webSocketListener, new SecureRandom(), 0L, WebSocketExtensions.INSTANCE.parse(response.getHeaders()), 0L);
        WebSocketListener webSocketListener2 = response.getWebSocketListener();
        Intrinsics.checkNotNull(webSocketListener2);
        webSocketListener2.onOpen(realWebSocket, responseBuild);
        String path = request.getPath();
        Intrinsics.checkNotNull(path);
        realWebSocket.initReaderAndWriter(Intrinsics.stringPlus("MockWebServer WebSocket ", path), streams);
        try {
            try {
                realWebSocket.loopReader();
                countDownLatch.await();
            } catch (IOException e) {
                realWebSocket.failWebSocket(e, null);
            }
        } finally {
            Util.closeQuietly(source);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeHttpResponse(Socket socket, BufferedSink sink, MockResponse response) throws InterruptedException, IOException {
        sleepIfDelayed(response.getHeadersDelay(TimeUnit.MILLISECONDS));
        sink.writeUtf8(response.getStatus());
        sink.writeUtf8("\r\n");
        writeHeaders(sink, response.getHeaders());
        Buffer body = response.getBody();
        if (body == null) {
            return;
        }
        sleepIfDelayed(response.getBodyDelay(TimeUnit.MILLISECONDS));
        throttledTransfer(response, socket, body, sink, body.size(), false);
        if (StringsKt.equals(HTTP.CHUNK_CODING, response.getHeaders().get("Transfer-Encoding"), true)) {
            writeHeaders(sink, response.getTrailers());
        }
    }

    private final void writeHeaders(BufferedSink sink, Headers headers) throws IOException {
        for (Pair<? extends String, ? extends String> pair : headers) {
            String strComponent1 = pair.component1();
            String strComponent2 = pair.component2();
            sink.writeUtf8(strComponent1);
            sink.writeUtf8(": ");
            sink.writeUtf8(strComponent2);
            sink.writeUtf8("\r\n");
        }
        sink.writeUtf8("\r\n");
        sink.flush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sleepIfDelayed(long delayMs) throws InterruptedException {
        if (delayMs != 0) {
            Thread.sleep(delayMs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void throttledTransfer(MockResponse policy, Socket socket, BufferedSource source, BufferedSink sink, long byteCount, boolean isRequest) throws InterruptedException, IOException {
        long j = 0;
        if (byteCount == 0) {
            return;
        }
        Buffer buffer = new Buffer();
        long throttleBytesPerPeriod = policy.getThrottleBytesPerPeriod();
        long throttlePeriod = policy.getThrottlePeriod(TimeUnit.MILLISECONDS);
        long j2 = byteCount / ((long) 2);
        boolean z = true;
        if (!isRequest ? policy.getSocketPolicy() != SocketPolicy.DISCONNECT_DURING_RESPONSE_BODY : policy.getSocketPolicy() != SocketPolicy.DISCONNECT_DURING_REQUEST_BODY) {
            z = false;
        }
        long j3 = byteCount;
        while (!socket.isClosed()) {
            long j4 = j;
            while (j4 < throttleBytesPerPeriod) {
                long jMin = Math.min(j3, throttleBytesPerPeriod - j4);
                long j5 = throttleBytesPerPeriod;
                if (z) {
                    jMin = Math.min(jMin, j3 - j2);
                }
                long j6 = source.read(buffer, jMin);
                if (j6 == -1) {
                    return;
                }
                sink.write(buffer, j6);
                sink.flush();
                j4 += j6;
                j3 -= j6;
                if (z && j3 == j2) {
                    socket.close();
                    return;
                }
                j = 0;
                if (j3 == 0) {
                    return;
                } else {
                    throttleBytesPerPeriod = j5;
                }
            }
            sleepIfDelayed(throttlePeriod);
            throttleBytesPerPeriod = throttleBytesPerPeriod;
        }
    }

    private final void readEmptyLine(BufferedSource source) throws IOException {
        String utf8LineStrict = source.readUtf8LineStrict();
        if (!(utf8LineStrict.length() == 0)) {
            throw new IllegalStateException(Intrinsics.stringPlus("Expected empty but was: ", utf8LineStrict).toString());
        }
    }

    public String toString() {
        return "MockWebServer[" + this.portField + ']';
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        shutdown();
    }

    /* JADX INFO: compiled from: MockWebServer.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0003H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/mockwebserver/MockWebServer$TruncatingBuffer;", "Lokio/Sink;", "remainingByteCount", "", "(J)V", "buffer", "Lokio/Buffer;", "getBuffer$mockwebserver", "()Lokio/Buffer;", "receivedByteCount", "getReceivedByteCount$mockwebserver", "()J", "setReceivedByteCount$mockwebserver", "close", "", "flush", "timeout", "Lokio/Timeout;", "write", "source", "byteCount", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class TruncatingBuffer implements Sink {
        private final Buffer buffer = new Buffer();
        private long receivedByteCount;
        private long remainingByteCount;

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
        }

        public TruncatingBuffer(long j) {
            this.remainingByteCount = j;
        }

        /* JADX INFO: renamed from: getBuffer$mockwebserver, reason: from getter */
        public final Buffer getBuffer() {
            return this.buffer;
        }

        /* JADX INFO: renamed from: getReceivedByteCount$mockwebserver, reason: from getter */
        public final long getReceivedByteCount() {
            return this.receivedByteCount;
        }

        public final void setReceivedByteCount$mockwebserver(long j) {
            this.receivedByteCount = j;
        }

        @Override // okio.Sink
        public void write(Buffer source, long byteCount) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            long jMin = Math.min(this.remainingByteCount, byteCount);
            if (jMin > 0) {
                source.read(this.buffer, jMin);
            }
            long j = byteCount - jMin;
            if (j > 0) {
                source.skip(j);
            }
            this.remainingByteCount -= jMin;
            this.receivedByteCount += byteCount;
        }

        @Override // okio.Sink
        /* JADX INFO: renamed from: timeout */
        public Timeout getTimeout() {
            return Timeout.NONE;
        }
    }

    /* JADX INFO: compiled from: MockWebServer.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J&\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokhttp3/mockwebserver/MockWebServer$Http2SocketHandler;", "Lokhttp3/internal/http2/Http2Connection$Listener;", "socket", "Ljava/net/Socket;", "protocol", "Lokhttp3/Protocol;", "(Lokhttp3/mockwebserver/MockWebServer;Ljava/net/Socket;Lokhttp3/Protocol;)V", "sequenceNumber", "Ljava/util/concurrent/atomic/AtomicInteger;", "onStream", "", "stream", "Lokhttp3/internal/http2/Http2Stream;", "pushPromises", "request", "Lokhttp3/mockwebserver/RecordedRequest;", "promises", "", "Lokhttp3/mockwebserver/PushPromise;", "readRequest", "writeResponse", "response", "Lokhttp3/mockwebserver/MockResponse;", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class Http2SocketHandler extends Http2Connection.Listener {
        private final Protocol protocol;
        private final AtomicInteger sequenceNumber;
        private final Socket socket;
        final /* synthetic */ MockWebServer this$0;

        public Http2SocketHandler(MockWebServer this$0, Socket socket, Protocol protocol) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(socket, "socket");
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            this.this$0 = this$0;
            this.socket = socket;
            this.protocol = protocol;
            this.sequenceNumber = new AtomicInteger();
        }

        @Override // okhttp3.internal.http2.Http2Connection.Listener
        public void onStream(Http2Stream stream) throws InterruptedException, IOException {
            Intrinsics.checkNotNullParameter(stream, "stream");
            MockResponse mockResponsePeek = this.this$0.getDispatcher().peek();
            if (mockResponsePeek.getSocketPolicy() == SocketPolicy.RESET_STREAM_AT_START) {
                this.this$0.dispatchBookkeepingRequest(this.sequenceNumber.getAndIncrement(), this.socket);
                ErrorCode errorCodeFromHttp2 = ErrorCode.INSTANCE.fromHttp2(mockResponsePeek.getHttp2ErrorCode());
                Intrinsics.checkNotNull(errorCodeFromHttp2);
                stream.close(errorCodeFromHttp2, null);
                return;
            }
            RecordedRequest request = readRequest(stream);
            this.this$0.atomicRequestCount.incrementAndGet();
            this.this$0.requestQueue.add(request);
            if (request.getFailure() != null) {
                return;
            }
            MockResponse mockResponseDispatch = this.this$0.getDispatcher().dispatch(request);
            if (mockResponseDispatch.getSocketPolicy() == SocketPolicy.DISCONNECT_AFTER_REQUEST) {
                this.socket.close();
                return;
            }
            writeResponse(stream, request, mockResponseDispatch);
            if (MockWebServer.logger.isLoggable(Level.FINE)) {
                MockWebServer.logger.fine(this.this$0 + " received request: " + request + " and responded: " + mockResponseDispatch + " protocol is " + this.protocol);
            }
            if (mockResponseDispatch.getSocketPolicy() == SocketPolicy.DISCONNECT_AT_END) {
                stream.getConnection().shutdown(ErrorCode.NO_ERROR);
            }
        }

        private final RecordedRequest readRequest(Http2Stream stream) throws InterruptedException, IOException {
            IOException e;
            Headers headersTakeHeaders = stream.takeHeaders();
            Headers.Builder builder = new Headers.Builder();
            String str = "<:method omitted>";
            String str2 = "<:path omitted>";
            boolean z = true;
            boolean z2 = true;
            for (Pair<? extends String, ? extends String> pair : headersTakeHeaders) {
                String strComponent1 = pair.component1();
                String strComponent2 = pair.component2();
                if (Intrinsics.areEqual(strComponent1, Header.TARGET_METHOD_UTF8)) {
                    str = strComponent2;
                } else if (Intrinsics.areEqual(strComponent1, Header.TARGET_PATH_UTF8)) {
                    str2 = strComponent2;
                } else if (this.protocol == Protocol.HTTP_2 || this.protocol == Protocol.H2_PRIOR_KNOWLEDGE) {
                    builder.add(strComponent1, strComponent2);
                } else {
                    throw new IllegalStateException();
                }
                if (Intrinsics.areEqual(strComponent1, "expect") && StringsKt.equals(strComponent2, HTTP.EXPECT_CONTINUE, true)) {
                    z2 = false;
                }
            }
            Headers headersBuild = builder.build();
            MockResponse mockResponsePeek = this.this$0.getDispatcher().peek();
            if (z2 || mockResponsePeek.getSocketPolicy() != SocketPolicy.EXPECT_CONTINUE) {
                z = z2;
            } else {
                stream.writeHeaders(CollectionsKt.listOf(new Header(Header.RESPONSE_STATUS, ByteString.INSTANCE.encodeUtf8("100 Continue"))), false, true);
                stream.getConnection().flush();
            }
            Buffer buffer = new Buffer();
            String str3 = str + TokenParser.SP + str2 + " HTTP/1.1";
            if (!z || mockResponsePeek.isDuplex()) {
                e = null;
            } else {
                try {
                    String str4 = headersBuild.get("content-length");
                    this.this$0.throttledTransfer(mockResponsePeek, this.socket, Okio.buffer(stream.getSource()), buffer, str4 == null ? Long.MAX_VALUE : Long.parseLong(str4), true);
                    e = null;
                } catch (IOException e2) {
                    e = e2;
                }
            }
            return new RecordedRequest(str3, headersBuild, CollectionsKt.emptyList(), buffer.size(), buffer, this.sequenceNumber.getAndIncrement(), this.socket, e);
        }

        private final void writeResponse(Http2Stream stream, RecordedRequest request, MockResponse response) throws InterruptedException, IOException {
            stream.getConnection().setSettings(response.getSettings());
            if (response.getSocketPolicy() == SocketPolicy.NO_RESPONSE) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            boolean z = true;
            List listSplit$default = StringsKt.split$default((CharSequence) response.getStatus(), new char[]{TokenParser.SP}, false, 3, 2, (Object) null);
            long headersDelay = response.getHeadersDelay(TimeUnit.MILLISECONDS);
            long bodyDelay = response.getBodyDelay(TimeUnit.MILLISECONDS);
            if (listSplit$default.size() < 2) {
                throw new AssertionError(Intrinsics.stringPlus("Unexpected status: ", response.getStatus()));
            }
            arrayList.add(new Header(Header.RESPONSE_STATUS, (String) listSplit$default.get(1)));
            for (Pair<? extends String, ? extends String> pair : response.getHeaders()) {
                arrayList.add(new Header(pair.component1(), pair.component2()));
            }
            Headers trailers = response.getTrailers();
            this.this$0.sleepIfDelayed(headersDelay);
            Buffer body = response.getBody();
            boolean z2 = body == null && response.getPushPromises().isEmpty() && !response.isDuplex();
            boolean z3 = body == null || bodyDelay != 0;
            if (z2 && trailers.size() != 0) {
                z = false;
            }
            if (!z) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("unsupported: no body and non-empty trailers ", trailers).toString());
            }
            stream.writeHeaders(arrayList, z2, z3);
            if (trailers.size() > 0) {
                stream.enqueueTrailers(trailers);
            }
            pushPromises(stream, request, response.getPushPromises());
            if (body != null) {
                BufferedSink bufferedSinkBuffer = Okio.buffer(stream.getSink());
                MockWebServer mockWebServer = this.this$0;
                try {
                    mockWebServer.sleepIfDelayed(bodyDelay);
                    mockWebServer.throttledTransfer(response, this.socket, body, bufferedSinkBuffer, body.size(), false);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(bufferedSinkBuffer, null);
                    return;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(bufferedSinkBuffer, th);
                        throw th2;
                    }
                }
            }
            if (response.isDuplex()) {
                DuplexResponseBody duplexResponseBody = response.getDuplexResponseBody();
                Intrinsics.checkNotNull(duplexResponseBody);
                duplexResponseBody.onRequest(request, stream);
            } else {
                if (z2) {
                    return;
                }
                stream.close(ErrorCode.NO_ERROR, null);
            }
        }

        private final void pushPromises(Http2Stream stream, RecordedRequest request, List<PushPromise> promises) throws InterruptedException, IOException {
            for (PushPromise pushPromise : promises) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Header(Header.TARGET_AUTHORITY, this.this$0.url(pushPromise.path()).host()));
                arrayList.add(new Header(Header.TARGET_METHOD, pushPromise.method()));
                arrayList.add(new Header(Header.TARGET_PATH, pushPromise.path()));
                for (Pair<? extends String, ? extends String> pair : pushPromise.headers()) {
                    arrayList.add(new Header(pair.component1(), pair.component2()));
                }
                this.this$0.requestQueue.add(new RecordedRequest(pushPromise.method() + TokenParser.SP + pushPromise.path() + " HTTP/1.1", pushPromise.headers(), CollectionsKt.emptyList(), 0L, new Buffer(), this.sequenceNumber.getAndIncrement(), this.socket, null, 128, null));
                writeResponse(stream.getConnection().pushStream(stream.getId(), arrayList, pushPromise.response().getBody() != null), request, pushPromise.response());
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [okhttp3.mockwebserver.MockWebServer$Companion$UNTRUSTED_TRUST_MANAGER$1] */
    static {
        MwsDuplexAccess.Companion companion = MwsDuplexAccess.INSTANCE;
        MwsDuplexAccess.instance = new MwsDuplexAccess() { // from class: okhttp3.mockwebserver.MockWebServer.Companion.1
            @Override // okhttp3.internal.duplex.MwsDuplexAccess
            public void setBody(MockResponse mockResponse, DuplexResponseBody duplexResponseBody) {
                Intrinsics.checkNotNullParameter(mockResponse, "mockResponse");
                Intrinsics.checkNotNullParameter(duplexResponseBody, "duplexResponseBody");
                mockResponse.setBody(duplexResponseBody);
            }
        };
        UNTRUSTED_TRUST_MANAGER = new X509TrustManager() { // from class: okhttp3.mockwebserver.MockWebServer$Companion$UNTRUSTED_TRUST_MANAGER$1
            @Override // javax.net.ssl.X509TrustManager
            public /* bridge */ /* synthetic */ void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                checkClientTrusted(x509CertificateArr, str);
                throw new KotlinNothingValueException();
            }

            @Override // javax.net.ssl.X509TrustManager
            public /* bridge */ /* synthetic */ void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                checkServerTrusted(x509CertificateArr, str);
                throw new KotlinNothingValueException();
            }

            @Override // javax.net.ssl.X509TrustManager
            public Void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Intrinsics.checkNotNullParameter(authType, "authType");
                throw new CertificateException();
            }

            @Override // javax.net.ssl.X509TrustManager
            public Void checkServerTrusted(X509Certificate[] chain, String authType) {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Intrinsics.checkNotNullParameter(authType, "authType");
                throw new AssertionError();
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                throw new AssertionError();
            }
        };
        logger = Logger.getLogger(MockWebServer.class.getName());
    }
}
