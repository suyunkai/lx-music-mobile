package com.wanos.careditproject.ui.adapter;

import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.wanos.careditproject.ui.strategy.CreatorBaseStrategy;
import com.wanos.careditproject.ui.viewholder.BaseViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorCommonChildAdapter<T, VH extends BaseViewHolder<?, ?>> extends ListAdapter<T, VH> {
    private CreatorBaseStrategy<T, VH> mStrategy;

    public CreatorCommonChildAdapter(DiffUtil.ItemCallback<T> itemCallback) {
        super(itemCallback);
    }

    public void registerStrategy(CreatorBaseStrategy<T, VH> creatorBaseStrategy) {
        this.mStrategy = creatorBaseStrategy;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return (VH) this.mStrategy.onCreateViewHolder(viewGroup, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VH vh, int i) {
        this.mStrategy.onBindViewHolder(vh, getItem(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(VH vh) {
        this.mStrategy.onUnbind(vh);
    }
}
