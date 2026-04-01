package com.baidubce.services.bos.model;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GetObjectAclResponse extends BosResponse {
    public static final int MAX_SUPPORTED_ACL_VERSION = 1;
    private List<Grant> accessControlList;
    private int version = 1;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public List<Grant> getAccessControlList() {
        return this.accessControlList;
    }

    public void setAccessControlList(List<Grant> list) {
        this.accessControlList = list;
    }
}
