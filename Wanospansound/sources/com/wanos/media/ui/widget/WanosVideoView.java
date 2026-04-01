package com.wanos.media.ui.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.VideoView;

/* JADX INFO: loaded from: classes3.dex */
public class WanosVideoView extends VideoView {
    public WanosVideoView(Context context) {
        super(context);
    }

    public WanosVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wanos.media.ui.widget.WanosVideoView.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
    }

    public WanosVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wanos.media.ui.widget.WanosVideoView.2
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
    }

    public WanosVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wanos.media.ui.widget.WanosVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
    }
}
