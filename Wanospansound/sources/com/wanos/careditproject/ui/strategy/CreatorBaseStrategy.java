package com.wanos.careditproject.ui.strategy;

import android.view.ViewGroup;
import com.wanos.careditproject.ui.viewholder.BaseViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CreatorBaseStrategy<T, VH extends BaseViewHolder> {
    public abstract void onBindViewHolder(VH vh, T t, int i);

    public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

    public void onUnbind(VH vh) {
    }
}
