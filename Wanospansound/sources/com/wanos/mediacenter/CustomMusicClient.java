package com.wanos.mediacenter;

import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.ecarx.eas.sdk.mediacenter.ContentInfo;
import com.ecarx.eas.sdk.mediacenter.MediaInfo;
import com.ecarx.eas.sdk.mediacenter.MediaListsInfo;
import com.ecarx.eas.sdk.mediacenter.MusicClient;
import com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo;
import com.ecarx.eas.sdk.mediacenter.RecommendInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaCenter.MediaCenterServer;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CustomMusicClient extends MusicClient {
    private static final String TAG = "wanos:[MusicClient]";
    private MediaCenterServer action;
    MusicPlaybackInfo musicPlaybackInfo;
    private long lastInvokeTime = 0;
    private long interval = 100;

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean ctrlPauseMediaList(int i) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean ctrlPlayMediaList(int i) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public long getCurrentProgress() {
        return 0L;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public int getCurrentSourceType() {
        return 6;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public int[] getMediaSourceTypeList() {
        return new int[]{6};
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onCancelRecommend(RecommendInfo recommendInfo) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    @Deprecated
    public boolean onCollect(int i, boolean z) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onDownload(int i, boolean z) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onExit() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    @Deprecated
    public boolean onForward() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onMediaForward(boolean z) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onMediaQualityChange(int i) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onMediaRewind(boolean z) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onMediaSelected(int i, String str) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onMediaSelected(MediaInfo mediaInfo) {
        return true;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onPlayRecommend(RecommendInfo recommendInfo) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onReplay() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    @Deprecated
    public boolean onRewind() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public void operationType(int i) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean selectListMediaPlay(int i, int i2, String str) {
        return false;
    }

    public void setMusicPlaybackInfo(MusicPlaybackInfo musicPlaybackInfo) {
        this.musicPlaybackInfo = musicPlaybackInfo;
    }

    public CustomMusicClient(MediaCenterServer mediaCenterServer) {
        this.action = mediaCenterServer;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onPlay() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer == null) {
            return false;
        }
        boolean zPlay = mediaCenterServer.play();
        if (zPlay) {
            return zPlay;
        }
        MediaCenterManager.getInstance().updateCurrentSourceType();
        MediaCenterManager.getInstance().sendNoContentError();
        return zPlay;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onPause() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer == null) {
            return true;
        }
        mediaCenterServer.pause();
        return true;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onNext() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer == null) {
            return true;
        }
        mediaCenterServer.next();
        return true;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onPrevious() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer == null) {
            return true;
        }
        mediaCenterServer.pre();
        return true;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onLoopModeChange(int i) {
        int iLoopModeCenterToPlayMap = LoopModeCenterToPlayMap(i);
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer == null) {
            return true;
        }
        mediaCenterServer.setPlayMode(iLoopModeCenterToPlayMap);
        return true;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onSourceSelected(int i) {
        Log.i(TAG, "onSourceSelected" + i);
        Log.i(TAG, "onSourceSelected: " + onPlay());
        return true;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public boolean onSourceChanged(int i, String str) {
        LogUtils.iTag("", "onSourceSelected: " + onPlay() + ",preApp:" + str);
        return true;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public MusicPlaybackInfo getMusicPlaybackInfo() {
        return this.musicPlaybackInfo;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public List<MediaInfo> getPlaylist(int i) {
        return new ArrayList();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public void onMediaCenterFocusChanged(String str) {
        Log.i(TAG, "currentRequestClient is " + str);
        if (this.action == null) {
            return;
        }
        rateLimitedInvoke(str);
    }

    public void rateLimitedInvoke(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Log.i(TAG, "LimitedInvoke时间戳 " + (jCurrentTimeMillis - this.lastInvokeTime));
        if (jCurrentTimeMillis - this.lastInvokeTime >= this.interval) {
            this.lastInvokeTime = jCurrentTimeMillis;
            doInvoke(str);
        }
    }

    private void doInvoke(String str) {
        this.action.setPlayerInterceptorFlag(str);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public List<ContentInfo> getContentList() {
        return new ArrayList();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public MediaListsInfo getMultiMediaList(int[] iArr) {
        return new MediaListsInfo();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public void onSeek(long j) {
        if (this.action != null) {
            checkSeekRange(j);
        }
    }

    public void checkSeekRange(long j) {
        com.wanos.commonlibrary.bean.MediaInfo currentMusicInfo;
        MusicInfoBean musicInfoBean;
        if (!UserInfoUtil.isVipAndUnexpired() && (currentMusicInfo = this.action.getCurrentMusicInfo()) != null && currentMusicInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && (musicInfoBean = currentMusicInfo.getMusicInfoBean()) != null && !musicInfoBean.isFree()) {
            boolean zIsVipMusic = musicInfoBean.isVipMusic();
            long previewStartTime = musicInfoBean.getPreviewStartTime();
            if (zIsVipMusic && j < previewStartTime * 1000) {
                return;
            }
        }
        this.action.seek(j);
    }

    public static int LoopModeCenterToPlayMap(int i) {
        if (i == 0) {
            return MediaPlayerEnum.PlayMode.listloopplay.ordinal();
        }
        if (i == 1) {
            return MediaPlayerEnum.PlayMode.singleloopplay.ordinal();
        }
        if (i != 2) {
            return -1;
        }
        return MediaPlayerEnum.PlayMode.randomplay.ordinal();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicClient, com.ecarx.eas.sdk.mediacenter.AbstractMusicClient
    public int ctrlCollect(int i, boolean z) {
        com.wanos.commonlibrary.bean.MediaInfo currentMusicInfo;
        System.out.println("wanos---------------------------------收藏");
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer == null || (currentMusicInfo = mediaCenterServer.getCurrentMusicInfo()) == null) {
            return 0;
        }
        int iFromMediaInfoGetFavorStatus = ClientImp.fromMediaInfoGetFavorStatus(currentMusicInfo);
        if (iFromMediaInfoGetFavorStatus == 1) {
            Log.i(TAG, "取消收藏");
            this.action.cancelFavor();
            return 0;
        }
        if (iFromMediaInfoGetFavorStatus != 2) {
            return 0;
        }
        Log.i(TAG, "添加收藏");
        this.action.addFavor();
        return 0;
    }
}
