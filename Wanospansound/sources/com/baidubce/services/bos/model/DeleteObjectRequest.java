package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class DeleteObjectRequest extends GenericObjectRequest {
    public DeleteObjectRequest(String str, String str2) {
        super(str, str2);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public DeleteObjectRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public DeleteObjectRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericObjectRequest
    public DeleteObjectRequest withKey(String str) {
        setKey(str);
        return this;
    }
}
