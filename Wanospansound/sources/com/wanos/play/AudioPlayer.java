package com.wanos.play;

import android.media.AudioTrack;
import com.wanos.util.Constant;
import com.wanos.util.NativeMethod;

/* JADX INFO: loaded from: classes3.dex */
public class AudioPlayer {
    private static final int mAudioFormat = 2;
    private static AudioTrack mAudioTrack = null;
    private static int mChannelConfig = 12;
    private static AudioPlayer mInstance = null;
    private static int mMode = 1;
    private static final int mSampleRateInHz = 48000;
    private static final int mStreamType = 3;
    private int mMinBufferSize;
    private boolean panIsInit = false;

    private void audioTrackListener() {
    }

    public static int getSampleRateInHz() {
        return 48000;
    }

    public static AudioPlayer getInstance() {
        if (mInstance == null) {
            mInstance = new AudioPlayer();
            int playerLayout = Constant.getPlayerLayout();
            mChannelConfig = playerLayout;
            mInstance.create(playerLayout);
        }
        return mInstance;
    }

    public static void deleteInstance() {
        AudioPlayer audioPlayer = mInstance;
        if (audioPlayer != null) {
            audioPlayer.release();
        }
    }

    public static AudioTrack getmAudioTrack() {
        return mAudioTrack;
    }

    public void create(int i) {
        this.mMinBufferSize = AudioTrack.getMinBufferSize(48000, mChannelConfig, 2);
        mChannelConfig = i;
        mAudioTrack = new AudioTrack(3, 48000, mChannelConfig, 2, this.mMinBufferSize, mMode);
    }

    public int getMinBufferSize() {
        return this.mMinBufferSize;
    }

    public void initPan() {
        if (this.panIsInit) {
            return;
        }
        NativeMethod.getInstance().initWanosRender(30, 0, 1024, 48000, 0, Constant.getPlayerLayoutName());
        this.panIsInit = true;
    }

    public void freePan() {
        NativeMethod.getInstance().freeWanosRender();
        this.panIsInit = false;
    }

    public boolean isPanIsInit() {
        return this.panIsInit;
    }

    public void play() {
        if (mAudioTrack != null) {
            audioTrackListener();
            mAudioTrack.play();
        } else {
            mAudioTrack = new AudioTrack(3, 48000, mChannelConfig, 2, this.mMinBufferSize, mMode);
            audioTrackListener();
            mAudioTrack.play();
        }
    }

    public void setAudioTrackFrameListener(AudioTrack.OnPlaybackPositionUpdateListener onPlaybackPositionUpdateListener) {
        AudioTrack audioTrack = mAudioTrack;
        if (audioTrack == null || onPlaybackPositionUpdateListener == null) {
            return;
        }
        audioTrack.setPlaybackPositionUpdateListener(onPlaybackPositionUpdateListener);
        mAudioTrack.setPositionNotificationPeriod(1024);
        mAudioTrack.setNotificationMarkerPosition(Constant.BUFFERSIZE);
    }

    public void write(short[] sArr) {
        if (sArr == null) {
            return;
        }
        int length = sArr.length;
        AudioTrack audioTrack = mAudioTrack;
        if (audioTrack == null || audioTrack.getPlayState() != 3) {
            return;
        }
        mAudioTrack.write(sArr, 0, length, 0);
    }

    public void write(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        int length = bArr.length;
        AudioTrack audioTrack = mAudioTrack;
        if (audioTrack == null || audioTrack.getPlayState() != 3) {
            return;
        }
        mAudioTrack.write(bArr, 0, length, 0);
    }

    public void stop() {
        AudioTrack audioTrack = mAudioTrack;
        if (audioTrack != null) {
            audioTrack.stop();
        }
    }

    public void release() {
        AudioTrack audioTrack = mAudioTrack;
        if (audioTrack != null) {
            audioTrack.release();
        }
        mAudioTrack = null;
    }
}
