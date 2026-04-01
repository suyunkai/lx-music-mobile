package com.wanos.media.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.blankj.utilcode.util.LogUtils;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import kotlin.jvm.functions.Function1;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerSourceViewModel extends AndroidViewModel implements OnStatusCallbackListener, MediaPlayerService.OnMediaInfoCallbackAppListener {
    public static final String TAG = "wanos:[PlayerSourceViewModel]";
    private MutableLiveData<MediaInfo> collectMediaInfoLive;
    private MutableLiveData<MediaInfo> curMediaInfoLive;
    private MutableLiveData<Boolean> isLoadingLive;
    public LiveData<Boolean> isMusicLive;
    private MutableLiveData<MediaPlayerEnum.CallBackState> playStatusLive;
    private MutableLiveData<Boolean> playWhenReadyLive;
    private MutableLiveData<Long> positionLive;
    private MutableLiveData<MediaPlayerEnum.UpdateLrcCallbackType> updateLrcStatusLive;

    public PlayerSourceViewModel(Application application) {
        super(application);
        this.playWhenReadyLive = new MutableLiveData<>(false);
        this.isLoadingLive = new MutableLiveData<>(false);
        this.curMediaInfoLive = new MutableLiveData<>();
        this.collectMediaInfoLive = new MutableLiveData<>();
        this.positionLive = new MutableLiveData<>(0L);
        this.updateLrcStatusLive = new MutableLiveData<>();
        this.playStatusLive = new MutableLiveData<>();
        this.isMusicLive = Transformations.map(this.curMediaInfoLive, new Function1() { // from class: com.wanos.media.viewmodel.PlayerSourceViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.m600lambda$new$0$comwanosmediaviewmodelPlayerSourceViewModel((MediaInfo) obj);
            }
        });
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-media-viewmodel-PlayerSourceViewModel, reason: not valid java name */
    /* synthetic */ Boolean m600lambda$new$0$comwanosmediaviewmodelPlayerSourceViewModel(MediaInfo mediaInfo) {
        return Boolean.valueOf(isMusic());
    }

    public void onMediaServiceConnected() {
        MediaInfo curMediaInfo = MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo();
        this.curMediaInfoLive.setValue(curMediaInfo);
        LogUtils.d(TAG, "registerMediaCallback: " + curMediaInfo);
        MediaPlayEngine.getInstance().getMediaPlayerService().addOnStatusCallbackListener(this);
        MediaPlayEngine.getInstance().getMediaPlayerService().addOnMediaInfoCallbackAppListener(this);
    }

    public LiveData<MediaInfo> getLiveMediaInfo() {
        return this.curMediaInfoLive;
    }

    public LiveData<MediaInfo> getCollectChangeMediaInfo() {
        return this.collectMediaInfoLive;
    }

    public LiveData<MediaPlayerEnum.CallBackState> getPlayStatus() {
        return this.playStatusLive;
    }

    public LiveData<Boolean> getPlayWhenReady() {
        return this.playWhenReadyLive;
    }

    public LiveData<Boolean> getIsLoading() {
        return this.isLoadingLive;
    }

    public LiveData<Long> getPosition() {
        return this.positionLive;
    }

    public LiveData<MediaPlayerEnum.UpdateLrcCallbackType> getUpdateLrcStatus() {
        return this.updateLrcStatusLive;
    }

    public boolean isMusic() {
        MediaInfo value = this.curMediaInfoLive.getValue();
        return value != null && value.getMediaType() == MediaPlayerEnum.MediaType.Music;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        MediaPlayEngine.getInstance().getMediaPlayerService().removeOnStatusCallbackListener(this);
        MediaPlayEngine.getInstance().getMediaPlayerService().removeOnMediaInfoCallbackAppListener(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent loginOrLogoutEvent) {
        MutableLiveData<MediaInfo> mutableLiveData = this.curMediaInfoLive;
        mutableLiveData.setValue(mutableLiveData.getValue());
    }

    @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
        if (callBackState == MediaPlayerEnum.CallBackState.PREPARING || callBackState == MediaPlayerEnum.CallBackState.PREPARE || callBackState == MediaPlayerEnum.CallBackState.PAUSE || callBackState == MediaPlayerEnum.CallBackState.ERROR || callBackState == MediaPlayerEnum.CallBackState.EXCEPTION || callBackState == MediaPlayerEnum.CallBackState.COMPLETE || callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            LogUtils.d(TAG, "onStatusonStatusCallbackNext: " + callBackState);
            this.playStatusLive.setValue(callBackState);
        }
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, MediaInfo mediaInfo) {
        LogUtils.d(TAG, "onMediaInfoCallbackAppNext: " + mediaInfo);
        if (mediaInfoCallbackType == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
            this.curMediaInfoLive.setValue(mediaInfo);
        }
    }
}
