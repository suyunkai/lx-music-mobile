package com.wanos.wanosplayermodule.audioTrack.drouteclient;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.LinearInterpolator;

/* JADX INFO: loaded from: classes3.dex */
public class WanosFadeInOutManager {
    public static final String TAG = "wanos:[WanosFadeInOutManager]";
    private static WanosFadeInOutManager mInstance;
    private ValueAnimator animator;
    private Config config = new Config();
    private boolean isPausing = false;
    private VolumeControl volumeControl;

    public interface PauseInner {
        void innerPause();
    }

    public interface PlayInner {
        void innerPlay();
    }

    public interface SeekInner {
        void innerSeek(long j, int i);
    }

    public interface VolumeControl {
        float getVolume();

        void setVolume(float f);
    }

    public static WanosFadeInOutManager getInstance() {
        if (mInstance == null) {
            mInstance = new WanosFadeInOutManager();
        }
        return mInstance;
    }

    public void init(VolumeControl volumeControl) {
        Log.e(TAG, "init...");
        this.volumeControl = volumeControl;
    }

    public void setConfig(Config config) {
        Log.e(TAG, "set config...");
        this.config = config;
    }

    public void play(PlayInner playInner) {
        Log.e(TAG, "play...");
        playInner.innerPlay();
        VolumeControl volumeControl = this.volumeControl;
        if (volumeControl != null) {
            volumeGradient(0.0f, volumeControl.getVolume(), null, false);
        }
    }

    public boolean isPausing() {
        return this.isPausing;
    }

    public void pause(final PauseInner pauseInner) {
        this.isPausing = true;
        Log.e(TAG, "pause...");
        VolumeControl volumeControl = this.volumeControl;
        if (volumeControl != null) {
            volumeGradient(volumeControl.getVolume(), 0.0f, new VolumeGradientCallback() { // from class: com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager.1
                @Override // com.wanos.wanosplayermodule.audioTrack.drouteclient.VolumeGradientCallback
                public void onGradEnd() {
                    WanosFadeInOutManager.this.isPausing = false;
                    pauseInner.innerPause();
                }
            }, true);
        } else {
            this.isPausing = false;
        }
    }

    public void seek(final SeekInner seekInner, boolean z, final long j, final int i) {
        Log.e(TAG, "seek...");
        VolumeControl volumeControl = this.volumeControl;
        if (volumeControl != null) {
            if (z) {
                volumeGradient(volumeControl.getVolume(), 0.0f, new VolumeGradientCallback() { // from class: com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager.2
                    @Override // com.wanos.wanosplayermodule.audioTrack.drouteclient.VolumeGradientCallback
                    public void onGradEnd() {
                        try {
                            Thread.sleep(WanosFadeInOutManager.this.config.seekBefore);
                            seekInner.innerSeek(j, i);
                            try {
                                Thread.sleep(WanosFadeInOutManager.this.config.seekAfter);
                                WanosFadeInOutManager wanosFadeInOutManager = WanosFadeInOutManager.this;
                                wanosFadeInOutManager.volumeGradient(0.0f, wanosFadeInOutManager.volumeControl.getVolume(), new VolumeGradientCallback() { // from class: com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager.2.1
                                    @Override // com.wanos.wanosplayermodule.audioTrack.drouteclient.VolumeGradientCallback
                                    public void onGradEnd() {
                                        Log.e(WanosFadeInOutManager.TAG, "seek...end");
                                    }
                                }, false);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        } catch (InterruptedException e2) {
                            throw new RuntimeException(e2);
                        }
                    }
                }, false);
            } else {
                seekInner.innerSeek(j, i);
                volumeGradient(0.0f, this.volumeControl.getVolume(), null, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void volumeGradient(final float f, float f2, final VolumeGradientCallback volumeGradientCallback, final boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, f2);
        this.animator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(this.config.animInterval);
        this.animator.setInterpolator(new LinearInterpolator());
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                float fFloatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                try {
                    System.out.println("-------------------------volume " + fFloatValue);
                    WanosFadeInOutManager.this.volumeControl.setVolume(fFloatValue);
                } catch (Exception unused) {
                    valueAnimator2.cancel();
                }
            }
        });
        this.animator.addListener(new AnimatorListenerAdapter() { // from class: com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                VolumeGradientCallback volumeGradientCallback2 = volumeGradientCallback;
                if (volumeGradientCallback2 != null) {
                    volumeGradientCallback2.onGradEnd();
                }
                if (z) {
                    WanosFadeInOutManager.this.volumeControl.setVolume(f);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                VolumeGradientCallback volumeGradientCallback2 = volumeGradientCallback;
                if (volumeGradientCallback2 != null) {
                    volumeGradientCallback2.onGradEnd();
                }
                if (z) {
                    WanosFadeInOutManager.this.volumeControl.setVolume(f);
                }
            }
        });
        this.animator.start();
    }
}
