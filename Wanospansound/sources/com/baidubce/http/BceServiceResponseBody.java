package com.baidubce.http;

import com.baidubce.callback.BceProgressCallback;
import com.baidubce.model.AbstractBceRequest;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* JADX INFO: loaded from: classes.dex */
public class BceServiceResponseBody<T extends AbstractBceRequest> extends ResponseBody {
    private BceProgressCallback<T> bceProgressCallback;
    private BufferedSource bceRespBufferedSource;
    private final ResponseBody bceResponseBody;
    private T request;

    public BceServiceResponseBody(ResponseBody responseBody, T t, BceProgressCallback<T> bceProgressCallback) {
        this.bceResponseBody = responseBody;
        this.request = t;
        this.bceProgressCallback = bceProgressCallback;
    }

    @Override // okhttp3.ResponseBody
    /* JADX INFO: renamed from: contentType */
    public MediaType get$contentType() {
        return this.bceResponseBody.get$contentType();
    }

    @Override // okhttp3.ResponseBody
    /* JADX INFO: renamed from: contentLength */
    public long getContentLength() {
        return this.bceResponseBody.getContentLength();
    }

    @Override // okhttp3.ResponseBody
    /* JADX INFO: renamed from: source */
    public BufferedSource getSource() {
        if (this.bceRespBufferedSource == null) {
            this.bceRespBufferedSource = Okio.buffer(source(this.bceResponseBody.getSource()));
        }
        return this.bceRespBufferedSource;
    }

    private Source source(BufferedSource bufferedSource) {
        return new ForwardingSource(bufferedSource) { // from class: com.baidubce.http.BceServiceResponseBody.1
            private long totalBytesRead = 0;

            /* JADX WARN: Multi-variable type inference failed */
            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                long j2 = super.read(buffer, j);
                this.totalBytesRead += j2 != -1 ? j2 : 0L;
                if (BceServiceResponseBody.this.bceProgressCallback != null && this.totalBytesRead > 0) {
                    BceServiceResponseBody.this.bceProgressCallback.onProgress(BceServiceResponseBody.this.request, this.totalBytesRead, BceServiceResponseBody.this.bceResponseBody.getContentLength());
                }
                return j2;
            }
        };
    }
}
