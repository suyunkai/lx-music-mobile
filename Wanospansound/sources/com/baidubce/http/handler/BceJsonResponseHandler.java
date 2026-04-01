package com.baidubce.http.handler;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class BceJsonResponseHandler implements HttpResponseHandler {
    @Override // com.baidubce.http.handler.HttpResponseHandler
    public boolean handle(BceHttpResponse bceHttpResponse, AbstractBceResponse abstractBceResponse) throws Exception {
        InputStream content = bceHttpResponse.getContent();
        if (content == null) {
            return true;
        }
        if (abstractBceResponse.getMetadata().getContentLength() > 0 || HTTP.CHUNK_CODING.equalsIgnoreCase(abstractBceResponse.getMetadata().getTransferEncoding())) {
            JsonUtils.load(bceHttpResponse, abstractBceResponse);
        }
        content.close();
        return true;
    }
}
