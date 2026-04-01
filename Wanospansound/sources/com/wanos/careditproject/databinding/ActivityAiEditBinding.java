package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.widget.AiFlowView;
import com.wanos.careditproject.view.widget.CustomAiEditView;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityAiEditBinding implements ViewBinding {
    public final CustomAiEditView aiEditView;
    public final AppCompatImageView btnBack;
    public final AiFlowView lableView;
    private final ConstraintLayout rootView;
    public final WanosTextView tvAiTitle;

    private ActivityAiEditBinding(ConstraintLayout constraintLayout, CustomAiEditView customAiEditView, AppCompatImageView appCompatImageView, AiFlowView aiFlowView, WanosTextView wanosTextView) {
        this.rootView = constraintLayout;
        this.aiEditView = customAiEditView;
        this.btnBack = appCompatImageView;
        this.lableView = aiFlowView;
        this.tvAiTitle = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiEditBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityAiEditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_ai_edit, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiEditBinding bind(View view) {
        int i = R.id.ai_edit_view;
        CustomAiEditView customAiEditView = (CustomAiEditView) ViewBindings.findChildViewById(view, i);
        if (customAiEditView != null) {
            i = R.id.btn_back;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView != null) {
                i = R.id.lable_view;
                AiFlowView aiFlowView = (AiFlowView) ViewBindings.findChildViewById(view, i);
                if (aiFlowView != null) {
                    i = R.id.tv_ai_title;
                    WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                    if (wanosTextView != null) {
                        return new ActivityAiEditBinding((ConstraintLayout) view, customAiEditView, appCompatImageView, aiFlowView, wanosTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
