package com.baidubce.model;

import com.baidubce.auth.BceCredentials;
import okhttp3.Call;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBceRequest {
    private Call call;
    private boolean canceled = false;
    private BceCredentials credentials;

    public abstract AbstractBceRequest withRequestCredentials(BceCredentials bceCredentials);

    public BceCredentials getRequestCredentials() {
        return this.credentials;
    }

    public void setRequestCredentials(BceCredentials bceCredentials) {
        this.credentials = bceCredentials;
    }

    public void cancel() {
        Call call = this.call;
        if (call != null) {
            call.cancel();
        }
        this.canceled = true;
    }

    public boolean getCanceled() {
        return this.canceled;
    }

    public boolean isCanceled() {
        Call call = this.call;
        if (call == null) {
            return this.canceled;
        }
        return call.isCanceled();
    }

    public void setCall(Call call) {
        this.call = call;
    }
}
