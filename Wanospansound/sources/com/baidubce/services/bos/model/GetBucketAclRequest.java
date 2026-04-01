package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class GetBucketAclRequest extends GenericBucketRequest {
    public GetBucketAclRequest(String str) {
        super(str);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public GetBucketAclRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public GetBucketAclRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
