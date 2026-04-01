package com.wanos.media.juyihall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.juyihall.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemRec2TitleBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final WanosTextView textView;
    public final TextView tvNextBatch;
    public final TextView tvPlayAll;

    private ItemRec2TitleBinding(ConstraintLayout constraintLayout, WanosTextView wanosTextView, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.textView = wanosTextView;
        this.tvNextBatch = textView;
        this.tvPlayAll = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemRec2TitleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRec2TitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_rec2_title, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemRec2TitleBinding bind(View view) {
        int i = R.id.text_view;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.tv_next_batch;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.tv_play_all;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new ItemRec2TitleBinding((ConstraintLayout) view, wanosTextView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
