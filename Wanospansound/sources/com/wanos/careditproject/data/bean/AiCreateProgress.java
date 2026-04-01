package com.wanos.careditproject.data.bean;

import com.blankj.utilcode.util.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AiCreateProgress implements IAiCreateState {
    private final String msg;

    public AiCreateProgress(int i) {
        this.msg = StringUtils.getString(i);
    }

    public String getMsg() {
        return this.msg;
    }
}
