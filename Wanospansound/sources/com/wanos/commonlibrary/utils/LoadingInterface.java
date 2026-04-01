package com.wanos.commonlibrary.utils;

/* JADX INFO: loaded from: classes3.dex */
public interface LoadingInterface {

    public interface OnClickListener {
        void onClick();
    }

    void dismissLoading();

    void dismissQrLoading();

    void showEmpty();

    void showEmpty(boolean z);

    void showError();

    void showLoading();

    void showNetworkError();

    void showQrLoading();
}
