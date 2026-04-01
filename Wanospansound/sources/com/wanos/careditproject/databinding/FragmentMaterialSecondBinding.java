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
public final class FragmentMaterialSecondBinding implements ViewBinding {
    public final MagicIndicator materialSecondMagicindicator;
    public final ViewPager materialSecondMusicList;
    private final LinearLayout rootView;

    private FragmentMaterialSecondBinding(LinearLayout linearLayout, MagicIndicator magicIndicator, ViewPager viewPager) {
        this.rootView = linearLayout;
        this.materialSecondMagicindicator = magicIndicator;
        this.materialSecondMusicList = viewPager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentMaterialSecondBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentMaterialSecondBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_material_second, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentMaterialSecondBinding bind(View view) {
        int i = R.id.material_second_magicindicator;
        MagicIndicator magicIndicator = (MagicIndicator) ViewBindings.findChildViewById(view, i);
        if (magicIndicator != null) {
            i = R.id.material_second_music_list;
            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(view, i);
            if (viewPager != null) {
                return new FragmentMaterialSecondBinding((LinearLayout) view, magicIndicator, viewPager);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
