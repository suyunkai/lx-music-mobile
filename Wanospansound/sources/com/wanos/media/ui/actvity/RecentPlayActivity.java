package com.wanos.media.ui.actvity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.ActivityRecentPlayBinding;
import com.wanos.media.ui.audiobook.AudioBookPlayedFragment;
import com.wanos.media.ui.music.MyMusicHistoryListFragment;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class RecentPlayActivity extends WanosBaseActivity implements View.OnClickListener {
    private static final String KEY_AUDIO_BOOK = "AUDIO_BOOK";
    private static final String KEY_MUSIC = "MUSIC";
    private AudioBookPlayedFragment audioBookPlayedFragment;
    private ActivityRecentPlayBinding binding;
    private Fragment currentFragment;
    private MyMusicHistoryListFragment myMusicHistoryListFragment;

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
            if (supportFragmentManager.findFragmentByTag(KEY_MUSIC) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(supportFragmentManager.findFragmentByTag(KEY_MUSIC)));
            }
            if (supportFragmentManager.findFragmentByTag(KEY_AUDIO_BOOK) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(supportFragmentManager.findFragmentByTag(KEY_AUDIO_BOOK)));
            }
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        }
        ActivityRecentPlayBinding activityRecentPlayBindingInflate = ActivityRecentPlayBinding.inflate(getLayoutInflater());
        this.binding = activityRecentPlayBindingInflate;
        setContentView(activityRecentPlayBindingInflate.getRoot());
        initView();
        initListener();
    }

    public void initView() {
        setTitleText(R.string.rec2_header_recent);
        initTabView();
    }

    private void initTabView() {
        String queryParameter;
        int i;
        Uri data = getIntent().getData();
        if (data != null && (queryParameter = data.getQueryParameter(AppConstants.ACTIVITY_TAB_PAGE)) != null && (i = Integer.parseInt(queryParameter)) <= 1) {
            selectTab(i);
        } else {
            selectTab(0);
        }
    }

    public void initListener() {
        this.binding.tabAudiobook.setOnClickListener(this);
        this.binding.tabMusic.setOnClickListener(this);
    }

    public void selectTab(int index) {
        if (index == 0) {
            this.binding.tabMusic.setSelected(true);
            showMusicFragment();
        } else {
            this.binding.tabMusic.setSelected(false);
        }
        if (index == 1) {
            this.binding.tabAudiobook.setSelected(true);
            showAudiobookFragment();
        } else {
            this.binding.tabAudiobook.setSelected(false);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tab_audiobook) {
            selectTab(1);
        } else {
            if (id != R.id.tab_music) {
                return;
            }
            selectTab(0);
        }
    }

    public void showMusicFragment() {
        Fragment fragment = this.myMusicHistoryListFragment;
        if (fragment == null) {
            MyMusicHistoryListFragment myMusicHistoryListFragment = new MyMusicHistoryListFragment();
            this.myMusicHistoryListFragment = myMusicHistoryListFragment;
            addFragment(myMusicHistoryListFragment, KEY_MUSIC);
            return;
        }
        showFragment(fragment);
    }

    public void showAudiobookFragment() {
        Fragment fragment = this.audioBookPlayedFragment;
        if (fragment == null) {
            AudioBookPlayedFragment audioBookPlayedFragment = new AudioBookPlayedFragment();
            this.audioBookPlayedFragment = audioBookPlayedFragment;
            addFragment(audioBookPlayedFragment, KEY_AUDIO_BOOK);
            return;
        }
        showFragment(fragment);
    }

    private void addFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment2 = this.currentFragment;
        if (fragment2 != null) {
            fragmentTransactionBeginTransaction.hide(fragment2);
        }
        fragmentTransactionBeginTransaction.add(R.id.fragment_container, fragment, tag);
        this.currentFragment = fragment;
        fragmentTransactionBeginTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
        fragmentTransactionBeginTransaction.commit();
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.hide(this.currentFragment);
        fragmentTransactionBeginTransaction.show(fragment);
        this.currentFragment = fragment;
        fragmentTransactionBeginTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        MyMusicHistoryListFragment myMusicHistoryListFragment = this.myMusicHistoryListFragment;
        if (myMusicHistoryListFragment != null && myMusicHistoryListFragment.isResumed()) {
            this.myMusicHistoryListFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
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
        if ((status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) && (fragment = this.currentFragment) != null && fragment.isResumed()) {
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
}
