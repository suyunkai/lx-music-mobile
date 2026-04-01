package com.wanos.careditproject.utils.codec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.dialog.EditLoadingValue;
import com.wanos.util.NativeMethod;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

/* JADX INFO: loaded from: classes3.dex */
public class AudioCodec {
    private static final String TAG = "AudioCodec";
    private static CodecListener codecListener = null;
    private static int maxThreadCount = 4;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static List<String> unDecodeList = Collections.synchronizedList(new ArrayList());
    private static List<String> decodingList = Collections.synchronizedList(new ArrayList());
    private static Map<String, String> audioUrl2Path = new HashMap();
    private static Map<Integer, Boolean> isDecoding = new HashMap();
    private static boolean codecStop = false;

    public interface AudioDecodeListener {
        void decodeFail(String str);

        void decodeOver(String str);
    }

    public interface AudioEncodeListener {
        void encodeFail(String str, String str2);

        void encodeOver(String str, String str2);
    }

    public interface CodecListener {
        void onFinish();
    }

    public static void init() {
        EditingUtils.log("AudioCodec init");
        codecStop = false;
        isDecoding.clear();
        unDecodeList.clear();
        decodingList.clear();
        audioUrl2Path.clear();
    }

    public static void destroy() {
        codecStop = true;
        EditingUtils.log("AudioCodec destroy");
    }

    public static void add(String str, String str2) {
        if (!unDecodeList.contains(str2) && !decodingList.contains(str2) && AudioPcmData.getInstance().getPcmData(str2) == null) {
            unDecodeList.add(str2);
            audioUrl2Path.put(str2, str);
            for (int i = 0; i < maxThreadCount; i++) {
                if (!isDecoding.containsKey(Integer.valueOf(i)) || !isDecoding.get(Integer.valueOf(i)).booleanValue()) {
                    start(i);
                    break;
                }
            }
        }
        EditingUtils.log("AudioCodec add path = " + str2);
    }

    public static void setCodecListener(CodecListener codecListener2) {
        codecListener = codecListener2;
    }

