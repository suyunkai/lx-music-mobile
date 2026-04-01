package com.wanos.media.base;

import android.util.Log;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.entity.IScreenStateChange;
import com.wanos.media.entity.IZeroCallback;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroCallbackReal implements IZeroCallback {
    public static final String TAG = "wanos:[ZeroCallbackReal]";
    private final List<IScreenStateChange> mScreenStateChangeList = new ArrayList();
    private boolean isPlayOtherMedia = false;

    @Override // com.wanos.media.entity.IZeroCallback
    public int getScreenState() {
        return 101;
    }

    @Override // com.wanos.media.entity.IZeroCallback
    public void onRelaxEnter() {
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        if (mediaPlayerService != null) {
            if (mediaPlayerService.isPlaying()) {
                Log.i(TAG, "onRelaxEnter: 正在播放其他内容");
                mediaPlayerService.pause();
                this.isPlayOtherMedia = true;
                return;
            }
            MediaPlayerEnum.CallBackState preStatus = mediaPlayerService.getPreStatus();
            if (preStatus != null) {
                Log.i(TAG, "onRelaxEnter: preStatus = " + preStatus.name());
                if (preStatus == MediaPlayerEnum.CallBackState.PREPARING || preStatus == MediaPlayerEnum.CallBackState.BUFFER_UPDATE) {
                    mediaPlayerService.setPlayerInterceptorFlag(true);
                    Log.i(TAG, "onRelaxEnter: 正在准备，标记为 暂停");
                    return;
                } else {
                    Log.i(TAG, "onRelaxEnter: stop other media ,其他情况 preStatus = " + preStatus.name());
                    return;
                }
            }
            return;
        }
        Log.i(TAG, "setStopOtherMedia: mediaPlayerService = null");
    }

    @Override // com.wanos.media.entity.IZeroCallback
    public void onRelaxExit() {
        Log.i(TAG, "onRelaxExit: isPlayOtherMedia = " + this.isPlayOtherMedia);
        if (this.isPlayOtherMedia) {
            this.isPlayOtherMedia = false;
            ThreadUtils.getMainHandler().postDelayed(new Runnable() { // from class: com.wanos.media.base.ZeroCallbackReal$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m473lambda$onRelaxExit$0$comwanosmediabaseZeroCallbackReal();
                }
            }, 400L);
        }
    }

    /* JADX INFO: renamed from: lambda$onRelaxExit$0$com-wanos-media-base-ZeroCallbackReal, reason: not valid java name */
    /* synthetic */ void m473lambda$onRelaxExit$0$comwanosmediabaseZeroCallbackReal() {
        MediaPlayerService mediaPlayerService;
        Log.i(TAG, "onRelaxExit: isPlayOtherMedia(延时后) = " + this.isPlayOtherMedia);
        if (this.isPlayOtherMedia || (mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService()) == null) {
            return;
        }
        mediaPlayerService.start();
    }

    @Override // com.wanos.media.entity.IZeroCallback
    public void addScreenStateChangeListener(IScreenStateChange iScreenStateChange) {
        if (iScreenStateChange == null || this.mScreenStateChangeList.contains(iScreenStateChange)) {
            return;
        }
        this.mScreenStateChangeList.add(iScreenStateChange);
    }

    @Override // com.wanos.media.entity.IZeroCallback
    public void removeScreenStateChangeListener(IScreenStateChange iScreenStateChange) {
        if (iScreenStateChange == null || !this.mScreenStateChangeList.contains(iScreenStateChange)) {
            return;
        }
        this.mScreenStateChangeList.remove(iScreenStateChange);
    }
}
