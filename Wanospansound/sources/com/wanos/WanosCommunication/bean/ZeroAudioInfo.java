package com.wanos.WanosCommunication.bean;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.StringUtils;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroAudioInfo {
    private List<SoundListEntity> soundList;
    private int total;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public List<SoundListEntity> getSoundList() {
        return this.soundList;
    }

    public void setSoundList(List<SoundListEntity> list) {
        this.soundList = list;
    }

    public String toString() {
        return "ZeroAudioInfo{total=" + this.total + ", soundList=" + this.soundList + '}';
    }

    public static class SoundListEntity implements Serializable {
        private String background;
        private boolean downloading;
        private String imagePath;
        private int isFree;
        private Integer isVip;
        private String soundIcon;
        private int soundId;
        private String soundInfo;
        private String soundLocalPath;
        private String soundName;
        private String soundPath;
        private String wanosPath;
        private boolean isSelect = false;
        private boolean insertResult = false;

        public String getWanosPath() {
            return this.wanosPath;
        }

        public void setWanosPath(String str) {
            this.wanosPath = str;
        }

        public String getImagePath() {
            return this.imagePath;
        }

        public void setImagePath(String str) {
            this.imagePath = str;
        }

        public String getSoundInfo() {
            return this.soundInfo;
        }

        public void setSoundInfo(String str) {
            this.soundInfo = str;
        }

        public Integer getIsVip() {
            return this.isVip;
        }

        public void setIsVip(Integer num) {
            this.isVip = num;
        }

        public String getBackground() {
            return this.background;
        }

        public void setBackground(String str) {
            this.background = str;
        }

        public boolean isCacheFile() {
            return FileUtils.isFileExists(this.soundLocalPath);
        }

        public boolean isDownloading() {
            return this.downloading;
        }

        public void setDownloading(boolean z) {
            this.downloading = z;
        }

        public int getSoundId() {
            return this.soundId;
        }

        public void setSoundId(int i) {
            this.soundId = i;
        }

        public String getSoundName() {
            return StringUtils.isEmpty(this.soundName) ? "-" : this.soundName;
        }

        public void setSoundName(String str) {
            this.soundName = str;
        }

        public String getSoundIcon() {
            return this.soundIcon;
        }

        public void setSoundIcon(String str) {
            this.soundIcon = str;
        }

        public int getIsFree() {
            return this.isFree;
        }

        public void setIsFree(int i) {
            this.isFree = i;
        }

        public String getSoundPath() {
            return this.soundPath;
        }

        public void setSoundPath(String str) {
            this.soundPath = str;
        }

        public boolean isSelect() {
            return this.isSelect;
        }

        public void setSelect(boolean z) {
            this.isSelect = z;
        }

        public String getSoundLocalPath() {
            return this.soundLocalPath;
        }

        public void setSoundLocalPath(String str) {
            this.soundLocalPath = str;
        }

        public boolean isInsertResult() {
            return this.insertResult;
        }

        public void setInsertResult(boolean z) {
            this.insertResult = z;
        }
    }
}
