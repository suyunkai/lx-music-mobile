package com.wanos.media.ui.audiobook.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.databinding.ActivityAudioBookAlbumBinding;
import com.wanos.media.presenter.AudioBookAlbumPresenter;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.AnitPlayClick;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanos.wanosplayermodule.MediaStatistic;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookAlbumActivity extends AudioBookAlbumBaseActivity {
    protected ActivityAudioBookAlbumBinding binding;
    protected boolean isAlbumPlaying = false;

    public static void buildAlbumPage(Context context, long id, boolean isResult) {
        Intent intent = new Intent(context, (Class<?>) AudioBookAlbumActivity.class);
        intent.putExtra(strFromBar, false);
        intent.putExtra(strID, id);
        intent.putExtra(strPlayPage, false);
        if (isResult) {
            ((Activity) context).startActivityForResult(intent, REQUEST_CODE_BACK);
        } else {
            context.startActivity(intent);
        }
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    protected void bindingLayout() {
        ActivityAudioBookAlbumBinding activityAudioBookAlbumBindingInflate = ActivityAudioBookAlbumBinding.inflate(getLayoutInflater());
        this.binding = activityAudioBookAlbumBindingInflate;
        setContentView(activityAudioBookAlbumBindingInflate.getRoot());
        this.chapterListView = this.binding.chapterList;
        this.refreshLayout = this.binding.audiobookRefreshLayout;
        this.emptyView = this.binding.emptyView;
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    public void getSchemeData(Uri data) {
        super.getSchemeData(data);
        this.isPlayPage = false;
        if (data.getBooleanQueryParameter("isAppIn", false)) {
            return;
        }
        MediaStatistic.getInstance().saveRecordWidgetMeidaAudioBookAlbum(this.albumId);
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity, com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        if (this.mPresenter != 0) {
            ((AudioBookAlbumPresenter) this.mPresenter).requestData(this.albumId, false);
        }
        super.onResume();
        setCallBackState();
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    public void initView() {
        super.initView();
        Util.setTextWeight(this.binding.tvPlay, 500);
        setTitleBarVisibility(8);
        initVisibleIsSpoken();
        this.binding.albumPage.setVisibility(0);
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.btnBack.setContentDescription(getResources().getString(R.string.close_back_click));
            this.binding.btnCollect.setContentDescription(getResources().getString(R.string.collect_audiobook_album));
            ViewCompat.setAccessibilityDelegate(this.binding.btnCollect, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumActivity.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View host, int action, Bundle args) {
                    if (action == 1013 && AudioBookAlbumActivity.this.audioBookAlbumInfoBean.getIsCollect() != 1) {
                        AudioBookAlbumActivity.this.collectAlbum();
                    }
                    if (action == 1014 && AudioBookAlbumActivity.this.audioBookAlbumInfoBean.getIsCollect() == 1) {
                        AudioBookAlbumActivity.this.collectAlbum();
                    }
                    return super.performAccessibilityAction(host, action, args);
                }
            });
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
                this.binding.ivPayState.setImageResource(R.drawable.ab_list_vip);
            } else {
                this.binding.ivPayState.setVisibility(8);
            }
            this.binding.ivAlbumState.setVisibility(0);
            if (this.audioBookAlbumInfoBean.getWritingStatus() == 1) {
                this.binding.ivAlbumState.setImageResource(R.drawable.ab_album_serialization);
            } else {
                this.binding.ivAlbumState.setImageResource(R.drawable.ab_album_end);
            }
            GlideUtil.setImageData(SizeMode.PLAY_LIST, this.audioBookAlbumInfoBean.getAvatar(), this.binding.imCover);
            updatePlayState();
            updateCollect();
        }
    }

    public void updatePlayState() {
        if ((getMediaPlayerService() == null || getMediaPlayerService().getCurMediaInfo() == null || getMediaPlayerService().getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Audiobook || getMediaPlayerService().getCurMediaInfo().getAudioBookInfoBean().getRadioId() != this.albumId) ? false : getMediaPlayerService().isPlaying()) {
            this.isAlbumPlaying = true;
            this.binding.ivPlay.setImageResource(R.drawable.ic_play_all);
            this.binding.tvPlay.setText(R.string.pause_all);
        } else {
            this.isAlbumPlaying = false;
            this.binding.ivPlay.setImageResource(R.drawable.ic_pause_all);
            this.binding.tvPlay.setText(R.string.play_all);
        }
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    public void updateCollect() {
        if (this.audioBookAlbumInfoBean.getIsCollect() == 1) {
            this.binding.ivCollect.setImageResource(R.drawable.ic_collect);
        } else {
            this.binding.ivCollect.setImageResource(R.drawable.ic_no_collect);
        }
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity
    public void initListener() {
        super.initListener();
        this.binding.btnPlay.setOnClickListener(this);
        this.binding.btnCollect.setOnClickListener(new AnitClick(800L) { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumActivity.2
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View v) {
                AudioBookAlbumActivity.this.collectAlbum();
            }
        });
        this.binding.btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AudioBookAlbumActivity.this.onBackPressed();
            }
        });
        this.binding.btnPlay.setOnClickListener(new AnitPlayClick(500L) { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumActivity.4
            @Override // com.wanos.media.util.AnitPlayClick
            public void onAnitClick(View v) {
                MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
                if (AudioBookAlbumActivity.this.isAlbumPlaying) {
                    if (mediaPlayerService != null) {
                        mediaPlayerService.pause();
                        return;
                    }
                    return;
                }
                if (AudioBookAlbumActivity.this.curPlayChapterId == -1 && AudioBookAlbumActivity.this.chapterBeanList.size() >= 1) {
                    AudioBookAlbumActivity audioBookAlbumActivity = AudioBookAlbumActivity.this;
                    audioBookAlbumActivity.curPlayChapterId = audioBookAlbumActivity.chapterBeanList.get(0).getId();
                    AudioBookAlbumActivity.this.audioBookChapterListAdapter.setPlayChapterId(AudioBookAlbumActivity.this.curPlayChapterId);
                    AudioBookAlbumActivity.this.audioBookChapterListAdapter.notifyDataSetChanged();
                }
                AudioBookAlbumActivity.this.isNightDayModel = false;
                AudioBookAlbumActivity.this.initPlayer();
            }
        });
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        view.getId();
        MediaPlayEngine.getInstance().getMediaPlayerService();
    }

    public void playAll() {
        if (this.chapterBeanList.size() >= 1 && this.curPlayChapterId == -1) {
            this.curPlayChapterId = this.chapterBeanList.get(0).getId();
            this.audioBookChapterListAdapter.setPlayChapterId(this.curPlayChapterId);
            this.audioBookChapterListAdapter.notifyDataSetChanged();
        }
        showPlayActivity();
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity, com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && type == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
            setCallBackState();
        }
    }

    @Override // com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity, com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        if (getMediaPlayerService() == null || getMediaPlayerService().getCurMediaInfo() == null || getMediaPlayerService().getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
            this.isAlbumPlaying = false;
            this.binding.ivPlay.setImageResource(R.drawable.ic_pause_all);
            this.binding.tvPlay.setText(R.string.play_all);
            return;
        }
        if (getMediaPlayerService().getCurMediaInfo().getAudioBookInfoBean().getRadioId() == this.albumId) {
            if (status == MediaPlayerEnum.CallBackState.STARTED) {
                this.isAlbumPlaying = true;
                this.binding.ivPlay.setImageResource(R.drawable.ic_play_all);
                this.binding.tvPlay.setText(R.string.pause_all);
                return;
            } else {
                if (status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.COMPLETE) {
                    this.isAlbumPlaying = false;
                    this.binding.ivPlay.setImageResource(R.drawable.ic_pause_all);
                    this.binding.tvPlay.setText(R.string.play_all);
                    return;
                }
                return;
            }
        }
        this.isAlbumPlaying = false;
        this.binding.ivPlay.setImageResource(R.drawable.ic_pause_all);
        this.binding.tvPlay.setText(R.string.play_all);
    }

    public void setCallBackState() {
        AudioBookMineChapterItemBean playingItemBean = AudioBookGlobalParams.getPlayingItemBean();
        this.audioBookChapterListAdapter.setPlayChapterId(playingItemBean != null ? playingItemBean.getId() : -1L);
        this.audioBookChapterListAdapter.notifyDataSetChanged();
    }
}
