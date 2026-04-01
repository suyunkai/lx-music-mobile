package com.wanos.careditproject.utils.exPlayer;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.util.Log;
import com.blankj.utilcode.util.Utils;
import com.wanos.careditproject.data.bean.AiAudioFocusState;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.PlayerUtils;

/* JADX INFO: loaded from: classes3.dex */
class AiExPlayMediaHelp {
    private static final String TAG = "Wanos[AI]-AiExPlayMediaHelp";
    private final AudioAttributes audioAttributes;
    private final AudioFocusRequest focusRequest;
    private final IAudioFocusChange iAudioFocusChange;
    private final AudioManager.OnAudioFocusChangeListener mAudioFocusListener;
    private boolean mAudioFocusState;
    private final AudioManager mAudioManger;
    private PlayerUtils.PlayerListener mPlayerCallback = null;
    private AiAudioFocusState state = AiAudioFocusState.DEFAULT;

    public interface IAudioFocusChange {
        void onAudioFocusGain();

        void onAudioFocusLoss();
    }

    public AiExPlayMediaHelp(IAudioFocusChange iAudioFocusChange) {
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPlayMediaHelp.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                if (i == -2) {
                    Log.i(AiExPlayMediaHelp.TAG, "onAudioFocusChange: 暂时失去焦点");
                    AiExPlayMediaHelp.this.mAudioFocusState = false;
                    if (AiExPlayMediaHelp.this.iAudioFocusChange == null || !PlayerUtils.isPlaying()) {
                        return;
                    }
                    AiExPlayMediaHelp.this.iAudioFocusChange.onAudioFocusLoss();
                    return;
                }
                if (i == -1) {
                    Log.i(AiExPlayMediaHelp.TAG, "onAudioFocusChange: 永久失去焦点");
                    AiExPlayMediaHelp.this.mAudioFocusState = false;
                    if (AiExPlayMediaHelp.this.iAudioFocusChange == null || !PlayerUtils.isPlaying()) {
                        return;
                    }
                    AiExPlayMediaHelp.this.iAudioFocusChange.onAudioFocusLoss();
                    return;
                }
                if (i != 1) {
                    return;
                }
                Log.i(AiExPlayMediaHelp.TAG, "onAudioFocusChange: 重新获取到焦点， state = " + AiExPlayMediaHelp.this.state);
                AiExPlayMediaHelp.this.mAudioFocusState = true;
                if (AiExPlayMediaHelp.this.iAudioFocusChange == null || AiExPlayMediaHelp.this.state != AiAudioFocusState.FOCUS) {
                    return;
                }
                AiExPlayMediaHelp.this.iAudioFocusChange.onAudioFocusGain();
            }
        };
        this.mAudioFocusListener = onAudioFocusChangeListener;
        AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
        this.audioAttributes = audioAttributesBuild;
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(1).setAudioAttributes(audioAttributesBuild).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(onAudioFocusChangeListener).build();
        this.focusRequest = audioFocusRequestBuild;
        this.iAudioFocusChange = iAudioFocusChange;
        AudioManager audioManager = (AudioManager) Utils.getApp().getSystemService("audio");
        this.mAudioManger = audioManager;
        int iRequestAudioFocus = audioManager.requestAudioFocus(audioFocusRequestBuild);
        this.mAudioFocusState = iRequestAudioFocus == 1;
        Log.i(TAG, "AiExPlayMediaHelp: 音频焦点请求结果 result = " + iRequestAudioFocus + ", mAudioFocusState = " + this.mAudioFocusState);
    }

    public int initAndPlayer(PlayerUtils.PlayerListener playerListener) {
        this.mPlayerCallback = playerListener;
        int msBySampleNum = EditingUtils.getMsBySampleNum(DataHelpAudioTrack.getMaxSampleNum());
        setProgressSampleNum(0);
        if (start()) {
            return msBySampleNum;
        }
        return -1;
    }

    public boolean start() {
        if (this.mPlayerCallback == null) {
            Log.e(TAG, "start: 未初始化播放器");
            return false;
        }
        if (!this.mAudioFocusState) {
            Log.i(TAG, "请求音频焦点");
            this.mAudioFocusState = this.mAudioManger.requestAudioFocus(this.focusRequest) == 1;
        }
        if (!this.mAudioFocusState) {
            Log.e(TAG, "未请求到音频焦点");
            return false;
        }
        this.state = AiAudioFocusState.DEFAULT;
        PlayerUtils.play(this.mPlayerCallback, true, true, false, true);
        return true;
    }

    public void stop() {
        if (this.mPlayerCallback == null) {
            Log.e(TAG, "stop: 未初始化播放器");
        } else {
            this.state = AiAudioFocusState.USER;
            PlayerUtils.stop(false);
        }
    }

    public void stop(AiAudioFocusState aiAudioFocusState) {
        if (this.mPlayerCallback == null) {
            Log.e(TAG, "stop: 未初始化播放器");
        } else {
            this.state = aiAudioFocusState;
            PlayerUtils.stop(false);
        }
    }

    public void reset() {
        if (this.mPlayerCallback == null) {
            Log.e(TAG, "stop: 未初始化播放器");
        } else {
            PlayerUtils.stop(true);
        }
    }

    public void setProgressSampleNum(int i) {
        if (this.mPlayerCallback == null) {
            return;
        }
        EditingParams.getInstance().setProgressSampleNumPreviewAi(EditingUtils.getSampleNumByMs(i));
    }

    public void onCleared() {
        PlayerUtils.stop(false);
        this.mAudioManger.abandonAudioFocusRequest(this.focusRequest);
    }
}
