package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wanos.careditproject.R;
import com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ItemCreatorMineWorkBinding extends ViewDataBinding {
    public final ImageView ivUser;
    public final CardView layoutAvatar;
    public final CardView layoutImage;
    public final LinearLayout layoutTitle;
    public final ConstraintLayout layoutUser;
    public final ConstraintLayout layoutWork;

    @Bindable
    protected CreatorMineWorkItemViewModel mData;
    public final TextView tvUserDesc;
    public final TextView tvUserName;

    public abstract void setData(CreatorMineWorkItemViewModel creatorMineWorkItemViewModel);

    protected ItemCreatorMineWorkBinding(Object obj, View view, int i, ImageView imageView, CardView cardView, CardView cardView2, LinearLayout linearLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivUser = imageView;
        this.layoutAvatar = cardView;
        this.layoutImage = cardView2;
        this.layoutTitle = linearLayout;
        this.layoutUser = constraintLayout;
        this.layoutWork = constraintLayout2;
        this.tvUserDesc = textView;
        this.tvUserName = textView2;
    }

    public CreatorMineWorkItemViewModel getData() {
        return this.mData;
    }

    public static ItemCreatorMineWorkBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorMineWorkBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemCreatorMineWorkBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_mine_work, viewGroup, z, obj);
    }

    public static ItemCreatorMineWorkBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorMineWorkBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemCreatorMineWorkBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_mine_work, null, false, obj);
    }

    public static ItemCreatorMineWorkBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorMineWorkBinding bind(View view, Object obj) {
        return (ItemCreatorMineWorkBinding) bind(obj, view, R.layout.item_creator_mine_work);
    }
}
