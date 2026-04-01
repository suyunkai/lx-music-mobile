package com.wanos.commonlibrary.bean;

import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;

/* JADX INFO: loaded from: classes3.dex */
public class MediaInfo {
    private AudioBookMineChapterItemBean audioBookInfoBean;
    private long groupId;
    private long mediaGroupType;
    MediaPlayerEnum.MediaType mediaType;
    private MusicInfoBean musicInfoBean;
    public MediaPlayerEnum.PlayMode playMode;
    int source = 1;

    public long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(long j) {
        this.groupId = j;
    }

    public MediaPlayerEnum.MediaType getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(MediaPlayerEnum.MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public MusicInfoBean getMusicInfoBean() {
        return this.musicInfoBean;
    }

    public void setMusicInfoBean(MusicInfoBean musicInfoBean) {
        this.musicInfoBean = musicInfoBean;
    }

    public AudioBookMineChapterItemBean getAudioBookInfoBean() {
        return this.audioBookInfoBean;
    }

    public void setAudioBookInfoBean(AudioBookMineChapterItemBean audioBookMineChapterItemBean) {
        this.audioBookInfoBean = audioBookMineChapterItemBean;
    }

    public long getMediaGroupType() {
        return this.mediaGroupType;
    }

    public void setMediaGroupType(long j) {
        this.mediaGroupType = j;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int i) {
        this.source = i;
    }
}
