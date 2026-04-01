package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityWanosPlayerBackBinding implements ViewBinding {
    public final ImageView editingBack;
    public final FragmentPrviewPageBinding pagePlayer;
    private final LinearLayout rootView;

    private ActivityWanosPlayerBackBinding(LinearLayout linearLayout, ImageView imageView, FragmentPrviewPageBinding fragmentPrviewPageBinding) {
        this.rootView = linearLayout;
        this.editingBack = imageView;
        this.pagePlayer = fragmentPrviewPageBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWanosPlayerBackBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityWanosPlayerBackBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_wanos_player_back, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWanosPlayerBackBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.editing_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.page_player))) != null) {
            return new ActivityWanosPlayerBackBinding((LinearLayout) view, imageView, FragmentPrviewPageBinding.bind(viewFindChildViewById));
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
