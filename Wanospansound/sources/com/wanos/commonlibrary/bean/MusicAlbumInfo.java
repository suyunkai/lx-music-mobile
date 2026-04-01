package com.wanos.commonlibrary.bean;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicAlbumInfo {
    long albumId = 1;
    String albumName;
    String avatar;
    String introduction;
    boolean isCollection;
    int musicCount;
    List<Long> musicIds;
    List<MusicInfoBean> musicList;
    List<Long> singerIds;
    List<SingerInfoBean> singerList;
    List<TagInfoBean> tagList;
    List<Long> tags;

    public long getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(long j) {
        this.albumId = j;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String str) {
        this.introduction = str;
    }

    public List<Long> getTags() {
        return this.tags;
    }

    public void setTags(List<Long> list) {
        this.tags = list;
    }

    public List<Long> getSingerIds() {
        return this.singerIds;
    }

    public void setSingerIds(List<Long> list) {
        this.singerIds = list;
    }

    public int getMusicCount() {
        return this.musicCount;
    }

    public void setMusicCount(int i) {
        this.musicCount = i;
    }

    public List<Long> getMusicIds() {
        return this.musicIds;
    }

    public void setMusicIds(List<Long> list) {
        this.musicIds = list;
    }

    public List<SingerInfoBean> getSingerList() {
        return this.singerList;
    }

    public void setSingerList(List<SingerInfoBean> list) {
        this.singerList = list;
    }

    public List<TagInfoBean> getTagList() {
        return this.tagList;
    }

    public void setTagList(List<TagInfoBean> list) {
        this.tagList = list;
    }

    public List<MusicInfoBean> getMusicList() {
        return this.musicList;
    }

    public void setMusicList(List<MusicInfoBean> list) {
        this.musicList = list;
    }

    public boolean isCollection() {
        return this.isCollection;
    }

    public void setCollection(boolean z) {
        this.isCollection = z;
    }
}
