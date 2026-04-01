package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.ProjectTagBean;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ItemCreatorHomeTagItemBinding extends ViewDataBinding {

    @Bindable
    protected ProjectTagBean mData;
    public final CheckBox tvTag;

    public abstract void setData(ProjectTagBean projectTagBean);

    protected ItemCreatorHomeTagItemBinding(Object obj, View view, int i, CheckBox checkBox) {
        super(obj, view, i);
        this.tvTag = checkBox;
    }

    public ProjectTagBean getData() {
        return this.mData;
    }

    public static ItemCreatorHomeTagItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeTagItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemCreatorHomeTagItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_tag_item, viewGroup, z, obj);
    }

    public static ItemCreatorHomeTagItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeTagItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemCreatorHomeTagItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_tag_item, null, false, obj);
    }

    public static ItemCreatorHomeTagItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeTagItemBinding bind(View view, Object obj) {
        return (ItemCreatorHomeTagItemBinding) bind(obj, view, R.layout.item_creator_home_tag_item);
    }
}
