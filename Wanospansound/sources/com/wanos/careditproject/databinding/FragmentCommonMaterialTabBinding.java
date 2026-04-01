package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.careditproject.R;
import net.lucode.hackware.magicindicator.MagicIndicator;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentCommonMaterialTabBinding implements ViewBinding {
    public final MagicIndicator commonMaterialMi;
    public final ViewPager2 commonMaterialVp;
    private final ConstraintLayout rootView;

    private FragmentCommonMaterialTabBinding(ConstraintLayout constraintLayout, MagicIndicator magicIndicator, ViewPager2 viewPager2) {
        this.rootView = constraintLayout;
        this.commonMaterialMi = magicIndicator;
        this.commonMaterialVp = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCommonMaterialTabBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCommonMaterialTabBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_common_material_tab, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCommonMaterialTabBinding bind(View view) {
        int i = R.id.common_material_mi;
        MagicIndicator magicIndicator = (MagicIndicator) ViewBindings.findChildViewById(view, i);
        if (magicIndicator != null) {
            i = R.id.common_material_vp;
            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
            if (viewPager2 != null) {
                return new FragmentCommonMaterialTabBinding((ConstraintLayout) view, magicIndicator, viewPager2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
