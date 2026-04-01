package com.wanos.media.ui.widget.lrc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.wanos.lyric.lrc.ILrcView;
import com.wanos.lyric.lrc.ILrcViewListener;
import com.wanos.lyric.lrc.impl.LrcRow;
import com.wanos.media.R;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class LrcView extends View implements ILrcView {
    public static final int DISPLAY_MODE_NORMAL = 0;
    public static final int DISPLAY_MODE_SCALE = 2;
    public static final int DISPLAY_MODE_SEEK = 1;
    public static final String TAG = "wanos:[LrcView]";
    private final int MODE_HIGH_LIGHT_KARAOKE;
    private final int MODE_HIGH_LIGHT_NORMAL;
    long currentMillis;
    private boolean isScale;
    private int mDisplayMode;
    private int mHighLightRow;
    private int mHighLightRowColor;
    private boolean mIsFirstMove;
    private float mLastMotionY;
    private String mLoadingLrcTip;
    private int mLrcFontSize;
    private int mLrcHighLightFontSize;
    private List<LrcRow> mLrcRows;
    private ILrcViewListener mLrcViewListener;
    private int mMinSeekFiredOffset;
    private int mNormalRowColor;
    private final Paint mPaint;
    private final PointF mPointerOneLastMotion;
    private final PointF mPointerTwoLastMotion;
    private final int mSeekLinePaddingX;
    private int mSeekRowTextColor;
    private int mSeekRowTextSize;
    private final int mode;
    int playRow;
    private int rowSpace;

    public LrcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDisplayMode = 0;
        this.mMinSeekFiredOffset = 10;
        this.mHighLightRow = 0;
        this.mHighLightRowColor = -1;
        this.mNormalRowColor = -7829368;
        this.mSeekRowTextColor = -16711681;
        this.mSeekRowTextSize = 20;
        this.mLrcFontSize = 15;
        this.mLrcHighLightFontSize = 18;
        this.rowSpace = 20;
        this.mSeekLinePaddingX = 0;
        this.mLoadingLrcTip = getContext().getString(R.string.the_music_no_lrc);
        this.MODE_HIGH_LIGHT_NORMAL = 0;
        this.MODE_HIGH_LIGHT_KARAOKE = 1;
        this.mode = 0;
        this.isScale = false;
        this.playRow = -1;
        this.mPointerOneLastMotion = new PointF();
        this.mPointerTwoLastMotion = new PointF();
        this.mIsFirstMove = false;
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.LrcView);
            this.mLrcFontSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, R.dimen.lrc_normal_text_size);
            this.mLrcHighLightFontSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, R.dimen.lrc_highlight_text_size);
            this.mMinSeekFiredOffset = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 10);
            this.mSeekRowTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, R.dimen.lrc_highlight_text_size);
            this.rowSpace = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, R.dimen.lrc_row_space);
            this.mHighLightRowColor = typedArrayObtainStyledAttributes.getColor(0, ViewCompat.MEASURED_SIZE_MASK);
            this.mNormalRowColor = typedArrayObtainStyledAttributes.getColor(5, 1728053247);
            this.mSeekRowTextColor = typedArrayObtainStyledAttributes.getColor(6, ViewCompat.MEASURED_SIZE_MASK);
        }
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setTextSize(this.mLrcFontSize);
    }

    @Override // com.wanos.lyric.lrc.ILrcView
    public void setListener(ILrcViewListener listener) {
        this.mLrcViewListener = listener;
    }

    @Override // com.wanos.lyric.lrc.ILrcView
    public void setLoadingTipText(String text) {
        if (!TextUtils.isEmpty(text)) {
            this.mLoadingLrcTip = text;
        }
        invalidate();
    }

    public boolean isScale() {
        return this.isScale;
    }

    public void setScale(boolean scale) {
        this.isScale = scale;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int iDrawHighLrcRow;
        int iDrawNormalLrcRow;
        int normalRowLastYOffet;
        int height = getHeight();
        int width = getWidth();
        List<LrcRow> list = this.mLrcRows;
        if (list == null || list.size() == 0) {
            if (this.mLoadingLrcTip != null) {
                this.mPaint.setColor(this.mHighLightRowColor);
                this.mPaint.setTextSize(this.mLrcFontSize);
                this.mPaint.setTextAlign(Paint.Align.CENTER);
                this.mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 500, false));
                canvas.drawText(this.mLoadingLrcTip, width / 2, (height / 2) - this.mLrcFontSize, this.mPaint);
                return;
            }
            return;
        }
        int i = width / 2;
        int i2 = (height / 2) - this.mLrcFontSize;
        if (this.mDisplayMode != 1 || this.mHighLightRow == this.playRow) {
            iDrawHighLrcRow = drawHighLrcRow(canvas, width, i, i2);
        } else {
            iDrawHighLrcRow = drawNormalLrcRow(canvas, width, i, i2);
        }
        int i3 = iDrawHighLrcRow;
        int i4 = this.mHighLightRow;
        int i5 = i4 - 1;
        if (i4 >= this.mLrcRows.size()) {
            return;
        }
        int i6 = i5;
        int i7 = i2;
        while (i7 > (-this.mLrcFontSize) && i6 >= 0) {
            String str = this.mLrcRows.get(i6).content;
            if (this.mDisplayMode == 1 && this.playRow == i6) {
                normalRowLastYOffet = getHighLightRowLastYOffet(width, str);
            } else {
                normalRowLastYOffet = getNormalRowLastYOffet(width, str);
            }
            int i8 = i7 - normalRowLastYOffet;
            if (this.mDisplayMode == 1 && this.playRow == i6) {
                drawHighLrcRow(canvas, width, i, i8, str);
            } else {
                drawNormalLrcRow(canvas, width, i, i8, str);
            }
            i6--;
            i7 = i8;
        }
        int i9 = i2 + i3;
        for (int i10 = this.mHighLightRow + 1; i9 < (height - this.mLrcFontSize) - this.rowSpace && i10 < this.mLrcRows.size(); i10++) {
            String str2 = this.mLrcRows.get(i10).content;
            if (this.mDisplayMode == 1 && this.playRow == i10) {
                iDrawNormalLrcRow = drawHighLrcRow(canvas, width, i, i9, str2);
            } else {
                iDrawNormalLrcRow = drawNormalLrcRow(canvas, width, i, i9, str2);
            }
            i9 += iDrawNormalLrcRow;
        }
    }

    private void drawKaraokeHighLightLrcRow(Canvas canvas, int width, int rowX, int highlightRowY) {
        LrcRow lrcRow = this.mLrcRows.get(this.mHighLightRow);
        String str = lrcRow.content;
        this.mPaint.setColor(this.mNormalRowColor);
        this.mPaint.setTextSize(this.mLrcFontSize);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        float f = highlightRowY;
        canvas.drawText(str, rowX, f, this.mPaint);
        int iMeasureText = (int) this.mPaint.measureText(str);
        int i = (width - iMeasureText) / 2;
        long startTime = lrcRow.getStartTime();
        int endTime = (int) ((((this.currentMillis - startTime) * 1.0f) / (lrcRow.getEndTime() - startTime)) * iMeasureText);
        if (endTime > 0) {
            this.mPaint.setColor(this.mHighLightRowColor);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(endTime, highlightRowY + this.rowSpace, Bitmap.Config.ARGB_8888);
            new Canvas(bitmapCreateBitmap).drawText(str, iMeasureText / 2, f, this.mPaint);
            canvas.drawBitmap(bitmapCreateBitmap, i, 0.0f, this.mPaint);
        }
    }

    private int drawNormalLrcRow(Canvas canvas, int width, int rowX, int highlightRowY) {
        return this.mHighLightRow < this.mLrcRows.size() ? drawNormalLrcRow(canvas, width, rowX, highlightRowY, this.mLrcRows.get(this.mHighLightRow).content) : this.rowSpace + this.mLrcFontSize;
    }

    private int drawNormalLrcRow(Canvas canvas, int width, int rowX, int highlightRowY, String content) {
        this.mPaint.setColor(this.mNormalRowColor);
        this.mPaint.setTextSize(this.mLrcFontSize);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        int i = this.rowSpace + this.mLrcFontSize;
        if (this.mHighLightRow >= this.mLrcRows.size()) {
            return i;
        }
        if (((int) this.mPaint.measureText(content)) > width) {
            StringBuffer stringBuffer = new StringBuffer();
            String[] strArrSplit = content.split("");
            if (strArrSplit.length <= 0) {
                return i;
            }
            int i2 = 0;
            int i3 = 0;
            int iMeasureText = 0;
            while (i2 < strArrSplit.length) {
                stringBuffer.append(strArrSplit[i2]);
                iMeasureText += (int) this.mPaint.measureText(strArrSplit[i2]);
                int i4 = i2 + 1;
                if ((i4 < strArrSplit.length ? (int) this.mPaint.measureText(strArrSplit[i4]) : 0) + iMeasureText > width) {
                    canvas.drawText(stringBuffer.toString(), rowX, highlightRowY, this.mPaint);
                    int i5 = this.rowSpace;
                    int i6 = this.mLrcFontSize;
                    highlightRowY = highlightRowY + (i5 / 2) + i6;
                    i3 = i3 + (i5 / 2) + i6;
                    stringBuffer.replace(0, stringBuffer.length(), "");
                    iMeasureText = 0;
                } else if (i2 == strArrSplit.length - 1) {
                    canvas.drawText(stringBuffer.toString(), rowX, highlightRowY, this.mPaint);
                    int i7 = this.rowSpace;
                    int i8 = this.mLrcFontSize;
                    highlightRowY = highlightRowY + i7 + i8;
                    i3 = i3 + i7 + i8;
                }
                i2 = i4;
            }
            return i3;
        }
        canvas.drawText(content, rowX, highlightRowY, this.mPaint);
        return i;
    }

    private int drawHighLrcRow(Canvas canvas, int width, int rowX, int highlightRowY) {
        return this.mHighLightRow < this.mLrcRows.size() ? drawHighLrcRow(canvas, width, rowX, highlightRowY, this.mLrcRows.get(this.mHighLightRow).content) : this.rowSpace + this.mLrcFontSize;
    }

    private int drawHighLrcRow(Canvas canvas, int width, int rowX, int highlightRowY, String highlightText) {
        int i = this.rowSpace + this.mLrcFontSize;
        this.mPaint.setColor(this.mHighLightRowColor);
        this.mPaint.setTextSize(this.mLrcHighLightFontSize);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        if (this.mHighLightRow >= this.mLrcRows.size()) {
            return i;
        }
        if (((int) this.mPaint.measureText(highlightText)) > width) {
            StringBuffer stringBuffer = new StringBuffer();
            String[] strArrSplit = highlightText.split("");
            if (strArrSplit.length <= 0) {
                return i;
            }
            int i2 = 0;
            int i3 = 0;
            int iMeasureText = 0;
            while (i2 < strArrSplit.length) {
                stringBuffer.append(strArrSplit[i2]);
                iMeasureText += (int) this.mPaint.measureText(strArrSplit[i2]);
                int i4 = i2 + 1;
                if ((i4 < strArrSplit.length ? (int) this.mPaint.measureText(strArrSplit[i4]) : 0) + iMeasureText > width) {
                    canvas.drawText(stringBuffer.toString(), rowX, highlightRowY, this.mPaint);
                    int i5 = this.rowSpace;
                    int i6 = this.mLrcFontSize;
                    highlightRowY = highlightRowY + (i5 / 2) + i6;
                    i3 = i3 + (i5 / 2) + i6;
                    stringBuffer.replace(0, stringBuffer.length(), "");
                    iMeasureText = 0;
                } else if (i2 == strArrSplit.length - 1) {
                    canvas.drawText(stringBuffer.toString(), rowX, highlightRowY, this.mPaint);
                    int i7 = this.rowSpace;
                    int i8 = this.mLrcFontSize;
                    highlightRowY = highlightRowY + i7 + i8;
                    i3 = i3 + i7 + i8;
                }
                i2 = i4;
            }
            return i3;
        }
        canvas.drawText(highlightText, rowX, highlightRowY, this.mPaint);
        return i;
    }

    private int getNormalRowLastYOffet(int width, String content) {
        this.mPaint.setColor(this.mNormalRowColor);
        this.mPaint.setTextSize(this.mLrcFontSize);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        int i = this.rowSpace + this.mLrcFontSize;
        if (this.mHighLightRow >= this.mLrcRows.size() || ((int) this.mPaint.measureText(content)) <= width) {
            return i;
        }
        String[] strArrSplit = content.split("");
        if (strArrSplit.length <= 0) {
            return i;
        }
        int i2 = 0;
        int i3 = 0;
        int iMeasureText = 0;
        while (i3 < strArrSplit.length) {
            iMeasureText += (int) this.mPaint.measureText(strArrSplit[i3]);
            int i4 = i3 + 1;
            if ((i4 < strArrSplit.length ? (int) this.mPaint.measureText(strArrSplit[i4]) : 0) + iMeasureText > width) {
                i2 = i2 + (this.rowSpace / 2) + this.mLrcFontSize;
                iMeasureText = 0;
            } else if (i3 == strArrSplit.length - 1) {
                i2 = i2 + this.rowSpace + this.mLrcFontSize;
            }
            i3 = i4;
        }
        return i2;
    }

    private int getHighLightRowLastYOffet(int width, String highlightText) {
        int i = this.rowSpace + this.mLrcFontSize;
        this.mPaint.setColor(this.mHighLightRowColor);
        this.mPaint.setTextSize(this.mLrcHighLightFontSize);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        if (this.mHighLightRow >= this.mLrcRows.size() || ((int) this.mPaint.measureText(highlightText)) <= width) {
            return i;
        }
        String[] strArrSplit = highlightText.split("");
        if (strArrSplit.length <= 0) {
            return i;
        }
        int i2 = 0;
        int i3 = 0;
        int iMeasureText = 0;
        while (i3 < strArrSplit.length) {
            iMeasureText += (int) this.mPaint.measureText(strArrSplit[i3]);
            int i4 = i3 + 1;
            if ((i4 < strArrSplit.length ? (int) this.mPaint.measureText(strArrSplit[i4]) : 0) + iMeasureText > width) {
                i2 = i2 + (this.rowSpace / 2) + this.mLrcFontSize;
                iMeasureText = 0;
            } else if (i3 == strArrSplit.length - 1) {
                i2 = i2 + this.rowSpace + this.mLrcFontSize;
            }
            i3 = i4;
        }
        return i2;
    }

    public void seekLrc(int position, boolean cb) {
        List<LrcRow> list = this.mLrcRows;
        if (list == null || position < 0 || position >= list.size() || this.mLrcRows.size() == 0) {
            return;
        }
        if (this.mHighLightRow != position || cb) {
            LrcRow lrcRow = this.mLrcRows.get(position);
            if (!cb) {
                this.mHighLightRow = position;
            }
            invalidate();
            ILrcViewListener iLrcViewListener = this.mLrcViewListener;
            if (iLrcViewListener == null || !cb) {
                return;
            }
            iLrcViewListener.onLrcSought(position, lrcRow);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            java.util.List<com.wanos.lyric.lrc.impl.LrcRow> r0 = r4.mLrcRows
            if (r0 == 0) goto L58
            int r0 = r0.size()
            if (r0 != 0) goto Lb
            goto L58
        Lb:
            int r0 = r5.getAction()
            java.lang.String r1 = "wanos:[LrcView]"
            r2 = 1
            if (r0 == 0) goto L38
            if (r0 == r2) goto L2b
            r3 = 2
            if (r0 == r3) goto L1d
            r5 = 3
            if (r0 == r5) goto L2b
            goto L57
        L1d:
            java.lang.String r0 = "one move"
            android.util.Log.d(r1, r0)
            int r0 = r4.mDisplayMode
            if (r0 != r3) goto L27
            return r2
        L27:
            r4.doSeek(r5)
            goto L57
        L2b:
            int r5 = r4.mDisplayMode
            if (r5 != r2) goto L34
            int r5 = r4.mHighLightRow
            r4.seekLrc(r5, r2)
        L34:
            r5 = 0
            r4.mDisplayMode = r5
            goto L57
        L38:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "down,mLastMotionY:"
            r0.<init>(r3)
            float r3 = r4.mLastMotionY
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
            float r5 = r5.getY()
            r4.mLastMotionY = r5
            r4.mIsFirstMove = r2
            r4.invalidate()
        L57:
            return r2
        L58:
            boolean r5 = super.onTouchEvent(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.media.ui.widget.lrc.LrcView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void doSeek(MotionEvent event) {
        float y = event.getY();
        float f = y - this.mLastMotionY;
        if (Math.abs(f) < this.mMinSeekFiredOffset) {
            return;
        }
        this.mDisplayMode = 1;
        int iAbs = Math.abs(((int) f) / this.mLrcFontSize);
        Log.d(TAG, "move to new hightlightrow : " + this.mHighLightRow + " offsetY: " + f + " rowOffset:" + iAbs);
        if (f < 0.0f) {
            this.mHighLightRow += iAbs;
        } else if (f > 0.0f) {
            this.mHighLightRow -= iAbs;
        }
        int iMax = Math.max(0, this.mHighLightRow);
        this.mHighLightRow = iMax;
        this.mHighLightRow = Math.min(iMax, this.mLrcRows.size() - 1);
        if (iAbs > 0) {
            this.mLastMotionY = y;
            invalidate();
        }
    }

    private void setTwoPointerLocation(MotionEvent event) {
        this.mPointerOneLastMotion.x = event.getX(0);
        this.mPointerOneLastMotion.y = event.getY(0);
        this.mPointerTwoLastMotion.x = event.getX(1);
        this.mPointerTwoLastMotion.y = event.getY(1);
    }

    @Override // com.wanos.lyric.lrc.ILrcView
    public void setLrc(List<LrcRow> lrcRows) {
        this.mLrcRows = lrcRows;
        invalidate();
    }

    @Override // com.wanos.lyric.lrc.ILrcView
    public void seekLrcToTime(long time) {
        List<LrcRow> list = this.mLrcRows;
        if (list == null || list.size() == 0 || this.mDisplayMode != 0 || this.currentMillis == time) {
            return;
        }
        this.currentMillis = time;
        int i = 0;
        while (i < this.mLrcRows.size()) {
            LrcRow lrcRow = this.mLrcRows.get(i);
            int i2 = i + 1;
            LrcRow lrcRow2 = i2 == this.mLrcRows.size() ? null : this.mLrcRows.get(i2);
            if ((time >= lrcRow.startTime && lrcRow2 != null && time < lrcRow2.startTime) || (time > lrcRow.startTime && lrcRow2 == null)) {
                this.playRow = i;
                seekLrc(i, false);
                return;
            }
            i = i2;
        }
    }
}
