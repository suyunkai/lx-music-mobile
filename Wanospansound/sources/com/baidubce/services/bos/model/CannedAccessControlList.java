package com.baidubce.services.bos.model;

import cz.msebera.android.httpclient.client.cache.HeaderConstants;

/* JADX INFO: loaded from: classes.dex */
public enum CannedAccessControlList {
    Private(HeaderConstants.PRIVATE),
    PublicRead("public-read"),
    PublicReadWrite("public-read-write");

    private final String cannedAclHeader;

    CannedAccessControlList(String str) {
        this.cannedAclHeader = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.cannedAclHeader;
    }
}
