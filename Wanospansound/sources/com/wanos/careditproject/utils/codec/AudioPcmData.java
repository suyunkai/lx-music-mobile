package com.wanos.careditproject.utils.codec;

import com.baidubce.BceConfig;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.opengl.waveUI.WaveUIGLRender;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AudioPcmData {
    private static AudioPcmData audioPcmData = null;
    private static int baseIndex = 1;
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
            WaveUIGLRender.pcmInit(EditingUtils.pcmWaveStep);
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
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str);
        if (this.pcmDataMap.containsKey(strRemoveParamOfUrl)) {
            PcmData pcmData = this.pcmDataMap.get(strRemoveParamOfUrl);
            pcmData.writeLen += i;
            if (i > 2) {
                int i2 = ((pcmData.writeLen / ((EditingUtils.pcmWaveStep * pcmData.channelNum) * 2)) + (pcmData.writeLen % ((EditingUtils.pcmWaveStep * pcmData.channelNum) * 2) > 0 ? 1 : 0)) - pcmData.wavePcmCount;
                for (int i3 = 0; i3 < i2; i3++) {
                    short s = 0;
                    for (int i4 = 0; i4 < EditingUtils.pcmWaveStep * pcmData.channelNum * 2; i4 += 2) {
                        int i5 = (EditingUtils.pcmWaveStep * i3 * pcmData.channelNum * 2) + i4;
                        int i6 = i - 2;
                        if (i5 > i6) {
                            i5 = i6;
                        }
                        short s2 = (short) ((bArr[i5] & 255) | (bArr[i5 + 1] << 8));
                        if (s <= s2) {
                            s = s2;
                        }
                    }
                    WaveUIGLRender.pcmAdd(strRemoveParamOfUrl, s);
                    pcmData.wavePcmCount++;
                }
            }
        }
    }

    public void addRecordWavPcm(String str, short[] sArr, int i) {
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str);
        for (int i2 = 0; i2 < i; i2++) {
            WaveUIGLRender.pcmAdd(strRemoveParamOfUrl, sArr[i2]);
        }
    }

    public void closeAddWavePcm(String str) {
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str);
        if (this.pcmDataMap.containsKey(strRemoveParamOfUrl)) {
            PcmData pcmData = this.pcmDataMap.get(strRemoveParamOfUrl);
            pcmData.wavePcmIsEnd = true;
            EditingUtils.log("getPCMFromAudio p.writeLen = " + pcmData.writeLen + ", " + pcmData.wavePcmCount);
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

    public synchronized PcmData initV2(String str, String str2, int i, int i2, int i3, String str3, int i4) {
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str2);
        if (this.pcmDataMap.containsKey(str2)) {
            return null;
        }
        PcmData pcmData = new PcmData();
        pcmData.name = str;
        pcmData.url = strRemoveParamOfUrl;
        pcmData.channelNum = i2;
        pcmData.sampleRate = i;
        pcmData.len = i3;
        pcmData.writeLen = 0;
        pcmData.exWriteLen = 0;
        pcmData.wavePcmIsEnd = false;
        pcmData.wavePcmCount = 0;
        pcmData.isWanos = i4;
        pcmData.wavePcm = new ArrayList((pcmData.len / EditingUtils.pcmWaveStep) + 1);
        pcmData.pcmFilePath = str3;
        this.pcmDataMap.put(pcmData.url, pcmData);
        return pcmData;
    }

    public synchronized PcmData init(String str, String str2, int i, int i2, int i3) {
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str2);
        if (this.pcmDataMap.containsKey(strRemoveParamOfUrl)) {
            return null;
        }
        PcmData pcmData = new PcmData();
        pcmData.name = str;
        pcmData.url = strRemoveParamOfUrl;
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
        return pcmData;
    }

    public void add(String str, PcmData pcmData) {
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str);
        if (this.pcmDataMap.containsKey(strRemoveParamOfUrl)) {
            return;
        }
        this.pcmDataMap.put(strRemoveParamOfUrl, pcmData);
    }

    public void changeUrl(String str, String str2) {
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str);
        String strRemoveParamOfUrl2 = EditingUtils.removeParamOfUrl(str2);
        if (this.pcmDataMap.containsKey(strRemoveParamOfUrl)) {
            Map<String, PcmData> map = this.pcmDataMap;
            map.put(strRemoveParamOfUrl2, map.get(strRemoveParamOfUrl));
        }
        WaveUIGLRender.pcmUrlChange(strRemoveParamOfUrl, strRemoveParamOfUrl2);
    }

    public PcmData getPcmData(String str) {
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str);
        if (this.pcmDataMap.containsKey(strRemoveParamOfUrl)) {
            return this.pcmDataMap.get(strRemoveParamOfUrl);
        }
        return null;
    }

    public void clearAllData() {
        this.pcmDataMap.clear();
        WaveUIGLRender.pcmClear();
    }

    public class PcmData {
        public int channelNum;
        public int exWriteLen;
        public int isWanos;
        public int len;
        public String name;
        public byte[] pcm;
        public String pcmFilePath;
        public int sampleRate;
        public String url;
        public List<Short> wavePcm;
        public int wavePcmCount = 0;
        public boolean wavePcmIsEnd;
        public int writeLen;

        public PcmData() {
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
