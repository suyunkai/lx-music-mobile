package cz.msebera.android.httpclient.impl.auth;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface SpnegoTokenGenerator {
    byte[] generateSpnegoDERObject(byte[] bArr) throws IOException;
}
