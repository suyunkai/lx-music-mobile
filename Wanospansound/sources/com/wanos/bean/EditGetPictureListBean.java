package com.wanos.bean;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class EditGetPictureListBean {
    private List<ProjectPictureInfo> list;
    private int page;
    private int page_size;
    private int total;

    public List<ProjectPictureInfo> getList() {
        return this.list;
    }

    public int getPage() {
        return this.page;
    }

    public int getPage_size() {
        return this.page_size;
    }

    public int getTotal() {
        return this.total;
    }

    public void setList(List<ProjectPictureInfo> list) {
        this.list = list;
    }

    public void setPage(int i) {
        this.page = i;
    }

    public void setPage_size(int i) {
        this.page_size = i;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public class ProjectPictureInfo {
        private String id;
        private String name;
        private String picture;

        public ProjectPictureInfo() {
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getPicture() {
            return this.picture;
        }

        public void setPicture(String str) {
            this.picture = str;
        }
    }
}
