package com.wanos.media.presenter;

/* JADX INFO: loaded from: classes3.dex */
public interface IABCallBack {
    default void requestFinish(int code, boolean isSuccess) {
    }

    default void requestFinish(boolean isSuccess) {
    }
}
