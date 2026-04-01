package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.bean.BellInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BellListResponse {
    private List<BellInfo> bellList;

    public List<BellInfo> getBellList() {
        return this.bellList;
    }

    public void setBellList(List<BellInfo> list) {
        this.bellList = list;
    }
}
