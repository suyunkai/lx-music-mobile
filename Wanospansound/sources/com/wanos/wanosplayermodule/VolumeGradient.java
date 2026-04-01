package com.wanos.wanosplayermodule;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.animation.LinearInterpolator;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: loaded from: classes3.dex */
public class VolumeGradient {
    private ValueAnimator animator;

    public interface VolumeGradientCallback {
        void onGradEnd(boolean z);
    }

    public void volumeGradient(final MediaPlayer mediaPlayer, final float f, float f2, final VolumeGradientCallback volumeGradientCallback, final boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, f2);
        this.animator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(200L);
        this.animator.setInterpolator(new LinearInterpolator());
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wanos.wanosplayermodule.VolumeGradient.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                float fFloatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                if (fFloatValue == 0.0f) {
                    LogUtils.e("TTTTTTTTT");
                }
                try {
                    mediaPlayer.setVolume(fFloatValue, fFloatValue);
                } catch (Exception unused) {
                    valueAnimator2.cancel();
                }
            }
        });
        this.animator.addListener(new AnimatorListenerAdapter() { // from class: com.wanos.wanosplayermodule.VolumeGradient.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                VolumeGradientCallback volumeGradientCallback2 = volumeGradientCallback;
                if (volumeGradientCallback2 != null) {
                    volumeGradientCallback2.onGradEnd(true);
                }
                if (z) {
                    MediaPlayer mediaPlayer2 = mediaPlayer;
                    float f3 = f;
                    mediaPlayer2.setVolume(f3, f3);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                LogUtils.e("animator" + VolumeGradient.this.animator.getAnimatedValue().toString());
                VolumeGradientCallback volumeGradientCallback2 = volumeGradientCallback;
                if (volumeGradientCallback2 != null) {
                    volumeGradientCallback2.onGradEnd(false);
                }
                if (z) {
                    MediaPlayer mediaPlayer2 = mediaPlayer;
                    float f3 = f;
                    mediaPlayer2.setVolume(f3, f3);
                }
            }
        });
        this.animator.start();
    }

    public float getCurV() {
        AudioManager audioManager = (AudioManager) Utils.getApp().getSystemService("audio");
        int streamVolume = audioManager.getStreamVolume(3);
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        if (streamMaxVolume <= 0) {
            return 1.0f;
        }
        return streamVolume / (streamMaxVolume * 1.0f);
    }

    public void cancleAnimator() {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }
}
