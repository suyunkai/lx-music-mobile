package com.wanos.codec;

import com.baidubce.BceConfig;
import com.wanos.util.EditingUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AudioPcmData {
    public static final int DEFAULT_SAMPLING_RATE = 48000;
    private static AudioPcmData audioPcmData = null;
    private static int baseIndex = 1;
    private static int maxLen;
    private Map<String, PcmData> pcmDataMap = new HashMap();
    private AudioPcmListener audioPcmListener = null;

    public interface AudioPcmListener {
        void onFinish(String str);
    }

    public static int getMoreMemSize() {
        return 5760000;
    }

    public void initAddWavePcm(String str) {
    }

    public static AudioPcmData getInstance() {
        if (audioPcmData == null) {
            audioPcmData = new AudioPcmData();
            maxLen = EditingUtils.totalTimeOfSecond * EditingUtils.sampleRateDefault * 2 * 2;
        }
        return audioPcmData;
    }

    public static int getBaseIndex() {
        int i = baseIndex;
        baseIndex = i + 15;
        return i;
    }

    public void setAudioPcmListener(AudioPcmListener audioPcmListener) {
        this.audioPcmListener = audioPcmListener;
    }

    public void addWavePcm(String str, byte[] bArr, int i) {
        if (this.pcmDataMap.containsKey(str)) {
            PcmData pcmData = this.pcmDataMap.get(str);
            pcmData.writeLen += i;
            if (i > 2) {
                int size = ((pcmData.writeLen / (EditingUtils.pcmWaveStep * pcmData.channelNum)) + (pcmData.writeLen % (EditingUtils.pcmWaveStep * pcmData.channelNum) > 0 ? 1 : 0)) - pcmData.wavePcm.size();
                for (int i2 = 0; i2 < size; i2++) {
                    short s = 0;
                    for (int i3 = 0; i3 < EditingUtils.pcmWaveStep * pcmData.channelNum; i3 += 2) {
                        int i4 = (EditingUtils.pcmWaveStep * i2 * pcmData.channelNum) + i3;
                        int i5 = i - 2;
                        if (i4 > i5) {
                            i4 = i5;
                        }
                        short s2 = (short) ((bArr[i4] & 255) | (bArr[i4 + 1] << 8));
                        if (s <= s2) {
                            s = s2;
                        }
                    }
                    pcmData.wavePcm.add(Short.valueOf(s));
                }
            }
        }
    }

    public void closeAddWavePcm(String str) {
        if (this.pcmDataMap.containsKey(str)) {
            PcmData pcmData = this.pcmDataMap.get(str);
            pcmData.wavePcmIsEnd = true;
            AudioPcmListener audioPcmListener = this.audioPcmListener;
            if (audioPcmListener != null) {
                audioPcmListener.onFinish(pcmData.url);
            }
        }
    }

    public String getNewPcmFilePath() {
        String str = EditingUtils.getPcmLocalPath() + BceConfig.BOS_DELIMITER + baseIndex + ".pcm";
        baseIndex++;
        return str;
    }

    public synchronized PcmData initV2(String str, String str2, int i, int i2, int i3, boolean z, String str3) {
        if (this.pcmDataMap.containsKey(str)) {
            return null;
        }
        EditingUtils.log("AudioPcmData init len =" + i3 + ",maxlen=" + maxLen);
        int i4 = maxLen;
        if (i3 > i4) {
            i3 = i4;
        }
        PcmData pcmData = new PcmData();
        pcmData.name = str;
        pcmData.url = str2;
        pcmData.channelNum = i2;
        pcmData.sampleRate = i;
        pcmData.len = i3;
        pcmData.writeLen = 0;
        pcmData.exWriteLen = 0;
        pcmData.wavePcmIsEnd = false;
        pcmData.wavePcm = new ArrayList((pcmData.len / EditingUtils.pcmWaveStep) + 1);
        pcmData.pcmFilePath = str3;
        this.pcmDataMap.put(pcmData.url, pcmData);
        if (!z) {
            return pcmData;
        }
        try {
            pcmData.pcm = new byte[pcmData.len];
            Arrays.fill(pcmData.pcm, (byte) 0);
            this.pcmDataMap.put(pcmData.url, pcmData);
            return pcmData;
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    public synchronized PcmData init(String str, String str2, int i, int i2, int i3, boolean z) {
        if (this.pcmDataMap.containsKey(str)) {
            return null;
        }
        EditingUtils.log("AudioPcmData init len =" + i3 + ",maxlen=" + maxLen);
        int i4 = maxLen;
        if (i3 > i4) {
            i3 = i4;
        }
        PcmData pcmData = new PcmData();
        pcmData.name = str;
        pcmData.url = str2;
        pcmData.channelNum = i2;
        pcmData.sampleRate = i;
        pcmData.len = i3;
        pcmData.writeLen = 0;
        pcmData.exWriteLen = 0;
        pcmData.wavePcmIsEnd = false;
        pcmData.wavePcm = new ArrayList((pcmData.len / EditingUtils.pcmWaveStep) + 1);
        pcmData.pcmFilePath = EditingUtils.getPcmLocalPath() + BceConfig.BOS_DELIMITER + baseIndex + ".pcm";
        this.pcmDataMap.put(pcmData.url, pcmData);
        baseIndex++;
        if (!z) {
            return pcmData;
        }
        try {
            pcmData.pcm = new byte[pcmData.len];
            Arrays.fill(pcmData.pcm, (byte) 0);
            this.pcmDataMap.put(pcmData.url, pcmData);
            return pcmData;
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    public synchronized boolean appendPcm(String str, byte[] bArr, int i, int i2) {
        if (!this.pcmDataMap.containsKey(str)) {
            return false;
        }
        PcmData pcmData = this.pcmDataMap.get(str);
        int i3 = pcmData.exWriteLen + i;
        int i4 = maxLen;
        if (i3 > i4) {
            int i5 = i4 - pcmData.exWriteLen;
            System.arraycopy(bArr, 0, pcmData.pcm, pcmData.exWriteLen, i5);
            pcmData.len = i5;
            pcmData.exWriteLen = pcmData.len;
            EditingUtils.log("lei audioFile name=" + str + ", p.len=" + pcmData.exWriteLen);
            return false;
        }
        if (pcmData.exWriteLen + i > pcmData.len) {
            pcmData.len += getMoreMemSize();
            try {
                byte[] bArr2 = new byte[pcmData.len];
                Arrays.fill(bArr2, (byte) 0);
                System.arraycopy(pcmData.pcm, 0, bArr2, 0, pcmData.exWriteLen);
                pcmData.pcm = bArr2;
            } catch (OutOfMemoryError unused) {
                return false;
            }
        }
        if (i > 0) {
            System.arraycopy(bArr, 0, pcmData.pcm, pcmData.exWriteLen, i);
            pcmData.exWriteLen += i;
        }
        if (i2 != 0) {
            pcmData.len = i2;
            pcmData.exWriteLen = pcmData.len;
            EditingUtils.log("lei audioFile name=" + str + ", p.len=" + pcmData.exWriteLen);
        }
        return true;
    }

    public void clearPcm(String str) {
        if (this.pcmDataMap.containsKey(str)) {
            this.pcmDataMap.get(str).pcm = null;
        }
    }

    public void add(String str, PcmData pcmData) {
        if (this.pcmDataMap.containsKey(str)) {
            return;
        }
        this.pcmDataMap.put(str, pcmData);
    }

    public PcmData getPcmData(String str) {
        if (this.pcmDataMap.containsKey(str)) {
            return this.pcmDataMap.get(str);
        }
        return null;
    }

    public void clearAllData() {
        this.pcmDataMap.clear();
    }

    public class PcmData {
        public int channelNum;
        public int exWriteLen;
        public int len;
        public String name;
        public byte[] pcm;
        public String pcmFilePath;
        public int sampleRate;
        public String url;
        public List<Short> wavePcm;
        public boolean wavePcmIsEnd;
        public int writeLen;

        public PcmData() {
        }

        public boolean isValid() {
            return this.len == this.writeLen;
        }

        public short getWaveData(int i, int i2) {
            short s = 0;
            while (i < i2) {
                short sShortValue = this.wavePcm.get(i).shortValue();
                if (s <= sShortValue) {
                    s = sShortValue;
                }
                i++;
            }
            return s;
        }
    }
}
