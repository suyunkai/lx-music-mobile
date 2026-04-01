package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class ViewCreatorTabBinding implements ViewBinding {
    public final ImageView indicator;
    private final ConstraintLayout rootView;
    public final WanosTextView textView;

    private ViewCreatorTabBinding(ConstraintLayout constraintLayout, ImageView imageView, WanosTextView wanosTextView) {
        this.rootView = constraintLayout;
        this.indicator = imageView;
        this.textView = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewCreatorTabBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewCreatorTabBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_creator_tab, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewCreatorTabBinding bind(View view) {
        int i = R.id.indicator;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.text_view;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                return new ViewCreatorTabBinding((ConstraintLayout) view, imageView, wanosTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
