package com.wanos.WanosCommunication.bean;

import android.util.Log;
import com.blankj.utilcode.util.GsonUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroThemeInfoEntity implements Serializable {
    private static final String TAG = "wanos[WanosVideoPage]";
    private List<AudioSceneInfoEntity> audioInfo;
    private String bgImg;
    private int bgType;
    private String durationRange;
    private long themeId;
    private String themeName;
    private String videoResource;

    public long getThemeId() {
        return this.themeId;
    }

    public String getBgImg() {
        return this.bgImg;
    }

    public String getThemeName() {
        return this.themeName;
    }

    public String getVideoResource() {
        return this.videoResource;
    }

    public int getBgType() {
        return this.bgType;
    }

    public String getDurationRange() {
        return this.durationRange;
    }

    public List<AudioSceneInfoEntity> getAudioInfo() {
        List<AudioSceneInfoEntity> list = this.audioInfo;
        if (list == null || list.isEmpty()) {
            return getTestData();
        }
        return this.audioInfo;
    }

    private List<AudioSceneInfoEntity> getTestData() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            AudioSceneInfoEntity audioSceneInfoEntity = new AudioSceneInfoEntity();
            audioSceneInfoEntity.setAudioId(i);
            audioSceneInfoEntity.setCollect(false);
            arrayList.add(audioSceneInfoEntity);
        }
        return arrayList;
    }

    public static class AudioSceneInfoEntity implements Serializable {
        private long audioId;
        private int beCollectBindId;
        private int bindId;
        private String detailInfo;
        private boolean isCollect;
        private List<ThemeAudioInfoEntity> mDetailInfo;
        private String melody;

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
                    Log.d(ZeroThemeInfoEntity.TAG, "解析场景信息失败:" + e.getMessage());
                    return null;
                }
            }
            return this.mDetailInfo;
        }

        public boolean isCollect() {
            return this.isCollect;
        }

        public void setCollect(boolean z) {
            this.isCollect = z;
        }

        public String toString() {
            return "AudioSceneInfoEntity{audioId=" + this.audioId + ", detailInfo='" + this.detailInfo + "', isCollect=" + this.isCollect + ", mDetailInfo=" + getDetailInfo() + '}';
        }
    }
}
