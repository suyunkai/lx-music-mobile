package com.wanos.careditproject.utils.codec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.view.Surface;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.codec.AudioCodec;
import com.wanos.careditproject.utils.codec.AudioPcmData;
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
        ByteBuffer[] byteBufferArr;
        FileOutputStream fileOutputStream;
        int i2;
        int i3;
        FileOutputStream fileOutputStream2;
        try {
            MediaFormat trackFormat = this.extractor.getTrackFormat(this.audioTrack);
            MediaCodec mediaCodecCreateDecoderByType = MediaCodec.createDecoderByType(trackFormat.getString(IMediaFormat.KEY_MIME));
            int i4 = 0;
            mediaCodecCreateDecoderByType.configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
            int integer = trackFormat.getInteger("sample-rate");
            int integer2 = trackFormat.getInteger("channel-count");
            long j2 = trackFormat.getLong("durationUs");
            if (trackFormat.containsKey("pcm-encoding")) {
                trackFormat.getInteger("pcm-encoding");
            }
            AudioFileInfo.getInstance().add(this.mAudioUrl, integer, integer2, (((long) EditingUtils.sampleRateDefault) * j2) / 1000000);
            AudioPcmData.PcmData pcmDataInit = AudioPcmData.getInstance().init(this.mAudioPath, this.mAudioUrl, integer, integer2, (int) (((j2 / 1000000) + 1) * ((long) integer) * ((long) integer2) * 2));
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
            FileOutputStream fileOutputStream3 = new FileOutputStream(pcmDataInit.pcmFilePath);
            byte[] bArr = new byte[10];
            if (integer != 48000) {
                bArr = new byte[NativeMethod.getInstance().resampleAudioInit(this.mAudioUrl, integer, 48000, integer2)];
            }
            boolean z = false;
            while (!z) {
                int i5 = i4;
                while (true) {
                    j = 0;
                    i = 1;
                    if (i5 >= inputBuffers.length) {
                        break;
                    }
                    int iDequeueInputBuffer = mediaCodecCreateDecoderByType.dequeueInputBuffer(0L);
                    if (iDequeueInputBuffer >= 0) {
                        ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
                        byteBuffer.clear();
                        int sampleData = this.extractor.readSampleData(byteBuffer, i4);
                        if (sampleData < 0) {
                            i3 = i5;
                            fileOutputStream2 = fileOutputStream3;
                            mediaCodecCreateDecoderByType.queueInputBuffer(iDequeueInputBuffer, 0, 0, 0L, 4);
                        } else {
                            i3 = i5;
                            fileOutputStream2 = fileOutputStream3;
                            bufferInfo2.offset = i4;
                            bufferInfo2.size = sampleData;
                            bufferInfo2.flags = 1;
                            bufferInfo2.presentationTimeUs = this.extractor.getSampleTime();
                            mediaCodecCreateDecoderByType.queueInputBuffer(iDequeueInputBuffer, bufferInfo2.offset, sampleData, bufferInfo2.presentationTimeUs, 0);
                            this.extractor.advance();
                        }
                    } else {
                        i3 = i5;
                        fileOutputStream2 = fileOutputStream3;
                    }
                    i5 = i3 + 1;
                    fileOutputStream3 = fileOutputStream2;
                }
                FileOutputStream fileOutputStream4 = fileOutputStream3;
                int i6 = i4;
                boolean z2 = z;
                while (i6 == 0) {
                    int iDequeueOutputBuffer = mediaCodecCreateDecoderByType.dequeueOutputBuffer(bufferInfo, j);
                    if (iDequeueOutputBuffer == -1) {
                        byteBufferArr = inputBuffers;
                        i6 = i;
                    } else {
                        if (iDequeueOutputBuffer == -3) {
                            mediaCodecCreateDecoderByType.getOutputBuffers();
                        } else if (iDequeueOutputBuffer == -2) {
                            mediaCodecCreateDecoderByType.getOutputFormat();
                        } else if (iDequeueOutputBuffer >= 0) {
                            ByteBuffer outputBuffer = mediaCodecCreateDecoderByType.getOutputBuffer(iDequeueOutputBuffer);
                            int i7 = bufferInfo.size;
                            byte[] bArr2 = new byte[i7];
                            outputBuffer.get(bArr2);
                            outputBuffer.clear();
                            this.pcmLen += bufferInfo.size;
                            if (integer != 48000) {
                                int i8 = (i7 / 2) / integer2;
                                int i9 = (i8 * 48000) / integer;
                                NativeMethod.getInstance().resample(this.mAudioUrl, bArr2, i8, bArr, i9);
                                int i10 = i9 * 2 * integer2;
                                fileOutputStream = fileOutputStream4;
                                fileOutputStream.write(bArr, i4, i10);
                                fileOutputStream.flush();
                                byteBufferArr = inputBuffers;
                                AudioPcmData.getInstance().addWavePcm(this.mAudioUrl, bArr, i10);
                            } else {
                                byteBufferArr = inputBuffers;
                                fileOutputStream = fileOutputStream4;
                                fileOutputStream.write(bArr2);
                                fileOutputStream.flush();
                                AudioPcmData.getInstance().addWavePcm(this.mAudioUrl, bArr2, i7);
                            }
                            i2 = 0;
                            mediaCodecCreateDecoderByType.releaseOutputBuffer(iDequeueOutputBuffer, false);
                            if ((bufferInfo.flags & 4) != 0) {
                                AudioPcmData.getInstance().addWavePcm(this.mAudioUrl, bArr2, i7);
                                AudioPcmData.getInstance().closeAddWavePcm(this.mAudioUrl);
                                EditingUtils.log("AudioDecodeRunnable end size=" + this.pcmLen + ", url =" + this.mAudioUrl);
                                this.extractor.release();
                                mediaCodecCreateDecoderByType.stop();
                                mediaCodecCreateDecoderByType.release();
                                z2 = true;
                                i6 = 1;
                            }
                            i4 = i2;
                            fileOutputStream4 = fileOutputStream;
                            inputBuffers = byteBufferArr;
                            j = 0;
                            i = 1;
                        }
                        byteBufferArr = inputBuffers;
                    }
                    i2 = i4;
                    fileOutputStream = fileOutputStream4;
                    i4 = i2;
                    fileOutputStream4 = fileOutputStream;
                    inputBuffers = byteBufferArr;
                    j = 0;
                    i = 1;
                }
                z = z2;
                fileOutputStream3 = fileOutputStream4;
            }
            NativeMethod.getInstance().resampleAudioClose(this.mAudioUrl);
            fileOutputStream3.close();
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
