package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutGuide3Binding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvContent;
    public final TextView tvGuideTitle;
    public final TextView tvNext;
    public final TextView tvSkip;
    public final View viewBlueDot;
    public final View viewWhiteDot;

    private LayoutGuide3Binding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view, View view2) {
        this.rootView = constraintLayout;
        this.tvContent = textView;
        this.tvGuideTitle = textView2;
        this.tvNext = textView3;
        this.tvSkip = textView4;
        this.viewBlueDot = view;
        this.viewWhiteDot = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutGuide3Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutGuide3Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_guide3, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutGuide3Binding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.tv_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.tv_guide_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                i = R.id.tv_next;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView3 != null) {
                    i = R.id.tv_skip;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView4 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.view_blue_dot))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.view_white_dot))) != null) {
                        return new LayoutGuide3Binding((ConstraintLayout) view, textView, textView2, textView3, textView4, viewFindChildViewById, viewFindChildViewById2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
