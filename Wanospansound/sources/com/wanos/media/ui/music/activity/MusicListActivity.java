package com.wanos.media.ui.music.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Priority;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.FreeGroupInfo;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.event.DailyExpiredEvent;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.ActivityMusicListBinding;
import com.wanos.media.presenter.MusicListPresenter;
import com.wanos.media.ui.music.adapter.MusicListAdapter;
import com.wanos.media.ui.widget.banner.util.BannerUtils;
import com.wanos.media.util.AnitPlayClick;
import com.wanos.media.view.MusicListView;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class MusicListActivity extends WanosBaseActivity<MusicListPresenter> implements MusicListView, View.OnClickListener {
    public static final String FROM_WIDGET = "from_widget";
    public static final String MUSIC_GROUP_ID_KEY = "groupId";
    public static final String TAG = "wanos:[MusicListActivity]";
    private ActivityMusicListBinding binding;
    private long groupId;
    private long groupType;
    private List<MusicInfoBean> mMediaMusicBeanList;
    private MusicGroupInfo mMediaMusicGroupBean;
    private MusicListAdapter musicListAdapter;
    private Parcelable rvState;
    private long curPlayGorupId = -100;
    private long curPlayGorupType = -100;
    private int page = 1;
    private boolean isFirstLoading = true;
    private boolean autoPlay = false;
    private String freeCover = "";
    private boolean isRecreate = false;
    boolean isLogin = false;

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Uri data;
        super.onCreate(savedInstanceState);
        boolean z = true;
        this.isRecreate = savedInstanceState != null;
        this.isLogin = UserInfoUtil.isLogin();
        Intent intent = getIntent();
        if (savedInstanceState == null) {
            this.groupId = intent.getLongExtra("groupId", -100L);
            Log.i(TAG, "onCreate1: groupId = " + this.groupId);
        } else {
            long j = savedInstanceState.getLong("groupId", 0L);
            if (j != 0) {
                this.groupId = j;
                Log.i(TAG, "onCreate2: groupId = " + this.groupId);
            } else {
                this.groupId = intent.getLongExtra("groupId", -100L);
                Log.i(TAG, "onCreate3: groupId = " + this.groupId);
            }
        }
        Log.i(TAG, "onCreate: groupId =" + this.groupId);
        this.autoPlay = intent.getBooleanExtra(FROM_WIDGET, false);
        if (!this.isFromDeepLinkStart && !intent.getBooleanExtra(FROM_WIDGET, false)) {
            z = false;
        }
        this.isFromDeepLinkStart = z;
        if (this.groupId == -100 && (data = intent.getData()) != null) {
            String queryParameter = data.getQueryParameter("groupId");
            boolean booleanQueryParameter = data.getBooleanQueryParameter("isAppIn", false);
            try {
                this.groupId = Long.valueOf(queryParameter).longValue();
                if (!booleanQueryParameter) {
                    MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_GROUP_PLAY, "" + this.groupId, "", "", "", 0);
                }
            } catch (Exception unused) {
                this.groupId = -5L;
            }
        }
        long j2 = this.groupId;
        if (j2 > -1) {
            this.groupType = -6L;
        } else {
            this.groupType = j2;
        }
        this.mPresenter = new MusicListPresenter(this, this.groupId);
        Log.i(TAG, "onCreate: mPresenter = " + this.mPresenter);
        ActivityMusicListBinding activityMusicListBindingInflate = ActivityMusicListBinding.inflate(getLayoutInflater());
        this.binding = activityMusicListBindingInflate;
        setContentView(activityMusicListBindingInflate.getRoot());
        iniView();
        initVisibleIsSpoken();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Parcelable parcelableOnSaveInstanceState = this.binding.listContent.recyclerviewMusicList.getLayoutManager().onSaveInstanceState();
        outState.putLong("groupId", this.groupId);
        outState.putParcelable("rvState", parcelableOnSaveInstanceState);
        super.onSaveInstanceState(outState);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.rvState = savedInstanceState.getParcelable("rvState");
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.btnBack.setContentDescription(getResources().getString(R.string.close_back_click));
            this.binding.leftContent.btnCollect.setContentDescription(getResources().getString(R.string.collect_music_group));
            ViewCompat.setAccessibilityDelegate(this.binding.leftContent.btnCollect, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.music.activity.MusicListActivity.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View host, int action, Bundle args) {
                    if (action == 1007 && !MusicListActivity.this.mMediaMusicGroupBean.isCollection()) {
                        MusicListActivity.this.collectMusicGroup();
                    }
                    if (action == 1008 && MusicListActivity.this.mMediaMusicGroupBean.isCollection()) {
                        MusicListActivity.this.collectMusicGroup();
                    }
                    return super.performAccessibilityAction(host, action, args);
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.groupId = intent.getLongExtra("groupId", -100L);
        boolean booleanExtra = intent.getBooleanExtra(FROM_WIDGET, false);
        this.autoPlay = booleanExtra;
        if (booleanExtra) {
            this.isRecreate = false;
            this.groupType = this.groupId;
            this.isFromDeepLinkStart = this.isFromDeepLinkStart || intent.getBooleanExtra(FROM_WIDGET, false);
            this.mPresenter = new MusicListPresenter(this, this.groupId);
            showLoading();
            iniView();
            initVisibleIsSpoken();
            this.binding.listContent.recyclerviewMusicList.scrollToPosition(0);
        }
    }

    private void iniView() {
        showLoading();
        Util.setTextWeight(this.binding.leftContent.tvPlay, 500);
        setTitleBarVisibility(8);
        initRefreshLayout();
        initRecyclerView();
        this.binding.leftContent.btnCollect.setOnClickListener(this);
        long j = this.groupId;
        if (j == -3 || j == -2 || j == -1 || j == -8 || j == -12) {
            this.binding.leftContent.tvMusicGroupName.setVisibility(8);
            this.binding.leftContent.btnCollect.setVisibility(8);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.binding.leftContent.imTopCard.getLayoutParams();
            layoutParams.topMargin = BannerUtils.dp2px(183.0f);
            this.binding.leftContent.imTopCard.setLayoutParams(layoutParams);
        } else {
            this.binding.leftContent.tvMusicGroupName.setVisibility(0);
            this.binding.leftContent.btnCollect.setVisibility(0);
        }
        this.binding.btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.music.activity.MusicListActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicListActivity.this.onBackPressed();
            }
        });
        showAiMusicMv();
        this.binding.leftContent.btnPlay.setOnClickListener(new AnitPlayClick(500L) { // from class: com.wanos.media.ui.music.activity.MusicListActivity.3
            @Override // com.wanos.media.util.AnitPlayClick
            public void onAnitClick(View v) {
                if (MusicListActivity.this.curPlayGorupId != MusicListActivity.this.groupId || MusicListActivity.this.curPlayGorupType != MusicListActivity.this.groupType) {
                    MusicListActivity musicListActivity = MusicListActivity.this;
                    musicListActivity.playMusicGroup(musicListActivity.groupId);
                } else if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                    if (MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()) {
                        MediaPlayEngine.getInstance().getMediaPlayerService().pause();
                    } else {
                        MusicListActivity musicListActivity2 = MusicListActivity.this;
                        musicListActivity2.playMusicGroup(musicListActivity2.curPlayGorupId);
                    }
                }
            }
        });
    }

    private void initRefreshLayout() {
        this.binding.listContent.meterialAutoRefreshLayout.autoRefresh();
        this.binding.listContent.meterialAutoRefreshLayout.setStartLoadMoreItemThreshold(30);
        this.binding.listContent.meterialAutoRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.music.activity.MusicListActivity.4
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                MusicListActivity.this.isFirstLoading = true;
                MusicListActivity.this.page = 1;
                Log.i(MusicListActivity.TAG, "onRefresh: mPresenter =" + MusicListActivity.this.mPresenter);
                if (MusicListActivity.this.mPresenter == null) {
                    MusicListActivity musicListActivity = MusicListActivity.this;
                    MusicListActivity musicListActivity2 = MusicListActivity.this;
                    musicListActivity.mPresenter = new MusicListPresenter(musicListActivity2, musicListActivity2.groupId);
                }
                ((MusicListPresenter) MusicListActivity.this.mPresenter).requestData(MusicListActivity.this.page, MusicListActivity.this);
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                MusicListActivity.this.isFirstLoading = false;
                int i = MusicListActivity.this.page + 1;
                if (MusicListActivity.this.mPresenter == null) {
                    MusicListActivity musicListActivity = MusicListActivity.this;
                    MusicListActivity musicListActivity2 = MusicListActivity.this;
                    musicListActivity.mPresenter = new MusicListPresenter(musicListActivity2, musicListActivity2.groupId);
                }
                ((MusicListPresenter) MusicListActivity.this.mPresenter).requestData(i, MusicListActivity.this);
            }
        });
    }

    private void initRecyclerView() {
        this.musicListAdapter = new MusicListAdapter(this, this.mMediaMusicBeanList, this.groupId, this.groupType);
        this.binding.listContent.recyclerviewMusicList.setAdapter(this.musicListAdapter);
        this.binding.listContent.recyclerviewMusicList.setItemAnimator(null);
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
        if (this.isFirstLoading) {
            setTitleBarVisibility(0);
            showLoadingView();
        }
    }

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
        if (this.isFirstLoading) {
            setTitleBarVisibility(8);
            closeLoadingView();
        }
        this.binding.listContent.meterialAutoRefreshLayout.finishRefresh();
        this.binding.listContent.meterialAutoRefreshLayout.finishRefreshLoadMore();
    }

    @Override // com.wanos.media.view.MusicListView
    public void showFailOrError(int code, String msg) {
        if (this.isFirstLoading) {
            setTitleBarVisibility(0);
            if (code == 207 || code == 101 || code == -1) {
                showLoadErrorView(getString(R.string.empty_music_group_unshelve), R.drawable.icon_empty, new View.OnClickListener() { // from class: com.wanos.media.ui.music.activity.MusicListActivity$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.m524x65a48370(view);
                    }
                });
            } else {
                showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.music.activity.MusicListActivity$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.m525xf2df34f1(view);
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: lambda$showFailOrError$0$com-wanos-media-ui-music-activity-MusicListActivity, reason: not valid java name */
    /* synthetic */ void m524x65a48370(View view) {
        this.binding.listContent.meterialAutoRefreshLayout.autoRefresh();
    }

    /* JADX INFO: renamed from: lambda$showFailOrError$1$com-wanos-media-ui-music-activity-MusicListActivity, reason: not valid java name */
    /* synthetic */ void m525xf2df34f1(View view) {
        this.binding.listContent.meterialAutoRefreshLayout.autoRefresh();
    }

    @Override // com.wanos.media.view.MusicListView
    public void updateView(MusicGroupInfo mediaMusicGroupBean, List<MusicInfoBean> mediaMusicBeanList) {
        RecyclerView.LayoutManager layoutManager;
        if (isFinishing()) {
            return;
        }
        this.mMediaMusicGroupBean = mediaMusicGroupBean;
        this.mMediaMusicBeanList = mediaMusicBeanList;
        this.groupId = mediaMusicGroupBean.getMusicGroupId();
        String name = this.mMediaMusicGroupBean.getName();
        boolean zIsCollection = this.mMediaMusicGroupBean.isCollection();
        String avatar = this.mMediaMusicGroupBean.getAvatar();
        long j = this.groupId;
        if (j == -3) {
            this.binding.leftContent.imTopCard.setImageResource(R.drawable.music_ranking_card);
        } else if (j == -1) {
            this.binding.leftContent.imTopCard.setImageResource(R.drawable.music_hot_detail_card);
        } else if (j != -8) {
            if (j == -12) {
                this.binding.leftContent.imTopCard.setImageResource(R.drawable.ic_cover_daily_recommend);
            } else {
                GlideUtil.setImageData(SizeMode.PLAY_LIST, avatar, this.binding.leftContent.imTopCard, Priority.HIGH);
            }
        }
        this.binding.leftContent.tvMusicGroupName.setText(name);
        this.binding.leftContent.ivCollect.setImageResource(zIsCollection ? R.drawable.ic_collect : R.drawable.ic_no_collect);
        int curPlayMediaId = setCurPlayMediaId();
        this.musicListAdapter.setData(this.mMediaMusicBeanList);
        this.musicListAdapter.notifyDataSetChanged();
        if (this.rvState != null && !this.autoPlay && (layoutManager = this.binding.listContent.recyclerviewMusicList.getLayoutManager()) != null) {
            layoutManager.onRestoreInstanceState(this.rvState);
        }
        List<MusicInfoBean> list = this.mMediaMusicBeanList;
        if (list == null || list.size() == 0) {
            this.binding.listContent.emptyView.tvEmpty.setText(R.string.empty_music);
            this.binding.listContent.emptyView.getRoot().setVisibility(0);
            this.binding.listContent.meterialAutoRefreshLayout.setVisibility(8);
        } else {
            this.binding.listContent.emptyView.getRoot().setVisibility(8);
            this.binding.listContent.meterialAutoRefreshLayout.setVisibility(0);
        }
        updateIsCanLoadMore();
        if (curPlayMediaId > -1 && !this.isRecreate) {
            this.binding.listContent.recyclerviewMusicList.scrollToPosition(curPlayMediaId);
        }
        List<MusicInfoBean> list2 = this.mMediaMusicBeanList;
        if (list2 == null || list2.size() == 0) {
            this.binding.leftContent.btnPlay.setClickable(false);
        } else {
            this.binding.leftContent.btnPlay.setClickable(true);
        }
        this.binding.leftContent.btnCollect.setOnClickListener(this);
        updateGroupPlayView();
        updatePlayView();
        if (this.autoPlay) {
            this.autoPlay = false;
            if (this.isRecreate) {
                return;
            }
            playMusicGroup(this.groupId);
        }
    }

    @Override // com.wanos.media.view.MusicListView
    public void updateFreeCover(FreeGroupInfo info) {
        GlideUtil.setImageData(SizeMode.PLAY_LIST, info.getAvatar(), this.binding.leftContent.imTopCard, Priority.HIGH);
    }

    private void updateGroupPlayView() {
        long j = this.curPlayGorupId;
        long j2 = this.groupId;
        int i = R.string.play_all;
        int i2 = R.drawable.ic_pause_list_all;
        if (j == j2 && this.curPlayGorupType == this.groupType) {
            if (getMediaPlayerService() != null && getMediaPlayerService().getCurMediaInfo() != null && getMediaPlayerService().getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Music) {
                ImageView imageView = this.binding.leftContent.ivPlay;
                if (getMediaPlayerService().isPlaying()) {
                    i2 = R.drawable.ic_play_list_all;
                }
                imageView.setImageResource(i2);
                TextView textView = this.binding.leftContent.tvPlay;
                if (getMediaPlayerService().isPlaying()) {
                    i = R.string.pause_all;
                }
                textView.setText(i);
                return;
            }
            this.binding.leftContent.ivPlay.setImageResource(R.drawable.ic_pause_list_all);
            this.binding.leftContent.tvPlay.setText(R.string.play_all);
            return;
        }
        this.binding.leftContent.ivPlay.setImageResource(R.drawable.ic_pause_list_all);
        this.binding.leftContent.tvPlay.setText(R.string.play_all);
    }

    @Override // com.wanos.media.view.MusicListView
    public void addDataView(int page, List<MusicInfoBean> mediausicBeans) {
        this.page = page;
        if (mediausicBeans != null) {
            this.mMediaMusicBeanList.addAll(mediausicBeans);
            this.musicListAdapter.setData(this.mMediaMusicBeanList);
            this.musicListAdapter.notifyDataSetChanged();
        }
        updateIsCanLoadMore();
    }

    @Override // com.wanos.media.view.MusicListView
    public void updateCollectStatus(long musicGroupId, boolean isCollect) {
        MusicGroupInfo musicGroupInfo;
        if (this.groupId == musicGroupId && (musicGroupInfo = this.mMediaMusicGroupBean) != null) {
            musicGroupInfo.setCollection(isCollect);
            this.binding.leftContent.ivCollect.setImageResource(isCollect ? R.drawable.ic_collect : R.drawable.ic_no_collect);
        }
        ToastUtil.showMsg(isCollect ? R.string.music_collect_complete : R.string.music_cancel_collect_complete);
    }

    private void updateIsCanLoadMore() {
        List<MusicInfoBean> list = this.mMediaMusicBeanList;
        boolean z = false;
        int size = list != null ? list.size() : 0;
        MaterialAutoRefreshLayout materialAutoRefreshLayout = this.binding.listContent.meterialAutoRefreshLayout;
        if (size > 0 && size % 100 == 0) {
            z = true;
        }
        materialAutoRefreshLayout.setLoadMore(z);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() != R.id.btn_collect) {
            return;
        }
        collectMusicGroup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collectMusicGroup() {
        if (UserInfoUtil.isLogin()) {
            Message message = new Message();
            message.what = 1;
            EventBus.getDefault().post(message);
            if (this.mMediaMusicGroupBean == null || this.mPresenter == 0) {
                return;
            }
            ((MusicListPresenter) this.mPresenter).collectOrCancleCollectMusicGroupRequest(this.groupId, true ^ this.mMediaMusicGroupBean.isCollection(), this);
            return;
        }
        showLoginDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playMusicGroup(long groupId) {
        List<MusicInfoBean> list = this.mMediaMusicBeanList;
        if (list == null || list.size() <= 0 || MediaPlayEngine.getInstance().getMediaPlayerService() == null) {
            return;
        }
        MusicInfoBean musicInfoBean = this.mMediaMusicBeanList.get(0);
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
        mediaInfo.setGroupId(groupId);
        if (groupId > -1) {
            mediaInfo.setMediaGroupType(-6L);
        } else {
            mediaInfo.setMediaGroupType(groupId);
        }
        mediaInfo.setMusicInfoBean(musicInfoBean);
        MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
        if (groupId >= 0) {
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_GROUP_PLAY, "" + groupId, "", "", "", 0);
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        updateMediaPlayView(type, mediaInfo);
    }

    private int setCurPlayMediaId() {
        long musicId;
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        int i = -1;
        if (mediaPlayerService != null) {
            MediaInfo curMediaInfo = mediaPlayerService.getCurMediaInfo();
            if (curMediaInfo != null) {
                this.curPlayGorupId = curMediaInfo.getGroupId();
                this.curPlayGorupType = curMediaInfo.getMediaGroupType();
            }
            if (curMediaInfo == null || curMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || curMediaInfo.getMusicInfoBean() == null) {
                musicId = -1;
            } else {
                musicId = curMediaInfo.getMusicInfoBean().getMusicId();
                List<MusicInfoBean> list = this.mMediaMusicBeanList;
                if (list != null && list.size() > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 < this.mMediaMusicBeanList.size()) {
                            MusicInfoBean musicInfoBean = this.mMediaMusicBeanList.get(i2);
                            if (musicInfoBean != null && musicInfoBean.getMusicId() == musicId) {
                                i = i2;
                                break;
                            }
                            i2++;
                        } else {
                            break;
                        }
                    }
                }
            }
            MusicListAdapter musicListAdapter = this.musicListAdapter;
            if (musicListAdapter != null) {
                musicListAdapter.setCurrPlayMusicId(musicId);
            }
        }
        return i;
    }

    private void updateMediaPlayView(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        List<MusicInfoBean> list;
        if (mediaInfo == null || (list = this.mMediaMusicBeanList) == null || list.size() <= 0) {
            return;
        }
        this.curPlayGorupId = mediaInfo.getGroupId();
        this.curPlayGorupType = mediaInfo.getMediaGroupType();
        if (this.musicListAdapter != null) {
            MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
            long currPlayMusicId = this.musicListAdapter.getCurrPlayMusicId();
            long musicId = (mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || musicInfoBean == null) ? -1L : musicInfoBean.getMusicId();
            int i = -1;
            for (int i2 = 0; i2 < this.mMediaMusicBeanList.size(); i2++) {
                MusicInfoBean musicInfoBean2 = this.mMediaMusicBeanList.get(i2);
                if (musicInfoBean2 != null && mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && musicInfoBean2.getMusicId() == musicId) {
                    if (type == MediaPlayerEnum.MediaInfoCallbackType.favorStatus) {
                        musicInfoBean2.setCollection(musicInfoBean.isCollection());
                        this.mMediaMusicBeanList.set(i2, musicInfoBean2);
                    }
                    i = i2;
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
            if (type != MediaPlayerEnum.MediaInfoCallbackType.favorStatus || i <= -1) {
                return;
            }
            this.musicListAdapter.setData(this.mMediaMusicBeanList);
            this.musicListAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        long j = this.curPlayGorupId;
        long j2 = this.groupId;
        int i = R.string.play_all;
        int i2 = R.drawable.ic_pause_list_all;
        if (j == j2 && this.curPlayGorupType == this.groupType) {
            if (status == MediaPlayerEnum.CallBackState.STARTED) {
                ImageView imageView = this.binding.leftContent.ivPlay;
                if (getMediaPlayerService().isPlaying()) {
                    i2 = R.drawable.ic_play_list_all;
                }
                imageView.setImageResource(i2);
                TextView textView = this.binding.leftContent.tvPlay;
                if (getMediaPlayerService().isPlaying()) {
                    i = R.string.pause_all;
                }
                textView.setText(i);
            } else if (status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.COMPLETE) {
                this.binding.leftContent.ivPlay.setImageResource(R.drawable.ic_pause_list_all);
                this.binding.leftContent.tvPlay.setText(R.string.play_all);
            }
        } else {
            this.binding.leftContent.ivPlay.setImageResource(R.drawable.ic_pause_list_all);
            this.binding.leftContent.tvPlay.setText(R.string.play_all);
        }
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            updatePlayView();
        }
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

    @Override // com.wanos.media.base.WanosBaseActivity
    public void loginOrLogout(LoginOrLogoutEvent event) {
        boolean zIsLogin = UserInfoUtil.isLogin();
        if (this.isLogin != zIsLogin) {
            this.isLogin = zIsLogin;
            if (this.mPresenter != 0) {
                ((MusicListPresenter) this.mPresenter).requestData(1, this);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListDataExpired(DailyExpiredEvent event) {
        Log.i(TAG, "list expired, reload and play");
        if (this.mPresenter != 0) {
            ((MusicListPresenter) this.mPresenter).requestData(1, this);
        }
    }

    public static void startMusicListActivity(Context context, long groupId) {
        Intent intent = new Intent(context, (Class<?>) MusicListActivity.class);
        intent.putExtra("groupId", groupId);
        if (context instanceof Activity) {
            ((Activity) context).startActivity(intent);
        }
    }
}
