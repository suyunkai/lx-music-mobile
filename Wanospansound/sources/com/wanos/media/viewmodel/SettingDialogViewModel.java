package com.wanos.media.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.AlarmDownStateEntity;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.BellsEntity;
import com.wanos.media.entity.GetAlarmAudioInfo;
import com.wanos.media.util.AlarmTools;
import com.wanos.media.util.MusicCacheUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.util.ZeroSettingMateData;
import com.wanos.media.view.SettingDialog;
import com.wanos.media.zero_p.R;
import com.wanos.zero.ZeroAudioFocusTools;
import com.wanos.zero.ZeroAudioPlayerTools;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SettingDialogViewModel extends ViewModel {
    public static final int INTENT_DEFAULT_STATE = 100;
    public static final int INTENT_FOCUS_PAUSE = 101;
    public static final int INTENT_STOPPED_PLAY = 1032;
    private static final String TAG = "SettingDialogViewModel";
    private final MutableLiveData<List<BellsEntity.BellsInfoEntity>> _BellsInfo;
    private final MutableLiveData<Integer> _SelectIndex;
    private final MutableLiveData<AlarmDownStateEntity> _State;
    public final LiveData<List<BellsEntity.BellsInfoEntity>> bellsInfo;
    private SettingDialog.IDismissListener iDismissListener;
    private final Observer<ZeroAudioFocusTools.AudioFocusState> mAudioFocusObserver;
    private final Observer<ZeroAudioPlayerTools.AudioPlayerState> mAudioPlayerObserver;
    private File mNowSelectFile;
    private int mStopReason;
    public final LiveData<Integer> selectIndex;
    public final LiveData<AlarmDownStateEntity> state;

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-media-viewmodel-SettingDialogViewModel, reason: not valid java name */
    /* synthetic */ void m602lambda$new$0$comwanosmediaviewmodelSettingDialogViewModel(ZeroAudioPlayerTools.AudioPlayerState audioPlayerState) {
        if (audioPlayerState == ZeroAudioPlayerTools.AudioPlayerState.STOPPED && this.mStopReason == 1032) {
            this.mStopReason = 100;
            ZeroAudioPlayerTools.getInstance().onMediaLaunch(this.mNowSelectFile, false);
        }
    }

    /* JADX INFO: renamed from: lambda$new$1$com-wanos-media-viewmodel-SettingDialogViewModel, reason: not valid java name */
    /* synthetic */ void m603lambda$new$1$comwanosmediaviewmodelSettingDialogViewModel(ZeroAudioFocusTools.AudioFocusState audioFocusState) {
        if (audioFocusState == ZeroAudioFocusTools.AudioFocusState.LOSS) {
            this.mStopReason = 100;
            ZeroAudioPlayerTools.getInstance().onMediaStop(0);
        } else if (audioFocusState == ZeroAudioFocusTools.AudioFocusState.LOSS_TRANSIENT) {
            this.mStopReason = 101;
            ZeroAudioPlayerTools.getInstance().onMediaPause();
        } else if (audioFocusState == ZeroAudioFocusTools.AudioFocusState.GAIN && this.mStopReason == 101) {
            ZeroAudioPlayerTools.getInstance().onMediaResume();
        }
    }

    public SettingDialogViewModel() {
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        this._SelectIndex = mutableLiveData;
        this.selectIndex = mutableLiveData;
        MutableLiveData<List<BellsEntity.BellsInfoEntity>> mutableLiveData2 = new MutableLiveData<>();
        this._BellsInfo = mutableLiveData2;
        this.bellsInfo = mutableLiveData2;
        MutableLiveData<AlarmDownStateEntity> mutableLiveData3 = new MutableLiveData<>();
        this._State = mutableLiveData3;
        this.state = mutableLiveData3;
        this.mNowSelectFile = null;
        this.mStopReason = 100;
        Observer<ZeroAudioPlayerTools.AudioPlayerState> observer = new Observer() { // from class: com.wanos.media.viewmodel.SettingDialogViewModel$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m602lambda$new$0$comwanosmediaviewmodelSettingDialogViewModel((ZeroAudioPlayerTools.AudioPlayerState) obj);
            }
        };
        this.mAudioPlayerObserver = observer;
        Observer<ZeroAudioFocusTools.AudioFocusState> observer2 = new Observer() { // from class: com.wanos.media.viewmodel.SettingDialogViewModel$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m603lambda$new$1$comwanosmediaviewmodelSettingDialogViewModel((ZeroAudioFocusTools.AudioFocusState) obj);
            }
        };
        this.mAudioFocusObserver = observer2;
        ZeroAudioFocusTools.getInstance().getAudioFocusState().observeForever(observer2);
        ZeroAudioPlayerTools.getInstance().getAudioPlayerState().observeForever(observer);
        AlarmTools.onLoadAlarmData(new AlarmTools.IDataLoadCallback() { // from class: com.wanos.media.viewmodel.SettingDialogViewModel$$ExternalSyntheticLambda2
            @Override // com.wanos.media.util.AlarmTools.IDataLoadCallback
            public final void onSuccess(List list) {
                this.f$0.m604lambda$new$2$comwanosmediaviewmodelSettingDialogViewModel(list);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$2$com-wanos-media-viewmodel-SettingDialogViewModel, reason: not valid java name */
    /* synthetic */ void m604lambda$new$2$comwanosmediaviewmodelSettingDialogViewModel(List list) {
        String strFindAlarmPath = ZeroSettingMateData.findAlarmPath();
        if (StringUtils.isEmpty(strFindAlarmPath)) {
            return;
        }
        File file = new File(strFindAlarmPath);
        if (file.exists() && file.isFile()) {
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                if (strFindAlarmPath.equalsIgnoreCase(MusicCacheUtils.getMusicCachePath(((BellsEntity.BellsInfoEntity) list.get(i)).getPath()))) {
                    this._SelectIndex.setValue(Integer.valueOf(i));
                    break;
                }
                i++;
            }
            this._BellsInfo.setValue(list);
        }
    }

    public void onItemClickIntent(final int i, final BellsEntity.BellsInfoEntity bellsInfoEntity) {
        if (bellsInfoEntity.isDownloading()) {
            ToastUtil.showMsg(R.string.zero_down_loading);
            return;
        }
        File file = new File(MusicCacheUtils.getMusicCachePath(bellsInfoEntity.getPath()));
        if (!file.exists() || !file.isFile()) {
            HttpTools.getInstance().getAlarAudioInfo(bellsInfoEntity.getId(), new HttpCallback<GetAlarmAudioInfo>() { // from class: com.wanos.media.viewmodel.SettingDialogViewModel.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseEntity<GetAlarmAudioInfo> baseEntity) {
                    GetAlarmAudioInfo data = baseEntity.getData();
                    String path = bellsInfoEntity.getPath();
                    if (data != null) {
                        path = bellsInfoEntity.getPath();
                    }
                    MusicCacheUtils.onLaunchTask(path, new MusicCacheUtils.IMusicCacheListener() { // from class: com.wanos.media.viewmodel.SettingDialogViewModel.1.1
                        @Override // com.wanos.media.util.MusicCacheUtils.IMusicCacheListener
                        public void onTaskStart(int i2) {
                            SettingDialogViewModel.this._State.setValue(new AlarmDownStateEntity(i, false, true));
                        }

                        @Override // com.wanos.media.util.MusicCacheUtils.IMusicCacheListener
                        public void onTaskSuccess(int i2, String str, Object obj) {
                            SettingDialogViewModel.this._State.setValue(new AlarmDownStateEntity(i, true, false));
                        }

                        @Override // com.wanos.media.util.MusicCacheUtils.IMusicCacheListener
                        public void onTaskError(int i2, String str) {
                            SettingDialogViewModel.this._State.setValue(new AlarmDownStateEntity(i, false, false));
                        }
                    }, Integer.valueOf(i));
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i2, String str) {
                    ToastUtil.showMsg(str);
                }
            });
            return;
        }
        this._SelectIndex.setValue(Integer.valueOf(i));
        this.mNowSelectFile = file;
        if (ZeroAudioPlayerTools.getInstance().isPlaying()) {
            this.mStopReason = 1032;
            ZeroAudioPlayerTools.getInstance().onMediaStop(100);
            return;
        }
        this.mStopReason = 100;
        if (ZeroAudioFocusTools.getInstance().isCanFocus()) {
            ZeroAudioPlayerTools.getInstance().onMediaLaunch(file, false);
        } else if (ZeroAudioFocusTools.getInstance().requestAudioFocus()) {
            ZeroAudioPlayerTools.getInstance().onMediaLaunch(file, false);
        } else {
            ToastUtil.showMsg(R.string.error_audio_focus);
        }
    }

    public void setDismissListener(SettingDialog.IDismissListener iDismissListener) {
        if (this.iDismissListener == null) {
            this.iDismissListener = iDismissListener;
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        ZeroLogcatTools.d(TAG, "onCleared");
        ZeroAudioFocusTools.getInstance().getAudioFocusState().removeObserver(this.mAudioFocusObserver);
        ZeroAudioPlayerTools.getInstance().getAudioPlayerState().removeObserver(this.mAudioPlayerObserver);
        ZeroAudioPlayerTools.getInstance().onMediaStop(0);
        ZeroAudioFocusTools.getInstance().releaseAudioFocus();
        SettingDialog.IDismissListener iDismissListener = this.iDismissListener;
        if (iDismissListener != null) {
            iDismissListener.onDismiss();
        }
    }
}
