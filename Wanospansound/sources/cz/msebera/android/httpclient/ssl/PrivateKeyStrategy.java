package cz.msebera.android.httpclient.ssl;

import java.net.Socket;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface PrivateKeyStrategy {
    String chooseAlias(Map<String, PrivateKeyDetails> map, Socket socket);
}
