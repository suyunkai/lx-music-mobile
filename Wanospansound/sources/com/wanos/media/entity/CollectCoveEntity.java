package com.wanos.media.entity;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CollectCoveEntity implements Serializable {
    private List<ImagesDTO> images;
    private Integer total;

    public List<ImagesDTO> getImages() {
        return this.images;
    }

    public void setImages(List<ImagesDTO> list) {
        this.images = list;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer num) {
        this.total = num;
    }

    public static class ImagesDTO {
        private Integer id;
        private Boolean isVip;
        private String name;
        private String path;

        public Integer getId() {
            return this.id;
        }

        public void setId(Integer num) {
            this.id = num;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getPath() {
            return this.path;
        }

        public void setPath(String str) {
            this.path = str;
        }

        public Boolean getIsVip() {
            return this.isVip;
        }

        public void setIsVip(Boolean bool) {
            this.isVip = bool;
        }
    }
}
