package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ItemCreatorHomeTitleBinding extends ViewDataBinding {
    protected ItemCreatorHomeTitleBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static ItemCreatorHomeTitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeTitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemCreatorHomeTitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_title, viewGroup, z, obj);
    }

    public static ItemCreatorHomeTitleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeTitleBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemCreatorHomeTitleBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_title, null, false, obj);
    }

    public static ItemCreatorHomeTitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeTitleBinding bind(View view, Object obj) {
        return (ItemCreatorHomeTitleBinding) bind(obj, view, R.layout.item_creator_home_title);
    }
}
