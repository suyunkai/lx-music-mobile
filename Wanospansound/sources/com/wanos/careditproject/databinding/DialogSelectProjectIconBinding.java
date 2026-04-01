package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogSelectProjectIconBinding implements ViewBinding {
    public final ImageView btnClose;
    public final TextView btnSure;
    public final ConstraintLayout dialogView;
    public final FrameLayout dialogWin;
    public final EmptyViewCBinding emptyView;
    private final FrameLayout rootView;
    public final RecyclerView rvIconList;
    public final TextView tvTitleSpeed;
    public final FrameLayout viewList;

    private DialogSelectProjectIconBinding(FrameLayout frameLayout, ImageView imageView, TextView textView, ConstraintLayout constraintLayout, FrameLayout frameLayout2, EmptyViewCBinding emptyViewCBinding, RecyclerView recyclerView, TextView textView2, FrameLayout frameLayout3) {
        this.rootView = frameLayout;
        this.btnClose = imageView;
        this.btnSure = textView;
        this.dialogView = constraintLayout;
        this.dialogWin = frameLayout2;
        this.emptyView = emptyViewCBinding;
        this.rvIconList = recyclerView;
        this.tvTitleSpeed = textView2;
        this.viewList = frameLayout3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogSelectProjectIconBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogSelectProjectIconBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_select_project_icon, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogSelectProjectIconBinding bind(View view) {
        int i = R.id.btn_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_sure;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.dialog_view;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                if (constraintLayout != null) {
                    FrameLayout frameLayout = (FrameLayout) view;
                    i = R.id.empty_view;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, i);
                    if (viewFindChildViewById != null) {
                        EmptyViewCBinding emptyViewCBindingBind = EmptyViewCBinding.bind(viewFindChildViewById);
                        i = R.id.rv_icon_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                        if (recyclerView != null) {
                            i = R.id.tv_title_speed;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                i = R.id.view_list;
                                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                if (frameLayout2 != null) {
                                    return new DialogSelectProjectIconBinding(frameLayout, imageView, textView, constraintLayout, frameLayout, emptyViewCBindingBind, recyclerView, textView2, frameLayout2);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
