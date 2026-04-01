package com.wanos.zero;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroAudioFocusTools {
    public static final AudioAttributes AUDIO_ATTRIBUTES = new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
    private static volatile ZeroAudioFocusTools INSTANCE = null;
    private static final String TAG = "wanos[Zero]-ZeroAudioFocusTools";
    private final MutableLiveData<AudioFocusState> audioFocusState = new MutableLiveData<>(AudioFocusState.DEFAULT);
    private final AudioManager mAudioManager = (AudioManager) Utils.getApp().getSystemService("audio");
    private final AudioFocusRequest mAudioFocusRequest = new AudioFocusRequest.Builder(1).setAudioAttributes(AUDIO_ATTRIBUTES).setWillPauseWhenDucked(true).setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() { // from class: com.wanos.zero.ZeroAudioFocusTools$$ExternalSyntheticLambda0
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public final void onAudioFocusChange(int i) {
            this.f$0.m704lambda$new$0$comwanoszeroZeroAudioFocusTools(i);
        }
    }).build();

    public enum AudioFocusState {
        GAIN,
        LOSS,
        LOSS_TRANSIENT,
        LOSS_TRANSIENT_CAN_DUCK,
        DEFAULT
    }

    public boolean releaseAudioFocus() {
        return true;
    }

    public static ZeroAudioFocusTools getInstance() {
        if (INSTANCE == null) {
            synchronized (ZeroAudioFocusTools.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ZeroAudioFocusTools();
                }
            }
        }
        return INSTANCE;
    }

    private ZeroAudioFocusTools() {
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-zero-ZeroAudioFocusTools, reason: not valid java name */
    /* synthetic */ void m704lambda$new$0$comwanoszeroZeroAudioFocusTools(int i) {
        if (i == -3) {
            Log.i(TAG, "ZeroAudioFocusTools: 临时丢失音频焦点");
            this.audioFocusState.postValue(AudioFocusState.LOSS_TRANSIENT_CAN_DUCK);
            return;
        }
        if (i == -2) {
            Log.i(TAG, "ZeroAudioFocusTools: 暂时丢失音频焦点");
            this.audioFocusState.postValue(AudioFocusState.LOSS_TRANSIENT);
        } else if (i == -1) {
            Log.i(TAG, "ZeroAudioFocusTools: 永久丢失音频焦点");
            this.audioFocusState.postValue(AudioFocusState.LOSS);
        } else {
            if (i != 1) {
                return;
            }
            Log.i(TAG, "ZeroAudioFocusTools: 获取到音频焦点");
            this.audioFocusState.postValue(AudioFocusState.GAIN);
        }
    }

    public LiveData<AudioFocusState> getAudioFocusState() {
        return this.audioFocusState;
    }

    public boolean requestAudioFocus() {
        if (1 != this.mAudioManager.requestAudioFocus(this.mAudioFocusRequest)) {
            return false;
        }
        this.audioFocusState.setValue(AudioFocusState.GAIN);
        return true;
    }

    public void setSpeakerphone() {
        this.mAudioManager.setSpeakerphoneOn(true);
    }

    public boolean isCanFocus() {
        return this.audioFocusState.getValue() == AudioFocusState.GAIN || this.audioFocusState.getValue() == AudioFocusState.LOSS_TRANSIENT_CAN_DUCK;
    }

    public boolean isCallActive() {
        return ((AudioManager) Utils.getApp().getSystemService("audio")).getMode() != 0;
    }
}
