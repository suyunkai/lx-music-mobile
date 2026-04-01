package com.wanos.media.ui.music;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.event.MediaCollectChangeEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentMyMusicGroupListBinding;
import com.wanos.media.presenter.MusicGroupListPresenter;
import com.wanos.media.ui.music.adapter.MusicGroupAdapter;
import com.wanos.media.view.MusicGroupListView;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class MyMusicGroupListFragment extends WanosBaseFragment<MusicGroupListPresenter> implements MusicGroupListView {
    private FragmentMyMusicGroupListBinding binding;
    private MusicGroupAdapter musicGroupAdapter;
    private List<MusicGroupInfo> mMediaMusicGroupBeanList = new ArrayList();
    private int page = 1;
    private int totalCount = 0;
    private boolean isFirstLoading = true;
    private boolean isNeedRefresh = false;

    @Override // com.wanos.media.base.WanosBaseFragment
    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return true;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mPresenter = new MusicGroupListPresenter(this, true);
        this.binding = FragmentMyMusicGroupListBinding.inflate(inflater, container, false);
        initView();
        return this.binding.getRoot();
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isNeedRefresh) {
            this.isNeedRefresh = false;
            this.binding.meterialAutoRefreshLayout.autoRefresh();
        }
        updateGroupPlayView(true);
    }

    private void initView() {
        this.binding.emptyView.getRoot().setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.title_bar_height));
        initRefreshLayout();
        initRecyclerView();
    }

    private void initRefreshLayout() {
        this.binding.meterialAutoRefreshLayout.autoRefresh();
        this.binding.meterialAutoRefreshLayout.setStartLoadMoreItemThreshold(48);
        this.binding.meterialAutoRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.music.MyMusicGroupListFragment.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                MyMusicGroupListFragment.this.isFirstLoading = true;
                MyMusicGroupListFragment.this.page = 1;
                if (MyMusicGroupListFragment.this.mPresenter == null || MyMusicGroupListFragment.this.getActivity() == null) {
                    return;
                }
                ((MusicGroupListPresenter) MyMusicGroupListFragment.this.mPresenter).requestData(MyMusicGroupListFragment.this.page, MyMusicGroupListFragment.this.getActivity());
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                MyMusicGroupListFragment.this.isFirstLoading = false;
                int i = MyMusicGroupListFragment.this.page + 1;
                if (MyMusicGroupListFragment.this.mPresenter == null || MyMusicGroupListFragment.this.getActivity() == null) {
                    return;
                }
                ((MusicGroupListPresenter) MyMusicGroupListFragment.this.mPresenter).requestData(i, MyMusicGroupListFragment.this.getActivity());
            }
        });
    }

    private void initRecyclerView() {
        this.musicGroupAdapter = new MusicGroupAdapter(getActivity(), this.mMediaMusicGroupBeanList);
        this.binding.recyclerviewMusicGroupList.setAdapter(this.musicGroupAdapter);
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
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
            showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.music.MyMusicGroupListFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    MyMusicGroupListFragment.this.binding.meterialAutoRefreshLayout.autoRefresh();
                }
            });
        }
    }

    @Override // com.wanos.media.view.MusicGroupListView
    public void updateView(int total, List<MusicGroupInfo> mediaMusicGroupBeanList) {
        if (isAdded()) {
            this.totalCount = total;
            this.mMediaMusicGroupBeanList = mediaMusicGroupBeanList;
            this.musicGroupAdapter.setData(mediaMusicGroupBeanList);
            updateGroupPlayView(false);
            this.musicGroupAdapter.notifyDataSetChanged();
            updateIsCanLoadMore();
            List<MusicGroupInfo> list = this.mMediaMusicGroupBeanList;
            if (list == null || list.size() == 0) {
                this.binding.meterialAutoRefreshLayout.setVisibility(8);
                this.binding.emptyView.getRoot().setVisibility(0);
                this.binding.emptyView.tvEmpty.setText(R.string.empty_music_group);
            } else {
                this.binding.meterialAutoRefreshLayout.setVisibility(0);
                this.binding.emptyView.getRoot().setVisibility(8);
            }
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshCollectState(Message msg) {
        if (msg.what == 1) {
            this.isNeedRefresh = true;
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
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

    @Override // com.wanos.media.base.WanosBaseFragment
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
