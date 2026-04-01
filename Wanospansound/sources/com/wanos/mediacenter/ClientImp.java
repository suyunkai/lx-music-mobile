package com.wanos.mediacenter;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MediaInfoBean;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SingerInfoBean;
import com.wanos.commonlibrary.mediaCenter.AudioConfig;
import com.wanos.commonlibrary.mediaCenter.MediaCenterClient;
import com.wanos.commonlibrary.mediaCenter.MediaCenterServer;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnMediaInfoCallbackListener;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.mediaPlayer.listener.OnUpdateSDKLrcListener;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.mediacenter.SemanticsManager;
import com.wanos.mediacenter.utils.MediaSharedPreferUtils;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ClientImp implements MediaCenterClient {
    private static final String TAG = "wanos:[ClientImp]";
    MediaCenterServer action;
    MediaPlayerEnum.CallBackState callBackState;
    private boolean isFront = false;
    MediaPlayerEnum.CallBackState playerStatus;
    String preCurrentRequestClient;

    public ClientImp(MediaCenterServer mediaCenterServer) {
        this.action = mediaCenterServer;
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public OnStatusCallbackListener getOnStatusCallbackListener() {
        return new OnStatusCallbackListener() { // from class: com.wanos.mediacenter.ClientImp$$ExternalSyntheticLambda0
            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
            public final void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
                this.f$0.m670x752d47e7(callBackState, objArr);
            }
        };
    }

    /* JADX INFO: renamed from: lambda$getOnStatusCallbackListener$0$com-wanos-mediacenter-ClientImp, reason: not valid java name */
    /* synthetic */ void m670x752d47e7(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED || callBackState == MediaPlayerEnum.CallBackState.ERROR || callBackState == MediaPlayerEnum.CallBackState.PREPARING || callBackState == MediaPlayerEnum.CallBackState.PREPARE) {
            this.playerStatus = callBackState;
            this.action.setPlayerStatus(callBackState);
            this.callBackState = callBackState;
        }
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            MediaCenterManager.getInstance().updatePlayInfo(updateMusicPlayState(this.action.getCurrentMusicInfo()), true);
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
            MediaCenterManager.getInstance().updateCurrentPlayStatus(false);
        }
        if (callBackState == MediaPlayerEnum.CallBackState.POSITION) {
            long jLongValue = Long.valueOf(String.valueOf(objArr[0])).longValue();
            if (this.callBackState != MediaPlayerEnum.CallBackState.PREPARING) {
                MediaCenterManager.getInstance().updateCurrentProgress(jLongValue);
            }
        }
        if (callBackState == MediaPlayerEnum.CallBackState.ERROR) {
            Object obj = objArr[0];
            if (obj == null) {
                return;
            }
            String string = obj.toString();
            if (string != null && string.length() > 0 && string.contains("what:-38")) {
                return;
            } else {
                MediaCenterManager.getInstance().updateCurrentPlayStatus(false);
            }
        }
        if (callBackState == MediaPlayerEnum.CallBackState.COMPLETE) {
            Log.i(TAG, "wanos播放完成");
            MediaCenterManager.getInstance().updateCurrentPlayStatus(false);
        }
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public OnMediaInfoCallbackListener getOnMediaInfoCallbackListener() {
        return new OnMediaInfoCallbackListener() { // from class: com.wanos.mediacenter.ClientImp$$ExternalSyntheticLambda1
            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnMediaInfoCallbackListener
            public final void onMediaInfoCallbackNext(MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, MediaInfo mediaInfo) {
                this.f$0.m669xb90e49f8(mediaInfoCallbackType, mediaInfo);
            }
        };
    }

    /* JADX INFO: renamed from: lambda$getOnMediaInfoCallbackListener$1$com-wanos-mediacenter-ClientImp, reason: not valid java name */
    /* synthetic */ void m669xb90e49f8(MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, MediaInfo mediaInfo) {
        if (MediaPlayerEnum.MediaInfoCallbackType.favorStatus == mediaInfoCallbackType) {
            Log.i(TAG, "歌曲收藏变化" + fromMediaInfoGetFavorStatus(mediaInfo));
            if (fromMediaInfoGetFavorStatus(mediaInfo) == 1) {
                MediaCenterManager.getInstance().updateCollectMsg2(true);
            } else if (fromMediaInfoGetFavorStatus(mediaInfo) == 2) {
                MediaCenterManager.getInstance().updateCollectMsg2(false);
            }
        }
        if (MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType == mediaInfoCallbackType) {
            Log.i(TAG, "mediaInfoType切歌后同步歌曲信息");
            WanosMusicPlaybackInfo.getInstance().setLrc(null);
            MediaCenterManager.getInstance().updateCurrentProgress(0L);
            MediaCenterManager.getInstance().updatePlayInfo(updateMusicPlayState(mediaInfo), false);
            this.action.setPlayerInterceptorFlag(false);
        }
        if (MediaPlayerEnum.MediaInfoCallbackType.supporFavor == mediaInfoCallbackType) {
            Log.i(TAG, "是否支持收藏 " + UserInfoUtil.isLogin());
            WanosMusicPlaybackInfo wanosMusicPlaybackInfo = WanosMusicPlaybackInfo.getInstance();
            wanosMusicPlaybackInfo.setSupporFavor(UserInfoUtil.isLogin());
            MediaCenterManager.getInstance().updateMusicPlaybackState(wanosMusicPlaybackInfo);
        }
        if (MediaPlayerEnum.MediaInfoCallbackType.playMode == mediaInfoCallbackType) {
            Log.i(TAG, "音频类型 " + mediaInfo.getMediaType());
            if (mediaInfo == null || mediaInfo.playMode == null) {
                return;
            }
            MediaCenterManager.getInstance().onSetLoopMode(LoopModeMap(mediaInfo.playMode));
        }
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public OnUpdateSDKLrcListener getOnUpdateSDKLrcListener() {
        return new OnUpdateSDKLrcListener() { // from class: com.wanos.mediacenter.ClientImp$$ExternalSyntheticLambda2
            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnUpdateSDKLrcListener
            public final void onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType updateLrcCallbackType, String str) {
                this.f$0.m671x1c1e747e(updateLrcCallbackType, str);
            }
        };
    }

    /* JADX INFO: renamed from: lambda$getOnUpdateSDKLrcListener$2$com-wanos-mediacenter-ClientImp, reason: not valid java name */
    /* synthetic */ void m671x1c1e747e(MediaPlayerEnum.UpdateLrcCallbackType updateLrcCallbackType, String str) {
        MediaInfo currentMusicInfo = this.action.getCurrentMusicInfo();
        if (currentMusicInfo == null || updateLrcCallbackType != MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS) {
            return;
        }
        if (currentMusicInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
            Log.i(TAG, "更新歌曲");
            MediaCenterManager.getInstance().updateLrc(str);
        } else {
            Log.i(TAG, "更新有声书");
            MediaCenterManager.getInstance().updateLrc("暂无歌词");
        }
    }

    public static int fromMediaInfoGetFavorStatus(MediaInfo mediaInfo) {
        AudioBookMineChapterItemBean audioBookInfoBean;
        if (mediaInfo == null) {
            return 2;
        }
        if (mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music) {
            return (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && (audioBookInfoBean = mediaInfo.getAudioBookInfoBean()) != null && audioBookInfoBean.getIsCollect() == 1) ? 1 : 2;
        }
        MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
        return (musicInfoBean == null || !musicInfoBean.isCollection()) ? 2 : 1;
    }

    public static String getMusicSingerName(List<SingerInfoBean> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = -1;
        for (int i2 = 0; i2 < list.size(); i2++) {
            SingerInfoBean singerInfoBean = list.get(i2);
            if (singerInfoBean != null) {
                String name = singerInfoBean.getName();
                if (!TextUtils.isEmpty(name)) {
                    i++;
                    if (i == 0) {
                        sb.append(name);
                    } else {
                        sb.append("、").append(name);
                    }
                }
            }
        }
        return sb.toString();
    }

    public MediaInfoBean updateMusicPlayState(MediaInfo mediaInfo) {
        if (mediaInfo == null) {
            return null;
        }
        if (mediaInfo.getMusicInfoBean() == null && mediaInfo.getAudioBookInfoBean() == null) {
            return null;
        }
        MediaInfoBean mediaInfoBean = new MediaInfoBean();
        MediaPlayerEnum.MediaType mediaType = mediaInfo.getMediaType();
        if (mediaType == MediaPlayerEnum.MediaType.Music) {
            mediaInfoBean.setMediaType(MediaPlayerEnum.MediaType.Music);
            mediaInfoBean.setTitle(mediaInfo.getMusicInfoBean().getName());
            List<SingerInfoBean> singerList = mediaInfo.getMusicInfoBean().getSingerList();
            if (singerList != null && !singerList.isEmpty()) {
                mediaInfoBean.setArtist(getMusicSingerName(singerList));
            }
            mediaInfoBean.setDuration((long) mediaInfo.getMusicInfoBean().getDuration());
            String avatar = mediaInfo.getMusicInfoBean().getAvatar();
            if (!avatar.contains("imageMogr2/format/webp/thumbnail/")) {
                avatar = avatar + "?imageMogr2/format/webp/thumbnail/";
            }
            mediaInfoBean.setArtwork(Uri.parse(avatar));
            mediaInfoBean.setCollection(mediaInfo.getMusicInfoBean().isCollection());
            mediaInfoBean.setRadioMode(MediaPlayerEnum.PlayMode.LoopModeMap(getPlayMode(MediaPlayerEnum.MediaType.Music)));
            if (avatar != null) {
                mediaInfoBean.setUuid(mediaInfo.getMusicInfoBean().getMusicId() + String.valueOf(avatar.hashCode()));
            } else {
                mediaInfoBean.setUuid(String.valueOf(mediaInfo.getMusicInfoBean().getMusicId()));
            }
        } else if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
            mediaInfoBean.setMediaType(MediaPlayerEnum.MediaType.Audiobook);
            mediaInfoBean.setTitle(mediaInfo.getAudioBookInfoBean().getName());
            AudioBookMineChapterItemBean audioBookInfoBean = mediaInfo.getAudioBookInfoBean();
            if (audioBookInfoBean != null) {
                mediaInfoBean.setArtist(audioBookInfoBean.getSpeaker().getName());
            }
            mediaInfoBean.setDuration(mediaInfo.getAudioBookInfoBean().getDuration());
            mediaInfoBean.setArtwork(Uri.parse(mediaInfo.getAudioBookInfoBean().getAvatar()));
            mediaInfoBean.setCollection(mediaInfo.getAudioBookInfoBean().getIsCollect() == 1);
            mediaInfoBean.setRadioMode(MediaPlayerEnum.PlayMode.LoopModeMap(getPlayMode(MediaPlayerEnum.MediaType.Audiobook)));
            String avatar2 = mediaInfo.getAudioBookInfoBean().getAvatar();
            if (avatar2 != null) {
                mediaInfoBean.setUuid(mediaInfo.getAudioBookInfoBean().getRadioId() + String.valueOf(avatar2.hashCode()));
            } else {
                mediaInfoBean.setUuid(String.valueOf(mediaInfo.getAudioBookInfoBean().getRadioId()));
            }
        }
        return mediaInfoBean;
    }

    public boolean isPlaying() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer == null) {
            Log.e(TAG, "isPlaying()监听回调为空");
            return false;
        }
        return mediaCenterServer.isPlaying();
    }

    private MediaPlayerEnum.PlayMode getPlayMode(MediaPlayerEnum.MediaType mediaType) {
        if (mediaType == MediaPlayerEnum.MediaType.Music) {
            return MediaSharedPreferUtils.getMusicMode();
        }
        if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
            return MediaSharedPreferUtils.getAudioBookMode();
        }
        return MediaPlayerEnum.PlayMode.orderplay;
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public void updateMusicPlayState(boolean z) {
        if (this.action != null) {
            Log.i(TAG, "updateMusicPlayState");
            MediaCenterManager.getInstance().updatePlayInfo(updateMusicPlayState(this.action.getCurrentMusicInfo()), z);
        }
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public void onFront() {
        this.isFront = true;
    }

    /* JADX INFO: renamed from: com.wanos.mediacenter.ClientImp$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode;

        static {
            int[] iArr = new int[MediaPlayerEnum.PlayMode.values().length];
            $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode = iArr;
            try {
                iArr[MediaPlayerEnum.PlayMode.randomplay.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode[MediaPlayerEnum.PlayMode.listloopplay.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode[MediaPlayerEnum.PlayMode.singleloopplay.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static int LoopModeMap(MediaPlayerEnum.PlayMode playMode) {
        int i = AnonymousClass2.$SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$PlayMode[playMode.ordinal()];
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? -1 : 1;
        }
        return 0;
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public void onBackground() {
        this.isFront = false;
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public void showNeedLogin() {
        showHint(R.string.need_login_message);
    }

    private void showHint(int i) {
        if (!AudioConfig.isNeedTTSHint) {
            ToastUtil.showSystemToast(i);
        } else {
            if (MediaCenterManager.getInstance().semanticsManager == null || MediaCenterManager.getInstance().mContext == null) {
                return;
            }
            MediaCenterManager.getInstance().semanticsManager.sendAudioFocusVrTts(new SemanticsManager.PlayCallback() { // from class: com.wanos.mediacenter.ClientImp.1
                @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                public void onCompleted() {
                }
            }, MediaCenterManager.getInstance().mContext.getText(i).toString());
        }
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public void showNeedVip() {
        showHint(R.string.need_toast_vip);
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public boolean currentFocusIsEmpty() {
        return MediaCenterManager.getInstance().currentFocusIsEmpty();
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public boolean hasCurrentFocus() {
        return MediaCenterManager.getInstance().hasCurrentFocus();
    }

    @Override // com.wanos.commonlibrary.mediaCenter.MediaCenterClient
    public void updateCurrentProgress(long j) {
        MediaCenterManager.getInstance().updateCurrentProgress(j);
    }
}
