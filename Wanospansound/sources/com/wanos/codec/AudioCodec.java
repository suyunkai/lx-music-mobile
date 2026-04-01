package com.wanos.codec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.util.EditingUtils;
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
        EditingUtils.log("AudioDecodeRunnable add path = " + str);
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
        getPCMFromAudio(str2, str, i, new AudioDecodeListener() { // from class: com.wanos.codec.AudioCodec.1
            @Override // com.wanos.codec.AudioCodec.AudioDecodeListener
            public void decodeOver(String str3) {
                synchronized (AudioCodec.decodingList) {
                    AudioCodec.decodingList.remove(str);
                }
                AudioCodec.start(i);
            }

            @Override // com.wanos.codec.AudioCodec.AudioDecodeListener
            public void decodeFail(String str3) {
                synchronized (AudioCodec.decodingList) {
                    AudioCodec.decodingList.remove(str);
                }
                AudioCodec.start(i);
            }
        });
    }

    public static String getPCMFromAudioSync(String str, String str2) {
        int iFfmpegDecodeAudio = NativeMethod.getInstance().ffmpegDecodeAudio(str, str2, new NativeMethod.IWanosDecoderListener() { // from class: com.wanos.codec.AudioCodec.2
            @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
            public void init(long j, int i, int i2, int i3) {
            }

            @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
            public void setBedBuffer(int i, byte[] bArr, int i2) {
            }

            @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
            public void write(byte[] bArr, int i) {
            }
        }, 2);
        if (iFfmpegDecodeAudio == 0) {
            return str;
        }
        Log.e(TAG, "音频解码失败，错误码: " + iFfmpegDecodeAudio);
        return "";
    }

    public static void getPCMFromAudio(final String str, final String str2, int i, final AudioDecodeListener audioDecodeListener) {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<String>() { // from class: com.wanos.codec.AudioCodec.3
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public String doInBackground() throws Throwable {
                NativeMethod.getInstance().ffmpegDecodeAudio(str, str2, new NativeMethod.IWanosDecoderListener() { // from class: com.wanos.codec.AudioCodec.3.1
                    @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
                    public void init(long j, int i2, int i3, int i4) {
                    }

                    @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
                    public void setBedBuffer(int i2, byte[] bArr, int i3) {
                    }

                    @Override // com.wanos.util.NativeMethod.IWanosDecoderListener
                    public void write(byte[] bArr, int i2) {
                    }
                }, 2);
                return str;
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(String str3) {
                AudioDecodeListener audioDecodeListener2 = audioDecodeListener;
                if (audioDecodeListener2 != null) {
                    audioDecodeListener2.decodeOver(str3);
                }
            }
        });
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

    public static void PcmToAudio(String str, String str2, final AudioEncodeListener audioEncodeListener) {
        new Thread(new AudioEncodeRunnable(str, str2, new AudioEncodeListener() { // from class: com.wanos.codec.AudioCodec.4
            @Override // com.wanos.codec.AudioCodec.AudioEncodeListener
            public void encodeOver(final String str3, final String str4) {
                if (audioEncodeListener != null) {
                    AudioCodec.handler.post(new Runnable() { // from class: com.wanos.codec.AudioCodec.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            audioEncodeListener.encodeOver(str3, str4);
                        }
                    });
                }
            }

            @Override // com.wanos.codec.AudioCodec.AudioEncodeListener
            public void encodeFail(final String str3, final String str4) {
                if (audioEncodeListener != null) {
                    AudioCodec.handler.post(new Runnable() { // from class: com.wanos.codec.AudioCodec.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            audioEncodeListener.encodeFail(str3, str4);
                        }
                    });
                }
            }
        })).start();
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
