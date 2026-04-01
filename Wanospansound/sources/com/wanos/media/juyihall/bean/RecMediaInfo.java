package com.wanos.media.juyihall.bean;

import android.text.TextUtils;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicAlbumInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SingerInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.juyihall.utils.Utils;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RecMediaInfo {
    List<SingerInfoBean> Performer;
    MediaAlbumInfo albumInfo;
    List<SingerInfoBean> author;
    String avatar;
    int freeEndTime;
    String introduction;
    boolean isCollect;
    boolean isFree;
    boolean isVip;
    String lrcPath;
    String mediaId;
    String mediaName;
    int sortOrder;
    String wanosPath;
    int no = 1;
    int contentType = 1;
    float duration = 6.0f;
    int canPreview = 10;
    int previewStartTime = 11;
    int previewEndTime = 12;
    int wanosSize = 15;

    public MusicInfoBean toMediaInfoBean() {
        MediaAlbumInfo mediaAlbumInfo;
        MusicInfoBean musicInfoBean = new MusicInfoBean();
        musicInfoBean.setMusicId(Utils.setFormatId(this.mediaId));
        musicInfoBean.setName(this.mediaName);
        musicInfoBean.setVipMusic(this.isVip);
        musicInfoBean.setFree(this.isFree);
        musicInfoBean.setDuration(this.duration);
        musicInfoBean.setFreeEndTime(this.freeEndTime);
        musicInfoBean.setPreviewEndTime(this.previewEndTime);
        musicInfoBean.setPreviewStartTime(this.previewStartTime);
        musicInfoBean.setLrcPath(this.lrcPath);
        musicInfoBean.setMusicSize(this.wanosSize);
        musicInfoBean.setContentType(this.contentType);
        musicInfoBean.setCollection(this.isCollect);
        musicInfoBean.setAudioIndex(this.sortOrder);
        MediaAlbumInfo mediaAlbumInfo2 = this.albumInfo;
        if (mediaAlbumInfo2 != null) {
            musicInfoBean.setAlbumList(mediaAlbumInfo2.toMusicAlbumInfo());
            musicInfoBean.setAlbumId(Utils.setFormatId(this.albumInfo.id));
        }
        List<SingerInfoBean> list = this.author;
        if (list != null && !list.isEmpty()) {
            musicInfoBean.setSingerList(this.author);
        } else {
            musicInfoBean.setSingerList(this.Performer);
        }
        if (TextUtils.isEmpty(this.avatar) && (mediaAlbumInfo = this.albumInfo) != null && !TextUtils.isEmpty(mediaAlbumInfo.avatar)) {
            musicInfoBean.setAvatar(this.albumInfo.avatar);
        } else {
            musicInfoBean.setAvatar(this.avatar);
        }
        musicInfoBean.setMusicPath(this.wanosPath);
        return musicInfoBean;
    }

    public static class MediaAlbumInfo {
        String avatar;
        String id;
        String name;

        public MusicAlbumInfo toMusicAlbumInfo() {
            MusicAlbumInfo musicAlbumInfo = new MusicAlbumInfo();
            musicAlbumInfo.setAlbumId(Utils.setFormatId(this.id));
            musicAlbumInfo.setAlbumName(this.name);
            musicAlbumInfo.setAvatar(this.avatar);
            return musicAlbumInfo;
        }
    }

    public static MediaInfo createRecommendMediaInfo(MusicInfoBean musicInfoBean) {
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
        mediaInfo.setGroupId(Utils.setFormatId(-12L));
        mediaInfo.setMediaGroupType(-12L);
        mediaInfo.setMusicInfoBean(musicInfoBean);
        if (musicInfoBean.getContentType() > 2) {
            mediaInfo.setAudioBookInfoBean(AudioBookMineChapterItemBean.createFrom(musicInfoBean));
        }
        return mediaInfo;
    }
}
