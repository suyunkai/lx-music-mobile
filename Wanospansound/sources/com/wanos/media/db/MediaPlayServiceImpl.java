package com.wanos.media.db;

import android.content.Context;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.bean.MediaMusicGorupDetailBean;
import com.wanos.WanosCommunication.response.GetAudioBookChapterListResponse;
import com.wanos.WanosCommunication.response.GetMusicGroupDetailResponse;
import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.commonlibrary.service.MediaPlayService;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.cacheData.audiobook.AudioBookChaptersCache;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayServiceImpl implements IProvider, MediaPlayService {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.commonlibrary.service.MediaPlayService
    public <T> void playAudioBook(T data, final Context context) {
        if (data instanceof AudioBookAlbumInfoBean) {
            final AudioBookAlbumInfoBean audioBookAlbumInfoBean = (AudioBookAlbumInfoBean) data;
            WanosDbUtils.getAudioBookHistoryItem(audioBookAlbumInfoBean.getId(), new DbCallBack<AudioBookMineChapterItemBean>() { // from class: com.wanos.media.db.MediaPlayServiceImpl.1
                @Override // com.wanos.media.db.DbCallBack
                public void callBackData(AudioBookMineChapterItemBean data2) {
                    if (data2 != null) {
                        MediaPlayServiceImpl.toPlay(data2, context);
                    } else {
                        final int i = 1;
                        WanOSRetrofitUtil.getAudioBookChapterListV1(1, 20, audioBookAlbumInfoBean.getId(), new ResponseCallBack<GetAudioBookChapterListResponse>(context) { // from class: com.wanos.media.db.MediaPlayServiceImpl.1.1
                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseFailure(int code, String msg) {
                            }

                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseSuccessful(GetAudioBookChapterListResponse response) {
                                if (response.data == null || response.data.list == null) {
                                    return;
                                }
                                List<AudioBookChapterItemBean> listAddAll = AudioBookChaptersCache.getInstance().addAll(audioBookAlbumInfoBean.getId(), i, response.data.list, 20);
                                if (listAddAll.size() > 0) {
                                    AudioBookChapterItemBean audioBookChapterItemBean = listAddAll.get(0);
                                    try {
                                        Gson gson = new Gson();
                                        AudioBookMineChapterItemBean audioBookMineChapterItemBean = (AudioBookMineChapterItemBean) gson.fromJson(gson.toJson(audioBookChapterItemBean), AudioBookMineChapterItemBean.class);
                                        audioBookMineChapterItemBean.setRadioId(audioBookAlbumInfoBean.getId());
                                        audioBookMineChapterItemBean.setRadioName(audioBookAlbumInfoBean.getName());
                                        audioBookMineChapterItemBean.setAvatar(audioBookAlbumInfoBean.getAvatar());
                                        audioBookMineChapterItemBean.setSpeaker(audioBookAlbumInfoBean.getSpeaker());
                                        audioBookMineChapterItemBean.setAuthor(audioBookAlbumInfoBean.getAuthor());
                                        audioBookMineChapterItemBean.setPageSize(20);
                                        WanosDbUtils.updateAudioBookHistory(audioBookMineChapterItemBean, null);
                                        MediaPlayServiceImpl.toPlay(audioBookMineChapterItemBean, context);
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public static void toPlay(AudioBookMineChapterItemBean bean, Context context) {
        if (bean.getIsVip() == 1 && !UserInfoUtil.isVipAndUnexpired()) {
            ((WanosBaseActivity) context).openWeChatPayActivity();
            return;
        }
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setGroupId(bean.getRadioId());
        mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Audiobook);
        mediaInfo.setAudioBookInfoBean(bean);
        MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
    }

    @Override // com.wanos.commonlibrary.service.MediaPlayService
    public void playMediaInfo(MediaInfo info) {
        MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(info);
    }

    @Override // com.wanos.commonlibrary.service.MediaPlayService
    public void playMusicAlbum(String albumId) {
        WanOSRetrofitUtil.getMusicGroupDetail(Long.valueOf(albumId).longValue(), 1, 10, new ResponseCallBack<GetMusicGroupDetailResponse>(null) { // from class: com.wanos.media.db.MediaPlayServiceImpl.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicGroupDetailResponse response) {
                MediaMusicGorupDetailBean mediaMusicGorupDetailBean = response.data;
                if (mediaMusicGorupDetailBean.getMusicGroupInfo() != null) {
                    MediaInfo mediaInfo = new MediaInfo();
                    mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                    mediaInfo.setGroupId(mediaMusicGorupDetailBean.getMusicGroupInfo().getMusicGroupId());
                    mediaInfo.setMediaGroupType(-6L);
                    mediaInfo.setMusicInfoBean(mediaMusicGorupDetailBean.getMusicGroupInfo().getMusicList().get(0));
                    ServiceRouter.getMediaPlayService().playMediaInfo(mediaInfo);
                }
            }
        });
    }

    @Override // com.wanos.commonlibrary.service.MediaPlayService
    public void resume() {
        MediaPlayEngine.getInstance().getMediaPlayerService().start();
    }

    @Override // com.wanos.commonlibrary.service.MediaPlayService
    public void pause() {
        MediaPlayEngine.getInstance().getMediaPlayerService().pause();
    }
}
