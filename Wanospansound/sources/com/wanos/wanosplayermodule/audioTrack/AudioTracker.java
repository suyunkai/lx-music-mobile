package com.wanos.wanosplayermodule.audioTrack;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.media.VolumeShaper;
import android.util.Log;
import com.wanos.wanosplayermodule.audioTrack.AudioFocus;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTracker {
    private static final int AUDIO_FORMAT = 2;
    private static final int CHANNEL = 4;
    private static final int SAMPLE_RATE = 16000;
    private static final String TAG = "wanos:[AudioTracker]";
    AudioFocus audioFocus;
    InputStream bis;
    private AudioPlayListener mAudioPlayListener;
    private AudioTrack mAudioTrack;
    private int mBufferSizeInBytes;
    Context mContext;
    private String mFilePath;
    VolumeShaper volumeShaper;
    private volatile Status mStatus = Status.STATUS_NO_READY;
    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    VolumeShaper.Configuration config = new VolumeShaper.Configuration.Builder().setInterpolatorType(1).setCurve(new float[]{0.0f, 0.5f, 1.0f}, new float[]{0.0f, 0.5f, 1.0f}).setDuration(1000).build();

    public interface AudioPlayListener {
        void onError(String str);

        void onStart();

        void onStop();
    }

    public enum Status {
        STATUS_NO_READY,
        STATUS_READY,
        STATUS_START,
        STATUS_STOP
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public AudioTracker(Context context) {
        this.mContext = context;
        this.audioFocus = new AudioFocus(context);
    }

    public void createAudioTrack(String str) throws IllegalStateException {
        this.mFilePath = str;
        int minBufferSize = AudioTrack.getMinBufferSize(16000, 4, 2);
        this.mBufferSizeInBytes = minBufferSize;
        if (minBufferSize <= 0) {
            throw new IllegalStateException("AudioTrack is not available " + this.mBufferSizeInBytes);
        }
        this.mAudioTrack = new AudioTrack.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).setLegacyStreamType(3).build()).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(16000).setChannelMask(4).build()).setTransferMode(1).setBufferSizeInBytes(this.mBufferSizeInBytes).build();
        this.mStatus = Status.STATUS_READY;
    }

    public void start() throws IllegalStateException {
        if (this.audioFocus.requestAudioFocus(new AudioFocus.OnAudioFocusListener() { // from class: com.wanos.wanosplayermodule.audioTrack.AudioTracker.1
            @Override // com.wanos.wanosplayermodule.audioTrack.AudioFocus.OnAudioFocusListener
            public void onFocusStart() {
            }

            @Override // com.wanos.wanosplayermodule.audioTrack.AudioFocus.OnAudioFocusListener
            public void onFocusStop() {
            }
        }) == 1) {
            if (this.mStatus == Status.STATUS_NO_READY || this.mAudioTrack == null) {
                throw new IllegalStateException("播放器尚未初始化");
            }
            if (this.mStatus == Status.STATUS_START) {
                this.mAudioTrack.stop();
                this.mStatus = Status.STATUS_STOP;
            }
            Log.d(TAG, "===start===");
            this.mStatus = Status.STATUS_START;
            this.mExecutorService.execute(new Runnable() { // from class: com.wanos.wanosplayermodule.audioTrack.AudioTracker.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AudioTracker.this.playAudioData();
                    } catch (IOException e) {
                        Log.e(AudioTracker.TAG, "playAudioData: ", e);
                        if (AudioTracker.this.mAudioPlayListener != null) {
                            AudioTracker.this.mAudioPlayListener.onError(e.getMessage());
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playAudioData() throws IOException {
        int i;
        try {
            this.bis = new BufferedInputStream(new FileInputStream(this.mFilePath));
            AudioPlayListener audioPlayListener = this.mAudioPlayListener;
            if (audioPlayListener != null) {
                audioPlayListener.onStart();
            }
            byte[] bArr = new byte[this.mBufferSizeInBytes];
            this.mAudioTrack.play();
            while (this.mStatus == Status.STATUS_START && (i = this.bis.read(bArr)) != -1) {
                this.mAudioTrack.write(bArr, 0, i);
            }
            try {
                this.bis.close();
                this.bis = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mAudioTrack.stop();
            AudioPlayListener audioPlayListener2 = this.mAudioPlayListener;
            if (audioPlayListener2 != null) {
                audioPlayListener2.onStop();
            }
        } finally {
        }
    }

    public void stop() throws IllegalStateException {
        Log.d(TAG, "===stop===");
        if (this.mStatus == Status.STATUS_NO_READY || this.mStatus == Status.STATUS_READY) {
            throw new IllegalStateException("播放尚未开始");
        }
        this.mStatus = Status.STATUS_STOP;
    }

    public void release() {
        Log.d(TAG, "==release===");
        this.mStatus = Status.STATUS_NO_READY;
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            audioTrack.release();
            this.mAudioTrack = null;
        }
    }

    public void setAudioPlayListener(AudioPlayListener audioPlayListener) {
        this.mAudioPlayListener = audioPlayListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r6v9 */
    public void copyFromAssetsToSdcard(Context context, boolean z, String str, String str2) throws Throwable {
        Throwable th;
        FileOutputStream fileOutputStream;
        IOException e;
        FileNotFoundException e2;
        File file = new File(str2);
        if (z || !(z || file.exists())) {
            try {
                try {
                    try {
                        context = context.getResources().getAssets().open(str);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (FileNotFoundException e3) {
                    fileOutputStream = null;
                    e2 = e3;
                    context = 0;
                } catch (IOException e4) {
                    fileOutputStream = null;
                    e = e4;
                    context = 0;
                } catch (Throwable th3) {
                    str = 0;
                    th = th3;
                    context = 0;
                }
                try {
                    fileOutputStream = new FileOutputStream(str2);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = context.read(bArr, 0, 1024);
                            if (i >= 0) {
                                fileOutputStream.write(bArr, 0, i);
                            } else {
                                try {
                                    break;
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                        }
                        fileOutputStream.close();
                    } catch (FileNotFoundException e6) {
                        e2 = e6;
                        e2.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        if (context == 0) {
                            return;
                        } else {
                            context.close();
                        }
                    } catch (IOException e8) {
                        e = e8;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e9) {
                                e9.printStackTrace();
                            }
                        }
                        if (context == 0) {
                            return;
                        } else {
                            context.close();
                        }
                    }
                } catch (FileNotFoundException e10) {
                    fileOutputStream = null;
                    e2 = e10;
                } catch (IOException e11) {
                    fileOutputStream = null;
                    e = e11;
                } catch (Throwable th4) {
                    str = 0;
                    th = th4;
                    if (str != 0) {
                        try {
                            str.close();
                        } catch (IOException e12) {
                            e12.printStackTrace();
                        }
                    }
                    if (context == 0) {
                        throw th;
                    }
                    try {
                        context.close();
                        throw th;
                    } catch (IOException e13) {
                        e13.printStackTrace();
                        throw th;
                    }
                }
                if (context != 0) {
                    context.close();
                }
            } catch (IOException e14) {
                e14.printStackTrace();
            }
        }
    }

    public void releaseAudioFocus() {
        AudioFocus audioFocus = this.audioFocus;
        if (audioFocus != null) {
            audioFocus.releaseAudioFocus();
        }
    }
}
