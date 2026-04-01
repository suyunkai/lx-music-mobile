package com.baidubce.http;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.NTLMEngineImpl;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import okhttp3.Authenticator;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/* JADX INFO: loaded from: classes.dex */
public class HttpClientFactory {
    public OkHttpClient createHttpClient(BceClientConfiguration bceClientConfiguration) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Protocol.HTTP_1_1);
        OkHttpClient.Builder builderFollowSslRedirects = new OkHttpClient.Builder().protocols(arrayList).hostnameVerifier(new HostnameVerifier() { // from class: com.baidubce.http.HttpClientFactory.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
            }
        }).retryOnConnectionFailure(false).cache(null).followRedirects(false).followSslRedirects(false);
        if (bceClientConfiguration != null) {
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(bceClientConfiguration.getMaxConnections());
            builderFollowSslRedirects.protocols(arrayList).connectTimeout(bceClientConfiguration.getConnectionTimeoutInMillis(), TimeUnit.MILLISECONDS).writeTimeout(bceClientConfiguration.getSocketTimeoutInMillis(), TimeUnit.MILLISECONDS).readTimeout(bceClientConfiguration.getSocketTimeoutInMillis(), TimeUnit.MILLISECONDS).dispatcher(dispatcher).connectionPool(new ConnectionPool(bceClientConfiguration.getMaxConnections(), bceClientConfiguration.getKeepAliveDuration(), TimeUnit.SECONDS));
            String proxyHost = bceClientConfiguration.getProxyHost();
            int proxyPort = bceClientConfiguration.getProxyPort();
            if (proxyHost != null && proxyPort > 0) {
                builderFollowSslRedirects.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
                String proxyUsername = bceClientConfiguration.getProxyUsername();
                String proxyPassword = bceClientConfiguration.getProxyPassword();
                String proxyDomain = bceClientConfiguration.getProxyDomain();
                String proxyWorkstation = bceClientConfiguration.getProxyWorkstation();
                if (proxyUsername != null && proxyPassword != null) {
                    builderFollowSslRedirects.proxyAuthenticator(new NTLMAuthenticator(proxyUsername, proxyPassword, proxyDomain, proxyWorkstation));
                }
            }
            if (bceClientConfiguration.getDns() != null) {
                builderFollowSslRedirects.dns(bceClientConfiguration.getDns());
            } else if (bceClientConfiguration.getIpv4Priority().booleanValue()) {
                builderFollowSslRedirects.dns(new V4PriorityDns());
            }
        }
        return builderFollowSslRedirects.build();
    }

    public static class NTLMAuthenticator implements Authenticator {
        private final String domain;
        final NTLMEngineImpl engine;
        private final String ntlmMsg1;
        private final String password;
        private final String username;
        private final String workstation;

        public NTLMAuthenticator(String str, String str2, String str3, String str4) {
            NTLMEngineImpl nTLMEngineImpl = new NTLMEngineImpl();
            this.engine = nTLMEngineImpl;
            this.domain = str4;
            this.username = str;
            this.password = str2;
            this.workstation = str3;
            String strGenerateType1Msg = null;
            try {
                strGenerateType1Msg = nTLMEngineImpl.generateType1Msg(null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.ntlmMsg1 = strGenerateType1Msg;
        }

        @Override // okhttp3.Authenticator
        public Request authenticate(Route route, Response response) throws IOException {
            String strGenerateType3Msg;
            List<String> listValues = response.headers().values("WWW-Authenticate");
            if (listValues.contains("NTLM")) {
                return response.request().newBuilder().header("Authorization", "NTLM " + this.ntlmMsg1).build();
            }
            try {
                strGenerateType3Msg = this.engine.generateType3Msg(this.username, this.password, this.domain, this.workstation, listValues.get(0).substring(5));
            } catch (Exception e) {
                e.printStackTrace();
                strGenerateType3Msg = null;
            }
            return response.request().newBuilder().header("Authorization", "NTLM " + strGenerateType3Msg).build();
        }
    }

    public class V4PriorityDns implements Dns {
        public V4PriorityDns() {
        }

        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (str == null) {
                throw new UnknownHostException("hostname == null");
            }
            try {
                ArrayList arrayList = new ArrayList();
                for (InetAddress inetAddress : InetAddress.getAllByName(str)) {
                    if (inetAddress instanceof Inet4Address) {
                        arrayList.add(0, inetAddress);
                    } else {
                        arrayList.add(inetAddress);
                    }
                }
                return arrayList;
            } catch (Exception e) {
                UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour");
                unknownHostException.initCause(e);
                throw unknownHostException;
            }
        }
    }
}
