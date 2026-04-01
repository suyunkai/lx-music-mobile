package com.wanos.codec;

import com.wanos.commonlibrary.event.EditProjectEvent;
import com.wanos.util.EditingParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class AudioFileInfo {
    protected static AudioFileInfo audioFileInfo;
    private DecodeAudioInfoListener listener;
    private Map<String, DecodeAudioInfo> decodeAudioInfoMap = new HashMap();
    private List<String> urlListToTrack = new ArrayList();

    public interface DecodeAudioInfoListener {
        void callback(DecodeAudioInfo decodeAudioInfo);
    }

    public static AudioFileInfo getInstance() {
        if (audioFileInfo == null) {
            audioFileInfo = new AudioFileInfo();
        }
        return audioFileInfo;
    }

    AudioFileInfo() {
    }

    public void setListener(DecodeAudioInfoListener decodeAudioInfoListener) {
        this.listener = decodeAudioInfoListener;
    }

    public void wait(String str) {
        this.urlListToTrack.add(str);
    }

    public void clear() {
        this.decodeAudioInfoMap.clear();
        this.urlListToTrack.clear();
    }

    public void add(String str, int i, int i2, long j) {
        DecodeAudioInfo decodeAudioInfo = new DecodeAudioInfo();
        decodeAudioInfo.url = str;
        decodeAudioInfo.sampleRate = i;
        decodeAudioInfo.channelNum = i2;
        decodeAudioInfo.sampleNum = j;
        this.decodeAudioInfoMap.put(str, decodeAudioInfo);
        if (this.urlListToTrack.contains(str)) {
            EditingParams.getInstance().getSelectTrackIndex();
            EditingParams.getInstance().getProgressSampleNum();
            EventBus.getDefault().post(new EditProjectEvent(EditProjectEvent.ProjectEventType.ShowTipsAndUpdateUI));
        }
    }

    public DecodeAudioInfo get(String str) {
        if (this.decodeAudioInfoMap.containsKey(str)) {
            return this.decodeAudioInfoMap.get(str);
        }
        return null;
    }

    public class DecodeAudioInfo {
        public int channelNum;
        public long sampleNum;
        public int sampleRate;
        public String url;

        public DecodeAudioInfo() {
        }
    }
}
