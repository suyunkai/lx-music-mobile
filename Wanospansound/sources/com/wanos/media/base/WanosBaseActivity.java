package com.wanos.media.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.base.BaseActivity;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.MediaCollectChangeEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.mediaCenter.AudioConfig;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.ComResCenter;
import com.wanos.media.R;
import com.wanos.media.databinding.ActivityWanosBaseBinding;
import com.wanos.media.presenter.IPresenter;
import com.wanos.media.ui.advertise.WanosJsBridge;
import com.wanos.media.util.AnitPlayClick;
import com.wanos.media.util.SearchEditText;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayServiceConnectListener;
import com.wanos.wanosplayermodule.MediaPlayerManager;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import me.jessyan.autosize.AutoSizeCompat;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WanosBaseActivity<P extends IPresenter> extends BaseActivity implements MediaPlayServiceConnectListener, OnStatusCallbackListener, MediaPlayerService.OnMediaInfoCallbackAppListener, MediaPlayerService.OnCacheProgressCallbackListener {
    private static final float CLICK_DRAG_TOLERANCE = 5.0f;
    public static final String TAG = "wanos:[WanosBaseActivity]";
    protected static int btnAiMusicMvX;
    protected static int btnAiMusicMvY;
    protected ActivityWanosBaseBinding activityWanosBaseBinding;
    private BaseActContent baseActContent;
    protected int currentNightMode;
    private float dX;
    private float dY;
    private Messenger mMainMessenger;
    private MediaPlayerService mMediaPlayerService;
    protected P mPresenter;
    private float preX;
    private float preY;
    private static DialogShowType dialogShowType = DialogShowType.NO;
    public static boolean isLoginConfirmed = false;
    private static String dialogShowName = "";
    private static boolean aiMusicMvIsInit = false;
    private boolean isUserClosePlayBar = false;
    public boolean isFromDeepLinkStart = false;
    private boolean btnAiMusicMvMove = false;
    private String jumpUrl = "";
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.wanos.media.base.WanosBaseActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Bundle data = message.getData();
            String string = data.getString(WanosJsBridge.METHOD_KEY_TOKEN);
            UserInfo userInfo = (UserInfo) new Gson().fromJson(data.getString("userInfo"), UserInfo.class);
            UserInfoUtil.saveUserInfo(userInfo);
            EventBus.getDefault().post(new UserInfoChangeEvent(userInfo));
            UserInfoUtil.getUserInfo().setToken(string);
        }
    };

    public enum DialogShowType {
        NO,
        Login,
        Logout,
        speedSet,
        AD,
        History,
        Code
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent loginOrLogoutEvent) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mediaCollectChange(MediaCollectChangeEvent mediaCollectChangeEvent) {
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnCacheProgressCallbackListener
    public void onCacheProgressCallbackNext(int i) {
    }

    protected void onNightModeChanged() {
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayServiceConnectListener
    public void onServiceDisconnected() {
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null) {
            baseActContent.onSaveInstanceState(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null) {
            baseActContent.onRestoreInstanceState(bundle);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.activityWanosBaseBinding = ActivityWanosBaseBinding.inflate(getLayoutInflater());
        if (ComResCenter.getInstance().mainServer != null) {
            BaseActContent baseActContentBaseActOnCreate = ComResCenter.getInstance().mainServer.baseActOnCreate(this, bundle);
            this.baseActContent = baseActContentBaseActOnCreate;
            baseActContentBaseActOnCreate.onCreate(bundle);
        }
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null && baseActContent.aiMusic() && !aiMusicMvIsInit) {
            initAiMusicMvPos();
        }
        this.currentNightMode = getResources().getConfiguration().uiMode & 48;
        if (getIntent().getData() != null) {
            this.isFromDeepLinkStart = true;
        }
        super.onCreate(bundle);
        super.setContentView(this.activityWanosBaseBinding.getRoot());
        getWindow().setSoftInputMode(48);
        initTitleBar();
        initPlayBar();
        initVisibleIsSpoken();
        BaseActContent baseActContent2 = this.baseActContent;
        if (baseActContent2 != null && baseActContent2.aiMusic()) {
            initAiMusicMv();
        }
        MediaPlayEngine.getInstance().addMediaPlayServiceConnectListener(this, true);
        EventBus.getDefault().register(this);
        BaseActContent baseActContent3 = this.baseActContent;
        if (baseActContent3 != null) {
            baseActContent3.showLoadDialog(this, dialogShowType, isLoginConfirmed);
        }
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.activityWanosBaseBinding.titleBar.titleLeftBtn.setContentDescription(getResources().getString(R.string.close_back_click));
            this.activityWanosBaseBinding.titleBar.btnSearch.setContentDescription(getResources().getString(R.string.open_search));
            this.activityWanosBaseBinding.playBar.setContentDescription(getResources().getString(R.string.open_current_media));
        }
    }

    public void geelyE171TopAndBottomPadding(int i, int i2) {
        if ("E171".equals(Build.MODEL)) {
            this.activityWanosBaseBinding.baseParentLl.setPadding(0, i, 0, i2);
        }
    }

    protected void initAiMusicMvPos() {
        btnAiMusicMvX = getResources().getDimensionPixelSize(R.dimen.card_ai_groove_left);
        btnAiMusicMvY = getResources().getDimensionPixelSize(R.dimen.card_ai_groove_bottom);
        aiMusicMvIsInit = true;
    }

    private void initTitleBar() {
        Util.setTextWeight(this.activityWanosBaseBinding.titleBar.tvTitle, 500);
        this.activityWanosBaseBinding.titleBar.titleLeftBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.base.WanosBaseActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WanosBaseActivity.this.onBackPressed();
            }
        });
    }

    public MediaPlayerService getMediaPlayerService() {
        return this.mMediaPlayerService;
    }

    private void initPlayBar() {
        this.activityWanosBaseBinding.playBar.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.base.WanosBaseActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaInfo curMediaInfo;
                if (WanosBaseActivity.this.mMediaPlayerService == null || (curMediaInfo = WanosBaseActivity.this.mMediaPlayerService.getCurMediaInfo()) == null || WanosBaseActivity.this.baseActContent == null) {
                    return;
                }
                MediaPlayerEnum.MediaType mediaType = curMediaInfo.getMediaType();
                if (mediaType == MediaPlayerEnum.MediaType.Music) {
                    WanosBaseActivity.this.baseActContent.openMusicPlayActivity(WanosBaseActivity.this);
                } else if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
                    WanosBaseActivity.this.baseActContent.openAudioBookPlayActivity(WanosBaseActivity.this);
                }
            }
        });
        this.activityWanosBaseBinding.btnPlayNext.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.base.WanosBaseActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WanosBaseActivity.this.mMediaPlayerService == null || !WanosBaseActivity.this.playChange()) {
                    return;
                }
                WanosBaseActivity.this.mMediaPlayerService.playNext();
                Log.i("zt", "用户点击快捷播放“下一首”按钮点击次数");
                MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_QUICKPLAY_NEXT_SONG_BUTTON, "", "", "", "", 0);
            }
        });
        this.activityWanosBaseBinding.btnPlayPause.setOnClickListener(new AnitPlayClick(500L) { // from class: com.wanos.media.base.WanosBaseActivity.4
            @Override // com.wanos.media.util.AnitPlayClick
            public void onAnitClick(View view) {
                if (WanosBaseActivity.this.mMediaPlayerService != null) {
                    if (WanosBaseActivity.this.mMediaPlayerService.isPlaying()) {
                        Log.i(WanosBaseActivity.TAG, "playBar 暂停");
                        WanosBaseActivity.this.mMediaPlayerService.pause();
                        Log.i("zt", "用户点击快捷播放“暂停”按钮点击次数");
                        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_QUICKPLAY_PAUSE_BUTTON, "", "", "", "", 0);
                        return;
                    }
                    Log.i(WanosBaseActivity.TAG, "playBar 开始");
                    WanosBaseActivity.this.mMediaPlayerService.start();
                    MediaInfo curMediaInfo = WanosBaseActivity.this.mMediaPlayerService.getCurMediaInfo();
                    if (curMediaInfo != null && curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
                        int iIsShowToast = WanosBaseActivity.this.isShowToast(MediaPlayerManager.getInstance().getCurrentMediaIndex());
                        if (iIsShowToast != 0) {
                            if (iIsShowToast == 2) {
                                ToastUtil.showMsg(R.string.toast_vip);
                            } else {
                                WanosBaseActivity.this.showLoginDialog();
                            }
                        }
                    }
                    Log.i("zt", "快捷播放“播放”按钮点击埋点");
                    MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_QUICKPLAY_PLAY_BUTTON, "", "", "", "", 0);
                }
            }
        });
    }

    int isShowToast(int i) {
        MediaInfo mediaInfo;
        if (i != -1 && (mediaInfo = MediaPlayerManager.getInstance().getMediaPlayList().get(i)) != null && mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && mediaInfo.getAudioBookInfoBean().getIsVip() == 1) {
            boolean zIsLogin = UserInfoUtil.isLogin();
            boolean zIsVipAndUnexpired = UserInfoUtil.isVipAndUnexpired();
            if (!zIsLogin) {
                return 1;
            }
            if (!zIsVipAndUnexpired) {
                return 2;
            }
        }
        return 0;
    }

    public void setTitleBarVisibility(int i) {
        this.activityWanosBaseBinding.titleBar.getRoot().setVisibility(i);
    }

    public void setPlayBarVisibility(int i) {
        this.activityWanosBaseBinding.playBar.setVisibility(i);
        this.isUserClosePlayBar = i != 0;
    }

    private void showPlayBar() {
        if (this.isUserClosePlayBar) {
            return;
        }
        this.activityWanosBaseBinding.playBar.setVisibility(0);
    }

    private void hidePlayBar() {
        this.activityWanosBaseBinding.playBar.setVisibility(8);
    }

    public void setTitleText(CharSequence charSequence) {
        this.activityWanosBaseBinding.titleBar.tvTitle.setText(charSequence);
    }

    public void setTitleText(int i) {
        this.activityWanosBaseBinding.titleBar.tvTitle.setText(i);
    }

    public void setLeftBtnImg(int i) {
        this.activityWanosBaseBinding.titleBar.titleLeftBtn.setImageResource(i);
    }

    public View getTitleRightView() {
        this.activityWanosBaseBinding.titleBar.searchEdit.setVisibility(0);
        this.activityWanosBaseBinding.titleBar.btnSearch.setVisibility(0);
        return this.activityWanosBaseBinding.titleBar.searchEdit;
    }

    public void setOnClickClearListener(SearchEditText.OnClickClearListener onClickClearListener) {
        this.activityWanosBaseBinding.titleBar.searchEdit.setOnClickClearListener(onClickClearListener);
    }

    public void setOnClickSearchListener(View.OnClickListener onClickListener) {
        this.activityWanosBaseBinding.titleBar.btnSearch.setOnClickListener(onClickListener);
    }

    public void setLeftBtnOnClickListener(View.OnClickListener onClickListener) {
        this.activityWanosBaseBinding.titleBar.titleLeftBtn.setOnClickListener(onClickListener);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i) {
        LayoutInflater.from(this).inflate(i, this.activityWanosBaseBinding.baseContentViewgroup);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        this.activityWanosBaseBinding.baseContentViewgroup.addView(view);
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null && baseActContent.aiMusic()) {
            setAiMusicMvPos();
        }
        if (!this.isUserClosePlayBar && this.mMediaPlayerService != null) {
            this.activityWanosBaseBinding.btnPlayPause.setImageResource(this.mMediaPlayerService.isPlaying() ? R.drawable.ic_play_bar_play : R.drawable.ic_play_bar_pause);
        }
        BaseActContent baseActContent2 = this.baseActContent;
        if (baseActContent2 != null) {
            baseActContent2.onResume();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.uiMode & 48;
        if (this.currentNightMode != i) {
            this.currentNightMode = i;
            BaseActContent baseActContent = this.baseActContent;
            if (baseActContent != null) {
                baseActContent.onConfigurationChanged(configuration);
            }
            int i2 = this.currentNightMode;
            if (i2 == 16) {
                AppCompatDelegate.setDefaultNightMode(1);
                recreate();
            } else if (i2 == 32) {
                AppCompatDelegate.setDefaultNightMode(2);
                recreate();
            }
            onNightModeChanged();
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null) {
            baseActContent.dismissLoginDialog();
        }
        P p = this.mPresenter;
        if (p != null) {
            p.onDestroy();
        }
        this.mPresenter = null;
        MediaPlayEngine.getInstance().removeMediaPlayServiceConnectListener(this);
        if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
            MediaPlayEngine.getInstance().removeMediaPlayServiceConnectListener(this);
            MediaPlayEngine.getInstance().getMediaPlayerService().removeOnStatusCallbackListener(this);
            MediaPlayEngine.getInstance().getMediaPlayerService().removeOnMediaInfoCallbackAppListener(this);
            MediaPlayEngine.getInstance().getMediaPlayerService().removeOnCacheProgressCallbackListener(this);
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoadingView() {
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(0);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(4);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(8);
    }

    public void showLoadingView(String str) {
        this.activityWanosBaseBinding.baseLoadingView.tvLoading.setText(str);
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(0);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(4);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(8);
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void closeLoadingView() {
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(8);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(0);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(8);
    }

    public void showLoadErrorView(View.OnClickListener onClickListener) {
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(8);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(4);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(0);
        this.activityWanosBaseBinding.baseErrorView.btnRetry.setOnClickListener(onClickListener);
    }

    public void showLoadErrorView(String str, View.OnClickListener onClickListener) {
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(8);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(4);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(0);
        this.activityWanosBaseBinding.baseErrorView.tvErrorMessage.setText(str);
        this.activityWanosBaseBinding.baseErrorView.btnRetry.setOnClickListener(onClickListener);
    }

    public void showLoadErrorView(String str, int i, View.OnClickListener onClickListener) {
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(8);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(4);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(0);
        this.activityWanosBaseBinding.baseErrorView.tvErrorMessage.setText(str);
        this.activityWanosBaseBinding.baseErrorView.ivError.setImageResource(i);
        this.activityWanosBaseBinding.baseErrorView.btnRetry.setOnClickListener(onClickListener);
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoginDialog() {
        showLoginDialog((DialogInterface.OnDismissListener) null, false);
    }

    public void showLoginDialog(DialogInterface.OnDismissListener onDismissListener, boolean z) {
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null) {
            baseActContent.showLoginDialog(onDismissListener, z);
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoginDialog(int i, String str) {
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null) {
            baseActContent.showLoginDialog(i, str);
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void openWeChatPayActivity() {
        Log.i(TAG, "openWeChatPayActivity: ");
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null) {
            baseActContent.openWeChatPayActivity();
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void openWeChatPayActivity(int i, int i2) {
        Log.i(TAG, "openWeChatPayActivity: from = " + i + " source = " + i2);
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null) {
            baseActContent.openWeChatPayActivity(i, i2);
        }
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayServiceConnectListener
    public void onServiceConnected(MediaPlayerService mediaPlayerService) {
        MediaPlayerService mediaPlayerService2 = MediaPlayEngine.getInstance().getMediaPlayerService();
        this.mMediaPlayerService = mediaPlayerService2;
        if (mediaPlayerService2 != null) {
            mediaPlayerService2.addOnStatusCallbackListener(this);
            this.mMediaPlayerService.addOnMediaInfoCallbackAppListener(this);
            mediaPlayerService.addOnCacheProgressCallbackListener(this);
            updatePlayBarMeidaView();
        }
    }

    private void updatePlayBarMeidaView() {
        MediaInfo curMediaInfo;
        AudioBookMineChapterItemBean audioBookInfoBean;
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null || this.isUserClosePlayBar || (curMediaInfo = mediaPlayerService.getCurMediaInfo()) == null) {
            return;
        }
        MediaPlayerEnum.MediaType mediaType = curMediaInfo.getMediaType();
        if (mediaType == MediaPlayerEnum.MediaType.Music) {
            MusicInfoBean musicInfoBean = curMediaInfo.getMusicInfoBean();
            if (musicInfoBean != null) {
                showPlayBar();
                GlideUtil.setImageData(musicInfoBean.getAvatar(), this.activityWanosBaseBinding.ivMediaCover, 100, 100);
                return;
            }
            return;
        }
        if (mediaType != MediaPlayerEnum.MediaType.Audiobook || (audioBookInfoBean = curMediaInfo.getAudioBookInfoBean()) == null) {
            return;
        }
        showPlayBar();
        GlideUtil.setImageData(audioBookInfoBean.getAvatar(), this.activityWanosBaseBinding.ivMediaCover, 100, 100);
    }

    @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            onMediaStart();
        } else if (callBackState == MediaPlayerEnum.CallBackState.PAUSING || callBackState == MediaPlayerEnum.CallBackState.PAUSE || callBackState == MediaPlayerEnum.CallBackState.ERROR || callBackState == MediaPlayerEnum.CallBackState.COMPLETE) {
            onMediaPause();
        }
    }

    private void onMediaStart() {
        if (this.isUserClosePlayBar) {
            return;
        }
        this.activityWanosBaseBinding.btnPlayPause.setImageResource(R.drawable.ic_play_bar_play);
    }

    private void onMediaPause() {
        if (this.isUserClosePlayBar) {
            return;
        }
        this.activityWanosBaseBinding.btnPlayPause.setImageResource(R.drawable.ic_play_bar_pause);
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, MediaInfo mediaInfo) {
        if (mediaInfoCallbackType == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
            updatePlayBarMeidaView();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        BaseActContent baseActContent = this.baseActContent;
        if (baseActContent != null) {
            baseActContent.onBackPressed();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources());
        } else {
            Log.i("AutoSizeCompat", "非主线程调用getResources()");
        }
        return super.getResources();
    }

    public void jumpUrlInApp(Context context, String str) {
        Uri uri = Uri.parse(str);
        if (Objects.equals(uri.getScheme(), "wanos")) {
            if ((Objects.equals(uri.getPath(), AppConstants.MEMBER_PAY_PATH) || Objects.equals(uri.getPath(), AppConstants.USER_INFO_CENTER_PATH)) && !UserInfoUtil.isLogin()) {
                showLoginDialog(-1, str);
                return;
            }
            if (Objects.equals(uri.getPath(), AppConstants.MY_MUSIC_PATH) || Objects.equals(uri.getPath(), AppConstants.MY_AUDIO_BOOK_PATH)) {
                if (!Objects.equals(uri.getQueryParameter(AppConstants.ACTIVITY_TAB_PAGE), ExifInterface.GPS_MEASUREMENT_2D) && !UserInfoUtil.isLogin()) {
                    showLoginDialog(-1, str);
                    return;
                } else {
                    jumpAction(context, uri);
                    return;
                }
            }
            if (Objects.equals(uri.getPath(), AppConstants.JUYIHALL_COLLECTION) && !UserInfoUtil.isLogin()) {
                showLoginDialog(-1, str);
                return;
            } else {
                jumpAction(context, uri);
                return;
            }
        }
        if (ComResCenter.getInstance().mainServer != null) {
            if (this.mMainMessenger == null) {
                this.mMainMessenger = new Messenger(new MainHandler(this));
            }
            ComResCenter.getInstance().mainServer.webActivityStart(context, str, UserInfoUtil.getToken(), this.mMainMessenger);
        }
    }

    private static class MainHandler extends Handler {
        private final WeakReference<Activity> mActivity;

        MainHandler(Activity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.mActivity.get() != null) {
                Bundle data = message.getData();
                String string = data.getString(WanosJsBridge.METHOD_KEY_TOKEN);
                UserInfo userInfo = (UserInfo) new Gson().fromJson(data.getString("userInfo"), UserInfo.class);
                UserInfoUtil.saveUserInfo(userInfo);
                EventBus.getDefault().post(new UserInfoChangeEvent(userInfo));
                UserInfoUtil.getUserInfo().setToken(string);
            }
        }
    }

    private void jumpAction(Context context, Uri uri) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(uri);
        context.startActivity(intent);
        Log.i(TAG, "jumpAction: =======");
    }

    public boolean playChange() {
        List<MediaInfo> currentList = this.mMediaPlayerService.getCurrentList();
        MediaInfo curMediaInfo = this.mMediaPlayerService.getCurMediaInfo();
        if (curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            AudioBookMineChapterItemBean audioBookInfoBean = curMediaInfo.getAudioBookInfoBean();
            if (currentList != null && currentList.size() != 0) {
                for (int i = 0; i < currentList.size(); i++) {
                    AudioBookMineChapterItemBean audioBookInfoBean2 = currentList.get(i).getAudioBookInfoBean();
                    if (audioBookInfoBean2 != null && audioBookInfoBean2.getId() == audioBookInfoBean.getId()) {
                        int i2 = i + 1;
                        if (i2 >= currentList.size() || currentList.get(i2).getAudioBookInfoBean().getIsVip() != 1 || UserInfoUtil.isVipAndUnexpired()) {
                            return true;
                        }
                        openWeChatPayActivity();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected void setAiMusicMvPos() {
        this.activityWanosBaseBinding.ivAiMusicmv.setX(btnAiMusicMvX);
        this.activityWanosBaseBinding.ivAiMusicmv.setY(btnAiMusicMvY);
    }

    protected void hideAiMusicMv() {
        this.activityWanosBaseBinding.ivAiMusicmv.setVisibility(8);
    }

    protected void showAiMusicMv() {
        if (this.baseActContent == null || !AudioConfig.isNeedAiMusic) {
            return;
        }
        this.baseActContent.showAiMusicMv();
    }

    private void initAiMusicMv() {
        this.activityWanosBaseBinding.ivAiMusicmv.setVisibility(8);
        this.activityWanosBaseBinding.ivAiMusicmv.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.base.WanosBaseActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.i(WanosBaseActivity.TAG, "open  activityWanosBaseBinding.ivAiMusicmv");
                Intent intent = new Intent("com.geely.aimusicmv.action");
                intent.setPackage("com.geely.aimusicmv");
                intent.putExtra("intention", "open");
                intent.putExtra("source", "wanos");
                WanosBaseActivity.this.startService(intent);
            }
        });
    }

    protected void setZeroTitleBarListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.activityWanosBaseBinding.titleBar.llZeroTitleBar.setVisibility(0);
            Util.setTextWeight(this.activityWanosBaseBinding.titleBar.tvShareCodeAdd, 500);
            this.activityWanosBaseBinding.titleBar.btnAddSoundByShareCode.setOnClickListener(onClickListener);
        }
    }
}
