package com.wanos.commonlibrary.bean;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SingerInfoBean {
    String avatar;
    String introduction;
    String name;
    long singerId;
    List<Long> tagIds;
    List<TagInfoBean> tagList;

    public long getSingerId() {
        return this.singerId;
    }

    public void setSingerId(long j) {
        this.singerId = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String str) {
        this.introduction = str;
    }

    public List<Long> getTagIds() {
        return this.tagIds;
    }

    public void setTagIds(List<Long> list) {
        this.tagIds = list;
    }

    public List<TagInfoBean> getTagList() {
        return this.tagList;
    }

    public void setTagList(List<TagInfoBean> list) {
        this.tagList = list;
    }
}
