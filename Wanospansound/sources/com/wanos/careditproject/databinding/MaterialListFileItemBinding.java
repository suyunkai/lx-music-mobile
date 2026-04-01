package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class MaterialListFileItemBinding implements ViewBinding {
    public final RecyclerView expansionRecyclerList;
    public final LinearLayout layoutFileItem;
    public final AppCompatImageView materialFileArrows;
    public final ImageView materialFileIcon;
    public final TextView materialFileName;
    public final ProgressBar materialFilePb;
    private final FrameLayout rootView;

    private MaterialListFileItemBinding(FrameLayout frameLayout, RecyclerView recyclerView, LinearLayout linearLayout, AppCompatImageView appCompatImageView, ImageView imageView, TextView textView, ProgressBar progressBar) {
        this.rootView = frameLayout;
        this.expansionRecyclerList = recyclerView;
        this.layoutFileItem = linearLayout;
        this.materialFileArrows = appCompatImageView;
        this.materialFileIcon = imageView;
        this.materialFileName = textView;
        this.materialFilePb = progressBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static MaterialListFileItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MaterialListFileItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.material_list_file_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MaterialListFileItemBinding bind(View view) {
        int i = R.id.expansion_recycler_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            i = R.id.layout_file_item;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.material_file_arrows;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView != null) {
                    i = R.id.material_file_icon;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        i = R.id.material_file_name;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.material_file_pb;
                            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                            if (progressBar != null) {
                                return new MaterialListFileItemBinding((FrameLayout) view, recyclerView, linearLayout, appCompatImageView, imageView, textView, progressBar);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
