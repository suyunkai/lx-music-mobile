package com.wanos.wanosplayermodule.audioTrack;

import android.content.Context;
import android.media.AudioManager;

/* JADX INFO: loaded from: classes3.dex */
public class AudioFocus {
    private AudioManager mAudioManager;
    public OnAudioFocusListener mFocusListener;
    private int mVolumeWhenFocusLossTransientCanDuck;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.wanos.wanosplayermodule.audioTrack.AudioFocus.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i == -3) {
                if (AudioFocus.this.mFocusListener != null) {
                    int streamVolume = AudioFocus.this.mAudioManager.getStreamVolume(3);
                    if (streamVolume > 0) {
                        AudioFocus.this.mVolumeWhenFocusLossTransientCanDuck = streamVolume;
                        AudioFocus.this.mAudioManager.setStreamVolume(3, AudioFocus.this.mVolumeWhenFocusLossTransientCanDuck / 2, 8);
                    }
                    AudioFocus.this.mFocusListener.onFocusStart();
                    return;
                }
                return;
            }
            if (i == -2) {
                if (AudioFocus.this.mFocusListener != null) {
                    AudioFocus.this.mFocusListener.onFocusStop();
                    return;
                }
                return;
            }
            if (i == -1) {
                if (AudioFocus.this.mFocusListener != null) {
                    AudioFocus.this.mFocusListener.onFocusStop();
                }
            } else if (i == 1 || i == 2 || i == 3) {
                int streamVolume2 = AudioFocus.this.mAudioManager.getStreamVolume(3);
                if (AudioFocus.this.mVolumeWhenFocusLossTransientCanDuck > 0 && streamVolume2 == AudioFocus.this.mVolumeWhenFocusLossTransientCanDuck / 2) {
                    AudioFocus.this.mAudioManager.setStreamVolume(3, AudioFocus.this.mVolumeWhenFocusLossTransientCanDuck, 8);
                }
                if (AudioFocus.this.mFocusListener != null) {
                    AudioFocus.this.mFocusListener.onFocusStart();
                }
            }
        }
    };

    public interface OnAudioFocusListener {
        void onFocusStart();

        void onFocusStop();
    }

    public AudioFocus(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public int requestAudioFocus(OnAudioFocusListener onAudioFocusListener) {
        this.mFocusListener = onAudioFocusListener;
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            return audioManager.requestAudioFocus(this.onAudioFocusChangeListener, 3, 2);
        }
        return 1;
    }

    public void releaseAudioFocus() {
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null || (onAudioFocusChangeListener = this.onAudioFocusChangeListener) == null) {
            return;
        }
        audioManager.abandonAudioFocus(onAudioFocusChangeListener);
    }

    public void onDestroy() {
        releaseAudioFocus();
        this.mAudioManager = null;
        this.mFocusListener = null;
    }
}
