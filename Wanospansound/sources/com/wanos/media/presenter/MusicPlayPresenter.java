package com.wanos.media.presenter;

import android.content.Context;
import android.util.Log;
import androidx.media3.extractor.metadata.id3.InternalFrame;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.MediaMusicDetailBean;
import com.wanos.WanosCommunication.response.AudioBookLikeChapterResponse;
import com.wanos.WanosCommunication.response.GetMusicDetailResponse;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.db.DbCallBack;
import com.wanos.media.db.WanosDbUtils;
import com.wanos.media.view.MusicPlayView;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerManager;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicPlayPresenter extends BasePresenter<MusicPlayView> implements MediaPlayerService.OnLoadMediaInfoPlayListCallbackListener {
    public static final String TAG = "wanos:[MusicPlayPresenter]";
    private long groupId;
    private long groupType;

    public MusicPlayPresenter(MusicPlayView view) {
        this.mView = view;
    }

    public void updateOnLoadMediaInfoPlayListCallbackListener() {
        if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
            MediaPlayEngine.getInstance().getMediaPlayerService().addOnLoadMediaInfoPlayListCallbackListener(this);
        }
    }

    @Override // com.wanos.media.presenter.BasePresenter, com.wanos.media.presenter.IPresenter
    public void onDestroy() {
        super.onDestroy();
        if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
            MediaPlayEngine.getInstance().getMediaPlayerService().removeOnLoadMediaInfoPlayListCallbackListener(this);
        }
    }

    public void requestMusicInfoData() {
        MediaInfo curMediaInfo;
        if (MediaPlayEngine.getInstance().getMediaPlayerService() == null || (curMediaInfo = MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo()) == null || curMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || curMediaInfo.getMusicInfoBean() == null) {
            return;
        }
        this.groupId = curMediaInfo.getGroupId();
        this.groupType = curMediaInfo.getMediaGroupType();
        MusicInfoBean musicInfoBean = curMediaInfo.getMusicInfoBean();
        if (this.mView != 0) {
            ((MusicPlayView) this.mView).updateMusicView(musicInfoBean);
            getPlayMusicListData();
        }
    }

    public void getPlayMusicListData() {
        if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
            MediaInfo curMediaInfo = MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo();
            if (curMediaInfo != null) {
                long groupId = curMediaInfo.getGroupId();
                if (groupId == -12 || groupId == -10 || groupId == -11) {
                    MediaPlayEngine.getInstance().getMediaPlayerService().requestPlayingMediaDataListData();
                }
            }
            if (MediaPlayEngine.getInstance().getMediaPlayerService().getPlayListLoadStatus() == MediaPlayerEnum.PlayListLoadStatus.NO_LOAD || MediaPlayEngine.getInstance().getMediaPlayerService().getPlayListLoadStatus() == MediaPlayerEnum.PlayListLoadStatus.FAIL_LOAD) {
                MediaPlayEngine.getInstance().getMediaPlayerService().requestPlayingMediaDataListData();
            } else if (MediaPlayEngine.getInstance().getMediaPlayerService().getPlayListLoadStatus() == MediaPlayerEnum.PlayListLoadStatus.START_LOAD) {
                onStartLoadMediaInfoPlayListCallback();
            } else if (MediaPlayEngine.getInstance().getMediaPlayerService().getPlayListLoadStatus() == MediaPlayerEnum.PlayListLoadStatus.COMPLETE_LOAD) {
                onSuccessfulLoadMediaInfoPlayListCallback();
            }
        }
    }

    public void requestMusicInfoData(long musicId) {
        Log.i(TAG, "requestMusicInfoData: musicId = " + musicId);
        if (this.mView != 0) {
            ((MusicPlayView) this.mView).showLoading();
            WanOSRetrofitUtil.getMusicDetail(musicId, new ResponseCallBack<GetMusicDetailResponse>(null) { // from class: com.wanos.media.presenter.MusicPlayPresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetMusicDetailResponse response) {
                    MediaMusicDetailBean mediaMusicDetailBean = response.data;
                    if (mediaMusicDetailBean != null && mediaMusicDetailBean.getMusicInfo() != null && MusicPlayPresenter.this.mView != 0) {
                        final MusicInfoBean musicInfo = mediaMusicDetailBean.getMusicInfo();
                        WanosDbUtils.updateMusicHistory(musicInfo, new DbCallBack<Boolean>() { // from class: com.wanos.media.presenter.MusicPlayPresenter.1.1
                            @Override // com.wanos.media.db.DbCallBack
                            public void callBackData(Boolean data) {
                                MusicPlayPresenter.this.groupId = -5L;
                                MusicPlayPresenter.this.groupType = -5L;
                                musicInfo.setPageSize(100);
                                musicInfo.setIndex(0);
                                MediaInfo mediaInfo = new MediaInfo();
                                mediaInfo.setMusicInfoBean(musicInfo);
                                mediaInfo.setGroupId(-5L);
                                mediaInfo.setMediaGroupType(-5L);
                                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                                if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                                    Log.i(MusicPlayPresenter.TAG, "callBackData: ->playMedia");
                                    MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo, true);
                                }
                                ((MusicPlayView) MusicPlayPresenter.this.mView).updateMusicView(musicInfo);
                                MusicPlayPresenter.this.getPlayMusicListData();
                                if (MusicPlayPresenter.this.mView != 0) {
                                    ((MusicPlayView) MusicPlayPresenter.this.mView).hideLoading();
                                }
                            }
                        });
                    } else if (MusicPlayPresenter.this.mView != 0) {
                        ((MusicPlayView) MusicPlayPresenter.this.mView).hideLoading();
                        ((MusicPlayView) MusicPlayPresenter.this.mView).showFailOrError("null");
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    if (MusicPlayPresenter.this.mView != 0) {
                        ((MusicPlayView) MusicPlayPresenter.this.mView).hideLoading();
                        ((MusicPlayView) MusicPlayPresenter.this.mView).showFailOrError(msg);
                    }
                }
            });
        }
    }

    public void collectOrCancleCollectMusicRequest(MusicInfoBean musicInfoBean, Context context) {
        final long musicId = musicInfoBean.getMusicId();
        final boolean z = !musicInfoBean.isCollection();
        if (musicInfoBean.getContentType() > 2) {
            WanOSRetrofitUtil.audioBookLikeChapter(musicId, z ? 1 : 0, new ResponseCallBack<AudioBookLikeChapterResponse>(context) { // from class: com.wanos.media.presenter.MusicPlayPresenter.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(AudioBookLikeChapterResponse response) {
                    if (MusicPlayPresenter.this.mView != 0) {
                        ((MusicPlayView) MusicPlayPresenter.this.mView).updateCollectStatus(musicId, z);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    Log.i(MusicPlayPresenter.TAG, "收藏或取消收藏失败----" + code + InternalFrame.ID + msg);
                }
            });
        } else if (z) {
            WanOSRetrofitUtil.musicCollect(musicId, 1, new ResponseCallBack<BaseResponse>(context) { // from class: com.wanos.media.presenter.MusicPlayPresenter.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    if (MusicPlayPresenter.this.mView != 0) {
                        ((MusicPlayView) MusicPlayPresenter.this.mView).updateCollectStatus(musicId, true);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    Log.i(MusicPlayPresenter.TAG, "收藏失败----" + code + InternalFrame.ID + msg);
                }
            });
        } else {
            WanOSRetrofitUtil.musicCollectCancel(musicId, 1, new ResponseCallBack<BaseResponse>(context) { // from class: com.wanos.media.presenter.MusicPlayPresenter.4
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    if (MusicPlayPresenter.this.mView != 0) {
                        ((MusicPlayView) MusicPlayPresenter.this.mView).updateCollectStatus(musicId, false);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    Log.i(MusicPlayPresenter.TAG, "取消收藏失败----" + code + InternalFrame.ID + msg);
                }
            });
        }
    }

    public List<MusicInfoBean> fromMediaInfoToMediaMusicBeanList(List<MediaInfo> mediaInfoList) {
        ArrayList arrayList = new ArrayList();
        if (mediaInfoList != null) {
            for (MediaInfo mediaInfo : mediaInfoList) {
                if (mediaInfo != null && mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && mediaInfo.getMusicInfoBean() != null) {
                    arrayList.add(mediaInfo.getMusicInfoBean());
                }
            }
        }
        return arrayList;
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnLoadMediaInfoPlayListCallbackListener
    public void onStartLoadMediaInfoPlayListCallback() {
        if (this.mView != 0) {
            ((MusicPlayView) this.mView).showListLoading();
        }
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnLoadMediaInfoPlayListCallbackListener
    public void onSuccessfulLoadMediaInfoPlayListCallback() {
        if (this.mView != 0) {
            ((MusicPlayView) this.mView).hideListLoading();
            ((MusicPlayView) this.mView).updateMusicListView(this.groupId, this.groupType, fromMediaInfoToMediaMusicBeanList(MediaPlayerManager.getInstance().getMediaPlayList()));
        }
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnLoadMediaInfoPlayListCallbackListener
    public void onFailLoadMediaInfoPlayListCallback() {
        if (this.mView != 0) {
            ((MusicPlayView) this.mView).showListFailOrError();
        }
    }
}
