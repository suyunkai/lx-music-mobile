package com.wanos.media.ui.music.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.event.MediaCollectChangeEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.ActivityMusicGroupListBinding;
import com.wanos.media.presenter.MusicGroupListPresenter;
import com.wanos.media.ui.music.adapter.MusicGroupAdapter;
import com.wanos.media.view.MusicGroupListView;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicGroupListActivity extends WanosBaseActivity<MusicGroupListPresenter> implements MusicGroupListView, View.OnClickListener {
    private ActivityMusicGroupListBinding binding;
    private List<MusicGroupInfo> mMediaMusicGroupBeanList;
    private MusicGroupAdapter musicGroupAdapter;
    private int page = 1;
    private int totalCount = 0;
    private boolean isFirstLoading = true;

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPresenter = new MusicGroupListPresenter(this);
        ActivityMusicGroupListBinding activityMusicGroupListBindingInflate = ActivityMusicGroupListBinding.inflate(getLayoutInflater());
        this.binding = activityMusicGroupListBindingInflate;
        setContentView(activityMusicGroupListBindingInflate.getRoot());
        iniView();
    }

    private void iniView() {
        setTitleText(R.string.music_group_recommended);
        initRefreshLayout();
        initRecyclerView();
        showAiMusicMv();
    }

    private void initRefreshLayout() {
        this.binding.meterialAutoRefreshLayout.autoRefresh();
        this.binding.meterialAutoRefreshLayout.setStartLoadMoreItemThreshold(56);
        this.binding.meterialAutoRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.music.activity.MusicGroupListActivity.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                MusicGroupListActivity.this.isFirstLoading = true;
                MusicGroupListActivity.this.page = 1;
                ((MusicGroupListPresenter) MusicGroupListActivity.this.mPresenter).requestData(MusicGroupListActivity.this.page, MusicGroupListActivity.this);
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                MusicGroupListActivity.this.isFirstLoading = false;
                ((MusicGroupListPresenter) MusicGroupListActivity.this.mPresenter).requestData(MusicGroupListActivity.this.page + 1, MusicGroupListActivity.this);
            }
        });
    }

    private void initRecyclerView() {
        this.mMediaMusicGroupBeanList = new ArrayList();
        this.musicGroupAdapter = new MusicGroupAdapter(this, this.mMediaMusicGroupBeanList);
        this.binding.recyclerviewMusicGroupList.setAdapter(this.musicGroupAdapter);
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
        if (this.isFirstLoading) {
            showLoadingView();
        }
    }

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
        if (this.isFirstLoading) {
            closeLoadingView();
        }
        this.binding.meterialAutoRefreshLayout.finishRefresh();
        this.binding.meterialAutoRefreshLayout.finishRefreshLoadMore();
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
        if (this.isFirstLoading) {
            showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.music.activity.MusicGroupListActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    MusicGroupListActivity.this.binding.meterialAutoRefreshLayout.autoRefresh();
                }
            });
        }
    }

    @Override // com.wanos.media.view.MusicGroupListView
    public void updateView(int total, List<MusicGroupInfo> mediaMusicGroupBeanList) {
        this.totalCount = total;
        this.mMediaMusicGroupBeanList = mediaMusicGroupBeanList;
        this.musicGroupAdapter.setData(mediaMusicGroupBeanList);
        updateGroupPlayView(false);
        this.musicGroupAdapter.notifyDataSetChanged();
        updateIsCanLoadMore();
        hideLoading();
    }

    @Override // com.wanos.media.view.MusicGroupListView
    public void addDataView(int page, List<MusicGroupInfo> mediaMusicGroupBeanList) {
        this.page = page;
        List<MusicGroupInfo> list = this.mMediaMusicGroupBeanList;
        if (list != null) {
            list.addAll(mediaMusicGroupBeanList);
            this.musicGroupAdapter.setData(this.mMediaMusicGroupBeanList);
            this.musicGroupAdapter.notifyDataSetChanged();
        }
        updateIsCanLoadMore();
    }

    private void updateIsCanLoadMore() {
        List<MusicGroupInfo> list = this.mMediaMusicGroupBeanList;
        this.binding.meterialAutoRefreshLayout.setLoadMore((list != null ? list.size() : 0) < this.totalCount);
    }

    public static void startMusicGroupListActivity(Context context) {
        Intent intent = new Intent(context, (Class<?>) MusicGroupListActivity.class);
        if (context instanceof Activity) {
            ((Activity) context).startActivity(intent);
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            updateGroupPlayView(true);
        }
    }

    private void updateGroupPlayView(boolean isNotifyDataSetChanged) {
        long groupId;
        long mediaGroupType;
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
        if (MediaPlayEngine.getInstance().getMediaPlayerService() == null || MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo() == null || MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Music) {
            groupId = -100;
            mediaGroupType = -100;
        } else {
            groupId = MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo().getGroupId();
            mediaGroupType = MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo().getMediaGroupType();
        }
        MusicGroupAdapter musicGroupAdapter = this.musicGroupAdapter;
        if (musicGroupAdapter != null) {
            musicGroupAdapter.setCurMusicGroupIdAndType(groupId, mediaGroupType);
            this.musicGroupAdapter.setCallBackState(callBackState);
            if (isNotifyDataSetChanged) {
                this.musicGroupAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity
    public void mediaCollectChange(MediaCollectChangeEvent event) {
        super.mediaCollectChange(event);
        if (event == null || !event.isMusic()) {
            return;
        }
        updateGroupMusicCollectStatus(event.getId(), event.isCollect());
    }

    private void updateGroupMusicCollectStatus(long id, boolean isCollect) {
        List<MusicGroupInfo> list = this.mMediaMusicGroupBeanList;
        if (list == null || list.size() <= 0) {
            return;
        }
        boolean z = false;
        for (int i = 0; i < this.mMediaMusicGroupBeanList.size(); i++) {
            MusicGroupInfo musicGroupInfo = this.mMediaMusicGroupBeanList.get(i);
            if (musicGroupInfo != null && musicGroupInfo.getMusicList() != null && musicGroupInfo.getMusicList().size() > 0 && musicGroupInfo.getMusicList().get(0) != null && id == musicGroupInfo.getMusicList().get(0).getMusicId()) {
                List<MusicInfoBean> musicList = musicGroupInfo.getMusicList();
                MusicInfoBean musicInfoBean = musicList.get(0);
                musicInfoBean.setCollection(isCollect);
                musicList.set(0, musicInfoBean);
                musicGroupInfo.setMusicList(musicList);
                this.mMediaMusicGroupBeanList.set(i, musicGroupInfo);
                z = true;
            }
        }
        if (z) {
            this.musicGroupAdapter.setData(this.mMediaMusicGroupBeanList);
            this.musicGroupAdapter.notifyDataSetChanged();
        }
    }
}
