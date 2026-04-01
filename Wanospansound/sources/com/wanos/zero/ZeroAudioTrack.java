package com.wanos.zero;

import android.media.AudioFormat;
import android.media.AudioTrack;
import android.util.Log;
import com.wanos.util.Constant;
import com.wanos.util.NativeMethod;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
class ZeroAudioTrack {
    private static ZeroAudioTrack INSTANCE = null;
    private static final int MEDIA_STATE_DESTROY = 504;
    private static final int MEDIA_STATE_IDLE = 501;
    static final int MEDIA_STATE_PLAY = 502;
    private static final int MEDIA_STATE_STOP = 503;
    public static final int SAMPLE_RATE_IN_HZ = 48000;
    private static final String TAG = "wanos[Zero]-ZeroAudioTrack";
    private final AudioTrack mAudioTrack;
    private final short[] tmpBufferShort = new short[Constant.BUFFERSIZE * Constant.getPlayerChannelNum()];
    private int mMediaState = 501;

    @Retention(RetentionPolicy.SOURCE)
    private @interface MediaState {
    }

    private ZeroAudioTrack() {
        int playerLayout = Constant.getPlayerLayout();
        int minBufferSize = AudioTrack.getMinBufferSize(48000, playerLayout, 2);
        this.mAudioTrack = new AudioTrack(ZeroAudioFocusTools.AUDIO_ATTRIBUTES, new AudioFormat.Builder().setSampleRate(48000).setEncoding(2).setChannelMask(playerLayout).build(), minBufferSize, 1, 0);
        Log.d(TAG, "ZeroAudioTrack: AudioTrack BufferSize=" + minBufferSize + ", AudioFormat = 2");
    }

    static ZeroAudioTrack getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ZeroAudioTrack();
        }
        return INSTANCE;
    }

    void setAudioTrackFrameListener(AudioTrack.OnPlaybackPositionUpdateListener onPlaybackPositionUpdateListener) {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack == null || onPlaybackPositionUpdateListener == null) {
            Log.e(TAG, "setAudioTrackFrameListener: mAudioTrack=" + this.mAudioTrack + ", callback=" + onPlaybackPositionUpdateListener);
            return;
        }
        audioTrack.setPlaybackPositionUpdateListener(onPlaybackPositionUpdateListener);
        this.mAudioTrack.setPositionNotificationPeriod(1024);
        this.mAudioTrack.setNotificationMarkerPosition(Constant.BUFFERSIZE);
    }

    void onAudioStart() {
        int i;
        if (checkAudioTrackState() || (i = this.mMediaState) == 504 || i == 502) {
            return;
        }
        NativeMethod.getInstance().freeWanosRender();
        NativeMethod.getInstance().initWanosRender(30, 0, 1024, 48000, 0, Constant.getPlayerLayoutName());
        this.mAudioTrack.play();
        this.mMediaState = 502;
        Log.d(TAG, "onAudioStart: 调用AudioTrack开始播放");
    }

    void onAudioStop() {
        if (!checkAudioTrackState() && this.mMediaState == 502) {
            this.mMediaState = 503;
            this.mAudioTrack.stop();
            Log.d(TAG, "onAudioStop: 调用AudioTrack停止播放");
        }
    }

    void onDestroy() {
        if (checkAudioTrackState()) {
            return;
        }
        if (this.mMediaState == 502) {
            this.mAudioTrack.stop();
        }
        this.mMediaState = 504;
        NativeMethod.getInstance().freeWanosRender();
        this.mAudioTrack.release();
        this.mAudioTrack.setPlaybackPositionUpdateListener(null);
        INSTANCE = null;
        Log.d(TAG, "onDestroy: 销毁AudioTrack");
    }

    private boolean checkAudioTrackState() {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack == null) {
            Log.e(TAG, "checkAudioTrackState: AudioTrack未创建");
            return true;
        }
        if (audioTrack.getState() == 1) {
            return false;
        }
        Log.e(TAG, "checkAudioTrackState: AudioTrack未准备就绪，不可用");
        return true;
    }

    int getMediaState() {
        return this.mMediaState;
    }

    void onAudioWrite(float[] fArr) {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack == null || this.mMediaState != 502) {
            return;
        }
        audioTrack.write(fArr, 0, fArr.length, 0);
    }

    void onAudioWriteForInt(float[] fArr) {
        if (this.mAudioTrack == null || this.mMediaState != 502) {
            return;
        }
        for (int i = 0; i < fArr.length; i++) {
            float f = fArr[i];
            if (f > 0.99996948d) {
                fArr[i] = 0.9999695f;
            } else if (f < -0.99996948d) {
                fArr[i] = -0.9999695f;
            }
            this.tmpBufferShort[i] = (short) (fArr[i] * 32767.0f);
        }
        AudioTrack audioTrack = this.mAudioTrack;
        short[] sArr = this.tmpBufferShort;
        audioTrack.write(sArr, 0, sArr.length, 0);
    }

    public short[] formatToInt(float[] fArr) {
        short[] sArr = new short[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            float f = fArr[i];
            if (f > 0.99996948d) {
                fArr[i] = 0.9999695f;
            } else if (f < -0.99996948d) {
                fArr[i] = -0.9999695f;
            }
            sArr[i] = (short) (fArr[i] * 32767.0f);
        }
        return sArr;
    }
}
