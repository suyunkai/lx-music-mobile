package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetAiEditViewBinding implements ViewBinding {
    public final AppCompatImageView btnCreate;
    public final WanosTextView btnEmotionType;
    public final LinearLayoutCompat btnRandomContent;
    public final WanosTextView btnStyleType;
    public final AppCompatEditText etInput;
    public final LinearLayoutCompat llSelect;
    public final LottieAnimationView lottieAnim;
    private final FrameLayout rootView;
    public final WanosTextView tvInputLength;
    public final WanosTextView tvSelectMode1;
    public final WanosTextView tvSelectMode2;
    public final WanosTextView tvSelectMode3;

    private WidgetAiEditViewBinding(FrameLayout frameLayout, AppCompatImageView appCompatImageView, WanosTextView wanosTextView, LinearLayoutCompat linearLayoutCompat, WanosTextView wanosTextView2, AppCompatEditText appCompatEditText, LinearLayoutCompat linearLayoutCompat2, LottieAnimationView lottieAnimationView, WanosTextView wanosTextView3, WanosTextView wanosTextView4, WanosTextView wanosTextView5, WanosTextView wanosTextView6) {
        this.rootView = frameLayout;
        this.btnCreate = appCompatImageView;
        this.btnEmotionType = wanosTextView;
        this.btnRandomContent = linearLayoutCompat;
        this.btnStyleType = wanosTextView2;
        this.etInput = appCompatEditText;
        this.llSelect = linearLayoutCompat2;
        this.lottieAnim = lottieAnimationView;
        this.tvInputLength = wanosTextView3;
        this.tvSelectMode1 = wanosTextView4;
        this.tvSelectMode2 = wanosTextView5;
        this.tvSelectMode3 = wanosTextView6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static WidgetAiEditViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static WidgetAiEditViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.widget_ai_edit_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WidgetAiEditViewBinding bind(View view) {
        int i = R.id.btn_create;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.btn_emotion_type;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                i = R.id.btn_random_content;
                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                if (linearLayoutCompat != null) {
                    i = R.id.btn_style_type;
                    WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                    if (wanosTextView2 != null) {
                        i = R.id.et_input;
                        AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
                        if (appCompatEditText != null) {
                            i = R.id.ll_select;
                            LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                            if (linearLayoutCompat2 != null) {
                                i = R.id.lottie_anim;
                                LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                                if (lottieAnimationView != null) {
                                    i = R.id.tv_input_length;
                                    WanosTextView wanosTextView3 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                    if (wanosTextView3 != null) {
                                        i = R.id.tv_select_mode_1;
                                        WanosTextView wanosTextView4 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                        if (wanosTextView4 != null) {
                                            i = R.id.tv_select_mode_2;
                                            WanosTextView wanosTextView5 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                            if (wanosTextView5 != null) {
                                                i = R.id.tv_select_mode_3;
                                                WanosTextView wanosTextView6 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                                if (wanosTextView6 != null) {
                                                    return new WidgetAiEditViewBinding((FrameLayout) view, appCompatImageView, wanosTextView, linearLayoutCompat, wanosTextView2, appCompatEditText, linearLayoutCompat2, lottieAnimationView, wanosTextView3, wanosTextView4, wanosTextView5, wanosTextView6);
                                                }
                                            }
                                        }
                                    }
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
