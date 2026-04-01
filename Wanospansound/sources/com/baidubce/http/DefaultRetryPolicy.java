package com.baidubce.http;

import androidx.media3.exoplayer.dash.DashMediaSource;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.ErrorCode;
import com.baidubce.util.BLog;
import com.baidubce.util.CheckUtils;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DefaultRetryPolicy implements RetryPolicy {
    private static final int SCALE_FACTOR = 300;
    private long maxDelayInMillis;
    private int maxErrorRetry;

    public DefaultRetryPolicy() {
        this(3, DashMediaSource.DEFAULT_FALLBACK_TARGET_LIVE_OFFSET_MS);
    }

    public DefaultRetryPolicy(int i, long j) {
        CheckUtils.checkArgument(i >= 0, "maxErrorRetry should be a non-negative.");
        CheckUtils.checkArgument(j >= 0, "maxDelayInMillis should be a non-negative.");
        this.maxErrorRetry = i;
        this.maxDelayInMillis = j;
    }

    @Override // com.baidubce.http.RetryPolicy
    public int getMaxErrorRetry() {
        return this.maxErrorRetry;
    }

    @Override // com.baidubce.http.RetryPolicy
    public long getMaxDelayInMillis() {
        return this.maxDelayInMillis;
    }

    @Override // com.baidubce.http.RetryPolicy
    public long getDelayBeforeNextRetryInMillis(BceClientException bceClientException, int i) {
        if (!shouldRetry(bceClientException, i)) {
            return -1L;
        }
        if (i < 0) {
            return 0L;
        }
        return (1 << (i + 1)) * 300;
    }

    protected boolean shouldRetry(BceClientException bceClientException, int i) {
        if (bceClientException.getCause() instanceof IOException) {
            BLog.error("Retry for IOException.");
            return true;
        }
        if (!(bceClientException instanceof BceServiceException)) {
            return false;
        }
        BceServiceException bceServiceException = (BceServiceException) bceClientException;
        if (bceServiceException.getStatusCode() == 500) {
            BLog.error("Retry for internal server error.");
            return true;
        }
        if (bceServiceException.getStatusCode() == 502) {
            BLog.debug("Retry for bad gateway.");
            return true;
        }
        if (bceServiceException.getStatusCode() == 503) {
            BLog.error("Retry for service unavailable.");
            return true;
        }
        String errorCode = bceServiceException.getErrorCode();
        if (ErrorCode.REQUEST_EXPIRED.equals(errorCode)) {
            BLog.error("Retry for request expired.");
            return true;
        }
        if (!ErrorCode.REQUEST_TIME_TOO_SKEWED.equals(errorCode)) {
            return false;
        }
        BLog.error("Retry for request time too skewed");
        return true;
    }
}
