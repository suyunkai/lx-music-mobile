package com.wanos.commonlibrary.bean;

import com.google.gson.annotations.SerializedName;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TagInfoBean implements Serializable, IPlayable {
    List<TagInfoBean> children;
    long mainType;
    String name;

    @SerializedName("Parent")
    long parent;
    MediaPlayerEnum.CallBackState playStatus;
    long tagId;

    public long getTagId() {
        return this.tagId;
    }

    public void setTagId(long j) {
        this.tagId = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getParent() {
        return this.parent;
    }

    public void setParent(long j) {
        this.parent = j;
    }

    public long getMainType() {
        return this.mainType;
    }

    public void setMainType(long j) {
        this.mainType = j;
    }

    public List<TagInfoBean> getChildren() {
        return this.children;
    }

    public void setChildren(List<TagInfoBean> list) {
        this.children = list;
    }

    @Override // com.wanos.commonlibrary.bean.IPlayable
    public void setPlayStatus(MediaPlayerEnum.CallBackState callBackState) {
        this.playStatus = callBackState;
    }

    @Override // com.wanos.commonlibrary.bean.IPlayable
    public MediaPlayerEnum.CallBackState getPlayStatus() {
        return this.playStatus;
    }
}
