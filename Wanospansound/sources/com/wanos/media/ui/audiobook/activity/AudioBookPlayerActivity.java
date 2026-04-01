package com.wanos.media.ui.audiobook.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import androidx.media3.common.C;
import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.ActivityAudioBookPlayerBinding;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.dialog.PlayerSpeedDialog;
import com.wanos.media.ui.login.activity.MemberPayActivity;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.AnitPlayClick;
import com.wanos.media.util.AppManager;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanos.wanosplayermodule.MediaStatistic;
import com.wanos.wanosplayermodule.util.MediaSharedPreferUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookPlayerActivity extends AudioBookAlbumBaseActivity {
    public static final String TAG = "wanos:[AudioBookPlayerActivity]";
    protected ActivityAudioBookPlayerBinding binding;
    protected boolean touchProgressBar = false;

    public static void buildPlayPage(Context context, long id, long playChapterId, int playChapterIndex, boolean isResult) {
        Intent intent = new Intent(context, (Class<?>) AudioBookPlayerActivity.class);
        intent.putExtra(strFromBar, false);
        intent.putExtra(strID, id);
        intent.putExtra(strPlayPage, true);
        intent.putExtra(strPlayChapterId, playChapterId);
        intent.putExtra(strPlayChapterIndex, playChapterIndex);
        if (isResult) {
            ((Activity) context).startActivityForResult(intent, REQUEST_CODE_BACK);
        } else {
            context.startActivity(intent);
        }
    }

    public static void buildPlayPageFromBar(Context context) {
        Intent intent = new Intent(context, (Class<?>) AudioBookPlayerActivity.class);
        intent.putExtra(strFromBar, true);
        context.startActivity(intent);
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    protected void bindingLayout() {
        ActivityAudioBookPlayerBinding activityAudioBookPlayerBindingInflate = ActivityAudioBookPlayerBinding.inflate(getLayoutInflater());
        this.binding = activityAudioBookPlayerBindingInflate;
        setContentView(activityAudioBookPlayerBindingInflate.getRoot());
        this.chapterListView = this.binding.chapterList;
        this.refreshLayout = this.binding.audiobookRefreshLayout;
        this.emptyView = this.binding.emptyView;
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity, com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        Log.e(TAG, "onMediaInfoCallbackAppNext: " + mediaInfo.getMediaType() + ",当前页面=" + getClass().getSimpleName());
        if (mediaInfo != null && mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
            onBackPressed();
        } else {
            super.onMediaInfoCallbackAppNext(type, mediaInfo);
        }
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    public void getSchemeData(Uri data) {
        super.getSchemeData(data);
        String queryParameter = data.getQueryParameter(strSchemeChapterId);
        String queryParameter2 = data.getQueryParameter(strSchemeChapterIndex);
        try {
            this.curPlayChapterId = Long.parseLong(queryParameter);
            this.curPlayChapterIndex = Integer.parseInt(queryParameter2);
            if (!data.getBooleanQueryParameter("isAppIn", false)) {
                MediaStatistic.getInstance().saveRecordWidgetMeidaAudioBookBookChapter(this.curPlayChapterId);
            }
        } catch (Exception unused) {
        }
        this.isPlayPage = true;
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    public void initView() {
        super.initView();
        setTitleBarVisibility(8);
        this.playModelType = MediaSharedPreferUtils.getAudioBookMode();
        updatePlayModeView();
        setPlayBarVisibility(8);
        this.binding.playPage.setVisibility(0);
        updatePlaySpeed();
        updatePlayStateUI();
        initVisibleIsSpoken();
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.btnBack.setContentDescription(getResources().getString(R.string.close_back_click));
            this.binding.ivChapterSkipPre.setContentDescription(getResources().getString(R.string.play_audiobook_last));
            this.binding.ivChapterSkipNext.setContentDescription(getResources().getString(R.string.play_audiobook_next));
            this.binding.ivChapterPre15.setContentDescription(getResources().getString(R.string.play_audiobook_pre_15));
            this.binding.ivChapterNext15.setContentDescription(getResources().getString(R.string.play_audiobook_next_15));
            this.binding.ivChapterSort.setContentDescription(getResources().getString(R.string.play_music_type));
            this.binding.chapterList.setContentDescription(getResources().getString(R.string.scroll_current_list));
        }
    }

    protected void updatePlaySpeed() {
        float doubleSpeed = MediaSharedPreferUtils.getDoubleSpeed();
        if (doubleSpeed == 0.75f) {
            this.binding.ivChapterSpeed.setImageResource(R.drawable.ab_speed_0);
            return;
        }
        if (doubleSpeed == 1.0f) {
            this.binding.ivChapterSpeed.setImageResource(R.drawable.ab_speed_1);
            return;
        }
        if (doubleSpeed == 1.25f) {
            this.binding.ivChapterSpeed.setImageResource(R.drawable.ab_speed_2);
            return;
        }
        if (doubleSpeed == 1.5f) {
            this.binding.ivChapterSpeed.setImageResource(R.drawable.ab_speed_3);
        } else if (doubleSpeed == 1.75f) {
            this.binding.ivChapterSpeed.setImageResource(R.drawable.ab_speed_4);
        } else if (doubleSpeed == 2.0f) {
            this.binding.ivChapterSpeed.setImageResource(R.drawable.ab_speed_5);
        }
    }

    public void updatePlayModeView() {
        if (this.playModelType == MediaPlayerEnum.PlayMode.orderplay) {
            this.binding.ivChapterSort.setImageResource(R.drawable.play_type_list);
            return;
        }
        if (this.playModelType == MediaPlayerEnum.PlayMode.randomplay) {
            this.binding.ivChapterSort.setImageResource(R.drawable.play_type_radom);
        } else if (this.playModelType == MediaPlayerEnum.PlayMode.singleloopplay) {
            this.binding.ivChapterSort.setImageResource(R.drawable.play_type_single);
        } else if (this.playModelType == MediaPlayerEnum.PlayMode.listloopplay) {
            this.binding.ivChapterSort.setImageResource(R.drawable.play_type_loop);
        }
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    protected void updateLeftView() {
        if (this.audioBookAlbumInfoBean != null) {
            Util.setTextWeight(this.binding.tvAlbumName, 500);
            this.binding.tvAlbumName.setText(this.audioBookAlbumInfoBean.getName());
            if (this.audioBookAlbumInfoBean.getSpeaker() != null) {
                this.binding.tvAudioBookAuthorName.setText(this.audioBookAlbumInfoBean.getSpeaker().getName());
            }
            if (this.audioBookAlbumInfoBean.getIsPay() == 1) {
                this.binding.ivPayState.setImageResource(R.drawable.ab_list_pay);
            } else if (this.audioBookAlbumInfoBean.getIsVip() == 1) {
                this.binding.ivPayState.setImageResource(R.drawable.ab_chapter_vip);
            } else {
                this.binding.ivPayState.setVisibility(8);
            }
            GlideUtil.setImageData(this.audioBookAlbumInfoBean.getAvatar(), this.binding.imCover, 200, 200);
            this.binding.ivAlbumState.setVisibility(0);
            if (this.audioBookAlbumInfoBean.getWritingStatus() == 1) {
                this.binding.ivAlbumState.setImageResource(R.drawable.ab_album_serialization);
            } else {
                this.binding.ivAlbumState.setImageResource(R.drawable.ab_album_end);
            }
            updateCollect();
            updateProgressView(0, true);
        }
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    public void initListener() {
        super.initListener();
        long j = 800;
        this.binding.ivCollect.setOnClickListener(new AnitClick(j) { // from class: com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View v) {
                AudioBookPlayerActivity.this.collectAlbum();
            }
        });
        this.binding.ivChapterPre15.setOnClickListener(this);
        this.binding.ivChapterSkipPre.setOnClickListener(this);
        this.binding.ivChapterPlay.setOnClickListener(this);
        this.binding.ivChapterSkipNext.setOnClickListener(this);
        this.binding.ivChapterNext15.setOnClickListener(this);
        this.binding.ivChapterSort.setOnClickListener(this);
        this.binding.ivChapterSpeed.setOnClickListener(this);
        this.binding.seekbarAudioProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean b2) {
                if (!b2 || AudioBookPlayerActivity.this.getMediaPlayerService() == null) {
                    return;
                }
                AudioBookPlayerActivity.this.binding.tvPlayTime.setText(AudioBookPlayerActivity.this.stringForTime((long) (r0.getMediaPlayerService().getDuration() * ((i * 1.0f) / seekBar.getMax()))));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                AudioBookPlayerActivity.this.touchProgressBar = true;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                AudioBookPlayerActivity.this.touchProgressBar = false;
                if (AudioBookPlayerActivity.this.getMediaPlayerService() == null || AudioBookPlayerActivity.this.submitVIP()) {
                    return;
                }
                AudioBookPlayerActivity.this.getMediaPlayerService().seekTo((((long) AudioBookPlayerActivity.this.getMediaPlayerService().getDuration()) * ((long) seekBar.getProgress())) / ((long) seekBar.getMax()), 3);
                if (AudioBookPlayerActivity.this.getMediaPlayerService().isPlaying()) {
                    return;
                }
                AudioBookPlayerActivity.this.isNightDayModel = false;
                AudioBookPlayerActivity.this.getMediaPlayerService().start();
            }
        });
        this.binding.btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AudioBookPlayerActivity.this.onBackPressed();
            }
        });
        this.binding.ivChapterPre15.setOnClickListener(new AnitClick(j) { // from class: com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity.4
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View v) {
                MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
                if (mediaPlayerService != null) {
                    long currentPosition = ((long) mediaPlayerService.getCurrentPosition()) - C.DEFAULT_SEEK_FORWARD_INCREMENT_MS;
                    if (currentPosition < 0) {
                        if (mediaPlayerService != null) {
                            AudioBookPlayerActivity.this.binding.seekbarAudioProgress.setProgress(0);
                            mediaPlayerService.seekTo(0L);
                        }
                    } else if (mediaPlayerService != null) {
                        if (mediaPlayerService.getDuration() != -1 && mediaPlayerService.getDuration() > 0) {
                            AudioBookPlayerActivity.this.binding.seekbarAudioProgress.setProgress((int) ((((long) AudioBookPlayerActivity.this.binding.seekbarAudioProgress.getMax()) * currentPosition) / ((long) mediaPlayerService.getDuration())));
                        }
                        mediaPlayerService.seekTo(currentPosition);
                    }
                    if (AudioBookPlayerActivity.this.getMediaPlayerService().isPlaying()) {
                        return;
                    }
                    AudioBookPlayerActivity.this.isNightDayModel = false;
                    AudioBookPlayerActivity.this.getMediaPlayerService().start();
                }
            }
        });
        this.binding.ivChapterNext15.setOnClickListener(new AnitClick(j) { // from class: com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity.5
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View v) {
                MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
                MediaInfo curMediaInfo = mediaPlayerService.getCurMediaInfo();
                if (curMediaInfo != null && curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
                    AudioBookMineChapterItemBean audioBookInfoBean = curMediaInfo.getAudioBookInfoBean();
                    Activity activityCurrentActivity = AppManager.getAppManager().currentActivity();
                    if (audioBookInfoBean != null && audioBookInfoBean.getIsVip() == 1 && !UserInfoUtil.isVipAndUnexpired() && !(activityCurrentActivity instanceof MemberPayActivity) && (activityCurrentActivity instanceof WanosBaseActivity)) {
                        ((WanosBaseActivity) activityCurrentActivity).openWeChatPayActivity();
                        return;
                    }
                }
                if (mediaPlayerService == null || mediaPlayerService.getDuration() <= 0) {
                    return;
                }
                long currentPosition = ((long) mediaPlayerService.getCurrentPosition()) + C.DEFAULT_SEEK_FORWARD_INCREMENT_MS;
                if (currentPosition >= mediaPlayerService.getDuration()) {
                    if (AudioBookPlayerActivity.this.playModelType != MediaPlayerEnum.PlayMode.singleloopplay) {
                        if (AudioBookPlayerActivity.this.playChange(true) && mediaPlayerService != null) {
                            mediaPlayerService.playNext();
                        }
                    } else if (mediaPlayerService != null) {
                        AudioBookPlayerActivity.this.binding.seekbarAudioProgress.setProgress(0);
                        mediaPlayerService.seekTo(0L);
                    }
                } else if (mediaPlayerService != null) {
                    mediaPlayerService.seekTo(currentPosition);
                    AudioBookPlayerActivity.this.updateProgressView(0, true);
                }
                if (AudioBookPlayerActivity.this.getMediaPlayerService().isPlaying()) {
                    return;
                }
                AudioBookPlayerActivity.this.isNightDayModel = false;
                AudioBookPlayerActivity.this.getMediaPlayerService().start();
            }
        });
        this.binding.ivChapterPlay.setOnClickListener(new AnitPlayClick(500L) { // from class: com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity.6
            @Override // com.wanos.media.util.AnitPlayClick
            public void onAnitClick(View v) {
                MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
                if (mediaPlayerService != null) {
                    if (mediaPlayerService.isPlaying()) {
                        mediaPlayerService.pause();
                        return;
                    }
                    for (int i = 0; i < AudioBookPlayerActivity.this.chapterBeanList.size(); i++) {
                        AudioBookChapterItemBean audioBookChapterItemBean = AudioBookPlayerActivity.this.chapterBeanList.get(i);
                        if (audioBookChapterItemBean.getId() == AudioBookPlayerActivity.this.curPlayChapterId && audioBookChapterItemBean.getIsVip() == 1 && !UserInfoUtil.isVipAndUnexpired()) {
                            ToastUtil.showMsg(R.string.audiobook_vip_toast);
                            AudioBookPlayerActivity.this.openWeChatPayActivity();
                            return;
                        }
                    }
                    AudioBookPlayerActivity.this.isNightDayModel = false;
                    mediaPlayerService.start();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean submitVIP() {
        for (int i = 0; i < this.chapterBeanList.size(); i++) {
            AudioBookChapterItemBean audioBookChapterItemBean = this.chapterBeanList.get(i);
            if (audioBookChapterItemBean.getId() == this.curPlayChapterId && audioBookChapterItemBean.getIsVip() == 1 && !UserInfoUtil.isVipAndUnexpired()) {
                ToastUtil.showMsg(R.string.audiobook_vip_toast);
                openWeChatPayActivity();
                return true;
            }
        }
        return false;
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    public void updateCollect() {
        if (this.audioBookAlbumInfoBean.getIsCollect() == 1) {
            this.binding.ivCollect.setImageResource(R.drawable.ic_collect_80);
        } else {
            this.binding.ivCollect.setImageResource(R.drawable.ic_no_collect_80);
        }
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    protected void updateChapterCollect() {
        this.binding.seekbarAudioProgress.setProgress(0);
        this.binding.seekbarAudioProgress.setSecondaryProgress(0);
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    protected void updateChapterDuration(long t) {
        this.binding.tvDuration.setText(stringForTime(t * 1000));
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    protected void updatePlayMode() {
        this.playModelType = MediaSharedPreferUtils.getAudioBookMode();
        updatePlayModeView();
    }

    public void updatePlayStateUI() {
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        if (mediaPlayerService != null) {
            if (mediaPlayerService.getCurMediaInfo() != null && mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean() != null && mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean().getRadioId() != this.albumId) {
                this.binding.ivChapterPlayState.setVisibility(0);
                this.binding.ivChapterPlayState.setImageResource(R.drawable.ic_music_play_pause);
                return;
            }
            MediaPlayerEnum.CallBackState playingStatusV2 = AudioBookGlobalParams.getPlayingStatusV2();
            if (mediaPlayerService.isPlaying()) {
                if (MediaPlayEngine.getInstance().getMediaPlayerService().getPauseStatus() == MediaPlayerEnum.PauseStatus.PAUSING) {
                    this.binding.ivChapterPlay.setEnabled(true);
                    this.binding.ivChapterPlayState.setImageResource(R.drawable.ic_music_play_pause);
                } else {
                    this.binding.ivChapterPlay.setEnabled(true);
                    this.binding.ivChapterPlayState.setImageResource(R.drawable.ic_music_play_playing);
                }
            } else if (playingStatusV2 == MediaPlayerEnum.CallBackState.PREPARING) {
                this.binding.ivChapterPlay.setEnabled(false);
            } else {
                this.binding.ivChapterPlay.setEnabled(true);
                this.binding.ivChapterPlayState.setImageResource(R.drawable.ic_music_play_pause);
            }
            if (playingStatusV2 == MediaPlayerEnum.CallBackState.PREPARING || playingStatusV2 == MediaPlayerEnum.CallBackState.PREPARE) {
                this.binding.pbAudiobookPlay.setVisibility(0);
                this.binding.ivChapterPlayState.setVisibility(8);
                return;
            } else if (playingStatusV2 != MediaPlayerEnum.CallBackState.PAUSE && playingStatusV2 != MediaPlayerEnum.CallBackState.STARTED) {
                this.binding.pbAudiobookPlay.setVisibility(8);
                this.binding.ivChapterPlayState.setVisibility(0);
                return;
            } else {
                if (playingStatusV2 == MediaPlayerEnum.CallBackState.PAUSE) {
                    this.binding.pbAudiobookPlay.setVisibility(8);
                    this.binding.ivChapterPlayState.setVisibility(0);
                    return;
                }
                return;
            }
        }
        this.binding.ivChapterPlay.setEnabled(true);
        this.binding.pbAudiobookPlay.setVisibility(8);
        this.binding.ivChapterPlayState.setVisibility(0);
        this.binding.ivChapterPlayState.setImageResource(R.drawable.ic_music_play_pause);
    }

    public void showPlayerSpeedDialog() {
        new PlayerSpeedDialog(this, new PlayerSpeedDialog.OnStateListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity.7
            @Override // com.wanos.media.ui.audiobook.dialog.PlayerSpeedDialog.OnStateListener
            public void onChange() {
                AudioBookPlayerActivity.this.updatePlaySpeed();
            }
        }).show();
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        switch (id) {
            case R.id.iv_chapter_skip_next /* 2131362415 */:
                if (playChange(true) && mediaPlayerService != null) {
                    mediaPlayerService.playNext();
                    break;
                }
                break;
            case R.id.iv_chapter_skip_pre /* 2131362416 */:
                if (playChange(false) && mediaPlayerService != null) {
                    mediaPlayerService.playPre();
                    break;
                }
                break;
            case R.id.iv_chapter_sort /* 2131362417 */:
                if (this.playModelType == MediaPlayerEnum.PlayMode.listloopplay) {
                    this.playModelType = MediaPlayerEnum.PlayMode.randomplay;
                    ToastUtil.showMsg(R.string.music_random_play);
                } else if (this.playModelType == MediaPlayerEnum.PlayMode.randomplay) {
                    this.playModelType = MediaPlayerEnum.PlayMode.singleloopplay;
                    ToastUtil.showMsg(R.string.music_single_loop_play);
                } else if (this.playModelType == MediaPlayerEnum.PlayMode.singleloopplay) {
                    this.playModelType = MediaPlayerEnum.PlayMode.listloopplay;
                    ToastUtil.showMsg(R.string.music_list_loop_play);
                }
                updatePlayModeView();
                getMediaPlayerService().setPlayMode(MediaPlayerEnum.MediaType.Audiobook, this.playModelType);
                break;
            case R.id.iv_chapter_speed /* 2131362418 */:
                showPlayerSpeedDialog();
                break;
        }
    }

    public boolean playChange(boolean isNext) {
        if (isNext) {
            for (int i = 0; i < this.chapterBeanList.size(); i++) {
                if (this.chapterBeanList.get(i).getId() == this.curPlayChapterId) {
                    int i2 = i + 1;
                    if (i2 >= this.chapterBeanList.size() || this.chapterBeanList.get(i2).getIsVip() != 1 || UserInfoUtil.isVipAndUnexpired()) {
                        return true;
                    }
                    openWeChatPayActivity();
                    return false;
                }
            }
        } else {
            for (int i3 = 0; i3 < this.chapterBeanList.size(); i3++) {
                if (this.chapterBeanList.get(i3).getId() == this.curPlayChapterId) {
                    int i4 = i3 - 1;
                    if (i4 < 0 || this.chapterBeanList.get(i4).getIsVip() != 1 || UserInfoUtil.isVipAndUnexpired()) {
                        return true;
                    }
                    openWeChatPayActivity();
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity, com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        if (this.isPlayPage) {
            if (getMediaPlayerService() != null && getMediaPlayerService().getCurMediaInfo() != null) {
                MediaInfo curMediaInfo = getMediaPlayerService().getCurMediaInfo();
                if (curMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
                    Log.i(TAG, "onStatusonStatusCallbackNext: 111====");
                    return;
                } else if (curMediaInfo.getAudioBookInfoBean() != null && curMediaInfo.getAudioBookInfoBean().getRadioId() != this.albumId) {
                    Log.i(TAG, "onStatusonStatusCallbackNext: return ====");
                    this.binding.seekbarAudioProgress.setProgress(0);
                    return;
                }
            }
            if (status == MediaPlayerEnum.CallBackState.PREPARING) {
                this.touchProgressBar = false;
                updatePlayStateUI();
                updateProgressView(0, false);
                return;
            }
            if (status == MediaPlayerEnum.CallBackState.PREPARE) {
                updatePlayStateUI();
                updateProgressView(0, true);
                return;
            }
            if (status == MediaPlayerEnum.CallBackState.STARTED) {
                updatePlayStateUI();
                return;
            }
            if (status == MediaPlayerEnum.CallBackState.PAUSING) {
                updatePlayStateUI();
                return;
            }
            if (status == MediaPlayerEnum.CallBackState.PAUSE) {
                updatePlayStateUI();
                return;
            }
            if (status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION) {
                updatePlayStateUI();
                return;
            }
            if (status == MediaPlayerEnum.CallBackState.COMPLETE) {
                updatePlayStateUI();
                return;
            }
            if (status == MediaPlayerEnum.CallBackState.SEEK_COMPLETE) {
                updateProgressView(0, true);
                return;
            }
            if (status == MediaPlayerEnum.CallBackState.PROGRESS) {
                int iIntValue = ((Integer) args[0]).intValue();
                boolean zBooleanValue = ((Boolean) args[1]).booleanValue();
                this.binding.ivChapterPlayState.setVisibility(zBooleanValue ? 8 : 0);
                this.binding.pbAudiobookPlay.setVisibility(zBooleanValue ? 0 : 8);
                this.binding.ivChapterPlay.setEnabled(!zBooleanValue);
                if (this.touchProgressBar) {
                    return;
                }
                updateProgressView(iIntValue, true);
            }
        }
    }

    protected void updateProgressView(int progress, boolean isPrepared) {
        int duration;
        int i = 0;
        if (getMediaPlayerService() != null) {
            int currentPosition = getMediaPlayerService().getCurrentPosition();
            duration = MediaPlayEngine.getInstance().getMediaPlayerService().getDuration();
            if (getMediaPlayerService() != null && getMediaPlayerService().getCurMediaInfo() != null) {
                MediaInfo curMediaInfo = getMediaPlayerService().getCurMediaInfo();
                if (curMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
                    Log.i(TAG, "updateProgressView: 111====");
                    return;
                } else if (curMediaInfo.getAudioBookInfoBean() != null && (curMediaInfo.getAudioBookInfoBean().getRadioId() != this.albumId || curMediaInfo.getAudioBookInfoBean().getId() != this.curPlayChapterId)) {
                    Log.i(TAG, "updateProgressView: return ====");
                    return;
                }
            }
            if (duration == 0) {
                this.binding.seekbarAudioProgress.setProgress(0);
            } else {
                this.binding.seekbarAudioProgress.setProgress(Math.round(this.binding.seekbarAudioProgress.getMax() * ((currentPosition * 1.0f) / duration)));
            }
            if (getMediaPlayerService().getCurMediaInfo() != null && getMediaPlayerService().getCurMediaInfo().getAudioBookInfoBean() != null && getMediaPlayerService().getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
                duration = (int) (getMediaPlayerService().getCurMediaInfo().getAudioBookInfoBean().getDuration() * 1000);
            }
            i = currentPosition;
        } else {
            duration = 0;
        }
        String strStringForTime = stringForTime(isPrepared ? i : 0L);
        String strStringForTime2 = stringForTime(duration);
        this.binding.tvPlayTime.setText(strStringForTime);
        if (isPrepared) {
            this.binding.tvDuration.setText(strStringForTime2);
        }
        this.binding.seekbarAudioProgress.setClickable(isPrepared);
        this.binding.seekbarAudioProgress.setEnabled(isPrepared);
        this.binding.seekbarAudioProgress.setSelected(isPrepared);
        this.binding.seekbarAudioProgress.setFocusable(isPrepared);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnCacheProgressCallbackListener
    public void onCacheProgressCallbackNext(int progress) {
        super.onCacheProgressCallbackNext(progress);
        if (getMediaPlayerService() != null && getMediaPlayerService().getCurMediaInfo() != null) {
            MediaInfo curMediaInfo = getMediaPlayerService().getCurMediaInfo();
            if (curMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
                Log.i(TAG, "updateProgressView: 111====");
                this.binding.seekbarAudioProgress.setSecondaryProgress(0);
                return;
            } else if (curMediaInfo.getAudioBookInfoBean() != null && (curMediaInfo.getAudioBookInfoBean().getRadioId() != this.albumId || curMediaInfo.getAudioBookInfoBean().getId() != this.curPlayChapterId)) {
                Log.i(TAG, "updateProgressView: return ====");
                this.binding.seekbarAudioProgress.setSecondaryProgress(0);
                return;
            }
        }
        if (this.binding.seekbarAudioProgress != null) {
            this.binding.seekbarAudioProgress.setSecondaryProgress(progress * 10);
        }
    }
}
