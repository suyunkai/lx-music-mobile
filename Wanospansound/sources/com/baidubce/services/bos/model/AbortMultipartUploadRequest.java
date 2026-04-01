package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/* JADX INFO: loaded from: classes.dex */
public class AbortMultipartUploadRequest extends GenericUploadRequest {
    public AbortMultipartUploadRequest(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    @Override // com.baidubce.model.AbstractBceRequest
    public AbortMultipartUploadRequest withRequestCredentials(BceCredentials bceCredentials) {
        setRequestCredentials(bceCredentials);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericBucketRequest
    public AbortMultipartUploadRequest withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericObjectRequest
    public AbortMultipartUploadRequest withKey(String str) {
        setKey(str);
        return this;
    }

    @Override // com.baidubce.services.bos.model.GenericUploadRequest
    public AbortMultipartUploadRequest withUploadId(String str) {
        setUploadId(str);
        return this;
    }
}
