package com.baidubce.services.bos.model;

/* JADX INFO: loaded from: classes.dex */
public class GetObjectMetadataResponse extends BosResponse {
    private ObjectMetadata objectMetadata = new ObjectMetadata();

    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }
}
