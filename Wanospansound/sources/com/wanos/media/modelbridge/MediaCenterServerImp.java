package com.wanos.media.modelbridge;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.MediaMusicListBean;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaCenter.MediaCenterServer;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.MainApplication;
import com.wanos.media.R;
import com.wanos.media.ui.advertise.WanosJsBridge;
import com.wanos.wanosplayermodule.MediaPlayerManager;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MediaCenterServerImp implements MediaCenterServer {
    public static final String COM_WANOS_MEDIA = "com.wanos.media";
    private static final String TAG = "wanos:[MediaCenterService]";
    private boolean isLoadingRankMusicMedia;
    private MediaPlayerService mMediaPlayerService = null;
    Handler mainHandler = new Handler(Looper.getMainLooper());
    MediaPlayerEnum.CallBackState playerStatus;
    String preCurrentRequestClient;

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public boolean play() {
        if (this.mMediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
            return false;
        }
        Log.e(TAG, WanosJsBridge.METHOD_KEY_PLAY);
        MediaInfo curMediaInfo = this.mMediaPlayerService.getCurMediaInfo();
        if (curMediaInfo == null) {
            return false;
        }
        Log.i(TAG, "play: curMediaInfo != null");
        if (curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            Log.i(TAG, "play: curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook");
            final int iIsShowToast = isShowToast(MediaPlayerManager.getInstance().getCurrentMediaIndex(), true, curMediaInfo);
            Log.i(TAG, "play: showToast = " + iIsShowToast);
            if (iIsShowToast > 0) {
                MainApplication.getInstance().centerClient.updateMusicPlayState(false);
                this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.i(MediaCenterServerImp.TAG, "run: >>");
                        if (iIsShowToast == 2) {
                            MediaCenterServerImp.this.mMediaPlayerService.onVipStatusCallback();
                        } else {
                            MediaCenterServerImp.this.mMediaPlayerService.onLoginStatusCallback();
                        }
                    }
                });
                return true;
            }
            if (iIsShowToast == -1) {
                this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ToastUtil.showMsg(R.string.please_check_net);
                    }
                });
                return true;
            }
            Log.i(TAG, "next: showToast = " + iIsShowToast);
        }
        this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.3
            @Override // java.lang.Runnable
            public void run() {
                Log.i(MediaCenterServerImp.TAG, "run: mMediaPlayerService.start");
                MediaCenterServerImp.this.mMediaPlayerService.start();
            }
        });
        return true;
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void pause() {
        if (this.mMediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
        } else {
            Log.e(TAG, "==pause==");
            this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.4
                @Override // java.lang.Runnable
                public void run() {
                    if (MediaCenterServerImp.this.mMediaPlayerService.isPlaying()) {
                        MediaCenterServerImp.this.mMediaPlayerService.pause();
                        Log.i(MediaCenterServerImp.TAG, "run:正在播放，直接 暂停");
                    } else if (MediaCenterServerImp.this.mMediaPlayerService.getPreStatus() == MediaPlayerEnum.CallBackState.PREPARING) {
                        Log.i(MediaCenterServerImp.TAG, "run: 通过MediaCenter暂停,preStatus = " + MediaCenterServerImp.this.mMediaPlayerService.getPreStatus().name());
                        MediaCenterServerImp.this.mMediaPlayerService.setPlayerInterceptorFlag(true);
                    } else {
                        Log.i(MediaCenterServerImp.TAG, "run: 通过MediaCenter暂停，其他情况不处理,preStatus = " + MediaCenterServerImp.this.mMediaPlayerService.getPreStatus().name());
                    }
                }
            });
        }
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void pre() {
        if (this.mMediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
            return;
        }
        Log.e(TAG, "pre");
        MediaInfo curMediaInfo = this.mMediaPlayerService.getCurMediaInfo();
        if (curMediaInfo != null && curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            int currentMediaIndex = MediaPlayerManager.getInstance().getCurrentMediaIndex() - 1;
            if (currentMediaIndex == -1) {
                currentMediaIndex = MediaPlayerManager.getInstance().getMediaPlayList().size() - 1;
            }
            final int iIsShowToast = isShowToast(currentMediaIndex, false, curMediaInfo);
            if (iIsShowToast > 0) {
                this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (iIsShowToast == 2) {
                            MediaCenterServerImp.this.mMediaPlayerService.onVipStatusCallback();
                        } else {
                            MediaCenterServerImp.this.mMediaPlayerService.onLoginStatusCallback();
                        }
                    }
                });
                return;
            } else {
                if (iIsShowToast == -1) {
                    this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.6
                        @Override // java.lang.Runnable
                        public void run() {
                            ToastUtil.showMsg(R.string.please_check_net);
                        }
                    });
                    return;
                }
                Log.i(TAG, "next: showToast = " + iIsShowToast);
            }
        }
        this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.7
            @Override // java.lang.Runnable
            public void run() {
                MediaCenterServerImp.this.mMediaPlayerService.playPre();
            }
        });
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void next() {
        if (this.mMediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
            return;
        }
        Log.e(TAG, "next");
        MediaInfo curMediaInfo = this.mMediaPlayerService.getCurMediaInfo();
        if (curMediaInfo != null && curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            int currentMediaIndex = MediaPlayerManager.getInstance().getCurrentMediaIndex() + 1;
            if (currentMediaIndex >= MediaPlayerManager.getInstance().getMediaPlayList().size()) {
                currentMediaIndex = 0;
            }
            final int iIsShowToast = isShowToast(currentMediaIndex, false, curMediaInfo);
            if (iIsShowToast > 0) {
                this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.8
                    @Override // java.lang.Runnable
                    public void run() {
                        if (iIsShowToast == 2) {
                            MediaCenterServerImp.this.mMediaPlayerService.onVipStatusCallback();
                        } else {
                            MediaCenterServerImp.this.mMediaPlayerService.onLoginStatusCallback();
                        }
                    }
                });
                return;
            } else {
                if (iIsShowToast == -1) {
                    this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.9
                        @Override // java.lang.Runnable
                        public void run() {
                            ToastUtil.showMsg(R.string.please_check_net);
                        }
                    });
                    return;
                }
                Log.i(TAG, "next: showToast = " + iIsShowToast);
            }
        }
        this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.10
            @Override // java.lang.Runnable
            public void run() {
                MediaCenterServerImp.this.mMediaPlayerService.playNext();
            }
        });
    }

    int isShowToast(int index, boolean isPlay, MediaInfo currentMediaInfo) {
        Log.i(TAG, "isShowToast: isPlay = " + isPlay);
        if (!isPlay) {
            List<MediaInfo> mediaPlayList = MediaPlayerManager.getInstance().getMediaPlayList();
            if (mediaPlayList == null || mediaPlayList.isEmpty() || index < 0) {
                Log.i(TAG, "isShowToast: 播放列表为空，重新请求一次");
                MediaPlayerManager.getInstance().requestMediaDataListData(-1, null);
                return -1;
            }
            currentMediaInfo = MediaPlayerManager.getInstance().getMediaPlayList().get(index);
        }
        if (currentMediaInfo == null || currentMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
            return 0;
        }
        int isVip = currentMediaInfo.getAudioBookInfoBean().getIsVip();
        Log.i(TAG, "是否是vip的有声书 " + isVip);
        if (isVip != 1) {
            return 0;
        }
        boolean zIsLogin = UserInfoUtil.isLogin();
        boolean zIsVipAndUnexpired = UserInfoUtil.isVipAndUnexpired();
        if (zIsLogin) {
            return !zIsVipAndUnexpired ? 2 : 0;
        }
        return 1;
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void addFavor() {
        if (this.mMediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
        } else {
            Log.i(TAG, "添加收藏");
            this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.11
                @Override // java.lang.Runnable
                public void run() {
                    MediaCenterServerImp.this.mMediaPlayerService.collect(true);
                }
            });
        }
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void cancelFavor() {
        if (this.mMediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
        } else {
            Log.i(TAG, "取消收藏");
            this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.12
                @Override // java.lang.Runnable
                public void run() {
                    MediaCenterServerImp.this.mMediaPlayerService.collect(false);
                }
            });
        }
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void setPlayMode(int mode) {
        if (this.mMediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
            return;
        }
        final MediaPlayerEnum.PlayMode playModeIntToEnum = MediaPlayerEnum.PlayMode.intToEnum(mode);
        Log.i(TAG, "设置模式" + playModeIntToEnum.ordinal());
        MediaInfo currentMusicInfo = getCurrentMusicInfo();
        if (currentMusicInfo != null) {
            final MediaPlayerEnum.MediaType mediaType = currentMusicInfo.getMediaType();
            this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.13
                @Override // java.lang.Runnable
                public void run() {
                    if (mediaType == MediaPlayerEnum.MediaType.Music) {
                        MediaCenterServerImp.this.mMediaPlayerService.setPlayMode(MediaPlayerEnum.MediaType.Music, playModeIntToEnum);
                    } else if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
                        MediaCenterServerImp.this.mMediaPlayerService.setPlayMode(MediaPlayerEnum.MediaType.Audiobook, playModeIntToEnum);
                    }
                }
            });
        }
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public boolean isPreparing() {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
            return false;
        }
        return mediaPlayerService.isPreparing();
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public boolean isPlaying() {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
            return false;
        }
        return mediaPlayerService.isPlaying();
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public MediaInfo getCurrentMusicInfo() {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
            return null;
        }
        return mediaPlayerService.getCurMediaInfo();
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void updateLrc(String lrcUrl, MediaPlayerEnum.AppType appType) {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
        } else {
            mediaPlayerService.updateLrc(lrcUrl, appType);
        }
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void seek(long msec) {
        if (this.mMediaPlayerService == null) {
            Log.e(TAG, "获取的服务是空");
            return;
        }
        Log.e(TAG, "seek:" + msec);
        MusicInfoBean musicInfoBean = this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean();
        boolean z = this.mMediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Music;
        if (UserInfoUtil.isVipAndUnexpired() || !z) {
            this.mMediaPlayerService.seekTo(msec);
            if (this.mMediaPlayerService.isPlaying()) {
                return;
            }
            this.mMediaPlayerService.start();
            return;
        }
        if (musicInfoBean != null) {
            boolean zIsVipMusic = musicInfoBean.isVipMusic();
            long previewStartTime = musicInfoBean.getPreviewStartTime() * 1000;
            long previewEndTime = musicInfoBean.getPreviewEndTime() * 1000;
            if (zIsVipMusic && (msec >= previewEndTime || msec < previewStartTime)) {
                if (musicInfoBean.isFree()) {
                    this.mMediaPlayerService.seekTo(msec);
                    if (this.mMediaPlayerService.isPlaying()) {
                        return;
                    }
                    this.mMediaPlayerService.start();
                    return;
                }
                if (MainApplication.getInstance().centerClient != null) {
                    this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.14
                        @Override // java.lang.Runnable
                        public void run() {
                            if (UserInfoUtil.isLogin()) {
                                MainApplication.getInstance().centerClient.showNeedVip();
                            } else {
                                MainApplication.getInstance().centerClient.showNeedLogin();
                            }
                        }
                    });
                    return;
                }
                return;
            }
            this.mMediaPlayerService.seekTo(msec);
            if (this.mMediaPlayerService.isPlaying()) {
                return;
            }
            this.mMediaPlayerService.start();
        }
    }

    public void setMediaPlayerService(MediaPlayerService mediaPlayerService) {
        this.mMediaPlayerService = mediaPlayerService;
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void requestRankMusicListAndPlayFirst() {
        if (this.mMediaPlayerService == null || this.isLoadingRankMusicMedia) {
            return;
        }
        this.isLoadingRankMusicMedia = true;
        WanOSRetrofitUtil.getRankMusicList(1, 1, new ResponseCallBack<GetMusicListResponse>(null) { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.15
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicListResponse response) {
                MediaMusicListBean mediaMusicListBean;
                MediaCenterServerImp.this.isLoadingRankMusicMedia = false;
                if (MediaCenterServerImp.this.mMediaPlayerService.getCurMediaInfo() != null || (mediaMusicListBean = response.data) == null || mediaMusicListBean.getMusicList() == null || mediaMusicListBean.getMusicList().size() <= 0) {
                    return;
                }
                MusicInfoBean musicInfoBean = mediaMusicListBean.getMusicList().get(0);
                final MediaInfo mediaInfo = new MediaInfo();
                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                mediaInfo.setGroupId(-3L);
                mediaInfo.setMediaGroupType(-3L);
                mediaInfo.setMusicInfoBean(musicInfoBean);
                MediaCenterServerImp.this.mainHandler.post(new Runnable() { // from class: com.wanos.media.modelbridge.MediaCenterServerImp.15.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.i(MediaCenterServerImp.TAG, "run: requestRankMusicListAndPlayFirst");
                        MediaCenterServerImp.this.mMediaPlayerService.playMedia(mediaInfo);
                    }
                });
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                MediaCenterServerImp.this.isLoadingRankMusicMedia = false;
            }
        });
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void setPlayerStatus(MediaPlayerEnum.CallBackState playerStatus) {
        this.playerStatus = playerStatus;
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void setPlayerInterceptorFlag(String currentRequestClient) {
        Log.i(TAG, "currentRequestClient" + currentRequestClient);
        Log.i(TAG, "当前播放器状态 " + this.playerStatus);
        if (this.playerStatus == MediaPlayerEnum.CallBackState.ERROR || this.playerStatus == MediaPlayerEnum.CallBackState.PREPARING) {
            if (this.mMediaPlayerService == null) {
                return;
            }
            Log.i(TAG, "currentRequestClient" + currentRequestClient);
            Log.i(TAG, "preCurrentRequestClient" + this.preCurrentRequestClient);
            if (!TextUtils.equals(currentRequestClient, "com.wanos.media") && TextUtils.equals(this.preCurrentRequestClient, "com.wanos.media")) {
                Log.i(TAG, "触发了" + Thread.currentThread().getName());
                this.mMediaPlayerService.setPlayerInterceptorFlag(true);
            } else if (!TextUtils.equals(currentRequestClient, "com.wanos.media") && this.preCurrentRequestClient == null) {
                Log.i(TAG, "第一次触发了" + Thread.currentThread().getName());
                this.mMediaPlayerService.setPlayerInterceptorFlag(true);
            } else {
                Log.i(TAG, "其他情况");
            }
        } else {
            Log.i(TAG, "currentRequestClient" + currentRequestClient);
            Log.i(TAG, "preCurrentRequestClient" + this.preCurrentRequestClient);
            Log.i(TAG, "其他情况");
        }
        if (TextUtils.equals(this.preCurrentRequestClient, currentRequestClient)) {
            return;
        }
        Log.i(TAG, "更新前一个" + currentRequestClient);
        this.preCurrentRequestClient = currentRequestClient;
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void setPlayerInterceptorFlag(boolean isInterceptorFlag) {
        this.mMediaPlayerService.setPlayerInterceptorFlag(isInterceptorFlag);
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void isUpdateMusicPlaybackState(boolean isSucceed) {
        if (TextUtils.equals(this.preCurrentRequestClient, "com.wanos.media")) {
            return;
        }
        Log.i(TAG, "更新前一个11com.wanos.media");
        this.preCurrentRequestClient = "com.wanos.media";
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterServer
    public void getMediaPlayerServiceAndSetListener() {
        this.mMediaPlayerService.getMediaPlayerServiceAndSetListener();
    }
}
