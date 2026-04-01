package com.wanos;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialHeadView extends FrameLayout implements MaterialHeadListener {
    private CircleProgressBar circleProgressBar;
    private boolean isShowArrow;
    private boolean isShowProgressBg;
    private MaterialHeadListener listener;
    private MaterialWaveView materialWaveView;
    private int progressBg;
    private int progressSize;
    private int progressStokeWidth;
    private int progressTextColor;
    private int progressValue;
    private int progressValueMax;
    private int[] progress_colors;
    private int textType;
    private int waveColor;

    @Override // com.wanos.MaterialHeadListener
    public void onRelease(MaterialRefreshLayout materialRefreshLayout, float f) {
    }

    public MaterialHeadView(Context context) {
        this(context, null);
    }

    public MaterialHeadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialHeadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    protected void init(AttributeSet attributeSet, int i) {
        if (isInEditMode()) {
            return;
        }
        setClipToPadding(false);
        setWillNotDraw(false);
    }

    public int getWaveColor() {
        return this.waveColor;
    }

    public void setWaveColor(int i) {
        this.waveColor = i;
        MaterialWaveView materialWaveView = this.materialWaveView;
        if (materialWaveView != null) {
            materialWaveView.setColor(i);
        }
    }

    public void setProgressSize(int i) {
        this.progressSize = i;
    }

    public void setProgressBg(int i) {
        this.progressBg = i;
    }

    public void setIsProgressBg(boolean z) {
        this.isShowProgressBg = z;
    }

    public void setProgressTextColor(int i) {
        this.progressTextColor = i;
    }

    public void setProgressColors(int[] iArr) {
        this.progress_colors = iArr;
    }

    public void setTextType(int i) {
        this.textType = i;
    }

    public void setProgressValue(int i) {
        this.progressValue = i;
        post(new Runnable() { // from class: com.wanos.MaterialHeadView.1
            @Override // java.lang.Runnable
            public void run() {
                if (MaterialHeadView.this.circleProgressBar != null) {
                    MaterialHeadView.this.circleProgressBar.setProgress(MaterialHeadView.this.progressValue);
                }
            }
        });
    }

    public void setProgressValueMax(int i) {
        this.progressValueMax = i;
    }

    public void setProgressStokeWidth(int i) {
        this.progressStokeWidth = i;
    }

    public void showProgressArrow(boolean z) {
        this.isShowArrow = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialWaveView materialWaveView = new MaterialWaveView(getContext());
        this.materialWaveView = materialWaveView;
        materialWaveView.setColor(this.waveColor);
        addView(this.materialWaveView);
        this.circleProgressBar = new CircleProgressBar(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(Util.dip2px(getContext(), this.progressSize), Util.dip2px(getContext(), this.progressSize));
        layoutParams.gravity = 17;
        this.circleProgressBar.setLayoutParams(layoutParams);
        this.circleProgressBar.setColorSchemeColors(this.progress_colors);
        this.circleProgressBar.setProgressStokeWidth(this.progressStokeWidth);
        this.circleProgressBar.setShowArrow(this.isShowArrow);
        this.circleProgressBar.setShowProgressText(this.textType == 0);
        this.circleProgressBar.setTextColor(this.progressTextColor);
        this.circleProgressBar.setProgress(this.progressValue);
        this.circleProgressBar.setMax(this.progressValueMax);
        this.circleProgressBar.setCircleBackgroundEnabled(this.isShowProgressBg);
        this.circleProgressBar.setProgressBackGroundColor(this.progressBg);
        addView(this.circleProgressBar);
    }

    @Override // com.wanos.MaterialHeadListener
    public void onComlete(MaterialRefreshLayout materialRefreshLayout) {
        MaterialWaveView materialWaveView = this.materialWaveView;
        if (materialWaveView != null) {
            materialWaveView.onComlete(materialRefreshLayout);
        }
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.onComlete(materialRefreshLayout);
            ViewCompat.setTranslationY(this.circleProgressBar, 0.0f);
            ViewCompat.setScaleX(this.circleProgressBar, 0.0f);
            ViewCompat.setScaleY(this.circleProgressBar, 0.0f);
        }
    }

    @Override // com.wanos.MaterialHeadListener
    public void onBegin(MaterialRefreshLayout materialRefreshLayout) {
        MaterialWaveView materialWaveView = this.materialWaveView;
        if (materialWaveView != null) {
            materialWaveView.onBegin(materialRefreshLayout);
        }
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.onBegin(materialRefreshLayout);
        }
    }

    @Override // com.wanos.MaterialHeadListener
    public void onPull(MaterialRefreshLayout materialRefreshLayout, float f) {
        MaterialWaveView materialWaveView = this.materialWaveView;
        if (materialWaveView != null) {
            materialWaveView.onPull(materialRefreshLayout, f);
        }
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.onPull(materialRefreshLayout, f);
            float fLimitValue = Util.limitValue(1.0f, f);
            ViewCompat.setScaleX(this.circleProgressBar, 1.0f);
            ViewCompat.setScaleY(this.circleProgressBar, 1.0f);
            ViewCompat.setAlpha(this.circleProgressBar, fLimitValue);
        }
    }

    @Override // com.wanos.MaterialHeadListener
    public void onRefreshing(MaterialRefreshLayout materialRefreshLayout) {
        MaterialWaveView materialWaveView = this.materialWaveView;
        if (materialWaveView != null) {
            materialWaveView.onRefreshing(materialRefreshLayout);
        }
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.onRefreshing(materialRefreshLayout);
        }
    }
}
