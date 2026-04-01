package com.wanos.WanosCommunication.bean;

import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import com.wanos.commonlibrary.bean.AudioBookSpeakerBean;
import com.wanos.commonlibrary.bean.IPlayable;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookAlbumInfoBean implements IPlayable, Serializable {
    private List<AudioBookSpeakerBean> author;
    private String avatar;
    private int canPreview;
    private List<AudioBookChapterItemBean> chapterList;
    private String description;
    private long id;
    private int isCollect;
    private int isPay;
    private int isVip;
    private String name;
    MediaPlayerEnum.CallBackState playStatus;
    private List<AudioBookSpeakerBean> speaker;
    private String subtitle;
    private int writingStatus;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int getIsVip() {
        return this.isVip;
    }

    public void setIsVip(int i) {
        this.isVip = i;
    }

    public int getIsPay() {
        return this.isPay;
    }

    public void setIsPay(int i) {
        this.isPay = i;
    }

    public int getCanPreview() {
        return this.canPreview;
    }

    public void setCanPreview(int i) {
        this.canPreview = i;
    }

    public int getWritingStatus() {
        return this.writingStatus;
    }

    public void setWritingStatus(int i) {
        this.writingStatus = i;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public List<AudioBookChapterItemBean> getChapterList() {
        return this.chapterList;
    }

    public void setChapterList(List<AudioBookChapterItemBean> list) {
        this.chapterList = list;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public AudioBookSpeakerBean getSpeaker() {
        List<AudioBookSpeakerBean> list = this.author;
        if (list == null || list.size() < 1) {
            return null;
        }
        return this.author.get(0);
    }

    public void setSpeaker(AudioBookSpeakerBean audioBookSpeakerBean) {
        ArrayList arrayList = new ArrayList();
        this.author = arrayList;
        arrayList.add(audioBookSpeakerBean);
    }

    public AudioBookSpeakerBean getAuthor() {
        if (this.author.size() >= 1) {
            return this.author.get(0);
        }
        return null;
    }

    public void setAuthor(AudioBookSpeakerBean audioBookSpeakerBean) {
        ArrayList arrayList = new ArrayList();
        this.author = arrayList;
        arrayList.add(audioBookSpeakerBean);
    }

    public int getIsCollect() {
        return this.isCollect;
    }

    public void setIsCollect(int i) {
        this.isCollect = i;
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
