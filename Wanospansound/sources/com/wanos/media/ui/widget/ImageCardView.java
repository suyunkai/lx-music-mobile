package com.wanos.media.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class ImageCardView extends AppCompatImageView {
    private SizeMode cardSizeMode;
    private int placeholder;

    public ImageCardView(Context context) {
        super(context);
        this.cardSizeMode = SizeMode.IMAGE_SIZE_MIDDLE;
        this.placeholder = -1;
    }

    public ImageCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.cardSizeMode = SizeMode.IMAGE_SIZE_MIDDLE;
        this.placeholder = -1;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        initAttrs(context, attributeSet);
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ImageCardView, 0, 0);
        try {
            int i = typedArrayObtainStyledAttributes.getInt(R.styleable.ImageCardView_cardSize, 1);
            if (i == 0) {
                this.cardSizeMode = SizeMode.IMAGE_SIZE_MAX;
            } else if (i == 2) {
                this.cardSizeMode = SizeMode.IMAGE_SIZE_MIN;
            } else {
                this.cardSizeMode = SizeMode.IMAGE_SIZE_MIDDLE;
            }
            this.placeholder = typedArrayObtainStyledAttributes.getResourceId(R.styleable.ImageCardView_placeHolder, -1);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void setImage(String str) {
        int i = this.placeholder;
        if (i != -1) {
            GlideUtil.setImageData(this.cardSizeMode, str, this, i);
        } else {
            GlideUtil.setImageData(this.cardSizeMode, str, this);
        }
    }

    public void setImage(String str, int i) {
        GlideUtil.setImageData(this.cardSizeMode, str, this, i);
    }
}
