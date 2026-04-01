package com.wanos.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.CustomDialogTitleBinding;

/* JADX INFO: loaded from: classes3.dex */
public class CustomDialogTitle extends FrameLayout {
    private final CustomDialogTitleBinding binding;

    public CustomDialogTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        CustomDialogTitleBinding customDialogTitleBindingInflate = CustomDialogTitleBinding.inflate(LayoutInflater.from(context), this, true);
        this.binding = customDialogTitleBindingInflate;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomDialogTitle);
        customDialogTitleBindingInflate.ivTitle.setText(typedArrayObtainStyledAttributes.getString(R.styleable.CustomDialogTitle_dialog_title));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.binding.btnBack.setOnClickListener(onClickListener);
    }

    public void setText(CharSequence charSequence) {
        this.binding.ivTitle.setText(charSequence);
    }
}
