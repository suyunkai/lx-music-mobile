package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.WidgetAiButtonViewBinding;

/* JADX INFO: loaded from: classes3.dex */
public class AiButtonView extends LinearLayoutCompat {
    public AiButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        WidgetAiButtonViewBinding widgetAiButtonViewBindingInflate = WidgetAiButtonViewBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AiButtonView);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.AiButtonView_ai_button_msg);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.AiButtonView_ai_button_icon, -1);
        typedArrayObtainStyledAttributes.recycle();
        if (!StringUtils.isEmpty(string)) {
            widgetAiButtonViewBindingInflate.tvButtonText.setText(string);
        }
        if (resourceId != -1) {
            widgetAiButtonViewBindingInflate.ivButtonIcon.setImageResource(resourceId);
        }
    }
}
