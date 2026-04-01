package com.wanos.WanosCommunication.bean;

import com.wanos.commonlibrary.bean.IPlayable;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.TagInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicGroupInfo implements Serializable, IPlayable {
    String avatar;
    String introduction;
    boolean isCollection;
    int musicCount;
    long musicGroupId;
    List<Long> musicIds;
    List<MusicInfoBean> musicList;
    String name;
    MediaPlayerEnum.CallBackState playStatus;
    List<TagInfoBean> tagList;
    List<Long> tags;

    public long getMusicGroupId() {
        return this.musicGroupId;
    }

    public void setMusicGroupId(long j) {
        this.musicGroupId = j;
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

    public List<Long> getMusicIds() {
        return this.musicIds;
    }

    public void setMusicIds(List<Long> list) {
        this.musicIds = list;
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

    @Override // com.wanos.commonlibrary.bean.IPlayable
    public void setPlayStatus(MediaPlayerEnum.CallBackState callBackState) {
        this.playStatus = callBackState;
    }

    @Override // com.wanos.commonlibrary.bean.IPlayable
    public MediaPlayerEnum.CallBackState getPlayStatus() {
        return this.playStatus;
    }
}
