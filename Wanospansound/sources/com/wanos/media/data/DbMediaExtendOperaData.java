package com.wanos.media.data;

import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.db.DbCallBack;
import com.wanos.media.db.WanosDbUtils;
import com.wanos.wanosplayermodule.data.ExtendOperaData;
import com.wanos.wanosplayermodule.data.MediaInfoCallBack;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DbMediaExtendOperaData extends ExtendOperaData {
    @Override // com.wanos.wanosplayermodule.data.ExtendOperaData
    public void requestMeidaInfoList(MediaPlayerEnum.MediaType mediaType, final MediaInfoCallBack mediaInfoCallBack) {
        if (mediaType == MediaPlayerEnum.MediaType.Music) {
            WanosDbUtils.getMusicHistoryList(new DbCallBack<List<MusicInfoBean>>() { // from class: com.wanos.media.data.DbMediaExtendOperaData.1
                @Override // com.wanos.media.db.DbCallBack
                public void callBackData(List<MusicInfoBean> data) {
                    if (data != null) {
                        ArrayList arrayList = new ArrayList();
                        for (MusicInfoBean musicInfoBean : data) {
                            MediaInfo mediaInfo = new MediaInfo();
                            mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                            mediaInfo.setGroupId(-5L);
                            mediaInfo.setMediaGroupType(-5L);
                            mediaInfo.setMusicInfoBean(musicInfoBean);
                            arrayList.add(mediaInfo);
                        }
                        MediaInfoCallBack mediaInfoCallBack2 = mediaInfoCallBack;
                        if (mediaInfoCallBack2 != null) {
                            mediaInfoCallBack2.returnQueryMediaInfoList(arrayList);
                        }
                    }
                }
            });
        } else if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
            WanosDbUtils.getAudioBookHistoryList(new DbCallBack<List<AudioBookMineChapterItemBean>>() { // from class: com.wanos.media.data.DbMediaExtendOperaData.2
                @Override // com.wanos.media.db.DbCallBack
                public void callBackData(List<AudioBookMineChapterItemBean> data) {
                    if (data != null) {
                        ArrayList arrayList = new ArrayList();
                        for (AudioBookMineChapterItemBean audioBookMineChapterItemBean : data) {
                            MediaInfo mediaInfo = new MediaInfo();
                            mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Audiobook);
                            mediaInfo.setGroupId(-5L);
                            mediaInfo.setMediaGroupType(-5L);
                            mediaInfo.setAudioBookInfoBean(audioBookMineChapterItemBean);
                            arrayList.add(mediaInfo);
                        }
                        MediaInfoCallBack mediaInfoCallBack2 = mediaInfoCallBack;
                        if (mediaInfoCallBack2 != null) {
                            mediaInfoCallBack2.returnQueryMediaInfoList(arrayList);
                        }
                    }
                }
            });
        }
    }

    @Override // com.wanos.wanosplayermodule.data.ExtendOperaData
    public void updateMeidaInfo(MediaInfo mediaInfo, final MediaInfoCallBack mediaInfoCallBack) {
        if (mediaInfo != null) {
            MediaPlayerEnum.MediaType mediaType = mediaInfo.getMediaType();
            if (mediaType == MediaPlayerEnum.MediaType.Music) {
                MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
                if (musicInfoBean != null) {
                    if (musicInfoBean.getContentType() > 2) {
                        updateAudioBookHistory(mediaInfo.getAudioBookInfoBean(), mediaInfoCallBack);
                        return;
                    } else {
                        WanosDbUtils.updateMusicHistory(musicInfoBean, new DbCallBack<Boolean>() { // from class: com.wanos.media.data.DbMediaExtendOperaData.3
                            @Override // com.wanos.media.db.DbCallBack
                            public void callBackData(Boolean data) {
                                if (mediaInfoCallBack == null || !data.booleanValue()) {
                                    return;
                                }
                                mediaInfoCallBack.mediaInfoUpdateSuccessful();
                            }
                        });
                        return;
                    }
                }
                return;
            }
            if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
                updateAudioBookHistory(mediaInfo.getAudioBookInfoBean(), mediaInfoCallBack);
            }
        }
    }

    private void updateAudioBookHistory(AudioBookMineChapterItemBean audioBookInfoBean, final MediaInfoCallBack mediaInfoCallBack) {
        if (audioBookInfoBean != null) {
            WanosDbUtils.updateAudioBookHistory(audioBookInfoBean, new DbCallBack<Boolean>() { // from class: com.wanos.media.data.DbMediaExtendOperaData.4
                @Override // com.wanos.media.db.DbCallBack
                public void callBackData(Boolean data) {
                    if (mediaInfoCallBack == null || !data.booleanValue()) {
                        return;
                    }
                    mediaInfoCallBack.mediaInfoUpdateSuccessful();
                }
            });
        }
    }
}
