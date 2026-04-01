package com.wanos.media;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.media3.exoplayer.dash.DashMediaSource;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.crashreport.CrashReport;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetServiceStatResponse;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.manager.AppViewModelProviders;
import com.wanos.commonlibrary.mediaCenter.AudioConfig;
import com.wanos.commonlibrary.mediaCenter.CarInfo;
import com.wanos.commonlibrary.mediaCenter.MediaCenterClient;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.base.BaseSetUp;
import com.wanos.media.base.CarConstants;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.base.ZeroCallbackReal;
import com.wanos.media.data.DbMediaExtendOperaData;
import com.wanos.media.db.DbCallBack;
import com.wanos.media.db.WanosDbUtils;
import com.wanos.media.modelbridge.MediaCenterServerImp;
import com.wanos.media.net.DomainNameInterceptor;
import com.wanos.media.net.RequestHeaderInterceptor;
import com.wanos.media.ui.StrReceiver;
import com.wanos.media.ui.actvity.MainActivity;
import com.wanos.media.ui.actvity.SplashActivity;
import com.wanos.media.ui.advertise.WanosJsBridge;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.info.ZeroInfoActivity;
import com.wanos.media.ui.login.activity.MemberPayActivity;
import com.wanos.media.util.AppManager;
import com.wanos.media.util.MyToastUtils;
import com.wanos.media.util.NetUtil;
import com.wanos.media.util.RequestParameterUtils;
import com.wanos.media.viewmodel.PlayerSourceViewModel;
import com.wanos.mediacenter.MediaCenterManager;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayServiceConnectListener;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanos.wanosplayermodule.audioTrack.AudioTracker;
import com.wanos.wanosplayermodule.receiver.PhoneStateReceiver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import me.jessyan.autosize.AutoSizeConfig;
import okhttp3.Interceptor;

/* JADX INFO: loaded from: classes3.dex */
public class MainApplication extends Application implements MediaPlayServiceConnectListener, OnStatusCallbackListener, MediaPlayerService.OnMediaInfoCallbackAppListener, MediaPlayerService.OnPlayTimeOutListener, MediaPlayerService.OnLoginStatusCallbackListener, MediaPlayerService.OnAudioFocusChangeCallbackListener, ViewModelStoreOwner {
    public static final String TAG = "wanos:[MainApplication]";
    private static MainApplication mainApplication;
    public static Activity topActivity;
    private ViewModelStore appViewModelStore;
    public CarInfo carInfo;
    public MediaCenterClient centerClient;
    private int currentNightMode;
    boolean isFirstUse;
    private AudioTracker mAudioTracker;
    private MediaPlayerService mMediaPlayerService;
    private Runnable mRunnable;
    String mSampleDirPath;
    public MediaCenterServerImp mediaCenterServer;
    private boolean isMainActvityCreated = false;
    private int mActivityCount = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    int countActivity = 0;
    boolean isBackground = true;
    boolean isOnce = true;
    boolean isFirstStart = true;
    long mCurrentTime = 0;
    long mJYTCurrentTime = 0;
    boolean isUiNightModeChangedWhenForeground = false;
    long mCurrentUseTime = 0;
    long lastTime = 0;

    @Override // com.wanos.wanosplayermodule.MediaPlayServiceConnectListener
    public void onServiceDisconnected() {
    }

