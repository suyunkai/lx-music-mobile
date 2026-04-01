package com.wanos.media.ui.audiobook.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.ActivityAudioBookMineBinding;
import com.wanos.media.db.DbCallBack;
import com.wanos.media.db.WanosDbUtils;
import com.wanos.media.presenter.AudioBookMinePresenter;
import com.wanos.media.ui.audiobook.AudioBookFollowFragment;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.AudioBookLikeChapterFragment;
import com.wanos.media.ui.audiobook.AudioBookPlayedFragment;
import com.wanos.viewmodel.AudioBookMineActivityViewModel;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookMineActivity extends WanosBaseActivity<AudioBookMinePresenter> implements View.OnClickListener {
    private static final String KEY_AudioBookFollowFragment = "AudioBookFollowFragment";
    private static final String KEY_AudioBookLikeChapterFragment = "AudioBookLikeChapterFragment";
    private static final String KEY_AudioBookPlayedFragment = "AudioBookPlayedFragment";
    private AudioBookFollowFragment audioBookFollowFragment;
    private AudioBookLikeChapterFragment audioBookLikeChapterFragment;
    private AudioBookPlayedFragment audioBookPlayedFragment;
    private ActivityAudioBookMineBinding binding;
    private Fragment currentFragment;
    private AudioBookMineActivityViewModel viewModel;

    public static void build(Context context) {
        context.startActivity(new Intent(context, (Class<?>) AudioBookMineActivity.class));
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
            if (supportFragmentManager.findFragmentByTag(KEY_AudioBookLikeChapterFragment) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(supportFragmentManager.findFragmentByTag(KEY_AudioBookLikeChapterFragment)));
            }
            if (supportFragmentManager.findFragmentByTag(KEY_AudioBookFollowFragment) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(supportFragmentManager.findFragmentByTag(KEY_AudioBookFollowFragment)));
            }
            if (supportFragmentManager.findFragmentByTag(KEY_AudioBookPlayedFragment) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(supportFragmentManager.findFragmentByTag(KEY_AudioBookPlayedFragment)));
            }
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        }
        this.viewModel = (AudioBookMineActivityViewModel) new ViewModelProvider(this).get(AudioBookMineActivityViewModel.class);
        ActivityAudioBookMineBinding activityAudioBookMineBindingInflate = ActivityAudioBookMineBinding.inflate(getLayoutInflater());
        this.binding = activityAudioBookMineBindingInflate;
        setContentView(activityAudioBookMineBindingInflate.getRoot());
        initView();
        initVisibleIsSpoken();
        initListener();
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.tabLikeChapter.setContentDescription(getResources().getString(R.string.switch_collect_chapter));
            this.binding.tabFollow.setContentDescription(getResources().getString(R.string.switch_collect_album));
            this.binding.tabPlayed.setContentDescription(getResources().getString(R.string.switch_history_play));
        }
    }

    public void initView() {
        setTitleText(R.string.ab_mine);
        Util.setTextWeight(this.binding.tabFollow, 500);
        Util.setTextWeight(this.binding.tabLikeChapter, 500);
        Util.setTextWeight(this.binding.tabPlayed, 500);
        initTabView();
    }

    private void initTabView() {
        Uri data = getIntent().getData();
        if (data != null) {
            String queryParameter = data.getQueryParameter(AppConstants.ACTIVITY_TAB_PAGE);
            if (queryParameter != null) {
                if (queryParameter.equals("0")) {
                    selectTab(0);
                    return;
                } else if (queryParameter.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    selectTab(1);
                    return;
                } else {
                    selectTab(2);
                    return;
                }
            }
            return;
        }
        if (!UserInfoUtil.isLogin()) {
            selectTab(2);
        } else {
            selectTab(this.viewModel.getCurrentTabIndex());
        }
    }

    public void initListener() {
        this.binding.tabLikeChapter.setOnClickListener(this);
        this.binding.tabFollow.setOnClickListener(this);
        this.binding.tabPlayed.setOnClickListener(this);
    }

    public void selectTab(int index) {
        this.viewModel.setCurrentTabIndex(index);
        if (index == 0) {
            if (!UserInfoUtil.isLogin()) {
                showLoginDialog();
                return;
            } else {
                this.binding.tabLikeChapter.setSelected(true);
                showLikeChapterFragment();
            }
        } else {
            this.binding.tabLikeChapter.setSelected(false);
        }
        if (index == 1) {
            if (!UserInfoUtil.isLogin()) {
                showLoginDialog();
                return;
            } else {
                this.binding.tabFollow.setSelected(true);
                showFollowFragment();
            }
        } else {
            this.binding.tabFollow.setSelected(false);
        }
        if (index == 2) {
            this.binding.tabPlayed.setSelected(true);
            showPlayedFragment();
        } else {
            this.binding.tabPlayed.setSelected(false);
        }
    }

    public void testDb() {
        int i = 0;
        while (i < 40) {
            int i2 = i + 1;
            WanosDbUtils.updateAudioBookHistory(new AudioBookMineChapterItemBean(i, i2), new DbCallBack<Boolean>() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookMineActivity.1
                @Override // com.wanos.media.db.DbCallBack
                public void callBackData(Boolean data) {
                }
            });
            i = i2;
        }
    }

    public void testPrintDb() {
        WanosDbUtils.getAudioBookHistoryList(new DbCallBack<List<AudioBookMineChapterItemBean>>() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookMineActivity.2
            @Override // com.wanos.media.db.DbCallBack
            public void callBackData(List<AudioBookMineChapterItemBean> data) {
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tab_follow) {
            selectTab(1);
        } else if (id == R.id.tab_like_chapter) {
            selectTab(0);
        } else {
            if (id != R.id.tab_played) {
                return;
            }
            selectTab(2);
        }
    }

    public void showLikeChapterFragment() {
        if (this.audioBookLikeChapterFragment == null) {
            this.audioBookLikeChapterFragment = new AudioBookLikeChapterFragment();
        }
        showFragment(this.audioBookLikeChapterFragment, KEY_AudioBookLikeChapterFragment);
    }

    public void showFollowFragment() {
        if (this.audioBookFollowFragment == null) {
            this.audioBookFollowFragment = new AudioBookFollowFragment();
        }
        showFragment(this.audioBookFollowFragment, KEY_AudioBookFollowFragment);
    }

    public void showPlayedFragment() {
        AudioBookPlayedFragment audioBookPlayedFragment = this.audioBookPlayedFragment;
        if (audioBookPlayedFragment == null) {
            this.audioBookPlayedFragment = new AudioBookPlayedFragment();
        } else {
            audioBookPlayedFragment.initData();
        }
        showFragment(this.audioBookPlayedFragment, KEY_AudioBookPlayedFragment);
    }

    private void showFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment2 = this.currentFragment;
        if (fragment2 != null && fragment2 != fragment) {
            fragmentTransactionBeginTransaction.hide(fragment2);
        }
        if (!fragment.isAdded()) {
            fragmentTransactionBeginTransaction.add(R.id.fragment_container, fragment, tag);
        } else {
            fragmentTransactionBeginTransaction.show(fragment);
        }
        fragmentTransactionBeginTransaction.commit();
        this.currentFragment = fragment;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        AudioBookFollowFragment audioBookFollowFragment;
        AudioBookLikeChapterFragment audioBookLikeChapterFragment;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AudioBookPlayerActivity.REQUEST_CODE_BACK) {
            if (AudioBookGlobalParams.getInstance().isLikeChapterIsUpdate() && (audioBookLikeChapterFragment = this.audioBookLikeChapterFragment) != null && audioBookLikeChapterFragment.isVisible()) {
                this.audioBookLikeChapterFragment.initData();
            }
            if (AudioBookGlobalParams.getInstance().isCollectAlbumIsUpdate() && (audioBookFollowFragment = this.audioBookFollowFragment) != null && audioBookFollowFragment.isVisible()) {
                this.audioBookFollowFragment.initData();
            }
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        AudioBookLikeChapterFragment audioBookLikeChapterFragment = this.audioBookLikeChapterFragment;
        if (audioBookLikeChapterFragment != null && audioBookLikeChapterFragment.isResumed()) {
            this.audioBookLikeChapterFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
        }
        AudioBookPlayedFragment audioBookPlayedFragment = this.audioBookPlayedFragment;
        if (audioBookPlayedFragment == null || !audioBookPlayedFragment.isResumed()) {
            return;
        }
        this.audioBookPlayedFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        Fragment fragment;
        super.onStatusonStatusCallbackNext(status, args);
        if (status == MediaPlayerEnum.CallBackState.PREPARE) {
            updateAudioBookHistoryView();
        }
        if ((status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) && (fragment = this.currentFragment) != null && fragment.isResumed()) {
            Fragment fragment2 = this.currentFragment;
            if (fragment2 instanceof WanosBaseFragment) {
                ((WanosBaseFragment) fragment2).onStatusonStatusCallbackNext(status, args);
            }
        }
    }

    public void updateAudioBookHistoryView() {
        MediaPlayerService mediaPlayerService;
        AudioBookMineChapterItemBean audioBookInfoBean;
        AudioBookPlayedFragment audioBookPlayedFragment = this.audioBookPlayedFragment;
        if (audioBookPlayedFragment == null || !audioBookPlayedFragment.isVisible() || (mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService()) == null || mediaPlayerService.getCurMediaInfo() == null || mediaPlayerService.getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Audiobook || (audioBookInfoBean = mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean()) == null) {
            return;
        }
        this.audioBookPlayedFragment.updateProgress(audioBookInfoBean.getRadioId(), audioBookInfoBean.getId(), audioBookInfoBean.getProgress());
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        return event.getPointerCount() == 2;
    }
}
