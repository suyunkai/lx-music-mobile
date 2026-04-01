package com.wanos.careditproject.event;

import com.wanos.WanosCommunication.bean.MaterialTypeInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialPlayEvent {
    private MaterialTypeInfoBean materialDataInfo;
    private MediaPlayerEnum.CallBackState status;

    public MaterialPlayEvent(MediaPlayerEnum.CallBackState callBackState, MaterialTypeInfoBean materialTypeInfoBean) {
        this.status = callBackState;
        this.materialDataInfo = materialTypeInfoBean;
    }

    public void setStatus(MediaPlayerEnum.CallBackState callBackState) {
        this.status = callBackState;
    }

    public MediaPlayerEnum.CallBackState getStatus() {
        return this.status;
    }

    public MaterialTypeInfoBean getMaterialDataInfo() {
        return this.materialDataInfo;
    }

    public void setMaterialDataInfo(MaterialTypeInfoBean materialTypeInfoBean) {
        this.materialDataInfo = materialTypeInfoBean;
    }
}
