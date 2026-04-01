package com.wanos;

/* JADX INFO: loaded from: classes3.dex */
public interface MaterialHeadListener {
    void onBegin(MaterialRefreshLayout materialRefreshLayout);

    void onComlete(MaterialRefreshLayout materialRefreshLayout);

    void onPull(MaterialRefreshLayout materialRefreshLayout, float f);

    void onRefreshing(MaterialRefreshLayout materialRefreshLayout);

    void onRelease(MaterialRefreshLayout materialRefreshLayout, float f);
}
