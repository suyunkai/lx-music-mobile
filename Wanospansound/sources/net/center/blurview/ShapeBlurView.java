package net.center.blurview;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import net.center.blurview.impl.AndroidStockBlurImpl;
import net.center.blurview.impl.AndroidXBlurImpl;
import net.center.blurview.impl.BlurImpl;
import net.center.blurview.impl.EmptyBlurImpl;
import net.center.blurview.impl.SupportLibraryBlurImpl;

/* JADX INFO: loaded from: classes3.dex */
public class ShapeBlurView extends View {
    private static int BLUR_IMPL = 0;
    public static final int DEFAULT_BORDER_COLOR = -1;
    private static final float DEFAULT_BORDER_WIDTH = 0.0f;
    private static final float DEFAULT_RADIUS = 0.0f;
    private static int RENDERING_COUNT;
    private static StopException STOP_EXCEPTION = new StopException();
    private int blurMode;
    private float cRadius;
    private final Path cornerPath;
    private float[] cornerRids;
    private float cx;
    private float cy;
    private final Paint mBitmapPaint;
    private Bitmap mBitmapToBlur;
    private final BlurImpl mBlurImpl;
    private float mBlurRadius;
    private Bitmap mBlurredBitmap;
    private Canvas mBlurringCanvas;
    private ColorStateList mBorderColor;
    private final Paint mBorderPaint;
    private final RectF mBorderRect;
    private float mBorderWidth;
    private Context mContext;
    private final float[] mCornerRadii;
    private View mDecorView;
    private boolean mDifferentRoot;
    private boolean mDirty;
    private float mDownSampleFactor;
    private boolean mIsRendering;
    private int mOverlayColor;
    private final RectF mRectFDst;
    private final Rect mRectSrc;
    private Matrix matrix;
    private final ViewTreeObserver.OnPreDrawListener preDrawListener;
    private BitmapShader shader;

    static /* synthetic */ int access$608() {
        int i = RENDERING_COUNT;
        RENDERING_COUNT = i + 1;
        return i;
    }

    static /* synthetic */ int access$610() {
        int i = RENDERING_COUNT;
        RENDERING_COUNT = i - 1;
        return i;
    }

