package com.wanos.util;

import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class NativeMethod {
    private static NativeMethod singleton;

    public interface IWanosDecoderListener {
        void init(long j, int i, int i2, int i3);

        void setBedBuffer(int i, byte[] bArr, int i2);

        void write(byte[] bArr, int i);
    }

    public native int audioClose(String str);

    public native int audioCloseAll();

    public native int audioIsValid(String str);

    public native int audioIsWanos(String str);

    public native int audioOpen(String str, String str2);

    public native int audioRead(String str, long j, float[] fArr, float[] fArr2);

    public native int decodeWanos(String str, IWanosDecoderListener iWanosDecoderListener);

    public native void doAudioPosHandler(float[][] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, float[] fArr6, int i, int[] iArr, int[] iArr2, float[][] fArr7);

    public native int encode(int i, float[] fArr, int i2);

    public native void encodeClose(int i);

    public native int encodeInit(int i, int i2, int i3, int i4, String str);

    public native int ffmpegDecodeAudio(String str, String str2, IWanosDecoderListener iWanosDecoderListener, int i, boolean z);

    public native void ffmpegDecodeAudioStart();

    public native void ffmpegDecodeAudioStop();

    public native void freeWanosRender();

    public native ByteBuffer getBedDataOneChannel(int i);

    public native int getOneFrameLen(byte[] bArr);

    public native int getOutResampleNum(String str, int i);

    public native int initWanosDecoder(int i);

    public native void initWanosRender(int i, int i2, int i3, int i4, int i5, String str);

    public native void initpan(String str);

    public native void playSpaceAudio(float[][] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, float[] fArr6, int i, int[] iArr, float[][] fArr7);

    public native int[] processWanosDecoder(byte[] bArr, int i);

    public native byte[] resample(String str, byte[] bArr, int i, byte[] bArr2, int i2);

    public native byte[] resampleAudio(byte[] bArr, int i, int i2, int i3, int i4, int i5);

    public native int resampleAudioClose(String str);

    public native int resampleAudioInit(String str, int i, int i2, int i3);

    public native void setObjGain(int i, float[] fArr);

    public native void setSFType(int i);

    public native float[] stereoPos(float[] fArr);

    public native String stringFromJNI();

    public static NativeMethod getInstance() {
        if (singleton == null) {
            synchronized (NativeMethod.class) {
                if (singleton == null) {
                    singleton = new NativeMethod();
                }
            }
        }
        return singleton;
    }

    static {
        IjkMediaPlayer.loadLibInit();
        System.loadLibrary("WanosPlayer");
    }

    public int ffmpegDecodeAudio(String str, String str2, IWanosDecoderListener iWanosDecoderListener, int i) {
        return ffmpegDecodeAudio(str, str2, iWanosDecoderListener, i, false);
    }
}
