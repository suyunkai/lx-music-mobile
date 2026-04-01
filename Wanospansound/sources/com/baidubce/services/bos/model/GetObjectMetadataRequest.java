package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class GetObjectMetadataRequest extends GenericObjectRequest {
    public GetObjectMetadataRequest(String str, String str2) {
        super(str, str2);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public GetObjectMetadataRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public GetObjectMetadataRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericObjectRequest
    public GetObjectMetadataRequest withKey(String str) {
        setKey(str);
        return this;
    }
}
