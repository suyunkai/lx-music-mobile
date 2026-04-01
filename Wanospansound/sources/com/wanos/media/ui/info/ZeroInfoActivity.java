package com.wanos.media.ui.info;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.exoplayer.Renderer;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.utils.GlideOptions;
import com.wanos.commonlibrary.utils.StatisticPollUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.ZeroApplication;
import com.wanos.media.base.BaseZeroFragment;
import com.wanos.media.base.IDialogStateCallback;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.EventExitScene;
import com.wanos.media.entity.IMenuLibCallback;
import com.wanos.media.entity.IZeroBaseAction;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.entity.ThemeAudioInfoEntity;
import com.wanos.media.entity.VolumeModifyEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.entity.ZeroThemeInfo;
import com.wanos.media.util.RelaxInfoRunBackground;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.view.AlarmDialog;
import com.wanos.media.view.SettingDialog;
import com.wanos.media.view.TrialEndDialog;
import com.wanos.media.widget.RelaxInfoActionButton;
import com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter;
import com.wanos.media.widget.controller.WanosZeroControlView;
import com.wanos.media.widget.video.ZeroVideoPageView;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.ActivityZeroInfoBinding;
import com.wanos.zero.ZeroAudioFocusTools;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroInfoActivity extends AppCompatActivity implements IZeroBaseAction, AlarmDialog.IDialogDismissCallback, IMenuLibCallback, StatisticPollUtil.ReportedPollListener, SettingDialog.IDismissListener, IDialogStateCallback {
    private static final String TAG = "ZeroInfoActivity";
    private static final String TAG_ZERO_NORMAL = "fragment_normal";
    private static final String TAG_ZERO_PRO = "fragment_pro";
    private ActivityZeroInfoBinding mViewBinding;
    private ZeroInfoViewModel mViewModel;
    private ZeroImmersionHelp mZeroImmersionHelp;
    private final Utils.OnAppStatusChangedListener mOnAppStatusChangedListener = new Utils.OnAppStatusChangedListener() { // from class: com.wanos.media.ui.info.ZeroInfoActivity.1
        @Override // com.blankj.utilcode.util.Utils.OnAppStatusChangedListener
        public void onForeground(Activity activity) {
        }

        @Override // com.blankj.utilcode.util.Utils.OnAppStatusChangedListener
        public void onBackground(Activity activity) {
            ZeroLogcatTools.d(ZeroInfoActivity.TAG, "app background " + activity);
            if (activity instanceof ZeroInfoActivity) {
                RelaxInfoRunBackground.showRunBackgroundMsg(StringUtils.getString(R.string.toast_not_background_play));
                ZeroInfoActivity.this.onExitSceneEvent(new EventExitScene());
            }
        }
    };
    private final Runnable mSettingRunnable = new Runnable() { // from class: com.wanos.media.ui.info.ZeroInfoActivity.2
        @Override // java.lang.Runnable
        public void run() {
            ZeroLogcatTools.i(ZeroInfoActivity.TAG, "SettingClick --> onDismiss: 开始播放主题");
            ZeroInfoActivity.this.mViewModel.setAlarmState(false, true);
        }
    };
    private boolean aTaste = false;

    public IMenuLibCallback getMenuLibCallback() {
        return this;
    }

    public IZeroBaseAction getPlayEvent() {
        return this;
    }

    static {
        System.loadLibrary("ZeroBall");
    }

    public static void onLaunch(Context context, ZeroPageMode zeroPageMode, SpaceThemeBaseInfo spaceThemeBaseInfo) {
        ZeroApplication.getZeroCallback().onRelaxEnter();
        if (ZeroAudioFocusTools.getInstance().isCallActive()) {
            ToastUtil.showMsg(StringUtils.getString(R.string.error_during_call));
            return;
        }
        if (!(!ZeroAudioFocusTools.getInstance().isCanFocus() ? ZeroAudioFocusTools.getInstance().requestAudioFocus() : true)) {
            ToastUtil.showMsg(R.string.error_audio_focus);
            return;
        }
        Intent intent = new Intent(context, (Class<?>) ZeroInfoActivity.class);
        intent.putExtra("open_model", zeroPageMode);
        intent.putExtra("item_info", spaceThemeBaseInfo);
        context.startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AppCompatDelegate.setDefaultNightMode(-1);
        this.mViewModel = (ZeroInfoViewModel) new ViewModelProvider(this).get(ZeroInfoViewModel.class);
        ActivityZeroInfoBinding activityZeroInfoBindingInflate = ActivityZeroInfoBinding.inflate(getLayoutInflater());
        this.mViewBinding = activityZeroInfoBindingInflate;
        setContentView(activityZeroInfoBindingInflate.getRoot());
        hideSystemBars();
        EventBus.getDefault().register(this);
        this.mZeroImmersionHelp = new ZeroImmersionHelp(this);
        this.mViewModel.initItemInfo((SpaceThemeBaseInfo) getIntent().getSerializableExtra("item_info"));
        this.mViewModel.initZeroPageMode((ZeroPageMode) getIntent().getSerializableExtra("open_model"));
        this.mViewBinding.controlView.setTitleText(this.mViewModel.getItemInfo().getName(), this.mViewModel.getItemInfo().isVip());
        this.mViewBinding.controlView.setMeEnterState(this.mViewModel.getInitState() == ZeroPageMode.XIAO_QI_PRO || this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_PRO);
        this.mViewBinding.controlView.setZeroPageMode(this.mViewModel.getCurrentState());
        ZeroLogcatTools.d(TAG, "启动模式 = " + this.mViewModel.getInitState() + ", 最新模式 = " + this.mViewModel.getCurrentState());
        StatisticPollUtil.getInstance().setReportedPollListener(this);
        if (this.mViewModel.getInitState() == ZeroPageMode.XIAO_QI_STANDARD || this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_STANDARD) {
            showFragment(this.mViewModel.getCurrentState());
        } else {
            this.mViewBinding.ivBgImage.setVisibility(0);
            new GlideOptions().setSize(1280, 760).setPlaceholder(-1).setCovertDimens(false).onLoad(this.mViewModel.getItemInfo().getBgImg(), this.mViewBinding.ivBgImage);
            if (this.mViewModel.getZeroThemeInfoEntity() == null) {
                this.mViewModel.initThemeInfoData();
            }
        }
        ThreadUtils.getMainHandler().postDelayed(this.mZeroImmersionHelp, Renderer.DEFAULT_DURATION_TO_PROGRESS_US);
        this.mViewBinding.controlView.setControlCallback(new WanosZeroControlView.IControlCallback() { // from class: com.wanos.media.ui.info.ZeroInfoActivity.3
            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public int onPageScrollState() {
                ZeroNormalFragment zeroNormalFragment = (ZeroNormalFragment) ZeroInfoActivity.this.findZeroFragment(ZeroInfoActivity.TAG_ZERO_NORMAL);
                if (zeroNormalFragment == null) {
                    return 0;
                }
                return zeroNormalFragment.getPageScrollState();
            }

            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public void onZeroBackClick() {
                ZeroInfoActivity.this.onBackPressed();
            }

            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public void onZeroRandomOrSelect(ZeroPageMode zeroPageMode, final WanosZeroControlView.ISceneLoadState iSceneLoadState) {
                if (ZeroInfoActivity.this.mViewModel.getInitState() != ZeroPageMode.MING_XIANG_STANDARD && ZeroInfoActivity.this.mViewModel.getInitState() != ZeroPageMode.MING_XIANG_PRO) {
                    BaseZeroFragment baseZeroFragmentFindZeroFragment = ZeroInfoActivity.this.findZeroFragment(ZeroInfoActivity.TAG_ZERO_PRO);
                    if (baseZeroFragmentFindZeroFragment instanceof ZeroProFragment) {
                        ((ZeroProFragment) baseZeroFragmentFindZeroFragment).initCacheBall();
                    }
                    BaseZeroFragment baseZeroFragmentFindZeroFragment2 = ZeroInfoActivity.this.findZeroFragment(ZeroInfoActivity.TAG_ZERO_NORMAL);
                    if (baseZeroFragmentFindZeroFragment2 instanceof ZeroNormalFragment) {
                        ((ZeroNormalFragment) baseZeroFragmentFindZeroFragment2).setRandomScene(new ZeroVideoPageView.ISceneLoadListener() { // from class: com.wanos.media.ui.info.ZeroInfoActivity.3.1
                            @Override // com.wanos.media.widget.video.ZeroVideoPageView.ISceneLoadListener
                            public void onSceneLoaded(AudioSceneInfoEntity audioSceneInfoEntity) {
                                ZeroLogcatTools.d(ZeroInfoActivity.TAG, "onZeroRandomOrSelect: 场景随机成功");
                                ZeroInfoActivity.this.setAudioData(audioSceneInfoEntity);
                                iSceneLoadState.onSceneLoaded();
                            }

                            @Override // com.wanos.media.widget.video.ZeroVideoPageView.ISceneLoadListener
                            public void onSceneLoadError(String str) {
                                ZeroLogcatTools.e(ZeroInfoActivity.TAG, "onZeroRandomOrSelect: 场景随机失败==>" + str);
                                ToastUtil.showMsg(str);
                                iSceneLoadState.onSceneLoaded();
                            }
                        });
                        return;
                    }
                    return;
                }
                ZeroLogcatTools.d(ZeroInfoActivity.TAG, "onZeroRandomOrSelect：冥想模式下，无法进行随机");
            }

            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public void onZeroPersonalizeClick(ZeroPageMode zeroPageMode) {
                if (ZeroPageMode.XIAO_QI_PRO == zeroPageMode || ZeroPageMode.MING_XIANG_PRO == zeroPageMode) {
                    ZeroInfoActivity.this.mViewBinding.controlView.setZeroPageMode(ZeroInfoActivity.this.mViewModel.getInitState());
                    ZeroInfoActivity zeroInfoActivity = ZeroInfoActivity.this;
                    zeroInfoActivity.showFragment(zeroInfoActivity.mViewModel.getInitState());
                } else if (ZeroPageMode.XIAO_QI_STANDARD == zeroPageMode || ZeroPageMode.MING_XIANG_STANDARD == zeroPageMode) {
                    if (ZeroInfoActivity.this.mViewModel.getZeroThemeInfoEntity() != null) {
                        if (ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.XIAO_QI_STANDARD) {
                            ZeroInfoActivity.this.mViewModel.setCurrentState(ZeroPageMode.XIAO_QI_PRO);
                        }
                        if (ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_STANDARD) {
                            ZeroInfoActivity.this.mViewModel.setCurrentState(ZeroPageMode.MING_XIANG_PRO);
                        }
                        ZeroInfoActivity.this.mViewBinding.controlView.setZeroPageMode(ZeroInfoActivity.this.mViewModel.getCurrentState());
                        ZeroInfoActivity zeroInfoActivity2 = ZeroInfoActivity.this;
                        zeroInfoActivity2.showFragment(zeroInfoActivity2.mViewModel.getCurrentState());
                        return;
                    }
                    ToastUtil.showMsg(ZeroInfoActivity.this.getString(R.string.theme_source_not_ready));
                }
            }

            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public void onEnterRelaxState() {
                ZeroLogcatTools.d(ZeroInfoActivity.TAG, "onEnterRelaxState: 进入小憩或冥想模式");
                ZeroInfoActivity zeroInfoActivity = ZeroInfoActivity.this;
                zeroInfoActivity.setControlState(true, zeroInfoActivity.mViewModel.isImmersionState());
                if (ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_STANDARD || ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_PRO) {
                    if (ZeroInfoActivity.this.mViewModel.getZeroThemeInfoEntity() == null) {
                        ToastUtil.showMsg(ZeroInfoActivity.this.getString(R.string.theme_source_not_ready));
                        return;
                    }
                    ZeroAudioBallTools.getInstance().onMediaReplay();
                }
                if (ZeroInfoActivity.this.mViewModel.getInitState() != ZeroPageMode.XIAO_QI_STANDARD) {
                    if (ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_STANDARD) {
                        Log.i("zt", "点击开启冥想人数");
                        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_MEDITE_OPEN, "", "", "", "", 0);
                        return;
                    }
                    return;
                }
                Log.i("zt", "点击开启小憩人数");
                MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_NAP_OPEN, "", "", "", "", 0);
            }

            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public void onExitRelaxState() {
                ZeroLogcatTools.d(ZeroInfoActivity.TAG, "onExitRelaxState: 退出小憩或冥想模式");
                ZeroInfoActivity.this.setExitRelaxState();
            }

            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public void onCancelRelaxState() {
                ZeroLogcatTools.d(ZeroInfoActivity.TAG, "onCancelRelaxState: 取消小憩或冥想模式");
                ZeroInfoActivity zeroInfoActivity = ZeroInfoActivity.this;
                zeroInfoActivity.setControlState(false, zeroInfoActivity.mViewModel.isImmersionState());
                if (ZeroInfoActivity.this.mViewModel.getInitState() != ZeroPageMode.XIAO_QI_STANDARD) {
                    if (ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_STANDARD) {
                        Log.i("zt", "点击结束冥想人数");
                        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_MEDITE_CLOSE, "", "", "", "", 0);
                        return;
                    }
                    return;
                }
                Log.i("zt", "点击结束小憩人数");
                MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_NAP_CLOSE, "", "", "", "", 0);
            }

            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public void onRelaxTimeSelect(int i, int i2, WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback) {
                ZeroLogcatTools.d(ZeroInfoActivity.TAG, "onRelaxTimeSelect: position==>" + i + ",time==>" + i2);
                if (ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_PRO || ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_STANDARD) {
                    ZeroInfoActivity.this.removeProFragment();
                    try {
                        BaseZeroFragment baseZeroFragmentFindZeroFragment = ZeroInfoActivity.this.findZeroFragment(ZeroInfoActivity.TAG_ZERO_NORMAL);
                        if (baseZeroFragmentFindZeroFragment instanceof ZeroNormalFragment) {
                            ((ZeroNormalFragment) baseZeroFragmentFindZeroFragment).setAudioPosition(i2, iItemStateCallback);
                        }
                    } catch (Exception e) {
                        ZeroLogcatTools.e(ZeroInfoActivity.TAG, "onRelaxTimeSelect: " + e.getMessage());
                    }
                }
            }

            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public void onTrialEnd() {
                if (ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.XIAO_QI_PRO || ZeroInfoActivity.this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_PRO) {
                    ZeroInfoActivity.this.mViewModel.setAlarmState(true, true);
                    TrialEndDialog.show(ZeroInfoActivity.this.getSupportFragmentManager(), new TrialEndDialog.ITrialCallback() { // from class: com.wanos.media.ui.info.ZeroInfoActivity.3.2
                        @Override // com.wanos.media.view.TrialEndDialog.ITrialCallback
                        public void onContinuePlay(TrialEndDialog trialEndDialog) {
                            trialEndDialog.dismiss();
                            ZeroInfoActivity.this.mViewModel.setAlarmState(false, true);
                        }

                        @Override // com.wanos.media.view.TrialEndDialog.ITrialCallback
                        public void onExitScene(TrialEndDialog trialEndDialog) {
                            trialEndDialog.dismiss();
                            ZeroInfoActivity.this.onExitSceneEvent(new EventExitScene());
                        }
                    });
                    return;
                }
                ToastUtil.showMsg(R.string.relax_trial_end);
                BaseZeroFragment baseZeroFragmentFindZeroFragment = ZeroInfoActivity.this.findZeroFragment(ZeroInfoActivity.TAG_ZERO_NORMAL);
                if (baseZeroFragmentFindZeroFragment instanceof ZeroNormalFragment) {
                    ((ZeroNormalFragment) baseZeroFragmentFindZeroFragment).setNextTheme();
                }
            }

            @Override // com.wanos.media.widget.controller.WanosZeroControlView.IControlCallback
            public void onSettingClick() {
                ZeroInfoActivity.this.mViewModel.setAlarmState(true, true);
                SettingDialog.show(ZeroInfoActivity.this.getSupportFragmentManager());
            }
        });
        this.mViewBinding.controlView.getEditStateLiveData().observe(this, new Observer() { // from class: com.wanos.media.ui.info.ZeroInfoActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m492lambda$onCreate$0$comwanosmediauiinfoZeroInfoActivity((Integer) obj);
            }
        });
        this.mViewModel.onThemeInfoEvent.observe(this, new Observer() { // from class: com.wanos.media.ui.info.ZeroInfoActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m493lambda$onCreate$1$comwanosmediauiinfoZeroInfoActivity((AudioSceneInfoEntity) obj);
            }
        });
        this.mViewModel.onMingXiangDurationEvent.observe(this, new Observer() { // from class: com.wanos.media.ui.info.ZeroInfoActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m494lambda$onCreate$2$comwanosmediauiinfoZeroInfoActivity((String[]) obj);
            }
        });
        this.mViewModel.onUserThemeLoadError.observe(this, new Observer() { // from class: com.wanos.media.ui.info.ZeroInfoActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m495lambda$onCreate$3$comwanosmediauiinfoZeroInfoActivity((String) obj);
            }
        });
        this.mViewModel.onXiaoQiDurationEvent.observe(this, new Observer() { // from class: com.wanos.media.ui.info.ZeroInfoActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m496lambda$onCreate$4$comwanosmediauiinfoZeroInfoActivity((String) obj);
            }
        });
        AppUtils.registerAppStatusChangedListener(this.mOnAppStatusChangedListener);
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-wanos-media-ui-info-ZeroInfoActivity, reason: not valid java name */
    /* synthetic */ void m492lambda$onCreate$0$comwanosmediauiinfoZeroInfoActivity(Integer num) {
        if (ZeroPageMode.MING_XIANG_STANDARD == this.mViewModel.getCurrentState() || ZeroPageMode.MING_XIANG_PRO == this.mViewModel.getCurrentState()) {
            BaseZeroFragment<?, ?> baseZeroFragmentFindZeroFragment = findZeroFragment(TAG_ZERO_NORMAL);
            if (baseZeroFragmentFindZeroFragment instanceof ZeroNormalFragment) {
                if (num.intValue() == 102) {
                    ((ZeroNormalFragment) baseZeroFragmentFindZeroFragment).setViewPageEnable(false);
                } else {
                    ((ZeroNormalFragment) baseZeroFragmentFindZeroFragment).setViewPageEnable(true);
                }
            }
        }
    }

    /* JADX INFO: renamed from: lambda$onCreate$1$com-wanos-media-ui-info-ZeroInfoActivity, reason: not valid java name */
    /* synthetic */ void m493lambda$onCreate$1$comwanosmediauiinfoZeroInfoActivity(AudioSceneInfoEntity audioSceneInfoEntity) {
        ZeroLogcatTools.d(TAG, "onThemeInfoEvent: entity = " + audioSceneInfoEntity.getAudioId());
        this.mViewModel.setZeroThemeInfoEntity(audioSceneInfoEntity);
        ZeroAudioBallTools.getInstance().initScene(audioSceneInfoEntity.getDetailInfo(), new AnonymousClass4());
    }

    /* JADX INFO: renamed from: com.wanos.media.ui.info.ZeroInfoActivity$4, reason: invalid class name */
    class AnonymousClass4 implements ZeroAudioBallTools.ILoadingCallback {
        AnonymousClass4() {
        }

        @Override // com.wanos.media.util.ZeroAudioBallTools.ILoadingCallback
        public void onAudioReady() {
            ZeroInfoActivity zeroInfoActivity = ZeroInfoActivity.this;
            zeroInfoActivity.showFragment(zeroInfoActivity.mViewModel.getInitState());
            if (!ZeroInfoActivity.this.mViewModel.getItemInfo().isVip()) {
                ZeroInfoActivity.this.mViewBinding.controlView.setRelaxInfoActionButtonState(RelaxInfoActionButton.State.NORMAL);
            } else if (!UserInfoUtil.isLogin() || !UserInfoUtil.isVipAndUnexpired()) {
                ZeroInfoActivity.this.mViewBinding.controlView.setRelaxInfoActionButtonState(RelaxInfoActionButton.State.TIMING);
            } else {
                ZeroInfoActivity.this.mViewBinding.controlView.setRelaxInfoActionButtonState(RelaxInfoActionButton.State.NORMAL);
            }
            ThreadUtils.getMainHandler().postDelayed(new Runnable() { // from class: com.wanos.media.ui.info.ZeroInfoActivity$4$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m498lambda$onAudioReady$0$comwanosmediauiinfoZeroInfoActivity$4();
                }
            }, 400L);
            ZeroInfoActivity.this.setRelaxState(true);
        }

        /* JADX INFO: renamed from: lambda$onAudioReady$0$com-wanos-media-ui-info-ZeroInfoActivity$4, reason: not valid java name */
        /* synthetic */ void m498lambda$onAudioReady$0$comwanosmediauiinfoZeroInfoActivity$4() {
            ZeroInfoActivity.this.mViewBinding.ivBgImage.setImageDrawable(null);
            ZeroInfoActivity.this.mViewBinding.ivBgImage.setVisibility(8);
            Glide.with((FragmentActivity) ZeroInfoActivity.this).clear(ZeroInfoActivity.this.mViewBinding.ivBgImage);
        }
    }

    /* JADX INFO: renamed from: lambda$onCreate$2$com-wanos-media-ui-info-ZeroInfoActivity, reason: not valid java name */
    /* synthetic */ void m494lambda$onCreate$2$comwanosmediauiinfoZeroInfoActivity(String[] strArr) {
        this.mViewBinding.controlView.setMingXiangPickerData(strArr);
    }

    /* JADX INFO: renamed from: lambda$onCreate$3$com-wanos-media-ui-info-ZeroInfoActivity, reason: not valid java name */
    /* synthetic */ void m495lambda$onCreate$3$comwanosmediauiinfoZeroInfoActivity(String str) {
        ToastUtil.showMsg(str);
        this.mViewBinding.controlView.setRelaxInfoActionButtonState(RelaxInfoActionButton.State.ERROR);
    }

    /* JADX INFO: renamed from: lambda$onCreate$4$com-wanos-media-ui-info-ZeroInfoActivity, reason: not valid java name */
    /* synthetic */ void m496lambda$onCreate$4$comwanosmediauiinfoZeroInfoActivity(String str) {
        this.mViewBinding.controlView.setXiaoQiPickerData();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            if (this.mViewModel.isImmersionState()) {
                setControlState(this.mViewBinding.controlView.isRelaxRunning(), false);
                return true;
            }
            ThreadUtils.getMainHandler().removeCallbacks(this.mZeroImmersionHelp);
        }
        if (1 == motionEvent.getAction()) {
            ThreadUtils.getMainHandler().postDelayed(this.mZeroImmersionHelp, Renderer.DEFAULT_DURATION_TO_PROGRESS_US);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ThreadUtils.getMainHandler().removeCallbacks(this.mSettingRunnable);
        EventBus.getDefault().unregister(this);
        AppUtils.unregisterAppStatusChangedListener(this.mOnAppStatusChangedListener);
        StatisticPollUtil.getInstance().clearHandler();
        ThreadUtils.getMainHandler().removeCallbacks(this.mZeroImmersionHelp);
    }

    public void setExitRelaxState() {
        ZeroLogcatTools.d(TAG, "setExitRelaxState: 退出小憩或冥想模式");
        this.mViewBinding.controlView.setStopCountdownTime();
        setControlState(false, this.mViewModel.isImmersionState());
        AlarmDialog.showDialog(getSupportFragmentManager(), (this.mViewModel.getInitState() == ZeroPageMode.XIAO_QI_STANDARD || this.mViewModel.getInitState() == ZeroPageMode.XIAO_QI_PRO) ? "小憩结束" : "冥想结束", this);
        this.mViewModel.setAlarmState(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFragment(ZeroPageMode zeroPageMode) {
        this.mViewModel.setCurrentState(zeroPageMode);
        Fragment fragmentFindFragmentByTag = getSupportFragmentManager().findFragmentByTag(TAG_ZERO_NORMAL);
        Fragment fragmentFindFragmentByTag2 = getSupportFragmentManager().findFragmentByTag(TAG_ZERO_PRO);
        if (zeroPageMode == ZeroPageMode.MING_XIANG_STANDARD || zeroPageMode == ZeroPageMode.XIAO_QI_STANDARD) {
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            if (!(fragmentFindFragmentByTag instanceof ZeroNormalFragment)) {
                fragmentFindFragmentByTag = ZeroNormalFragment.newInstance(this.mViewModel.getItemInfo().getThemeId(), this.mViewModel.getCurrentState());
                fragmentTransactionBeginTransaction.add(R.id.fl_model, fragmentFindFragmentByTag, TAG_ZERO_NORMAL);
            }
            fragmentTransactionBeginTransaction.show(fragmentFindFragmentByTag);
            if (fragmentFindFragmentByTag2 != null) {
                fragmentTransactionBeginTransaction.hide(fragmentFindFragmentByTag2);
            }
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            Log.d(TAG, "showFragment: 展示标志模式Fragment");
            return;
        }
        if (zeroPageMode == ZeroPageMode.MING_XIANG_PRO || zeroPageMode == ZeroPageMode.XIAO_QI_PRO) {
            FragmentTransaction fragmentTransactionBeginTransaction2 = getSupportFragmentManager().beginTransaction();
            if (!(fragmentFindFragmentByTag2 instanceof ZeroProFragment)) {
                fragmentFindFragmentByTag2 = ZeroProFragment.newInstance(this.mViewModel.getCurrentState(), this.mViewModel.getItemInfo().getThemeId(), this.mViewModel.getZeroThemeInfoEntity(), this.mViewModel.getItemInfo().getBgImg(), this.mViewModel.getItemInfo().getName(), ZeroPageMode.MING_XIANG_PRO == this.mViewModel.getInitState() || ZeroPageMode.XIAO_QI_PRO == this.mViewModel.getInitState());
                fragmentTransactionBeginTransaction2.add(R.id.fl_model, fragmentFindFragmentByTag2, TAG_ZERO_PRO);
            }
            fragmentTransactionBeginTransaction2.show(fragmentFindFragmentByTag2);
            if (fragmentFindFragmentByTag != null) {
                fragmentTransactionBeginTransaction2.hide(fragmentFindFragmentByTag);
            }
            Log.d(TAG, "showFragment: 展示个性化模式Fragment");
            fragmentTransactionBeginTransaction2.commitAllowingStateLoss();
            return;
        }
        throw new RuntimeException("未知的体验模式=" + zeroPageMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setControlState(boolean z, boolean z2) {
        Log.d(TAG, "setControlState: isTaste = " + z + ", isImmersion = " + z2);
        this.mViewModel.setImmersionState(z2);
        this.aTaste = z;
        BaseZeroFragment<?, ?> baseZeroFragmentFindZeroFragment = findZeroFragment(TAG_ZERO_PRO);
        if (baseZeroFragmentFindZeroFragment != null) {
            baseZeroFragmentFindZeroFragment.setControlState(z, this.mViewModel.isImmersionState());
        }
        BaseZeroFragment<?, ?> baseZeroFragmentFindZeroFragment2 = findZeroFragment(TAG_ZERO_NORMAL);
        if (baseZeroFragmentFindZeroFragment2 != null) {
            baseZeroFragmentFindZeroFragment2.setControlState(z, this.mViewModel.isImmersionState());
        }
        this.mViewBinding.controlView.setImmerseState(this.mViewModel.isImmersionState());
    }

    public void setImmersionState() {
        setControlState(this.mViewBinding.controlView.isRelaxRunning(), !(getSupportFragmentManager().findFragmentByTag(TAG_ZERO_PRO) instanceof ZeroProFragment ? ((ZeroProFragment) r0).isControlBall() : false));
    }

    public boolean isTaste() {
        return this.aTaste;
    }

    public boolean isImmersion() {
        return this.mViewModel.isImmersionState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BaseZeroFragment<?, ?> findZeroFragment(String str) {
        Fragment fragmentFindFragmentByTag = getSupportFragmentManager().findFragmentByTag(str);
        if (fragmentFindFragmentByTag != null && (fragmentFindFragmentByTag instanceof BaseZeroFragment)) {
            return (BaseZeroFragment) fragmentFindFragmentByTag;
        }
        return null;
    }

    public void setBallVolume(VolumeModifyEntity volumeModifyEntity) {
        BaseZeroFragment<?, ?> baseZeroFragmentFindZeroFragment = findZeroFragment(TAG_ZERO_PRO);
        if (baseZeroFragmentFindZeroFragment instanceof ZeroProFragment) {
            ((ZeroProFragment) baseZeroFragmentFindZeroFragment).setBallVolume(volumeModifyEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeProFragment() {
        BaseZeroFragment<?, ?> baseZeroFragmentFindZeroFragment = findZeroFragment(TAG_ZERO_PRO);
        if (baseZeroFragmentFindZeroFragment != null) {
            ((ZeroProFragment) baseZeroFragmentFindZeroFragment).resetViewModelDta();
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.remove(baseZeroFragmentFindZeroFragment);
            fragmentTransactionBeginTransaction.commitNow();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if ((this.mViewModel.getInitState() != ZeroPageMode.XIAO_QI_PRO && this.mViewModel.getCurrentState() == ZeroPageMode.XIAO_QI_PRO) || (this.mViewModel.getInitState() != ZeroPageMode.MING_XIANG_PRO && this.mViewModel.getCurrentState() == ZeroPageMode.MING_XIANG_PRO)) {
            this.mViewBinding.controlView.setZeroPageMode(this.mViewModel.getInitState());
            showFragment(this.mViewModel.getInitState());
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.wanos.media.entity.IZeroBaseAction
    public void setSelectInfo(ZeroThemeInfo zeroThemeInfo) {
        this.mViewModel.setZeroThemeInfoEntity(null);
        this.mViewModel.setItemInfo(zeroThemeInfo.getThemeId(), zeroThemeInfo.getThemeBgImage(), zeroThemeInfo.getThemeName(), zeroThemeInfo.isVip());
        this.mViewBinding.controlView.setTitleText(zeroThemeInfo.getThemeName(), zeroThemeInfo.isVip());
        removeProFragment();
        this.mViewBinding.controlView.setRelaxInfoActionButtonState(RelaxInfoActionButton.State.LOADING);
    }

    @Override // com.wanos.media.entity.IZeroBaseAction
    public void setAudioData(final AudioSceneInfoEntity audioSceneInfoEntity) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        ZeroLogcatTools.d(TAG, "setAudioData: entity = " + audioSceneInfoEntity.getAudioId());
        ZeroAudioBallTools.getInstance().initScene(audioSceneInfoEntity.getDetailInfo(), new ZeroAudioBallTools.ILoadingCallback() { // from class: com.wanos.media.ui.info.ZeroInfoActivity$$ExternalSyntheticLambda5
            @Override // com.wanos.media.util.ZeroAudioBallTools.ILoadingCallback
            public final void onAudioReady() {
                this.f$0.m497lambda$setAudioData$5$comwanosmediauiinfoZeroInfoActivity(audioSceneInfoEntity);
            }
        });
        StatisticPollUtil.getInstance().startPoll(audioSceneInfoEntity.getAudioId() + "");
    }

    /* JADX INFO: renamed from: lambda$setAudioData$5$com-wanos-media-ui-info-ZeroInfoActivity, reason: not valid java name */
    /* synthetic */ void m497lambda$setAudioData$5$comwanosmediauiinfoZeroInfoActivity(AudioSceneInfoEntity audioSceneInfoEntity) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        this.mViewModel.setZeroThemeInfoEntity(audioSceneInfoEntity);
        BaseZeroFragment<?, ?> baseZeroFragmentFindZeroFragment = findZeroFragment(TAG_ZERO_PRO);
        if (baseZeroFragmentFindZeroFragment instanceof ZeroProFragment) {
            ((ZeroProFragment) baseZeroFragmentFindZeroFragment).setSceneInfo(audioSceneInfoEntity);
        }
        setRelaxState(true);
        if (this.mViewModel.getItemInfo().isVip()) {
            if (UserInfoUtil.isLogin() && UserInfoUtil.isVipAndUnexpired()) {
                this.mViewBinding.controlView.setRelaxInfoActionButtonState(RelaxInfoActionButton.State.NORMAL);
                return;
            } else {
                this.mViewBinding.controlView.setRelaxInfoActionButtonState(RelaxInfoActionButton.State.TIMING);
                return;
            }
        }
        this.mViewBinding.controlView.setRelaxInfoActionButtonState(RelaxInfoActionButton.State.NORMAL);
    }

    public void setRelaxState(boolean z) {
        this.mViewModel.setRelaxState(z);
    }

    public RelaxInfoActionButton.State getRelaxButtonState() {
        return this.mViewBinding.controlView.getRelaxInfoActionButtonState();
    }

    @Override // com.wanos.media.entity.IZeroBaseAction
    public void setDurationInfo(String[] strArr) {
        this.mViewBinding.controlView.setMingXiangPickerData(strArr);
    }

    @Override // com.wanos.media.entity.IZeroBaseAction
    public void onThemeLoadError(String str) {
        ToastUtil.showMsg(str);
        this.mViewBinding.controlView.setRelaxInfoActionButtonState(RelaxInfoActionButton.State.ERROR);
    }

    @Override // com.wanos.media.view.AlarmDialog.IDialogDismissCallback
    public void onAlarmDialogDismiss() {
        this.mViewModel.setAlarmState(false);
    }

    @Override // com.wanos.media.entity.IMenuLibCallback
    public List<Long> getSceneAudioId() {
        return ZeroAudioBallTools.getInstance().getSceneSoundIds();
    }

    @Override // com.wanos.media.entity.IMenuLibCallback
    public boolean addAudioSound(ThemeAudioInfoEntity themeAudioInfoEntity) {
        ZeroAudioBallTools.getInstance().insertBall(themeAudioInfoEntity);
        return true;
    }

    @Override // com.wanos.media.entity.IMenuLibCallback
    public boolean deleteAudioSound(long j) {
        ZeroAudioBallTools.getInstance().deleteBall(j);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onExitSceneEvent(EventExitScene eventExitScene) {
        if (isDestroyed() || isFinishing()) {
            return;
        }
        Fragment fragmentFindFragmentByTag = getSupportFragmentManager().findFragmentByTag(SettingDialog.TAG);
        if (fragmentFindFragmentByTag instanceof SettingDialog) {
            ((SettingDialog) fragmentFindFragmentByTag).dismiss();
        }
        Fragment fragmentFindFragmentByTag2 = getSupportFragmentManager().findFragmentByTag(AlarmDialog.TAG);
        if (fragmentFindFragmentByTag2 instanceof AlarmDialog) {
            ((AlarmDialog) fragmentFindFragmentByTag2).dismiss();
        }
        if (this.mViewModel.getCurrentState() == ZeroPageMode.MING_XIANG_PRO || this.mViewModel.getCurrentState() == ZeroPageMode.XIAO_QI_PRO) {
            this.mViewBinding.controlView.setZeroPageMode(this.mViewModel.getInitState());
            showFragment(this.mViewModel.getInitState());
        }
        onBackPressed();
    }

    @Override // com.wanos.commonlibrary.utils.StatisticPollUtil.ReportedPollListener
    public void reported(String str) {
        if (this.mViewModel.getInitState() == ZeroPageMode.XIAO_QI_STANDARD) {
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_NAP_PLAYING_30S_RATE_REPORT, str, "", "", "", 0);
        } else if (this.mViewModel.getInitState() == ZeroPageMode.MING_XIANG_STANDARD) {
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_MEDITE_PLAYING_30S_RATE_REPORT, str, "", "", "", 0);
        }
        StatisticPollUtil.getInstance().startPoll(str);
    }

    @Override // com.wanos.media.view.SettingDialog.IDismissListener
    public void onDismiss() {
        ZeroLogcatTools.i(TAG, "SettingClick --> onDismiss: ");
        ThreadUtils.getMainHandler().postDelayed(this.mSettingRunnable, 400L);
    }

    public void showSystemBars() {
        WindowInsetsController windowInsetsController;
        if (Build.VERSION.SDK_INT >= 30 && (windowInsetsController = getWindow().getDecorView().getWindowInsetsController()) != null) {
            windowInsetsController.show(WindowInsets.Type.systemBars());
            windowInsetsController.setSystemBarsBehavior(2);
        }
        getWindow().setStatusBarColor(0);
        getWindow().setNavigationBarColor(0);
        getWindow().getDecorView().setSystemUiVisibility(5888);
    }

    public void hideSystemBars() {
        WindowInsetsController windowInsetsController;
        if (Build.VERSION.SDK_INT < 30 || (windowInsetsController = getWindow().getDecorView().getWindowInsetsController()) == null) {
            return;
        }
        windowInsetsController.hide(WindowInsets.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(2);
    }

    /* JADX INFO: renamed from: com.wanos.media.ui.info.ZeroInfoActivity$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$media$base$IDialogStateCallback$DialogState;

        static {
            int[] iArr = new int[IDialogStateCallback.DialogState.values().length];
            $SwitchMap$com$wanos$media$base$IDialogStateCallback$DialogState = iArr;
            try {
                iArr[IDialogStateCallback.DialogState.DISMISS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$media$base$IDialogStateCallback$DialogState[IDialogStateCallback.DialogState.DISPLAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.wanos.media.base.IDialogStateCallback
    public void onDialogStateChanged(IDialogStateCallback.DialogState dialogState) {
        int i = AnonymousClass5.$SwitchMap$com$wanos$media$base$IDialogStateCallback$DialogState[dialogState.ordinal()];
        if (i == 1) {
            hideSystemBars();
        } else {
            if (i != 2) {
                return;
            }
            showSystemBars();
        }
    }
}
