package cz.msebera.android.httpclient.client.entity;

import cz.msebera.android.httpclient.HttpEntity;

/* JADX INFO: loaded from: classes3.dex */
public class GzipDecompressingEntity extends DecompressingEntity {
    public GzipDecompressingEntity(HttpEntity httpEntity) {
        super(httpEntity, GZIPInputStreamFactory.getInstance());
    }
}
