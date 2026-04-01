package com.wanos.media.ui.actvity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.wanos.WanosCommunication.bean.AdDialogInfoBean;
import com.wanos.WanosCommunication.response.GetAdDialogInfoResponse;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.ResourceNotExistEvent;
import com.wanos.commonlibrary.event.ShowLoginEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.router.PageRouter;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideOptions;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.ReStartUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.BaseAdDialog;
import com.wanos.media.base.CarConstants;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.ActivityMainBinding;
import com.wanos.media.juyihall.Recommend2Fragment;
import com.wanos.media.presenter.MainPresenter;
import com.wanos.media.ui.actvity.TableAdapter;
import com.wanos.media.ui.advertise.dialog.AdvertiseDialog;
import com.wanos.media.ui.audiobook.AudioBookHomeFragment;
import com.wanos.media.ui.login.activity.UserInfoAndOrderActivity;
import com.wanos.media.ui.music.MusicHomeFragment;
import com.wanos.media.ui.search.SearchActivity;
import com.wanos.media.ui.video.VideoHomeFragment;
import com.wanos.media.ui.widget.banner.util.BannerUtils;
import com.wanos.media.view.MainView;
import com.wanos.media.view.RelaxHomeFragment;
import com.wanos.mediacenter.MediaCenterManager;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanosj.musicsourceview.MusicSourceContentProvider;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import retrofit2.Call;

