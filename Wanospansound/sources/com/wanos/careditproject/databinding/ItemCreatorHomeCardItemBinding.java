package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ItemCreatorHomeCardItemBinding extends ViewDataBinding {
    public final FrameLayout btnAudioBookPlay;
    public final ImageView btnCollect;
    public final CardView cvItem;
    public final ImageView ivProjectItem;
    public final CardView layoutCollect;

    @Bindable
    protected ProjectInfo mData;
    public final ProgressBar pbAudiobookPlay;
    public final FrameLayout playLayout;
    public final ImageView playState;
    public final ConstraintLayout projectContentLl;
    public final ProgressBar seekbarProgress;
    public final TextView tvDuration0;
    public final TextView tvDuration1;
    public final TextView tvEdit;
    public final TextView tvPlayTime;
    public final TextView tvProjectName;

    public abstract void setData(ProjectInfo projectInfo);

    protected ItemCreatorHomeCardItemBinding(Object obj, View view, int i, FrameLayout frameLayout, ImageView imageView, CardView cardView, ImageView imageView2, CardView cardView2, ProgressBar progressBar, FrameLayout frameLayout2, ImageView imageView3, ConstraintLayout constraintLayout, ProgressBar progressBar2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.btnAudioBookPlay = frameLayout;
        this.btnCollect = imageView;
        this.cvItem = cardView;
        this.ivProjectItem = imageView2;
        this.layoutCollect = cardView2;
        this.pbAudiobookPlay = progressBar;
        this.playLayout = frameLayout2;
        this.playState = imageView3;
        this.projectContentLl = constraintLayout;
        this.seekbarProgress = progressBar2;
        this.tvDuration0 = textView;
        this.tvDuration1 = textView2;
        this.tvEdit = textView3;
        this.tvPlayTime = textView4;
        this.tvProjectName = textView5;
    }

    public ProjectInfo getData() {
        return this.mData;
    }

    public static ItemCreatorHomeCardItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeCardItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemCreatorHomeCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_card_item, viewGroup, z, obj);
    }

    public static ItemCreatorHomeCardItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeCardItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemCreatorHomeCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_creator_home_card_item, null, false, obj);
    }

    public static ItemCreatorHomeCardItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCreatorHomeCardItemBinding bind(View view, Object obj) {
        return (ItemCreatorHomeCardItemBinding) bind(obj, view, R.layout.item_creator_home_card_item);
    }
}
