package com.wanos.careditproject.view.audiotrackedit.editView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.wanos.careditproject.model.server.ClipModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.codec.AudioPcmData;

/* JADX INFO: loaded from: classes3.dex */
public class WaveView extends ViewGroup {
    private String clipId;
    private String mAudioPath;
    private int numOfLine;
    private int originStart;
    private int showW;
    private int totalH;
    private Paint wavePaint;

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public WaveView(Context context) {
        super(context);
        this.originStart = 0;
        this.clipId = "";
        init(context);
    }

    public WaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.originStart = 0;
        this.clipId = "";
        init(context);
    }

    public void init(Context context) {
        this.numOfLine = EditingUtils.sampleRateDefault / ((EditingUtils.distanceWidthMax * EditingUtils.minLineCountOfMaxLine) / EditingUtils.pcmLineWidth);
        EditingUtils.log("WaveView init numOfLine=" + this.numOfLine);
        this.showW = EditingUtils.dp2px(context, EditingUtils.pcmLineWidth);
        this.totalH = EditingUtils.dp2px(context, EditingUtils.DPtrackViewH);
        Paint paint = new Paint();
        this.wavePaint = paint;
        paint.setColor(Color.parseColor("#FFFFFFFF"));
        this.wavePaint.setStyle(Paint.Style.FILL);
    }

    public void setTrackIndex(int i, String str, String str2) {
        this.clipId = str;
        this.mAudioPath = str2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float trackScaleFactor = EditingParams.getInstance().getTrackScaleFactor();
        ClipModel clipById = DataHelpAudioTrack.getClipById(this.clipId);
        if (clipById != null) {
            this.originStart = (int) clipById.getOriginStart();
        } else {
            this.originStart = 0;
        }
        EditingUtils.log("WaveView pcmData.name=");
        int width = getWidth();
        float height = getHeight() / 2;
        float f = width;
        canvas.drawLine(0.0f, height, f, height, this.wavePaint);
        AudioPcmData.PcmData pcmData = AudioPcmData.getInstance().getPcmData(this.mAudioPath);
        if (pcmData != null) {
            int iSampleNum2px = EditingUtils.sampleNum2px(this.originStart, getContext());
            int i = ((pcmData.writeLen / 2) / this.numOfLine) / pcmData.channelNum;
            int i2 = iSampleNum2px / this.showW;
            for (int i3 = i2; i3 < i; i3++) {
                float fAbs = Math.abs((pcmData.wavePcm.size() > ((int) ((((pcmData.channelNum * i3) * this.numOfLine) / EditingUtils.pcmWaveStep) / trackScaleFactor)) ? pcmData.wavePcm.get(r8).shortValue() : (short) 0) / 32767.0f);
                int i4 = this.totalH;
                float f2 = i4 * fAbs;
                float f3 = (i4 - f2) / 2.0f;
                int i5 = this.showW;
                float f4 = (i3 - i2) * i5;
                if (f4 > f) {
                    return;
                }
                canvas.drawRect(f4, f3, f4 + i5, f3 + f2, this.wavePaint);
            }
        }
    }
}