    public ShapeBlurView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRectSrc = new Rect();
        this.mRectFDst = new RectF();
        this.blurMode = 0;
        this.cx = 0.0f;
        this.cy = 0.0f;
        this.cRadius = 0.0f;
        float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f};
        this.mCornerRadii = fArr;
        this.cornerPath = new Path();
        this.mBorderRect = new RectF();
        this.mBorderWidth = 0.0f;
        this.mBorderColor = ColorStateList.valueOf(-1);
        this.matrix = new Matrix();
        this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: net.center.blurview.ShapeBlurView.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                int[] iArr = new int[2];
                Bitmap bitmap = ShapeBlurView.this.mBlurredBitmap;
                View view = ShapeBlurView.this.mDecorView;
                if (view != null && ShapeBlurView.this.isShown() && ShapeBlurView.this.prepare()) {
                    boolean z = ShapeBlurView.this.mBlurredBitmap != bitmap;
                    view.getLocationOnScreen(iArr);
                    int i = -iArr[0];
                    int i2 = -iArr[1];
                    ShapeBlurView.this.getLocationOnScreen(iArr);
                    int i3 = i + iArr[0];
                    int i4 = i2 + iArr[1];
                    ShapeBlurView.this.mBitmapToBlur.eraseColor(ShapeBlurView.this.mOverlayColor & ViewCompat.MEASURED_SIZE_MASK);
                    int iSave = ShapeBlurView.this.mBlurringCanvas.save();
                    ShapeBlurView.this.mIsRendering = true;
                    ShapeBlurView.access$608();
                    try {
                        ShapeBlurView.this.mBlurringCanvas.scale((ShapeBlurView.this.mBitmapToBlur.getWidth() * 1.0f) / ShapeBlurView.this.getWidth(), (ShapeBlurView.this.mBitmapToBlur.getHeight() * 1.0f) / ShapeBlurView.this.getHeight());
                        ShapeBlurView.this.mBlurringCanvas.translate(-i3, -i4);
                        if (view.getBackground() != null) {
                            view.getBackground().draw(ShapeBlurView.this.mBlurringCanvas);
                        }
                        view.draw(ShapeBlurView.this.mBlurringCanvas);
                    } catch (StopException unused) {
                    } catch (Throwable th) {
                        ShapeBlurView.this.mIsRendering = false;
                        ShapeBlurView.access$610();
                        ShapeBlurView.this.mBlurringCanvas.restoreToCount(iSave);
                        throw th;
                    }
                    ShapeBlurView.this.mIsRendering = false;
                    ShapeBlurView.access$610();
                    ShapeBlurView.this.mBlurringCanvas.restoreToCount(iSave);
                    ShapeBlurView shapeBlurView = ShapeBlurView.this;
                    shapeBlurView.blur(shapeBlurView.mBitmapToBlur, ShapeBlurView.this.mBlurredBitmap);
                    if (z || ShapeBlurView.this.mDifferentRoot) {
                        ShapeBlurView.this.invalidate();
                    }
                }
                return true;
            }
        };
        this.mContext = context;
        this.mBlurImpl = getBlurImpl();
        try {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeBlurView);
            this.mBlurRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.ShapeBlurView_blur_radius, TypedValue.applyDimension(1, 10.0f, context.getResources().getDisplayMetrics()));
            this.mDownSampleFactor = typedArrayObtainStyledAttributes.getFloat(R.styleable.ShapeBlurView_blur_down_sample, 4.0f);
            this.mOverlayColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ShapeBlurView_blur_overlay_color, 0);
            float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeBlurView_blur_corner_radius, -1);
            fArr[0] = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeBlurView_blur_corner_radius_top_left, -1);
            fArr[1] = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeBlurView_blur_corner_radius_top_right, -1);
            fArr[2] = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeBlurView_blur_corner_radius_bottom_right, -1);
            fArr[3] = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeBlurView_blur_corner_radius_bottom_left, -1);
            initCornerData(dimensionPixelSize);
            this.blurMode = typedArrayObtainStyledAttributes.getInt(R.styleable.ShapeBlurView_blur_mode, 0);
            float dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeBlurView_blur_border_width, -1);
            this.mBorderWidth = dimensionPixelSize2;
            if (dimensionPixelSize2 < 0.0f) {
                this.mBorderWidth = 0.0f;
            }
            ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(R.styleable.ShapeBlurView_blur_border_color);
            this.mBorderColor = colorStateList;
            if (colorStateList == null) {
                this.mBorderColor = ColorStateList.valueOf(-1);
            }
            typedArrayObtainStyledAttributes.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paint paint = new Paint();
        this.mBitmapPaint = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mBorderPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        paint2.setColor(this.mBorderColor.getColorForState(getState(), -1));
        paint2.setStrokeWidth(this.mBorderWidth);
    }

    private void initCornerData(float f) {
        int length = this.mCornerRadii.length;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            float[] fArr = this.mCornerRadii;
            if (fArr[i] < 0.0f) {
                fArr[i] = 0.0f;
            } else {
                z = true;
            }
        }
        if (!z) {
            if (f < 0.0f) {
                f = 0.0f;
            }
            int length2 = this.mCornerRadii.length;
            for (int i2 = 0; i2 < length2; i2++) {
                this.mCornerRadii[i2] = f;
            }
        }
        initCornerRids();
    }

    private void initCornerRids() {
        float[] fArr = this.cornerRids;
        if (fArr == null) {
            float[] fArr2 = this.mCornerRadii;
            float f = fArr2[1];
            float f2 = fArr2[2];
            float f3 = fArr2[3];
            this.cornerRids = new float[]{fArr2[0], fArr2[0], f, f, f2, f2, f3, f3};
            return;
        }
        float[] fArr3 = this.mCornerRadii;
        fArr[0] = fArr3[0];
        fArr[1] = fArr3[0];
        float f4 = fArr3[1];
        fArr[2] = f4;
        fArr[3] = f4;
        float f5 = fArr3[2];
        fArr[4] = f5;
        fArr[5] = f5;
        float f6 = fArr3[3];
        fArr[6] = f6;
        fArr[7] = f6;
    }

    protected BlurImpl getBlurImpl() {
        if (BLUR_IMPL == 0) {
            try {
                AndroidStockBlurImpl androidStockBlurImpl = new AndroidStockBlurImpl();
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(4, 4, Bitmap.Config.ARGB_8888);
                androidStockBlurImpl.prepare(getContext(), bitmapCreateBitmap, 4.0f);
                androidStockBlurImpl.release();
                bitmapCreateBitmap.recycle();
                BLUR_IMPL = 3;
            } catch (Throwable unused) {
            }
        }
        if (BLUR_IMPL == 0) {
            try {
                getClass().getClassLoader().loadClass("androidx.renderscript.RenderScript");
                AndroidXBlurImpl androidXBlurImpl = new AndroidXBlurImpl();
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(4, 4, Bitmap.Config.ARGB_8888);
                androidXBlurImpl.prepare(getContext(), bitmapCreateBitmap2, 4.0f);
                androidXBlurImpl.release();
                bitmapCreateBitmap2.recycle();
                BLUR_IMPL = 1;
            } catch (Throwable unused2) {
            }
        }
        if (BLUR_IMPL == 0) {
            try {
                getClass().getClassLoader().loadClass("android.support.v8.renderscript.RenderScript");
                SupportLibraryBlurImpl supportLibraryBlurImpl = new SupportLibraryBlurImpl();
                Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(4, 4, Bitmap.Config.ARGB_8888);
                supportLibraryBlurImpl.prepare(getContext(), bitmapCreateBitmap3, 4.0f);
                supportLibraryBlurImpl.release();
                bitmapCreateBitmap3.recycle();
                BLUR_IMPL = 2;
            } catch (Throwable unused3) {
            }
        }
        if (BLUR_IMPL == 0) {
            BLUR_IMPL = -1;
        }
        int i = BLUR_IMPL;
        if (i == 1) {
            return new AndroidXBlurImpl();
        }
        if (i == 2) {
            return new SupportLibraryBlurImpl();
        }
        if (i == 3) {
            return new AndroidStockBlurImpl();
        }
        return new EmptyBlurImpl();
    }

    public float getCornerRadius() {
        return getMaxCornerRadius();
    }

    public float getMaxCornerRadius() {
        float fMax = 0.0f;
        for (float f : this.mCornerRadii) {
            fMax = Math.max(f, fMax);
        }
        return fMax;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public int getBorderColor() {
        return this.mBorderColor.getDefaultColor();
    }

    public int getBlurMode() {
        return this.blurMode;
    }

    private void releaseBitmap() {
        Bitmap bitmap = this.mBitmapToBlur;
        if (bitmap != null) {
            bitmap.recycle();
            this.mBitmapToBlur = null;
        }
        Bitmap bitmap2 = this.mBlurredBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.mBlurredBitmap = null;
        }
        if (this.matrix != null) {
            this.matrix = null;
        }
        if (this.shader != null) {
            this.shader = null;
        }
        this.mContext = null;
    }

    protected void release() {
        releaseBitmap();
        this.mBlurImpl.release();
    }

    protected boolean prepare() {
        Bitmap bitmap;
        float f = this.mBlurRadius;
        if (f == 0.0f) {
            release();
            return false;
        }
        float f2 = this.mDownSampleFactor;
        float f3 = f / f2;
        if (f3 > 25.0f) {
            f2 = (f2 * f3) / 25.0f;
            f3 = 25.0f;
        }
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(1, (int) (width / f2));
        int iMax2 = Math.max(1, (int) (height / f2));
        boolean z = this.mDirty;
        if (this.mBlurringCanvas == null || (bitmap = this.mBlurredBitmap) == null || bitmap.getWidth() != iMax || this.mBlurredBitmap.getHeight() != iMax2) {
            releaseBitmap();
            try {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMax, iMax2, Bitmap.Config.ARGB_8888);
                this.mBitmapToBlur = bitmapCreateBitmap;
                if (bitmapCreateBitmap != null) {
                    this.mBlurringCanvas = new Canvas(this.mBitmapToBlur);
                    Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iMax, iMax2, Bitmap.Config.ARGB_8888);
                    this.mBlurredBitmap = bitmapCreateBitmap2;
                    if (bitmapCreateBitmap2 == null) {
                        release();
                        return false;
                    }
                    z = true;
                } else {
                    release();
                    return false;
                }
            } catch (OutOfMemoryError unused) {
                release();
                return false;
            } catch (Throwable unused2) {
                release();
                return false;
            }
        }
        if (z) {
            if (!this.mBlurImpl.prepare(getContext(), this.mBitmapToBlur, f3)) {
                return false;
            }
            this.mDirty = false;
        }
        return true;
    }

    protected void blur(Bitmap bitmap, Bitmap bitmap2) {
        this.shader = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.mBlurImpl.blur(bitmap, bitmap2);
    }

    protected View getActivityDecorView() {
        Context context = getContext();
        for (int i = 0; i < 4 && !(context instanceof Activity) && (context instanceof ContextWrapper); i++) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return ((Activity) context).getWindow().getDecorView();
        }
        return null;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        View activityDecorView = getActivityDecorView();
        this.mDecorView = activityDecorView;
        if (activityDecorView != null) {
            activityDecorView.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
            boolean z = this.mDecorView.getRootView() != getRootView();
            this.mDifferentRoot = z;
            if (z) {
                this.mDecorView.postInvalidate();
                return;
            }
            return;
        }
        this.mDifferentRoot = false;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        View view = this.mDecorView;
        if (view != null) {
            view.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
        }
        release();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.mIsRendering) {
            throw STOP_EXCEPTION;
        }
        if (RENDERING_COUNT > 0) {
            return;
        }
        super.draw(canvas);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBlurredBitmap(canvas, this.mBlurredBitmap, this.mOverlayColor);
    }

    protected void drawBlurredBitmap(Canvas canvas, Bitmap bitmap, int i) {
        if (bitmap != null) {
            int i2 = this.blurMode;
            if (i2 == 1) {
                drawCircleRectBitmap(canvas, bitmap, i);
            } else if (i2 == 2) {
                drawOvalRectBitmap(canvas, bitmap, i);
            } else {
                drawRoundRectBitmap(canvas, bitmap, i);
            }
        }
    }

    private void drawRoundRectBitmap(Canvas canvas, Bitmap bitmap, int i) {
        try {
            this.mRectFDst.right = getWidth();
            this.mRectFDst.bottom = getHeight();
            this.cornerPath.addRoundRect(this.mRectFDst, this.cornerRids, Path.Direction.CW);
            this.cornerPath.close();
            canvas.clipPath(this.cornerPath);
            this.mRectSrc.right = bitmap.getWidth();
            this.mRectSrc.bottom = bitmap.getHeight();
            canvas.drawBitmap(bitmap, this.mRectSrc, this.mRectFDst, (Paint) null);
            this.mBitmapPaint.setColor(i);
            canvas.drawRect(this.mRectFDst, this.mBitmapPaint);
            float f = this.mBorderWidth;
            if (f > 0.0f) {
                this.mBorderPaint.setStrokeWidth(f * 2.0f);
                canvas.drawPath(this.cornerPath, this.mBorderPaint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawOvalRectBitmap(Canvas canvas, Bitmap bitmap, int i) {
        try {
            this.mRectFDst.right = getWidth();
            this.mRectFDst.bottom = getHeight();
            this.mBitmapPaint.reset();
            this.mBitmapPaint.setAntiAlias(true);
            if (this.shader == null) {
                this.shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (this.matrix == null) {
                Matrix matrix = new Matrix();
                this.matrix = matrix;
                matrix.postScale(this.mRectFDst.width() / bitmap.getWidth(), this.mRectFDst.height() / bitmap.getHeight());
            }
            this.shader.setLocalMatrix(this.matrix);
            this.mBitmapPaint.setShader(this.shader);
            canvas.drawOval(this.mRectFDst, this.mBitmapPaint);
            this.mBitmapPaint.reset();
            this.mBitmapPaint.setAntiAlias(true);
            this.mBitmapPaint.setColor(i);
            canvas.drawOval(this.mRectFDst, this.mBitmapPaint);
            if (this.mBorderWidth > 0.0f) {
                this.mBorderRect.set(this.mRectFDst);
                RectF rectF = this.mBorderRect;
                float f = this.mBorderWidth;
                rectF.inset(f / 2.0f, f / 2.0f);
                canvas.drawOval(this.mBorderRect, this.mBorderPaint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawCircleRectBitmap(Canvas canvas, Bitmap bitmap, int i) {
        try {
            this.mRectFDst.right = getMeasuredWidth();
            this.mRectFDst.bottom = getMeasuredHeight();
            this.mRectSrc.right = bitmap.getWidth();
            this.mRectSrc.bottom = bitmap.getHeight();
            this.mBitmapPaint.reset();
            this.mBitmapPaint.setAntiAlias(true);
            if (this.shader == null) {
                this.shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (this.matrix == null) {
                Matrix matrix = new Matrix();
                this.matrix = matrix;
                matrix.postScale(this.mRectFDst.width() / this.mRectSrc.width(), this.mRectFDst.height() / this.mRectSrc.height());
            }
            this.shader.setLocalMatrix(this.matrix);
            this.mBitmapPaint.setShader(this.shader);
            if (this.mRectFDst.width() >= this.mRectSrc.width()) {
                this.cx = this.mRectFDst.width() / 2.0f;
                this.cy = this.mRectFDst.height() / 2.0f;
                this.cRadius = Math.min(this.mRectFDst.width(), this.mRectFDst.height()) / 2.0f;
                this.mBorderRect.set(this.mRectFDst);
            } else {
                this.cx = this.mRectSrc.width() / 2.0f;
                this.cy = this.mRectSrc.height() / 2.0f;
                this.cRadius = Math.min(this.mRectSrc.width(), this.mRectSrc.height()) / 2.0f;
                this.mBorderRect.set(this.mRectSrc);
            }
            canvas.drawCircle(this.cx, this.cy, this.cRadius, this.mBitmapPaint);
            this.mBitmapPaint.reset();
            this.mBitmapPaint.setAntiAlias(true);
            this.mBitmapPaint.setColor(i);
            canvas.drawCircle(this.cx, this.cy, this.cRadius, this.mBitmapPaint);
            if (this.mBorderWidth > 0.0f) {
                if (this.mBorderRect.width() > this.mBorderRect.height()) {
                    float fAbs = Math.abs(this.mBorderRect.height() - this.mBorderRect.width()) / 2.0f;
                    this.mBorderRect.left = fAbs;
                    RectF rectF = this.mBorderRect;
                    rectF.right = Math.min(rectF.width(), this.mBorderRect.height()) + fAbs;
                    RectF rectF2 = this.mBorderRect;
                    rectF2.bottom = Math.min(rectF2.width(), this.mBorderRect.height());
                } else if (this.mBorderRect.width() < this.mBorderRect.height()) {
                    float fAbs2 = Math.abs(this.mBorderRect.height() - this.mBorderRect.width()) / 2.0f;
                    this.mBorderRect.top = fAbs2;
                    RectF rectF3 = this.mBorderRect;
                    rectF3.right = Math.min(rectF3.width(), this.mBorderRect.height());
                    RectF rectF4 = this.mBorderRect;
                    rectF4.bottom = Math.min(rectF4.width(), this.mBorderRect.height()) + fAbs2;
                } else {
                    RectF rectF5 = this.mBorderRect;
                    rectF5.right = Math.min(rectF5.width(), this.mBorderRect.height());
                    RectF rectF6 = this.mBorderRect;
                    rectF6.bottom = Math.min(rectF6.width(), this.mBorderRect.height());
                }
                RectF rectF7 = this.mBorderRect;
                float f = this.mBorderWidth;
                rectF7.inset(f / 2.0f, f / 2.0f);
                canvas.drawOval(this.mBorderRect, this.mBorderPaint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int dp2px(float f) {
        return (int) ((f * this.mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public int[] getState() {
        return StateSet.WILD_CARD;
    }

    private static class StopException extends RuntimeException {
        private StopException() {
        }
    }

    public void refreshView(Builder builder) {
        boolean z;
        if (builder == null) {
            return;
        }
        boolean z2 = true;
        if (builder.blurMode == -1 || this.blurMode == builder.blurMode) {
            z = false;
        } else {
            this.blurMode = builder.blurMode;
            z = true;
        }
        if (builder.mBorderColor != null && !this.mBorderColor.equals(builder.mBorderColor)) {
            ColorStateList colorStateList = builder.mBorderColor;
            this.mBorderColor = colorStateList;
            this.mBorderPaint.setColor(colorStateList.getColorForState(getState(), -1));
            if (this.mBorderWidth > 0.0f) {
                z = true;
            }
        }
        if (builder.mBorderWidth > 0.0f) {
            float f = builder.mBorderWidth;
            this.mBorderWidth = f;
            this.mBorderPaint.setStrokeWidth(f);
            z = true;
        }
        if (this.mCornerRadii[0] != builder.mCornerRadii[0] || this.mCornerRadii[1] != builder.mCornerRadii[1] || this.mCornerRadii[2] != builder.mCornerRadii[2] || this.mCornerRadii[3] != builder.mCornerRadii[3]) {
            this.mCornerRadii[0] = builder.mCornerRadii[0];
            this.mCornerRadii[1] = builder.mCornerRadii[1];
            this.mCornerRadii[3] = builder.mCornerRadii[3];
            this.mCornerRadii[2] = builder.mCornerRadii[2];
            initCornerRids();
            z = true;
        }
        if (builder.mOverlayColor != -1 && this.mOverlayColor != builder.mOverlayColor) {
            this.mOverlayColor = builder.mOverlayColor;
            z = true;
        }
        if (builder.mBlurRadius > 0.0f && this.mBlurRadius != builder.mBlurRadius) {
            this.mBlurRadius = builder.mBlurRadius;
            this.mDirty = true;
            z = true;
        }
        if (builder.mDownSampleFactor <= 0.0f || this.mDownSampleFactor == builder.mDownSampleFactor) {
            z2 = z;
        } else {
            this.mDownSampleFactor = builder.mDownSampleFactor;
            this.mDirty = true;
            releaseBitmap();
        }
        if (z2) {
            invalidate();
        }
    }

    public static class Builder {
        private int blurMode;
        private float mBlurRadius;
        private ColorStateList mBorderColor;
        private float mBorderWidth;
        private Context mContext;
        private final float[] mCornerRadii;
        private float mDownSampleFactor;
        private int mOverlayColor;

        private Builder(Context context) {
            this.mDownSampleFactor = -1.0f;
            this.mOverlayColor = -1;
            this.mBlurRadius = -1.0f;
            this.mBorderWidth = -1.0f;
            this.mBorderColor = null;
            this.blurMode = -1;
            this.mCornerRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
            this.mContext = context.getApplicationContext();
        }

        public Builder setBlurRadius(float f) {
            this.mBlurRadius = f;
            return this;
        }

        public Builder setDownSampleFactor(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("DownSample factor must be greater than 0.");
            }
            this.mDownSampleFactor = f;
            return this;
        }

        public Builder setOverlayColor(int i) {
            this.mOverlayColor = i;
            return this;
        }

        public Builder setCornerRadius(int i, float f) {
            this.mCornerRadii[i] = f;
            return this;
        }

        public Builder setCornerRadiusDimen(int i) {
            float dimension = this.mContext.getResources().getDimension(i);
            return setCornerRadius(dimension, dimension, dimension, dimension);
        }

        public Builder setCornerRadius(float f) {
            return setCornerRadius(f, f, f, f);
        }

        public Builder setCornerRadius(float f, float f2, float f3, float f4) {
            float[] fArr = this.mCornerRadii;
            fArr[0] = f;
            fArr[1] = f2;
            fArr[3] = f3;
            fArr[2] = f4;
            return this;
        }

        public Builder setBorderWidth(int i) {
            return setBorderWidth(this.mContext.getResources().getDimension(i));
        }

        public Builder setBorderWidth(float f) {
            this.mBorderWidth = f;
            return this;
        }

        public Builder setBorderColor(int i) {
            return setBorderColor(ColorStateList.valueOf(ContextCompat.getColor(this.mContext, i)));
        }

        public Builder setBorderColor(ColorStateList colorStateList) {
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(-1);
            }
            this.mBorderColor = colorStateList;
            return this;
        }

        public Builder setBlurMode(int i) {
            this.blurMode = i;
            return this;
        }
    }

    public static Builder build(Context context) {
        return new Builder(context);
    }
}
