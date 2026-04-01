package com.wanos;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialHeaderView extends FrameLayout implements MaterialHeadListener {
    private static final String Tag = "MaterialHeaderView";
    private static float density;
    private CircleProgressBar circleProgressBar;
    private boolean isShowArrow;
    private boolean isShowProgressBg;
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

    public MaterialHeaderView(Context context) {
        this(context, null);
    }

    public MaterialHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialHeaderView(Context context, AttributeSet attributeSet, int i) {
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
        float f = density;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(((int) f) * i, ((int) f) * i);
        layoutParams.gravity = 17;
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.setLayoutParams(layoutParams);
        }
    }

    public void setProgressBg(int i) {
        this.progressBg = i;
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.setProgressBackGroundColor(i);
        }
    }

    public void setIsProgressBg(boolean z) {
        this.isShowProgressBg = z;
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.setCircleBackgroundEnabled(z);
        }
    }

    public void setProgressTextColor(int i) {
        this.progressTextColor = i;
    }

    public void setProgressColors(int[] iArr) {
        this.progress_colors = iArr;
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.setColorSchemeColors(iArr);
        }
    }

    public void setTextType(int i) {
        this.textType = i;
    }

    public void setProgressValue(int i) {
        this.progressValue = i;
        post(new Runnable() { // from class: com.wanos.MaterialHeaderView.1
            @Override // java.lang.Runnable
            public void run() {
                if (MaterialHeaderView.this.circleProgressBar != null) {
                    MaterialHeaderView.this.circleProgressBar.setProgress(MaterialHeaderView.this.progressValue);
                }
            }
        });
    }

    public void setProgressValueMax(int i) {
        this.progressValueMax = i;
    }

    public void setProgressStokeWidth(int i) {
        this.progressStokeWidth = i;
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.setProgressStokeWidth(i);
        }
    }

    public void showProgressArrow(boolean z) {
        this.isShowArrow = z;
        CircleProgressBar circleProgressBar = this.circleProgressBar;
        if (circleProgressBar != null) {
            circleProgressBar.setShowArrow(z);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        density = getContext().getResources().getDisplayMetrics().density;
        if (this.circleProgressBar == null) {
            this.circleProgressBar = new CircleProgressBar(getContext());
            float f = density;
            int i = this.progressSize;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(((int) f) * i, ((int) f) * i);
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
        if (this.materialWaveView == null) {
            MaterialWaveView materialWaveView = new MaterialWaveView(getContext());
            this.materialWaveView = materialWaveView;
            materialWaveView.setColor(this.waveColor);
            addView(this.materialWaveView);
        }
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
            ViewCompat.setScaleX(circleProgressBar, 0.001f);
            ViewCompat.setScaleY(this.circleProgressBar, 0.001f);
            this.circleProgressBar.onBegin(materialRefreshLayout);
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
            ViewCompat.setScaleX(this.circleProgressBar, fLimitValue);
            ViewCompat.setScaleY(this.circleProgressBar, fLimitValue);
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
