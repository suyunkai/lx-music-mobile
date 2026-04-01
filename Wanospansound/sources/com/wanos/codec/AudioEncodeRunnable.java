package com.wanos.codec;

import com.wanos.codec.AudioCodec;
import com.wanos.codec.AudioPcmData;
import com.wanos.util.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AudioEncodeRunnable implements Runnable {
    private static final String TAG = "AudioEncodeRunnable";
    private String localPath;
    private AudioCodec.AudioEncodeListener mListener;
    private String pcmKey;

    public native void encode();

    public AudioEncodeRunnable(String str, String str2, AudioCodec.AudioEncodeListener audioEncodeListener) {
        this.pcmKey = str;
        this.localPath = str2;
        this.mListener = audioEncodeListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        AudioPcmData.PcmData pcmData = AudioPcmData.getInstance().getPcmData(this.pcmKey);
        if (pcmData == null) {
            AudioCodec.AudioEncodeListener audioEncodeListener = this.mListener;
            if (audioEncodeListener != null) {
                audioEncodeListener.encodeFail(this.pcmKey, this.localPath);
                return;
            }
            return;
        }
        AACEncoder aACEncoder = new AACEncoder();
        int iInit = aACEncoder.init(64000, 1, 48000, 16, this.localPath);
        EditingUtils.log("AudioEncodeRunnable aacEncoder.init res = " + iInit);
        try {
            if (iInit == 0) {
                try {
                    if (aACEncoder.encode(pcmData.pcm, pcmData.len) == 0) {
                        AudioCodec.AudioEncodeListener audioEncodeListener2 = this.mListener;
                        if (audioEncodeListener2 != null) {
                            audioEncodeListener2.encodeOver(this.pcmKey, this.localPath);
                        }
                    } else {
                        AudioCodec.AudioEncodeListener audioEncodeListener3 = this.mListener;
                        if (audioEncodeListener3 != null) {
                            audioEncodeListener3.encodeFail(this.pcmKey, this.localPath);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    AudioCodec.AudioEncodeListener audioEncodeListener4 = this.mListener;
                    if (audioEncodeListener4 != null) {
                        audioEncodeListener4.encodeFail(this.pcmKey, this.localPath);
                    }
                }
                return;
            }
            AudioCodec.AudioEncodeListener audioEncodeListener5 = this.mListener;
            if (audioEncodeListener5 != null) {
                audioEncodeListener5.encodeFail(this.pcmKey, this.localPath);
            }
        } finally {
            aACEncoder.uninit();
        }
    }
}
