package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class ModuleCoverDetailsBean {
    private String content;
    private String coverImage;
    private long endTime;
    private int id;
    private String remark;
    private long startTime;
    private String title;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getCoverImage() {
        return this.coverImage;
    }

    public void setCoverImage(String str) {
        this.coverImage = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }
}
