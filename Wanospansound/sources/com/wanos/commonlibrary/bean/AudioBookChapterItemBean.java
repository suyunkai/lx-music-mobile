package com.wanos.commonlibrary.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookChapterItemBean implements Serializable {
    protected String avatar;
    protected int canPreview;
    protected long duration;
    protected long id;
    protected int index;
    protected String introduction;
    protected int isCollect;
    protected int isPay;
    protected int isVip;
    protected String name;
    protected String path;
    protected String statusMsg;

    public AudioBookChapterItemBean() {
        this.index = 0;
    }

    public AudioBookChapterItemBean(int i, int i2) {
        this.index = 0;
        this.id = i;
        this.isCollect = 0;
        this.isVip = 2;
        this.isPay = 1;
        this.canPreview = 1;
        this.name = i + "《哈利·波特与魔法石》有声预告片";
        this.avatar = "https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/15.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032251;1706672251&q-key-time=1698032251;1706672251&q-header-list=&q-url-param-list=&q-signature=63c227dd42a7693e247bc363092bbe17aff0c6c1";
        this.statusMsg = "";
        this.index = i2;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int getIsCollect() {
        return this.isCollect;
    }

    public void setIsCollect(int i) {
        this.isCollect = i;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public int getIsVip() {
        return this.isVip;
    }

    public void setIsVip(int i) {
        this.isVip = i;
    }

    public int getIsPay() {
        return this.isPay;
    }

    public void setIsPay(int i) {
        this.isPay = i;
    }

    public int getCanPreview() {
        return this.canPreview;
    }

    public void setCanPreview(int i) {
        this.canPreview = i;
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

    public String getStatusMsg() {
        return this.statusMsg;
    }

    public void setStatusMsg(String str) {
        this.statusMsg = str;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String str) {
        this.introduction = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }
}
