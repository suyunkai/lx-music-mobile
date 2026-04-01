package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ItemCreatorHomeCardListBinding extends ViewDataBinding {
    public final RecyclerView recyclerView;

    protected ItemCreatorHomeCardListBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.recyclerView = recyclerView;
    }

    public static ItemCreatorHomeCardListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeCardListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemCreatorHomeCardListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_card_list, viewGroup, z, obj);
    }

    public static ItemCreatorHomeCardListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeCardListBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemCreatorHomeCardListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_card_list, null, false, obj);
    }

    public static ItemCreatorHomeCardListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeCardListBinding bind(View view, Object obj) {
        return (ItemCreatorHomeCardListBinding) bind(obj, view, R.layout.item_creator_home_card_list);
    }
}
