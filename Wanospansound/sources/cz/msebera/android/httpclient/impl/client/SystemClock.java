package cz.msebera.android.httpclient.impl.client;

/* JADX INFO: loaded from: classes3.dex */
class SystemClock implements Clock {
    SystemClock() {
    }

    @Override // cz.msebera.android.httpclient.impl.client.Clock
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
