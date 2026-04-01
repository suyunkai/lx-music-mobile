package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class GetObjectAclRequest extends GenericObjectRequest {
    public GetObjectAclRequest() {
    }

    public GetObjectAclRequest(String str, String str2) {
        super(str, str2);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public GetObjectAclRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public GetObjectAclRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericObjectRequest
    public GetObjectAclRequest withKey(String str) {
        setKey(str);
        return this;
    }
}
