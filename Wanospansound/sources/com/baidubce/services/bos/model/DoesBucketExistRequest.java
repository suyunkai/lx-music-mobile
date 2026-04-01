package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class DoesBucketExistRequest extends GenericBucketRequest {
    public DoesBucketExistRequest(String str) {
        super(str);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public DoesBucketExistRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public DoesBucketExistRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }
}
