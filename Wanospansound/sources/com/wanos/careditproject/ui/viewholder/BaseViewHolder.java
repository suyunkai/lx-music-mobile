package com.wanos.careditproject.ui.viewholder;

import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseViewHolder<VB extends ViewDataBinding, T> extends RecyclerView.ViewHolder {
    protected final VB binding;
    protected T data;

    protected abstract void onBind();

    protected abstract void onUnbind();

    public BaseViewHolder(VB vb) {
        super(vb.getRoot());
        this.binding = vb;
    }

    public final void bind(T t) {
        this.data = t;
        onBind();
        this.binding.setVariable(BR.data, t);
        this.binding.executePendingBindings();
    }

    public final void unbind() {
        onUnbind();
        this.data = null;
    }
}
