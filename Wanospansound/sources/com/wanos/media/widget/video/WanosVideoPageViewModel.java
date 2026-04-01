package com.wanos.media.widget.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.ThemeInfoEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.entity.ZeroThemeInfo;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.util.ZeroPressureDataManager;
import com.wanos.media.widget.video.WanosVideoRepository;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class WanosVideoPageViewModel extends ViewModel {
    private static final String TAG = "WanosVideoPageViewModel";
    private final MutableLiveData<String[]> _durationRange;
    private final MutableLiveData<WanosVideoRepository.LoadingState> _sceneLoadState;
    private final MutableLiveData<List<ZeroThemeInfo>> _themeList;
    private final MutableLiveData<Boolean> _vipStyleState;
    public final LiveData<String[]> durationRange;
    public final LiveData<WanosVideoRepository.LoadingState> sceneLoadState;
    public final LiveData<List<ZeroThemeInfo>> themeList;
    public final LiveData<Boolean> vipStyleState;
    private final WanosVideoRepository mRepository = new WanosVideoRepository();
    private final ZeroAudioBallTools mAudioTools = new ZeroAudioBallTools();
    private ZeroPageMode initPageMode = null;
    private int mCurrentViewPagePosition = -1;
    private ThemeInfoEntity mCurrentThemeInfo = null;
    private AudioSceneInfoEntity mCurrentSceneInfo = null;

    public WanosVideoPageViewModel() {
        MutableLiveData<List<ZeroThemeInfo>> mutableLiveData = new MutableLiveData<>();
        this._themeList = mutableLiveData;
        this.themeList = mutableLiveData;
        MutableLiveData<Boolean> mutableLiveData2 = new MutableLiveData<>();
        this._vipStyleState = mutableLiveData2;
        this.vipStyleState = mutableLiveData2;
        MutableLiveData<String[]> mutableLiveData3 = new MutableLiveData<>();
        this._durationRange = mutableLiveData3;
        this.durationRange = mutableLiveData3;
        MutableLiveData<WanosVideoRepository.LoadingState> mutableLiveData4 = new MutableLiveData<>(WanosVideoRepository.LoadingState.LOADING);
        this._sceneLoadState = mutableLiveData4;
        this.sceneLoadState = mutableLiveData4;
    }

    public void initPageMode(ZeroPageMode zeroPageMode, int i) {
        ZeroLogcatTools.i(TAG, "setInitPageMode: initPageMode = " + zeroPageMode);
        if (this.initPageMode == null) {
            this.initPageMode = zeroPageMode;
            if (zeroPageMode == ZeroPageMode.MING_XIANG_STANDARD || this.initPageMode == ZeroPageMode.MING_XIANG_PRO) {
                ArrayList<ZeroThemeInfo> mxData = ZeroPressureDataManager.getInstance().getMxData();
                this.mCurrentViewPagePosition = findThemeIndexForThemeId(mxData, i);
                this._themeList.setValue(mxData);
            } else {
                if (this.initPageMode == ZeroPageMode.XIAO_QI_STANDARD || this.initPageMode == ZeroPageMode.XIAO_QI_PRO) {
                    ArrayList<ZeroThemeInfo> xqData = ZeroPressureDataManager.getInstance().getXqData();
                    this.mCurrentViewPagePosition = findThemeIndexForThemeId(xqData, i);
                    this._themeList.setValue(xqData);
                    return;
                }
                ZeroLogcatTools.w(TAG, "setInitPageMode: 未知状态，initPageMode = " + zeroPageMode);
            }
        }
    }

    public void requestThemeInfo(int i) {
        ZeroThemeInfo zeroThemeInfoFindThemeListForThemeId = findThemeListForThemeId(i);
        if (zeroThemeInfoFindThemeListForThemeId == null) {
            ZeroLogcatTools.e(TAG, "requestThemeInfo: 未在列表中查询到+" + i + "+主题");
            return;
        }
        this.mCurrentViewPagePosition = findThemeIndexForThemeId(this.themeList.getValue(), i);
        this._sceneLoadState.setValue(WanosVideoRepository.LoadingState.LOADING);
        this._vipStyleState.setValue(Boolean.valueOf(zeroThemeInfoFindThemeListForThemeId.isVip() && !(UserInfoUtil.isLogin() && UserInfoUtil.isVipAndUnexpired())));
        this.mRepository.onLoadThemeInfoData(this.initPageMode, i, new WanosVideoRepository.IThemeInfoLoaded() { // from class: com.wanos.media.widget.video.WanosVideoPageViewModel.1
            @Override // com.wanos.media.widget.video.WanosVideoRepository.IThemeInfoLoaded
            public void onThemeInfoLoaded(ThemeInfoEntity themeInfoEntity) {
                ZeroLogcatTools.i(WanosVideoPageViewModel.TAG, "requestThemeInfo: 主题详情数据获取成功,主题名称 = " + themeInfoEntity.getThemeName());
                WanosVideoPageViewModel.this.mCurrentThemeInfo = themeInfoEntity;
                if (WanosVideoPageViewModel.this.initPageMode == ZeroPageMode.MING_XIANG_PRO || WanosVideoPageViewModel.this.initPageMode == ZeroPageMode.MING_XIANG_STANDARD) {
                    String[] strArrFindMingXiangDuration = WanosVideoPageViewModel.this.mRepository.findMingXiangDuration(WanosVideoPageViewModel.this.initPageMode, WanosVideoPageViewModel.this.mCurrentThemeInfo);
                    ZeroLogcatTools.i(WanosVideoPageViewModel.TAG, "requestThemeInfo: 主题时长可选范围 = " + strArrFindMingXiangDuration);
                    if (strArrFindMingXiangDuration != null) {
                        WanosVideoPageViewModel.this._durationRange.setValue(strArrFindMingXiangDuration);
                    }
                }
                int i2 = 0;
                if ((WanosVideoPageViewModel.this.initPageMode == ZeroPageMode.MING_XIANG_PRO || WanosVideoPageViewModel.this.initPageMode == ZeroPageMode.MING_XIANG_STANDARD) && WanosVideoPageViewModel.this.mCurrentThemeInfo.getAudioInfo() != null && !WanosVideoPageViewModel.this.mCurrentThemeInfo.getAudioInfo().isEmpty()) {
                    List<AudioSceneInfoEntity> audioInfo = WanosVideoPageViewModel.this.mCurrentThemeInfo.getAudioInfo();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= audioInfo.size()) {
                            break;
                        }
                        if (audioInfo.get(i3).getDurtion() == 10) {
                            i2 = i3;
                            break;
                        }
                        i3++;
                    }
                }
                WanosVideoPageViewModel.this.requestSceneInfo(i2);
            }

            @Override // com.wanos.media.widget.video.WanosVideoRepository.IThemeInfoLoaded
            public void onThemeInfoLoadFailed(String str) {
                WanosVideoPageViewModel.this._sceneLoadState.setValue(WanosVideoRepository.LoadingState.LOADED_ERROR);
                ToastUtil.showMsg(str);
            }
        });
    }

    public void requestSceneInfo(int i) {
        if (this.mCurrentThemeInfo == null) {
            ZeroLogcatTools.e(TAG, "requestSceneInfo: 主题信息错误，未获取到主题");
        } else {
            this._sceneLoadState.setValue(WanosVideoRepository.LoadingState.LOADING);
            this.mRepository.onLoadSceneInfoData(this.mCurrentThemeInfo, i, new AnonymousClass2());
        }
    }

    /* JADX INFO: renamed from: com.wanos.media.widget.video.WanosVideoPageViewModel$2, reason: invalid class name */
    class AnonymousClass2 implements WanosVideoRepository.ISceneInfoLoaded {
        AnonymousClass2() {
        }

        @Override // com.wanos.media.widget.video.WanosVideoRepository.ISceneInfoLoaded
        public void onSceneInfoLoaded(AudioSceneInfoEntity audioSceneInfoEntity) {
            ZeroLogcatTools.i(WanosVideoPageViewModel.TAG, "requestSceneInfo: 场景详情数据获取成功,场景ID = " + audioSceneInfoEntity.getAudioId());
            WanosVideoPageViewModel.this._sceneLoadState.setValue(WanosVideoRepository.LoadingState.LOADED_SUCCESS);
            WanosVideoPageViewModel.this.mCurrentSceneInfo = audioSceneInfoEntity;
            WanosVideoPageViewModel.this.mAudioTools.initScene(WanosVideoPageViewModel.this.mCurrentSceneInfo.getDetailInfo(), false, new ZeroAudioBallTools.ILoadingCallback() { // from class: com.wanos.media.widget.video.WanosVideoPageViewModel$2$$ExternalSyntheticLambda0
                @Override // com.wanos.media.util.ZeroAudioBallTools.ILoadingCallback
                public final void onAudioReady() {
                    this.f$0.m659xb3406824();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onSceneInfoLoaded$0$com-wanos-media-widget-video-WanosVideoPageViewModel$2, reason: not valid java name */
        /* synthetic */ void m659xb3406824() {
            WanosVideoPageViewModel.this.mAudioTools.onMediaStart(0);
        }

        @Override // com.wanos.media.widget.video.WanosVideoRepository.ISceneInfoLoaded
        public void onSceneInfoLoadFailed(String str) {
            WanosVideoPageViewModel.this._sceneLoadState.setValue(WanosVideoRepository.LoadingState.LOADED_ERROR);
            ToastUtil.showMsg(str);
        }
    }

    private int findThemeIndexForThemeId(List<ZeroThemeInfo> list, int i) {
        if (list == null) {
            return -1;
        }
        int i2 = 0;
        if (list.size() == 1) {
            return 0;
        }
        while (i2 < list.size()) {
            if (list.get(i2).getThemeId() == i) {
                if (i2 == 0) {
                    return 1;
                }
                return i2 == list.size() - 1 ? list.size() : i2 + 1;
            }
            i2++;
        }
        return -1;
    }

    private ZeroThemeInfo findThemeListForThemeId(int i) {
        List<ZeroThemeInfo> value = this.themeList.getValue();
        if (value == null) {
            return null;
        }
        for (ZeroThemeInfo zeroThemeInfo : value) {
            if (zeroThemeInfo.getThemeId() == i) {
                return zeroThemeInfo;
            }
        }
        return null;
    }
}
