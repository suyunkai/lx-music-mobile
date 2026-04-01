package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogCreatorSettingV2Binding implements ViewBinding {
    public final ImageView btnClose;
    public final ConstraintLayout dialogView;
    public final FrameLayout dialogWin;
    private final FrameLayout rootView;
    public final TextView tvInputDevices;
    public final TextView tvTitleSpeed;

    private DialogCreatorSettingV2Binding(FrameLayout frameLayout, ImageView imageView, ConstraintLayout constraintLayout, FrameLayout frameLayout2, TextView textView, TextView textView2) {
        this.rootView = frameLayout;
        this.btnClose = imageView;
        this.dialogView = constraintLayout;
        this.dialogWin = frameLayout2;
        this.tvInputDevices = textView;
        this.tvTitleSpeed = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogCreatorSettingV2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCreatorSettingV2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_creator_setting_v2, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCreatorSettingV2Binding bind(View view) {
        int i = R.id.btn_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.dialog_view;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
            if (constraintLayout != null) {
                FrameLayout frameLayout = (FrameLayout) view;
                i = R.id.tv_input_devices;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.tv_title_speed;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        return new DialogCreatorSettingV2Binding(frameLayout, imageView, constraintLayout, frameLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
