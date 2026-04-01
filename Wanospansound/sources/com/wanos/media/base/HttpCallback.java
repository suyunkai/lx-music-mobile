package com.wanos.media.base;

import android.content.Context;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.media.entity.BaseEntity;

/* JADX INFO: loaded from: classes3.dex */
public abstract class HttpCallback<T> extends ResponseCallBack<BaseEntity<T>> {
    public HttpCallback(Context context) {
        super(context);
    }

    public HttpCallback() {
        super(null);
    }
}
