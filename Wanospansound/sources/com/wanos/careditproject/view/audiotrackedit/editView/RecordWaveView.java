package com.wanos.careditproject.view.audiotrackedit.editView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.wanos.careditproject.R;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.RecordUtils;

/* JADX INFO: loaded from: classes3.dex */
public class RecordWaveView extends View {
    private String clipId;
    private float lineDistance;
    private float lineW;
    private String mAudioPath;
    private int numOfLine;
    private int originStart;
    private float totalH;
    private int viewW;
    private int wavLineMaxNum;
    private int wavLineNum;
    private float[] wavPoints;
    private Paint wavePaint;

    public RecordWaveView(Context context) {
        super(context);
        this.originStart = 0;
        this.clipId = "";
        this.wavLineMaxNum = 40;
        this.wavLineNum = 0;
        this.viewW = 0;
        init(context);
    }

    public RecordWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.originStart = 0;
        this.clipId = "";
        this.wavLineMaxNum = 40;
        this.wavLineNum = 0;
        this.viewW = 0;
        init(context);
    }

    public void init(Context context) {
        this.numOfLine = EditingUtils.sampleRateDefault / ((EditingUtils.distanceWidthMax * EditingUtils.minLineCountOfMaxLine) / EditingUtils.pcmLineWidth);
        EditingUtils.log("WaveView init numOfLine=" + this.numOfLine);
        this.lineW = context.getResources().getDimension(R.dimen.edit_record_line_w);
        this.lineDistance = context.getResources().getDimension(R.dimen.edit_record_line_distance);
        this.totalH = context.getResources().getDimension(R.dimen.edit_record_line_h_max);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.edit_record_lines_w);
        this.viewW = dimensionPixelSize;
        int i = (int) (dimensionPixelSize / (this.lineW + this.lineDistance));
        this.wavLineMaxNum = i;
        this.wavPoints = new float[i * 4];
        Paint paint = new Paint();
        this.wavePaint = paint;
        paint.setColor(Color.parseColor("#FF3C3C"));
        this.wavePaint.setStyle(Paint.Style.FILL);
        this.wavePaint.setStrokeWidth(this.lineW);
        this.wavePaint.setAntiAlias(true);
    }

    public void setTrackIndex(int i, String str, String str2) {
        this.clipId = str;
        this.mAudioPath = str2;
    }

    public void setData(RecordUtils.RecordRes recordRes) {
        int i;
        int i2;
        if (recordRes != null) {
            short[] sArr = recordRes.recordWavData;
            int i3 = recordRes.recordWavDataLen;
            int iMin = Math.min(this.wavLineMaxNum, i3);
            if (i3 > iMin) {
                i2 = i3 - iMin;
                i = 0;
            } else {
                i = this.viewW - ((int) ((this.lineW + this.lineDistance) * iMin));
                i2 = 0;
            }
            int height = (int) ((getHeight() - this.totalH) / 2.0f);
            for (int i4 = 0; i4 < iMin; i4++) {
                float f = i + ((this.lineW + this.lineDistance) * i4);
                float fAbs = Math.abs(sArr[i2 + i4] / 32767.0f);
                float f2 = this.totalH;
                float f3 = fAbs * f2;
                if (f3 < 2.0f) {
                    f3 = 2.0f;
                }
                float f4 = ((f2 - f3) / 2.0f) + height;
                float[] fArr = this.wavPoints;
                int i5 = i4 * 4;
                float f5 = f + 0.5f;
                fArr[i5] = f5;
                fArr[i5 + 1] = f4;
                fArr[i5 + 2] = f5;
                fArr[i5 + 3] = f4 + f3;
            }
            this.wavLineNum = iMin;
            return;
        }
        this.wavLineNum = 0;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.wavLineNum;
        if (i > 0) {
            canvas.drawLines(this.wavPoints, 0, i * 4, this.wavePaint);
        }
    }
}
