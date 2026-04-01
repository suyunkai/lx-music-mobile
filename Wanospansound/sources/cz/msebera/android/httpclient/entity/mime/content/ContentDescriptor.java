package cz.msebera.android.httpclient.entity.mime.content;

/* JADX INFO: loaded from: classes3.dex */
public interface ContentDescriptor {
    String getCharset();

    long getContentLength();

    String getMediaType();

    String getMimeType();

    String getSubType();

    String getTransferEncoding();
}
