package com.baidubce.services.bos.model;

/* JADX INFO: loaded from: classes.dex */
public class PutSuperObjectResponse extends CompleteMultipartUploadResponse {
    private boolean isUploadPart = true;
    private String serverCallbackReturnBody;

    @Override // com.baidubce.services.bos.model.CompleteMultipartUploadResponse, com.baidubce.services.bos.model.BosResponse
    public String getServerCallbackReturnBody() {
        return this.serverCallbackReturnBody;
    }

    @Override // com.baidubce.services.bos.model.CompleteMultipartUploadResponse, com.baidubce.services.bos.model.BosResponse
    public void setServerCallbackReturnBody(String str) {
        this.serverCallbackReturnBody = str;
    }

    public boolean getIsUploadPart() {
        return this.isUploadPart;
    }

    public void setIsUploadPart(boolean z) {
        this.isUploadPart = z;
    }
}
