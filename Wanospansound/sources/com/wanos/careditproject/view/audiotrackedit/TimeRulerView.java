package com.wanos.careditproject.view.audiotrackedit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.timepicker.TimeModel;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.StorageUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class TimeRulerView extends ViewGroup {
    private int bottomH;
    private int distanceW;
    private int distanceWSecond;
    private int lineColor;
    private int lineHeight1;
    private int lineHeight2;
    private int lineStartY1;
    private int lineStartY2;
    private int lineStrokeW;
    private Paint paint;
    private Map<Float, Integer> scale2Num;
    private float scaleFactor;
    private int showCount;
    private int stepS;
    private int timeTotal;
    private List<TextView> tvTimeList;
    private int viewH;

    public TimeRulerView(Context context) {
        super(context);
        this.lineColor = Color.parseColor("#626669");
        this.stepS = EditingUtils.minLineCountOfMaxLine;
        this.scaleFactor = 1.0f;
        this.showCount = 0;
    }

    public TimeRulerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lineColor = Color.parseColor("#626669");
        this.stepS = EditingUtils.minLineCountOfMaxLine;
        this.scaleFactor = 1.0f;
        this.showCount = 0;
        init(context);
    }

    public void vScrollTo(int i, int i2) {
        scrollTo(i, i2);
    }

    public void setViewScaleX(float f) {
        boolean z;
        this.scaleFactor = f;
        for (int i = 0; i < this.showCount; i++) {
            TextView textView = this.tvTimeList.get(i);
            if (textView != null) {
                Iterator<Float> it = this.scale2Num.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    Float next = it.next();
                    if (this.scaleFactor <= next.floatValue()) {
                        if (i % this.scale2Num.get(next).intValue() == 0) {
                            textView.setVisibility(0);
                        } else {
                            textView.setVisibility(4);
                        }
                        z = true;
                    }
                }
                if (!z) {
                    textView.setVisibility(0);
                }
            }
        }
        EditingUtils.log("setViewScaleX = " + f);
        requestLayout();
    }

    private void init(Context context) {
        this.timeTotal = EditingUtils.getTotalTimeOfSecond();
        this.tvTimeList = new ArrayList();
        ArrayMap arrayMap = new ArrayMap();
        this.scale2Num = arrayMap;
        arrayMap.put(Float.valueOf(0.08f), 40);
        this.scale2Num.put(Float.valueOf(0.15f), 20);
        this.scale2Num.put(Float.valueOf(0.3f), 10);
        this.scale2Num.put(Float.valueOf(1.0f), 5);
        this.lineHeight1 = EditingUtils.dp2px(context, 14.0f);
        this.lineHeight2 = EditingUtils.dp2px(context, 24.0f);
        this.lineStrokeW = EditingUtils.dp2px(context, 1.0f);
        this.bottomH = EditingUtils.dp2px(context, 16.0f);
        this.distanceW = EditingUtils.dp2px(context, EditingUtils.distanceWidthMax);
        this.distanceWSecond = EditingUtils.dp2px(context, EditingUtils.distanceWidthMax * EditingUtils.minLineCountOfMaxLine);
        this.lineStartY1 = EditingUtils.dp2px(context, 60.0f) - this.lineHeight1;
        this.lineStartY2 = EditingUtils.dp2px(context, 60.0f) - this.lineHeight2;
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(this.lineColor);
        this.paint.setAntiAlias(true);
        update(false);
    }

    public void update(boolean z) {
        int i;
        if (StorageUtils.getInstance().getBeatSwitch()) {
            float beatSpeed = 60.0f / StorageUtils.getInstance().getBeatSpeed();
            if (z) {
                this.scaleFactor = beatSpeed;
            }
            i = (int) (this.timeTotal / beatSpeed);
        } else {
            i = this.timeTotal;
        }
        this.showCount = i;
        if (i > this.tvTimeList.size()) {
            int size = i - this.tvTimeList.size();
            for (int i2 = 0; i2 < size; i2++) {
                TextView textView = new TextView(getContext());
                textView.setTextSize(2, 16.0f);
                textView.setTextColor(-1);
                addView(textView);
                this.tvTimeList.add(textView);
            }
        } else if (i < this.tvTimeList.size()) {
            EditingUtils.log("TimeRulerView count = " + i + ", size=" + this.tvTimeList.size());
            for (int i3 = i - 1; i3 < this.tvTimeList.size(); i3++) {
                this.tvTimeList.get(i3).setVisibility(8);
            }
        }
        if (StorageUtils.getInstance().getBeatSwitch()) {
            showClickTrack(i);
        } else {
            showTimeLine(i);
        }
        setViewScaleX(this.scaleFactor);
        if (z) {
            EditingUtils.log("TimeRulerView update ");
            requestLayout();
        }
    }

    public void redraw() {
        EditingUtils.log("TimeRulerView redraw ");
        invalidate();
    }

    public void showClickTrack(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.tvTimeList.get(i2).setVisibility(0);
            this.tvTimeList.get(i2).setText(i2 + "");
        }
    }

    public void showTimeLine(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.tvTimeList.get(i2).setVisibility(0);
            this.tvTimeList.get(i2).setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2 / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2 % 60)));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int iDp2px = EditingUtils.dp2px(getContext(), 20.0f);
        int iDp2px2 = EditingUtils.dp2px(getContext(), 55.0f);
        for (int i5 = 0; i5 < this.showCount; i5++) {
            TextView textView = this.tvTimeList.get(i5);
            if (textView != null) {
                if (StorageUtils.getInstance().getBeatSwitch()) {
                    int beatSpeed = ((int) (this.distanceWSecond * i5 * this.scaleFactor * (60.0f / StorageUtils.getInstance().getBeatSpeed()))) + EditingUtils.dp2px(getContext(), 3.0f) + (getMeasuredWidth() / 2);
                    int iDp2px3 = EditingUtils.dp2px(getContext(), 40.0f);
                    if (textView.length() > 3) {
                        iDp2px3 = EditingUtils.dp2px(getContext(), 50.0f);
                    }
                    textView.layout(beatSpeed, iDp2px, iDp2px3 + beatSpeed, iDp2px2);
                } else {
                    int iDp2px4 = ((int) (this.distanceWSecond * i5 * this.scaleFactor)) + EditingUtils.dp2px(getContext(), 5.0f) + (getMeasuredWidth() / 2);
                    textView.layout(iDp2px4, iDp2px, EditingUtils.dp2px(getContext(), 70.0f) + iDp2px4, iDp2px2);
                }
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        EditingUtils.log("TimeRulerView PcmDataView onMeasure");
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        super.onDraw(canvas);
        int i3 = -getScrollX();
        this.lineStartY1 = (getHeight() - this.bottomH) - this.lineHeight1;
        this.lineStartY2 = (getHeight() - this.bottomH) - this.lineHeight2;
        if (StorageUtils.getInstance().getBeatSwitch()) {
            float beatSpeed = 60.0f / StorageUtils.getInstance().getBeatSpeed();
            int i4 = (int) ((this.timeTotal / beatSpeed) + 1.0f);
            float f = this.distanceWSecond * beatSpeed;
            for (int i5 = 0; i5 < i4; i5++) {
                int measuredWidth = (int) ((i5 * f * this.scaleFactor) + (getMeasuredWidth() / 2));
                int i6 = i3 + measuredWidth;
                if (i6 >= 0 && i6 <= EditingUtils.getScreenWidth() * EditingUtils.redrawDrawValue) {
                    float f2 = this.lineStartY2;
                    if (i5 % 5 != 0) {
                        f2 = this.lineStartY1;
                    }
                    canvas.drawLine(measuredWidth, f2, measuredWidth + 1, getHeight() - this.bottomH, this.paint);
                }
            }
            return;
        }
        int i7 = this.timeTotal * EditingUtils.minLineCountOfMaxLine;
        float f3 = (this.distanceWSecond * 1.0f) / EditingUtils.minLineCountOfMaxLine;
        for (int i8 = 0; i8 < i7; i8++) {
            int measuredWidth2 = (int) ((i8 * f3 * this.scaleFactor) + (getMeasuredWidth() / 2));
            int i9 = i3 + measuredWidth2;
            if (i9 >= 0 && i9 <= EditingUtils.getScreenWidth() * EditingUtils.redrawDrawValue) {
                Boolean bool = false;
                Iterator<Float> it = this.scale2Num.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Float next = it.next();
                    if (this.scaleFactor < next.floatValue()) {
                        int iIntValue = this.scale2Num.get(next).intValue();
                        if (i8 % ((EditingUtils.minLineCountOfMaxLine * iIntValue) / 5) == 0) {
                            if (i8 % (EditingUtils.minLineCountOfMaxLine * iIntValue) == 0) {
                                i2 = this.lineStartY2;
                            } else {
                                i2 = this.lineStartY1;
                            }
                            canvas.drawLine(measuredWidth2, i2, measuredWidth2 + 1, getHeight() - this.bottomH, this.paint);
                        }
                        bool = true;
                    }
                }
                if (!bool.booleanValue()) {
                    if (i8 % EditingUtils.minLineCountOfMaxLine == 0) {
                        i = this.lineStartY2;
                    } else {
                        i = this.lineStartY1;
                    }
                    canvas.drawLine(measuredWidth2, i, measuredWidth2 + 1, getHeight() - this.bottomH, this.paint);
                }
            }
        }
    }
}