    public static boolean isFinish() {
        for (int i = 0; i < maxThreadCount; i++) {
            if (isDecoding.containsKey(Integer.valueOf(i)) && isDecoding.get(Integer.valueOf(i)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static void start(final int i) {
        final String str;
        String str2;
        EditingUtils.log("AudioCodec start codecStop=" + codecStop);
        if (codecStop) {
            return;
        }
        if (unDecodeList.size() <= 0) {
            isDecoding.put(Integer.valueOf(i), false);
            CodecListener codecListener2 = codecListener;
            if (codecListener2 != null) {
                codecListener2.onFinish();
                return;
            }
            return;
        }
        isDecoding.put(Integer.valueOf(i), true);
        synchronized (unDecodeList) {
            List<String> list = unDecodeList;
            str = list.get(list.size() - 1);
            str2 = audioUrl2Path.get(str);
            List<String> list2 = unDecodeList;
            list2.remove(list2.size() - 1);
        }
        synchronized (decodingList) {
            decodingList.add(str);
        }
        getPCMFromAudio(str2, str, i, new AudioDecodeListener() { // from class: com.wanos.careditproject.utils.codec.AudioCodec.1
            @Override // com.wanos.careditproject.utils.codec.AudioCodec.AudioDecodeListener
            public void decodeOver(String str3) {
                synchronized (AudioCodec.decodingList) {
                    AudioCodec.decodingList.remove(str);
                }
                AudioCodec.start(i);
            }

            @Override // com.wanos.careditproject.utils.codec.AudioCodec.AudioDecodeListener
            public void decodeFail(String str3) {
                synchronized (AudioCodec.decodingList) {
                    AudioCodec.decodingList.remove(str);
                }
                AudioCodec.start(i);
            }
        });
    }

    public static void getPCMFromAudio(String str, String str2, int i, AudioDecodeListener audioDecodeListener) {
        getPCMFromAudio(str, str2, i, audioDecodeListener, false);
    }

    public static void getPCMFromAudio(final String str, final String str2, int i, final AudioDecodeListener audioDecodeListener, final boolean z) {
        Thread thread = new Thread(new Runnable() { // from class: com.wanos.careditproject.utils.codec.AudioCodec.2
            @Override // java.lang.Runnable
            public void run() {
                final String newPcmFilePath = AudioPcmData.getInstance().getNewPcmFilePath();
                EditingUtils.log("AudioCodec init  audioUrl=" + str2);
                int iFfmpegDecodeAudio = NativeMethod.getInstance().ffmpegDecodeAudio(str, newPcmFilePath, new NativeMethod.IWanosDecoderListener() { // from class: com.wanos.careditproject.utils.codec.AudioCodec.2.1
                    @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
                    public void setBedBuffer(int i2, byte[] bArr, int i3) {
                    }

                    @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
                    public void init(long j, int i2, int i3, int i4) {
                        long j2 = (long) (j * (EditingUtils.sampleRateDefault / 1000000.0f));
                        EditingUtils.log("getPCMFromAudio init outPath = " + newPcmFilePath + ", durationS = " + j + ", sampleNum=" + j2 + ", audioUrl=" + str2);
                        AudioFileInfo.getInstance().add(str2, i2, i3, j2);
                        long j3 = (int) (((j / 1000000) + 1) * ((long) i2) * ((long) i3) * 2);
                        EditingUtils.log("getPCMFromAudio init size = " + j3);
                        if (AudioPcmData.getInstance().initV2(str, str2, i2, i3, (int) j3, newPcmFilePath, i4) == null) {
                            return;
                        }
                        EditLoadingValue.getInstance().setDecodeProgressInit(str2, j3);
                    }

                    @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
                    public void write(byte[] bArr, int i2) {
                        if (AudioCodec.codecStop) {
                            return;
                        }
                        AudioPcmData.getInstance().addWavePcm(str2, bArr, i2);
                        EditLoadingValue.getInstance().setDecodeProgress(str2, i2);
                    }
                }, 0, z);
                if (AudioCodec.codecStop) {
                    return;
                }
                EditingUtils.log("getPCMFromAudio res = " + iFfmpegDecodeAudio);
                AudioPcmData.getInstance().closeAddWavePcm(str2);
                AudioDecodeListener audioDecodeListener2 = audioDecodeListener;
                if (audioDecodeListener2 != null) {
                    if (iFfmpegDecodeAudio == 0) {
                        audioDecodeListener2.decodeOver(str);
                    } else {
                        audioDecodeListener2.decodeFail(str);
                    }
                }
            }
        });
        thread.setPriority(10);
        thread.start();
    }

    public static void encodeAudioToWanos(final String str, final String str2, final AudioDecodeListener audioDecodeListener) {
        Thread thread = new Thread(new Runnable() { // from class: com.wanos.careditproject.utils.codec.AudioCodec.3
            @Override // java.lang.Runnable
            public void run() {
                final float[] fArr = new float[EditingUtils.encodeStep * 15];
                final int[] iArr = {1};
                final int[] iArr2 = {0};
                final int[] iArr3 = new int[1];
                int iFfmpegDecodeAudio = NativeMethod.getInstance().ffmpegDecodeAudio(str, "", new NativeMethod.IWanosDecoderListener() { // from class: com.wanos.careditproject.utils.codec.AudioCodec.3.1
                    @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
                    public void setBedBuffer(int i, byte[] bArr, int i2) {
                    }

                    @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
                    public void init(long j, int i, int i2, int i3) {
                        long j2 = (long) (j * (EditingUtils.sampleRateDefault / 1000000.0f));
                        iArr[0] = i2;
                        iArr3[0] = NativeMethod.getInstance().encodeInit(EditingUtils.encodeStep, i2, 48000, 32, str2);
                        if (iArr3[0] <= 0) {
                            return;
                        }
                        AudioFileInfo.getInstance().add(str2, i, i2, j2);
                        long j3 = (int) (((j / 1000000) + 1) * ((long) i) * ((long) i2) * 2);
                        EditingUtils.log("getPCMFromAudio init size = " + j3);
                        AudioPcmData.getInstance().initV2(str2, str2, i, i2, (int) j3, "", 1);
                    }

                    @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
                    public void write(byte[] bArr, int i) {
                        if (AudioCodec.codecStop) {
                            return;
                        }
                        int i2 = i / EditingUtils.sizeOfShort;
                        int i3 = EditingUtils.encodeStep * iArr[0];
                        for (int i4 = 0; i4 < i2; i4++) {
                            int i5 = i4 * 2;
                            short s = (short) ((bArr[i5] & 255) | (bArr[i5 + 1] << 8));
                            float[] fArr2 = fArr;
                            int[] iArr4 = iArr2;
                            int i6 = iArr4[0];
                            fArr2[i6] = s / 32768.0f;
                            int i7 = i6 + 1;
                            iArr4[0] = i7;
                            if (i7 == i3) {
                                NativeMethod.getInstance().encode(iArr3[0], fArr, i3);
                                iArr2[0] = 0;
                            }
                        }
                        AudioPcmData.getInstance().addWavePcm(str2, bArr, i);
                    }
                }, 0);
                if (AudioCodec.codecStop) {
                    return;
                }
                NativeMethod.getInstance().encodeClose(iArr3[0]);
                AudioPcmData.getInstance().closeAddWavePcm(str2);
                AudioDecodeListener audioDecodeListener2 = audioDecodeListener;
                if (audioDecodeListener2 != null) {
                    if (iFfmpegDecodeAudio == 0) {
                        audioDecodeListener2.decodeOver(str);
                    } else {
                        audioDecodeListener2.decodeFail(str);
                    }
                }
            }
        });
        thread.setPriority(10);
        thread.start();
    }

    public static void getPCMFromAudioCodec(String str, String str2, int i, AudioDecodeListener audioDecodeListener) {
        if (AudioPcmData.getInstance().getPcmData(str2) != null) {
            if (audioDecodeListener != null) {
                audioDecodeListener.decodeFail(str);
                return;
            }
            return;
        }
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            EditingUtils.log(str);
            mediaExtractor.setDataSource(str);
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= mediaExtractor.getTrackCount()) {
                    i2 = -1;
                    break;
                } else {
                    if (mediaExtractor.getTrackFormat(i2).getString(IMediaFormat.KEY_MIME).startsWith("audio/")) {
                        z = true;
                        break;
                    }
                    i2++;
                }
            }
            int i3 = i2;
            if (z) {
                mediaExtractor.selectTrack(i3);
                new Thread(new AudioDecodeRunnable(mediaExtractor, i3, str, str2, audioDecodeListener)).start();
            } else {
                Log.e(TAG, "音频文件没有音频音轨");
                if (audioDecodeListener != null) {
                    audioDecodeListener.decodeFail(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "解码失败: " + str);
            if (audioDecodeListener != null) {
                audioDecodeListener.decodeFail(str);
            }
        }
    }

    public static void addADTStoPacket(byte[] bArr, int i) {
        bArr[0] = -1;
        bArr[1] = -7;
        bArr[2] = (byte) 80;
        bArr[3] = (byte) (128 + (i >> 11));
        bArr[4] = (byte) ((i & 2047) >> 3);
        bArr[5] = (byte) (((i & 7) << 5) + 31);
        bArr[6] = -4;
    }

    public static void getAudioInfo(String str, String str2, AudioDecodeListener audioDecodeListener) {
        boolean z;
        if (AudioPcmData.getInstance().getPcmData(str2) != null) {
            return;
        }
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            EditingUtils.log(str);
            mediaExtractor.setDataSource(str);
            int i = 0;
            while (true) {
                if (i >= mediaExtractor.getTrackCount()) {
                    i = -1;
                    z = false;
                    break;
                } else {
                    if (mediaExtractor.getTrackFormat(i).getString(IMediaFormat.KEY_MIME).startsWith("audio/")) {
                        z = true;
                        break;
                    }
                    i++;
                }
            }
            if (z) {
                mediaExtractor.selectTrack(i);
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
                MediaCodec.createDecoderByType(trackFormat.getString(IMediaFormat.KEY_MIME)).configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
                EditingUtils.log("getAudioInfo sampleRate=" + trackFormat.getInteger("sample-rate") + ",channelNum=" + trackFormat.getInteger("channel-count") + ",sampleNum=" + ((trackFormat.getLong("durationUs") * ((long) EditingUtils.sampleRateDefault)) / 1000000));
                return;
            }
            Log.e(TAG, "音频文件没有音频音轨");
            if (audioDecodeListener != null) {
                audioDecodeListener.decodeFail(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "解码失败: " + str);
            if (audioDecodeListener != null) {
                audioDecodeListener.decodeFail(str);
            }
        }
    }
}
