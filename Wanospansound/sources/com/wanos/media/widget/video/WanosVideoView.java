package com.wanos.media.widget.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/* JADX INFO: loaded from: classes3.dex */
public class WanosVideoView extends VideoView {
    public WanosVideoView(Context context) {
        super(context);
    }

    public WanosVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WanosVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
    }
}
