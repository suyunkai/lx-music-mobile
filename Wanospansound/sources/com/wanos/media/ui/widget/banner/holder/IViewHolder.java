package com.wanos.media.ui.widget.banner.holder;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public interface IViewHolder<T, VH> {
    void onBindView(VH vh, T t, int i, int i2);

    VH onCreateHolder(ViewGroup viewGroup, int i);
}
