package com.wanos.commonlibrary.bean;

import com.google.gson.annotations.SerializedName;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.SystemAndServerTimeSynchUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class MusicInfoBean implements Serializable, IPlayable {
    long albumId;
    MusicAlbumInfo albumList;
    int audioIndex;
    String avatar;
    private int contentType = 0;
    float duration;

    @SerializedName("FreeEndTime")
    long freeEndTime;
    int index;
    boolean isCollection;
    boolean isFree;
    String lrcPath;
    long musicId;
    String musicPath;
    long musicSize;
    String name;
    int pageSize;
    MediaPlayerEnum.CallBackState playStatus;
    long previewEndTime;
    long previewStartTime;
    List<Long> singerIds;
    List<SingerInfoBean> singerList;
    List<Long> tagIds;
    List<TagInfoBean> tagList;
    boolean vipMusic;

    public long getMusicId() {
        return this.musicId;
    }

    public void setMusicId(long j) {
        this.musicId = j;
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

    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    public boolean isVipMusic() {
        return this.vipMusic;
    }

    public void setVipMusic(boolean z) {
        this.vipMusic = z;
    }

    public long getPreviewStartTime() {
        return this.previewStartTime;
    }

    public void setPreviewStartTime(long j) {
        this.previewStartTime = j;
    }

    public long getPreviewEndTime() {
        return this.previewEndTime;
    }

    public void setPreviewEndTime(long j) {
        this.previewEndTime = j;
    }

    public List<Long> getSingerIds() {
        return this.singerIds;
    }

    public void setSingerIds(List<Long> list) {
        this.singerIds = list;
    }

    public long getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(long j) {
        this.albumId = j;
    }

    public List<Long> getTagIds() {
        return this.tagIds;
    }

    public void setTagIds(List<Long> list) {
        this.tagIds = list;
    }

    public String getLrcPath() {
        return this.lrcPath;
    }

    public void setLrcPath(String str) {
        this.lrcPath = str;
    }

    public String getMusicPath() {
        return this.musicPath;
    }

    public void setMusicPath(String str) {
        this.musicPath = str;
    }

    public long getMusicSize() {
        return this.musicSize;
    }

    public void setMusicSize(long j) {
        this.musicSize = j;
    }

    public List<SingerInfoBean> getSingerList() {
        return this.singerList;
    }

    public void setSingerList(List<SingerInfoBean> list) {
        this.singerList = list;
    }

    public MusicAlbumInfo getAlbumList() {
        return this.albumList;
    }

    public void setAlbumList(MusicAlbumInfo musicAlbumInfo) {
        this.albumList = musicAlbumInfo;
    }

    public List<TagInfoBean> getTagList() {
        return this.tagList;
    }

    public void setTagList(List<TagInfoBean> list) {
        this.tagList = list;
    }

    public boolean isCollection() {
        return this.isCollection;
    }

    public void setCollection(boolean z) {
        this.isCollection = z;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getAudioIndex() {
        return this.audioIndex;
    }

    public void setAudioIndex(int i) {
        this.audioIndex = i;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
    }

    public boolean getFree() {
        return this.isFree;
    }

    public boolean isFree() {
        if (this.isFree) {
            long j = this.freeEndTime;
            if (j > 0 && j * 1000 > SystemAndServerTimeSynchUtil.getSytemTrueTime()) {
                return true;
            }
        }
        return false;
    }

    public void setFree(boolean z) {
        this.isFree = z;
    }

    public long getFreeEndTime() {
        return this.freeEndTime;
    }

    public void setFreeEndTime(long j) {
        this.freeEndTime = j;
    }

    @Override // com.wanos.commonlibrary.bean.IPlayable
    public void setPlayStatus(MediaPlayerEnum.CallBackState callBackState) {
        this.playStatus = callBackState;
    }

    @Override // com.wanos.commonlibrary.bean.IPlayable
    public MediaPlayerEnum.CallBackState getPlayStatus() {
        return this.playStatus;
    }

    public void setContentType(int i) {
        this.contentType = i;
    }

    public int getContentType() {
        return this.contentType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MusicInfoBean musicInfoBean = (MusicInfoBean) obj;
        return this.musicId == musicInfoBean.musicId && Objects.equals(this.name, musicInfoBean.name);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.musicId), this.name);
    }
}
