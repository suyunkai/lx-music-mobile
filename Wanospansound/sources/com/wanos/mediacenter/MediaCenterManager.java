package com.wanos.mediacenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.extractor.ts.TsExtractor;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.eas.sdk.mediacenter.MediaCenterAPI;
import com.ecarx.eas.sdk.mediacenter.exception.MediaCenterException;
import com.ecarx.eas.sdk.vehicle.api.VehicleAPI;
import com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo;
import com.wanos.commonlibrary.bean.MediaInfoBean;
import com.wanos.commonlibrary.mediaCenter.CarInfo;
import com.wanos.commonlibrary.mediaCenter.MediaCenterClient;
import com.wanos.commonlibrary.mediaCenter.MediaCenterServer;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.mediacenter.bean.MediaCenterInitCompleteEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import kotlin.Result;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class MediaCenterManager implements DefaultLifecycleObserver {
    public static final String COM_WANOS_MEDIA = "com.wanos.media";
    private static volatile MediaCenterManager INSTANCE = null;
    private static final int MAX_RETRY_COUNT = 4;
    private static final int RETRY_DELAY_MS = 200;
    public static final String TAG = "wanos:[MediaCenterManager]";
    private static String currentRequestClient = null;
    public static boolean is245 = false;
    public static boolean isCheckDefault = false;
    public static boolean isEnableAdv = true;
    public static boolean isNeedTtsAudioFocus = false;
    private static Boolean isRegisterMusic = null;
    public static boolean isUseNativeTTS = false;
    public static boolean isUseNewHide = false;
    public static boolean isVoiceIgnoreFocus = false;
    private static Object token;
    private MediaCenterServer action;
    List<Integer> advCCValueL;
    ICarInfo iCarInfo;
    String mAppVersionName;
    Class mCls;
    public Context mContext;
    CustomMusicClient mCustomMusicClient;
    private Handler mHandler;
    private MediaCenterAPI mMediaCenterAPI;
    MediaInfoBean mMediaInfoBean;
    public SemanticsManager semanticsManager;
    public static Integer[] advCCValue = {Integer.valueOf(TsExtractor.TS_STREAM_TYPE_AC3), Integer.valueOf(TsExtractor.TS_STREAM_TYPE_HDMV_DTS), 132, 133, Integer.valueOf(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO), Integer.valueOf(TsExtractor.TS_STREAM_TYPE_E_AC3), Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS_HD)};
    private static MutableLiveData<String> showImg = new MutableLiveData<>();
    Integer[] validValue = {1, 2, 3, 4, 5, 6, 7, 128, Integer.valueOf(TsExtractor.TS_STREAM_TYPE_AC3), Integer.valueOf(TsExtractor.TS_STREAM_TYPE_HDMV_DTS), 131, 132, 133, Integer.valueOf(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO), Integer.valueOf(TsExtractor.TS_STREAM_TYPE_E_AC3), Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS_HD), 137, Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS), 140, 141};
    boolean isOnce = false;
    private int retryCount = 0;
    private CarInfoImp carInfoImp = new CarInfoImp();
    final Runnable retryRunnable = new Runnable() { // from class: com.wanos.mediacenter.MediaCenterManager.2
        @Override // java.lang.Runnable
        public void run() {
            Log.i(MediaCenterManager.TAG, "run: retryRunnable");
            if (MediaCenterManager.token != null || MediaCenterManager.this.retryCount >= 4) {
                return;
            }
            Log.i(MediaCenterManager.TAG, "run: retryRunnable retryCount < MAX_RETRY_COUNT");
            MediaCenterManager.this.retryIfNeeded();
        }
    };

    public final void init() {
    }

    public final void unregisterMusic() {
    }

    private MediaCenterManager() {
    }

    public final String getCurrentRequestClient() {
        return currentRequestClient;
    }

    public final void setCurrentRequestClient(String str) {
        currentRequestClient = str;
    }

    private void createFile(File file, int i) {
        File file2 = new File(file, "cc" + i);
        if (file2.exists()) {
            file2.delete();
        }
        try {
            Log.i(TAG, "createFile:" + file2 + " result:" + file2.createNewFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CarInfo getCarInfo() {
        return this.carInfoImp;
    }

    boolean isAdvancedDev(int i) {
        List<Integer> list = this.advCCValueL;
        if (list == null || list.isEmpty() || this.advCCValueL.contains(Integer.valueOf(i))) {
            Log.i(TAG, "cc is " + i);
            return true;
        }
        Log.i(TAG, "carInfoCC is " + i);
        return false;
    }

    public static MediaCenterManager getInstance() {
        if (INSTANCE == null) {
            synchronized (MediaCenterManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MediaCenterManager();
                }
            }
        }
        return INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retryIfNeeded() {
        int i;
        this.mHandler.removeCallbacks(this.retryRunnable);
        Object obj = token;
        if (obj == null && (i = this.retryCount) < 4) {
            this.retryCount = i + 1;
            Log.i(TAG, "重试初始化... 尝试次数 " + this.retryCount);
            this.mHandler.postDelayed(new Runnable() { // from class: com.wanos.mediacenter.MediaCenterManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m672lambda$retryIfNeeded$0$comwanosmediacenterMediaCenterManager();
                }
            }, 200L);
        } else if (obj == null) {
            Log.e(TAG, "MediaCenter初始化失败，已尝试 4 次");
        } else {
            Log.i(TAG, "retryIfNeeded: =11=");
        }
    }

    /* JADX INFO: renamed from: lambda$retryIfNeeded$0$com-wanos-mediacenter-MediaCenterManager, reason: not valid java name */
    /* synthetic */ void m672lambda$retryIfNeeded$0$comwanosmediacenterMediaCenterManager() {
        initMediaCenterAPI(this.mContext, this.action);
    }

    public final MediaCenterClient initMediaCenter(Context context, Class cls, final MediaCenterServer mediaCenterServer) {
        if (this.isOnce) {
            return null;
        }
        this.action = mediaCenterServer;
        this.isOnce = true;
        Log.i(TAG, "wanos initMediaCenter,retryCount = " + this.retryCount);
        final VehicleAPI vehicleAPI = VehicleAPI.get(context.getApplicationContext());
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        this.mContext = context;
        this.mCls = cls;
        if (this.advCCValueL == null) {
            this.advCCValueL = Arrays.asList(advCCValue);
        }
        if (vehicleAPI != null) {
            vehicleAPI.init(context.getApplicationContext(), new ECarXApiClient.Callback() { // from class: com.wanos.mediacenter.MediaCenterManager.1
                @Override // com.ecarx.eas.sdk.ECarXApiClient.Callback
                public void onAPIReady(boolean z) {
                    if (z) {
                        Log.e(MediaCenterManager.TAG, "onAPIReady is true");
                        MediaCenterManager.this.iCarInfo = vehicleAPI.getCarInfo();
                        if (MediaCenterManager.this.iCarInfo == null) {
                            Log.w(MediaCenterManager.TAG, "iCarInfo is null");
                            MediaCenterManager mediaCenterManager = MediaCenterManager.this;
                            mediaCenterManager.initMediaCenterAPI(mediaCenterManager.mContext, mediaCenterServer);
                            return;
                        } else {
                            if (MediaCenterManager.is245) {
                                Log.i(MediaCenterManager.TAG, "onAPIReady: is245 = true");
                                MediaCenterManager mediaCenterManager2 = MediaCenterManager.this;
                                mediaCenterManager2.showHideWidget(mediaCenterManager2.mContext, false);
                                MediaCenterManager.this.checkCCValue();
                                return;
                            }
                            MediaCenterManager mediaCenterManager3 = MediaCenterManager.this;
                            mediaCenterManager3.initMediaCenterAPI(mediaCenterManager3.mContext, mediaCenterServer);
                            return;
                        }
                    }
                    Log.e(MediaCenterManager.TAG, "onAPIReady is false");
                }
            });
        } else {
            Log.i(TAG, "initMediaCenter: vehicleAPI is null");
        }
        this.mAppVersionName = getAppVersionName(context);
        return new ClientImp(mediaCenterServer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkCCValue() {
        int carConfig = this.iCarInfo.getCarConfig(466);
        Log.i(TAG, "cc value:" + carConfig);
        if (isAdvancedDev(carConfig) || !isEnableAdv) {
            Log.i(TAG, "停止循环 高配");
            Context context = this.mContext;
            if (context == null || this.action == null) {
                return;
            }
            showAppIcon(context);
            initMediaCenterAPI(this.mContext, this.action);
            return;
        }
        Log.i(TAG, "停止循环 低配");
        if (isUseNewHide) {
            hideAppIcon(this.mContext);
        } else {
            hideApp(this.mContext, this.mCls);
        }
    }

    void initMediaCenterAPI(final Context context, final MediaCenterServer mediaCenterServer) {
        Log.i(TAG, "initMediaCenterAPI");
        this.mHandler.removeCallbacks(this.retryRunnable);
        this.mHandler.postDelayed(this.retryRunnable, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        MediaCenterAPI.get(context).init(context.getApplicationContext(), new ECarXApiClient.Callback() { // from class: com.wanos.mediacenter.MediaCenterManager.3
            @Override // com.ecarx.eas.sdk.ECarXApiClient.Callback
            public void onAPIReady(boolean z) {
                Log.e(MediaCenterManager.TAG, "onAPIReady: MediaCenterAPI init");
                if (z) {
                    MediaCenterManager.this.mHandler.removeCallbacks(MediaCenterManager.this.retryRunnable);
                    MediaCenterManager.this.mMediaCenterAPI = MediaCenterAPI.get(context);
                    try {
                        MediaCenterManager.this.mCustomMusicClient = new CustomMusicClient(mediaCenterServer);
                        MediaCenterManager.this.mCustomMusicClient.setMusicPlaybackInfo(WanosMusicPlaybackInfo.getInstance());
                        Object unused = MediaCenterManager.token = MediaCenterManager.this.mMediaCenterAPI.registerMusic("com.wanos.media", MediaCenterManager.this.mCustomMusicClient);
                        Log.i(MediaCenterManager.TAG, "onAPIReady: token is got");
                        EventBus.getDefault().post(new MediaCenterInitCompleteEvent());
                        boolean zRegisterMusicRecoveryIntent = MediaCenterManager.this.registerMusicRecoveryIntent();
                        MediaCenterManager.this.semanticsManager = new SemanticsManager(MediaCenterManager.this.mContext, mediaCenterServer, MediaCenterManager.this.mMediaCenterAPI, MediaCenterManager.token);
                        boolean zRegisterChannel = MediaCenterManager.this.semanticsManager.registerChannel();
                        boolean zUpdateMediaSourceTypeList = MediaCenterManager.this.mMediaCenterAPI.updateMediaSourceTypeList(MediaCenterManager.token, new int[]{6});
                        boolean zDeclareSupportCollectTypes = MediaCenterManager.this.declareSupportCollectTypes();
                        Log.i(MediaCenterManager.TAG, "---------------------b1 " + zRegisterMusicRecoveryIntent);
                        Log.i(MediaCenterManager.TAG, "---------------------b2 " + zRegisterChannel);
                        Log.i(MediaCenterManager.TAG, "---------------------b3 " + zUpdateMediaSourceTypeList);
                        Log.i(MediaCenterManager.TAG, "---------------------b4 " + zDeclareSupportCollectTypes);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        MediaCenterManager.this.retryIfNeeded();
                        return;
                    }
                }
                Log.i(MediaCenterManager.TAG, "onAPIReady 链接失败");
                MediaCenterManager.this.retryIfNeeded();
            }
        });
    }

    private void hideApp(Context context, Class cls) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, (Class<?>) cls);
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if (componentEnabledSetting == 0 || componentEnabledSetting == 1) {
            packageManager.setComponentEnabledSetting(componentName, 2, 0);
        }
        packageManager.setApplicationEnabledSetting(context.getPackageName(), 2, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHideWidget(Context context, boolean z) {
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, "com.wanos.media.WanosAppWidget"), z ? 1 : 2, 1);
    }

    public final void updateCurrentProgress(long j) {
        MediaCenterAPI mediaCenterAPI;
        if (token == null || (mediaCenterAPI = getMediaCenterAPI()) == null) {
            return;
        }
        try {
            mediaCenterAPI.updateCurrentProgress(token, j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void updatePlayInfo(MediaInfoBean mediaInfoBean, boolean z) {
        Log.i(TAG, "updatePlayInfo: isPlaying = " + z);
        if (mediaInfoBean == null) {
            return;
        }
        this.mMediaInfoBean = mediaInfoBean;
        if (token == null) {
            Log.i(TAG, "token is null");
        } else {
            try {
                requestFocus();
                WanosMusicPlaybackInfo.getInstance().setMediaInfoBean(mediaInfoBean);
                WanosMusicPlaybackInfo.getInstance().setPlaying(z);
                if (!UserInfoUtil.isLogin()) {
                    Log.i(TAG, "updatePlayInfo: 用户未登录，不更新收藏状态");
                    WanosMusicPlaybackInfo.getInstance().setCollected(false);
                } else {
                    Log.i(TAG, "updatePlayInfo: 用户已登录，更新收藏状态");
                    WanosMusicPlaybackInfo.getInstance().setCollected(mediaInfoBean.isCollection());
                }
                WanosMusicPlaybackInfo.getInstance().setLoopMode(mediaInfoBean.getRadioMode());
                Log.i(TAG, "更新节⽬信息 " + updateMusicPlaybackState(WanosMusicPlaybackInfo.getInstance()));
                updateMediaSourceTypeList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "appVersionName is " + this.mAppVersionName);
    }

    public final void updateCurrentSourceType() {
        MediaCenterAPI mediaCenterAPI = getMediaCenterAPI();
        if (mediaCenterAPI != null) {
            mediaCenterAPI.updateCurrentSourceType(token, 6);
        }
    }

    public final boolean updateMusicPlaybackState(WanosMusicPlaybackInfo wanosMusicPlaybackInfo) {
        Log.i(TAG, "updateMusicPlaybackState: " + wanosMusicPlaybackInfo.getTitle() + ", duration = " + wanosMusicPlaybackInfo.getDuration() + ",isPlaying = " + wanosMusicPlaybackInfo.isPlaying());
        MediaCenterAPI mediaCenterAPI = getMediaCenterAPI();
        if (mediaCenterAPI != null) {
            try {
                return mediaCenterAPI.updateMusicPlaybackState(token, wanosMusicPlaybackInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private final void updateMediaSourceTypeList() {
        Log.i(TAG, "updateMediaSourceTypeList: ");
        MediaCenterAPI mediaCenterAPI = getMediaCenterAPI();
        if (mediaCenterAPI != null) {
            mediaCenterAPI.updateMediaSourceTypeList(token, new int[]{6});
        }
    }

    public void updateCollectMsg2(boolean z) {
        WanosMusicPlaybackInfo wanosMusicPlaybackInfo = WanosMusicPlaybackInfo.getInstance();
        wanosMusicPlaybackInfo.setMediaInfoBean(this.mMediaInfoBean);
        wanosMusicPlaybackInfo.setCollected(z);
        updateMusicPlaybackState(wanosMusicPlaybackInfo);
    }

    public void onSetLoopMode(int i) {
        if (getMediaCenterAPI() != null) {
            WanosMusicPlaybackInfo wanosMusicPlaybackInfo = WanosMusicPlaybackInfo.getInstance();
            wanosMusicPlaybackInfo.setMediaInfoBean(this.mMediaInfoBean);
            wanosMusicPlaybackInfo.setLoopMode(i);
            updateMusicPlaybackState(wanosMusicPlaybackInfo);
        }
    }

    public void updateCurrentPlayStatus(boolean z) {
        if (getMediaCenterAPI() != null) {
            WanosMusicPlaybackInfo wanosMusicPlaybackInfo = WanosMusicPlaybackInfo.getInstance();
            wanosMusicPlaybackInfo.setMediaInfoBean(this.mMediaInfoBean);
            wanosMusicPlaybackInfo.setPlaying(z);
            updateMusicPlaybackState(wanosMusicPlaybackInfo);
        }
    }

    public final MediaCenterAPI getMediaCenterAPI() {
        return INSTANCE.mMediaCenterAPI;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean declareSupportCollectTypes() {
        try {
            return this.mMediaCenterAPI.declareSupportCollectTypes(token, new int[]{0, -1});
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean registerMusicRecoveryIntent() {
        boolean zRegisterMusicRecoveryIntent = false;
        try {
            zRegisterMusicRecoveryIntent = getMediaCenterAPI().registerMusicRecoveryIntent(token, 0, new Intent("com.wanos.media.recovery").setPackage("com.wanos.media"));
            Log.i(TAG, "registerMusicRecoveryIntent result:" + zRegisterMusicRecoveryIntent);
            return zRegisterMusicRecoveryIntent;
        } catch (Throwable unused) {
            Result.Companion companion = Result.INSTANCE;
            return zRegisterMusicRecoveryIntent;
        }
    }

    public final void unRegisterMusicRecoveryIntent() {
        MediaCenterAPI mediaCenterAPI = getMediaCenterAPI();
        if (mediaCenterAPI != null) {
            try {
                mediaCenterAPI.unRegisterMusicRecoveryIntent(token);
            } catch (MediaCenterException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void requestFocus() {
        Context context = this.mContext;
        if (context == null || token == null) {
            return;
        }
        try {
            String strQueryCurrentFocusClient = MediaCenterAPI.get(context).queryCurrentFocusClient(token);
            if (TextUtils.isEmpty(strQueryCurrentFocusClient) || !"com.wanos.media".equals(strQueryCurrentFocusClient)) {
                boolean zRequestPlay = MediaCenterAPI.get(this.mContext).requestPlay(token);
                Log.i(TAG, "requestPlay: " + zRequestPlay);
                this.action.isUpdateMusicPlaybackState(zRequestPlay);
                updateCurrentSourceType();
            }
            if ("com.wanos.media".equals(strQueryCurrentFocusClient)) {
                this.action.isUpdateMusicPlaybackState(true);
            }
        } catch (MediaCenterException e) {
            Log.e(TAG, "error:" + e.getMessage());
        }
    }

    public boolean currentFocusIsEmpty() {
        Log.i(TAG, "currentFocusIsEmpty: token=" + token);
        Context context = this.mContext;
        if (context == null || token == null) {
            return false;
        }
        try {
            String strQueryCurrentFocusClient = MediaCenterAPI.get(context).queryCurrentFocusClient(token);
            Log.i(TAG, "front:" + strQueryCurrentFocusClient);
            return TextUtils.isEmpty(strQueryCurrentFocusClient);
        } catch (MediaCenterException unused) {
            return false;
        }
    }

    public boolean hasCurrentFocus() {
        Context context = this.mContext;
        if (context == null || token == null) {
            return false;
        }
        try {
            String strQueryCurrentFocusClient = MediaCenterAPI.get(context).queryCurrentFocusClient(token);
            Log.i(TAG, "front:" + strQueryCurrentFocusClient);
            return "com.wanos.media".equals(strQueryCurrentFocusClient);
        } catch (MediaCenterException unused) {
            return false;
        }
    }

    public void updateLrc(String str) {
        WanosMusicPlaybackInfo wanosMusicPlaybackInfo = WanosMusicPlaybackInfo.getInstance();
        wanosMusicPlaybackInfo.setLrc(str);
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer == null || !mediaCenterServer.isPlaying()) {
            return;
        }
        updateMusicPlaybackState(wanosMusicPlaybackInfo);
    }

    public boolean isPlaying() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer != null) {
            return mediaCenterServer.isPlaying();
        }
        return false;
    }

    public boolean isPreparing() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer != null) {
            return mediaCenterServer.isPreparing();
        }
        return false;
    }

    public String queryCurrentFocusClient() {
        if (token == null) {
            Log.e(TAG, "token is null");
            return null;
        }
        MediaCenterAPI mediaCenterAPI = getMediaCenterAPI();
        if (mediaCenterAPI == null) {
            return null;
        }
        try {
            return mediaCenterAPI.queryCurrentFocusClient(token);
        } catch (MediaCenterException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAppVersionName(Context context) {
        String str = "";
        try {
            String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str2 != null) {
                try {
                    if (str2.length() > 0) {
                        return str2;
                    }
                } catch (Exception unused) {
                    str = str2;
                    Log.e(TAG, "VersionInfo Exception");
                    return str;
                }
            }
            return "";
        } catch (Exception unused2) {
        }
    }

    private void showAppIcon(Context context) {
        showHideWidget(context, true);
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, "com.wanos.media.ui.actvity.SplashActivity"), 1, 1);
    }

    private void hideAppIcon(Context context) {
        showHideWidget(context, false);
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, "com.wanos.media.ui.actvity.SplashActivity"), 2, 1);
    }

    public void sendNoContentError() {
        if (token == null) {
            Log.e(TAG, "token is null");
            return;
        }
        MediaCenterAPI mediaCenterAPI = getMediaCenterAPI();
        if (mediaCenterAPI != null) {
            mediaCenterAPI.updateErrorMsg(token, 6, "暂无播放内容");
        }
    }

    public Object getToken() {
        return token;
    }
}
