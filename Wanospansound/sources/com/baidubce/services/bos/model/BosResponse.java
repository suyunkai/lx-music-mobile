package com.baidubce.services.bos.model;

import com.baidubce.model.AbstractBceResponse;

/* JADX INFO: loaded from: classes.dex */
public class BosResponse extends AbstractBceResponse {
    private String serverCallbackReturnBody;

    public BosResponse() {
        this.metadata = new BosResponseMetadata();
    }

    @Override // com.baidubce.model.AbstractBceResponse
    public BosResponseMetadata getMetadata() {
        return (BosResponseMetadata) this.metadata;
    }

    public String getServerCallbackReturnBody() {
        return this.serverCallbackReturnBody;
    }

    public void setServerCallbackReturnBody(String str) {
        this.serverCallbackReturnBody = str;
    }
}
