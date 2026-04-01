package com.baidubce.callback;

import com.baidubce.model.AbstractBceRequest;

/* JADX INFO: loaded from: classes.dex */
public interface BceProgressCallback<T extends AbstractBceRequest> {
    void onProgress(T t, long j, long j2);
}
