package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.wanos.careditproject.databinding.WidgetAiStateTextViewBinding;

/* JADX INFO: loaded from: classes3.dex */
public class AiStateTextView extends LinearLayoutCompat {
    private final WidgetAiStateTextViewBinding binding;

    public AiStateTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.binding = WidgetAiStateTextViewBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void setHintMsg(String str) {
        this.binding.tvAiMsg.setText(str);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            this.binding.lottieState.playAnimation();
        } else {
            this.binding.lottieState.pauseAnimation();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.binding.lottieState.clearAnimation();
    }
}
