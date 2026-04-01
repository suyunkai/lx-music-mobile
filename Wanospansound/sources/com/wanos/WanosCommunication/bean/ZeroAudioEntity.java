package com.wanos.WanosCommunication.bean;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroAudioEntity implements Serializable {
    private List<TagsEntity> tags;
    private int total;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public List<TagsEntity> getTags() {
        return this.tags;
    }

    public void setTags(List<TagsEntity> list) {
        this.tags = list;
    }

    public static class TagsEntity implements Serializable {
        private int tagId;
        private String tagName;

        public String getTagName() {
            return this.tagName;
        }

        public int getTagId() {
            return this.tagId;
        }
    }
}
