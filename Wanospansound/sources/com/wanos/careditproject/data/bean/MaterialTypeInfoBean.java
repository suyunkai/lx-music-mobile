package com.wanos.careditproject.data.bean;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialTypeInfoBean {
    private String ballRouteMap;
    private int channel;
    private List<MaterialTypeInfoBean> children;
    private boolean collect;
    private String createTime;
    private int depth;
    private int dirId;
    private String format;
    private int id;
    private int isWanosAudio;
    private List<MaterialTypeInfoBean> kids;
    private int m_id;
    private String m_name;
    private int m_parentId;
    private String name;
    private int parent;
    private int parentId;
    private int sampleNum;
    private int sampleRate;
    private int sampleTate;
    private String timeLen;
    private int transitionWanosStatus;
    private int type;
    private int typeId;
    private String updateTime;
    private String url;
    private int urlSize;
    private String urlSrc;
    private int urlWanosSize;
    private int userId;
    private int viewType = 1;
    private boolean isExpansion = false;

    public int getId() {
        if (this.viewType == 1) {
            return this.id;
        }
        return this.m_id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        if (this.viewType == 1) {
            return this.name;
        }
        return this.m_name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int i) {
        this.depth = i;
    }

    public int getParent() {
        return this.parent;
    }

    public void setParent(int i) {
        this.parent = i;
    }

    public List<MaterialTypeInfoBean> getChildren() {
        return this.children;
    }

    public void setChildren(List<MaterialTypeInfoBean> list) {
        this.children = list;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int i) {
        this.parentId = i;
    }

    public List<MaterialTypeInfoBean> getKids() {
        return this.kids;
    }

    public void setKids(List<MaterialTypeInfoBean> list) {
        this.kids = list;
    }

    public int getM_id() {
        return this.m_id;
    }

    public void setM_id(int i) {
        this.m_id = i;
    }

    public int getSampleNum() {
        return this.sampleNum;
    }

    public void setSampleNum(int i) {
        this.sampleNum = i;
    }

    public int getChannel() {
        return this.channel;
    }

    public void setChannel(int i) {
        this.channel = i;
    }

    public String getM_name() {
        return this.m_name;
    }

    public void setM_name(String str) {
        this.m_name = str;
    }

    public String getUrlSrc() {
        return this.urlSrc;
    }

    public void setUrlSrc(String str) {
        this.urlSrc = str;
    }

    public String getTimeLen() {
        return this.timeLen;
    }

    public void setTimeLen(String str) {
        this.timeLen = str;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public String getUrlSrcWanos() {
        return this.url;
    }

    public void setUrlSrcWanos(String str) {
        this.url = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int i) {
        this.typeId = i;
    }

    public int getSampleTate() {
        return this.sampleTate;
    }

    public void setSampleTate(int i) {
        this.sampleTate = i;
    }

    public boolean isCollect() {
        return this.collect;
    }

    public void setCollect(boolean z) {
        this.collect = z;
    }

    public int getDirId() {
        return this.dirId;
    }

    public void setDirId(int i) {
        this.dirId = i;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public int getM_parentId() {
        return this.m_parentId;
    }

    public void setM_parentId(int i) {
        this.m_parentId = i;
    }

    public int getIsWanosAudio() {
        return this.isWanosAudio;
    }

    public void setIsWanosAudio(int i) {
        this.isWanosAudio = i;
    }

    public int getUrlSize() {
        return this.urlSize;
    }

    public void setUrlSize(int i) {
        this.urlSize = i;
    }

    public int getUrlWanosSize() {
        return this.urlWanosSize;
    }

    public void setUrlWanosSize(int i) {
        this.urlWanosSize = i;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(int i) {
        this.sampleRate = i;
    }

    public String getBallRouteMap() {
        return this.ballRouteMap;
    }

    public void setBallRouteMap(String str) {
        this.ballRouteMap = str;
    }

    public int getTransitionWanosStatus() {
        return this.transitionWanosStatus;
    }

    public void setTransitionWanosStatus(int i) {
        this.transitionWanosStatus = i;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int i) {
        this.viewType = i;
    }

    public boolean isExpansion() {
        return this.isExpansion;
    }

    public void setExpansion(boolean z) {
        this.isExpansion = z;
    }
}
