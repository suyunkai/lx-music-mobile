package com.wanos.careditproject.utils.codec;

import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.commonlibrary.event.EditProjectEvent;
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
        this.urlListToTrack.add(EditingUtils.removeParamOfUrl(str));
    }

    public void clear() {
        this.decodeAudioInfoMap.clear();
        this.urlListToTrack.clear();
    }

    public void add(String str, int i, int i2, long j) {
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str);
        DecodeAudioInfo decodeAudioInfo = new DecodeAudioInfo();
        decodeAudioInfo.url = strRemoveParamOfUrl;
        decodeAudioInfo.sampleRate = i;
        decodeAudioInfo.channelNum = i2;
        decodeAudioInfo.sampleNum = j;
        this.decodeAudioInfoMap.put(strRemoveParamOfUrl, decodeAudioInfo);
        if (this.urlListToTrack.contains(strRemoveParamOfUrl)) {
            DataHelpAudioTrack.DataHelpResult dataHelpResultAddTrackWithUrl = DataHelpAudioTrack.addTrackWithUrl(strRemoveParamOfUrl, null, i, i2, j, EditingParams.getInstance().getSelectTrackIndex(), EditingParams.getInstance().getProgressSampleNum(), "");
            String errMsg = dataHelpResultAddTrackWithUrl.isSuccess() ? "添加成功！" : dataHelpResultAddTrackWithUrl.getErrMsg();
            EditProjectEvent editProjectEvent = new EditProjectEvent(EditProjectEvent.ProjectEventType.ShowTipsAndUpdateUI);
            editProjectEvent.param = errMsg;
            EventBus.getDefault().post(editProjectEvent);
        }
    }

    public DecodeAudioInfo get(String str) {
        String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(str);
        if (this.decodeAudioInfoMap.containsKey(strRemoveParamOfUrl)) {
            return this.decodeAudioInfoMap.get(strRemoveParamOfUrl);
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
