package com.wanos.careditproject.data.bean;

/* JADX INFO: loaded from: classes3.dex */
public class AiCreateSuccess implements IAiCreateState {
    private final AiFindCreateStateEntity data;

    public AiCreateSuccess(AiFindCreateStateEntity aiFindCreateStateEntity) {
        this.data = aiFindCreateStateEntity;
    }

    public AiFindCreateStateEntity getData() {
        return this.data;
    }
}
