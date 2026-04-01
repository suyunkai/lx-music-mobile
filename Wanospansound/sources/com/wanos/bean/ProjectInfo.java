package com.wanos.bean;

import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectInfo implements Serializable {
    private AutherInfo authorInfo;
    private int canTemplate;
    private int cate;
    private int collectCount;
    private String content;
    private long createTime;
    private float duration;
    private String historyPath;
    private String id;
    private boolean isCollect;
    private boolean isOpen;
    private String noPassReason;
    private String picture;
    private int plantType;
    private int projectType;
    private int publishRange;
    private int relaxStatus;
    private int reviewStatus;
    private int status;
    private String title;
    private long updateTime;
    private int userId;
    private String wanosPath;
    private WorkType workType;

    public boolean equals(Object obj) {
        return false;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getWanosPath() {
        return this.wanosPath;
    }

    public void setWanosPath(String str) {
        this.wanosPath = str;
    }

    public String getHistoryPath() {
        return this.historyPath;
    }

    public void setHistoryPath(String str) {
        this.historyPath = str;
    }

    public AutherInfo getAutherInfo() {
        return this.authorInfo;
    }

    public void setAutherInfo(AutherInfo autherInfo) {
        this.authorInfo = autherInfo;
    }

    public WorkType getWorkType() {
        return this.workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public boolean isCollect() {
        return this.isCollect;
    }

    public void setCollect(boolean z) {
        this.isCollect = z;
    }

    public int getCollectCount() {
        return this.collectCount;
    }

    public void setCollectCount(int i) {
        this.collectCount = i;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public int getCate() {
        return this.cate;
    }

    public void setCate(int i) {
        this.cate = i;
    }

    public int getPlantType() {
        return this.plantType;
    }

    public void setPlantType(int i) {
        this.plantType = i;
    }

    public int getRelaxStatus() {
        return this.relaxStatus;
    }

    public void setRelaxStatus(int i) {
        this.relaxStatus = i;
    }

    public int getCanTemplate() {
        return this.canTemplate;
    }

    public void setCanTemplate(int i) {
        this.canTemplate = i;
    }

    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    public int getPublishRange() {
        return this.publishRange;
    }

    public void setPublishRange(int i) {
        this.publishRange = i;
    }

    public int getReviewStatus() {
        return this.reviewStatus;
    }

    public void setReviewStatus(int i) {
        this.reviewStatus = i;
    }

    public String getNoPassReason() {
        return this.noPassReason;
    }

    public void setNoPassReason(String str) {
        this.noPassReason = str;
    }

    public int hashCode() {
        return Objects.hash(this.id);
    }

    public int getProjectType() {
        return this.projectType;
    }

    public void setProjectType(int i) {
        this.projectType = i;
    }
}
