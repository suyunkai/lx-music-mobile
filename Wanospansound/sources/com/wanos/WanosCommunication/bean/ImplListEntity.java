package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class ImplListEntity {
    private long id;
    private String title = "落日海岸作品名称";
    private String time = "03:20";
    private boolean selected = false;

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTime() {
        return this.time;
    }

    public boolean isSelected() {
        return this.selected;
    }
}
