package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.ProjectTagBean;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ItemCreatorHomeTagListBinding extends ViewDataBinding {

    @Bindable
    protected ProjectTagBean mData;
    public final RecyclerView rvTagList;
    public final WanosTextView tvTag;

    public abstract void setData(ProjectTagBean projectTagBean);

    protected ItemCreatorHomeTagListBinding(Object obj, View view, int i, RecyclerView recyclerView, WanosTextView wanosTextView) {
        super(obj, view, i);
        this.rvTagList = recyclerView;
        this.tvTag = wanosTextView;
    }

    public ProjectTagBean getData() {
        return this.mData;
    }

    public static ItemCreatorHomeTagListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeTagListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemCreatorHomeTagListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_tag_list, viewGroup, z, obj);
    }

    public static ItemCreatorHomeTagListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeTagListBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemCreatorHomeTagListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_tag_list, null, false, obj);
    }

    public static ItemCreatorHomeTagListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeTagListBinding bind(View view, Object obj) {
        return (ItemCreatorHomeTagListBinding) bind(obj, view, R.layout.item_creator_home_tag_list);
    }
}
