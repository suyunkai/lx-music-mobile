package com.wanos.media.ui.audiobook;

import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookGlobalParams {
    private static AudioBookGlobalParams instance = null;
    private static int screenWidth = 2560;
    private boolean likeChapterIsUpdate = false;
    private boolean collectAlbumIsUpdate = false;
    private MediaPlayerEnum.CallBackState callBackState = MediaPlayerEnum.CallBackState.PAUSE;

    public static AudioBookGlobalParams getInstance() {
        if (instance == null) {
            instance = new AudioBookGlobalParams();
        }
        return instance;
    }

    public boolean isLikeChapterIsUpdate() {
        return this.likeChapterIsUpdate;
    }

    public void setLikeChapterIsUpdate(boolean likeChapterIsUpdate) {
        this.likeChapterIsUpdate = likeChapterIsUpdate;
    }

    public boolean isCollectAlbumIsUpdate() {
        return this.collectAlbumIsUpdate;
    }

    public void setCollectAlbumIsUpdate(boolean collectAlbumIsUpdate) {
        this.collectAlbumIsUpdate = collectAlbumIsUpdate;
    }

    public static AudioBookMineChapterItemBean getPlayingItemBean() {
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        if (mediaPlayerService != null && mediaPlayerService.getCurMediaInfo() != null && mediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            return mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean();
        }
        if (mediaPlayerService == null || mediaPlayerService.getCurMediaInfo() == null || mediaPlayerService.getCurMediaInfo().getMusicInfoBean() == null || mediaPlayerService.getCurMediaInfo().getMusicInfoBean().getContentType() <= 2) {
            return null;
        }
        return mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean();
    }

    public static MediaPlayerEnum.CallBackState getPlayingStatus() {
        MediaPlayerEnum.CallBackState callBackState = MediaPlayerEnum.CallBackState.PAUSE;
        if (MediaPlayEngine.getInstance().getMediaPlayerService() == null) {
            return callBackState;
        }
        if (MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()) {
            if (MediaPlayEngine.getInstance().getMediaPlayerService().getPauseStatus() == MediaPlayerEnum.PauseStatus.PAUSING) {
                return MediaPlayerEnum.CallBackState.PAUSING;
            }
            return MediaPlayerEnum.CallBackState.STARTED;
        }
        if (getInstance().getCallBackState() == MediaPlayerEnum.CallBackState.PREPARING) {
            return MediaPlayerEnum.CallBackState.PREPARING;
        }
        return MediaPlayerEnum.CallBackState.PAUSE;
    }

    public static MediaPlayerEnum.CallBackState getPlayingStatusV2() {
        MediaPlayerEnum.CallBackState callBackState = MediaPlayerEnum.CallBackState.PAUSE;
        if (MediaPlayEngine.getInstance().getMediaPlayerService() == null) {
            return callBackState;
        }
        if (MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()) {
            if (MediaPlayEngine.getInstance().getMediaPlayerService().getPauseStatus() == MediaPlayerEnum.PauseStatus.PAUSING) {
                return MediaPlayerEnum.CallBackState.PAUSING;
            }
            return MediaPlayerEnum.CallBackState.STARTED;
        }
        if (getInstance().getCallBackState() == MediaPlayerEnum.CallBackState.PREPARING) {
            return MediaPlayerEnum.CallBackState.PREPARING;
        }
        return MediaPlayerEnum.CallBackState.PAUSE;
    }

    public MediaPlayerEnum.CallBackState getCallBackState() {
        return this.callBackState;
    }

    public void setCallBackState(MediaPlayerEnum.CallBackState callBackState) {
        this.callBackState = callBackState;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth2) {
        screenWidth = screenWidth2;
    }
}
