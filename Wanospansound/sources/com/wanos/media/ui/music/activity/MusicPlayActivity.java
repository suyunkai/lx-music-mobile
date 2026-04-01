package com.wanos.media.ui.music.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.bumptech.glide.Priority;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.event.CollectChange2ColEvent;
import com.wanos.commonlibrary.event.CollectChange2HisEvent;
import com.wanos.commonlibrary.event.DailyExpiredEvent;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.ResourceNotExistEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.lyric.lrc.ILrcViewListener;
import com.wanos.lyric.lrc.impl.LrcRow;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.ActivityMusicPlayBinding;
import com.wanos.media.presenter.MusicPlayPresenter;
import com.wanos.media.ui.music.MusicTagStateHelp;
import com.wanos.media.ui.music.adapter.MusicListAdapter;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.AnitPlayClick;
import com.wanos.media.view.MusicPlayView;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerManager;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanos.wanosplayermodule.MediaStatistic;
import com.wanos.wanosplayermodule.util.MediaInfoChangeUitl;
import com.wanos.wanosplayermodule.util.MediaSharedPreferUtils;
import java.util.Formatter;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class MusicPlayActivity extends WanosBaseActivity<MusicPlayPresenter> implements MusicPlayView, MediaPlayerService.OnUpdateAppLrcListener {
    public static final String MUSIC_ID_KEY = "musicId";
    private ObjectAnimator alphaAnima;
    private AnimatorSet animatorSet;
    private ActivityMusicPlayBinding binding;
    private MusicInfoBean currPlayMediaMusic;
    private long currentTime;
    String currentTimeStr;
    private long dataMusicId;
    private Handler lrcSoughtHandler;
    private Runnable lrcSoughtRunnable;
    private List<MusicInfoBean> mMediaMusicBeanList;
    private MusicListAdapter musicListAdapter;
    private MediaPlayerEnum.PlayMode playModelType;
    private int startTrackingProgress;
    String totalTimeStr;
    private ObjectAnimator translationAnimator;
    private boolean isOpenPlayList = false;
    private boolean isOpenPlayLrc = false;
    private boolean isFirstResume = true;
    private long curMusicId = -1;
    private long lrcPauseScrollTime = 0;
    private long groupId = -100;
    private long groupType = -100;
    private long curPlayGorupId = -100;
    private long curPlayGorupType = -100;
    private int vipPayCompleteStartProgress = -1;
    protected boolean touchProgressBar = false;
    private boolean isNightDayModel = false;
    private boolean isFirst = true;
    private AnitClick anitClick = new AnitClick(this) { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.1
        @Override // com.wanos.media.util.AnitClick
        public void onAnitClick(View v) {
            switch (v.getId()) {
                case R.id.btn_last /* 2131361997 */:
                    if (MusicPlayActivity.this.getMediaPlayerService() != null) {
                        MusicPlayActivity.this.getMediaPlayerService().playPre();
                        MusicPlayActivity.this.binding.leftContent.seekbarMusicProgress.setSecondaryProgress(0);
                    }
                    break;
                case R.id.btn_next /* 2131362007 */:
                    if (MusicPlayActivity.this.getMediaPlayerService() != null) {
                        MusicPlayActivity.this.getMediaPlayerService().playNext();
                        MusicPlayActivity.this.binding.leftContent.seekbarMusicProgress.setSecondaryProgress(0);
                    }
                    break;
                case R.id.btn_vip /* 2131362052 */:
                    MusicPlayActivity.this.openWeChatPayActivity();
                    break;
                case R.id.iv_collect /* 2131362420 */:
                    if (UserInfoUtil.isLogin()) {
                        if (MusicPlayActivity.this.currPlayMediaMusic != null && MusicPlayActivity.this.mPresenter != null) {
                            MusicPlayActivity.this.binding.leftContent.ivCollect.setClickable(false);
                            ((MusicPlayPresenter) MusicPlayActivity.this.mPresenter).collectOrCancleCollectMusicRequest(MusicPlayActivity.this.currPlayMediaMusic, MusicPlayActivity.this.anitClick.getContext());
                            break;
                        }
                    } else {
                        MusicPlayActivity.this.showLoginDialog();
                        break;
                    }
                    break;
                case R.id.play_list /* 2131362783 */:
                    MusicPlayActivity.this.openOrClosePlayList();
                    if (MusicPlayActivity.this.isOpenPlayList) {
                        MusicPlayActivity.this.closePlayList();
                    } else {
                        MusicPlayActivity.this.openPlayList();
                    }
                    break;
                case R.id.play_lrc /* 2131362784 */:
                    MusicPlayActivity.this.switchPlayLrc();
                    break;
                case R.id.play_type /* 2131362787 */:
                    if (MusicPlayActivity.this.playModelType != MediaPlayerEnum.PlayMode.listloopplay) {
                        if (MusicPlayActivity.this.playModelType != MediaPlayerEnum.PlayMode.randomplay) {
                            if (MusicPlayActivity.this.playModelType == MediaPlayerEnum.PlayMode.singleloopplay) {
                                MusicPlayActivity.this.playModelType = MediaPlayerEnum.PlayMode.listloopplay;
                                ToastUtil.showMsg(R.string.music_list_loop_play);
                                MusicPlayActivity.this.binding.leftContent.playType.setImageResource(R.drawable.play_type_loop);
                            }
                        } else {
                            MusicPlayActivity.this.playModelType = MediaPlayerEnum.PlayMode.singleloopplay;
                            ToastUtil.showMsg(R.string.music_single_loop_play);
                            MusicPlayActivity.this.binding.leftContent.playType.setImageResource(R.drawable.play_type_single);
                        }
                    } else {
                        MusicPlayActivity.this.playModelType = MediaPlayerEnum.PlayMode.randomplay;
                        ToastUtil.showMsg(R.string.music_random_play);
                        MusicPlayActivity.this.binding.leftContent.playType.setImageResource(R.drawable.play_type_radom);
                    }
                    MusicPlayActivity.this.getMediaPlayerService().setPlayMode(MediaPlayerEnum.MediaType.Music, MusicPlayActivity.this.playModelType);
                    break;
                case R.id.title_left_btn /* 2131363031 */:
                    MusicPlayActivity.this.onBackPressed();
                    break;
            }
        }
    };
    boolean hasAddOnUpdateAppLrcCallbackListener = false;

    /* JADX INFO: Access modifiers changed from: private */
    public void switchPlayLrc() {
        if (this.isOpenPlayLrc) {
            closePlayLrc();
        } else {
            openPlayLrc();
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        MediaPlayerService mediaPlayerService = getMediaPlayerService();
        long j = this.dataMusicId;
        if (j != -1 && mediaPlayerService != null) {
            MediaInfo curMediaInfo = mediaPlayerService.getCurMediaInfo();
            if (curMediaInfo != null) {
                outState.putLong("dataMusicId", curMediaInfo.getMusicInfoBean().getMusicId());
            } else {
                outState.putLong("dataMusicId", this.dataMusicId);
            }
        } else {
            outState.putLong("dataMusicId", j);
        }
        Log.i(WanosBaseActivity.TAG, "onSaveInstanceState: ");
        outState.putBoolean("isNightDay", true);
        outState.putInt("Progress", this.startTrackingProgress);
        outState.putBoolean("isOpenPlayLrc", this.isOpenPlayLrc);
        outState.putBoolean("isOpenPlayList", this.isOpenPlayList);
        outState.putParcelable("rvState", this.binding.rightContent.musicListContent.recyclerviewMusicList.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Uri data = getIntent().getData();
            this.dataMusicId = -1L;
            if (data != null) {
                String queryParameter = data.getQueryParameter(MUSIC_ID_KEY);
                boolean booleanQueryParameter = data.getBooleanQueryParameter("isAppIn", false);
                try {
                    this.dataMusicId = Long.valueOf(queryParameter).longValue();
                    if (!booleanQueryParameter) {
                        MediaStatistic.getInstance().saveRecordWidgetMeidaMusic(this.dataMusicId);
                    }
                } catch (Exception unused) {
                }
            }
        } else {
            this.dataMusicId = savedInstanceState.getLong("dataMusicId", -1L);
        }
        this.mPresenter = new MusicPlayPresenter(this);
        ActivityMusicPlayBinding activityMusicPlayBindingInflate = ActivityMusicPlayBinding.inflate(getLayoutInflater());
        this.binding = activityMusicPlayBindingInflate;
        setContentView(activityMusicPlayBindingInflate.getRoot());
        iniView();
        this.isFirst = savedInstanceState == null;
        requestData();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(WanosBaseActivity.TAG, "onRestoreInstanceState: ");
        this.isNightDayModel = savedInstanceState.getBoolean("isNightDay", false);
        this.isOpenPlayLrc = savedInstanceState.getBoolean("isOpenPlayLrc", false);
        this.isOpenPlayList = savedInstanceState.getBoolean("isOpenPlayList", false);
        int i = savedInstanceState.getInt("Progress", 0);
        Parcelable parcelable = savedInstanceState.getParcelable("rvState");
        if (parcelable != null && this.binding.rightContent.musicListContent.recyclerviewMusicList != null) {
            this.binding.rightContent.musicListContent.recyclerviewMusicList.getLayoutManager().onRestoreInstanceState(parcelable);
        }
        if (i != 0 && getMediaPlayerService() != null) {
            getMediaPlayerService().getDuration();
            this.binding.leftContent.seekbarMusicProgress.setProgress(i);
        }
        if (this.isOpenPlayLrc) {
            openPlayLrc();
        }
        if (this.isOpenPlayList) {
            this.binding.leftContent.playList.setImageResource(R.drawable.ic_music_play_list_select);
            startAnima(1.0f, 0.0f, 0L);
        }
        if (this.isOpenPlayList || this.isOpenPlayLrc) {
            this.binding.leftContent.flTopCard.setVisibility(0);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void requestData() {
        if (this.mPresenter == 0 || getMediaPlayerService() == null) {
            return;
        }
        if (!this.hasAddOnUpdateAppLrcCallbackListener) {
            getMediaPlayerService().addOnUpdateAppLrcCallbackListener(this);
            this.hasAddOnUpdateAppLrcCallbackListener = true;
        }
        ((MusicPlayPresenter) this.mPresenter).updateOnLoadMediaInfoPlayListCallbackListener();
        updateProgressContentView();
        if (this.dataMusicId != -1) {
            if (getMediaPlayerService() != null && getMediaPlayerService().isPlaying() && (getMediaPlayerService().getCurMediaInfo() == null || getMediaPlayerService().getCurMediaInfo().getMusicInfoBean() == null || this.dataMusicId != getMediaPlayerService().getCurMediaInfo().getMusicInfoBean().getMusicId())) {
                getMediaPlayerService().pause();
            }
            ((MusicPlayPresenter) this.mPresenter).requestMusicInfoData(this.dataMusicId);
            return;
        }
        ((MusicPlayPresenter) this.mPresenter).requestMusicInfoData();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayServiceConnectListener
    public void onServiceConnected(MediaPlayerService mediaPlayerService) {
        super.onServiceConnected(mediaPlayerService);
        requestData();
    }

    private void iniView() {
        setTitleBarVisibility(8);
        setPlayBarVisibility(8);
        initLeftContentView();
        initRightContentView();
        initVisibleIsSpoken();
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.leftContent.titleLeftBtn.setContentDescription(getResources().getString(R.string.close_back_click));
            this.binding.leftContent.playList.setContentDescription(getResources().getString(R.string.open_music_list));
            this.binding.leftContent.playLrc.setContentDescription(getResources().getString(R.string.open_music_lyric));
            this.binding.leftContent.btnLast.setContentDescription(getResources().getString(R.string.play_music_last));
            this.binding.leftContent.btnNext.setContentDescription(getResources().getString(R.string.play_music_next));
            this.binding.leftContent.playType.setContentDescription(getResources().getString(R.string.play_music_type));
            this.binding.rightContent.btnVip.setContentDescription(getResources().getString(R.string.open_member));
            this.binding.rightContent.musicListContent.recyclerviewMusicList.setContentDescription(getResources().getString(R.string.scroll_current_list));
            ViewCompat.setAccessibilityDelegate(this.binding.leftContent.playList, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.2
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View host, int action, Bundle args) {
                    if (action == 1001 && !MusicPlayActivity.this.isOpenPlayList) {
                        MusicPlayActivity.this.openOrClosePlayList();
                        MusicPlayActivity.this.openPlayList();
                        MusicPlayActivity.this.binding.leftContent.playLrc.setImageResource(R.drawable.ic_music_play_irc_normal);
                    }
                    if (action == 1002 && MusicPlayActivity.this.isOpenPlayList) {
                        MusicPlayActivity.this.openOrClosePlayList();
                        MusicPlayActivity.this.closePlayList();
                    }
                    return super.performAccessibilityAction(host, action, args);
                }
            });
            ViewCompat.setAccessibilityDelegate(this.binding.leftContent.playLrc, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.3
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View host, int action, Bundle args) {
                    if (action == 1003 && !MusicPlayActivity.this.isOpenPlayLrc) {
                        MusicPlayActivity.this.openPlayLrc();
                    }
                    if (action == 1004 && MusicPlayActivity.this.isOpenPlayLrc) {
                        MusicPlayActivity.this.closePlayLrc();
                    }
                    return super.performAccessibilityAction(host, action, args);
                }
            });
        }
    }

    private void initLeftContentView() {
        Util.setTextWeight(this.binding.leftContent.tvMusicName, 500);
        this.binding.leftContent.titleLeftBtn.setOnClickListener(this.anitClick);
        this.binding.leftContent.titleLeftBtn.setOnClickListener(this.anitClick);
        this.binding.leftContent.ivCollect.setOnClickListener(this.anitClick);
        this.binding.leftContent.btnLast.setOnClickListener(this.anitClick);
        this.binding.leftContent.btnPlay.setOnClickListener(this.anitClick);
        this.binding.leftContent.btnNext.setOnClickListener(this.anitClick);
        this.binding.leftContent.playType.setOnClickListener(this.anitClick);
        this.binding.leftContent.playList.setOnClickListener(this.anitClick);
        this.binding.leftContent.playLrc.setOnClickListener(this.anitClick);
        this.binding.leftContent.btnPlay.setOnClickListener(new AnitPlayClick(500L) { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.4
            @Override // com.wanos.media.util.AnitPlayClick
            public void onAnitClick(View v) {
                if (MusicPlayActivity.this.getMediaPlayerService() != null) {
                    if (MusicPlayActivity.this.getMediaPlayerService().isPlaying()) {
                        MusicPlayActivity.this.getMediaPlayerService().pause();
                    } else {
                        MusicPlayActivity.this.getMediaPlayerService().start();
                    }
                }
            }
        });
        this.binding.leftContent.seekbarMusicProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.5
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean b2) {
                MusicPlayActivity.this.checkSeekBarRange(seekBar);
                if (!b2 || MusicPlayActivity.this.getMediaPlayerService() == null) {
                    return;
                }
                MusicPlayActivity.this.binding.leftContent.tvCurrTime.setText(MusicPlayActivity.this.stringForTime((long) (r5.getMediaPlayerService().getDuration() * ((i * 1.0f) / 100.0f))));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                MusicPlayActivity.this.touchProgressBar = true;
                MusicPlayActivity.this.startTrackingProgress = seekBar.getProgress();
                MusicPlayActivity.this.vipPayCompleteStartProgress = -1;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                MusicPlayActivity.this.touchProgressBar = false;
                MusicPlayActivity.this.updateVipStatus();
                MusicPlayActivity.this.vipPayCompleteStartProgress = -1;
                if (UserInfoUtil.isVipAndUnexpired()) {
                    if (MusicPlayActivity.this.getMediaPlayerService() != null) {
                        MusicPlayActivity.this.getMediaPlayerService().seekTo((((long) MusicPlayActivity.this.getMediaPlayerService().getDuration()) * ((long) seekBar.getProgress())) / 100, 3);
                        if (MusicPlayActivity.this.getMediaPlayerService().isPlaying()) {
                            return;
                        }
                        MusicPlayActivity.this.getMediaPlayerService().start();
                        return;
                    }
                    return;
                }
                if (MusicPlayActivity.this.getMediaPlayerService() == null || MusicPlayActivity.this.currPlayMediaMusic == null) {
                    return;
                }
                boolean zIsVipMusic = MusicPlayActivity.this.currPlayMediaMusic.isVipMusic();
                long duration = MusicPlayActivity.this.getMediaPlayerService().getDuration();
                long progress = ((long) seekBar.getProgress()) * (duration / 100);
                long previewStartTime = MusicPlayActivity.this.currPlayMediaMusic.getPreviewStartTime() * 1000;
                long previewEndTime = MusicPlayActivity.this.currPlayMediaMusic.getPreviewEndTime() * 1000;
                if (zIsVipMusic && (progress >= previewEndTime || progress < previewStartTime)) {
                    if (MusicPlayActivity.this.currPlayMediaMusic.isFree()) {
                        MusicPlayActivity.this.getMediaPlayerService().seekTo((duration * ((long) seekBar.getProgress())) / 100, 3);
                        if (MusicPlayActivity.this.getMediaPlayerService().isPlaying()) {
                            return;
                        }
                        MusicPlayActivity.this.getMediaPlayerService().start();
                        return;
                    }
                    MusicPlayActivity.this.vipPayCompleteStartProgress = seekBar.getProgress();
                    seekBar.setProgress(MusicPlayActivity.this.startTrackingProgress);
                    MusicPlayActivity.this.binding.leftContent.tvCurrTime.setText(MusicPlayActivity.this.stringForTime((long) (r0.getMediaPlayerService().getDuration() * ((MusicPlayActivity.this.startTrackingProgress * 1.0f) / 100.0f))));
                    MusicPlayActivity.this.getMediaPlayerService().seekTo((duration * ((long) MusicPlayActivity.this.startTrackingProgress)) / 100, 3);
                    if (MusicPlayActivity.this.getMediaPlayerService().isPlaying()) {
                        MusicPlayActivity.this.getMediaPlayerService().pause();
                    }
                    ToastUtil.showMsg(R.string.music_vip_alert);
                    MusicPlayActivity.this.openWeChatPayActivity();
                    return;
                }
                MusicPlayActivity.this.getMediaPlayerService().seekTo((duration * ((long) seekBar.getProgress())) / 100, 3);
                if (MusicPlayActivity.this.getMediaPlayerService().isPlaying()) {
                    return;
                }
                MusicPlayActivity.this.getMediaPlayerService().start();
            }
        });
        if (getMediaPlayerService() != null) {
            this.binding.leftContent.ivPlay.setImageResource(getMediaPlayerService().isPlaying() ? R.drawable.ic_music_play_playing : R.drawable.ic_music_play_pause);
            this.currentTime = getMediaPlayerService().getCurrentPosition();
            int duration = getMediaPlayerService().getDuration();
            updateProgressView(duration != 0 ? Math.round(((this.currentTime * 1.0f) / duration) * 100.0f) : 0, duration > 0);
        }
        updatePlayMode();
    }

    public void checkSeekBarRange(SeekBar seekBar) {
        MusicInfoBean musicInfoBean;
        if (UserInfoUtil.isVipAndUnexpired() || getMediaPlayerService() == null || (musicInfoBean = this.currPlayMediaMusic) == null || musicInfoBean.isFree()) {
            return;
        }
        boolean zIsVipMusic = this.currPlayMediaMusic.isVipMusic();
        double progress = (((((double) seekBar.getProgress()) * duration) / 100.0d) * 1.0d) / 1000.0d;
        long previewStartTime = this.currPlayMediaMusic.getPreviewStartTime();
        long previewEndTime = this.currPlayMediaMusic.getPreviewEndTime();
        if (previewEndTime > previewStartTime && zIsVipMusic) {
            double d2 = previewEndTime;
            if (progress >= d2 || progress < previewStartTime) {
                double d3 = previewStartTime;
                if (progress >= d3) {
                    if (progress > d2) {
                        seekBar.setProgress(((int) Math.round(((d2 * 1000.0d) / duration) * 100.0d)) + 1);
                    }
                } else {
                    int iRound = (int) Math.round(((d3 * 1000.0d) / duration) * 100.0d);
                    if (iRound >= 1) {
                        iRound--;
                    }
                    seekBar.setProgress(iRound);
                }
            }
        }
    }

    private void updatePlayMode() {
        MediaPlayerEnum.PlayMode musicMode = MediaSharedPreferUtils.getMusicMode();
        this.playModelType = musicMode;
        if (musicMode == MediaPlayerEnum.PlayMode.listloopplay) {
            this.binding.leftContent.playType.setImageResource(R.drawable.play_type_loop);
            return;
        }
        if (this.playModelType == MediaPlayerEnum.PlayMode.randomplay) {
            this.binding.leftContent.playType.setImageResource(R.drawable.play_type_radom);
        } else if (this.playModelType == MediaPlayerEnum.PlayMode.singleloopplay) {
            this.binding.leftContent.playType.setImageResource(R.drawable.play_type_single);
        } else if (this.playModelType == MediaPlayerEnum.PlayMode.orderplay) {
            this.binding.leftContent.playType.setImageResource(R.drawable.play_type_list);
        }
    }

    private void updateProgressContentView() {
        if (getMediaPlayerService() != null) {
            if (getMediaPlayerService().getPreprareStatus() == MediaPlayerEnum.PreprareStatus.PREPRAREING) {
                this.binding.leftContent.ivPlay.setVisibility(8);
                this.binding.leftContent.pbMusicPlay.setVisibility(0);
                this.binding.leftContent.btnPlay.setEnabled(false);
            } else {
                this.binding.leftContent.ivPlay.setVisibility(0);
                this.binding.leftContent.pbMusicPlay.setVisibility(8);
                this.binding.leftContent.btnPlay.setEnabled(true);
                this.binding.leftContent.ivPlay.setImageResource(getMediaPlayerService().isPlaying() ? R.drawable.ic_music_play_playing : R.drawable.ic_music_play_pause);
            }
            this.currentTime = getMediaPlayerService().getCurrentPosition();
            int duration = getMediaPlayerService().getDuration();
            updateProgressView(duration != 0 ? Math.round(((this.currentTime * 1.0f) / duration) * 100.0f) : 0, duration > 0);
        }
    }

    private void initRightContentView() {
        this.binding.rightContent.btnVip.setOnClickListener(this.anitClick);
        initLrcView();
        initRecyclerView();
    }

    private void initLrcView() {
        this.binding.rightContent.lrcView.setListener(new ILrcViewListener() { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.6
            @Override // com.wanos.lyric.lrc.ILrcViewListener
            public void onLrcSought(int newPosition, LrcRow row) {
                MusicPlayActivity.this.lrcSoughtWait();
            }
        });
    }

    private void initRecyclerView() {
        MusicListAdapter musicListAdapter = new MusicListAdapter(this, this.mMediaMusicBeanList, this.groupId, this.groupType);
        this.musicListAdapter = musicListAdapter;
        musicListAdapter.setHandRefreshPlayList(false);
        this.binding.rightContent.musicListContent.recyclerviewMusicList.setAdapter(this.musicListAdapter);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.isFirstResume) {
            startAnima(0.0f, 1.0f, 0L);
            this.binding.rightContent.musicListContent.getRoot().setVisibility(0);
        }
        this.isFirstResume = false;
        if (this.isNightDayModel) {
            return;
        }
        if (getMediaPlayerService() != null && getMediaPlayerService().getCurMediaInfo() != null && getMediaPlayerService().getCurMediaInfo().getMusicInfoBean() != null) {
            long j = this.curMusicId;
            if (j != -1 && j != getMediaPlayerService().getCurMediaInfo().getMusicInfoBean().getMusicId()) {
                requestData();
            }
        }
        if (this.vipPayCompleteStartProgress != -1) {
            if (UserInfoUtil.isVipAndUnexpired() && getMediaPlayerService() != null) {
                this.binding.leftContent.seekbarMusicProgress.setProgress(this.startTrackingProgress);
                getMediaPlayerService().seekTo((((long) getMediaPlayerService().getDuration()) * ((long) this.binding.leftContent.seekbarMusicProgress.getProgress())) / 100, 3);
                if (!getMediaPlayerService().isPlaying()) {
                    getMediaPlayerService().start();
                }
            }
            this.vipPayCompleteStartProgress = -1;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        requestData();
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
        setTitleBarVisibility(0);
        showLoadingView();
    }

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
        setTitleBarVisibility(8);
        closeLoadingView();
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
        setTitleBarVisibility(0);
        showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MusicPlayActivity.this.mPresenter == null || MusicPlayActivity.this.dataMusicId == -1) {
                    return;
                }
                ((MusicPlayPresenter) MusicPlayActivity.this.mPresenter).requestMusicInfoData(MusicPlayActivity.this.dataMusicId);
            }
        });
    }

    @Override // com.wanos.media.view.MusicPlayView
    public void updateMusicView(MusicInfoBean musicInfoBean) {
        updateMusicView(musicInfoBean, true);
    }

    public void updateMusicView(MusicInfoBean musicInfoBean, boolean isUpdatePreListenNode) {
        this.currPlayMediaMusic = musicInfoBean;
        this.curMusicId = musicInfoBean.getMusicId();
        updateLrc();
        GlideUtil.setImageData(SizeMode.PLAY, this.currPlayMediaMusic.getAvatar(), this.binding.leftContent.imTopCard, Priority.HIGH);
        GlideUtil.setImageData(SizeMode.PLAY, this.currPlayMediaMusic.getAvatar(), this.binding.rightContent.ivRightMusicCover, Priority.HIGH);
        String subTitleByInfo = MediaInfoChangeUitl.getSubTitleByInfo(this.currPlayMediaMusic);
        this.binding.leftContent.tvMusicName.setText(this.currPlayMediaMusic.getName());
        this.binding.leftContent.tvSingerName.setText(subTitleByInfo);
        ImageView imageView = this.binding.leftContent.ivCollect;
        boolean zIsLogin = UserInfoUtil.isLogin();
        int i = R.drawable.ic_music_play_no_collect;
        if (zIsLogin && this.currPlayMediaMusic.isCollection()) {
            i = R.drawable.ic_music_play_collect;
        }
        imageView.setImageResource(i);
        if (this.currPlayMediaMusic.getContentType() <= 2) {
            this.binding.leftContent.playLrc.setEnabled(true);
        } else {
            if (this.isOpenPlayLrc) {
                closePlayLrc();
            }
            this.binding.leftContent.playLrc.setEnabled(false);
        }
        Log.d(WanosBaseActivity.TAG, "updateMusicView: isVipMusic = " + this.currPlayMediaMusic.isVipMusic() + ", isFree = " + this.currPlayMediaMusic.isFree());
        MusicTagStateHelp.initMusicTagState(this.currPlayMediaMusic.isVipMusic(), this.currPlayMediaMusic.isFree(), this.binding.leftContent.ivVip);
        updateVipStatus(isUpdatePreListenNode);
    }

    @Override // com.wanos.media.view.MusicPlayView
    public void updateCollectStatus(long musicId, boolean isCollect) {
        this.binding.leftContent.ivCollect.setClickable(true);
        MusicInfoBean musicInfoBean = this.currPlayMediaMusic;
        if (musicInfoBean != null && musicInfoBean.getMusicId() == musicId) {
            this.currPlayMediaMusic.setCollection(isCollect);
            ImageView imageView = this.binding.leftContent.ivCollect;
            boolean zIsLogin = UserInfoUtil.isLogin();
            int i = R.drawable.ic_music_play_no_collect;
            if (zIsLogin && this.currPlayMediaMusic.isCollection()) {
                i = R.drawable.ic_music_play_collect;
            }
            imageView.setImageResource(i);
            if (!isCollect && this.groupId == -4) {
                EventBus.getDefault().post(new CollectChange2HisEvent(this.currPlayMediaMusic.getMusicId()));
            } else {
                EventBus.getDefault().post(new CollectChange2ColEvent());
            }
        }
        if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
            MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, musicId, isCollect);
        }
        ToastUtil.showMsg(isCollect ? R.string.music_collect_complete : R.string.music_cancel_collect_complete);
    }

    @Override // com.wanos.media.view.MusicPlayView
    public void updateCollectFailer() {
        this.binding.leftContent.ivCollect.setClickable(true);
    }

    @Override // com.wanos.media.view.MusicPlayView
    public void updateMusicListView(long groupId, long groupType, List<MusicInfoBean> mediaMusicBeanList) {
        this.mMediaMusicBeanList = mediaMusicBeanList;
        this.groupId = groupId;
        this.groupType = groupType;
        this.musicListAdapter.setGroupId(groupId);
        this.musicListAdapter.setGroupType(groupType);
        this.musicListAdapter.setData(this.mMediaMusicBeanList);
        List<MusicInfoBean> list = this.mMediaMusicBeanList;
        if (list == null || list.size() == 0) {
            this.binding.rightContent.musicListContent.recyclerviewMusicList.setVisibility(8);
            this.binding.rightContent.musicListContent.listErrorView.getRoot().setVisibility(8);
            this.binding.rightContent.musicListContent.emptyView.getRoot().setVisibility(0);
            this.binding.rightContent.musicListContent.emptyView.tvEmpty.setText(R.string.empty_music);
            this.binding.rightContent.musicListContent.listLoadingView.getRoot().setVisibility(8);
        } else {
            this.binding.rightContent.musicListContent.recyclerviewMusicList.setVisibility(0);
            this.binding.rightContent.musicListContent.listErrorView.getRoot().setVisibility(8);
            this.binding.rightContent.musicListContent.emptyView.getRoot().setVisibility(8);
            this.binding.rightContent.musicListContent.listLoadingView.getRoot().setVisibility(8);
        }
        int curPlayMediaId = setCurPlayMediaId();
        this.musicListAdapter.notifyDataSetChanged();
        if (curPlayMediaId > -1 && this.isFirst) {
            this.isFirst = false;
            this.binding.rightContent.musicListContent.recyclerviewMusicList.scrollToPosition(curPlayMediaId);
        }
        updatePlayView();
    }

    @Override // com.wanos.media.view.MusicPlayView
    public void showListLoading() {
        this.binding.rightContent.musicListContent.recyclerviewMusicList.setVisibility(8);
        this.binding.rightContent.musicListContent.listErrorView.getRoot().setVisibility(8);
        this.binding.rightContent.musicListContent.emptyView.getRoot().setVisibility(8);
        this.binding.rightContent.musicListContent.listLoadingView.getRoot().setVisibility(0);
    }

    @Override // com.wanos.media.view.MusicPlayView
    public void hideListLoading() {
        this.binding.rightContent.musicListContent.recyclerviewMusicList.setVisibility(0);
        this.binding.rightContent.musicListContent.listErrorView.getRoot().setVisibility(8);
        this.binding.rightContent.musicListContent.emptyView.getRoot().setVisibility(8);
        this.binding.rightContent.musicListContent.listLoadingView.getRoot().setVisibility(8);
    }

    @Override // com.wanos.media.view.MusicPlayView
    public void showListFailOrError() {
        this.binding.rightContent.musicListContent.recyclerviewMusicList.setVisibility(8);
        this.binding.rightContent.musicListContent.listErrorView.getRoot().setVisibility(0);
        this.binding.rightContent.musicListContent.emptyView.getRoot().setVisibility(8);
        this.binding.rightContent.musicListContent.listLoadingView.getRoot().setVisibility(8);
        this.binding.rightContent.musicListContent.listErrorView.btnRetry.setOnClickListener(new AnitClick() { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.8
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View v) {
                ((MusicPlayPresenter) MusicPlayActivity.this.mPresenter).getPlayMusicListData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openOrClosePlayList() {
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null && (animatorSet.isStarted() || this.animatorSet.isRunning())) {
            this.animatorSet.cancel();
        }
        float f = 0.0f;
        float f2 = 1.0f;
        if (this.isOpenPlayList) {
            this.binding.leftContent.playList.setImageResource(R.drawable.ic_music_play_list_normal);
            this.isOpenPlayList = false;
        } else {
            this.binding.leftContent.playList.setImageResource(R.drawable.ic_music_play_list_select);
            this.isOpenPlayLrc = false;
            this.isOpenPlayList = true;
            f2 = 0.0f;
            f = 1.0f;
        }
        startAnima(f, f2, 200L);
    }

    private void startAnima(final float startValue, float endValue, long duration) {
        if (this.animatorSet == null) {
            this.animatorSet = new AnimatorSet();
            ObjectAnimator objectAnimator = new ObjectAnimator();
            this.translationAnimator = objectAnimator;
            objectAnimator.setTarget(this.binding.rightContent.musicListContent.getRoot());
            this.translationAnimator.setPropertyName("translationX");
            ObjectAnimator objectAnimator2 = new ObjectAnimator();
            this.alphaAnima = objectAnimator2;
            objectAnimator2.setTarget(this.binding.rightContent.flConverLrc);
            this.alphaAnima.setPropertyName("alpha");
            this.animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.9
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    if (startValue != 0.0f || MusicPlayActivity.this.binding.rightContent.flConverLrc == null) {
                        return;
                    }
                    MusicPlayActivity.this.binding.rightContent.flConverLrc.setVisibility(0);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                    if (MusicPlayActivity.this.binding.rightContent.flConverLrc == null || !MusicPlayActivity.this.isOpenPlayList) {
                        MusicPlayActivity.this.binding.rightContent.flConverLrc.setVisibility(0);
                    } else {
                        MusicPlayActivity.this.binding.rightContent.flConverLrc.setVisibility(8);
                    }
                }
            });
        }
        if (this.animatorSet.isStarted() || this.animatorSet.isRunning()) {
            return;
        }
        float screenWidth = CommonUtils.getScreenWidth(this) / 2.0f;
        this.translationAnimator.setFloatValues(startValue * screenWidth, screenWidth * endValue);
        this.alphaAnima.setFloatValues(startValue, endValue);
        this.animatorSet.play(this.translationAnimator).with(this.alphaAnima);
        this.animatorSet.setDuration(duration);
        this.animatorSet.start();
    }

    public static void startMusicPlayActivity(Context context) {
        Intent intent = new Intent(context, (Class<?>) MusicPlayActivity.class);
        if (context instanceof Activity) {
            ((Activity) context).startActivity(intent);
        }
    }

    public static void startMusicPlayActivity(Context context, boolean isNormalOpenPlayList) {
        Intent intent = new Intent(context, (Class<?>) MusicPlayActivity.class);
        if (context instanceof Activity) {
            intent.putExtra("isNormalOpenPlayList", isNormalOpenPlayList);
            ((Activity) context).startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openPlayLrc() {
        this.isOpenPlayLrc = true;
        this.binding.leftContent.playList.setImageResource(R.drawable.ic_music_play_list_normal);
        this.binding.leftContent.playLrc.setImageResource(R.drawable.ic_music_play_lrc_select);
        this.binding.rightContent.lrcView.setVisibility(0);
        this.binding.rightContent.rightCdCover.setVisibility(8);
        this.binding.leftContent.flTopCard.setVisibility(0);
        if (this.isOpenPlayList) {
            openOrClosePlayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closePlayLrc() {
        this.isOpenPlayLrc = false;
        this.binding.leftContent.playLrc.setImageResource(R.drawable.ic_music_play_lrc_selector);
        this.binding.rightContent.lrcView.setVisibility(8);
        this.binding.rightContent.rightCdCover.setVisibility(0);
        this.binding.leftContent.flTopCard.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openPlayList() {
        this.binding.rightContent.lrcView.setVisibility(8);
        this.binding.rightContent.rightCdCover.setVisibility(0);
        this.binding.leftContent.flTopCard.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closePlayList() {
        if (this.isOpenPlayLrc) {
            this.isOpenPlayLrc = false;
            this.binding.leftContent.playLrc.setImageResource(R.drawable.ic_music_play_lrc_selector);
            this.binding.rightContent.lrcView.setVisibility(8);
            this.binding.rightContent.rightCdCover.setVisibility(8);
            this.binding.leftContent.flTopCard.setVisibility(0);
            return;
        }
        this.binding.leftContent.playLrc.setImageResource(R.drawable.ic_music_play_lrc_selector);
        this.binding.leftContent.flTopCard.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lrcSoughtWait() {
        this.lrcPauseScrollTime = 3000L;
        if (this.lrcSoughtHandler == null) {
            this.lrcSoughtHandler = new Handler();
            this.lrcSoughtRunnable = new Runnable() { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.10
                @Override // java.lang.Runnable
                public void run() {
                    MusicPlayActivity.this.lrcPauseScrollTime = 0L;
                    MusicPlayActivity.this.startLrcPlay();
                }
            };
        }
        this.lrcSoughtHandler.removeCallbacks(this.lrcSoughtRunnable);
        this.lrcSoughtHandler.postDelayed(this.lrcSoughtRunnable, 3000L);
    }

    private void stopLrcSoughtWait() {
        Handler handler = this.lrcSoughtHandler;
        if (handler != null) {
            handler.removeCallbacks(this.lrcSoughtRunnable);
            this.lrcPauseScrollTime = 0L;
        }
    }

    private void updateLrc() {
        stopLrcSoughtWait();
        if (this.currPlayMediaMusic != null) {
            getMediaPlayerService().updateLrc(this.currPlayMediaMusic.getLrcPath(), MediaPlayerEnum.AppType.App);
        }
        startLrcPlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLrcPlay() {
        if (this.lrcPauseScrollTime == 0) {
            this.binding.rightContent.lrcView.seekLrcToTime(this.currentTime);
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        boolean z = getMediaPlayerService() == null || this.currentTime != ((long) getMediaPlayerService().getCurrentPosition());
        this.currentTime = getMediaPlayerService().getCurrentPosition();
        if (status == MediaPlayerEnum.CallBackState.PREPARING) {
            this.touchProgressBar = false;
            this.binding.leftContent.ivPlay.setVisibility(8);
            this.binding.leftContent.pbMusicPlay.setVisibility(0);
            this.binding.leftContent.btnPlay.setEnabled(false);
            updateProgressView(0, false);
            this.binding.rightContent.lrcView.setLoadingTipText("");
            this.binding.rightContent.lrcView.setLrc(null);
            this.binding.rightContent.lrcView.seekLrc(0, false);
            updateLrc();
        } else if (status == MediaPlayerEnum.CallBackState.PREPARE) {
            this.binding.leftContent.ivPlay.setVisibility(8);
            this.binding.leftContent.pbMusicPlay.setVisibility(0);
            this.binding.leftContent.btnPlay.setEnabled(false);
            updateProgressView(0, true);
            updateVipStatus();
            if (z) {
                startLrcPlay();
            }
        } else if (status == MediaPlayerEnum.CallBackState.STARTED) {
            this.binding.leftContent.ivPlay.setVisibility(0);
            this.binding.leftContent.pbMusicPlay.setVisibility(8);
            this.binding.leftContent.btnPlay.setEnabled(true);
            this.binding.leftContent.ivPlay.setImageResource(R.drawable.ic_music_play_playing);
        } else if (status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE) {
            this.binding.leftContent.ivPlay.setVisibility(0);
            this.binding.leftContent.pbMusicPlay.setVisibility(8);
            this.binding.leftContent.btnPlay.setEnabled(true);
            this.binding.leftContent.ivPlay.setImageResource(R.drawable.ic_music_play_pause);
        } else if (status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION) {
            this.binding.leftContent.ivPlay.setVisibility(8);
            this.binding.leftContent.pbMusicPlay.setVisibility(0);
            this.binding.leftContent.btnPlay.setEnabled(false);
        } else if (status == MediaPlayerEnum.CallBackState.COMPLETE) {
            this.binding.leftContent.ivPlay.setVisibility(0);
            this.binding.leftContent.pbMusicPlay.setVisibility(8);
            this.binding.leftContent.btnPlay.setEnabled(true);
            this.binding.leftContent.ivPlay.setImageResource(R.drawable.ic_music_play_pause);
        } else if (status != MediaPlayerEnum.CallBackState.SEEK_COMPLETE && status == MediaPlayerEnum.CallBackState.PROGRESS) {
            int iIntValue = ((Integer) args[0]).intValue();
            boolean zBooleanValue = ((Boolean) args[1]).booleanValue();
            this.binding.leftContent.ivPlay.setVisibility(zBooleanValue ? 8 : 0);
            this.binding.leftContent.pbMusicPlay.setVisibility(zBooleanValue ? 0 : 8);
            this.binding.leftContent.btnPlay.setEnabled(!zBooleanValue);
            if (!this.touchProgressBar) {
                updateProgressView(iIntValue, true);
            }
            if (z) {
                startLrcPlay();
            }
        }
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            updatePlayView();
        }
    }

    private void updateProgressView(int progress, boolean isPrepared) {
        int currentPosition;
        int duration;
        this.binding.leftContent.seekbarMusicProgress.setProgress(progress);
        if (getMediaPlayerService() != null) {
            currentPosition = getMediaPlayerService().getCurrentPosition();
            duration = MediaPlayEngine.getInstance().getMediaPlayerService().getDuration();
        } else {
            currentPosition = 0;
            duration = 0;
        }
        String strStringForTime = stringForTime(isPrepared ? currentPosition : 0L);
        String strStringForTime2 = stringForTime(isPrepared ? duration : 0L);
        if (!strStringForTime.equals(this.currentTimeStr)) {
            this.currentTimeStr = strStringForTime;
            this.binding.leftContent.tvCurrTime.setText(this.currentTimeStr);
        }
        if (!strStringForTime2.equals(this.totalTimeStr)) {
            this.totalTimeStr = strStringForTime2;
            this.binding.leftContent.tvDurTime.setText(this.totalTimeStr);
        }
        this.binding.leftContent.seekbarMusicProgress.setClickable(isPrepared);
        this.binding.leftContent.seekbarMusicProgress.setEnabled(isPrepared);
        this.binding.leftContent.seekbarMusicProgress.setSelected(isPrepared);
        this.binding.leftContent.seekbarMusicProgress.setFocusable(isPrepared);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String stringForTime(long totalMS) {
        long j = totalMS / 1000;
        long j2 = j % 60;
        long j3 = j / 60;
        long j4 = j3 % 60;
        return j > 216000 ? new Formatter().format("%02d:%02d:%02d", Long.valueOf(j3 / 3600), Long.valueOf(j4), Long.valueOf(j2)).toString() : new Formatter().format("%02d:%02d", Long.valueOf(j4), Long.valueOf(j2)).toString();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        Log.e(WanosBaseActivity.TAG, "onMediaInfoCallbackAppNext: " + mediaInfo.getMediaType() + ",当前页面=" + getClass().getSimpleName());
        if (mediaInfo != null && mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music) {
            onBackPressed();
        } else {
            updateMediaPlayView(type, mediaInfo);
        }
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnUpdateAppLrcListener
    public void onUpdateAppLrc(final MediaPlayerEnum.UpdateLrcCallbackType type, final List<LrcRow> lrcRowsList) {
        runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.music.activity.MusicPlayActivity.11
            @Override // java.lang.Runnable
            public void run() {
                if (MusicPlayActivity.this.binding.rightContent.lrcView == null || MediaPlayerEnum.UpdateLrcCallbackType.LOADING == type) {
                    return;
                }
                MusicPlayActivity.this.binding.rightContent.lrcView.setLrc(lrcRowsList);
                MusicPlayActivity.this.binding.rightContent.lrcView.seekLrc(0, false);
                if (MediaPlayerEnum.UpdateLrcCallbackType.START == type) {
                    MusicPlayActivity.this.binding.rightContent.lrcView.setLoadingTipText("正在获取歌词...");
                } else if (MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS == type) {
                    MusicPlayActivity.this.binding.rightContent.lrcView.setLoadingTipText(MusicPlayActivity.this.getString(R.string.the_music_no_lrc));
                } else if (MediaPlayerEnum.UpdateLrcCallbackType.FAILED == type) {
                    MusicPlayActivity.this.binding.rightContent.lrcView.setLoadingTipText("获取歌词失败");
                }
                MusicPlayActivity.this.startLrcPlay();
            }
        });
    }

    private int setCurPlayMediaId() {
        long musicId;
        List<MusicInfoBean> list;
        int i = -1;
        if (this.currPlayMediaMusic != null && (list = this.mMediaMusicBeanList) != null && list.size() > 0) {
            musicId = this.currPlayMediaMusic.getMusicId();
            int i2 = 0;
            while (true) {
                if (i2 < this.mMediaMusicBeanList.size()) {
                    MusicInfoBean musicInfoBean = this.mMediaMusicBeanList.get(i2);
                    if (musicInfoBean != null && musicInfoBean.getMusicId() == this.currPlayMediaMusic.getMusicId()) {
                        i = i2;
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
        } else {
            musicId = -1;
        }
        MusicListAdapter musicListAdapter = this.musicListAdapter;
        if (musicListAdapter != null) {
            musicListAdapter.setCurrPlayMusicId(musicId);
        }
        return i;
    }

    private void updateMediaPlayView(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        List<MusicInfoBean> list;
        long musicId;
        if (mediaInfo == null || (list = this.mMediaMusicBeanList) == null || list.size() <= 0) {
            return;
        }
        this.curPlayGorupId = mediaInfo.getGroupId();
        this.curPlayGorupType = mediaInfo.getMediaGroupType();
        MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
        if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && musicInfoBean != null && this.curPlayGorupId == this.groupId && this.curPlayGorupType == this.groupType) {
            musicId = musicInfoBean.getMusicId();
            if (type == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
                updateMusicView(musicInfoBean, false);
            } else if (type == MediaPlayerEnum.MediaInfoCallbackType.favorStatus && musicId == this.currPlayMediaMusic.getMusicId()) {
                this.currPlayMediaMusic.setCollection(musicInfoBean.isCollection());
                ImageView imageView = this.binding.leftContent.ivCollect;
                boolean zIsLogin = UserInfoUtil.isLogin();
                int i = R.drawable.ic_music_play_no_collect;
                if (zIsLogin && this.currPlayMediaMusic.isCollection()) {
                    i = R.drawable.ic_music_play_collect;
                }
                imageView.setImageResource(i);
            }
        } else {
            musicId = -1;
        }
        MusicListAdapter musicListAdapter = this.musicListAdapter;
        if (musicListAdapter != null) {
            long currPlayMusicId = musicListAdapter.getCurrPlayMusicId();
            int i2 = -1;
            for (int i3 = 0; i3 < this.mMediaMusicBeanList.size(); i3++) {
                MusicInfoBean musicInfoBean2 = this.mMediaMusicBeanList.get(i3);
                if (musicInfoBean2 != null && mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && musicInfoBean != null && musicInfoBean2.getMusicId() == musicId) {
                    if (type == MediaPlayerEnum.MediaInfoCallbackType.favorStatus) {
                        musicInfoBean2.setCollection(musicInfoBean.isCollection());
                        this.mMediaMusicBeanList.set(i3, musicInfoBean2);
                    }
                    i2 = i3;
                }
            }
            if (type == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
                if (currPlayMusicId != musicId) {
                    this.musicListAdapter.setCurrPlayMusicId(musicId);
                    this.musicListAdapter.notifyDataSetChanged();
                    return;
                }
                return;
            }
            if (type != MediaPlayerEnum.MediaInfoCallbackType.favorStatus) {
                if (type == MediaPlayerEnum.MediaInfoCallbackType.playMode) {
                    updatePlayMode();
                }
            } else if (i2 > -1) {
                this.musicListAdapter.setData(this.mMediaMusicBeanList);
                this.musicListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent event) {
        if (this.mPresenter != 0) {
            ((MusicPlayPresenter) this.mPresenter).requestMusicInfoData();
        }
        updateVipStatus();
        if (this.isNightDayModel) {
            this.isNightDayModel = false;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userInfoChangeEvent(UserInfoChangeEvent event) {
        updateVipStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVipStatus() {
        updateVipStatus(true);
    }

    private void updateVipStatus(boolean updateVipStatus) {
        int iRound;
        int iRound2;
        int duration;
        MusicInfoBean musicInfoBean = this.currPlayMediaMusic;
        if (musicInfoBean != null) {
            if (musicInfoBean.isVipMusic() && !UserInfoUtil.isVipAndUnexpired()) {
                if (this.currPlayMediaMusic.isFree()) {
                    this.binding.rightContent.btnVip.setVisibility(8);
                    this.binding.leftContent.seekbarMusicProgress.updateProgressDrawable(0, 0);
                    return;
                }
                long previewStartTime = this.currPlayMediaMusic.getPreviewStartTime();
                long previewEndTime = this.currPlayMediaMusic.getPreviewEndTime();
                if (getMediaPlayerService() == null || !updateVipStatus || (duration = getMediaPlayerService().getDuration()) <= 0 || previewEndTime > duration || previewEndTime <= previewStartTime) {
                    iRound = 0;
                    iRound2 = 0;
                } else {
                    float f = duration;
                    iRound2 = Math.round(((previewStartTime * 1000.0f) / f) * 100.0f);
                    iRound = Math.round(((previewEndTime * 1000.0f) / f) * 100.0f);
                }
                this.binding.rightContent.btnVip.setVisibility(0);
                this.binding.leftContent.seekbarMusicProgress.updateProgressDrawable(iRound2, iRound);
                return;
            }
            this.binding.rightContent.btnVip.setVisibility(8);
            this.binding.leftContent.seekbarMusicProgress.updateProgressDrawable(0, 0);
            return;
        }
        this.binding.rightContent.btnVip.setVisibility(8);
        this.binding.leftContent.seekbarMusicProgress.updateProgressDrawable(0, 0);
    }

    private void updatePlayView() {
        MediaPlayerEnum.CallBackState callBackState = MediaPlayerEnum.CallBackState.PAUSE;
        if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
            if (MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()) {
                if (MediaPlayEngine.getInstance().getMediaPlayerService().getPauseStatus() == MediaPlayerEnum.PauseStatus.PAUSING) {
                    callBackState = MediaPlayerEnum.CallBackState.PAUSING;
                } else {
                    callBackState = MediaPlayerEnum.CallBackState.STARTED;
                }
            } else if (MediaPlayEngine.getInstance().getMediaPlayerService().getPreprareStatus() == MediaPlayerEnum.PreprareStatus.ONLY_PREPRAREING || MediaPlayEngine.getInstance().getMediaPlayerService().getPreprareStatus() == MediaPlayerEnum.PreprareStatus.PREPRAREING) {
                callBackState = MediaPlayerEnum.CallBackState.PREPARING;
            } else {
                callBackState = MediaPlayerEnum.CallBackState.PAUSE;
            }
        }
        MusicListAdapter musicListAdapter = this.musicListAdapter;
        if (musicListAdapter != null) {
            musicListAdapter.setCallBackState(callBackState);
            this.musicListAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnCacheProgressCallbackListener
    public void onCacheProgressCallbackNext(int progress) {
        super.onCacheProgressCallbackNext(progress);
        if (this.binding.leftContent.seekbarMusicProgress != null) {
            this.binding.leftContent.seekbarMusicProgress.setSecondaryProgress(progress);
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        stopLrcSoughtWait();
        if (getMediaPlayerService() != null) {
            getMediaPlayerService().removeOnUpdateAppLrcCallbackListener(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resourceNotExist(ResourceNotExistEvent event) {
        long mediaId = event.getMediaId();
        if (getMediaPlayerService() == null || getMediaPlayerService().getCurMediaInfo() == null || getMediaPlayerService().getCurMediaInfo().getMusicInfoBean().getMusicId() != mediaId || this.mMediaMusicBeanList.size() <= 1) {
            return;
        }
        getMediaPlayerService().listLoopPlay();
        List<MediaInfo> mediaPlayList = MediaPlayerManager.getInstance().getMediaPlayList();
        List<MediaInfo> mediaRandomList = MediaPlayerManager.getInstance().getMediaRandomList();
        MediaInfo mediaInfo = null;
        MediaInfo mediaInfo2 = null;
        for (int i = 0; i < mediaPlayList.size(); i++) {
            if (mediaPlayList.get(i).getMusicInfoBean().getMusicId() == mediaId) {
                mediaInfo2 = mediaPlayList.get(i);
            }
        }
        if (mediaInfo2 != null) {
            mediaPlayList.remove(mediaInfo2);
        }
        for (int i2 = 0; i2 < mediaRandomList.size(); i2++) {
            if (mediaRandomList.get(i2).getMusicInfoBean().getMusicId() == mediaId) {
                mediaInfo = mediaRandomList.get(i2);
            }
        }
        if (mediaInfo != null) {
            mediaRandomList.remove(mediaInfo);
        }
        int currentMediaIndex = MediaPlayerManager.getInstance().getCurrentMediaIndex();
        if (currentMediaIndex != 0) {
            MediaPlayerManager.getInstance().updateCurrentIndex(currentMediaIndex - 1);
        }
        if (getMediaPlayerService().getPlayMode(MediaPlayerEnum.MediaType.Music) == MediaPlayerEnum.PlayMode.randomplay && this.mPresenter != 0) {
            this.musicListAdapter.setData(((MusicPlayPresenter) this.mPresenter).fromMediaInfoToMediaMusicBeanList(mediaRandomList));
        } else {
            this.musicListAdapter.setData(((MusicPlayPresenter) this.mPresenter).fromMediaInfoToMediaMusicBeanList(mediaPlayList));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListDataExpired(DailyExpiredEvent event) {
        Log.i(WanosBaseActivity.TAG, "list expired, reload and play");
        if (this.mPresenter != 0) {
            ((MusicPlayPresenter) this.mPresenter).requestMusicInfoData();
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        return event.getPointerCount() == 2;
    }
}
