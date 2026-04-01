package com.wanos.media.entity;

import com.wanos.WanosCommunication.BaseResponse;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class BaseEntity<D> extends BaseResponse implements Serializable {
    private D data;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public D getData() {
        return this.data;
    }
}
