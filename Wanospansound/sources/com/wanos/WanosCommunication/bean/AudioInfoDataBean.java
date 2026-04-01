package com.wanos.WanosCommunication.bean;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioInfoDataBean {
    String avatar;
    int id;
    boolean likeStatus;
    int listenTimes;
    String lrcPath;
    String name;
    String path;
    List<musicSinger> singer;
    float timeLen;

    public class TAG {
        int id;
        String name;

        public TAG() {
        }
    }

    public class musicSinger {
        int id;
        String name;

        public musicSinger() {
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

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public float getTimeLen() {
        return this.timeLen;
    }

    public void setTimeLen(float f) {
        this.timeLen = f;
    }

    public int getListenTimes() {
        return this.listenTimes;
    }

    public void setListenTimes(int i) {
        this.listenTimes = i;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public boolean isLikeStatus() {
        return this.likeStatus;
    }

    public void setLikeStatus(boolean z) {
        this.likeStatus = z;
    }

    public List<musicSinger> getSinger() {
        return this.singer;
    }

    public void setSinger(List<musicSinger> list) {
        this.singer = list;
    }

    public String getLrcPath() {
        return this.lrcPath;
    }

    public void setLrcPath(String str) {
        this.lrcPath = str;
    }
}
