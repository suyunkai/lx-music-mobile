package com.ecarx.eas.sdk.mediacenter.callback;

/* JADX INFO: loaded from: classes2.dex */
public interface ApiCallback<T> {
    void onDataReceived(T t);

    void onException(int i, String str);

    void onProgress(Object obj, int i);
}
