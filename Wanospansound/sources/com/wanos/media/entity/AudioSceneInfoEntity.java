package com.wanos.media.entity;

import com.blankj.utilcode.util.GsonUtils;
import com.wanos.media.util.ZeroLogcatTools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class AudioSceneInfoEntity implements Serializable {
    private static final String TAG = "AudioSceneInfoEntity";
    private long audioId;
    private int beCollectBindId;
    private int bindId;
    private String detailInfo;
    private int durtion;
    private List<ThemeAudioInfoEntity> mDetailInfo;
    private String melody;

    public int getDurtion() {
        return this.durtion;
    }

    public int getBeCollectBindId() {
        return this.beCollectBindId;
    }

    public void setBeCollectBindId(int i) {
        this.beCollectBindId = i;
    }

    public String getMelody() {
        return this.melody;
    }

    public int getBindId() {
        return this.bindId;
    }

    public void setBindId(int i) {
        this.bindId = i;
    }

    public long getAudioId() {
        return this.audioId;
    }

    public void setAudioId(long j) {
        this.audioId = j;
    }

    public void setDetailInfo(List<ThemeAudioInfoEntity> list) {
        this.mDetailInfo = list;
    }

    public List<ThemeAudioInfoEntity> getDetailInfo() {
        String str = this.detailInfo;
        if (str == null || "null".equalsIgnoreCase(str)) {
            return null;
        }
        if (this.mDetailInfo == null) {
            this.mDetailInfo = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(this.detailInfo);
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.mDetailInfo.add((ThemeAudioInfoEntity) GsonUtils.fromJson(jSONArray.optString(i), ThemeAudioInfoEntity.class));
                }
            } catch (Exception e) {
                ZeroLogcatTools.e(TAG, "解析场景信息失败:" + e.getMessage());
                return null;
            }
        }
        return this.mDetailInfo;
    }
}
