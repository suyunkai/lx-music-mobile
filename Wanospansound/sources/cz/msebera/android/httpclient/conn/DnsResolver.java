package cz.msebera.android.httpclient.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes3.dex */
public interface DnsResolver {
    InetAddress[] resolve(String str) throws UnknownHostException;
}
