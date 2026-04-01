package com.wanos.media.db;

import android.text.TextUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.reflect.TypeToken;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.AudioBookSpeakerBean;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SingerInfoBean;
import com.wanos.commonlibrary.utils.SystemAndServerTimeSynchUtil;
import com.wanos.media.db.entity.music.DbAudioBookInfo;
import com.wanos.media.db.entity.music.DbMusicInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DbInfoChangeBeanUtils {
    public static MusicInfoBean fromDbMusicToMusic(DbMusicInfo dbMusicInfo) {
        if (dbMusicInfo == null) {
            return null;
        }
        MusicInfoBean musicInfoBean = new MusicInfoBean();
        musicInfoBean.setMusicId(dbMusicInfo.getId());
        musicInfoBean.setAvatar(dbMusicInfo.getAvatar());
        musicInfoBean.setLrcPath(dbMusicInfo.getLrcPath());
        musicInfoBean.setName(dbMusicInfo.getName());
        musicInfoBean.setMusicPath(dbMusicInfo.getPath());
        musicInfoBean.setDuration(dbMusicInfo.getTimeLen());
        musicInfoBean.setCollection(dbMusicInfo.isLikeStatus());
        musicInfoBean.setVipMusic(dbMusicInfo.isVipAuth());
        musicInfoBean.setFree(dbMusicInfo.isFree());
        musicInfoBean.setFreeEndTime(dbMusicInfo.getFreeEndTime());
        musicInfoBean.setPreviewStartTime(dbMusicInfo.getPreviewStartTime());
        musicInfoBean.setPreviewEndTime(dbMusicInfo.getPreviewEndTime());
        if (!TextUtils.isEmpty(dbMusicInfo.getSinger())) {
            musicInfoBean.setSingerList((List) GsonUtils.fromJson(dbMusicInfo.getSinger(), new TypeToken<List<SingerInfoBean>>() { // from class: com.wanos.media.db.DbInfoChangeBeanUtils.1
            }.getType()));
        }
        return musicInfoBean;
    }

    public static List<MusicInfoBean> fromDbMusicListToMusicList(List<DbMusicInfo> dbMusicInfoList) {
        if (dbMusicInfoList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < dbMusicInfoList.size(); i++) {
            arrayList.add(fromDbMusicToMusic(dbMusicInfoList.get(i)));
        }
        return arrayList;
    }

    public static DbMusicInfo fromMusicToDbMusic(MusicInfoBean mediaMusicBean) {
        if (mediaMusicBean == null) {
            return null;
        }
        DbMusicInfo dbMusicInfo = new DbMusicInfo();
        dbMusicInfo.setId(mediaMusicBean.getMusicId());
        dbMusicInfo.setAvatar(mediaMusicBean.getAvatar());
        dbMusicInfo.setLrcPath(mediaMusicBean.getLrcPath());
        dbMusicInfo.setName(mediaMusicBean.getName());
        dbMusicInfo.setPath(mediaMusicBean.getMusicPath());
        dbMusicInfo.setTimeLen(mediaMusicBean.getDuration());
        dbMusicInfo.setLikeStatus(mediaMusicBean.isCollection());
        dbMusicInfo.setVipAuth(mediaMusicBean.isVipMusic());
        dbMusicInfo.setFree(mediaMusicBean.getFree());
        dbMusicInfo.setFreeEndTime(mediaMusicBean.getFreeEndTime());
        dbMusicInfo.setPreviewStartTime(mediaMusicBean.getPreviewStartTime());
        dbMusicInfo.setPreviewEndTime(mediaMusicBean.getPreviewEndTime());
        dbMusicInfo.setDbUpadteTime(SystemAndServerTimeSynchUtil.getSytemTrueTime());
        if (mediaMusicBean.getSingerList() != null && mediaMusicBean.getSingerList().size() > 0) {
            dbMusicInfo.setSinger(GsonUtils.toJson(mediaMusicBean.getSingerList()));
        }
        return dbMusicInfo;
    }

    public static AudioBookMineChapterItemBean fromDbAudioBookToAudioBook(DbAudioBookInfo dbInfo) {
        if (dbInfo == null) {
            return null;
        }
        AudioBookMineChapterItemBean audioBookMineChapterItemBean = new AudioBookMineChapterItemBean();
        audioBookMineChapterItemBean.setId(dbInfo.getId());
        audioBookMineChapterItemBean.setDuration(dbInfo.getDuration());
        audioBookMineChapterItemBean.setIsVip(dbInfo.getIsVip());
        audioBookMineChapterItemBean.setIsPay(dbInfo.getIsPay());
        audioBookMineChapterItemBean.setCanPreview(dbInfo.getCanPreview());
        audioBookMineChapterItemBean.setName(dbInfo.getName());
        audioBookMineChapterItemBean.setAvatar(dbInfo.getAvatar());
        audioBookMineChapterItemBean.setProgress(dbInfo.getProgress());
        audioBookMineChapterItemBean.setIndex(dbInfo.getIndex());
        audioBookMineChapterItemBean.setRadioId(dbInfo.getRadioId());
        audioBookMineChapterItemBean.setRadioName(dbInfo.getRadioName());
        audioBookMineChapterItemBean.setPath(dbInfo.getPath());
        audioBookMineChapterItemBean.setSpeaker(new AudioBookSpeakerBean(dbInfo.getSpeakId(), dbInfo.getSpeakName(), dbInfo.getSpeakAvatar()));
        return audioBookMineChapterItemBean;
    }

    public static List<AudioBookMineChapterItemBean> fromDbAudioBookListToAudioBookList(List<DbAudioBookInfo> dbInfoList) {
        if (dbInfoList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < dbInfoList.size(); i++) {
            arrayList.add(fromDbAudioBookToAudioBook(dbInfoList.get(i)));
        }
        return arrayList;
    }

    public static DbAudioBookInfo fromAudioBookToDbAudioBook(AudioBookMineChapterItemBean bean) {
        DbAudioBookInfo dbAudioBookInfo = new DbAudioBookInfo();
        dbAudioBookInfo.setDbId(bean.getRadioId());
        dbAudioBookInfo.setId(bean.getId());
        dbAudioBookInfo.setDuration(bean.getDuration());
        dbAudioBookInfo.setIsVip(bean.getIsVip());
        dbAudioBookInfo.setIsPay(bean.getIsPay());
        dbAudioBookInfo.setCanPreview(bean.getCanPreview());
        dbAudioBookInfo.setName(bean.getName());
        dbAudioBookInfo.setAvatar(bean.getAvatar());
        dbAudioBookInfo.setProgress(bean.getProgress());
        dbAudioBookInfo.setIndex(bean.getIndex());
        dbAudioBookInfo.setRadioId(bean.getRadioId());
        dbAudioBookInfo.setRadioName(bean.getRadioName());
        dbAudioBookInfo.setPath(bean.getPath());
        dbAudioBookInfo.setDbUpadteTime(SystemAndServerTimeSynchUtil.getSytemTrueTime());
        AudioBookSpeakerBean speaker = bean.getSpeaker();
        if (speaker != null) {
            dbAudioBookInfo.setSpeakId(speaker.getId());
            dbAudioBookInfo.setSpeakName(speaker.getName());
            dbAudioBookInfo.setSpeakAvatar(speaker.getAvatar());
        }
        return dbAudioBookInfo;
    }
}
