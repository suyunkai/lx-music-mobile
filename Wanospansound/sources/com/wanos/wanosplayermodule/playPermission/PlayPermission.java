package com.wanos.wanosplayermodule.playPermission;

import android.util.Log;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager;

/* JADX INFO: loaded from: classes3.dex */
public class PlayPermission {
    public static final String TAG = "wanos:[PlayPermission]";
    private boolean isFreeMusic;
    boolean isMusicVip = false;
    MediaPlayerEnum.MediaType mCurrentMediaType;
    MediaPlayerService mMediaPlayerService;
    MusicPreviewCallback mMusicPreviewCallback;
    long previewEndTime;

    public interface AudioBookCallback {
        void action();
    }

    public interface MusicPreviewCallback {
        void PreviewTimeDone();

        void seek(long j);
    }

    public PlayPermission(MediaPlayerService mediaPlayerService) {
        this.mMediaPlayerService = mediaPlayerService;
    }

    public void musicPermissionVerify(MediaInfo mediaInfo, MusicPreviewCallback musicPreviewCallback) {
        this.mMusicPreviewCallback = musicPreviewCallback;
        boolean zIsVipAndUnexpired = UserInfoUtil.isVipAndUnexpired();
        Log.d(TAG, "loginVipAndUnexpired " + zIsVipAndUnexpired);
        if (mediaInfo != null) {
            if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
                MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
                if (musicInfoBean != null) {
                    boolean zIsVipMusic = musicInfoBean.isVipMusic();
                    boolean zIsFree = musicInfoBean.isFree();
                    this.isFreeMusic = zIsFree;
                    if (zIsVipMusic && !zIsFree && !zIsVipAndUnexpired) {
                        long previewStartTime = musicInfoBean.getPreviewStartTime();
                        if (previewStartTime > 0) {
                            musicPreviewCallback.seek(previewStartTime * 1000);
                        }
                        if (musicInfoBean.getPreviewEndTime() == 0) {
                            musicInfoBean.setPreviewEndTime(1L);
                        }
                    }
                    this.isMusicVip = musicInfoBean.isVipMusic();
                    this.previewEndTime = musicInfoBean.getPreviewEndTime();
                }
                this.mCurrentMediaType = MediaPlayerEnum.MediaType.Music;
                return;
            }
            this.mCurrentMediaType = MediaPlayerEnum.MediaType.Audiobook;
        }
    }

    public void getMediaPlayerServiceAndSetListener() {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService != null) {
            mediaPlayerService.addOnStatusCallbackListener(new OnStatusCallbackListener() { // from class: com.wanos.wanosplayermodule.playPermission.PlayPermission.1
                @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
                public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
                    if (callBackState == MediaPlayerEnum.CallBackState.POSITION) {
                        long jLongValue = Long.valueOf(String.valueOf(objArr[0])).longValue();
                        Log.i(PlayPermission.TAG, "onStatusonStatusCallbackNext: currentPosition = " + jLongValue);
                        boolean zIsVipAndUnexpired = UserInfoUtil.isVipAndUnexpired();
                        if (PlayPermission.this.mCurrentMediaType != MediaPlayerEnum.MediaType.Music || !PlayPermission.this.isMusicVip || zIsVipAndUnexpired || PlayPermission.this.isFreeMusic || PlayPermission.this.previewEndTime <= 0 || jLongValue <= PlayPermission.this.previewEndTime * 1000) {
                            return;
                        }
                        Log.d(PlayPermission.TAG, "previewEndTime " + (PlayPermission.this.previewEndTime * 1000));
                        if (PlayPermission.this.mMusicPreviewCallback != null) {
                            if (CommonUtils.isIs371()) {
                                if (!WanosFadeInOutManager.getInstance().isPausing()) {
                                    PlayPermission.this.mMusicPreviewCallback.PreviewTimeDone();
                                    return;
                                } else {
                                    Log.i(PlayPermission.TAG, "onStatusonStatusCallbackNext: 已经在淡出暂停播放中，不做任何操作");
                                    return;
                                }
                            }
                            PlayPermission.this.mMusicPreviewCallback.PreviewTimeDone();
                            return;
                        }
                        Log.d(PlayPermission.TAG, "MusicPreviewCallback is null");
                    }
                }
            });
        } else {
            Log.d(TAG, "没有获取播放器实例");
        }
    }

    public void audioBookPermissionVerify(MediaInfo mediaInfo, AudioBookCallback audioBookCallback) {
        if (UserInfoUtil.isVipAndUnexpired() || mediaInfo == null || mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
            return;
        }
        int isVip = mediaInfo.getAudioBookInfoBean().getIsVip();
        Log.d(TAG, "isAudioBookVip " + isVip);
        if (isVip == 1) {
            audioBookCallback.action();
        }
    }
}
