package com.wanos.commonlibrary.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookMineChapterItemBean extends AudioBookChapterItemBean {
    private int pageSize;
    private long progress;
    private List<AudioBookSpeakerBean> radioAuthor;
    protected long radioId;
    protected String radioName;
    private List<AudioBookSpeakerBean> speaker;
    protected int total;

    public AudioBookMineChapterItemBean() {
        this.total = 0;
    }

    public AudioBookMineChapterItemBean(int i, int i2) {
        super(i, i2);
        this.total = 0;
        setRadioId(i2);
    }

    public long getRadioId() {
        return this.radioId;
    }

    public void setRadioId(long j) {
        this.radioId = j;
    }

    public String getRadioName() {
        return this.radioName;
    }

    public void setRadioName(String str) {
        this.radioName = str;
    }

    public AudioBookSpeakerBean getSpeaker() {
        List<AudioBookSpeakerBean> list = this.radioAuthor;
        if (list == null || list.size() < 1) {
            return null;
        }
        return this.radioAuthor.get(0);
    }

    public void setSpeaker(AudioBookSpeakerBean audioBookSpeakerBean) {
        ArrayList arrayList = new ArrayList();
        this.radioAuthor = arrayList;
        arrayList.add(audioBookSpeakerBean);
    }

    public AudioBookSpeakerBean getAuthor() {
        List<AudioBookSpeakerBean> list = this.speaker;
        if (list == null || list.size() < 1) {
            return null;
        }
        return this.speaker.get(0);
    }

    public void setAuthor(AudioBookSpeakerBean audioBookSpeakerBean) {
        ArrayList arrayList = new ArrayList();
        this.speaker = arrayList;
        arrayList.add(audioBookSpeakerBean);
    }

    public long getProgress() {
        return this.progress;
    }

    public void setProgress(long j) {
        this.progress = j;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public static AudioBookMineChapterItemBean createFrom(MusicInfoBean musicInfoBean) {
        AudioBookMineChapterItemBean audioBookMineChapterItemBean = new AudioBookMineChapterItemBean();
        audioBookMineChapterItemBean.setRadioId(musicInfoBean.getAlbumId());
        audioBookMineChapterItemBean.setId(musicInfoBean.getMusicId());
        audioBookMineChapterItemBean.setName(musicInfoBean.getName());
        audioBookMineChapterItemBean.setIsVip(musicInfoBean.isVipMusic() ? 1 : 0);
        audioBookMineChapterItemBean.setDuration((long) musicInfoBean.getDuration());
        audioBookMineChapterItemBean.setIsCollect(musicInfoBean.isCollection() ? 1 : 0);
        audioBookMineChapterItemBean.setPath(musicInfoBean.getMusicPath());
        audioBookMineChapterItemBean.setAvatar(musicInfoBean.getAvatar());
        audioBookMineChapterItemBean.setIndex(musicInfoBean.getAudioIndex());
        if (musicInfoBean.getSingerList() != null && !musicInfoBean.getSingerList().isEmpty()) {
            SingerInfoBean singerInfoBean = musicInfoBean.getSingerList().get(0);
            audioBookMineChapterItemBean.setSpeaker(new AudioBookSpeakerBean((int) singerInfoBean.getSingerId(), singerInfoBean.getName(), singerInfoBean.getAvatar()));
        }
        return audioBookMineChapterItemBean;
    }
}
