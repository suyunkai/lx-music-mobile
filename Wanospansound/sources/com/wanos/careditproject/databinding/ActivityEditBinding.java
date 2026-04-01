package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityEditBinding implements ViewBinding {
    public final LinearLayout btnShowName;
    public final ConstraintLayout clTopLayout;
    public final ImageView editingBack;
    public final LinearLayout editingPublish;
    public final ImageView ivShowName;
    public final ConstraintLayout layoutTime;
    public final FragmentContainerView pageContainerView;
    private final LinearLayout rootView;
    public final Spinner spinnerShowType;
    public final ImageView tab0;
    public final ImageView tab0Bg;
    public final ImageView tab1;
    public final ImageView tab1Bg;
    public final TextView tvShowName;
    public final TextView tvTime0;
    public final TextView tvTime1;
    public final TextView tvTimeT;

    private ActivityEditBinding(LinearLayout linearLayout, LinearLayout linearLayout2, ConstraintLayout constraintLayout, ImageView imageView, LinearLayout linearLayout3, ImageView imageView2, ConstraintLayout constraintLayout2, FragmentContainerView fragmentContainerView, Spinner spinner, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.btnShowName = linearLayout2;
        this.clTopLayout = constraintLayout;
        this.editingBack = imageView;
        this.editingPublish = linearLayout3;
        this.ivShowName = imageView2;
        this.layoutTime = constraintLayout2;
        this.pageContainerView = fragmentContainerView;
        this.spinnerShowType = spinner;
        this.tab0 = imageView3;
        this.tab0Bg = imageView4;
        this.tab1 = imageView5;
        this.tab1Bg = imageView6;
        this.tvShowName = textView;
        this.tvTime0 = textView2;
        this.tvTime1 = textView3;
        this.tvTimeT = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEditBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityEditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_edit, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEditBinding bind(View view) {
        int i = R.id.btn_show_name;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.cl_top_layout;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
            if (constraintLayout != null) {
                i = R.id.editing_back;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.editing_publish;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout2 != null) {
                        i = R.id.iv_show_name;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView2 != null) {
                            i = R.id.layout_time;
                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                            if (constraintLayout2 != null) {
                                i = R.id.page_container_view;
                                FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
                                if (fragmentContainerView != null) {
                                    i = R.id.spinner_show_type;
                                    Spinner spinner = (Spinner) ViewBindings.findChildViewById(view, i);
                                    if (spinner != null) {
                                        i = R.id.tab_0;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                        if (imageView3 != null) {
                                            i = R.id.tab_0_bg;
                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                            if (imageView4 != null) {
                                                i = R.id.tab_1;
                                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                if (imageView5 != null) {
                                                    i = R.id.tab_1_bg;
                                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                    if (imageView6 != null) {
                                                        i = R.id.tv_show_name;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView != null) {
                                                            i = R.id.tv_time_0;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_time_1;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_time_t;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView4 != null) {
                                                                        return new ActivityEditBinding((LinearLayout) view, linearLayout, constraintLayout, imageView, linearLayout2, imageView2, constraintLayout2, fragmentContainerView, spinner, imageView3, imageView4, imageView5, imageView6, textView, textView2, textView3, textView4);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
