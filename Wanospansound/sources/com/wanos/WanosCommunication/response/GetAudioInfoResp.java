package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.bean.ThemeAudioInfoEntity;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetAudioInfoResp {
    private List<TagGroupDTO> tagGroup;

    public List<TagGroupDTO> getTagGroup() {
        return this.tagGroup;
    }

    public void setTagGroup(List<TagGroupDTO> list) {
        this.tagGroup = list;
    }

    public static class TagGroupDTO implements Serializable {
        private List<ThemeAudioInfoEntity> sounds;
        private Integer tagId;
        private String tagName;

        public Integer getTagId() {
            return this.tagId;
        }

        public void setTagId(Integer num) {
            this.tagId = num;
        }

        public String getTagName() {
            return this.tagName;
        }

        public void setTagName(String str) {
            this.tagName = str;
        }

        public List<ThemeAudioInfoEntity> getSounds() {
            return this.sounds;
        }

        public void setSounds(List<ThemeAudioInfoEntity> list) {
            this.sounds = list;
        }

        public static class SoundsDTO implements Serializable {
            private String imagePath;
            private Integer isVip;
            private Integer soundId;
            private String soundInfo;
            private String soundName;
            private String wanosPath;

            public Integer getSoundId() {
                return this.soundId;
            }

            public void setSoundId(Integer num) {
                this.soundId = num;
            }

            public String getSoundName() {
                return this.soundName;
            }

            public void setSoundName(String str) {
                this.soundName = str;
            }

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
        }
    }
}
