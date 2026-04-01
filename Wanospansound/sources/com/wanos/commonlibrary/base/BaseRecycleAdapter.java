package com.wanos.commonlibrary.base;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseRecycleAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public static final int ITEM_TYPE_FOOTER = 4371;
    public static final int ITEM_TYPE_HEADER = 4370;
    public static final int ITEM_TYPE_NORMAL = 4369;
    protected List<T> datas;
    protected View mFooterView;
    protected View mHeaderView;
    private boolean isHasHeader = false;
    private boolean isHasFooter = false;

    protected abstract void bindData(VH vh, int i);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

    public BaseRecycleAdapter(List<T> list) {
        if (list != null) {
            this.datas = list;
        } else {
            this.datas = new ArrayList();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VH vh, int i) {
        if (this.isHasHeader && this.isHasFooter) {
            if (i == 0 || i == this.datas.size() + 1) {
                return;
            } else {
                bindData(vh, i - 1);
            }
        }
        if (i != 0 && this.isHasHeader && !this.isHasFooter) {
            bindData(vh, i - 1);
        }
        if (!this.isHasHeader && this.isHasFooter) {
            if (i == this.datas.size()) {
                return;
            } else {
                bindData(vh, i);
            }
        }
        if (this.isHasHeader || this.isHasFooter) {
            return;
        }
        bindData(vh, i);
    }

    public void setHeaderView(View view) {
        this.mHeaderView = view;
        this.isHasHeader = true;
        notifyDataSetChanged();
    }

    public void setFooterView(View view) {
        this.mFooterView = view;
        this.isHasFooter = true;
        notifyDataSetChanged();
    }

    public void removeHeaderView() {
        this.isHasHeader = false;
        notifyDataSetChanged();
    }

    public void removeFooterView() {
        this.isHasFooter = false;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        boolean z = this.isHasHeader;
        return (z && i == 0) ? ITEM_TYPE_HEADER : (z && this.isHasFooter && i == this.datas.size() + 1) ? ITEM_TYPE_FOOTER : (!this.isHasHeader && this.isHasFooter && i == this.datas.size()) ? ITEM_TYPE_FOOTER : ITEM_TYPE_NORMAL;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.datas;
        int size = list == null ? 0 : list.size();
        if (this.isHasFooter) {
            size++;
        }
        return this.isHasHeader ? size + 1 : size;
    }

    public boolean isHasHeader() {
        return this.isHasHeader;
    }

    public boolean isHasFooter() {
        return this.isHasFooter;
    }

    public void setData(List<T> list) {
        this.datas = list;
    }

    public void addDataAll(List<T> list) {
        this.datas.addAll(list);
    }

    public List<T> getDatas() {
        return this.datas;
    }
}
