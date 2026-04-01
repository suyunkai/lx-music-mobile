package com.wanos.carstudio.audiotrackplayer.Interaction;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AudioPcmData {
    private static AudioPcmData audioPcmData;
    private final Map<String, PcmData> pcmDataMap = new HashMap();

    public static int getMoreMemSize() {
        return 120;
    }

    public static AudioPcmData getInstance() {
        if (audioPcmData == null) {
            audioPcmData = new AudioPcmData();
        }
        return audioPcmData;
    }

    public synchronized boolean init(String str, int i, int i2, int i3) {
        if (this.pcmDataMap.containsKey(str)) {
            return false;
        }
        PcmData pcmData = new PcmData();
        pcmData.name = str;
        pcmData.channelNum = i2;
        pcmData.sampleRate = i;
        pcmData.len = i3;
        pcmData.writeLen = 0;
        pcmData.pcm = new byte[pcmData.len];
        this.pcmDataMap.put(pcmData.name, pcmData);
        return true;
    }

    public synchronized void appendPcm(String str, byte[] bArr, int i, int i2) {
        if (this.pcmDataMap.containsKey(str)) {
            PcmData pcmData = this.pcmDataMap.get(str);
            if (pcmData.writeLen + i > pcmData.len) {
                pcmData.len += getMoreMemSize();
                pcmData.pcm = new byte[pcmData.len];
            }
            if (i > 0) {
                System.arraycopy(bArr, 0, pcmData.pcm, pcmData.writeLen, i);
                pcmData.writeLen += i;
            }
            if (i2 != 0) {
                pcmData.len = i2;
                pcmData.writeLen = pcmData.len;
            }
        }
    }

    public PcmData getPcmData(String str) {
        if (this.pcmDataMap.containsKey(str)) {
            return this.pcmDataMap.get(str);
        }
        return null;
    }

    public class PcmData {
        public int channelNum;
        public int len;
        public String name;
        public byte[] pcm;
        public int sampleRate;
        public int writeLen;

        public PcmData() {
        }

        public boolean isValid() {
            return this.len == this.writeLen;
        }
    }
}
