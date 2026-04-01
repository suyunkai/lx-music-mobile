package com.wanos.careditproject.event;

import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;

/* JADX INFO: loaded from: classes3.dex */
public class CollectDataInfoEvent {
    private MaterialTypeInfoBean curBean;
    private int position;
    private int titleType;

    public CollectDataInfoEvent(int i, int i2, MaterialTypeInfoBean materialTypeInfoBean) {
        this.titleType = i;
        this.position = i2;
        this.curBean = materialTypeInfoBean;
    }

    public int getTitleType() {
        return this.titleType;
    }

    public void setTitleType(int i) {
        this.titleType = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public MaterialTypeInfoBean getCurBean() {
        return this.curBean;
    }

    public void setCurBean(MaterialTypeInfoBean materialTypeInfoBean) {
        this.curBean = materialTypeInfoBean;
    }
}
