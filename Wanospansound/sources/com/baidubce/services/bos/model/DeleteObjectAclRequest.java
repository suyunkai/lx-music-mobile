package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class DeleteObjectAclRequest extends GenericObjectRequest {
    public DeleteObjectAclRequest() {
    }

    public DeleteObjectAclRequest(String str, String str2) {
        super(str, str2);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public DeleteObjectAclRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public DeleteObjectAclRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericObjectRequest
    public DeleteObjectAclRequest withKey(String str) {
        setKey(str);
        return this;
    }
}
