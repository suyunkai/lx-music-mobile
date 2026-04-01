package com.wanos.media.ui.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.GsonUtils;
import com.wanos.commonlibrary.event.SingleLiveEvent;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.ZeroApplication;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.EventExitScene;
import com.wanos.media.entity.GetAudioInfoResp;
import com.wanos.media.entity.IScreenStateChange;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.entity.ThemeInfoEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.util.CacheTools;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroCacheScan;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.viewmodel.SoundMaterialViewModel;
import com.wanos.media.widget.video.ISceneLoadedCallback;
import com.wanos.media.widget.video.IThemeLoadedCallback;
import com.wanos.media.widget.video.ZeroThemeDataHelp;
import com.wanos.media.zero_p.R;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroInfoViewModel extends ViewModel implements IScreenStateChange {
    private static final String TAG = "ZeroInfoViewModel";
    private MutableLiveData<String[]> _MingXiangDurationEvent;
    private MutableLiveData<AudioSceneInfoEntity> _ThemeInfoEvent;
    private SingleLiveEvent<String> _UserThemeLoadError;
    private MutableLiveData<String> _XiaoQiDurationEvent;
    private final ZeroAudioPlayController audioPlayController;
    private ZeroPageMode mCurrentState;
    private ZeroPageMode mInitState;
    private SpaceThemeBaseInfo mItemInfo;
    public LiveData<String[]> onMingXiangDurationEvent;
    public LiveData<AudioSceneInfoEntity> onThemeInfoEvent;
    public LiveData<String> onUserThemeLoadError;
    public LiveData<String> onXiaoQiDurationEvent;
    private AudioSceneInfoEntity mZeroThemeInfoEntity = null;
    private boolean mImmersionState = false;

    public ZeroInfoViewModel() {
        MutableLiveData<String[]> mutableLiveData = new MutableLiveData<>();
        this._MingXiangDurationEvent = mutableLiveData;
        this.onMingXiangDurationEvent = mutableLiveData;
        MutableLiveData<AudioSceneInfoEntity> mutableLiveData2 = new MutableLiveData<>();
        this._ThemeInfoEvent = mutableLiveData2;
        this.onThemeInfoEvent = mutableLiveData2;
        SingleLiveEvent<String> singleLiveEvent = new SingleLiveEvent<>();
        this._UserThemeLoadError = singleLiveEvent;
        this.onUserThemeLoadError = singleLiveEvent;
        MutableLiveData<String> mutableLiveData3 = new MutableLiveData<>();
        this._XiaoQiDurationEvent = mutableLiveData3;
        this.onXiaoQiDurationEvent = mutableLiveData3;
        ZeroLogcatTools.d(TAG, TAG);
        this.audioPlayController = new ZeroAudioPlayController();
        ZeroCacheScan.getInstance().setScanStop();
        ZeroAudioBallTools.getInstance().onActivityCreate();
        HttpTools.getInstance().getAudioLibrary(new HttpCallback<GetAudioInfoResp>() { // from class: com.wanos.media.ui.info.ZeroInfoViewModel.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseEntity<GetAudioInfoResp> baseEntity) {
                CacheTools.toObjectLocal(ZeroApplication.getApplication(), SoundMaterialViewModel.KEY_MATERIAL_CACHE, GsonUtils.toJson(baseEntity.getData()));
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                ZeroLogcatTools.e(ZeroInfoViewModel.TAG, "initMaterialCache: 素材库数据预缓存失败==>" + str);
            }
        });
        ZeroApplication.getZeroCallback().addScreenStateChangeListener(this);
    }

    public void setImmersionState(boolean z) {
        this.mImmersionState = z;
    }

    public boolean isImmersionState() {
        return this.mImmersionState;
    }

    public void initThemeInfoData() {
        HttpTools.getInstance().getUserThemeInfo(getItemInfo().getThemeId(), new HttpCallback<ThemeInfoEntity>() { // from class: com.wanos.media.ui.info.ZeroInfoViewModel.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(final BaseEntity<ThemeInfoEntity> baseEntity) {
                ThemeInfoEntity data = baseEntity.getData();
                if (data == null) {
                    onResponseFailure(baseEntity.code, "主题详情获取失败");
                    return;
                }
                List<AudioSceneInfoEntity> audioInfo = data.getAudioInfo();
                if (audioInfo != null && !audioInfo.isEmpty()) {
                    if (ZeroInfoViewModel.this.mInitState != ZeroPageMode.MING_XIANG_PRO && ZeroInfoViewModel.this.mInitState != ZeroPageMode.MING_XIANG_STANDARD) {
                        ZeroInfoViewModel.this._XiaoQiDurationEvent.setValue(data.getThemeName());
                    } else {
                        String[] strArrFindMingXiangDuration = ZeroThemeDataHelp.getInstance().findMingXiangDuration(ZeroPageMode.MING_XIANG_PRO, data);
                        if (strArrFindMingXiangDuration != null) {
                            ZeroInfoViewModel.this._MingXiangDurationEvent.setValue(strArrFindMingXiangDuration);
                        }
                    }
                    ZeroThemeDataHelp.getInstance().onLoadSceneData(ZeroPageMode.XIAO_QI_PRO, data, 0, new ISceneLoadedCallback() { // from class: com.wanos.media.ui.info.ZeroInfoViewModel.2.1
                        @Override // com.wanos.media.widget.video.ISceneLoadedCallback
                        public void onSceneInfo(ThemeInfoEntity themeInfoEntity, AudioSceneInfoEntity audioSceneInfoEntity) {
                            ZeroInfoViewModel.this._ThemeInfoEvent.setValue(((ThemeInfoEntity) baseEntity.getData()).getAudioInfo().get(0));
                        }

                        @Override // com.wanos.media.widget.video.ISceneLoadedCallback
                        public void onSceneErrorInfo(String str) {
                            ZeroInfoViewModel.this._UserThemeLoadError.setValue(str);
                        }
                    });
                    return;
                }
                onResponseFailure(baseEntity.code, "主题场景获取失败");
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                ZeroInfoViewModel.this._UserThemeLoadError.setValue(str);
            }
        });
    }

    public void setAlarmState(boolean z) {
        this.audioPlayController.setAlarmPlayState(z, this.mInitState);
    }

    public void setAlarmState(boolean z, boolean z2) {
        this.audioPlayController.setAlarmPlayState(z, this.mInitState, z2);
    }

    public void setRelaxState(boolean z) {
        this.audioPlayController.setRelaxPlayState(z);
    }

    public void setSelectThemeId(long j) {
        ZeroThemeDataHelp.getInstance().onLoadThemeInfoData(getInitState(), (int) j, new IThemeLoadedCallback() { // from class: com.wanos.media.ui.info.ZeroInfoViewModel.3
            @Override // com.wanos.media.widget.video.IThemeLoadedCallback
            public void onThemeDurationInfo(String[] strArr) {
            }

            @Override // com.wanos.media.widget.video.IThemeLoadedCallback
            public void onThemeErrorInfo(String str) {
            }

            @Override // com.wanos.media.widget.video.IThemeLoadedCallback
            public void onThemeInfo(ThemeInfoEntity themeInfoEntity) {
            }

            @Override // com.wanos.media.widget.video.IThemeLoadedCallback
            public void onVideoInfo(ThemeInfoEntity themeInfoEntity) {
            }
        });
    }

    public void initItemInfo(SpaceThemeBaseInfo spaceThemeBaseInfo) {
        if (this.mItemInfo != null) {
            return;
        }
        this.mItemInfo = spaceThemeBaseInfo;
    }

    public void initZeroPageMode(ZeroPageMode zeroPageMode) {
        if (this.mInitState != null) {
            return;
        }
        this.mInitState = zeroPageMode;
        this.mCurrentState = zeroPageMode;
    }

    public void setItemInfo(long j, String str, String str2, boolean z) {
        SpaceThemeBaseInfo spaceThemeBaseInfo = this.mItemInfo;
        if (spaceThemeBaseInfo == null) {
            ZeroLogcatTools.e(TAG, "mItemInfo == NULL");
            return;
        }
        spaceThemeBaseInfo.setThemeId(j);
        this.mItemInfo.setBgImg(str);
        this.mItemInfo.setName(str2);
        this.mItemInfo.setVip(z);
    }

    public void setZeroThemeInfoEntity(AudioSceneInfoEntity audioSceneInfoEntity) {
        this.mZeroThemeInfoEntity = audioSceneInfoEntity;
    }

    public void setCurrentState(ZeroPageMode zeroPageMode) {
        this.mCurrentState = zeroPageMode;
    }

    public AudioSceneInfoEntity getZeroThemeInfoEntity() {
        return this.mZeroThemeInfoEntity;
    }

    public SpaceThemeBaseInfo getItemInfo() {
        SpaceThemeBaseInfo spaceThemeBaseInfo = this.mItemInfo;
        if (spaceThemeBaseInfo != null) {
            return spaceThemeBaseInfo;
        }
        throw new NullPointerException("未调用 initZeroPageMode()");
    }

    public ZeroPageMode getInitState() {
        ZeroPageMode zeroPageMode = this.mInitState;
        if (zeroPageMode != null) {
            return zeroPageMode;
        }
        throw new NullPointerException("未调用 initZeroPageMode()");
    }

    public ZeroPageMode getCurrentState() {
        if (this.mInitState == null) {
            throw new NullPointerException("未调用 initZeroPageMode()");
        }
        return this.mCurrentState;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.audioPlayController.onReleaseAudioFocus();
        ZeroApplication.getZeroCallback().removeScreenStateChangeListener(this);
        ZeroAudioBallTools.getInstance().onActivityDestroy();
        ZeroThemeDataHelp.getInstance().onActivityDestroy();
        ZeroApplication.getZeroCallback().onRelaxExit();
    }

    @Override // com.wanos.media.entity.IScreenStateChange
    public void onScreenStateChange(int i) {
        ZeroLogcatTools.d(TAG, "onScreenStateChange: screenState = " + i);
        if (i != 101) {
            ToastUtil.showMsg(R.string.error_portrait_use);
            EventBus.getDefault().post(new EventExitScene());
        }
    }
}
