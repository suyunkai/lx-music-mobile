package okhttp3.internal.connection;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownServiceException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.RoutePlanner;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.platform.Platform;

/* JADX INFO: compiled from: RealRoutePlanner.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0015H\u0002J\u0012\u0010\u001d\u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0013H\u0016J\b\u0010!\u001a\u00020\u000fH\u0016J\b\u0010\"\u001a\u00020#H\u0002J'\u0010$\u001a\u00020#2\u0006\u0010\u001c\u001a\u00020\u00152\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010&H\u0000¢\u0006\u0002\b'J\n\u0010(\u001a\u0004\u0018\u00010)H\u0002J-\u0010*\u001a\u0004\u0018\u00010)2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010#2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010&H\u0000¢\u0006\u0002\b,J\u0012\u0010-\u001a\u0004\u0018\u00010\u00152\u0006\u0010.\u001a\u00020\u001fH\u0002J\u0010\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u000201H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lokhttp3/internal/connection/RealRoutePlanner;", "Lokhttp3/internal/connection/RoutePlanner;", "client", "Lokhttp3/OkHttpClient;", "address", "Lokhttp3/Address;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/internal/connection/RealCall;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "(Lokhttp3/OkHttpClient;Lokhttp3/Address;Lokhttp3/internal/connection/RealCall;Lokhttp3/internal/http/RealInterceptorChain;)V", "getAddress", "()Lokhttp3/Address;", "deferredPlans", "Lkotlin/collections/ArrayDeque;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "getDeferredPlans", "()Lkotlin/collections/ArrayDeque;", "doExtensiveHealthChecks", "", "nextRouteToTry", "Lokhttp3/Route;", "routeSelection", "Lokhttp3/internal/connection/RouteSelector$Selection;", "routeSelector", "Lokhttp3/internal/connection/RouteSelector;", "createTunnelRequest", "Lokhttp3/Request;", "route", "hasNext", "failedConnection", "Lokhttp3/internal/connection/RealConnection;", "isCanceled", "plan", "planConnect", "Lokhttp3/internal/connection/ConnectPlan;", "planConnectToRoute", "routes", "", "planConnectToRoute$okhttp", "planReuseCallConnection", "Lokhttp3/internal/connection/ReusePlan;", "planReusePooledConnection", "planToReplace", "planReusePooledConnection$okhttp", "retryRoute", "connection", "sameHostAndPort", "url", "Lokhttp3/HttpUrl;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class RealRoutePlanner implements RoutePlanner {
    private final Address address;
    private final RealCall call;
    private final OkHttpClient client;
    private final ArrayDeque<RoutePlanner.Plan> deferredPlans;
    private final boolean doExtensiveHealthChecks;
    private Route nextRouteToTry;
    private RouteSelector.Selection routeSelection;
    private RouteSelector routeSelector;

    public RealRoutePlanner(OkHttpClient client, Address address, RealCall call, RealInterceptorChain chain) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(chain, "chain");
        this.client = client;
        this.address = address;
        this.call = call;
        this.doExtensiveHealthChecks = !Intrinsics.areEqual(chain.getRequest().method(), "GET");
        this.deferredPlans = new ArrayDeque<>();
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public Address getAddress() {
        return this.address;
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public ArrayDeque<RoutePlanner.Plan> getDeferredPlans() {
        return this.deferredPlans;
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public boolean isCanceled() {
        return this.call.getCanceled();
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public RoutePlanner.Plan plan() throws IOException {
        ReusePlan reusePlanPlanReuseCallConnection = planReuseCallConnection();
        if (reusePlanPlanReuseCallConnection != null) {
            return reusePlanPlanReuseCallConnection;
        }
        ReusePlan reusePlanPlanReusePooledConnection$okhttp$default = planReusePooledConnection$okhttp$default(this, null, null, 3, null);
        if (reusePlanPlanReusePooledConnection$okhttp$default != null) {
            return reusePlanPlanReusePooledConnection$okhttp$default;
        }
        if (!getDeferredPlans().isEmpty()) {
            return getDeferredPlans().removeFirst();
        }
        ConnectPlan connectPlanPlanConnect = planConnect();
        ReusePlan reusePlanPlanReusePooledConnection$okhttp = planReusePooledConnection$okhttp(connectPlanPlanConnect, connectPlanPlanConnect.getRoutes$okhttp());
        if (reusePlanPlanReusePooledConnection$okhttp != null) {
            return reusePlanPlanReusePooledConnection$okhttp;
        }
        return connectPlanPlanConnect;
    }

    private final ReusePlan planReuseCallConnection() {
        Socket socketReleaseConnectionNoEvents$okhttp;
        RealConnection connection = this.call.getConnection();
        if (connection == null) {
            return null;
        }
        boolean zIsHealthy = connection.isHealthy(this.doExtensiveHealthChecks);
        synchronized (connection) {
            if (!zIsHealthy) {
                connection.setNoNewExchanges(true);
                socketReleaseConnectionNoEvents$okhttp = this.call.releaseConnectionNoEvents$okhttp();
            } else {
                socketReleaseConnectionNoEvents$okhttp = (connection.getNoNewExchanges() || !sameHostAndPort(connection.route().address().url())) ? this.call.releaseConnectionNoEvents$okhttp() : null;
            }
        }
        if (this.call.getConnection() != null) {
            if (!(socketReleaseConnectionNoEvents$okhttp == null)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            return new ReusePlan(connection);
        }
        if (socketReleaseConnectionNoEvents$okhttp != null) {
            _UtilJvmKt.closeQuietly(socketReleaseConnectionNoEvents$okhttp);
        }
        this.call.getEventListener().connectionReleased(this.call, connection);
        return null;
    }

    private final ConnectPlan planConnect() throws IOException {
        Route route = this.nextRouteToTry;
        if (route != null) {
            this.nextRouteToTry = null;
            return planConnectToRoute$okhttp$default(this, route, null, 2, null);
        }
        RouteSelector.Selection selection = this.routeSelection;
        if (selection != null && selection.hasNext()) {
            return planConnectToRoute$okhttp$default(this, selection.next(), null, 2, null);
        }
        RouteSelector routeSelector = this.routeSelector;
        if (routeSelector == null) {
            routeSelector = new RouteSelector(getAddress(), this.call.getClient().getRouteDatabase(), this.call, this.client.getFastFallback(), this.call.getEventListener());
            this.routeSelector = routeSelector;
        }
        if (!routeSelector.hasNext()) {
            throw new IOException("exhausted all routes");
        }
        RouteSelector.Selection next = routeSelector.next();
        this.routeSelection = next;
        if (this.call.getCanceled()) {
            throw new IOException("Canceled");
        }
        return planConnectToRoute$okhttp(next.next(), next.getRoutes());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ReusePlan planReusePooledConnection$okhttp$default(RealRoutePlanner realRoutePlanner, ConnectPlan connectPlan, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            connectPlan = null;
        }
        if ((i & 2) != 0) {
            list = null;
        }
        return realRoutePlanner.planReusePooledConnection$okhttp(connectPlan, list);
    }

    public final ReusePlan planReusePooledConnection$okhttp(ConnectPlan planToReplace, List<Route> routes) {
        RealConnection realConnectionCallAcquirePooledConnection = this.client.connectionPool().getDelegate().callAcquirePooledConnection(this.doExtensiveHealthChecks, getAddress(), this.call, routes, planToReplace != null && planToReplace.getIsReady());
        if (realConnectionCallAcquirePooledConnection == null) {
            return null;
        }
        if (planToReplace != null) {
            this.nextRouteToTry = planToReplace.getRoute();
            planToReplace.closeQuietly();
        }
        this.call.getEventListener().connectionAcquired(this.call, realConnectionCallAcquirePooledConnection);
        return new ReusePlan(realConnectionCallAcquirePooledConnection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ConnectPlan planConnectToRoute$okhttp$default(RealRoutePlanner realRoutePlanner, Route route, List list, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            list = null;
        }
        return realRoutePlanner.planConnectToRoute$okhttp(route, list);
    }

    public final ConnectPlan planConnectToRoute$okhttp(Route route, List<Route> routes) throws IOException {
        Intrinsics.checkNotNullParameter(route, "route");
        if (route.address().sslSocketFactory() == null) {
            if (!route.address().connectionSpecs().contains(ConnectionSpec.CLEARTEXT)) {
                throw new UnknownServiceException("CLEARTEXT communication not enabled for client");
            }
            String strHost = route.address().url().host();
            if (!Platform.INSTANCE.get().isCleartextTrafficPermitted(strHost)) {
                throw new UnknownServiceException("CLEARTEXT communication to " + strHost + " not permitted by network security policy");
            }
        } else if (route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            throw new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS");
        }
        return new ConnectPlan(this.client, this.call, this, route, routes, 0, route.requiresTunnel() ? createTunnelRequest(route) : null, -1, false);
    }

    private final Request createTunnelRequest(Route route) throws IOException {
        Request requestBuild = new Request.Builder().url(route.address().url()).method("CONNECT", null).header("Host", _UtilJvmKt.toHostHeader(route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", _UtilCommonKt.userAgent).build();
        Request requestAuthenticate = route.address().proxyAuthenticator().authenticate(route, new Response.Builder().request(requestBuild).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header("Proxy-Authenticate", "OkHttp-Preemptive").build());
        return requestAuthenticate == null ? requestBuild : requestAuthenticate;
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public boolean hasNext(RealConnection failedConnection) {
        RouteSelector routeSelector;
        Route routeRetryRoute;
        if ((!getDeferredPlans().isEmpty()) || this.nextRouteToTry != null) {
            return true;
        }
        if (failedConnection != null && (routeRetryRoute = retryRoute(failedConnection)) != null) {
            this.nextRouteToTry = routeRetryRoute;
            return true;
        }
        RouteSelector.Selection selection = this.routeSelection;
        boolean z = false;
        if (selection != null && selection.hasNext()) {
            z = true;
        }
        if (z || (routeSelector = this.routeSelector) == null) {
            return true;
        }
        return routeSelector.hasNext();
    }

    private final Route retryRoute(RealConnection connection) {
        synchronized (connection) {
            if (connection.getRouteFailureCount() != 0) {
                return null;
            }
            if (!connection.getNoNewExchanges()) {
                return null;
            }
            if (!_UtilJvmKt.canReuseConnectionFor(connection.route().address().url(), getAddress().url())) {
                return null;
            }
            return connection.route();
        }
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public boolean sameHostAndPort(HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        HttpUrl httpUrlUrl = getAddress().url();
        return url.port() == httpUrlUrl.port() && Intrinsics.areEqual(url.host(), httpUrlUrl.host());
    }
}
