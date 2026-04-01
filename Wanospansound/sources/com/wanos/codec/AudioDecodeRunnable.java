package com.wanos.codec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.view.Surface;
import com.wanos.codec.AudioCodec;
import com.wanos.codec.AudioPcmData;
import com.wanos.util.EditingUtils;
import com.wanos.util.NativeMethod;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

/* JADX INFO: loaded from: classes3.dex */
public class AudioDecodeRunnable implements Runnable {
    private static final String TAG = "AudioDecodeRunnable";
    static final int TIMEOUT_USEC = 0;
    private static final int outSampleRate = 48000;
    private int audioTrack;
    private MediaExtractor extractor;
    private String mAudioPath;
    private String mAudioUrl;
    private AudioCodec.AudioDecodeListener mListener;
    private int pcmLen = 0;

    public AudioDecodeRunnable(MediaExtractor mediaExtractor, int i, String str, String str2, AudioCodec.AudioDecodeListener audioDecodeListener) {
        this.extractor = mediaExtractor;
        this.audioTrack = i;
        this.mListener = audioDecodeListener;
        this.mAudioPath = str;
        this.mAudioUrl = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        try {
            MediaFormat trackFormat = this.extractor.getTrackFormat(this.audioTrack);
            MediaCodec mediaCodecCreateDecoderByType = MediaCodec.createDecoderByType(trackFormat.getString(IMediaFormat.KEY_MIME));
            trackFormat.setInteger("wanos", 100);
            int i7 = 0;
            mediaCodecCreateDecoderByType.configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
            int integer = trackFormat.getInteger("sample-rate");
            int integer2 = trackFormat.getInteger("channel-count");
            long j2 = trackFormat.getLong("durationUs");
            if (trackFormat.containsKey("pcm-encoding")) {
                trackFormat.getInteger("pcm-encoding");
            }
            AudioFileInfo.getInstance().add(this.mAudioUrl, integer, integer2, (((long) EditingUtils.sampleRateDefault) * j2) / 1000000);
            int i8 = integer2;
            AudioPcmData.PcmData pcmDataInit = AudioPcmData.getInstance().init(this.mAudioPath, this.mAudioUrl, integer, i8, (int) (((j2 / 1000000) + 1) * ((long) integer) * ((long) integer2) * 2), false);
            if (pcmDataInit == null) {
                AudioCodec.AudioDecodeListener audioDecodeListener = this.mListener;
                if (audioDecodeListener != null) {
                    audioDecodeListener.decodeFail(this.mAudioPath);
                    return;
                }
                return;
            }
            mediaCodecCreateDecoderByType.start();
            ByteBuffer[] inputBuffers = mediaCodecCreateDecoderByType.getInputBuffers();
            mediaCodecCreateDecoderByType.getOutputBuffers();
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            MediaCodec.BufferInfo bufferInfo2 = new MediaCodec.BufferInfo();
            FileOutputStream fileOutputStream = new FileOutputStream(pcmDataInit.pcmFilePath);
            byte[] bArr = new byte[10];
            if (integer != 48000) {
                bArr = new byte[NativeMethod.getInstance().resampleAudioInit(this.mAudioUrl, integer, 48000, i8)];
            }
            boolean z = false;
            while (!z) {
                int i9 = i7;
                while (true) {
                    j = 0;
                    i = 1;
                    if (i9 >= inputBuffers.length) {
                        break;
                    }
                    int iDequeueInputBuffer = mediaCodecCreateDecoderByType.dequeueInputBuffer(0L);
                    if (iDequeueInputBuffer >= 0) {
                        ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
                        byteBuffer.clear();
                        int sampleData = this.extractor.readSampleData(byteBuffer, i7);
                        if (sampleData < 0) {
                            i6 = i9;
                            i5 = integer;
                            mediaCodecCreateDecoderByType.queueInputBuffer(iDequeueInputBuffer, 0, 0, 0L, 4);
                        } else {
                            i5 = integer;
                            i6 = i9;
                            bufferInfo2.offset = i7;
                            bufferInfo2.size = sampleData;
                            bufferInfo2.flags = 1;
                            bufferInfo2.presentationTimeUs = this.extractor.getSampleTime();
                            mediaCodecCreateDecoderByType.queueInputBuffer(iDequeueInputBuffer, bufferInfo2.offset, sampleData, bufferInfo2.presentationTimeUs, 0);
                            this.extractor.advance();
                        }
                    } else {
                        i5 = integer;
                        i6 = i9;
                    }
                    i9 = i6 + 1;
                    integer = i5;
                }
                int i10 = integer;
                int i11 = i7;
                boolean z2 = z;
                while (i11 == 0) {
                    int iDequeueOutputBuffer = mediaCodecCreateDecoderByType.dequeueOutputBuffer(bufferInfo, j);
                    if (iDequeueOutputBuffer == -1) {
                        i3 = i8;
                        i11 = i;
                    } else {
                        if (iDequeueOutputBuffer == -3) {
                            mediaCodecCreateDecoderByType.getOutputBuffers();
                        } else if (iDequeueOutputBuffer == -2) {
                            mediaCodecCreateDecoderByType.getOutputFormat();
                        } else if (iDequeueOutputBuffer >= 0) {
                            ByteBuffer outputBuffer = mediaCodecCreateDecoderByType.getOutputBuffer(iDequeueOutputBuffer);
                            int i12 = bufferInfo.size;
                            byte[] bArr2 = new byte[i12];
                            outputBuffer.get(bArr2);
                            outputBuffer.clear();
                            this.pcmLen += bufferInfo.size;
                            i2 = i10;
                            if (i2 != 48000) {
                                int i13 = (i12 / 2) / i8;
                                int i14 = (i13 * 48000) / i2;
                                NativeMethod.getInstance().resample(this.mAudioUrl, bArr2, i13, bArr, i14);
                                int i15 = i14 * 2 * i8;
                                fileOutputStream.write(bArr, i7, i15);
                                fileOutputStream.flush();
                                i3 = i8;
                                AudioPcmData.getInstance().addWavePcm(this.mAudioUrl, bArr, i15);
                            } else {
                                i3 = i8;
                                fileOutputStream.write(bArr2);
                                fileOutputStream.flush();
                                AudioPcmData.getInstance().addWavePcm(this.mAudioUrl, bArr2, i12);
                            }
                            i4 = 0;
                            mediaCodecCreateDecoderByType.releaseOutputBuffer(iDequeueOutputBuffer, false);
                            if ((bufferInfo.flags & 4) != 0) {
                                AudioPcmData.getInstance().addWavePcm(this.mAudioUrl, bArr2, i12);
                                AudioPcmData.getInstance().closeAddWavePcm(this.mAudioUrl);
                                EditingUtils.log("AudioDecodeRunnable end size=" + this.pcmLen + ", url =" + this.mAudioUrl);
                                this.extractor.release();
                                mediaCodecCreateDecoderByType.stop();
                                mediaCodecCreateDecoderByType.release();
                                z2 = true;
                                i11 = 1;
                            }
                            i7 = i4;
                            i10 = i2;
                            i8 = i3;
                            j = 0;
                            i = 1;
                        }
                        i3 = i8;
                    }
                    i4 = i7;
                    i2 = i10;
                    i7 = i4;
                    i10 = i2;
                    i8 = i3;
                    j = 0;
                    i = 1;
                }
                z = z2;
                integer = i10;
            }
            NativeMethod.getInstance().resampleAudioClose(this.mAudioUrl);
            fileOutputStream.close();
            AudioCodec.AudioDecodeListener audioDecodeListener2 = this.mListener;
            if (audioDecodeListener2 != null) {
                audioDecodeListener2.decodeOver(this.mAudioPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            AudioCodec.AudioDecodeListener audioDecodeListener3 = this.mListener;
            if (audioDecodeListener3 != null) {
                audioDecodeListener3.decodeFail(this.mAudioPath);
            }
        }
    }
}
