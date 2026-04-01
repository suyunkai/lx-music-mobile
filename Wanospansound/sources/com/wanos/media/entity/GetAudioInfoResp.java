package com.wanos.media.entity;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetAudioInfoResp implements Serializable {
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
    }
}
