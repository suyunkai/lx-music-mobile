package com.wanos.media.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class WanosSeekBar extends AppCompatSeekBar {
    private int endNode;
    private int startNode;
    private int width;

    public WanosSeekBar(Context context) {
        super(context);
        this.startNode = 0;
        this.endNode = 0;
    }

    public WanosSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.startNode = 0;
        this.endNode = 0;
    }

    public WanosSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.startNode = 0;
        this.endNode = 0;
    }

    public int getStartNode() {
        return this.startNode;
    }

    public int getEndNode() {
        return this.endNode;
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        updateProgressDrawable(this.startNode, this.endNode);
    }

    public void updateProgressDrawable(int startNode, int endNode) {
        float max;
        float f;
        int max2;
        if (getWidth() <= 0) {
            this.startNode = startNode;
            this.endNode = endNode;
            return;
        }
        this.startNode = startNode;
        this.endNode = endNode;
        boolean z = (startNode == 0 && endNode == 0) ? false : true;
        this.width = getWidth();
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.seek_bar_node_width);
        int intrinsicWidth = getThumb().getIntrinsicWidth();
        if (!z) {
            dimensionPixelOffset = 0;
        }
        int max3 = (int) (this.width * ((startNode * 1.0f) / getMax()));
        int i = dimensionPixelOffset * 2;
        if (max3 > i) {
            max3 -= i;
        }
        float f2 = endNode;
        if (f2 > getMax() / 2.0f) {
            max = this.width * ((f2 * 1.0f) / getMax());
            f = intrinsicWidth;
        } else {
            max = this.width * ((f2 * 1.0f) / getMax());
            f = (intrinsicWidth - dimensionPixelOffset) / 2.0f;
        }
        int i2 = (int) (max - f);
        if (f2 > getMax() / 2.0f) {
            int i3 = this.width;
            max2 = (int) ((i3 - (i3 * ((f2 * 1.0f) / getMax()))) - (intrinsicWidth / 2.0f));
        } else {
            int i4 = this.width;
            max2 = (int) ((i4 - (i4 * ((f2 * 1.0f) / getMax()))) - intrinsicWidth);
        }
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) progressDrawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i5 = 0; i5 < numberOfLayers; i5++) {
                int id = layerDrawable.getId(i5);
                if (id == R.id.vip_node_start) {
                    layerDrawable.setLayerInsetLeft(i5, max3);
                    layerDrawable.setLayerWidth(i5, dimensionPixelOffset);
                } else if (id == R.id.vip_node_end) {
                    layerDrawable.setLayerInsetLeft(i5, i2);
                    layerDrawable.setLayerWidth(i5, dimensionPixelOffset);
                } else if (id == R.id.start_progress) {
                    layerDrawable.setLayerWidth(i5, max3);
                } else if (id == R.id.end_progress) {
                    if (endNode > 0) {
                        layerDrawable.setLayerWidth(i5, max2);
                    } else {
                        layerDrawable.setLayerWidth(i5, 0);
                    }
                }
            }
            setProgressDrawableTiled(layerDrawable);
            return;
        }
        setProgressDrawableTiled(progressDrawable);
    }
}
