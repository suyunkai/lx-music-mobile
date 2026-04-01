package com.wanos;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialRefreshLayout extends FrameLayout {
    private static final int BIG_PROGRESS_SIZE = 60;
    private static final int DEFAULT_HEAD_HEIGHT = 70;
    private static final int DEFAULT_PROGRESS_SIZE = 50;
    private static final int DEFAULT_WAVE_HEIGHT = 140;
    private static final int HIGHER_WAVE_HEIGHT = 180;
    private static final int PROGRESS_STOKE_WIDTH = 3;
    public static final String Tag = "MaterialRefreshLayout";
    private static final int hIGHER_HEAD_HEIGHT = 100;
    protected boolean canDrag2Refresh;
    private int[] colorSchemeColors;
    private int colorsId;
    private DecelerateInterpolator decelerateInterpolator;
    private float headHeight;
    protected boolean isLoadMore;
    protected boolean isLoadMoreing;
    private boolean isMaterialStyle;
    private boolean isOverlay;
    protected boolean isRefreshing;
    private boolean isShowWave;
    private boolean isSunStyle;
    protected View mChildView;
    protected float mCurrentY;
    protected float mHeadHeight;
    protected MaterialFooterView mMaterialFooterView;
    protected MaterialHeaderView mMaterialHeaderView;
    protected SunLayout mSunLayout;
    protected float mTouchY;
    protected float mWaveHeight;
    private int progressBg;
    private int progressMax;
    private int progressSize;
    private int progressSizeType;
    private int progressTextColor;
    private int progressValue;
    protected MaterialRefreshListener refreshListener;
    private boolean showArrow;
    private boolean showProgressBg;
    private int textType;
    private int waveColor;
    private float waveHeight;
    private int waveType;

    public MaterialRefreshLayout(Context context) {
        this(context, null, 0);
    }

    public MaterialRefreshLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.showArrow = true;
        this.progressSize = 0;
        this.canDrag2Refresh = true;
        this.isSunStyle = false;
        this.isMaterialStyle = true;
        init(context, attributeSet, i);
    }

    protected void init(Context context, AttributeSet attributeSet, int i) {
        if (isInEditMode()) {
            return;
        }
        if (getChildCount() > 1) {
            throw new RuntimeException("can only have one child widget");
        }
        this.decelerateInterpolator = new DecelerateInterpolator(10.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialRefreshLayout, i, 0);
        this.isOverlay = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MaterialRefreshLayout_overlay, false);
        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.MaterialRefreshLayout_wave_height_type, 0);
        this.waveType = i2;
        if (i2 == 0) {
            this.headHeight = 70.0f;
            this.waveHeight = 140.0f;
            MaterialWaveView.DefaulHeadHeight = 70;
            MaterialWaveView.DefaulWaveHeight = DEFAULT_WAVE_HEIGHT;
        } else {
            this.headHeight = 100.0f;
            this.waveHeight = 180.0f;
            MaterialWaveView.DefaulHeadHeight = 100;
            MaterialWaveView.DefaulWaveHeight = HIGHER_WAVE_HEIGHT;
        }
        this.waveColor = typedArrayObtainStyledAttributes.getColor(R.styleable.MaterialRefreshLayout_wave_color, -1);
        this.isShowWave = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MaterialRefreshLayout_wave_show, true);
        this.colorsId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.MaterialRefreshLayout_progress_colors, R.array.material_colors);
        this.colorSchemeColors = context.getResources().getIntArray(this.colorsId);
        this.showArrow = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MaterialRefreshLayout_progress_show_arrow, true);
        this.textType = typedArrayObtainStyledAttributes.getInt(R.styleable.MaterialRefreshLayout_progress_text_visibility, 1);
        this.progressTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.MaterialRefreshLayout_progress_text_color, ViewCompat.MEASURED_STATE_MASK);
        this.progressValue = typedArrayObtainStyledAttributes.getInteger(R.styleable.MaterialRefreshLayout_progress_value, 0);
        this.progressMax = typedArrayObtainStyledAttributes.getInteger(R.styleable.MaterialRefreshLayout_progress_max_value, 100);
        this.showProgressBg = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MaterialRefreshLayout_progress_show_circle_backgroud, true);
        this.progressBg = typedArrayObtainStyledAttributes.getColor(R.styleable.MaterialRefreshLayout_progress_backgroud_color, CircleProgressBar.DEFAULT_CIRCLE_BG_LIGHT);
        int i3 = typedArrayObtainStyledAttributes.getInt(R.styleable.MaterialRefreshLayout_progress_size_type, 0);
        this.progressSizeType = i3;
        if (i3 == 0) {
            this.progressSize = 50;
        } else {
            this.progressSize = 60;
        }
        this.isLoadMore = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MaterialRefreshLayout_isLoadMore, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(Tag, "onAttachedToWindow");
        Context context = getContext();
        View childAt = getChildAt(0);
        this.mChildView = childAt;
        if (childAt == null) {
            return;
        }
        setWaveHeight(Util.dip2px(context, this.waveHeight));
        setHeaderHeight(Util.dip2px(context, this.headHeight));
        if (this.isSunStyle) {
            if (this.mSunLayout == null) {
                this.mSunLayout = new SunLayout(context);
                new FrameLayout.LayoutParams(-1, Util.dip2px(context, 100.0f)).gravity = 48;
                this.mSunLayout.setVisibility(8);
                setHeaderView(this.mSunLayout);
            }
        } else if (this.isMaterialStyle && this.mMaterialHeaderView == null) {
            this.mMaterialHeaderView = new MaterialHeaderView(context);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, Util.dip2px(context, 100.0f));
            layoutParams.gravity = 48;
            this.mMaterialHeaderView.setLayoutParams(layoutParams);
            this.mMaterialHeaderView.setWaveColor(this.isShowWave ? this.waveColor : 0);
            this.mMaterialHeaderView.showProgressArrow(this.showArrow);
            this.mMaterialHeaderView.setProgressSize(this.progressSize);
            this.mMaterialHeaderView.setProgressColors(this.colorSchemeColors);
            this.mMaterialHeaderView.setProgressStokeWidth(3);
            this.mMaterialHeaderView.setTextType(this.textType);
            this.mMaterialHeaderView.setProgressTextColor(this.progressTextColor);
            this.mMaterialHeaderView.setProgressValue(this.progressValue);
            this.mMaterialHeaderView.setProgressValueMax(this.progressMax);
            this.mMaterialHeaderView.setIsProgressBg(this.showProgressBg);
            this.mMaterialHeaderView.setProgressBg(this.progressBg);
            this.mMaterialHeaderView.setVisibility(8);
            setHeaderView(this.mMaterialHeaderView);
        }
        if (this.mMaterialFooterView == null) {
            this.mMaterialFooterView = new MaterialFooterView(context);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, Util.dip2px(context, 100.0f));
            layoutParams2.gravity = 80;
            this.mMaterialFooterView.setLayoutParams(layoutParams2);
            this.mMaterialFooterView.showProgressArrow(this.showArrow);
            this.mMaterialFooterView.setProgressSize(this.progressSize);
            this.mMaterialFooterView.setProgressColors(this.colorSchemeColors);
            this.mMaterialFooterView.setProgressStokeWidth(3);
            this.mMaterialFooterView.setTextType(this.textType);
            this.mMaterialFooterView.setProgressValue(this.progressValue);
            this.mMaterialFooterView.setProgressValueMax(this.progressMax);
            this.mMaterialFooterView.setIsProgressBg(this.showProgressBg);
            this.mMaterialFooterView.setProgressBg(this.progressBg);
            this.mMaterialFooterView.setVisibility(8);
            setFooderView(this.mMaterialFooterView);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isRefreshing) {
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            float y = motionEvent.getY();
            this.mTouchY = y;
            this.mCurrentY = y;
        } else if (action == 2) {
            float y2 = motionEvent.getY() - this.mTouchY;
            if (y2 > 0.0f && !canChildScrollUp() && this.canDrag2Refresh) {
                MaterialHeaderView materialHeaderView = this.mMaterialHeaderView;
                if (materialHeaderView != null) {
                    materialHeaderView.setVisibility(0);
                    this.mMaterialHeaderView.onBegin(this);
                } else {
                    SunLayout sunLayout = this.mSunLayout;
                    if (sunLayout != null) {
                        sunLayout.setVisibility(0);
                        this.mSunLayout.onBegin(this);
                    }
                }
                return true;
            }
            if (y2 < 0.0f && !canChildScrollDown() && this.isLoadMore) {
                if (this.mMaterialFooterView != null && !this.isLoadMoreing) {
                    soveLoadMoreLogic();
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    protected void soveLoadMoreLogic() {
        this.isLoadMoreing = true;
        this.mMaterialFooterView.setVisibility(0);
        this.mMaterialFooterView.onBegin(this);
        this.mMaterialFooterView.onRefreshing(this);
        MaterialRefreshListener materialRefreshListener = this.refreshListener;
        if (materialRefreshListener != null) {
            materialRefreshListener.onRefreshLoadMore(this);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isRefreshing) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                float y = motionEvent.getY();
                this.mCurrentY = y;
                float fMax = Math.max(0.0f, Math.min(this.mWaveHeight * 2.0f, y - this.mTouchY));
                if (this.mChildView != null) {
                    float interpolation = (this.decelerateInterpolator.getInterpolation((fMax / this.mWaveHeight) / 2.0f) * fMax) / 2.0f;
                    float f = interpolation / this.mHeadHeight;
                    MaterialHeaderView materialHeaderView = this.mMaterialHeaderView;
                    if (materialHeaderView != null) {
                        materialHeaderView.getLayoutParams().height = (int) interpolation;
                        this.mMaterialHeaderView.requestLayout();
                        this.mMaterialHeaderView.onPull(this, f);
                    } else {
                        SunLayout sunLayout = this.mSunLayout;
                        if (sunLayout != null) {
                            sunLayout.getLayoutParams().height = (int) interpolation;
                            this.mSunLayout.requestLayout();
                            this.mSunLayout.onPull(this, f);
                        }
                    }
                    if (!this.isOverlay) {
                        ViewCompat.setTranslationY(this.mChildView, interpolation);
                    }
                }
                return true;
            }
            if (action != 3) {
                return super.onTouchEvent(motionEvent);
            }
        }
        View view = this.mChildView;
        if (view != null) {
            if (this.mMaterialHeaderView != null) {
                if (!this.isOverlay) {
                    float translationY = ViewCompat.getTranslationY(view);
                    float f2 = this.mHeadHeight;
                    if (translationY >= f2) {
                        createAnimatorTranslationY(this.mChildView, f2, this.mMaterialHeaderView);
                        updateListener();
                    } else {
                        createAnimatorTranslationY(this.mChildView, 0.0f, this.mMaterialHeaderView);
                    }
                } else if (r0.getLayoutParams().height > this.mHeadHeight) {
                    updateListener();
                    this.mMaterialHeaderView.getLayoutParams().height = (int) this.mHeadHeight;
                    this.mMaterialHeaderView.requestLayout();
                } else {
                    this.mMaterialHeaderView.getLayoutParams().height = 0;
                    this.mMaterialHeaderView.requestLayout();
                }
            } else {
                if (this.mSunLayout != null) {
                    if (!this.isOverlay) {
                        float translationY2 = ViewCompat.getTranslationY(view);
                        float f3 = this.mHeadHeight;
                        if (translationY2 >= f3) {
                            createAnimatorTranslationY(this.mChildView, f3, this.mSunLayout);
                            updateListener();
                        } else {
                            createAnimatorTranslationY(this.mChildView, 0.0f, this.mSunLayout);
                        }
                    } else if (r0.getLayoutParams().height > this.mHeadHeight) {
                        updateListener();
                        this.mSunLayout.getLayoutParams().height = (int) this.mHeadHeight;
                        this.mSunLayout.requestLayout();
                    } else {
                        this.mSunLayout.getLayoutParams().height = 0;
                        this.mSunLayout.requestLayout();
                    }
                }
            }
        }
        return true;
    }

    public void setSunStyle(boolean z) {
        this.isSunStyle = z;
    }

    public void setMaterialStyle(boolean z) {
        MaterialHeaderView materialHeaderView;
        this.isMaterialStyle = z;
        if (z || (materialHeaderView = this.mMaterialHeaderView) == null || materialHeaderView.getParent() == null) {
            return;
        }
        removeView(this.mMaterialHeaderView);
    }

    public void autoRefresh() {
        postDelayed(new Runnable() { // from class: com.wanos.MaterialRefreshLayout.1
            @Override // java.lang.Runnable
            public void run() {
                if (MaterialRefreshLayout.this.isRefreshing) {
                    return;
                }
                if (MaterialRefreshLayout.this.mMaterialHeaderView != null) {
                    MaterialRefreshLayout.this.mMaterialHeaderView.setVisibility(0);
                    if (MaterialRefreshLayout.this.isOverlay) {
                        MaterialRefreshLayout.this.mMaterialHeaderView.getLayoutParams().height = (int) MaterialRefreshLayout.this.mHeadHeight;
                        MaterialRefreshLayout.this.mMaterialHeaderView.requestLayout();
                    } else {
                        MaterialRefreshLayout materialRefreshLayout = MaterialRefreshLayout.this;
                        materialRefreshLayout.createAnimatorTranslationY(materialRefreshLayout.mChildView, MaterialRefreshLayout.this.mHeadHeight, MaterialRefreshLayout.this.mMaterialHeaderView);
                    }
                } else if (MaterialRefreshLayout.this.mSunLayout != null) {
                    MaterialRefreshLayout.this.mSunLayout.setVisibility(0);
                    if (MaterialRefreshLayout.this.isOverlay) {
                        MaterialRefreshLayout.this.mSunLayout.getLayoutParams().height = (int) MaterialRefreshLayout.this.mHeadHeight;
                        MaterialRefreshLayout.this.mSunLayout.requestLayout();
                    } else {
                        MaterialRefreshLayout materialRefreshLayout2 = MaterialRefreshLayout.this;
                        materialRefreshLayout2.createAnimatorTranslationY(materialRefreshLayout2.mChildView, MaterialRefreshLayout.this.mHeadHeight, MaterialRefreshLayout.this.mSunLayout);
                    }
                }
                MaterialRefreshLayout.this.updateListener();
            }
        }, 50L);
    }

    public void autoRefreshLoadMore() {
        post(new Runnable() { // from class: com.wanos.MaterialRefreshLayout.2
            @Override // java.lang.Runnable
            public void run() {
                if (MaterialRefreshLayout.this.isLoadMore) {
                    MaterialRefreshLayout.this.soveLoadMoreLogic();
                    return;
                }
                throw new RuntimeException("you must setLoadMore ture");
            }
        });
    }

    public void updateListener() {
        this.isRefreshing = true;
        MaterialHeaderView materialHeaderView = this.mMaterialHeaderView;
        if (materialHeaderView != null) {
            materialHeaderView.onRefreshing(this);
        } else {
            SunLayout sunLayout = this.mSunLayout;
            if (sunLayout != null) {
                sunLayout.onRefreshing(this);
            }
        }
        MaterialRefreshListener materialRefreshListener = this.refreshListener;
        if (materialRefreshListener != null) {
            materialRefreshListener.onRefresh(this);
        }
    }

    public void setLoadMore(boolean z) {
        this.isLoadMore = z;
    }

    public void setCanDrag2Refresh(boolean z) {
        this.canDrag2Refresh = z;
    }

    public void setProgressColors(int[] iArr) {
        this.colorSchemeColors = iArr;
    }

    public void setShowArrow(boolean z) {
        this.showArrow = z;
    }

    public void setShowProgressBg(boolean z) {
        this.showProgressBg = z;
    }

    public void setWaveColor(int i) {
        this.waveColor = i;
    }

    public void setWaveShow(boolean z) {
        this.isShowWave = z;
    }

    public void setIsOverLay(boolean z) {
        this.isOverlay = z;
    }

    public void setProgressValue(int i) {
        this.progressValue = i;
        this.mMaterialHeaderView.setProgressValue(i);
    }

    public void createAnimatorTranslationY(final View view, float f, final FrameLayout frameLayout) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompatAnimate = ViewCompat.animate(view);
        viewPropertyAnimatorCompatAnimate.setDuration(250L);
        viewPropertyAnimatorCompatAnimate.setInterpolator(new DecelerateInterpolator());
        viewPropertyAnimatorCompatAnimate.translationY(f);
        viewPropertyAnimatorCompatAnimate.start();
        viewPropertyAnimatorCompatAnimate.setUpdateListener(new ViewPropertyAnimatorUpdateListener() { // from class: com.wanos.MaterialRefreshLayout.3
            @Override // androidx.core.view.ViewPropertyAnimatorUpdateListener
            public void onAnimationUpdate(View view2) {
                frameLayout.getLayoutParams().height = (int) ViewCompat.getTranslationY(view);
                frameLayout.requestLayout();
            }
        });
    }

    public boolean canChildScrollUp() {
        if (this.mChildView == null) {
            return false;
        }
        return ViewCompat.canScrollVertically(this.mChildView, -1);
    }

    public boolean canChildScrollDown() {
        if (this.mChildView == null) {
            return false;
        }
        return ViewCompat.canScrollVertically(this.mChildView, 1);
    }

    public void setWaveHigher() {
        this.headHeight = 100.0f;
        this.waveHeight = 180.0f;
        MaterialWaveView.DefaulHeadHeight = 100;
        MaterialWaveView.DefaulWaveHeight = HIGHER_WAVE_HEIGHT;
    }

    public void finishRefreshing() {
        View view = this.mChildView;
        if (view != null) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompatAnimate = ViewCompat.animate(view);
            viewPropertyAnimatorCompatAnimate.setDuration(200L);
            viewPropertyAnimatorCompatAnimate.y(ViewCompat.getTranslationY(this.mChildView));
            viewPropertyAnimatorCompatAnimate.translationY(0.0f);
            viewPropertyAnimatorCompatAnimate.setInterpolator(new DecelerateInterpolator());
            viewPropertyAnimatorCompatAnimate.start();
            MaterialHeaderView materialHeaderView = this.mMaterialHeaderView;
            if (materialHeaderView != null) {
                materialHeaderView.onComlete(this);
            } else {
                SunLayout sunLayout = this.mSunLayout;
                if (sunLayout != null) {
                    sunLayout.onComlete(this);
                }
            }
            MaterialRefreshListener materialRefreshListener = this.refreshListener;
            if (materialRefreshListener != null) {
                materialRefreshListener.onfinish();
            }
        }
        this.isRefreshing = false;
        this.progressValue = 0;
    }

    public void finishRefresh() {
        finishRefreshing();
    }

    public void finishRefreshLoadMore() {
        postDelayed(new Runnable() { // from class: com.wanos.MaterialRefreshLayout.4
            @Override // java.lang.Runnable
            public void run() {
                if (MaterialRefreshLayout.this.mMaterialFooterView == null || !MaterialRefreshLayout.this.isLoadMoreing) {
                    return;
                }
                MaterialRefreshLayout.this.isLoadMoreing = false;
                MaterialRefreshLayout.this.mMaterialFooterView.onComlete(MaterialRefreshLayout.this);
            }
        }, 300L);
    }

    private void setHeaderView(View view) {
        addView(view);
    }

    public void setHeader(View view) {
        setHeaderView(view);
    }

    public void setFooderView(View view) {
        addView(view);
    }

    public void setWaveHeight(float f) {
        this.mWaveHeight = f;
    }

    public void setHeaderHeight(float f) {
        this.mHeadHeight = f;
    }

    public void setMaterialRefreshListener(MaterialRefreshListener materialRefreshListener) {
        this.refreshListener = materialRefreshListener;
    }

    public boolean isRefreshing() {
        return this.isRefreshing;
    }

    public boolean isLoadMoreing() {
        return this.isLoadMoreing;
    }
}
