package com.wanos.media.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.LogUtils;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.event.CollectChange2HisEvent;
import com.wanos.commonlibrary.event.ResourceNotExistEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentMusicListBinding;
import com.wanos.media.db.DbCallBack;
import com.wanos.media.db.WanosDbUtils;
import com.wanos.media.presenter.MusicListPresenter;
import com.wanos.media.ui.music.adapter.MusicListAdapter;
import com.wanos.media.ui.widget.SpaceItemDecoration;
import com.wanos.media.ui.widget.banner.util.BannerUtils;
import com.wanos.media.view.MusicListView;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class MyMusicHistoryListFragment extends WanosBaseFragment<MusicListPresenter> implements MusicListView {
    private FragmentMusicListBinding binding;
    private MediaPlayerService mMediaPlayerService;
    private MusicListAdapter musicListAdapter;
    private List<MusicInfoBean> mediaMusicBeanList = new ArrayList();
    private boolean isFirstLoading = true;
    private int page = 1;

    static /* synthetic */ void lambda$resourceNotExist$0(Boolean bool) {
    }

    static /* synthetic */ void lambda$resourceNotExist$1(Boolean bool) {
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return true;
    }

    @Override // com.wanos.media.view.MusicListView
    public void updateCollectStatus(long musicGroupId, boolean isCollect) {
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        this.mPresenter = new MusicListPresenter(this, -5L);
        this.binding = FragmentMusicListBinding.inflate(inflater, container, false);
        this.mMediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        initView();
        return this.binding.getRoot();
    }

    private void initView() {
        this.binding.emptyView.getRoot().setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.title_bar_height));
        initRefreshLayout();
        initRecyclerView();
    }

    private void initRefreshLayout() {
        this.binding.meterialAutoRefreshLayout.autoRefresh();
        this.binding.meterialAutoRefreshLayout.setStartLoadMoreItemThreshold(48);
        this.binding.meterialAutoRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.music.MyMusicHistoryListFragment.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                MyMusicHistoryListFragment.this.isFirstLoading = true;
                MyMusicHistoryListFragment.this.page = 1;
                if (MyMusicHistoryListFragment.this.mPresenter == null || MyMusicHistoryListFragment.this.getActivity() == null) {
                    return;
                }
                ((MusicListPresenter) MyMusicHistoryListFragment.this.mPresenter).requestData(MyMusicHistoryListFragment.this.page, MyMusicHistoryListFragment.this.getActivity());
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                MyMusicHistoryListFragment.this.isFirstLoading = false;
                int i = MyMusicHistoryListFragment.this.page + 1;
                if (MyMusicHistoryListFragment.this.mPresenter == null || MyMusicHistoryListFragment.this.getActivity() == null) {
                    return;
                }
                ((MusicListPresenter) MyMusicHistoryListFragment.this.mPresenter).requestData(i, MyMusicHistoryListFragment.this.getActivity());
            }
        });
    }

    private void initRecyclerView() {
        this.musicListAdapter = new MusicListAdapter(getActivity(), this.mediaMusicBeanList, -5L, -5L);
        this.binding.recyclerviewMusicList.setAdapter(this.musicListAdapter);
        this.binding.recyclerviewMusicList.addItemDecoration(new SpaceItemDecoration(BannerUtils.dp2px(40.0f)));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshCollectState(CollectChange2HisEvent msg) {
        for (int i = 0; i < this.mediaMusicBeanList.size(); i++) {
            MusicInfoBean musicInfoBean = this.mediaMusicBeanList.get(i);
            if (musicInfoBean != null && musicInfoBean.getMusicId() == msg.getMusicId()) {
                this.mediaMusicBeanList.get(i).setCollection(false);
                this.musicListAdapter.notifyItemChanged(i);
            }
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
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
            showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.music.MyMusicHistoryListFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    MyMusicHistoryListFragment.this.binding.meterialAutoRefreshLayout.autoRefresh();
                }
            });
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setCurPlayMediaId(true);
        updatePlayView();
    }

    @Override // com.wanos.media.view.MusicListView
    public void updateView(MusicGroupInfo mediaMusicGroupBean, List<MusicInfoBean> mediaMusicBeanList) {
        if (isAdded()) {
            if (mediaMusicBeanList == null) {
                mediaMusicBeanList = new ArrayList<>();
            }
            this.mediaMusicBeanList = mediaMusicBeanList;
            this.musicListAdapter.setData(mediaMusicBeanList);
            List<MusicInfoBean> list = this.mediaMusicBeanList;
            if (list == null || list.size() == 0) {
                this.binding.meterialAutoRefreshLayout.setVisibility(8);
                this.binding.emptyView.getRoot().setVisibility(0);
                this.binding.emptyView.tvEmpty.setText(R.string.empty_history_music);
            } else {
                this.binding.meterialAutoRefreshLayout.setVisibility(0);
                this.binding.emptyView.getRoot().setVisibility(8);
            }
            setCurPlayMediaId(true);
            updateIsCanLoadMore(mediaMusicBeanList.size());
        }
    }

    @Override // com.wanos.media.view.MusicListView
    public void addDataView(int page, List<MusicInfoBean> list) {
        this.page = page;
        List<MusicInfoBean> list2 = this.mediaMusicBeanList;
        if (list2 != null) {
            list2.addAll(list);
            this.musicListAdapter.setData(this.mediaMusicBeanList);
            this.musicListAdapter.notifyDataSetChanged();
        }
        updateIsCanLoadMore(list == null ? 0 : list.size());
    }

    private void updateIsCanLoadMore(int size) {
        this.binding.meterialAutoRefreshLayout.setLoadMore(size > 0 && size % 100 == 0);
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        updateMediaPlayView(type, mediaInfo);
    }

    private int setCurPlayMediaId(boolean isNotifyDataSetChanged) {
        long musicId;
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        int i = -1;
        if (mediaPlayerService != null) {
            MediaInfo curMediaInfo = mediaPlayerService.getCurMediaInfo();
            if (curMediaInfo == null || curMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || curMediaInfo.getMusicInfoBean() == null) {
                musicId = -1;
            } else {
                musicId = curMediaInfo.getMusicInfoBean().getMusicId();
                LogUtils.e(curMediaInfo.getMusicInfoBean().getName());
                List<MusicInfoBean> list = this.mediaMusicBeanList;
                if (list != null && list.size() > 0) {
                    LogUtils.e(Integer.valueOf(this.mediaMusicBeanList.size()));
                    int i2 = 0;
                    while (true) {
                        if (i2 < this.mediaMusicBeanList.size()) {
                            MusicInfoBean musicInfoBean = this.mediaMusicBeanList.get(i2);
                            if (musicInfoBean != null && musicInfoBean.getMusicId() == musicId) {
                                LogUtils.e(Integer.valueOf(i2));
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
                if (isNotifyDataSetChanged) {
                    this.musicListAdapter.notifyDataSetChanged();
                }
            }
        }
        return i;
    }

    private void updateMediaPlayView(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        List<MusicInfoBean> list;
        if (mediaInfo == null || (list = this.mediaMusicBeanList) == null || list.size() <= 0 || this.musicListAdapter == null) {
            return;
        }
        MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
        long musicId = (mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || musicInfoBean == null || mediaInfo.getMusicInfoBean() == null || mediaInfo.getMusicInfoBean().getContentType() > 2) ? -1L : musicInfoBean.getMusicId();
        int i = -1;
        for (int i2 = 0; i2 < this.mediaMusicBeanList.size(); i2++) {
            MusicInfoBean musicInfoBean2 = this.mediaMusicBeanList.get(i2);
            if (musicInfoBean2 != null && musicInfoBean2.getMusicId() == musicId) {
                if (type == MediaPlayerEnum.MediaInfoCallbackType.favorStatus) {
                    musicInfoBean2.setCollection(musicInfoBean.isCollection());
                    this.mediaMusicBeanList.set(i2, musicInfoBean2);
                }
                i = i2;
            }
        }
        if (type == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
            this.musicListAdapter.setCurrPlayMusicId(musicId);
            int i3 = 0;
            while (true) {
                if (i3 >= this.mediaMusicBeanList.size()) {
                    break;
                }
                MusicInfoBean musicInfoBean3 = this.mediaMusicBeanList.get(i3);
                if (musicId == musicInfoBean3.getMusicId() && i3 != 0) {
                    this.mediaMusicBeanList.remove(musicInfoBean3);
                    this.mediaMusicBeanList.add(0, musicInfoBean3);
                    break;
                }
                i3++;
            }
            this.musicListAdapter.notifyDataSetChanged();
            return;
        }
        if (type != MediaPlayerEnum.MediaInfoCallbackType.favorStatus || i <= -1) {
            return;
        }
        this.musicListAdapter.setData(this.mediaMusicBeanList);
        this.musicListAdapter.notifyDataSetChanged();
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            updatePlayView();
        }
    }

    private void updatePlayView() {
        MusicInfoBean musicInfoBean;
        List<MusicInfoBean> list;
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
        if (this.musicListAdapter != null) {
            if (MediaPlayEngine.getInstance().getMediaPlayerService() != null && MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo() != null && (musicInfoBean = MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo().getMusicInfoBean()) != null && musicInfoBean.getContentType() <= 2 && (list = this.mediaMusicBeanList) != null && list.size() > 0 && this.mediaMusicBeanList.get(0) != null && this.mediaMusicBeanList.get(0).getName() != null && !this.mediaMusicBeanList.get(0).getName().equals(musicInfoBean.getName())) {
                Iterator<MusicInfoBean> it = this.mediaMusicBeanList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MusicInfoBean next = it.next();
                    if (next != null && next.getName().equals(musicInfoBean.getName())) {
                        this.mediaMusicBeanList.remove(next);
                        break;
                    }
                }
                this.mediaMusicBeanList.add(0, musicInfoBean);
            }
            this.musicListAdapter.setCallBackState(callBackState);
            this.musicListAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resourceNotExist(ResourceNotExistEvent event) {
        long mediaId = event.getMediaId();
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null || mediaPlayerService.getCurMediaInfo() == null) {
            return;
        }
        long musicId = this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean().getMusicId();
        if (musicId == mediaId && this.mediaMusicBeanList.size() > 1) {
            for (int i = 0; i < this.mediaMusicBeanList.size(); i++) {
                if (this.mediaMusicBeanList.get(i).getMusicId() == mediaId) {
                    MusicInfoBean musicInfoBean = this.mediaMusicBeanList.get(i);
                    this.mediaMusicBeanList.remove(musicInfoBean);
                    WanosDbUtils.deleteMusicHistory(musicInfoBean, new DbCallBack() { // from class: com.wanos.media.ui.music.MyMusicHistoryListFragment$$ExternalSyntheticLambda0
                        @Override // com.wanos.media.db.DbCallBack
                        public final void callBackData(Object obj) {
                            MyMusicHistoryListFragment.lambda$resourceNotExist$0((Boolean) obj);
                        }
                    });
                }
            }
            this.musicListAdapter.setData(this.mediaMusicBeanList);
            return;
        }
        if (musicId == mediaId && this.mediaMusicBeanList.size() == 1) {
            if (this.mediaMusicBeanList.get(0).getMusicId() == mediaId) {
                MusicInfoBean musicInfoBean2 = this.mediaMusicBeanList.get(0);
                this.mediaMusicBeanList.remove(musicInfoBean2);
                WanosDbUtils.deleteMusicHistory(musicInfoBean2, new DbCallBack() { // from class: com.wanos.media.ui.music.MyMusicHistoryListFragment$$ExternalSyntheticLambda1
                    @Override // com.wanos.media.db.DbCallBack
                    public final void callBackData(Object obj) {
                        MyMusicHistoryListFragment.lambda$resourceNotExist$1((Boolean) obj);
                    }
                });
            }
            List<MusicInfoBean> list = this.mediaMusicBeanList;
            if (list == null || list.size() == 0) {
                this.musicListAdapter.setData(this.mediaMusicBeanList);
                this.binding.meterialAutoRefreshLayout.setVisibility(8);
                this.binding.emptyView.getRoot().setVisibility(0);
                this.binding.emptyView.tvEmpty.setText(R.string.empty_history_music);
                return;
            }
            this.musicListAdapter.setData(this.mediaMusicBeanList);
        }
    }
}
