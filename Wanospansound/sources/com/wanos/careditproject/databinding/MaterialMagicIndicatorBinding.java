package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.wanos.careditproject.R;
import net.lucode.hackware.magicindicator.MagicIndicator;

/* JADX INFO: loaded from: classes3.dex */
public final class MaterialMagicIndicatorBinding implements ViewBinding {
    public final LinearLayout materialIndicatorLaout;
    public final MagicIndicator materialMagicindicator;
    public final ViewPager myWallpaperViewPager;
    private final LinearLayout rootView;

    private MaterialMagicIndicatorBinding(LinearLayout linearLayout, LinearLayout linearLayout2, MagicIndicator magicIndicator, ViewPager viewPager) {
        this.rootView = linearLayout;
        this.materialIndicatorLaout = linearLayout2;
        this.materialMagicindicator = magicIndicator;
        this.myWallpaperViewPager = viewPager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static MaterialMagicIndicatorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MaterialMagicIndicatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.material_magic_indicator, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MaterialMagicIndicatorBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.material_magicindicator;
        MagicIndicator magicIndicator = (MagicIndicator) ViewBindings.findChildViewById(view, i);
        if (magicIndicator != null) {
            i = R.id.my_wallpaper_view_pager;
            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(view, i);
            if (viewPager != null) {
                return new MaterialMagicIndicatorBinding(linearLayout, linearLayout, magicIndicator, viewPager);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
