package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ItemCreatorHomeHeaderBinding extends ViewDataBinding {
    public final ImageView btnMySpace;
    public final ImageView btnSoundArea;
    public final ImageView btnStartCreate;

    protected ItemCreatorHomeHeaderBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3) {
        super(obj, view, i);
        this.btnMySpace = imageView;
        this.btnSoundArea = imageView2;
        this.btnStartCreate = imageView3;
    }

    public static ItemCreatorHomeHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemCreatorHomeHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_header, viewGroup, z, obj);
    }

    public static ItemCreatorHomeHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeHeaderBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemCreatorHomeHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_header, null, false, obj);
    }

    public static ItemCreatorHomeHeaderBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeHeaderBinding bind(View view, Object obj) {
        return (ItemCreatorHomeHeaderBinding) bind(obj, view, R.layout.item_creator_home_header);
    }
}