/* JADX INFO: loaded from: classes3.dex */
public class MainActivity extends WanosBaseActivity<MainPresenter> implements MainView, DialogInterface.OnDismissListener {
    public static final String FRAGMENT_TYPE_WANOS_AUDIO_BOOK = "AUDIOBOOK";
    public static final String FRAGMENT_TYPE_WANOS_MUSIC = "WANOSMUSIC";
    public static final String FRAGMENT_TYPE_WANOS_VIDEO = "WANOSVIDEO";
    private int activityId;
    private int afterAction;
    private ActivityMainBinding binding;
    private Call<GetAdDialogInfoResponse> call;
    private Fragment creatorFragment;
    private Fragment currentFragment;
    private AdvertiseDialog dialog;
    private String image;
    private InputMethodManager imm;
    private ReStartUtil instance;
    private int isAuto;
    private String jumpUrl;
    private TableAdapter mTableAdapter;
    private MediaPlayerService.LocalBinder musicControl;
    private Fragment recommend2Fragment;
    private AudioBookHomeFragment wanOSAudioBookFragment;
    private MusicHomeFragment wanOSMusicFragment;
    private VideoHomeFragment wanOSVideoFragment;
    private RelaxHomeFragment wanosZeroFragment;
    private final List<BaseAdDialog> adDialogList = new ArrayList();
    View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.wanos.media.ui.actvity.MainActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id != R.id.iv_right_top_right) {
                if (id != R.id.ll_wanos_serach) {
                    return;
                }
                MainActivity.this.startActivity(new Intent(MainActivity.this.getBaseContext(), (Class<?>) SearchActivity.class));
                return;
            }
            if (!UserInfoUtil.isLogin()) {
                MainActivity.this.showLoginDialog();
            } else {
                UserInfoAndOrderActivity.startAct(MainActivity.this);
            }
        }
    };

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("image", this.image);
        outState.putString("jumpUrl", this.jumpUrl);
        outState.putInt("activityId", this.activityId);
        outState.putInt("afterAction", this.afterAction);
        outState.putInt("isAuto", this.isAuto);
        TableAdapter tableAdapter = this.mTableAdapter;
        outState.putInt("tabSelected", tableAdapter == null ? 1 : tableAdapter.getSelectId());
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(WanosBaseActivity.TAG, "MainActivity----onCreate()----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(new Date(System.currentTimeMillis())));
        this.mPresenter = new MainPresenter(this);
        ActivityMainBinding activityMainBindingInflate = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding = activityMainBindingInflate;
        setContentView(activityMainBindingInflate.getRoot());
        this.instance = ReStartUtil.getInstance();
        if (savedInstanceState == null) {
            this.call = ((MainPresenter) this.mPresenter).getAdDialogInfo(6, this.instance.isJuyihallRestart());
        }
        int[] iArr = CarConstants.mTableState[CarConstants.buildIndex];
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            arrayList.add(new TableEntity(i));
        }
        this.mTableAdapter = new TableAdapter(arrayList, this);
        this.binding.llMainLeft.setLayoutManager(new LinearLayoutManager(this));
        this.binding.llMainLeft.setAdapter(this.mTableAdapter);
        this.mTableAdapter.setOnItemClickListener(new TableAdapter.OnItemClickListener() { // from class: com.wanos.media.ui.actvity.MainActivity$$ExternalSyntheticLambda0
            @Override // com.wanos.media.ui.actvity.TableAdapter.OnItemClickListener
            public final void onItemClick(TableEntity tableEntity) {
                this.f$0.m482lambda$onCreate$0$comwanosmediauiactvityMainActivity(tableEntity);
            }
        });
        if (savedInstanceState != null) {
            Log.i(WanosBaseActivity.TAG, "setSelectTableId: onCrate = 内存");
            TableAdapter tableAdapter = this.mTableAdapter;
            tableAdapter.setSelectTableId(savedInstanceState.getInt("tabSelected", tableAdapter.getSelectId()));
        } else {
            Log.i(WanosBaseActivity.TAG, "setSelectTableId: onCrate = 默认");
            TableAdapter tableAdapter2 = this.mTableAdapter;
            tableAdapter2.setSelectTableId(tableAdapter2.getSelectId());
        }
        iniView();
        initMusicSourceLayout();
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_OPEN_WANOS_APP, "" + UserInfoUtil.getUserInfo().getUserId(), "", "", "", 0);
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-wanos-media-ui-actvity-MainActivity, reason: not valid java name */
    /* synthetic */ void m482lambda$onCreate$0$comwanosmediauiactvityMainActivity(TableEntity tableEntity) {
        int id = tableEntity.getId();
        if (id == 0) {
            if (CarConstants.aiMusic[CarConstants.buildIndex]) {
                showAiMusicMv();
            }
            isCancelRequest(this.call);
            this.call = ((MainPresenter) this.mPresenter).getAdDialogInfo(6, this.instance.isJuyihallRestart());
            initJuYiHallAction();
            return;
        }
        if (id == 1) {
            if (CarConstants.aiMusic[CarConstants.buildIndex]) {
                showAiMusicMv();
            }
            isCancelRequest(this.call);
            this.call = ((MainPresenter) this.mPresenter).getAdDialogInfo(2, this.instance.isMusicRestart());
            initMusicAction();
            return;
        }
        if (id == 2) {
            if (CarConstants.aiMusic[CarConstants.buildIndex]) {
                showAiMusicMv();
            }
            isCancelRequest(this.call);
            this.call = ((MainPresenter) this.mPresenter).getAdDialogInfo(3, this.instance.isBookRestart());
            initAudioBookAction();
            return;
        }
        if (id == 3) {
            initVideoAction();
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_ENTER_THEATER, "", "", "", "", 0);
        } else if (id == 4) {
            isCancelRequest(this.call);
            initZeroAction();
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_NAVBAR_COMFORT_SPACE_BOTTON, "", "", "", "", 0);
        } else {
            if (id != 5) {
                return;
            }
            isCancelRequest(this.call);
            initCreatorAction();
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i(WanosBaseActivity.TAG, "MainActivity----onResume()----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(new Date(System.currentTimeMillis())));
    }

    @Override // com.wanos.media.base.WanosBaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.image = savedInstanceState.getString("image");
        this.jumpUrl = savedInstanceState.getString("jumpUrl");
        this.activityId = savedInstanceState.getInt("activityId", 0);
        this.afterAction = savedInstanceState.getInt("afterAction", 0);
        this.isAuto = savedInstanceState.getInt("isAuto", 0);
        String str = this.image;
        if (str == null || TextUtils.isEmpty(str) || this.dialog != null) {
            return;
        }
        savedInstanceState.clear();
        AdDialogInfoBean adDialogInfoBean = new AdDialogInfoBean();
        adDialogInfoBean.setImage(this.image);
        adDialogInfoBean.setJumpUrl(this.jumpUrl);
        adDialogInfoBean.setActivityId(this.activityId);
        adDialogInfoBean.setAfterAction(this.afterAction);
        adDialogInfoBean.setIsAuto(this.isAuto);
        AdvertiseDialog advertiseDialogShowAdDialog = showAdDialog(adDialogInfoBean);
        this.dialog = advertiseDialogShowAdDialog;
        advertiseDialogShowAdDialog.showDialog();
    }

    private void initMusicSourceLayout() {
        try {
            View musicSource = MusicSourceContentProvider.getMusicSource(this, CarConstants.buildIndex);
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(-2, -2);
            layoutParams.leftToLeft = R.id.ll_main_left;
            if (!MediaCenterManager.is245) {
                layoutParams.rightToRight = R.id.ll_main_left;
            }
            layoutParams.topToTop = 0;
            musicSource.setLayoutParams(layoutParams);
            ((ConstraintLayout) findViewById(R.id.parent_c)).addView(musicSource, layoutParams);
            View iconView = MusicSourceContentProvider.getIconView(musicSource);
            View container = MusicSourceContentProvider.getContainer(musicSource);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) iconView.getLayoutParams();
            layoutParams2.height = BannerUtils.dp2px(80.0f);
            layoutParams2.width = -2;
            iconView.setLayoutParams(layoutParams2);
            if (CommonUtils.isIs371()) {
                container.setPadding(BannerUtils.dp2px(42.0f), 0, 0, 0);
            } else if (MediaCenterManager.is245) {
                container.setPadding(BannerUtils.dp2px(96.0f), 0, 0, 0);
            } else {
                container.setPadding(BannerUtils.dp2px(58.0f), 0, 0, 0);
            }
            if (CommonUtils.isChannelNot245() && container != null) {
                container.setContentDescription(getResources().getString(R.string.switch_music_source));
            }
            View arrow = MusicSourceContentProvider.getArrow(musicSource);
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) arrow.getLayoutParams();
            if (CommonUtils.isIs371()) {
                layoutParams3.width = BannerUtils.dp2px(20.0f);
                layoutParams3.height = BannerUtils.dp2px(20.0f);
            }
            if (!MediaCenterManager.is245) {
                layoutParams3.leftMargin = 0;
            }
            arrow.setLayoutParams(layoutParams3);
        } catch (Exception unused) {
        }
    }

    private void iniView() {
        this.imm = (InputMethodManager) getSystemService("input_method");
        ((MainPresenter) this.mPresenter).getUserInfo();
        setTitleBarVisibility(8);
        initTextWeight();
        updateHeader();
        initTabView(getIntent());
        initListener();
        initVisibleIsSpoken();
        showAiMusicMv();
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.ivRightTopRight.setContentDescription(getResources().getString(R.string.open_login_dialog));
            this.binding.llWanosSerach.setContentDescription(getResources().getString(R.string.open_search));
        }
    }

    private void initTabView(Intent intent) {
        Uri data;
        int i;
        if (intent == null || (data = intent.getData()) == null || this.mTableAdapter == null) {
            return;
        }
        String queryParameter = data.getQueryParameter(AppConstants.ACTIVITY_TAB_PAGE);
        Log.i(WanosBaseActivity.TAG, "setSelectTableId: Intent = " + queryParameter);
        try {
            i = Integer.parseInt(queryParameter);
        } catch (Exception unused) {
            i = 0;
        }
        if (i < this.mTableAdapter.getTableData().size()) {
            TableAdapter tableAdapter = this.mTableAdapter;
            tableAdapter.setSelectTableId(tableAdapter.getTableData().get(i).getId());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initTabView(intent);
    }

    private void initTextWeight() {
        Util.setTextWeight(this.binding.tvRightTopLeft, 500);
    }

    public AdvertiseDialog showAdDialog(final AdDialogInfoBean bean) {
        AdvertiseDialog advertiseDialogNewInstance = AdvertiseDialog.newInstance();
        advertiseDialogNewInstance.setFm(getSupportFragmentManager());
        if (bean != null && !TextUtils.isEmpty(bean.getImage())) {
            advertiseDialogNewInstance.setImageUrl(bean.getImage());
        }
        advertiseDialogNewInstance.setOnLeftClickListener(new AdvertiseDialog.OnLeftClickListener() { // from class: com.wanos.media.ui.actvity.MainActivity.1
            @Override // com.wanos.media.ui.advertise.dialog.AdvertiseDialog.OnLeftClickListener
            public void onConfirm(Context context) {
                MainActivity.this.image = null;
                AdDialogInfoBean adDialogInfoBean = bean;
                if (adDialogInfoBean == null || TextUtils.isEmpty(adDialogInfoBean.getJumpUrl())) {
                    MainActivity.this.adConfirmClick(bean);
                } else {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.jumpUrlInApp(mainActivity, bean.getJumpUrl());
                }
            }

            @Override // com.wanos.media.ui.advertise.dialog.AdvertiseDialog.OnLeftClickListener
            public void onCancel() {
                MainActivity.this.image = null;
            }
        });
        return advertiseDialogNewInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adConfirmClick(AdDialogInfoBean bean) {
        if (!UserInfoUtil.isLogin()) {
            if (bean.getIsAuto() == 2) {
                showLoginDialog(bean.getActivityId(), "");
                return;
            } else {
                showLoginDialog();
                return;
            }
        }
        if (bean.getIsAuto() == 2) {
            ((MainPresenter) this.mPresenter).giveVipMember(bean.getActivityId());
        }
    }

    private void updateHeader() {
        if (UserInfoUtil.isLogin() && UserInfoUtil.getUserInfo() != null && !TextUtils.isEmpty(UserInfoUtil.getUserInfo().getAvatar())) {
            GlideUtil.setImageData(UserInfoUtil.getUserInfo().getAvatar(), this.binding.ivRightTopRight, 80, 80, R.drawable.p_user_ava_d);
        } else {
            Glide.with(this.binding.ivRightTopRight).clear(this.binding.ivRightTopRight);
            this.binding.ivRightTopRight.setImageResource(R.drawable.p_user_ava_d);
        }
    }

    private void initMusicAction() {
        setTopText(getString(R.string.wanos_music));
        if (this.wanOSMusicFragment == null) {
            this.wanOSMusicFragment = new MusicHomeFragment();
        }
        this.binding.llWanosSerach.setVisibility(0);
        showFragment(this.wanOSMusicFragment);
    }

    private void initAudioBookAction() {
        setTopText(getString(R.string.wanos_audio_book));
        if (this.wanOSAudioBookFragment == null) {
            this.wanOSAudioBookFragment = new AudioBookHomeFragment();
        }
        this.binding.llWanosSerach.setVisibility(0);
        showFragment(this.wanOSAudioBookFragment);
    }

    private void initZeroAction() {
        if (CarConstants.aiMusic[CarConstants.buildIndex]) {
            hideAiMusicMv();
        }
        setTopText(StringUtils.getString(R.string.zero));
        this.binding.llWanosSerach.setVisibility(8);
        if (this.wanosZeroFragment == null) {
            this.wanosZeroFragment = new RelaxHomeFragment();
        }
        showFragment(this.wanosZeroFragment);
    }

    private void initJuYiHallAction() {
        setTopText(StringUtils.getString(R.string.ju_yi_hall));
        this.binding.llWanosSerach.setVisibility(0);
        if (this.recommend2Fragment == null) {
            this.recommend2Fragment = new Recommend2Fragment();
        }
        showFragment(this.recommend2Fragment);
    }

    private void initVideoAction() {
        if (CarConstants.aiMusic[CarConstants.buildIndex]) {
            hideAiMusicMv();
        }
        setTopText(getString(R.string.wanos_video));
        if (this.wanOSVideoFragment == null) {
            this.wanOSVideoFragment = new VideoHomeFragment();
        }
        this.binding.llWanosSerach.setVisibility(8);
        showFragment(this.wanOSVideoFragment);
    }

    private void initCreatorAction() {
        if (CarConstants.aiMusic[CarConstants.buildIndex]) {
            hideAiMusicMv();
        }
        setTopText(StringUtils.getString(R.string.wanos_creator));
        this.binding.llWanosSerach.setVisibility(8);
        if (this.creatorFragment == null) {
            this.creatorFragment = (Fragment) ARouter.getInstance().build(PageRouter.CREATOR_HOME_FRAGMENT).navigation();
        }
        showFragment(this.creatorFragment);
    }

    private void initListener() {
        this.binding.llWanosSerach.setOnClickListener(this.onClickListener);
        this.binding.ivRightTopRight.setOnClickListener(this.onClickListener);
    }

    private void showFragment(Fragment fragment) {
        this.currentFragment = fragment;
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.layout_right_bottom, fragment);
        fragmentTransactionBeginTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
        fragmentTransactionBeginTransaction.commit();
    }

    private void setTopText(String title) {
        this.binding.tvRightTopLeft.setText(title);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && getSupportFragmentManager().getBackStackEntryCount() == 0) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        Fragment fragment = this.currentFragment;
        if (fragment == null || !fragment.isResumed()) {
            return;
        }
        Fragment fragment2 = this.currentFragment;
        if (fragment2 instanceof WanosBaseFragment) {
            ((WanosBaseFragment) fragment2).onMediaInfoCallbackAppNext(type, mediaInfo);
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        Fragment fragment = this.currentFragment;
        if (fragment == null || !fragment.isResumed()) {
            return;
        }
        Fragment fragment2 = this.currentFragment;
        if (fragment2 instanceof WanosBaseFragment) {
            ((WanosBaseFragment) fragment2).onStatusonStatusCallbackNext(status, args);
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent event) {
        updateHeader();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userInfoChangeEvent(UserInfoChangeEvent event) {
        updateHeader();
    }

    public void showLogin(ShowLoginEvent event) {
        this.isResume = true;
        showLoginDialog();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout1(ResourceNotExistEvent event) {
        ToastUtil.showMsg("资源不存在,播放停止");
    }

    private void isCancelRequest(Call<GetAdDialogInfoResponse> call) {
        this.adDialogList.clear();
        if (call == null || call.isCanceled()) {
            return;
        }
        call.cancel();
    }

    @Override // com.wanos.media.view.MainView
    public void getAdDialogSuccess(final AdDialogInfoBean bean, final int type) {
        if (bean.getActivityId() != 0) {
            GlideUtil.setPreloadData(this, bean.getImage(), SizeMode.ADVERTISEMENT, new GlideOptions.OnLoadListener() { // from class: com.wanos.media.ui.actvity.MainActivity.3
                @Override // com.wanos.commonlibrary.utils.GlideOptions.OnLoadListener
                public void onLoadSuccess(Bitmap bitmap) {
                    if (MainActivity.this.isFinishing() || MainActivity.this.isDestroyed()) {
                        return;
                    }
                    MainActivity.this.adDialogList.clear();
                    if (MainActivity.this.instance != null && MainActivity.this.instance.isMusicRestart() && type == 2) {
                        MainActivity.this.instance.setMusicRestart(false);
                    }
                    if (MainActivity.this.instance != null && MainActivity.this.instance.isBookRestart() && type == 3) {
                        MainActivity.this.instance.setBookRestart(false);
                    }
                    if (MainActivity.this.instance != null && MainActivity.this.instance.isJuyihallRestart() && type == 6) {
                        MainActivity.this.instance.setJuyihallRestart(false);
                    }
                    if (MainActivity.this.dialog != null && MainActivity.this.dialog.isShowing()) {
                        MainActivity.this.dialog.dismissAllowingStateLoss();
                    }
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.dialog = mainActivity.showAdDialog(bean);
                    MainActivity.this.adDialogList.add(MainActivity.this.dialog);
                    ((BaseAdDialog) MainActivity.this.adDialogList.get(0)).showDialog();
                    MainActivity.this.dialog.showDialogList(MainActivity.this.adDialogList);
                    MainActivity.this.dialog.setOnDismissListener(MainActivity.this);
                    MainActivity.this.image = bean.getImage();
                    MainActivity.this.jumpUrl = bean.getJumpUrl();
                    MainActivity.this.activityId = bean.getActivityId();
                    MainActivity.this.afterAction = bean.getAfterAction();
                    MainActivity.this.isAuto = bean.getIsAuto();
                    MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_POP_WINDOW, bean.getActivityId() + "", "", "", "", 0);
                }
            });
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        return event.getPointerCount() == 2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        this.adDialogList.remove(this.dialog);
        this.dialog = null;
    }
}
