package com.ecarx.eas.sdk.mediacenter.callback;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DefaultCallback<T> implements ApiCallback<T> {
    @Override // com.ecarx.eas.sdk.mediacenter.callback.ApiCallback
    public void onProgress(Object obj, int i) {
    }

    public abstract void onResult(T t);

    @Override // com.ecarx.eas.sdk.mediacenter.callback.ApiCallback
    public void onDataReceived(T t) {
        onResult(t);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.callback.ApiCallback
    public void onException(int i, String str) {
        onResult(null);
    }
}
