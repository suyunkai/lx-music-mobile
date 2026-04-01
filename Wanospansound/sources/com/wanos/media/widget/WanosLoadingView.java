package com.wanos.media.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.core.content.ContextCompat;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public class WanosLoadingView extends ProgressBar {
    public WanosLoadingView(Context context) {
        super(context);
    }

    public WanosLoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setIndeterminateDrawable(ContextCompat.getDrawable(context, R.drawable.custom_progress));
    }

    public WanosLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setIndeterminateDrawable(ContextCompat.getDrawable(context, R.drawable.custom_progress));
    }
}
