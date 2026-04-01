package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class CreateBucketRequest extends GenericBucketRequest {
    public CreateBucketRequest(String str) {
        super(str);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public CreateBucketRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public CreateBucketRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
