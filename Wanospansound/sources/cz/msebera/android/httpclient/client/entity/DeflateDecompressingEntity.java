package cz.msebera.android.httpclient.client.entity;

import cz.msebera.android.httpclient.HttpEntity;

/* JADX INFO: loaded from: classes3.dex */
public class DeflateDecompressingEntity extends DecompressingEntity {
    public DeflateDecompressingEntity(HttpEntity httpEntity) {
        super(httpEntity, DeflateInputStreamFactory.getInstance());
    }
}
