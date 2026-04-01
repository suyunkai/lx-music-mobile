package com.wanos.media.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.TypedValue;
import android.view.View;
import com.blankj.utilcode.util.ColorUtils;
import com.wanos.media.zero_p.R;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.abs.IPagerNavigator;

/* JADX INFO: loaded from: classes3.dex */
public class DummyCircleNavigator extends View implements IPagerNavigator {
    private final float SPACE;
    private List<PointF> mCirclePoints;
    private int mCurrentIndex;
    private int mDefaultColor;
    private int mIndicatorCount;
    private float mIndicatorRadius;
    private int mIndicatorSize;
    private final Paint mPaint;
    private int mSelectColor;

    @Override // net.lucode.hackware.magicindicator.abs.IPagerNavigator
    public void onAttachToMagicIndicator() {
    }

    @Override // net.lucode.hackware.magicindicator.abs.IPagerNavigator
    public void onDetachFromMagicIndicator() {
    }

    @Override // net.lucode.hackware.magicindicator.abs.IPagerNavigator
    public void onPageScrollStateChanged(int i) {
    }

    @Override // net.lucode.hackware.magicindicator.abs.IPagerNavigator
    public void onPageScrolled(int i, float f, int i2) {
    }

    public DummyCircleNavigator(Context context, int i) {
        super(context);
        this.mCirclePoints = new ArrayList();
        this.mIndicatorCount = 0;
        this.mCurrentIndex = -1;
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mIndicatorCount = i;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mSelectColor = ColorUtils.getColor(R.color.zero_help_indicator_select);
        this.mDefaultColor = ColorUtils.getColor(R.color.zero_help_indicator_default);
        this.SPACE = TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mCirclePoints.isEmpty()) {
            return;
        }
        this.mPaint.setColor(this.mDefaultColor);
        for (int i = 0; i < this.mCirclePoints.size(); i++) {
            PointF pointF = this.mCirclePoints.get(i);
            canvas.drawCircle(pointF.x, pointF.y, this.mIndicatorRadius, this.mPaint);
        }
        int i2 = this.mCurrentIndex;
        if (i2 < 0 || i2 >= this.mCirclePoints.size()) {
            return;
        }
        this.mPaint.setColor(this.mSelectColor);
        PointF pointF2 = this.mCirclePoints.get(this.mCurrentIndex);
        canvas.drawCircle(pointF2.x, pointF2.y, this.mIndicatorRadius, this.mPaint);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i2);
        this.mIndicatorSize = size;
        this.mIndicatorRadius = size / 2.0f;
        int i3 = this.mIndicatorCount;
        setMeasuredDimension((int) ((i3 * size) + ((i3 - 1) * this.SPACE)), size);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        initCirclePoints();
    }

    @Override // net.lucode.hackware.magicindicator.abs.IPagerNavigator
    public void onPageSelected(int i) {
        this.mCurrentIndex = i;
        invalidate();
    }

    @Override // net.lucode.hackware.magicindicator.abs.IPagerNavigator
    public void notifyDataSetChanged() {
        initCirclePoints();
    }

    private void initCirclePoints() {
        this.mCirclePoints.clear();
        if (this.mIndicatorCount <= 0) {
            return;
        }
        float f = this.mIndicatorRadius;
        for (int i = 0; i < this.mIndicatorCount; i++) {
            this.mCirclePoints.add(new PointF(f, this.mIndicatorRadius));
            f = f + this.mIndicatorRadius + this.SPACE;
        }
    }
}
