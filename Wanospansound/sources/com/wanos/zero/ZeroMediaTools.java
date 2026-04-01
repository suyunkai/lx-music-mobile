package com.wanos.zero;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroMediaTools {
    private static final String TAG = "wanos[Zero]ZeroMediaTools";
    private final ZeroPlayEngine mZeroMediaPlayer;

    public ZeroMediaTools() {
        Log.i(TAG, "ZeroMediaTools: 构造播放器引擎");
        ZeroPlayEngine zeroPlayEngine = new ZeroPlayEngine();
        this.mZeroMediaPlayer = zeroPlayEngine;
        zeroPlayEngine.start();
    }

    public void onZeroActivityDestroy() {
        Log.i(TAG, "onZeroActivityDestroy: 销毁播放器引擎");
        this.mZeroMediaPlayer.setMediaDestroy();
    }

    public void onMediaInsertBall(BallEntity ballEntity) {
        Log.i(TAG, "onMediaInsertBall: 添加音源，音源ID = " + ballEntity);
        this.mZeroMediaPlayer.setMediaInsert(ballEntity);
    }

    public void onMediaDeleteBall(int i) {
        Log.i(TAG, "onMediaDeleteBall: 删除音源，音源ID = " + i);
        this.mZeroMediaPlayer.setMediaDelete(i);
    }

    public void onMediaModifyXY(int i, float f, float f2) {
        this.mZeroMediaPlayer.setMediaModifyPosXY(i, f, f2);
    }

    public void onMediaModifyZ(int i, float f) {
        this.mZeroMediaPlayer.setMediaModifyPosZ(i, f);
    }

    public void onMediaReset() {
        Log.i(TAG, "onMediaReset: 重置播放器");
        this.mZeroMediaPlayer.setMediaReset();
    }

    public void onMediaReplay() {
        Log.i(TAG, "onMediaReplay: 重新播放");
        this.mZeroMediaPlayer.setMediaReplay();
    }

    public void onMediaStop() {
        Log.i(TAG, "onMediaStop: 暂停播放");
        this.mZeroMediaPlayer.setStopMedia();
    }

    public void onMediaStart() {
        Log.i(TAG, "onMediaStart: 开始播放");
        this.mZeroMediaPlayer.setStartMedia();
    }

    public int getPlayState() {
        return this.mZeroMediaPlayer.getPlayState();
    }

    public void onMediaVolume(int i, float f) {
        this.mZeroMediaPlayer.setMediaVolume(i, f);
    }

    public void setMediaFrameCallback(IAudioFrameCallback iAudioFrameCallback) {
        this.mZeroMediaPlayer.setMediaFrameCallback(iAudioFrameCallback);
    }

    public float[] getBallCurrentPos(int i) {
        return this.mZeroMediaPlayer.getMediaBallPos(i);
    }
}