    static /* synthetic */ int access$308(MainApplication mainApplication2) {
        int i = mainApplication2.mActivityCount;
        mainApplication2.mActivityCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$310(MainApplication mainApplication2) {
        int i = mainApplication2.mActivityCount;
        mainApplication2.mActivityCount = i - 1;
        return i;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "MainApplication----onCreate()----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(new Date(System.currentTimeMillis())));
        AutoSizeConfig.getInstance().setDesignWidthInDp(CarConstants.densityWidth[CarConstants.buildIndex]);
        AutoSizeConfig.getInstance().setDesignHeightInDp(CarConstants.densityHeight[CarConstants.buildIndex]);
        this.appViewModelStore = new ViewModelStore();
        mainApplication = this;
        CarConstants.init();
        Utils.init(this);
        WanOSRetrofitUtil.init(false, (List<Interceptor>) new ArrayList(Arrays.asList(new DomainNameInterceptor(), new RequestHeaderInterceptor())));
        if (CarConstants.isShowZero()) {
            ZeroApplication.initZeroModel(this, new ZeroCallbackReal());
        }
        if (Util.isMainProcess(this)) {
            NetUtil.init(this);
            CrashReport.initCrashReport(this, "6d60e9c1f3", false);
            CrashReport.setAppVersion(this, BuildConfig.VERSION_NAME);
            CrashReport.setAppChannel(this, CommonUtils.getAppChannelId());
            MediaPlayEngine.getInstance().init(mainApplication);
            MediaPlayEngine.getInstance().setExtendOperaData(new DbMediaExtendOperaData());
            this.mediaCenterServer = new MediaCenterServerImp();
            CarInfo carInfo = MediaCenterManager.getInstance().getCarInfo();
            this.carInfo = carInfo;
            carInfo.init(this, new CarInfo.OnVinLis() { // from class: com.wanos.media.MainApplication.1
                @Override // com.wanos.commonlibrary.mediaCenter.CarInfo.OnVinLis
                public void onGetVin(String vin) {
                    Log.i(MainApplication.TAG, "onGetVin: vin is " + vin);
                    CommonUtils.setDeviceId(MainApplication.mainApplication, vin);
                    CrashReport.setDeviceModel(MainApplication.mainApplication, vin);
                    RequestParameterUtils.updateDeviceId(vin);
                    MainApplication.this.initServerSystemTime();
                }
            });
            MediaPlayEngine.getInstance().addMediaPlayServiceConnectListener(this, true);
            ARouter.init(this);
            registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.wanos.media.MainApplication.2
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    if (activity instanceof MainActivity) {
                        MainApplication.this.isMainActvityCreated = true;
                    }
                    AppManager.addActivity(activity);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    MainApplication.access$308(MainApplication.this);
                    if (MainApplication.this.centerClient == null || MainApplication.this.mActivityCount != 1) {
                        return;
                    }
                    MainApplication.this.centerClient.onFront();
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    Log.i(MainApplication.TAG, "onActivityResumed: topActivity is " + activity.getClass().getSimpleName());
                    MainApplication.topActivity = activity;
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    MainApplication.access$310(MainApplication.this);
                    if (MainApplication.this.centerClient == null || MainApplication.this.mActivityCount != 0) {
                        return;
                    }
                    MainApplication.this.centerClient.onBackground();
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    if (activity instanceof MainActivity) {
                        MainApplication.this.isMainActvityCreated = false;
                    }
                    AppManager.getAppManager().removeActivity(activity);
                }
            });
            try {
                int userId = getUserId(getPackageManager().getPackageUid(getPackageName(), 128));
                Log.i(TAG, "userId is " + userId);
                if (userId != 0) {
                    this.centerClient = MediaCenterManager.getInstance().initMediaCenter(this, SplashActivity.class, this.mediaCenterServer);
                }
                if (CarConstants.needActResumeStartPlay[CarConstants.buildIndex]) {
                    initBackgroundCallBack();
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("ecarx.intent.action.carsignal.AVNOFF_ON");
                    intentFilter.addAction("ecarx.intent.action.DISPLAY_OFF");
                    intentFilter.addAction("ecarx.intent.action.AUDIO_FOCUS_RELEASE");
                    intentFilter.setPriority(1000);
                    registerReceiver(new StrReceiver(), intentFilter);
                }
                Log.d(TAG, "appVersionName is 1.0.43");
                registerBluetoothPhone();
                ComResCenter.getInstance().mainServer = new BaseSetUp();
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void initBackgroundCallBack() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.wanos.media.MainApplication.3
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                Log.i(MainApplication.TAG, "onActivityStarted: countActivity = " + MainApplication.this.countActivity);
                MainApplication.this.countActivity++;
                if (MainApplication.this.countActivity == 1 && MainApplication.this.isBackground) {
                    Log.i(MainApplication.TAG, "onActivityStarted: 应用进入前台");
                    MainApplication.this.isBackground = false;
                    if (MainApplication.this.isOnce) {
                        MainApplication.this.isOnce = false;
                        MainApplication.this.mHandler.postDelayed(new Runnable() { // from class: com.wanos.media.MainApplication.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Log.i(MainApplication.TAG, "CurrentFocus is " + MediaCenterManager.getInstance().queryCurrentFocusClient());
                                if (MainApplication.this.mMediaPlayerService == null || MainApplication.this.mMediaPlayerService.getCurMediaInfo() == null || !MainApplication.this.isAppForeground(MainApplication.mainApplication)) {
                                    return;
                                }
                                MainApplication.this.mMediaPlayerService.start();
                            }
                        }, 1500L);
                    } else {
                        if (CarConstants.isShowZero() && (activity instanceof ZeroInfoActivity)) {
                            Log.i(MainApplication.TAG, "onActivityStarted: ZeroInfoActivity return");
                            return;
                        }
                        final String strQueryCurrentFocusClient = MediaCenterManager.getInstance().queryCurrentFocusClient();
                        Log.i(MainApplication.TAG, "CurrentFocus is " + strQueryCurrentFocusClient);
                        if (MainApplication.this.mRunnable == null) {
                            MainApplication.this.mRunnable = new Runnable() { // from class: com.wanos.media.MainApplication.3.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    boolean zIsAppForeground = MainApplication.this.isAppForeground(MainApplication.mainApplication);
                                    Log.d(MainApplication.TAG, "当前是否是前台 " + zIsAppForeground + ",isUiNightModeChangedWhenForeground = " + MainApplication.this.isUiNightModeChangedWhenForeground + ",s =" + strQueryCurrentFocusClient);
                                    if (!TextUtils.equals(strQueryCurrentFocusClient, "com.wanos.media") && zIsAppForeground && !MainApplication.this.isUiNightModeChangedWhenForeground) {
                                        Log.i(MainApplication.TAG, "run: 当前不是媒体中心，并且应用在前台，开始播放");
                                        MainApplication.this.mMediaPlayerService.start();
                                    } else {
                                        Log.i(MainApplication.TAG, "run: 不满足条件，不自动播放");
                                    }
                                    if (MainApplication.this.isUiNightModeChangedWhenForeground) {
                                        Log.i(MainApplication.TAG, "run: isModeChanged = true");
                                        MainApplication.this.isUiNightModeChangedWhenForeground = false;
                                    } else {
                                        Log.i(MainApplication.TAG, "run: isModeChanged = false");
                                    }
                                }
                            };
                        }
                        MainApplication.this.mHandler.removeCallbacks(MainApplication.this.mRunnable);
                        MainApplication.this.mHandler.postDelayed(MainApplication.this.mRunnable, 500L);
                    }
                    Log.d(MainApplication.TAG, "appVersionName is 1.0.43");
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                MainApplication.this.countActivity--;
                Log.i(MainApplication.TAG, "onActivityStopped: countActivity = " + MainApplication.this.countActivity);
                if (MainApplication.this.countActivity <= 0 && !MainApplication.this.isBackground) {
                    Log.i(MainApplication.TAG, "onActivityStarted: 应用进入后台");
                    MainApplication.this.isBackground = true;
                }
                Log.d(MainApplication.TAG, "appVersionName is 1.0.43");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                Log.i(MainApplication.TAG, "onActivitySaveInstanceState: activity is " + activity.getClass().getSimpleName());
            }
        });
    }

    public boolean isAppForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(WanosJsBridge.H5_KEY_ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100) {
                Log.i(TAG, "isAppForeground: processInfo.processName = " + runningAppProcessInfo.processName);
                if (runningAppProcessInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getUserId(int uid) {
        return uid / 100000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initServerSystemTime() {
        WanOSRetrofitUtil.getServerStat(new ResponseCallBack<GetServiceStatResponse>(null) { // from class: com.wanos.media.MainApplication.4
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetServiceStatResponse response) {
            }
        });
    }

    public static MainApplication getInstance() {
        return mainApplication;
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayServiceConnectListener
    public void onServiceConnected(MediaPlayerService mediaPlayerService) {
        MediaPlayerService mediaPlayerService2 = MediaPlayEngine.getInstance().getMediaPlayerService();
        this.mMediaPlayerService = mediaPlayerService2;
        if (mediaPlayerService2 != null) {
            mediaPlayerService2.addOnStatusCallbackListener(this);
            this.mMediaPlayerService.addOnMediaInfoCallbackAppListener(this);
            this.mMediaPlayerService.addOnPlayTimeOutListener(this);
            this.mMediaPlayerService.addOnLoginStatusCallbackListener(this);
            this.mMediaPlayerService.addOnAudioFocusChangeCallbackListener(this);
            this.mediaCenterServer.setMediaPlayerService(this.mMediaPlayerService);
            MediaCenterClient mediaCenterClient = this.centerClient;
            if (mediaCenterClient != null) {
                this.mMediaPlayerService.addOnStatusCallbackListener(mediaCenterClient.getOnStatusCallbackListener());
                this.mMediaPlayerService.addOnMediaInfoCallbackListener(this.centerClient.getOnMediaInfoCallbackListener());
                this.mMediaPlayerService.addOnUpdateSDKLrcCallbackListener(this.centerClient.getOnUpdateSDKLrcListener());
            }
            this.mediaCenterServer.getMediaPlayerServiceAndSetListener();
            ((PlayerSourceViewModel) AppViewModelProviders.getApplicationViewModel(this, PlayerSourceViewModel.class)).onMediaServiceConnected();
        }
    }

    public boolean isMainActvityCreated() {
        return this.isMainActvityCreated;
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        if (mediaInfo != null && mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && type == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
            AudioBookMineChapterItemBean audioBookInfoBean = mediaInfo.getAudioBookInfoBean();
            Activity activityCurrentActivity = AppManager.getAppManager().currentActivity();
            if (audioBookInfoBean == null || audioBookInfoBean.getIsVip() != 1 || UserInfoUtil.isVipAndUnexpired() || (activityCurrentActivity instanceof MemberPayActivity) || !(activityCurrentActivity instanceof WanosBaseActivity)) {
                return;
            }
            ((WanosBaseActivity) activityCurrentActivity).openWeChatPayActivity();
        }
    }

    @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        MediaInfo curMediaInfo;
        AudioBookMineChapterItemBean audioBookInfoBean;
        AudioBookGlobalParams.getInstance().setCallBackState(status);
        if (status == MediaPlayerEnum.CallBackState.PREPARING) {
            MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
            if (mediaPlayerService != null && mediaPlayerService.getCurMediaInfo() != null && mediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Audiobook && (audioBookInfoBean = mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean()) != null && audioBookInfoBean.getIsVip() == 1 && !UserInfoUtil.isVipAndUnexpired()) {
                mediaPlayerService.proxyShutdown();
                mediaPlayerService.onStopPlayBecauseFileNotExist();
            }
            updateAudioBookHistory();
            return;
        }
        if (status == MediaPlayerEnum.CallBackState.PREPARE) {
            updateAudioBookHistory();
            return;
        }
        if (status == MediaPlayerEnum.CallBackState.STARTED) {
            if (this.isFirstStart) {
                this.mCurrentTime = System.currentTimeMillis();
            }
            this.mJYTCurrentTime = System.currentTimeMillis();
            this.isFirstStart = false;
            MediaPlayerService mediaPlayerService2 = this.mMediaPlayerService;
            if (mediaPlayerService2 != null && mediaPlayerService2.getCurMediaInfo() != null && this.mMediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Music) {
                MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_MUSIC_PLAY, "" + this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean().getMusicId(), "", "", "", 0);
                return;
            }
            MediaPlayerService mediaPlayerService3 = this.mMediaPlayerService;
            if (mediaPlayerService3 == null || mediaPlayerService3.getCurMediaInfo() == null || this.mMediaPlayerService.getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
                return;
            }
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_AUDIO_BOOK_ALBUM_CHAPTER_PLAY, "" + this.mMediaPlayerService.getCurMediaInfo().getAudioBookInfoBean().getId(), "", "", "", 0);
            return;
        }
        if (status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION) {
            return;
        }
        if (status == MediaPlayerEnum.CallBackState.COMPLETE) {
            MediaPlayerService mediaPlayerService4 = this.mMediaPlayerService;
            if (mediaPlayerService4 == null || mediaPlayerService4.getCurMediaInfo() == null || this.mMediaPlayerService.getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Music) {
                return;
            }
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_PLAY_MUSIC_COMPLETED, "" + this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean().getMusicId(), "", "", "", 0);
            return;
        }
        if (status != MediaPlayerEnum.CallBackState.SEEK_COMPLETE && status == MediaPlayerEnum.CallBackState.PROGRESS) {
            ((Integer) args[0]).intValue();
            updateAudioBookHistory();
            if (System.currentTimeMillis() - this.mCurrentTime > DashMediaSource.DEFAULT_FALLBACK_TARGET_LIVE_OFFSET_MS) {
                MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo();
                MediaPlayerService mediaPlayerService5 = this.mMediaPlayerService;
                if (mediaPlayerService5 != null && mediaPlayerService5.getCurMediaInfo() != null && this.mMediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Music && this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean() != null && this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean().getContentType() <= 2) {
                    MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_PLAY_MUSIC_PLAYING_30S_RATE_REPORT, "" + this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean().getMusicId(), "", "", "", 0);
                    if (this.mMediaPlayerService.getCurMediaInfo().getGroupId() > 0) {
                        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_PLAY_MUSIC_GROUP_PLAYING_30S_RATE_REPORT, "" + this.mMediaPlayerService.getCurMediaInfo().getGroupId(), "", "", "", 0);
                    }
                    this.mCurrentTime = System.currentTimeMillis();
                } else {
                    MediaPlayerService mediaPlayerService6 = this.mMediaPlayerService;
                    if (mediaPlayerService6 != null && mediaPlayerService6.getCurMediaInfo() != null && (this.mMediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Audiobook || (this.mMediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Music && this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean() != null && this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean().getContentType() > 2))) {
                        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_PLAY_AUDIO_BOOK_ALBUM_CHAPTER_PLAYING_30S_RATE_REPORT, "" + this.mMediaPlayerService.getCurMediaInfo().getAudioBookInfoBean().getId(), "", "", "", 0);
                        this.mCurrentTime = System.currentTimeMillis();
                    }
                }
            }
            if (System.currentTimeMillis() - this.mJYTCurrentTime < DashMediaSource.DEFAULT_FALLBACK_TARGET_LIVE_OFFSET_MS || (curMediaInfo = MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo()) == null || curMediaInfo.getMediaGroupType() != -12) {
                return;
            }
            this.mJYTCurrentTime = System.currentTimeMillis();
            Log.i("zt", "用户点击每日推荐播放时长埋点");
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_DAY_RECOMMEND_PLAYING_30S_RATE_REPORT, "", "", "", "", 0);
        }
    }

    private void updateAudioBookHistory() {
        AudioBookMineChapterItemBean audioBookInfoBean;
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        this.mMediaPlayerService = mediaPlayerService;
        if (mediaPlayerService == null || mediaPlayerService.getCurMediaInfo() == null || this.mMediaPlayerService.getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Audiobook || (audioBookInfoBean = this.mMediaPlayerService.getCurMediaInfo().getAudioBookInfoBean()) == null) {
            return;
        }
        WanosDbUtils.updateAudioBookHistory(audioBookInfoBean, new DbCallBack<Boolean>() { // from class: com.wanos.media.MainApplication.5
            @Override // com.wanos.media.db.DbCallBack
            public void callBackData(Boolean data) {
            }
        });
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnPlayTimeOutListener
    public void onPlayTimeOut() {
        ToastUtil.showMsg(getString(R.string.please_check_net));
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnLoginStatusCallbackListener
    public void onLoginStatusCallback() {
        if (this.mActivityCount == 1) {
            ToastUtil.showMsg(R.string.login_message);
            return;
        }
        if (AudioConfig.isWindowAddViewToast) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.lastTime < 2100) {
                Log.i(TAG, "onLoginStatusCallback: 点的太快了");
                return;
            } else {
                MyToastUtils.make().Text("登录体验更多功能").show();
                this.lastTime = jCurrentTimeMillis;
                return;
            }
        }
        MediaCenterClient mediaCenterClient = this.centerClient;
        if (mediaCenterClient != null) {
            mediaCenterClient.showNeedLogin();
        }
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnLoginStatusCallbackListener
    public void onVipStatusCallback() {
        if (this.mActivityCount == 1) {
            ToastUtil.showMsg(R.string.toast_vip);
            return;
        }
        if (AudioConfig.isWindowAddViewToast) {
            MyToastUtils.make().Text("完整播放，需开通会员").show();
            return;
        }
        MediaCenterClient mediaCenterClient = this.centerClient;
        if (mediaCenterClient != null) {
            mediaCenterClient.showNeedVip();
        }
    }

    private void registerBluetoothPhone() {
        PhoneStateReceiver phoneStateReceiver = new PhoneStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        intentFilter.addAction(PhoneStateReceiver.BT_HEADSET_CLIENT_ACTION_CALL_CHANGED);
        Log.d(TAG, "PhoneStateReceiver PhoneStateReceiver filter_dynamic: " + intentFilter);
        registerReceiver(phoneStateReceiver, intentFilter);
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnAudioFocusChangeCallbackListener
    public void onAudioFocusChangeCallback() {
        if (AudioConfig.isShowAudioFocusToast) {
            if (this.mActivityCount == 1) {
                ToastUtil.showMsg(R.string.audio_focus_occupation_no_play);
            } else if (AudioConfig.isWindowAddViewToast) {
                MyToastUtils.make().Text("音频通道被占用，无法播放焦点").show();
            } else {
                ToastUtil.showSystemToast(R.string.audio_focus_occupation_no_play);
            }
        }
    }

    public static Activity getTopActivity() {
        return topActivity;
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        return this.appViewModelStore;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int i = newConfig.uiMode & 48;
        if (this.currentNightMode != i) {
            Log.i(TAG, "onConfigurationChanged: 日夜间模式切换，currentNightMode=" + this.currentNightMode + " nightMode=" + i);
            this.currentNightMode = i;
            if (isAppForeground(mainApplication)) {
                Log.i(TAG, "onConfigurationChanged: 处于前台");
                this.isUiNightModeChangedWhenForeground = true;
            } else {
                Log.i(TAG, "onConfigurationChanged: 处于后台");
            }
        }
    }
}
