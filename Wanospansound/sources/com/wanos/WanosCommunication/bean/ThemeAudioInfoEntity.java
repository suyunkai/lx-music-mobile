package com.wanos.WanosCommunication.bean;

import android.util.Log;
import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.repackaged.com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ThemeAudioInfoEntity implements Serializable {
    private static final String TAG = "wanos[WanosVideoPage]";
    private String imagePath;
    private int isVip;
    private String soundDetail;
    private long soundId;
    private String soundName;
    private String tagName;
    private ThemeSoundInfoEntity themeSoundInfoEntity;
    private String wanosPath;

    public long getSoundId() {
        return this.soundId;
    }

    public String getSoundName() {
        return this.soundName;
    }

    public String getWanosPath() {
        return this.wanosPath;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String str) {
        this.tagName = str;
    }

    public void setSoundId(long j) {
        this.soundId = j;
    }

    public void setSoundName(String str) {
        this.soundName = str;
    }

    public void setWanosPath(String str) {
        this.wanosPath = str;
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    public void setIsVip(int i) {
        this.isVip = i;
    }

    public void setSoundDetail(String str) {
        this.soundDetail = str;
    }

    public void setThemeSoundInfoEntity(ThemeSoundInfoEntity themeSoundInfoEntity) {
        this.themeSoundInfoEntity = themeSoundInfoEntity;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ThemeAudioInfoEntity m372clone() {
        ThemeAudioInfoEntity themeAudioInfoEntity = new ThemeAudioInfoEntity();
        themeAudioInfoEntity.setWanosPath(getWanosPath());
        themeAudioInfoEntity.setSoundId(getSoundId());
        themeAudioInfoEntity.setIsVip(getIsVip());
        themeAudioInfoEntity.setTagName(getTagName());
        themeAudioInfoEntity.setImagePath(getImagePath());
        themeAudioInfoEntity.setSoundName(getSoundName());
        return themeAudioInfoEntity;
    }

    public ThemeSoundInfoEntity getSoundInfo() {
        try {
            if (this.themeSoundInfoEntity == null) {
                this.themeSoundInfoEntity = (ThemeSoundInfoEntity) GsonUtils.fromJson(this.soundDetail, ThemeSoundInfoEntity.class);
            }
            return this.themeSoundInfoEntity;
        } catch (Exception e) {
            Log.d(TAG, "轨迹信息解析失败:" + e.getMessage());
            return null;
        }
    }

    public int getIsVip() {
        return this.isVip;
    }

    public String toString() {
        return "ThemeAudioInfoEntity{soundId=" + this.soundId + ", soundName='" + this.soundName + "', wanosPath='" + this.wanosPath + "', imagePath='" + this.imagePath + "', isVip=" + this.isVip + ", tagName='" + this.tagName + "', themeSoundInfoEntity=" + getSoundInfo() + '}';
    }

    public static class ThemeSoundInfoEntity implements Serializable {
        private float Angle;
        private List<BallRouteList> BallRouteList;
        private int DB;
        private int NumChannels;
        private float Speed;
        private int Volume;

        public void setNumChannels(int i) {
            this.NumChannels = i;
        }

        public void setDB(int i) {
            this.DB = i;
        }

        public void setSpeed(float f) {
            this.Speed = f;
        }

        public void setAngle(float f) {
            this.Angle = f;
        }

        public void setBallRouteList(List<BallRouteList> list) {
            this.BallRouteList = Lists.newArrayList(list);
        }

        public void addBallRouteList(BallRouteList ballRouteList) {
            this.BallRouteList.add(ballRouteList);
        }

        public void clearBallRouteList() {
            this.BallRouteList.clear();
        }

        public void setVolume(int i) {
            this.Volume = i;
        }

        public int getVolume() {
            int i = this.DB;
            return i == 999 ? this.Volume : i;
        }

        public int getDB() {
            return this.DB;
        }

        public int getNumChannels() {
            return this.NumChannels;
        }

        public float getSpeed() {
            return this.Speed;
        }

        public float getAngle() {
            return this.Angle;
        }

        public List<BallRouteList> getBallRouteList() {
            return this.BallRouteList;
        }

        public String toString() {
            return "ThemeSoundInfoEntity{NumChannels=" + this.NumChannels + ", DB=" + this.DB + ", Speed=" + this.Speed + ", Angle=" + this.Angle + ", BallRouteList=" + this.BallRouteList + '}';
        }

        public static class BallRouteList implements Serializable {
            private List<Float> Pos;
            private long Seek;

            public void setSeek(long j) {
                this.Seek = j;
            }

            public void setPos(List<Float> list) {
                this.Pos = list;
            }

            public long getSeek() {
                return this.Seek;
            }

            public List<Float> getPos() {
                return this.Pos;
            }

            public String toString() {
                return "BallRouteList{Seek=" + this.Seek + ", Pos=" + this.Pos + '}';
            }
        }
    }
}
