package com.wanos.media.entity;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BellsEntity implements Serializable {
    private List<BellsInfoEntity> bellList;

    public List<BellsInfoEntity> getBellList() {
        return this.bellList;
    }

    public void setBellList(List<BellsInfoEntity> list) {
        this.bellList = list;
    }

    public static class BellsInfoEntity implements Serializable {
        private String coverImg;
        private int id;
        private boolean isCache;
        private boolean isDownloading;
        private boolean isVip;
        private String name;
        private String path;

        public boolean isCache() {
            return this.isCache;
        }

        public void setCache(boolean z) {
            this.isCache = z;
        }

        public BellsInfoEntity() {
        }

        public BellsInfoEntity(int i, String str, String str2, String str3, boolean z) {
            this.id = i;
            this.name = str;
            this.coverImg = str2;
            this.path = str3;
            this.isVip = z;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getCoverImg() {
            return this.coverImg;
        }

        public void setCoverImg(String str) {
            this.coverImg = str;
        }

        public String getPath() {
            return this.path;
        }

        public void setPath(String str) {
            this.path = str;
        }

        public boolean isVip() {
            return this.isVip;
        }

        public void setVip(boolean z) {
            this.isVip = z;
        }

        public boolean isDownloading() {
            return this.isDownloading;
        }

        public void setDownloading(boolean z) {
            this.isDownloading = z;
        }
    }
}
