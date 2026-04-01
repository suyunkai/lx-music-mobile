package com.wanos.media.entity;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroThemeListEntity {
    private List<SpaceThemeBaseInfo> ThemeList;
    private int total;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public List<SpaceThemeBaseInfo> getThemeList() {
        List<SpaceThemeBaseInfo> list = this.ThemeList;
        return list == null ? Collections.emptyList() : list;
    }

    public void setThemeList(List<SpaceThemeBaseInfo> list) {
        this.ThemeList = list;
    }
}
