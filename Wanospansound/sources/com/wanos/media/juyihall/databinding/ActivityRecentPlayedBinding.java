package com.wanos.media.juyihall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.media.juyihall.R;
import net.lucode.hackware.magicindicator.MagicIndicator;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityRecentPlayedBinding implements ViewBinding {
    public final MagicIndicator abMineTabIndicator;
    public final LinearLayout abTitleBar;
    private final ConstraintLayout rootView;
    public final ImageView titleLeftBtn;
    public final TextView tvTitle;
    public final ViewPager2 viewPager;

    private ActivityRecentPlayedBinding(ConstraintLayout constraintLayout, MagicIndicator magicIndicator, LinearLayout linearLayout, ImageView imageView, TextView textView, ViewPager2 viewPager2) {
        this.rootView = constraintLayout;
        this.abMineTabIndicator = magicIndicator;
        this.abTitleBar = linearLayout;
        this.titleLeftBtn = imageView;
        this.tvTitle = textView;
        this.viewPager = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRecentPlayedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityRecentPlayedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_recent_played, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRecentPlayedBinding bind(View view) {
        int i = R.id.ab_mine_tab_indicator;
        MagicIndicator magicIndicator = (MagicIndicator) ViewBindings.findChildViewById(view, i);
        if (magicIndicator != null) {
            i = R.id.ab_title_bar;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.title_left_btn;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.tv_title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.view_pager;
                        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                        if (viewPager2 != null) {
                            return new ActivityRecentPlayedBinding((ConstraintLayout) view, magicIndicator, linearLayout, imageView, textView, viewPager2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
