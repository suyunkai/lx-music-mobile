package com.wanos.media.ui.audiobook.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.response.AudioBookLikeRadioResponse;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.databinding.EmptyViewBinding;
import com.wanos.commonlibrary.event.AudioBookCollectEvent;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.cacheData.audiobook.AudioBookAlbumsCache;
import com.wanos.media.cacheData.audiobook.AudioBookChaptersCache;
import com.wanos.media.db.DbCallBack;
import com.wanos.media.db.WanosDbUtils;
import com.wanos.media.presenter.AudioBookAlbumPresenter;
import com.wanos.media.presenter.IABCallBack;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.adapter.AudioBookChapterListAdapter;
import com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter;
import com.wanos.media.view.AudioBookAlbumInfoView;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookAlbumBaseActivity extends WanosBaseActivity<AudioBookAlbumPresenter> implements AudioBookAlbumInfoView, View.OnClickListener {
    public static int REQUEST_CODE_BACK = 1;
    public static final String TAG = "wanos:[AudioBookAlbumBaseActivity]";
    public static String strBean = "BEAN";
    public static String strFromBar = "FROMBAR";
    public static String strID = "ID";
    public static String strPlayChapterId = "PLAYCHAPTERID";
    public static String strPlayChapterIndex = "PLAYCHAPTERINDEX";
    public static String strPlayPage = "PLAYPAGE";
    public static String strSchemeChapterId = "chapterid";
    public static String strSchemeChapterIndex = "chapterindex";
    public static String strSchemeId = "id";
    protected long albumId;
    protected AudioBookAlbumInfoBean audioBookAlbumInfoBean;
    protected AudioBookChapterListAdapter audioBookChapterListAdapter;
    private LinearLayoutManager chapterListLayout;
    protected RecyclerView chapterListView;
    protected EmptyViewBinding emptyView;
    public boolean isNightDayModel;
    protected int nextPage;
    private Parcelable originalState;
    protected MediaPlayerEnum.PlayMode playModelType;
    protected int previousPage;
    protected MaterialAutoRefreshLayout refreshLayout;
    protected boolean isPlayPage = false;
    protected long curPlayChapterId = -1;
    protected int curPlayChapterIndex = -1;
    protected List<AudioBookChapterItemBean> chapterBeanList = new ArrayList();
    private boolean isFromBar = false;
    private boolean isLoginTag = false;
    long lastLoginOrLogoutTime = 0;

    protected void bindingLayout() {
    }

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    public void onClick(View view) {
    }

    public void showAlbumPage() {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    public void showPlayPage() {
    }

    protected void updateChapterCollect() {
    }

    protected void updateChapterDuration(long t) {
    }

    public void updateCollect() {
    }

    protected void updateLeftView() {
    }

    protected void updatePlayMode() {
    }

    public Parcelable getRecyclerViewState() {
        return this.chapterListLayout.onSaveInstanceState();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong("ID", this.curPlayChapterId);
        outState.putInt("Index", this.curPlayChapterIndex);
        outState.putBoolean("isNightDay", true);
        outState.putParcelable("rvState", getRecyclerViewState());
        outState.putSerializable("audioBookAlbumInfoBean", this.audioBookAlbumInfoBean);
        super.onSaveInstanceState(outState);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioBookChaptersCache.getInstance().onInit();
        bindingLayout();
        initData(savedInstanceState);
        initView();
        initListener();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.curPlayChapterId = savedInstanceState.getLong("ID", -1L);
        this.curPlayChapterIndex = savedInstanceState.getInt("Index", -1);
        this.isNightDayModel = savedInstanceState.getBoolean("isNightDay", false);
        this.originalState = savedInstanceState.getParcelable("rvState");
        this.audioBookAlbumInfoBean = (AudioBookAlbumInfoBean) savedInstanceState.getSerializable("audioBookAlbumInfoBean");
        AudioBookChapterListAdapter audioBookChapterListAdapter = this.audioBookChapterListAdapter;
        if (audioBookChapterListAdapter != null) {
            audioBookChapterListAdapter.setPlayChapterId(this.curPlayChapterId);
            this.audioBookChapterListAdapter.setAlbumInfoBean(this.audioBookAlbumInfoBean);
        } else {
            initChapterListView();
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        updateLeftView();
        this.audioBookChapterListAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AudioBookChaptersCache.getInstance().onDestory();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getSchemeData(Uri data) {
        try {
            this.albumId = Long.parseLong(data.getQueryParameter(strSchemeId));
        } catch (Exception unused) {
        }
    }

    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent.getData() != null) {
            getSchemeData(intent.getData());
        } else {
            boolean booleanExtra = intent.getBooleanExtra(strFromBar, false);
            this.isFromBar = booleanExtra;
            if (booleanExtra) {
                if (getMediaPlayerService() != null) {
                    this.albumId = getMediaPlayerService().getCurMediaInfo().getAudioBookInfoBean().getRadioId();
                    this.curPlayChapterId = getMediaPlayerService().getCurMediaInfo().getAudioBookInfoBean().getId();
                    this.curPlayChapterIndex = getMediaPlayerService().getCurMediaInfo().getAudioBookInfoBean().getIndex();
                    this.isPlayPage = true;
                }
            } else {
                this.albumId = intent.getLongExtra(strID, 0L);
                boolean booleanExtra2 = intent.getBooleanExtra(strPlayPage, false);
                this.isPlayPage = booleanExtra2;
                if (booleanExtra2 && savedInstanceState == null) {
                    this.curPlayChapterId = intent.getLongExtra(strPlayChapterId, -1L);
                    this.curPlayChapterIndex = intent.getIntExtra(strPlayChapterIndex, -1);
                }
            }
        }
        this.mPresenter = new AudioBookAlbumPresenter(this, this);
        ((AudioBookAlbumPresenter) this.mPresenter).requestData(this.albumId, false);
        initDataEx();
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_AUDIO_BOOK_ALBUM_PLAY, "" + this.albumId, "", "", "", 0);
    }

    protected void initDataEx() {
        if (this.isPlayPage) {
            initChapterData(this.curPlayChapterIndex);
        } else {
            WanosDbUtils.getAudioBookHistoryItem(this.albumId, new DbCallBack<AudioBookMineChapterItemBean>() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.1
                @Override // com.wanos.media.db.DbCallBack
                public void callBackData(AudioBookMineChapterItemBean data) {
                    if (data != null) {
                        AudioBookMineChapterItemBean playingItemBean = AudioBookGlobalParams.getPlayingItemBean();
                        if (AudioBookAlbumBaseActivity.this.audioBookChapterListAdapter != null) {
                            if (playingItemBean != null && playingItemBean.getId() == data.getId()) {
                                AudioBookAlbumBaseActivity.this.curPlayChapterId = data.getId();
                                AudioBookAlbumBaseActivity.this.curPlayChapterIndex = data.getIndex();
                                AudioBookAlbumBaseActivity audioBookAlbumBaseActivity = AudioBookAlbumBaseActivity.this;
                                audioBookAlbumBaseActivity.initChapterData(audioBookAlbumBaseActivity.curPlayChapterIndex);
                                AudioBookAlbumBaseActivity.this.audioBookChapterListAdapter.setPlayChapterId(AudioBookAlbumBaseActivity.this.curPlayChapterId);
                                return;
                            }
                            AudioBookAlbumBaseActivity.this.initChapterData(0);
                            return;
                        }
                        return;
                    }
                    AudioBookAlbumBaseActivity.this.initChapterData(0);
                }
            });
        }
    }

    public void initChapterData(int index) {
        final int i = (index / AudioBookAlbumPresenter.chapterCountOfPage) + 1;
        setTitleBarVisibility(0);
        showLoadingView();
        ((AudioBookAlbumPresenter) this.mPresenter).requestChapterList(this.albumId, i, true, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.2
            @Override // com.wanos.media.presenter.IABCallBack
            public void requestFinish(int code, boolean isSuccess) {
                if (AudioBookAlbumBaseActivity.this.isFinishing()) {
                    return;
                }
                AudioBookAlbumBaseActivity.this.setTitleBarVisibility(8);
                AudioBookAlbumBaseActivity.this.closeLoadingView();
                if (isSuccess) {
                    if (AudioBookAlbumBaseActivity.this.originalState != null) {
                        AudioBookAlbumBaseActivity.this.chapterListLayout.onRestoreInstanceState(AudioBookAlbumBaseActivity.this.originalState);
                        Log.i(AudioBookAlbumBaseActivity.TAG, "requestFinish: 恢复之前的RecyclerView状态");
                    } else {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= AudioBookAlbumBaseActivity.this.chapterBeanList.size()) {
                                break;
                            }
                            if (AudioBookAlbumBaseActivity.this.chapterBeanList.get(i2).getId() == AudioBookAlbumBaseActivity.this.curPlayChapterId) {
                                AudioBookAlbumBaseActivity.this.chapterListLayout.scrollToPositionWithOffset(i2, 0);
                                break;
                            }
                            i2++;
                        }
                    }
                    if (i == 1) {
                        AudioBookAlbumBaseActivity.this.refreshLayout.setMaterialStyle(false);
                    }
                    if (AudioBookAlbumBaseActivity.this.isPlayPage) {
                        AudioBookAlbumBaseActivity.this.initPlayer();
                        return;
                    }
                    return;
                }
                AudioBookAlbumBaseActivity.this.setTitleBarVisibility(0);
                AudioBookAlbumBaseActivity.this.showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        AudioBookAlbumBaseActivity.this.initDataEx();
                    }
                });
            }
        });
    }

    public void initView() {
        updateLeftView();
        initChapterListView();
    }

    protected void initChapterListView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        this.chapterListLayout = linearLayoutManager;
        this.chapterListView.setLayoutManager(linearLayoutManager);
        AudioBookChapterListAdapter audioBookChapterListAdapter = new AudioBookChapterListAdapter(this, this.chapterBeanList);
        this.audioBookChapterListAdapter = audioBookChapterListAdapter;
        audioBookChapterListAdapter.setFromBar(this.isFromBar);
        this.audioBookChapterListAdapter.setAlbumInfoBean(this.audioBookAlbumInfoBean);
        this.audioBookChapterListAdapter.setPlayChapterId(this.curPlayChapterId);
        this.chapterListView.setAdapter(this.audioBookChapterListAdapter);
        initRefreshView();
    }

    public void initPlayer() {
        MediaPlayerService mediaPlayerService;
        AudioBookChapterItemBean audioBookChapterItemBean;
        Log.i(TAG, "initPlayer: ");
        if (this.isNightDayModel || this.audioBookAlbumInfoBean == null || this.chapterBeanList.size() == 0 || (mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService()) == null) {
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.chapterBeanList.size()) {
                audioBookChapterItemBean = null;
                break;
            }
            audioBookChapterItemBean = this.chapterBeanList.get(i);
            if (audioBookChapterItemBean.getId() == this.curPlayChapterId) {
                break;
            } else {
                i++;
            }
        }
        if (mediaPlayerService.getCurMediaInfo() == null || mediaPlayerService.getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Audiobook || mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean() == null || mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean().getRadioId() != this.albumId || mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean().getId() != this.curPlayChapterId) {
            if (audioBookChapterItemBean != null) {
                Log.i(TAG, "initPlayer: playBean != null");
                toPlay(audioBookChapterItemBean);
                return;
            }
            return;
        }
        if (mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean().getIsVip() == 1 && !UserInfoUtil.isVipAndUnexpired() && !this.isFromBar && !this.isLoginTag) {
            openWeChatPayActivity();
        } else {
            if (mediaPlayerService.isPlaying() || this.isFromBar) {
                return;
            }
            mediaPlayerService.start();
        }
    }

    protected void toPlay(final AudioBookChapterItemBean bean) {
        Log.i(TAG, "toPlay: ");
        WanosDbUtils.getAudioBookHistoryItem(this.albumId, new DbCallBack<AudioBookMineChapterItemBean>() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.3
            @Override // com.wanos.media.db.DbCallBack
            public void callBackData(AudioBookMineChapterItemBean data) {
                try {
                    Gson gson = new Gson();
                    AudioBookMineChapterItemBean audioBookMineChapterItemBean = (AudioBookMineChapterItemBean) gson.fromJson(gson.toJson(bean), AudioBookMineChapterItemBean.class);
                    audioBookMineChapterItemBean.setRadioId(AudioBookAlbumBaseActivity.this.audioBookAlbumInfoBean.getId());
                    audioBookMineChapterItemBean.setRadioName(AudioBookAlbumBaseActivity.this.audioBookAlbumInfoBean.getName());
                    audioBookMineChapterItemBean.setAvatar(AudioBookAlbumBaseActivity.this.audioBookAlbumInfoBean.getAvatar());
                    audioBookMineChapterItemBean.setSpeaker(AudioBookAlbumBaseActivity.this.audioBookAlbumInfoBean.getSpeaker());
                    audioBookMineChapterItemBean.setAuthor(AudioBookAlbumBaseActivity.this.audioBookAlbumInfoBean.getAuthor());
                    audioBookMineChapterItemBean.setPageSize(AudioBookAlbumPresenter.chapterCountOfPage);
                    audioBookMineChapterItemBean.setProgress(0L);
                    if (data != null && data.getId() == AudioBookAlbumBaseActivity.this.curPlayChapterId) {
                        audioBookMineChapterItemBean.setProgress(data.getProgress());
                    }
                    WanosDbUtils.updateAudioBookHistory(audioBookMineChapterItemBean, new DbCallBack<Boolean>() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.3.1
                        @Override // com.wanos.media.db.DbCallBack
                        public void callBackData(Boolean data2) {
                        }
                    });
                    MediaInfo mediaInfo = new MediaInfo();
                    mediaInfo.setGroupId(AudioBookAlbumBaseActivity.this.albumId);
                    mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Audiobook);
                    mediaInfo.setAudioBookInfoBean(audioBookMineChapterItemBean);
                    MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
                } catch (Exception unused) {
                }
            }
        });
    }

    private void initRefreshView() {
        this.refreshLayout.setMaterialStyle(true);
        this.refreshLayout.setLoadMore(true);
        this.refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.4
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                AudioBookAlbumBaseActivity audioBookAlbumBaseActivity = AudioBookAlbumBaseActivity.this;
                audioBookAlbumBaseActivity.previousPage = ((AudioBookAlbumPresenter) audioBookAlbumBaseActivity.mPresenter).getPrePage(AudioBookAlbumBaseActivity.this.albumId) - 1;
                if (AudioBookAlbumBaseActivity.this.previousPage >= 1) {
                    AudioBookAlbumBaseActivity audioBookAlbumBaseActivity2 = AudioBookAlbumBaseActivity.this;
                    audioBookAlbumBaseActivity2.getData(audioBookAlbumBaseActivity2.previousPage, false);
                } else {
                    AudioBookAlbumBaseActivity.this.refreshLayout.finishRefresh();
                    AudioBookAlbumBaseActivity.this.refreshLayout.finishRefreshLoadMore();
                }
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                AudioBookAlbumBaseActivity audioBookAlbumBaseActivity = AudioBookAlbumBaseActivity.this;
                audioBookAlbumBaseActivity.nextPage = ((AudioBookAlbumPresenter) audioBookAlbumBaseActivity.mPresenter).getNextPage(AudioBookAlbumBaseActivity.this.albumId) + 1;
                AudioBookAlbumBaseActivity audioBookAlbumBaseActivity2 = AudioBookAlbumBaseActivity.this;
                audioBookAlbumBaseActivity2.getData(audioBookAlbumBaseActivity2.nextPage, true);
            }
        });
    }

    public void getData(final int page, final boolean nextPage) {
        ((AudioBookAlbumPresenter) this.mPresenter).requestChapterList(this.albumId, page, nextPage, new IABCallBack() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.5
            @Override // com.wanos.media.presenter.IABCallBack
            public void requestFinish(int code, boolean isSuccess) {
                AudioBookAlbumBaseActivity.this.refreshLayout.finishRefresh();
                AudioBookAlbumBaseActivity.this.refreshLayout.finishRefreshLoadMore();
                if (page == 1) {
                    AudioBookAlbumBaseActivity.this.refreshLayout.setMaterialStyle(false);
                }
                AudioBookAlbumBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (nextPage) {
                            return;
                        }
                        AudioBookAlbumBaseActivity.this.chapterListView.scrollToPosition(AudioBookAlbumPresenter.chapterCountOfPage);
                    }
                });
            }
        });
    }

    public void initListener() {
        this.audioBookChapterListAdapter.setListener(new AudioBookChapterListBaseAdapter.ChapterItemListener() { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.6
            @Override // com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter.ChapterItemListener
            public void onPlay(long id, int index) {
                AudioBookAlbumBaseActivity.this.curPlayChapterId = id;
                AudioBookAlbumBaseActivity.this.curPlayChapterIndex = index;
                if (!AudioBookAlbumBaseActivity.this.isPlayPage) {
                    AudioBookAlbumBaseActivity.this.showPlayActivity();
                } else {
                    AudioBookAlbumBaseActivity.this.isNightDayModel = false;
                    AudioBookAlbumBaseActivity.this.initPlayer();
                }
                AudioBookAlbumBaseActivity.this.updateChapterCollect();
            }

            @Override // com.wanos.media.ui.audiobook.adapter.AudioBookChapterListBaseAdapter.ChapterItemListener
            public void onCollect(long id, int isCollect) {
                if (AudioBookAlbumBaseActivity.this.curPlayChapterId == id) {
                    AudioBookAlbumBaseActivity.this.updateChapterCollect();
                }
            }
        });
    }

    public void showPlayActivity() {
        AudioBookPlayerActivity.buildPlayPage(this, this.albumId, this.curPlayChapterId, this.curPlayChapterIndex, false);
    }

    public void collectAlbum() {
        if (!UserInfoUtil.isLogin()) {
            showLoginDialog();
        } else {
            final int i = this.audioBookAlbumInfoBean.getIsCollect() == 1 ? 0 : 1;
            WanOSRetrofitUtil.audioBookLikeAlbum(this.albumId, i, new ResponseCallBack<AudioBookLikeRadioResponse>(this) { // from class: com.wanos.media.ui.audiobook.activity.AudioBookAlbumBaseActivity.7
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(AudioBookLikeRadioResponse response) {
                    AudioBookGlobalParams.getInstance().setCollectAlbumIsUpdate(true);
                    AudioBookAlbumInfoBean audioBookAlbumInfoBeanCollectAlbum = AudioBookAlbumsCache.getInstance().collectAlbum(AudioBookAlbumBaseActivity.this.albumId, i);
                    ToastUtil.showMsg(i == 1 ? R.string.music_collect_complete : R.string.music_cancel_collect_complete);
                    if (audioBookAlbumInfoBeanCollectAlbum != null) {
                        AudioBookAlbumBaseActivity.this.audioBookAlbumInfoBean = audioBookAlbumInfoBeanCollectAlbum;
                    } else {
                        AudioBookAlbumBaseActivity.this.audioBookAlbumInfoBean.setIsCollect(i);
                    }
                    AudioBookAlbumBaseActivity.this.updateCollect();
                    EventBus.getDefault().post(new AudioBookCollectEvent(AudioBookAlbumBaseActivity.this.isFromBar));
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    if (i == 1) {
                        ToastUtil.showMsg("收藏失败");
                    } else {
                        ToastUtil.showMsg("取消收藏失败");
                    }
                }
            });
        }
    }

    protected String stringForTime(long totalMS) {
        long j = totalMS / 1000;
        long j2 = j % 60;
        long j3 = j / 60;
        long j4 = j3 % 60;
        return j > 216000 ? new Formatter().format("%02d:%02d:%02d", Long.valueOf(j3 / 3600), Long.valueOf(j4), Long.valueOf(j2)).toString() : new Formatter().format("%02d:%02d", Long.valueOf(j4), Long.valueOf(j2)).toString();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            AudioBookMineChapterItemBean audioBookInfoBean = mediaInfo.getAudioBookInfoBean();
            if (type == MediaPlayerEnum.MediaInfoCallbackType.favorStatus) {
                Log.d(TAG, "onMediaInfoCallbackAppNext newBean.getRadioId() = " + audioBookInfoBean.getRadioId() + ",albumId=" + this.albumId);
                AudioBookChaptersCache.getInstance().collectChapter(audioBookInfoBean.getRadioId(), audioBookInfoBean.getId(), audioBookInfoBean.getIsCollect());
                if (audioBookInfoBean.getRadioId() == this.albumId) {
                    this.audioBookChapterListAdapter.notifyDataSetChanged();
                    return;
                }
                return;
            }
            if (type == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
                Log.d(TAG, "onMediaInfoCallbackAppNext mediaInfo");
                if (audioBookInfoBean.getRadioId() == this.albumId) {
                    this.curPlayChapterId = audioBookInfoBean.getId();
                    this.curPlayChapterIndex = audioBookInfoBean.getIndex();
                    AudioBookChapterListAdapter audioBookChapterListAdapter = this.audioBookChapterListAdapter;
                    if (audioBookChapterListAdapter != null) {
                        audioBookChapterListAdapter.setPlayChapterId(this.curPlayChapterId);
                        this.audioBookChapterListAdapter.notifyDataSetChanged();
                        int i = 0;
                        while (true) {
                            if (i >= this.chapterBeanList.size()) {
                                break;
                            }
                            AudioBookChapterItemBean audioBookChapterItemBean = this.chapterBeanList.get(i);
                            if (audioBookChapterItemBean.getId() == this.curPlayChapterId) {
                                updateChapterDuration(audioBookChapterItemBean.getDuration());
                                this.chapterListView.scrollToPosition(i);
                                break;
                            }
                            i++;
                        }
                    }
                    updateChapterCollect();
                    return;
                }
                return;
            }
            if (type == MediaPlayerEnum.MediaInfoCallbackType.playMode) {
                updatePlayMode();
            }
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            this.audioBookChapterListAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.wanos.media.view.AudioBookAlbumInfoView
    public void updateChapterView(List<AudioBookChapterItemBean> list, boolean isLoadMore) {
        if (isFinishing()) {
            return;
        }
        if (isLoadMore) {
            Log.d(TAG, "binding.audiobookRefreshLayout.setLoadMore(true)");
            this.refreshLayout.setLoadMore(true);
        } else {
            Log.d(TAG, "binding.audiobookRefreshLayout.setLoadMore(false)");
            this.refreshLayout.setLoadMore(false);
        }
        if (list.size() == 0) {
            this.refreshLayout.setVisibility(8);
            this.emptyView.getRoot().setVisibility(0);
            this.emptyView.tvEmpty.setText(R.string.empty_audiobook);
            return;
        }
        this.refreshLayout.setVisibility(0);
        this.emptyView.getRoot().setVisibility(8);
        this.chapterBeanList = list;
        updateChapterCollect();
        AudioBookChapterListAdapter audioBookChapterListAdapter = this.audioBookChapterListAdapter;
        if (audioBookChapterListAdapter != null) {
            audioBookChapterListAdapter.setData(list);
            this.audioBookChapterListAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.wanos.media.view.AudioBookAlbumInfoView
    public void updateAlbumView(AudioBookAlbumInfoBean bean) {
        if (isFinishing()) {
            return;
        }
        this.audioBookAlbumInfoBean = bean;
        AudioBookChapterListAdapter audioBookChapterListAdapter = this.audioBookChapterListAdapter;
        if (audioBookChapterListAdapter != null) {
            audioBookChapterListAdapter.setAlbumInfoBean(bean);
        }
        updateLeftView();
        if (this.isPlayPage) {
            initPlayer();
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent event) {
        Log.i(TAG, "loginOrLogout: " + event.isLogin());
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastLoginOrLogoutTime < 1000) {
            Log.i(TAG, "loginOrLogout: too fast");
            return;
        }
        this.lastLoginOrLogoutTime = jCurrentTimeMillis;
        this.isLoginTag = event.isLogin();
        if (event.isLogin()) {
            this.isNightDayModel = false;
        }
        if (this.isNightDayModel) {
            return;
        }
        AudioBookChaptersCache.getInstance().onDestory();
        if (this.mPresenter != 0) {
            ((AudioBookAlbumPresenter) this.mPresenter).requestData(this.albumId, true);
            initDataEx();
        }
    }
}
