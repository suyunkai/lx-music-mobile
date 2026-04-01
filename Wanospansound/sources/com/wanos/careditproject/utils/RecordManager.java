package com.wanos.careditproject.utils;

import android.media.AudioDeviceInfo;
import android.media.AudioRecord;

/* JADX INFO: loaded from: classes3.dex */
public class RecordManager {
    private static final int DEFAULT_AUDIO_FORMAT = 2;
    private static final int DEFAULT_AUDIO_SOURCE = 1;
    private static final int DEFAULT_CHANNEL_CONFIG = 16;
    public static final int DEFAULT_SAMPLING_RATE = 48000;
    protected static RecordManager mInstance;
    private AudioRecord mAudioRecord;
    private int mBufferSize;
    private byte[] mPCMBuffer;
    private AudioDeviceInfo mAudioDeviceInfo = null;
    private boolean mIsRecording = false;

    RecordManager() {
    }

    public static RecordManager getInstance() {
        if (mInstance == null) {
            mInstance = new RecordManager();
        }
        return mInstance;
    }

    protected void createRecord(int i) {
        int minBufferSize = AudioRecord.getMinBufferSize(48000, 16, 2);
        int i2 = EditingUtils.encodeStep * EditingUtils.sizeOfShort;
        this.mBufferSize = i2;
        if (minBufferSize > i2) {
            this.mBufferSize = minBufferSize;
        }
        EditingUtils.log("mBufferSize:" + this.mBufferSize);
        this.mAudioRecord = new AudioRecord(i, 48000, 16, 2, this.mBufferSize);
        this.mPCMBuffer = new byte[this.mBufferSize];
    }

    public boolean start(int i) {
        createRecord(i);
        AudioDeviceInfo audioDeviceInfo = this.mAudioDeviceInfo;
        if (audioDeviceInfo != null) {
            this.mAudioRecord.setPreferredDevice(audioDeviceInfo);
        }
        try {
            this.mAudioRecord.startRecording();
            this.mIsRecording = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.mIsRecording = false;
            return false;
        }
    }

    public byte[] read() {
        int i = this.mAudioRecord.read(this.mPCMBuffer, 0, this.mBufferSize);
        if (i == -3 || i == -2) {
            stop();
            return null;
        }
        return this.mPCMBuffer;
    }

    public int read(byte[] bArr, int i) {
        int i2 = this.mAudioRecord.read(bArr, 0, i);
        if (i2 != -3 && i2 != -2) {
            return i2;
        }
        stop();
        return i2;
    }

    public boolean isRecording() {
        return this.mIsRecording;
    }

    public void stop() {
        try {
            this.mIsRecording = false;
            this.mAudioRecord.stop();
            this.mAudioRecord.release();
            this.mAudioRecord = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDevice(AudioDeviceInfo audioDeviceInfo) {
        this.mAudioDeviceInfo = audioDeviceInfo;
    }
}
