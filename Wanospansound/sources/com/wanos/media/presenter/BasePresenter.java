package com.wanos.media.presenter;

import com.wanos.media.view.IBaseView;

/* JADX INFO: loaded from: classes3.dex */
public class BasePresenter<V extends IBaseView> implements IPresenter {
    protected V mView;
    protected int mPageIndex = 1;
    protected int mPageSize = 100;
    protected int orderPageSize = 10;

    @Override // com.wanos.media.presenter.IPresenter
    public void onDestroy() {
    }

    @Override // com.wanos.media.presenter.IPresenter
    public void onStart() {
    }

    public void attachView(V v) {
        this.mView = v;
    }

    public void dettachView() {
        this.mView = null;
    }

    public boolean isViewAttached() {
        return this.mView != null;
    }

    public V getView() {
        return this.mView;
    }
}
