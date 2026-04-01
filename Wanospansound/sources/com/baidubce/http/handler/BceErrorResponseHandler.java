package com.baidubce.http.handler;

import com.baidubce.BceErrorResponse;
import com.baidubce.BceServiceException;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.Headers;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class BceErrorResponseHandler implements HttpResponseHandler {
    @Override // com.baidubce.http.handler.HttpResponseHandler
    public boolean handle(BceHttpResponse bceHttpResponse, AbstractBceResponse abstractBceResponse) throws Exception {
        if (bceHttpResponse.getStatusCode() / 100 == 2) {
            return false;
        }
        InputStream content = bceHttpResponse.getContent();
        BceServiceException bceServiceException = null;
        if (content != null) {
            BceErrorResponse bceErrorResponseLoadError = JsonUtils.loadError(content);
            if (bceErrorResponseLoadError == null) {
                BceServiceException bceServiceException2 = new BceServiceException(bceHttpResponse.getStatusText());
                bceServiceException2.setErrorCode(null);
                bceServiceException2.setRequestId(bceHttpResponse.getHeader(Headers.BCE_REQUEST_ID));
                bceServiceException = bceServiceException2;
            } else if (bceErrorResponseLoadError.getMessage() != null) {
                bceServiceException = new BceServiceException(bceErrorResponseLoadError.getMessage());
                bceServiceException.setErrorCode(bceErrorResponseLoadError.getCode());
                bceServiceException.setRequestId(bceErrorResponseLoadError.getRequestId());
            }
            content.close();
        }
        if (bceServiceException == null) {
            bceServiceException = new BceServiceException(bceHttpResponse.getStatusText());
            bceServiceException.setRequestId(abstractBceResponse.getMetadata().getBceRequestId());
        }
        bceServiceException.setStatusCode(bceHttpResponse.getStatusCode());
        if (bceServiceException.getStatusCode() >= 500) {
            bceServiceException.setErrorType(BceServiceException.ErrorType.Service);
            throw bceServiceException;
        }
        bceServiceException.setErrorType(BceServiceException.ErrorType.Client);
        throw bceServiceException;
    }
}
