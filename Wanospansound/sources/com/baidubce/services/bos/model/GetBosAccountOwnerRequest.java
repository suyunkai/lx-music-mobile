package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/* JADX INFO: loaded from: classes.dex */
public class GetBosAccountOwnerRequest extends AbstractBceRequest {
    @Override // com.baidubce.model.AbstractBceRequest
    public GetBosAccountOwnerRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }
}
