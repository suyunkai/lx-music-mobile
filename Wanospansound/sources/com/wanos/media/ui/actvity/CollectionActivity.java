package com.wanos.media.ui.actvity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.ActivityCollectionBinding;
import com.wanos.media.ui.audiobook.AudioBookFollowFragment;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.AudioBookLikeChapterFragment;
import com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity;
import com.wanos.media.ui.music.MyMusicGroupListFragment;
import com.wanos.media.ui.music.MyMusicListFragment;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionActivity extends WanosBaseActivity implements View.OnClickListener {
    private static final String KEY_AUDIO_BOOK_ALBUM = "AUDIO_BOOK_ALBUM";
    private static final String KEY_AUDIO_BOOK_CHAPTER = "AUDIO_BOOK_CHAPTER";
    private static final String KEY_MY_MUSIC = "MY_MUSIC";
    private static final String KEY_MY_MUSIC_ALBUM = "MY_MUSIC_ALBUM";
    private AudioBookFollowFragment audioBookFollowFragment;
    private AudioBookLikeChapterFragment audioBookLikeChapterFragment;
    private ActivityCollectionBinding binding;
    private Fragment currentFragment;
    private MyMusicGroupListFragment myMusicGroupListFragment;
    private MyMusicListFragment myMusicListFragment;

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
            if (supportFragmentManager.findFragmentByTag(KEY_MY_MUSIC) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(supportFragmentManager.findFragmentByTag(KEY_MY_MUSIC)));
            }
            if (supportFragmentManager.findFragmentByTag(KEY_MY_MUSIC_ALBUM) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(supportFragmentManager.findFragmentByTag(KEY_MY_MUSIC_ALBUM)));
            }
            if (supportFragmentManager.findFragmentByTag(KEY_AUDIO_BOOK_ALBUM) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(supportFragmentManager.findFragmentByTag(KEY_AUDIO_BOOK_ALBUM)));
            }
            if (supportFragmentManager.findFragmentByTag(KEY_AUDIO_BOOK_CHAPTER) != null) {
                fragmentTransactionBeginTransaction.remove((Fragment) Objects.requireNonNull(supportFragmentManager.findFragmentByTag(KEY_AUDIO_BOOK_CHAPTER)));
            }
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        }
        ActivityCollectionBinding activityCollectionBindingInflate = ActivityCollectionBinding.inflate(getLayoutInflater());
        this.binding = activityCollectionBindingInflate;
        setContentView(activityCollectionBindingInflate.getRoot());
        initView();
        initListener();
    }

    public void initView() {
        setTitleText(R.string.rec2_header_collect);
        initTabView();
    }

    private void initTabView() {
        String queryParameter;
        int i;
        Uri data = getIntent().getData();
        if (data != null && (queryParameter = data.getQueryParameter(AppConstants.ACTIVITY_TAB_PAGE)) != null && (i = Integer.parseInt(queryParameter)) <= 3) {
            selectTab(i);
        } else {
            selectTab(0);
        }
    }

    public void initListener() {
        this.binding.tabChapter.setOnClickListener(this);
        this.binding.tabMusic.setOnClickListener(this);
        this.binding.tabMusicAlbum.setOnClickListener(this);
        this.binding.tabAudiobookAlbum.setOnClickListener(this);
    }

    public void selectTab(int index) {
        if (index == 0) {
            this.binding.tabMusic.setSelected(true);
            showMusicFragment();
        } else {
            this.binding.tabMusic.setSelected(false);
        }
        if (index == 1) {
            this.binding.tabMusicAlbum.setSelected(true);
            showMusicAlbumFragment();
        } else {
            this.binding.tabMusicAlbum.setSelected(false);
        }
        if (index == 2) {
            this.binding.tabAudiobookAlbum.setSelected(true);
            showAudiobookAlbumFragment();
        } else {
            this.binding.tabAudiobookAlbum.setSelected(false);
        }
        if (index == 3) {
            this.binding.tabChapter.setSelected(true);
            showAudiobookChapterFragment();
        } else {
            this.binding.tabChapter.setSelected(false);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_audiobook_album /* 2131362975 */:
                selectTab(2);
                break;
            case R.id.tab_chapter /* 2131362976 */:
                selectTab(3);
                break;
            case R.id.tab_music /* 2131362981 */:
                selectTab(0);
                break;
            case R.id.tab_music_album /* 2131362982 */:
                selectTab(1);
                break;
        }
    }

    public void showMusicFragment() {
        Fragment fragment = this.myMusicListFragment;
        if (fragment == null) {
            MyMusicListFragment myMusicListFragment = new MyMusicListFragment();
            this.myMusicListFragment = myMusicListFragment;
            addFragment(myMusicListFragment, KEY_MY_MUSIC);
            return;
        }
        showFragment(fragment);
    }

    public void showMusicAlbumFragment() {
        Fragment fragment = this.myMusicGroupListFragment;
        if (fragment == null) {
            MyMusicGroupListFragment myMusicGroupListFragment = new MyMusicGroupListFragment();
            this.myMusicGroupListFragment = myMusicGroupListFragment;
            addFragment(myMusicGroupListFragment, KEY_MY_MUSIC_ALBUM);
            return;
        }
        showFragment(fragment);
    }

    public void showAudiobookAlbumFragment() {
        Fragment fragment = this.audioBookFollowFragment;
        if (fragment == null) {
            AudioBookFollowFragment audioBookFollowFragment = new AudioBookFollowFragment();
            this.audioBookFollowFragment = audioBookFollowFragment;
            addFragment(audioBookFollowFragment, KEY_AUDIO_BOOK_ALBUM);
            return;
        }
        showFragment(fragment);
    }

    public void showAudiobookChapterFragment() {
        Fragment fragment = this.audioBookLikeChapterFragment;
        if (fragment == null) {
            AudioBookLikeChapterFragment audioBookLikeChapterFragment = new AudioBookLikeChapterFragment();
            this.audioBookLikeChapterFragment = audioBookLikeChapterFragment;
            addFragment(audioBookLikeChapterFragment, KEY_AUDIO_BOOK_CHAPTER);
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
        MyMusicListFragment myMusicListFragment = this.myMusicListFragment;
        if (myMusicListFragment != null && myMusicListFragment.isResumed()) {
            this.myMusicListFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
        }
        MyMusicGroupListFragment myMusicGroupListFragment = this.myMusicGroupListFragment;
        if (myMusicGroupListFragment != null && myMusicGroupListFragment.isResumed()) {
            this.myMusicGroupListFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
        }
        AudioBookFollowFragment audioBookFollowFragment = this.audioBookFollowFragment;
        if (audioBookFollowFragment != null && audioBookFollowFragment.isResumed()) {
            this.audioBookFollowFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
        }
        AudioBookLikeChapterFragment audioBookLikeChapterFragment = this.audioBookLikeChapterFragment;
        if (audioBookLikeChapterFragment == null || !audioBookLikeChapterFragment.isResumed()) {
            return;
        }
        this.audioBookLikeChapterFragment.onMediaInfoCallbackAppNext(type, mediaInfo);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            AudioBookFollowFragment audioBookFollowFragment = this.audioBookFollowFragment;
            if (audioBookFollowFragment != null) {
                audioBookFollowFragment.onStatusonStatusCallbackNext(status, args);
            }
            AudioBookLikeChapterFragment audioBookLikeChapterFragment = this.audioBookLikeChapterFragment;
            if (audioBookLikeChapterFragment != null) {
                audioBookLikeChapterFragment.onStatusonStatusCallbackNext(status, args);
            }
            MyMusicListFragment myMusicListFragment = this.myMusicListFragment;
            if (myMusicListFragment != null) {
                myMusicListFragment.onStatusonStatusCallbackNext(status, args);
            }
            MyMusicGroupListFragment myMusicGroupListFragment = this.myMusicGroupListFragment;
            if (myMusicGroupListFragment != null) {
                myMusicGroupListFragment.onStatusonStatusCallbackNext(status, args);
            }
        }
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
}
