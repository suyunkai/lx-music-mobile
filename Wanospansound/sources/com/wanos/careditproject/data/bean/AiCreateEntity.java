package com.wanos.careditproject.data.bean;

import com.blankj.utilcode.util.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AiCreateEntity {
    private String trackId;

    public String getTrack_id() {
        return StringUtils.isEmpty(this.trackId) ? "" : this.trackId;
    }
}
