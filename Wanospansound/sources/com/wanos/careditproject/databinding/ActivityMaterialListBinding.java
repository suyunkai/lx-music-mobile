package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;
import net.lucode.hackware.magicindicator.MagicIndicator;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityMaterialListBinding implements ViewBinding {
    public final ViewPager2 materialListViewpager;
    public final MagicIndicator materialMagicindicator;
    private final LinearLayout rootView;
    public final ImageView searchBtn;
    public final ImageView turnBack;
    public final WanosTextView tvTitle;

    private ActivityMaterialListBinding(LinearLayout linearLayout, ViewPager2 viewPager2, MagicIndicator magicIndicator, ImageView imageView, ImageView imageView2, WanosTextView wanosTextView) {
        this.rootView = linearLayout;
        this.materialListViewpager = viewPager2;
        this.materialMagicindicator = magicIndicator;
        this.searchBtn = imageView;
        this.turnBack = imageView2;
        this.tvTitle = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMaterialListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMaterialListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_material_list, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMaterialListBinding bind(View view) {
        int i = R.id.material_list_viewpager;
        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
        if (viewPager2 != null) {
            i = R.id.material_magicindicator;
            MagicIndicator magicIndicator = (MagicIndicator) ViewBindings.findChildViewById(view, i);
            if (magicIndicator != null) {
                i = R.id.search_btn;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.turn_back;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView2 != null) {
                        i = R.id.tv_title;
                        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                        if (wanosTextView != null) {
                            return new ActivityMaterialListBinding((LinearLayout) view, viewPager2, magicIndicator, imageView, imageView2, wanosTextView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
