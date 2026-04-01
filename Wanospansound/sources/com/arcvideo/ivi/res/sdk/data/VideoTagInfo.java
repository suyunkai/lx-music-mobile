package com.arcvideo.ivi.res.sdk.data;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class VideoTagInfo {
    private List<TagChildrenInfo> children;
    private String id;
    private String name;

    public final List<TagChildrenInfo> getChildren() {
        return this.children;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final void setChildren(List<TagChildrenInfo> list) {
        this.children = list;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final void setName(String str) {
        this.name = str;
    }
}
