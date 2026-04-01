package com.wanos.careditproject.data.bean;

/* JADX INFO: loaded from: classes3.dex */
public class CloudInfoEntity {
    private int id;
    private String name;
    private int parent_id;

    public int getId() {
        return this.id;
    }

    public int getParent_id() {
        return this.parent_id;
    }

    public String getName() {
        return this.name;
    }

    public CloudInfoEntity(int i, String str) {
        this.id = i;
        this.name = str;
    }
}
