package com.baidubce.http;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.Signer;
import com.baidubce.callback.BceProgressCallback;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.CheckUtils;
import com.baidubce.util.HttpUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/* JADX INFO: loaded from: classes.dex */
public class BceHttpClient {
    private static final HttpClientFactory httpClientFactory = new HttpClientFactory();
    private final BceClientConfiguration config;
    private long diffMillis;
    private OkHttpClient httpClient;
    private final Signer signer;

    public BceHttpClient(BceClientConfiguration bceClientConfiguration, Signer signer) {
        this(bceClientConfiguration, httpClientFactory.createHttpClient(bceClientConfiguration), signer);
    }

    public BceHttpClient(BceClientConfiguration bceClientConfiguration, OkHttpClient okHttpClient, Signer signer) {
        this.diffMillis = 0L;
        CheckUtils.isNotNull(bceClientConfiguration, "config should not be null.");
        CheckUtils.isNotNull(signer, "signer should not be null.");
        this.config = bceClientConfiguration;
        this.httpClient = okHttpClient;
        this.signer = signer;
    }

    public <T extends AbstractBceRequest> OkHttpClient addResponseProgressCallback(final T t, final BceProgressCallback<T> bceProgressCallback) {
        return this.httpClient.newBuilder().addNetworkInterceptor(new Interceptor() { // from class: com.baidubce.http.BceHttpClient.1
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Response responseProceed = chain.proceed(chain.request());
                return responseProceed.newBuilder().body(new BceServiceResponseBody(responseProceed.body(), t, bceProgressCallback)).build();
            }
        }).build();
    }

    public <T extends AbstractBceResponse, M extends AbstractBceRequest> T execute(InternalRequest<M> internalRequest, Class<T> cls, HttpResponseHandler[] httpResponseHandlerArr) {
        return (T) execute(internalRequest, cls, httpResponseHandlerArr, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01ae A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T extends com.baidubce.model.AbstractBceResponse, M extends com.baidubce.model.AbstractBceRequest> T execute(com.baidubce.internal.InternalRequest<M> r19, java.lang.Class<T> r20, com.baidubce.http.handler.HttpResponseHandler[] r21, com.baidubce.callback.BceProgressCallback<M> r22) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.http.BceHttpClient.execute(com.baidubce.internal.InternalRequest, java.lang.Class, com.baidubce.http.handler.HttpResponseHandler[], com.baidubce.callback.BceProgressCallback):com.baidubce.model.AbstractBceResponse");
    }

    protected long getDelayBeforeNextRetryInMillis(InternalRequest internalRequest, BceClientException bceClientException, int i, RetryPolicy retryPolicy) {
        int i2 = i - 1;
        if (i2 >= retryPolicy.getMaxErrorRetry()) {
            return -1L;
        }
        return Math.min(retryPolicy.getMaxDelayInMillis(), retryPolicy.getDelayBeforeNextRetryInMillis(bceClientException, i2));
    }

    protected <T extends AbstractBceRequest> Request createHttpRequest(InternalRequest<T> internalRequest, BceProgressCallback<T> bceProgressCallback) {
        String aSCIIString = internalRequest.getUri().toASCIIString();
        String canonicalQueryString = HttpUtils.getCanonicalQueryString(internalRequest.getParameters(), false);
        if (canonicalQueryString.length() > 0) {
            aSCIIString = aSCIIString + "?" + canonicalQueryString;
        }
        Request.Builder builderUrl = new Request.Builder().url(aSCIIString);
        if (internalRequest.getHttpMethod() == HttpMethodName.GET) {
            builderUrl.get();
        } else if (internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            if (internalRequest.getContent() != null) {
                builderUrl.put(new BceServiceRequestBody(internalRequest, bceProgressCallback));
            } else {
                builderUrl.put(RequestBody.create((MediaType) null, new byte[0]));
            }
        } else if (internalRequest.getHttpMethod() == HttpMethodName.POST) {
            if (internalRequest.getContent() != null) {
                builderUrl.post(new BceServiceRequestBody(internalRequest, bceProgressCallback));
            } else {
                builderUrl.post(RequestBody.create((MediaType) null, new byte[0]));
            }
        } else if (internalRequest.getHttpMethod() == HttpMethodName.DELETE) {
            builderUrl.delete();
        } else if (internalRequest.getHttpMethod() == HttpMethodName.HEAD) {
            builderUrl.head();
        } else {
            throw new BceClientException("Unknown HTTP method name: " + internalRequest.getHttpMethod());
        }
        for (Map.Entry<String, String> entry : internalRequest.getHeaders().entrySet()) {
            if (!entry.getKey().equalsIgnoreCase("Content-Length") && !entry.getKey().equalsIgnoreCase("Host")) {
                builderUrl.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return builderUrl.build();
    }

    private class BceServiceRequestBody<T extends AbstractBceRequest> extends RequestBody {
        private BceProgressCallback<T> callback;
        private long length;
        private MediaType mediaType;
        private T request;
        private InputStream restartableInputStream;

        BceServiceRequestBody(InternalRequest<T> internalRequest, BceProgressCallback<T> bceProgressCallback) {
            if (internalRequest.getContent() != null) {
                this.mediaType = MediaType.parse(internalRequest.getHeaders().get("Content-Type"));
                this.restartableInputStream = internalRequest.getContent();
                this.length = getContentLength(internalRequest);
                this.callback = bceProgressCallback;
                this.request = (T) internalRequest.getRequest();
            }
        }

        BceServiceRequestBody(InternalRequest<T> internalRequest) {
            if (internalRequest.getContent() != null) {
                this.mediaType = MediaType.parse(internalRequest.getHeaders().get("Content-Type"));
                this.restartableInputStream = internalRequest.getContent();
                this.length = getContentLength(internalRequest);
                this.callback = null;
                this.request = (T) internalRequest.getRequest();
            }
        }

        @Override // okhttp3.RequestBody
        /* JADX INFO: renamed from: contentType */
        public MediaType getContentType() {
            return this.mediaType;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            long jContentLength = contentLength();
            Source source = Okio.source(this.restartableInputStream);
            long j = 0;
            while (j < jContentLength) {
                long j2 = source.read(bufferedSink.getBufferField(), Math.min(jContentLength - j, BceHttpClient.this.config.getUploadSegmentPart()));
                if (j2 == -1) {
                    break;
                }
                long j3 = j + j2;
                bufferedSink.flush();
                BceProgressCallback<T> bceProgressCallback = this.callback;
                if (bceProgressCallback != null) {
                    bceProgressCallback.onProgress(this.request, j3, jContentLength);
                }
                j = j3;
            }
            if (source != null) {
                source.close();
            }
        }

        @Override // okhttp3.RequestBody
        public long contentLength() throws IOException {
            return this.length;
        }

        private long getContentLength(InternalRequest<T> internalRequest) {
            String str = internalRequest.getHeaders().get("Content-Length");
            if (str != null) {
                return Long.parseLong(str);
            }
            return 0L;
        }
    }
}
