package com.wanos.mediacenter.utils;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;

/* JADX INFO: loaded from: classes3.dex */
public class AudioFocusUtils {
    private static AudioFocusRequest audioFocusRequest;
    private static AudioManager audioManager;
    private static OnAudioFocusListener onAudioFocusListener;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.wanos.mediacenter.utils.AudioFocusUtils.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i == -3) {
                if (AudioFocusUtils.onAudioFocusListener != null) {
                    AudioFocusUtils.onAudioFocusListener.onAudioFocusLostTransientCanDuck();
                }
            } else if (i == -2) {
                if (AudioFocusUtils.onAudioFocusListener != null) {
                    AudioFocusUtils.onAudioFocusListener.onAudioFocusLostTransient();
                }
            } else {
                if (i != -1) {
                    if (i == 1 && AudioFocusUtils.onAudioFocusListener != null) {
                        AudioFocusUtils.onAudioFocusListener.onAudioFocusGain();
                        return;
                    }
                    return;
                }
                if (AudioFocusUtils.onAudioFocusListener != null) {
                    AudioFocusUtils.onAudioFocusListener.onAudioFocusLost();
                }
            }
        }
    };

    public interface OnAudioFocusListener {
        void onAudioFocusGain();

        void onAudioFocusLost();

        void onAudioFocusLostTransient();

        void onAudioFocusLostTransientCanDuck();
    }

    public AudioFocusUtils(Context context) {
        if (audioManager == null) {
            audioManager = (AudioManager) context.getSystemService("audio");
            audioFocusRequest = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).setAcceptsDelayedFocusGain(true).setWillPauseWhenDucked(true).setOnAudioFocusChangeListener(this.onAudioFocusChangeListener).build();
        }
    }

    public int requestAudioFocus() {
        return audioManager.requestAudioFocus(this.onAudioFocusChangeListener, 3, 2);
    }

    public void releaseAudioFocus() {
        if (audioManager != null) {
            audioManager.requestAudioFocus(audioFocusRequest);
        }
    }

    public static void setOnAudioFocusListener(OnAudioFocusListener onAudioFocusListener2) {
        onAudioFocusListener = onAudioFocusListener2;
    }
}
