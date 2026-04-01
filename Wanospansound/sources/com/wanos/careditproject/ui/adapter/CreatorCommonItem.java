package com.wanos.careditproject.ui.adapter;

import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorCommonItem {
    private Object data;
    private final int id;
    private int type;

    public CreatorCommonItem(int i, int i2, Object obj) {
        this.id = i;
        this.type = i2;
        this.data = obj;
    }

    public int getId() {
        return this.id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CreatorCommonItem creatorCommonItem = (CreatorCommonItem) obj;
        return this.id == creatorCommonItem.id && this.type == creatorCommonItem.type && Objects.equals(this.data, creatorCommonItem.data);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.id), Integer.valueOf(this.type), this.data);
    }
}
