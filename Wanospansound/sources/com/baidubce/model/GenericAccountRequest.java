package com.baidubce.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class GenericAccountRequest extends AbstractBceRequest {
    @Override // com.baidubce.model.AbstractBceRequest
    public GenericAccountRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }
}
