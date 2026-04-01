package com.wanos.careditproject.view.audiotrackedit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.wanos.careditproject.model.server.TrackItemModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTrackBgView extends ViewGroup {
    private Paint borderPaint;
    private boolean isSelected;
    private Paint linePaint;
    private int selectIndex;
    private int selectTrackW;
    private int selectTrackY;

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public AudioTrackBgView(Context context) {
        super(context);
        this.selectTrackY = 0;
        this.selectTrackW = 0;
        this.selectIndex = 0;
        init(context);
    }

    public AudioTrackBgView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.selectTrackY = 0;
        this.selectTrackW = 0;
        this.selectIndex = 0;
        init(context);
    }

    public void showSelectTrack(int i, int i2, int i3) {
        this.selectTrackY = i;
        this.selectTrackW = i2;
        this.selectIndex = i3;
        if (i3 != -1) {
            TrackItemModel trackByIndex = DataHelpAudioTrack.getTrackByIndex(i3);
            if (trackByIndex != null) {
                int color_index = trackByIndex.getColor_index() % EditingUtils.trackColorList.length;
                if (color_index == -1) {
                    color_index = 0;
                }
                this.linePaint.setColor(Color.parseColor((DataHelpAudioTrack.trackIsPlay(this.selectIndex) ? new StringBuilder("#6F").append(EditingUtils.trackColorList[color_index]) : new StringBuilder("#6F").append(EditingUtils.trackColorGray)).toString()));
                invalidate();
                return;
            }
            return;
        }
        invalidate();
    }

    private void init(Context context) {
        EditingUtils.log("AudioTracksView PcmDataView PcmDataView a");
        Paint paint = new Paint();
        this.linePaint = paint;
        paint.setColor(Color.parseColor("#FFFF00"));
        this.linePaint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        EditingUtils.log("AudioTracksView PcmDataView onDraw viewW=");
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(0.0f, this.selectTrackY);
        path.lineTo(0.0f, this.selectTrackY + this.selectTrackW);
        path.lineTo(getWidth(), this.selectTrackY + this.selectTrackW);
        path.lineTo(getWidth(), this.selectTrackY);
        path.close();
        canvas.drawPath(path, this.linePaint);
    }
}
