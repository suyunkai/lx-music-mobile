package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.careditproject.R;
import com.wanos.media.databinding.LayoutTitleBarBinding;
import net.lucode.hackware.magicindicator.MagicIndicator;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityCreatorCommunityBinding implements ViewBinding {
    public final ViewPager2 cPager;
    public final MagicIndicator cTab;
    public final LayoutTitleBarBinding cTitleBar;
    private final ConstraintLayout rootView;

    private ActivityCreatorCommunityBinding(ConstraintLayout constraintLayout, ViewPager2 viewPager2, MagicIndicator magicIndicator, LayoutTitleBarBinding layoutTitleBarBinding) {
        this.rootView = constraintLayout;
        this.cPager = viewPager2;
        this.cTab = magicIndicator;
        this.cTitleBar = layoutTitleBarBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCreatorCommunityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityCreatorCommunityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_creator_community, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCreatorCommunityBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.c_pager;
        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
        if (viewPager2 != null) {
            i = R.id.c_tab;
            MagicIndicator magicIndicator = (MagicIndicator) ViewBindings.findChildViewById(view, i);
            if (magicIndicator != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.c_title_bar))) != null) {
                return new ActivityCreatorCommunityBinding((ConstraintLayout) view, viewPager2, magicIndicator, LayoutTitleBarBinding.bind(viewFindChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
