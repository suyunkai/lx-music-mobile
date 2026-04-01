package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityMeThemeBinding implements ViewBinding {
    public final WanosTextView btnMingXiang;
    public final WanosTextView btnXiaoQi;
    public final FragmentContainerView flMeTheme;
    private final LinearLayoutCompat rootView;

    private ActivityMeThemeBinding(LinearLayoutCompat linearLayoutCompat, WanosTextView wanosTextView, WanosTextView wanosTextView2, FragmentContainerView fragmentContainerView) {
        this.rootView = linearLayoutCompat;
        this.btnMingXiang = wanosTextView;
        this.btnXiaoQi = wanosTextView2;
        this.flMeTheme = fragmentContainerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ActivityMeThemeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMeThemeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_me_theme, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeThemeBinding bind(View view) {
        int i = R.id.btn_ming_xiang;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.btn_xiao_qi;
            WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView2 != null) {
                i = R.id.fl_me_theme;
                FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
                if (fragmentContainerView != null) {
                    return new ActivityMeThemeBinding((LinearLayoutCompat) view, wanosTextView, wanosTextView2, fragmentContainerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
