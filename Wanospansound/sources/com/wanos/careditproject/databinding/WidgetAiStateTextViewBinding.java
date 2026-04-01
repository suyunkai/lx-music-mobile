package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetAiStateTextViewBinding implements ViewBinding {
    public final LottieAnimationView lottieState;
    private final LinearLayoutCompat rootView;
    public final WanosTextView tvAiMsg;

    private WidgetAiStateTextViewBinding(LinearLayoutCompat linearLayoutCompat, LottieAnimationView lottieAnimationView, WanosTextView wanosTextView) {
        this.rootView = linearLayoutCompat;
        this.lottieState = lottieAnimationView;
        this.tvAiMsg = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static WidgetAiStateTextViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static WidgetAiStateTextViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.widget_ai_state_text_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WidgetAiStateTextViewBinding bind(View view) {
        int i = R.id.lottie_state;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
        if (lottieAnimationView != null) {
            i = R.id.tv_ai_msg;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                return new WidgetAiStateTextViewBinding((LinearLayoutCompat) view, lottieAnimationView, wanosTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
