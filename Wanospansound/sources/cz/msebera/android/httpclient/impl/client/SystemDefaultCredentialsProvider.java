package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.auth.NTCredentials;
import cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.util.Args;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class SystemDefaultCredentialsProvider implements CredentialsProvider {
    private static final Map<String, String> SCHEME_MAP;
    private final BasicCredentialsProvider internal = new BasicCredentialsProvider();

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        SCHEME_MAP = concurrentHashMap;
        concurrentHashMap.put("Basic".toUpperCase(Locale.ROOT), "Basic");
        concurrentHashMap.put("Digest".toUpperCase(Locale.ROOT), "Digest");
        concurrentHashMap.put("NTLM".toUpperCase(Locale.ROOT), "NTLM");
        concurrentHashMap.put("Negotiate".toUpperCase(Locale.ROOT), "SPNEGO");
        concurrentHashMap.put("Kerberos".toUpperCase(Locale.ROOT), "Kerberos");
    }

    private static String translateScheme(String str) {
        if (str == null) {
            return null;
        }
        String str2 = SCHEME_MAP.get(str);
        return str2 != null ? str2 : str;
    }

    @Override // cz.msebera.android.httpclient.client.CredentialsProvider
    public void setCredentials(AuthScope authScope, Credentials credentials) {
        this.internal.setCredentials(authScope, credentials);
    }

    private static PasswordAuthentication getSystemCreds(String str, AuthScope authScope, Authenticator.RequestorType requestorType) {
        return Authenticator.requestPasswordAuthentication(authScope.getHost(), null, authScope.getPort(), str, null, translateScheme(authScope.getScheme()), null, requestorType);
    }

    @Override // cz.msebera.android.httpclient.client.CredentialsProvider
    public Credentials getCredentials(AuthScope authScope) {
        String schemeName;
        Credentials usernamePasswordCredentials;
        Args.notNull(authScope, "Auth scope");
        Credentials credentials = this.internal.getCredentials(authScope);
        if (credentials != null) {
            return credentials;
        }
        if (authScope.getHost() != null) {
            HttpHost origin = authScope.getOrigin();
            if (origin != null) {
                schemeName = origin.getSchemeName();
            } else {
                schemeName = authScope.getPort() == 443 ? "https" : HttpHost.DEFAULT_SCHEME_NAME;
            }
            PasswordAuthentication systemCreds = getSystemCreds(schemeName, authScope, Authenticator.RequestorType.SERVER);
            if (systemCreds == null) {
                systemCreds = getSystemCreds(schemeName, authScope, Authenticator.RequestorType.PROXY);
            }
            if (systemCreds == null && (systemCreds = getProxyCredentials(HttpHost.DEFAULT_SCHEME_NAME, authScope)) == null) {
                systemCreds = getProxyCredentials("https", authScope);
            }
            if (systemCreds != null) {
                String property = System.getProperty("http.auth.ntlm.domain");
                if (property != null) {
                    return new NTCredentials(systemCreds.getUserName(), new String(systemCreds.getPassword()), null, property);
                }
                if ("NTLM".equalsIgnoreCase(authScope.getScheme())) {
                    usernamePasswordCredentials = new NTCredentials(systemCreds.getUserName(), new String(systemCreds.getPassword()), null, null);
                } else {
                    usernamePasswordCredentials = new UsernamePasswordCredentials(systemCreds.getUserName(), new String(systemCreds.getPassword()));
                }
                return usernamePasswordCredentials;
            }
        }
        return null;
    }

    private static PasswordAuthentication getProxyCredentials(String str, AuthScope authScope) {
        String property;
        String property2;
        String property3 = System.getProperty(str + ".proxyHost");
        if (property3 == null || (property = System.getProperty(str + ".proxyPort")) == null) {
            return null;
        }
        try {
            if (authScope.match(new AuthScope(property3, Integer.parseInt(property))) < 0 || (property2 = System.getProperty(str + ".proxyUser")) == null) {
                return null;
            }
            String property4 = System.getProperty(str + ".proxyPassword");
            return new PasswordAuthentication(property2, property4 != null ? property4.toCharArray() : new char[0]);
        } catch (NumberFormatException unused) {
        }
        return null;
    }

    @Override // cz.msebera.android.httpclient.client.CredentialsProvider
    public void clear() {
        this.internal.clear();
    }
}
