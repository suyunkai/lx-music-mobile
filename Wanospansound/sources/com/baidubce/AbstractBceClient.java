package com.baidubce;

import com.baidubce.auth.BceV1Signer;
import com.baidubce.callback.BceProgressCallback;
import com.baidubce.http.BceHttpClient;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.DateUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBceClient {
    public static final String DEFAULT_CONTENT_TYPE = "application/json; charset=utf-8";
    public static final String DEFAULT_SERVICE_DOMAIN = "baidubce.com";
    public static final String URL_PREFIX = "v1";
    public BceHttpClient client;
    protected BceClientConfiguration config;
    private HttpResponseHandler[] responseHandlers;
    private ConcurrentHashMap<String, URI> bktVirEndpoint = new ConcurrentHashMap<>();
    private String serviceId = computeServiceId();
    private URI endpoint = computeEndpoint();

    public boolean isRegionSupported() {
        return true;
    }

    public AbstractBceClient(BceClientConfiguration bceClientConfiguration, HttpResponseHandler[] httpResponseHandlerArr) {
        this.config = bceClientConfiguration;
        this.client = new BceHttpClient(bceClientConfiguration, new BceV1Signer());
        this.responseHandlers = httpResponseHandlerArr;
    }

    public URI getEndpoint() {
        return this.endpoint;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public BceHttpClient getClient() {
        return this.client;
    }

    public void setClient(BceHttpClient bceHttpClient) {
        this.client = bceHttpClient;
    }

    protected <T extends AbstractBceResponse, M extends AbstractBceRequest> T invokeHttpClient(InternalRequest<M> internalRequest, Class<T> cls) {
        return (T) invokeHttpClient(internalRequest, cls, null);
    }

    protected <T extends AbstractBceResponse, M extends AbstractBceRequest> T invokeHttpClient(InternalRequest<M> internalRequest, Class<T> cls, BceProgressCallback<M> bceProgressCallback) {
        if (!internalRequest.getHeaders().containsKey("Content-Type")) {
            internalRequest.addHeader("Content-Type", DEFAULT_CONTENT_TYPE);
        }
        if (!internalRequest.getHeaders().containsKey("Date")) {
            internalRequest.addHeader("Date", DateUtils.rfc822DateFormat());
        }
        return (T) this.client.execute(internalRequest, cls, this.responseHandlers, bceProgressCallback);
    }

    private String computeServiceId() {
        String name = getClass().getPackage().getName();
        String str = AbstractBceClient.class.getPackage().getName() + ".services.";
        if (!name.startsWith(str)) {
            throw new IllegalStateException("Unrecognized prefix for the client package : " + name + ", '" + str + "' expected");
        }
        String strSubstring = name.substring(str.length());
        if (strSubstring.indexOf(46) != -1) {
            throw new IllegalStateException("The client class should be put in package like " + str + "XXX");
        }
        String name2 = getClass().getName();
        String str2 = name + '.' + Character.toUpperCase(strSubstring.charAt(0)) + strSubstring.substring(1) + "Client";
        if (name2.equals(str2)) {
            return strSubstring;
        }
        throw new IllegalStateException("Invalid class name " + name2 + ", " + str2 + " expected");
    }

    private URI computeEndpoint() {
        String endpoint = this.config.getEndpoint();
        if (endpoint == null) {
            try {
                endpoint = isRegionSupported() ? String.format("%s://%s.%s.%s", this.config.getProtocol(), this.serviceId, this.config.getRegion(), DEFAULT_SERVICE_DOMAIN) : String.format("%s://%s.%s", this.config.getProtocol(), this.serviceId, DEFAULT_SERVICE_DOMAIN);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid endpoint." + endpoint, e);
            }
        }
        return new URI(endpoint);
    }

    public URI getBktVirEndpoint(String str) {
        if (this.bktVirEndpoint.isEmpty() || !this.bktVirEndpoint.containsKey(str)) {
            return null;
        }
        return this.bktVirEndpoint.get(str);
    }

    public void computeBktVirEndpoint(String str) {
        String str2;
        if (str == null || str.isEmpty()) {
            return;
        }
        String host = this.endpoint.getHost();
        if (host.startsWith(str) && host.split("\\.").length >= 4) {
            str2 = this.config.getProtocol().toString().toLowerCase() + "://" + host;
        } else {
            str2 = this.config.getProtocol().toString().toLowerCase() + "://" + str + '.' + host;
        }
        if (str2 != null) {
            try {
                this.bktVirEndpoint.put(str, new URI(str2));
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid endpoint." + this.endpoint, e);
            }
        }
    }
}
