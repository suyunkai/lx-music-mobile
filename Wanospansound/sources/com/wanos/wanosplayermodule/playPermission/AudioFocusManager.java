package com.wanos.wanosplayermodule.playPermission;

import android.app.Application;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.commonlibrary.mediaCenter.AudioConfig;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanos.wanosplayermodule.util.MediaSharedPreferUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AudioFocusManager {
    private static final String TAG = "wanos:[AudioFocusManager]";
    private final Handler handler;
    MediaPlayerService mMediaPlayerService;
    AudioFocusRequest mRequestAudioFocus;
    AudioManager manager;
    private Runnable runnable;
    public int AUDIO_FOCUS_TYPE = 17;
    AudioManager.OnAudioFocusChangeListener listener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.wanos.wanosplayermodule.playPermission.AudioFocusManager.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            Log.d(AudioFocusManager.TAG, "focusChange:" + i);
            if (i == -2) {
                Log.d(AudioFocusManager.TAG, "短暂失去焦点");
                AudioFocusManager.this.AUDIO_FOCUS_TYPE = -2;
                ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.wanosplayermodule.playPermission.AudioFocusManager.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AudioFocusManager.this.playerServicePause(true);
                    }
                });
                AudioFocusManager.this.wanOSOnOff();
                AudioFocusManager.this.runnable = new Runnable() { // from class: com.wanos.wanosplayermodule.playPermission.AudioFocusManager.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                    }
                };
                AudioFocusManager.this.handler.postDelayed(AudioFocusManager.this.runnable, 200L);
                return;
            }
            if (i == -1) {
                AudioFocusManager.this.AUDIO_FOCUS_TYPE = -1;
                ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.wanosplayermodule.playPermission.AudioFocusManager.1.4
                    @Override // java.lang.Runnable
                    public void run() {
                        AudioFocusManager.this.playerServicePause(false);
                    }
                });
                AudioFocusManager.this.wanOSOnOff();
                Log.d(AudioFocusManager.TAG, "长久失去焦点");
                return;
            }
            if (i != 1) {
                return;
            }
            Log.d(AudioFocusManager.TAG, "重新获取焦点");
            AudioFocusManager.this.AUDIO_FOCUS_TYPE = 1;
            ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.wanosplayermodule.playPermission.AudioFocusManager.1.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AudioFocusManager.this.mMediaPlayerService == null) {
                        AudioFocusManager.this.mMediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
                        if (AudioFocusManager.this.mMediaPlayerService != null) {
                            AudioFocusManager.this.mMediaPlayerService.start();
                            return;
                        }
                        return;
                    }
                    AudioFocusManager.this.mMediaPlayerService.start();
                }
            });
            AudioFocusManager.this.wanOSOnOn();
        }
    };

    public AudioFocusManager(MediaPlayerService mediaPlayerService) {
        this.mMediaPlayerService = mediaPlayerService;
        setCallbackListener();
        this.handler = new Handler();
        this.runnable = null;
    }

    public boolean audioFocusRequest() {
        AudioFocusRequest audioFocusRequest;
        initManagerAndAudioFocus();
        AudioManager audioManager = this.manager;
        if (audioManager != null && (audioFocusRequest = this.mRequestAudioFocus) != null) {
            int iRequestAudioFocus = audioManager.requestAudioFocus(audioFocusRequest);
            Log.d(TAG, "audioFocusRequest----ret:" + iRequestAudioFocus);
            if (iRequestAudioFocus == 1) {
                Log.d(TAG, "焦点申请成功----ret:" + iRequestAudioFocus);
                wanOSOnOn();
                return true;
            }
            Log.d(TAG, "焦点申请失败2----manager:" + (this.manager == null) + "----mRequestAudioFocus:" + (this.mRequestAudioFocus == null));
            wanOSOnOff();
            return false;
        }
        Log.d(TAG, "焦点申请失败1----manager:" + (this.manager == null) + "----mRequestAudioFocus:" + (this.mRequestAudioFocus == null));
        wanOSOnOff();
        return false;
    }

    public void initManagerAndAudioFocus() {
        AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setContentType(2).setUsage(1).build();
        Application application = CommonUtils.getApplication();
        if (this.manager == null) {
            this.manager = (AudioManager) application.getSystemService("audio");
        }
        if (this.mRequestAudioFocus == null) {
            this.mRequestAudioFocus = new AudioFocusRequest.Builder(1).setOnAudioFocusChangeListener(this.listener).setAudioAttributes(audioAttributesBuild).setAcceptsDelayedFocusGain(true).build();
        }
    }

    public void playerServicePause(boolean z) {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            MediaPlayerService mediaPlayerService2 = MediaPlayEngine.getInstance().getMediaPlayerService();
            this.mMediaPlayerService = mediaPlayerService2;
            if (mediaPlayerService2 == null || !mediaPlayerService2.isPlaying()) {
                return;
            }
            this.mMediaPlayerService.pause(z);
            Log.i(TAG, "playerServicePause: 暂停1");
            return;
        }
        if (mediaPlayerService.isPlaying()) {
            this.mMediaPlayerService.pause(z);
            Log.d(TAG, "暂停");
        }
    }

    public void abandonAudioFocus() {
        if (this.manager != null && this.mRequestAudioFocus != null) {
            Log.d(TAG, "释放焦点");
            this.manager.abandonAudioFocusRequest(this.mRequestAudioFocus);
        } else {
            initManagerAndAudioFocus();
            this.manager.abandonAudioFocusRequest(this.mRequestAudioFocus);
        }
        wanOSOnOff();
    }

    public void setCallbackListener() {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService != null) {
            mediaPlayerService.addOnStatusCallbackListener(new OnStatusCallbackListener() { // from class: com.wanos.wanosplayermodule.playPermission.AudioFocusManager.2
                @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
                public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
                    if (callBackState == MediaPlayerEnum.CallBackState.PAUSE || callBackState == MediaPlayerEnum.CallBackState.COMPLETE) {
                        Log.i(AudioFocusManager.TAG, "onStatusonStatusCallbackNext: AUDIO_FOCUS_TYPE = " + AudioFocusManager.this.AUDIO_FOCUS_TYPE);
                        if (AudioFocusManager.this.AUDIO_FOCUS_TYPE == -2) {
                            Log.i(AudioFocusManager.TAG, "onStatusonStatusCallbackNext:AUDIOFOCUS_LOSS_TRANSIENT : 暂时失去焦点");
                            return;
                        }
                        if (AudioFocusManager.this.AUDIO_FOCUS_TYPE == -1) {
                            Log.i(AudioFocusManager.TAG, "onStatusonStatusCallbackNext: AUDIOFOCUS_LOSS 永久失去音频焦点");
                        }
                        AudioFocusManager.this.abandonAudioFocus();
                    }
                }
            });
        } else {
            Log.d(TAG, "没有获取播放器实例");
        }
    }

    public void wanOSOnOn() {
        toggleWanosEffect(true);
    }

    public void toggleWanosEffect(boolean z) {
        if (this.manager == null || !AudioConfig.isNeedConfigAudioPar) {
            return;
        }
        Log.i(TAG, "eff value 2:" + z);
        this.manager.setParameters(String.format("wanos_effect=(%s)", Integer.valueOf(z ? 1 : 0)));
    }

    public void wanOSOnOff() {
        toggleWanosEffect(false);
    }

    public void hasDisplayOffMessages() {
        if (Build.VERSION.SDK_INT >= 29 ? this.handler.hasCallbacks(this.runnable) : false) {
            System.out.println("wanos: 保存播放状态是true");
            MediaSharedPreferUtils.putIsPlaying(true);
        }
        this.handler.removeCallbacks(this.runnable);
    }
}
