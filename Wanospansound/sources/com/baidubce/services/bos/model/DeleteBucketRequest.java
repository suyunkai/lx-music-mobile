package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class DeleteBucketRequest extends GenericBucketRequest {
    public DeleteBucketRequest(String str) {
        super(str);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public DeleteBucketRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public DeleteBucketRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
