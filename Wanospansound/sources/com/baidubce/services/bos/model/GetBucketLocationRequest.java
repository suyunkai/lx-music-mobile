package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class GetBucketLocationRequest extends GenericBucketRequest {
    public GetBucketLocationRequest(String str) {
        super(str);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public GetBucketLocationRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public GetBucketLocationRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
