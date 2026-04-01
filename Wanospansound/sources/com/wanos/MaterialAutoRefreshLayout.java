package com.wanos;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialAutoRefreshLayout extends MaterialRefreshLayout {
    private boolean isAutoRefresh;
    private int startLoadMoreItemThreshold;

    public MaterialAutoRefreshLayout(Context context) {
        this(context, null, 0);
    }

    public MaterialAutoRefreshLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialAutoRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isAutoRefresh = false;
        this.startLoadMoreItemThreshold = 30;
        setMaterialStyle(false);
    }

    public int getStartLoadMoreItemThreshold() {
        return this.startLoadMoreItemThreshold;
    }

    public void setStartLoadMoreItemThreshold(int i) {
        this.startLoadMoreItemThreshold = i;
    }

    @Override // com.wanos.MaterialRefreshLayout, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mChildView != null && (this.mChildView instanceof RecyclerView)) {
            ((RecyclerView) this.mChildView).setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.wanos.MaterialAutoRefreshLayout.1
                @Override // android.view.View.OnScrollChangeListener
                public void onScrollChange(View view, int i, int i2, int i3, int i4) {
                    if (!MaterialAutoRefreshLayout.this.needAutoLoadMore() || !MaterialAutoRefreshLayout.this.isLoadMore || MaterialAutoRefreshLayout.this.mMaterialFooterView == null || MaterialAutoRefreshLayout.this.isLoadMoreing) {
                        return;
                    }
                    MaterialAutoRefreshLayout.this.soveLoadMoreLogic();
                }
            });
        }
        setIsOverLay(true);
    }

    public boolean needAutoLoadMore() {
        boolean z = !canChildScrollDown();
        if (this.mChildView != null && (this.mChildView instanceof RecyclerView)) {
            RecyclerView recyclerView = (RecyclerView) this.mChildView;
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (linearLayoutManager != null) {
                int iFindLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter != null && adapter.getItemCount() - iFindLastVisibleItemPosition < this.startLoadMoreItemThreshold) {
                    if (!z) {
                        this.isAutoRefresh = true;
                    }
                    return true;
                }
            }
        }
        return z;
    }

    @Override // com.wanos.MaterialRefreshLayout
    protected void soveLoadMoreLogic() {
        this.isLoadMoreing = true;
        if (!this.isAutoRefresh) {
            this.mMaterialFooterView.setVisibility(0);
        } else {
            this.isAutoRefresh = false;
        }
        this.mMaterialFooterView.onBegin(this);
        this.mMaterialFooterView.onRefreshing(this);
        if (this.refreshListener != null) {
            this.refreshListener.onRefreshLoadMore(this);
        }
    }
}
