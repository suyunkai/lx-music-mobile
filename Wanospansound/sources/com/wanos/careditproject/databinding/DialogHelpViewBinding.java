package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogHelpViewBinding implements ViewBinding {
    public final LinearLayout IndicatorList;
    public final ImageView btnCloseHelp;
    public final ConstraintLayout dialogView;
    public final FrameLayout dialogWin;
    private final FrameLayout rootView;
    public final ViewPager2 vpContent;

    private DialogHelpViewBinding(FrameLayout frameLayout, LinearLayout linearLayout, ImageView imageView, ConstraintLayout constraintLayout, FrameLayout frameLayout2, ViewPager2 viewPager2) {
        this.rootView = frameLayout;
        this.IndicatorList = linearLayout;
        this.btnCloseHelp = imageView;
        this.dialogView = constraintLayout;
        this.dialogWin = frameLayout2;
        this.vpContent = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogHelpViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogHelpViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_help_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogHelpViewBinding bind(View view) {
        int i = R.id.Indicator_list;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.btn_close_help;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.dialog_view;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                if (constraintLayout != null) {
                    FrameLayout frameLayout = (FrameLayout) view;
                    i = R.id.vp_content;
                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                    if (viewPager2 != null) {
                        return new DialogHelpViewBinding(frameLayout, linearLayout, imageView, constraintLayout, frameLayout, viewPager2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
