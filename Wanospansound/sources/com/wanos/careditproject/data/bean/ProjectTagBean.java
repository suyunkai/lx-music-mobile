package com.wanos.careditproject.data.bean;

import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectTagBean {
    private int id;
    private List<ProjectTagBean> list;
    private String name;
    private boolean selected;

    public boolean equals(Object obj) {
        return false;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<ProjectTagBean> getList() {
        return this.list;
    }

    public void setList(List<ProjectTagBean> list) {
        this.list = list;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.list, Boolean.valueOf(this.selected));
    }
}
