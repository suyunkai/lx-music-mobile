package com.wanos;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.animation.Animation;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes3.dex */
public class CircleProgressBar extends AppCompatImageView implements MaterialHeadListener {
    public static final int DEFAULT_CIRCLE_BG_LIGHT = -328966;
    private static final int DEFAULT_CIRCLE_DIAMETER = 40;
    public static final int DEFAULT_TEXT_SIZE = 9;
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final int STROKE_WIDTH_LARGE = 3;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private int mArrowHeight;
    private int mArrowWidth;
    private int mBackGroundColor;
    private ShapeDrawable mBgCircle;
    private boolean mCircleBackgroundEnabled;
    private int[] mColors;
    private int mDiameter;
    private boolean mIfDrawText;
    private int mInnerRadius;
    private Animation.AnimationListener mListener;
    private int mMax;
    private int mProgress;
    private int mProgressColor;
    public MaterialProgressDrawable mProgressDrawable;
    private int mProgressStokeWidth;
    private int mShadowRadius;
    private boolean mShowArrow;
    private int mTextColor;
    private Paint mTextPaint;
    private int mTextSize;

    private boolean elevationSupported() {
        return true;
    }

    @Override // com.wanos.MaterialHeadListener
    public void onRelease(MaterialRefreshLayout materialRefreshLayout, float f) {
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public final void setImageDrawable(Drawable drawable) {
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public final void setImageResource(int i) {
    }

    public CircleProgressBar(Context context) {
        super(context);
        this.mColors = new int[]{ViewCompat.MEASURED_STATE_MASK};
        init(context, null, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mColors = new int[]{ViewCompat.MEASURED_STATE_MASK};
        init(context, attributeSet, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mColors = new int[]{ViewCompat.MEASURED_STATE_MASK};
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleProgressBar, i, 0);
        float f = getContext().getResources().getDisplayMetrics().density;
        this.mBackGroundColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CircleProgressBar_mlpb_background_color, DEFAULT_CIRCLE_BG_LIGHT);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.CircleProgressBar_mlpb_progress_color, DEFAULT_CIRCLE_BG_LIGHT);
        this.mProgressColor = color;
        this.mColors = new int[]{color};
        this.mInnerRadius = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_inner_radius, -1);
        this.mProgressStokeWidth = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_progress_stoke_width, (int) (3.0f * f));
        this.mArrowWidth = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_arrow_width, -1);
        this.mArrowHeight = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_arrow_height, -1);
        this.mTextSize = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_progress_text_size, (int) (f * 9.0f));
        this.mTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CircleProgressBar_mlpb_progress_text_color, ViewCompat.MEASURED_STATE_MASK);
        this.mShowArrow = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CircleProgressBar_mlpb_show_arrow, false);
        this.mCircleBackgroundEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CircleProgressBar_mlpb_enable_circle_background, true);
        this.mProgress = typedArrayObtainStyledAttributes.getInt(R.styleable.CircleProgressBar_mlpb_progress, 0);
        this.mMax = typedArrayObtainStyledAttributes.getInt(R.styleable.CircleProgressBar_mlpb_max, 100);
        if (typedArrayObtainStyledAttributes.getInt(R.styleable.CircleProgressBar_mlpb_progress_text_visibility, 1) != 1) {
            this.mIfDrawText = true;
        }
        Paint paint = new Paint();
        this.mTextPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mTextPaint.setColor(this.mTextColor);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        typedArrayObtainStyledAttributes.recycle();
        MaterialProgressDrawable materialProgressDrawable = new MaterialProgressDrawable(getContext(), this);
        this.mProgressDrawable = materialProgressDrawable;
        materialProgressDrawable.setStartEndTrim(0.0f, 0.75f);
        super.setImageDrawable(this.mProgressDrawable);
    }

    public void setProgressBackGroundColor(int i) {
        this.mBackGroundColor = i;
        invalidate();
    }

    public void setTextColor(int i) {
        this.mTextColor = i;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (elevationSupported()) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth() + (this.mShadowRadius * 2), getMeasuredHeight() + (this.mShadowRadius * 2));
    }

    public int getProgressStokeWidth() {
        return this.mProgressStokeWidth;
    }

    public void setProgressStokeWidth(int i) {
        this.mProgressStokeWidth = (int) (i * getContext().getResources().getDisplayMetrics().density);
        invalidate();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        float f = getContext().getResources().getDisplayMetrics().density;
        int iMin = Math.min(getMeasuredWidth(), getMeasuredHeight());
        this.mDiameter = iMin;
        if (iMin <= 0) {
            this.mDiameter = ((int) f) * 40;
        }
        if (getBackground() == null && this.mCircleBackgroundEnabled) {
            int i5 = (int) (Y_OFFSET * f);
            int i6 = (int) (f * 0.0f);
            this.mShadowRadius = (int) (SHADOW_RADIUS * f);
            if (elevationSupported()) {
                this.mBgCircle = new ShapeDrawable(new OvalShape());
                ViewCompat.setElevation(this, f * 4.0f);
            } else {
                int i7 = this.mShadowRadius;
                ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShadow(i7, this.mDiameter - (i7 * 2)));
                this.mBgCircle = shapeDrawable;
                ViewCompat.setLayerType(this, 1, shapeDrawable.getPaint());
                this.mBgCircle.getPaint().setShadowLayer(this.mShadowRadius, i6, i5, KEY_SHADOW_COLOR);
                int i8 = this.mShadowRadius;
                setPadding(i8, i8, i8, i8);
            }
            this.mBgCircle.getPaint().setColor(this.mBackGroundColor);
            setBackgroundDrawable(this.mBgCircle);
        }
        this.mProgressDrawable.setBackgroundColor(this.mBackGroundColor);
        this.mProgressDrawable.setColorSchemeColors(this.mColors);
        MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
        int i9 = this.mDiameter;
        double d2 = i9;
        double d3 = i9;
        int i10 = this.mInnerRadius;
        double d4 = i10 <= 0 ? (i9 - (this.mProgressStokeWidth * 2)) / 4 : i10;
        int i11 = this.mProgressStokeWidth;
        double d5 = i11;
        int i12 = this.mArrowWidth;
        if (i12 < 0) {
            i12 = i11 * 4;
        }
        float f2 = i12;
        int i13 = this.mArrowHeight;
        materialProgressDrawable.setSizeParameters(d2, d3, d4, d5, f2, i13 < 0 ? i11 * 2 : i13);
        if (isShowArrow()) {
            this.mProgressDrawable.showArrowOnFirstStart(true);
            this.mProgressDrawable.setArrowScale(1.0f);
            this.mProgressDrawable.showArrow(true);
        }
        super.setImageDrawable(null);
        super.setImageDrawable(this.mProgressDrawable);
        this.mProgressDrawable.setAlpha(255);
        if (getVisibility() == 0) {
            this.mProgressDrawable.setStartEndTrim(0.0f, 0.8f);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mIfDrawText) {
            canvas.drawText(String.format("%s%%", Integer.valueOf(this.mProgress)), (getWidth() / 2) - ((r0.length() * this.mTextSize) / 4), (getHeight() / 2) + (this.mTextSize / 4), this.mTextPaint);
        }
    }

    public boolean isShowArrow() {
        return this.mShowArrow;
    }

    public void setShowArrow(boolean z) {
        this.mShowArrow = z;
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public final void setImageURI(Uri uri) {
        super.setImageURI(uri);
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.mListener = animationListener;
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    public void setColorSchemeResources(int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = resources.getColor(iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setColorSchemeColors(int... iArr) {
        this.mColors = iArr;
        MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
        if (materialProgressDrawable != null) {
            materialProgressDrawable.setColorSchemeColors(iArr);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(getResources().getColor(i));
        }
    }

    public boolean isShowProgressText() {
        return this.mIfDrawText;
    }

    public void setShowProgressText(boolean z) {
        this.mIfDrawText = z;
    }

    public int getMax() {
        return this.mMax;
    }

    public void setMax(int i) {
        this.mMax = i;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public void setProgress(int i) {
        if (getMax() > 0) {
            this.mProgress = i;
        }
        invalidate();
    }

    public boolean circleBackgroundEnabled() {
        return this.mCircleBackgroundEnabled;
    }

    public void setCircleBackgroundEnabled(boolean z) {
        this.mCircleBackgroundEnabled = z;
        invalidate();
    }

    @Override // android.view.View
    public int getVisibility() {
        return super.getVisibility();
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
        if (materialProgressDrawable != null) {
            materialProgressDrawable.setVisible(i == 0, false);
            if (i != 0) {
                this.mProgressDrawable.stop();
                return;
            }
            if (this.mProgressDrawable.isRunning()) {
                this.mProgressDrawable.stop();
            }
            this.mProgressDrawable.start();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
        if (materialProgressDrawable != null) {
            materialProgressDrawable.stop();
            this.mProgressDrawable.setVisible(getVisibility() == 0, false);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
        if (materialProgressDrawable != null) {
            materialProgressDrawable.stop();
            this.mProgressDrawable.setVisible(false, false);
        }
    }

    @Override // com.wanos.MaterialHeadListener
    public void onComlete(MaterialRefreshLayout materialRefreshLayout) {
        MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
        if (materialProgressDrawable != null) {
            materialProgressDrawable.stop();
        }
        setVisibility(4);
    }

    @Override // com.wanos.MaterialHeadListener
    public void onBegin(MaterialRefreshLayout materialRefreshLayout) {
        setVisibility(0);
        this.mProgressDrawable.setStartEndTrim(0.0f, 0.75f);
    }

    @Override // com.wanos.MaterialHeadListener
    public void onPull(MaterialRefreshLayout materialRefreshLayout, float f) {
        this.mProgressDrawable.setProgressRotation(f);
    }

    @Override // com.wanos.MaterialHeadListener
    public void onRefreshing(MaterialRefreshLayout materialRefreshLayout) {
        MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
        if (materialProgressDrawable != null) {
            materialProgressDrawable.start();
        }
    }

    private class OvalShadow extends OvalShape {
        private final int mCircleDiameter;
        private final RadialGradient mRadialGradient;
        private final Paint mShadowPaint;
        private final int mShadowRadius;

        public OvalShadow(int i, int i2) {
            Paint paint = new Paint();
            this.mShadowPaint = paint;
            this.mShadowRadius = i;
            this.mCircleDiameter = i2;
            RadialGradient radialGradient = new RadialGradient(i2 / 2, i2 / 2, i, new int[]{CircleProgressBar.FILL_SHADOW_COLOR, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.mRadialGradient = radialGradient;
            paint.setShader(radialGradient);
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            float width = CircleProgressBar.this.getWidth() / 2;
            float height = CircleProgressBar.this.getHeight() / 2;
            canvas.drawCircle(width, height, (this.mCircleDiameter / 2) + this.mShadowRadius, this.mShadowPaint);
            canvas.drawCircle(width, height, this.mCircleDiameter / 2, paint);
        }
    }
}
