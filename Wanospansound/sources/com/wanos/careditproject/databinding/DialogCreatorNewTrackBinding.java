package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogCreatorNewTrackBinding implements ViewBinding {
    public final ImageView btnClose;
    public final LinearLayout btnRecord;
    public final LinearLayout btnRes;
    public final ConstraintLayout dialogView;
    public final FrameLayout dialogWin;
    private final FrameLayout rootView;
    public final TextView tvTitleSpeed;

    private DialogCreatorNewTrackBinding(FrameLayout frameLayout, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, ConstraintLayout constraintLayout, FrameLayout frameLayout2, TextView textView) {
        this.rootView = frameLayout;
        this.btnClose = imageView;
        this.btnRecord = linearLayout;
        this.btnRes = linearLayout2;
        this.dialogView = constraintLayout;
        this.dialogWin = frameLayout2;
        this.tvTitleSpeed = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogCreatorNewTrackBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCreatorNewTrackBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_creator_new_track, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCreatorNewTrackBinding bind(View view) {
        int i = R.id.btn_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_record;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.btn_res;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout2 != null) {
                    i = R.id.dialog_view;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                    if (constraintLayout != null) {
                        FrameLayout frameLayout = (FrameLayout) view;
                        i = R.id.tv_title_speed;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            return new DialogCreatorNewTrackBinding(frameLayout, imageView, linearLayout, linearLayout2, constraintLayout, frameLayout, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
