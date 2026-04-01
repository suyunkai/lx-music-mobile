package com.wanos.media.ui.music.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.ActivityMyMusicBinding;
import com.wanos.media.presenter.MainPresenter;
import com.wanos.media.ui.music.MyMusicGroupListFragment;
import com.wanos.media.ui.music.MyMusicHistoryListFragment;
import com.wanos.media.ui.music.MyMusicListFragment;
import com.wanos.media.view.MainView;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class MyMusicActivity extends WanosBaseActivity<MainPresenter> implements MainView {
    public static final String FRAGMENT_TYPE_MY_MUSIC_GROUP = "MUSIC_GROUP";
    public static final String FRAGMENT_TYPE_MY_MUSIC_HISTORY = "MUSIC_HISTORY";
    public static final String FRAGMENT_TYPE_MY_MUSIC_SINGLE = "MUSIC_SINGLE";
    private ActivityMyMusicBinding binding;
    private Fragment currentFragment;
    private FragmentManager manager;
    private MyMusicGroupListFragment myMusicGroupListFragment;
    private MyMusicHistoryListFragment myMusicHistoryListFragment;
    private MyMusicListFragment myMusicListFragment;
    View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.wanos.media.ui.music.activity.MyMusicActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tab_my_music /* 2131362983 */:
                    if (UserInfoUtil.isLogin()) {
                        MyMusicActivity.this.initMusicSingleAction();
                    } else {
                        MyMusicActivity.this.showLoginDialog();
                    }
                    break;
                case R.id.tab_my_music_group /* 2131362984 */:
                    if (UserInfoUtil.isLogin()) {
                        MyMusicActivity.this.initMusicGroupAction();
                    } else {
                        MyMusicActivity.this.showLoginDialog();
                    }
                    break;
                case R.id.tab_my_music_history /* 2131362985 */:
                    MyMusicActivity.this.initMusicHistoryAction();
                    break;
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

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPresenter = new MainPresenter(this);
        ActivityMyMusicBinding activityMyMusicBindingInflate = ActivityMyMusicBinding.inflate(getLayoutInflater());
        this.binding = activityMyMusicBindingInflate;
        setContentView(activityMyMusicBindingInflate.getRoot());
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.manager = supportFragmentManager;
        if (savedInstanceState != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
            if (this.manager.findFragmentByTag(AppConstants.COLLECT_MUSIC_TAG) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(this.manager.findFragmentByTag(AppConstants.COLLECT_MUSIC_TAG)));
            }
            if (this.manager.findFragmentByTag(AppConstants.COLLECT_MUSIC_GROUP_TAG) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(this.manager.findFragmentByTag(AppConstants.COLLECT_MUSIC_GROUP_TAG)));
            }
            if (this.manager.findFragmentByTag(AppConstants.HISTORY_PLAY_TAG) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(this.manager.findFragmentByTag(AppConstants.HISTORY_PLAY_TAG)));
            }
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        }
        iniView();
        initVisibleIsSpoken();
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.tabMyMusic.setContentDescription(getResources().getString(R.string.switch_collect_music));
            this.binding.tabMyMusicGroup.setContentDescription(getResources().getString(R.string.switch_collect_music_group));
            this.binding.tabMyMusicHistory.setContentDescription(getResources().getString(R.string.switch_music_history));
        }
    }

    private void iniView() {
        setTitleText(R.string.my_music);
        Util.setTextWeight(this.binding.tabMyMusic, 500);
        Util.setTextWeight(this.binding.tabMyMusicGroup, 500);
        Util.setTextWeight(this.binding.tabMyMusicHistory, 500);
        initTabView();
        iniListenner();
    }

    private void initTabView() {
        Uri data = getIntent().getData();
        if (data != null) {
            String queryParameter = data.getQueryParameter(AppConstants.ACTIVITY_TAB_PAGE);
            if (queryParameter != null) {
                if (queryParameter.equals("0")) {
                    initMusicSingleAction();
                    return;
                } else if (queryParameter.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    initMusicGroupAction();
                    return;
                } else {
                    initMusicHistoryAction();
                    return;
                }
            }
            return;
        }
        if (UserInfoUtil.isLogin()) {
            initMusicSingleAction();
        } else {
            initMusicHistoryAction();
        }
    }

    private void initLeftMenu() {
        this.binding.tabMyMusic.setSelected(false);
        this.binding.tabMyMusicGroup.setSelected(false);
        this.binding.tabMyMusicHistory.setSelected(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMusicSingleAction() {
        initLeftMenu();
        this.binding.tabMyMusic.setSelected(true);
        Fragment fragment = this.myMusicListFragment;
        if (fragment == null) {
            MyMusicListFragment myMusicListFragment = new MyMusicListFragment();
            this.myMusicListFragment = myMusicListFragment;
            addFragment(myMusicListFragment, AppConstants.COLLECT_MUSIC_TAG);
            return;
        }
        showFragment(fragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMusicGroupAction() {
        initLeftMenu();
        this.binding.tabMyMusicGroup.setSelected(true);
        Fragment fragment = this.myMusicGroupListFragment;
        if (fragment == null) {
            MyMusicGroupListFragment myMusicGroupListFragment = new MyMusicGroupListFragment();
            this.myMusicGroupListFragment = myMusicGroupListFragment;
            addFragment(myMusicGroupListFragment, AppConstants.COLLECT_MUSIC_GROUP_TAG);
            return;
        }
        showFragment(fragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMusicHistoryAction() {
        initLeftMenu();
        this.binding.tabMyMusicHistory.setSelected(true);
        Fragment fragment = this.myMusicHistoryListFragment;
        if (fragment == null) {
            MyMusicHistoryListFragment myMusicHistoryListFragment = new MyMusicHistoryListFragment();
            this.myMusicHistoryListFragment = myMusicHistoryListFragment;
            addFragment(myMusicHistoryListFragment, AppConstants.HISTORY_PLAY_TAG);
            return;
        }
        showFragment(fragment);
    }

    private void iniListenner() {
        this.binding.tabMyMusic.setOnClickListener(this.onClickListener);
        this.binding.tabMyMusicHistory.setOnClickListener(this.onClickListener);
        this.binding.tabMyMusicGroup.setOnClickListener(this.onClickListener);
    }

    private void addFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransactionBeginTransaction = this.manager.beginTransaction();
        Fragment fragment2 = this.currentFragment;
        if (fragment2 != null) {
            fragmentTransactionBeginTransaction.hide(fragment2);
        }
        fragmentTransactionBeginTransaction.add(R.id.layout_right, fragment, tag);
        this.currentFragment = fragment;
        fragmentTransactionBeginTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
        fragmentTransactionBeginTransaction.commit();
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = this.manager.beginTransaction();
        fragmentTransactionBeginTransaction.hide(this.currentFragment);
        fragmentTransactionBeginTransaction.show(fragment);
        this.currentFragment = fragment;
        fragmentTransactionBeginTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
        fragmentTransactionBeginTransaction.commit();
    }

    public static void startMyMusicActivity(Context context) {
        Intent intent = new Intent(context, (Class<?>) MyMusicActivity.class);
        if (context instanceof Activity) {
            ((Activity) context).startActivity(intent);
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        MyMusicListFragment myMusicListFragment = this.myMusicListFragment;
        if (myMusicListFragment != null) {
            myMusicListFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
        }
        MyMusicGroupListFragment myMusicGroupListFragment = this.myMusicGroupListFragment;
        if (myMusicGroupListFragment != null && myMusicGroupListFragment.isResumed()) {
            this.myMusicGroupListFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
        }
        MyMusicHistoryListFragment myMusicHistoryListFragment = this.myMusicHistoryListFragment;
        if (myMusicHistoryListFragment != null) {
            myMusicHistoryListFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        MyMusicListFragment myMusicListFragment = this.myMusicListFragment;
        if (myMusicListFragment != null) {
            myMusicListFragment.onStatusonStatusCallbackNext(status, args);
        }
        MyMusicGroupListFragment myMusicGroupListFragment = this.myMusicGroupListFragment;
        if (myMusicGroupListFragment != null && myMusicGroupListFragment.isResumed()) {
            this.myMusicGroupListFragment.onStatusonStatusCallbackNext(status, args);
        }
        MyMusicHistoryListFragment myMusicHistoryListFragment = this.myMusicHistoryListFragment;
        if (myMusicHistoryListFragment != null) {
            myMusicHistoryListFragment.onStatusonStatusCallbackNext(status, args);
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        return event.getPointerCount() == 2;
    }
}
