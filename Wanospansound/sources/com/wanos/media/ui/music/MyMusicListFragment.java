package com.wanos.media.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.event.CollectChange2ColEvent;
import com.wanos.commonlibrary.event.CollectChange2HisEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentMusicListBinding;
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
public class MyMusicListFragment extends WanosBaseFragment<MusicListPresenter> implements MusicListView {
    private FragmentMusicListBinding binding;
    private MusicListAdapter musicListAdapter;
    private List<MusicInfoBean> mediaMusicBeanList = new ArrayList();
    private int page = 1;
    private boolean isFirstLoading = true;

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
        this.mPresenter = new MusicListPresenter(this, -4L);
        this.binding = FragmentMusicListBinding.inflate(inflater, container, false);
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
        this.binding.meterialAutoRefreshLayout.setStartLoadMoreItemThreshold(30);
        this.binding.meterialAutoRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.music.MyMusicListFragment.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                MyMusicListFragment.this.isFirstLoading = true;
                MyMusicListFragment.this.page = 1;
                if (MyMusicListFragment.this.mPresenter == null || MyMusicListFragment.this.getActivity() == null) {
                    return;
                }
                ((MusicListPresenter) MyMusicListFragment.this.mPresenter).requestData(MyMusicListFragment.this.page, MyMusicListFragment.this.getActivity());
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                MyMusicListFragment.this.isFirstLoading = false;
                int i = MyMusicListFragment.this.page + 1;
                if (MyMusicListFragment.this.mPresenter == null || MyMusicListFragment.this.getActivity() == null) {
                    return;
                }
                ((MusicListPresenter) MyMusicListFragment.this.mPresenter).requestData(i, MyMusicListFragment.this.getActivity());
            }
        });
    }

    private void initRecyclerView() {
        this.musicListAdapter = new MusicListAdapter(getActivity(), this.mediaMusicBeanList, -4L, -4L);
        this.binding.recyclerviewMusicList.setAdapter(this.musicListAdapter);
        this.binding.recyclerviewMusicList.addItemDecoration(new SpaceItemDecoration(BannerUtils.dp2px(40.0f)));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshShowEmptyState(CollectChange2HisEvent msg) {
        List<MusicInfoBean> list = this.mediaMusicBeanList;
        if (list != null && !list.isEmpty()) {
            Iterator<MusicInfoBean> it = this.mediaMusicBeanList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MusicInfoBean next = it.next();
                if (next.getMusicId() == msg.getMusicId()) {
                    this.mediaMusicBeanList.remove(next);
                    break;
                }
            }
        }
        List<MusicInfoBean> list2 = this.mediaMusicBeanList;
        if (list2 == null || list2.size() == 0) {
            this.binding.meterialAutoRefreshLayout.setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            this.binding.emptyView.tvEmpty.setText(R.string.empty_music);
            return;
        }
        this.binding.meterialAutoRefreshLayout.setVisibility(0);
        this.binding.emptyView.getRoot().setVisibility(8);
        List<MusicInfoBean> list3 = this.mediaMusicBeanList;
        if (list3 == null || list3.size() == 0) {
            return;
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshCollectState(CollectChange2ColEvent msg) {
        this.binding.meterialAutoRefreshLayout.setLoadMore(true);
        this.binding.meterialAutoRefreshLayout.autoRefresh();
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
            showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.music.MyMusicListFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    MyMusicListFragment.this.binding.meterialAutoRefreshLayout.autoRefresh();
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
                this.binding.emptyView.tvEmpty.setText(R.string.empty_music);
            } else {
                this.binding.meterialAutoRefreshLayout.setVisibility(0);
                this.binding.emptyView.getRoot().setVisibility(8);
            }
            setCurPlayMediaId(true);
            updateIsCanLoadMore(mediaMusicBeanList.size());
        }
    }

    @Override // com.wanos.media.view.MusicListView
    public void addDataView(int page, List<MusicInfoBean> mediausicBeans) {
        this.page = page;
        if (mediausicBeans != null) {
            this.mediaMusicBeanList.addAll(mediausicBeans);
            this.musicListAdapter.setData(this.mediaMusicBeanList);
            this.musicListAdapter.notifyDataSetChanged();
        }
        updateIsCanLoadMore(mediausicBeans == null ? 0 : mediausicBeans.size());
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
                List<MusicInfoBean> list = this.mediaMusicBeanList;
                if (list != null && list.size() > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 < this.mediaMusicBeanList.size()) {
                            MusicInfoBean musicInfoBean = this.mediaMusicBeanList.get(i2);
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
                if (isNotifyDataSetChanged) {
                    this.musicListAdapter.notifyDataSetChanged();
                }
            }
        }
        return i;
    }

    private void updateMediaPlayView(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        if (mediaInfo == null || this.mediaMusicBeanList == null || this.musicListAdapter == null) {
            return;
        }
        MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
        long musicId = (mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || musicInfoBean == null) ? -1L : musicInfoBean.getMusicId();
        if (type == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
            this.musicListAdapter.setCurrPlayMusicId(musicId);
            this.musicListAdapter.notifyDataSetChanged();
            return;
        }
        if (type != MediaPlayerEnum.MediaInfoCallbackType.favorStatus || musicId == -1) {
            return;
        }
        if (this.mediaMusicBeanList.size() == 0) {
            if (musicInfoBean.isCollection()) {
                this.mediaMusicBeanList.add(0, musicInfoBean);
                this.musicListAdapter.notifyDataSetChanged();
            }
        } else {
            int i = -1;
            for (int i2 = 0; i2 < this.mediaMusicBeanList.size(); i2++) {
                MusicInfoBean musicInfoBean2 = this.mediaMusicBeanList.get(i2);
                if (musicInfoBean2 != null && mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && musicInfoBean2.getMusicId() == musicId) {
                    i = i2;
                }
            }
            if (musicInfoBean.isCollection()) {
                if (i <= -1) {
                    this.mediaMusicBeanList.add(0, musicInfoBean);
                    this.musicListAdapter.notifyDataSetChanged();
                }
            } else if (i > -1) {
                this.mediaMusicBeanList.remove(i);
                this.musicListAdapter.notifyDataSetChanged();
            }
        }
        List<MusicInfoBean> list = this.mediaMusicBeanList;
        if (list == null || list.size() == 0) {
            this.binding.meterialAutoRefreshLayout.setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            this.binding.emptyView.tvEmpty.setText(R.string.empty_music);
        } else {
            this.binding.meterialAutoRefreshLayout.setVisibility(0);
            this.binding.emptyView.getRoot().setVisibility(8);
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
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
}
