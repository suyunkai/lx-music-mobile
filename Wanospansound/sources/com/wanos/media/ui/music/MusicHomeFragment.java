package com.wanos.media.ui.music;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.bumptech.glide.Glide;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wanos.WanosCommunication.bean.BannerBean;
import com.wanos.WanosCommunication.bean.ModuleCoverDetailsBean;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.MediaCollectChangeEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.NetworkUtils;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentMusicHomeBinding;
import com.wanos.media.databinding.LayoutMusicHomeRecyclerviewHeaderBinding;
import com.wanos.media.presenter.MusicHomePresenter;
import com.wanos.media.ui.music.activity.MusicGroupListActivity;
import com.wanos.media.ui.music.activity.MusicListActivity;
import com.wanos.media.ui.music.activity.MyMusicActivity;
import com.wanos.media.ui.music.adapter.MusicBannerAdapter;
import com.wanos.media.ui.music.adapter.MusicGroupAdapter;
import com.wanos.media.ui.music.adapter.MusicHomeAdapter;
import com.wanos.media.ui.widget.banner.indicator.RectangleIndicator;
import com.wanos.media.view.MusicHomeView;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicHomeFragment extends WanosBaseFragment<MusicHomePresenter> implements MusicHomeView, View.OnClickListener {
    public static final String TAG = "wanos:[MusicHomeFragment]";
    private List<BannerBean> bannerBeanList;
    private FragmentMusicHomeBinding binding;
    private MusicInfoBean hotMediaMusicBean;
    private LayoutMusicHomeRecyclerviewHeaderBinding layoutMusicHomeRecyclerviewHeaderBinding;
    private MusicBannerAdapter musicBannerAdapter;
    private MusicGroupAdapter musicGroupAdapter;
    private MusicHomeAdapter musicHomeAdapter;
    private MusicInfoBean myMediaMusicBean;
    private List<MusicInfoBean> mediaMusicBeanList = new ArrayList();
    private List<MusicGroupInfo> mediaMusicGroupBeanList = new ArrayList();
    private String freeCover = "";
    private String myMusicCover = "";
    private String myMusicTitle = "";
    private String myMusicContent = "";
    private boolean isFirstLoading = true;

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mPresenter = new MusicHomePresenter(this);
        this.binding = FragmentMusicHomeBinding.inflate(inflater, container, false);
        this.layoutMusicHomeRecyclerviewHeaderBinding = LayoutMusicHomeRecyclerviewHeaderBinding.inflate(inflater, container, false);
        initView();
        ((MusicHomePresenter) this.mPresenter).requestData();
        return this.binding.getRoot();
    }

    private void initView() {
        initTextWeight();
        initHeardView();
        initRefreshLayout();
        initRecyclerView();
        initListener();
        initVisibleIsSpoken();
    }

    private void initRefreshLayout() {
        this.binding.homeMusicRefreshLayout.setEnableRefresh(true);
        this.binding.homeMusicRefreshLayout.setEnableLoadMore(false);
        this.binding.homeMusicRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.wanos.media.ui.music.MusicHomeFragment$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                this.f$0.m522x2fda06cd(refreshLayout);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initRefreshLayout$0$com-wanos-media-ui-music-MusicHomeFragment, reason: not valid java name */
    /* synthetic */ void m522x2fda06cd(RefreshLayout refreshLayout) {
        if (this.mPresenter != 0) {
            ((MusicHomePresenter) this.mPresenter).requestData();
        }
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.layoutMusicHomeRecyclerviewHeaderBinding.cdTopOne.setContentDescription(getResources().getString(R.string.open_card_one));
            this.layoutMusicHomeRecyclerviewHeaderBinding.cdTopTwo.setContentDescription(getResources().getString(R.string.open_card_two));
            this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo.setContentDescription(getResources().getString(R.string.play_free_music_group));
            this.layoutMusicHomeRecyclerviewHeaderBinding.cdTopThree.setContentDescription(getResources().getString(R.string.open_card_three));
            this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicGroupMore.setContentDescription(getResources().getString(R.string.open_music_group_more));
            this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicRankingListMore.setContentDescription(getResources().getString(R.string.open_music_ranking_list_more));
        }
    }

    private void initTextWeight() {
        Util.setTextWeight(this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicGroupMore, 500);
        Util.setTextWeight(this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicRankingListMore, 500);
        if (NetworkUtils.isConnectedAvailableNetwork(getActivity())) {
            return;
        }
        this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicGroupMore.setVisibility(8);
        this.layoutMusicHomeRecyclerviewHeaderBinding.rvRecommendedMusicGroup.setVisibility(8);
        this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicRankingListMore.setVisibility(8);
    }

    private void initListener() {
        this.layoutMusicHomeRecyclerviewHeaderBinding.cdTopTwo.setOnClickListener(this);
        this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayTwo.setOnClickListener(this);
        this.layoutMusicHomeRecyclerviewHeaderBinding.cdTopThree.setOnClickListener(this);
        this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayThree.setOnClickListener(this);
        this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicGroupMore.setOnClickListener(this);
        this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicRankingListMore.setOnClickListener(this);
    }

    private void initHeardView() {
        initHeardTopView();
        initHeardBottomView();
    }

    private void initHeardBottomView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 6);
        MusicGroupAdapter musicGroupAdapter = new MusicGroupAdapter(getActivity(), this.mediaMusicGroupBeanList);
        this.musicGroupAdapter = musicGroupAdapter;
        musicGroupAdapter.setHomePageShow(true);
        this.layoutMusicHomeRecyclerviewHeaderBinding.rvRecommendedMusicGroup.setLayoutManager(gridLayoutManager);
        this.layoutMusicHomeRecyclerviewHeaderBinding.rvRecommendedMusicGroup.setAdapter(this.musicGroupAdapter);
    }

    private void initHeardTopView() {
        initBanner();
        initFreeGroupCover();
    }

    private void initBanner() {
        if (this.bannerBeanList == null) {
            this.bannerBeanList = new ArrayList();
        }
        this.musicBannerAdapter = new MusicBannerAdapter(getActivity(), this.bannerBeanList);
        this.layoutMusicHomeRecyclerviewHeaderBinding.musicHomeBanner.setAdapter(this.musicBannerAdapter);
        this.layoutMusicHomeRecyclerviewHeaderBinding.musicHomeBanner.setIndicator(new RectangleIndicator(getActivity()));
    }

    private void initFreeGroupCover() {
        Glide.with(this).load(this.freeCover).into(this.layoutMusicHomeRecyclerviewHeaderBinding.ivTopTwo);
        Glide.with(this).load(this.myMusicCover).into(this.layoutMusicHomeRecyclerviewHeaderBinding.ivTopThree);
        this.layoutMusicHomeRecyclerviewHeaderBinding.myMusicTitle.setText(this.myMusicTitle);
        this.layoutMusicHomeRecyclerviewHeaderBinding.myMusicContent.setText(this.myMusicContent);
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        final int spanCount = gridLayoutManager.getSpanCount();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.wanos.media.ui.music.MusicHomeFragment.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                if (position == 0) {
                    return spanCount;
                }
                return 1;
            }
        });
        this.binding.recyclerviewMusicHome.setLayoutManager(gridLayoutManager);
        this.musicHomeAdapter = new MusicHomeAdapter(getActivity(), this.mediaMusicBeanList);
        this.binding.recyclerviewMusicHome.setAdapter(this.musicHomeAdapter);
        this.musicHomeAdapter.setHeaderView(this.layoutMusicHomeRecyclerviewHeaderBinding.getRoot());
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
            this.isFirstLoading = false;
        }
    }

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
        closeLoadingView();
        FragmentMusicHomeBinding fragmentMusicHomeBinding = this.binding;
        if (fragmentMusicHomeBinding == null || !fragmentMusicHomeBinding.homeMusicRefreshLayout.isRefreshing()) {
            return;
        }
        this.binding.homeMusicRefreshLayout.finishRefresh();
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
        showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.music.MusicHomeFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m523x7edf3a97(view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$showFailOrError$1$com-wanos-media-ui-music-MusicHomeFragment, reason: not valid java name */
    /* synthetic */ void m523x7edf3a97(View view) {
        if (this.mPresenter != 0) {
            this.isFirstLoading = true;
            ((MusicHomePresenter) this.mPresenter).requestData();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_music_play_three /* 2131362004 */:
                if (UserInfoUtil.isLogin()) {
                    if (this.myMediaMusicBean != null && MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        MediaPlayerEnum.CallBackState callBackState = (MediaPlayerEnum.CallBackState) this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayThree.getTag();
                        if (callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
                            this.hotMediaMusicBean.setPageSize(100);
                            MediaInfo mediaInfo = new MediaInfo();
                            mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                            mediaInfo.setGroupId(-4L);
                            mediaInfo.setMediaGroupType(-4L);
                            mediaInfo.setMusicInfoBean(this.myMediaMusicBean);
                            MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
                        } else if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
                            MediaPlayEngine.getInstance().getMediaPlayerService().pause();
                        }
                        break;
                    }
                } else if (getActivity() != null && (getActivity() instanceof WanosBaseActivity)) {
                    ((WanosBaseActivity) getActivity()).showLoginDialog();
                    break;
                }
                break;
            case R.id.btn_music_play_two /* 2131362005 */:
                if (this.hotMediaMusicBean != null && MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                    MediaPlayerEnum.CallBackState callBackState2 = (MediaPlayerEnum.CallBackState) this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayTwo.getTag();
                    if (callBackState2 == MediaPlayerEnum.CallBackState.PAUSE) {
                        this.hotMediaMusicBean.setPageSize(100);
                        MediaInfo mediaInfo2 = new MediaInfo();
                        mediaInfo2.setMediaType(MediaPlayerEnum.MediaType.Music);
                        mediaInfo2.setGroupId(-8L);
                        mediaInfo2.setMediaGroupType(-8L);
                        mediaInfo2.setMusicInfoBean(this.hotMediaMusicBean);
                        MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo2);
                    } else if (callBackState2 == MediaPlayerEnum.CallBackState.STARTED) {
                        MediaPlayEngine.getInstance().getMediaPlayerService().pause();
                    }
                } else {
                    ToastUtil.showMsg(getString(R.string.no_content_yet));
                }
                break;
            case R.id.cd_top_three /* 2131362077 */:
                MyMusicActivity.startMyMusicActivity(getActivity());
                break;
            case R.id.cd_top_two /* 2131362078 */:
                MusicListActivity.startMusicListActivity(getActivity(), -8L);
                MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_MUSIC_MID_BANNER, "0", "", "", "", 0);
                break;
            case R.id.tv_music_group_more /* 2131363163 */:
                MusicGroupListActivity.startMusicGroupListActivity(getActivity());
                break;
            case R.id.tv_music_ranking_list_more /* 2131363167 */:
                MusicListActivity.startMusicListActivity(getActivity(), -3L);
                break;
        }
    }

    @Override // com.wanos.media.view.MusicHomeView
    public void updateBanner(List<BannerBean> bannerBeanList) {
        this.bannerBeanList = bannerBeanList;
        this.musicBannerAdapter.setDatas(bannerBeanList);
    }

    @Override // com.wanos.media.view.MusicHomeView
    public void updateFreeGroupCover(ModuleCoverDetailsBean freeGroupInfo) {
        this.freeCover = freeGroupInfo.getCoverImage();
        GlideUtil.setHomeImageData(SizeMode.BANNER_HOME, this.freeCover, this.layoutMusicHomeRecyclerviewHeaderBinding.ivTopTwo);
    }

    @Override // com.wanos.media.view.MusicHomeView
    public void updateMyMusicCover(ModuleCoverDetailsBean coverBean) {
        this.myMusicCover = coverBean.getCoverImage();
        this.myMusicTitle = coverBean.getTitle();
        this.myMusicContent = coverBean.getContent();
        GlideUtil.setHomeImageData(SizeMode.BANNER_HOME, this.myMusicCover, this.layoutMusicHomeRecyclerviewHeaderBinding.ivTopThree);
        this.layoutMusicHomeRecyclerviewHeaderBinding.myMusicTitle.setText(this.myMusicTitle);
        this.layoutMusicHomeRecyclerviewHeaderBinding.myMusicContent.setText(this.myMusicContent);
    }

    @Override // com.wanos.media.view.MusicHomeView
    public void updateHotMusicView(MusicInfoBean musicInfoBean) {
        this.hotMediaMusicBean = musicInfoBean;
    }

    @Override // com.wanos.media.view.MusicHomeView
    public void updateMyMusicView(MusicInfoBean musicInfoBean) {
        this.myMediaMusicBean = musicInfoBean;
    }

    @Override // com.wanos.media.view.MusicHomeView
    public void updateRecommedMusicGroupView(List<MusicGroupInfo> mediaMusicGroupBeanList) {
        this.mediaMusicGroupBeanList = mediaMusicGroupBeanList;
        this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicGroupMore.setVisibility(this.mediaMusicGroupBeanList.size() != 0 ? 0 : 8);
        this.layoutMusicHomeRecyclerviewHeaderBinding.rvRecommendedMusicGroup.setVisibility(this.mediaMusicGroupBeanList.size() == 0 ? 8 : 0);
        this.musicGroupAdapter.setData(this.mediaMusicGroupBeanList);
        this.musicGroupAdapter.notifyDataSetChanged();
    }

    @Override // com.wanos.media.view.MusicHomeView
    public void updateRankingMusicView(List<MusicInfoBean> mediaMusicBeanList) {
        this.mediaMusicBeanList = mediaMusicBeanList;
        this.layoutMusicHomeRecyclerviewHeaderBinding.tvMusicRankingListMore.setVisibility(this.mediaMusicBeanList.size() != 0 ? 0 : 8);
        this.musicHomeAdapter.setData(this.mediaMusicBeanList);
        this.musicHomeAdapter.notifyDataSetChanged();
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        this.layoutMusicHomeRecyclerviewHeaderBinding.musicHomeBanner.start();
        setCurPlayMediaId();
        updatePlayView();
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        this.layoutMusicHomeRecyclerviewHeaderBinding.musicHomeBanner.stop();
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        updateMediaPlayView(type, mediaInfo);
    }

    private void setCurPlayMediaId() {
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        if (mediaPlayerService != null) {
            MediaInfo curMediaInfo = mediaPlayerService.getCurMediaInfo();
            long musicId = (curMediaInfo == null || curMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || curMediaInfo.getMusicInfoBean() == null || curMediaInfo.getGroupId() != -3) ? -1L : curMediaInfo.getMusicInfoBean().getMusicId();
            MusicHomeAdapter musicHomeAdapter = this.musicHomeAdapter;
            if (musicHomeAdapter == null || musicHomeAdapter.getCurrPlayMusicId() == musicId) {
                return;
            }
            this.musicHomeAdapter.setCurrPlayMusicId(musicId);
            this.musicHomeAdapter.notifyDataSetChanged();
        }
    }

    private void updateMediaPlayView(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        List<MusicInfoBean> list;
        if (mediaInfo == null || (list = this.mediaMusicBeanList) == null || list.size() <= 0 || this.musicHomeAdapter == null) {
            return;
        }
        MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
        long currPlayMusicId = this.musicHomeAdapter.getCurrPlayMusicId();
        long musicId = (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && musicInfoBean != null && mediaInfo.getGroupId() == -3) ? musicInfoBean.getMusicId() : -1L;
        if (type != MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType || currPlayMusicId == musicId) {
            return;
        }
        this.musicHomeAdapter.setCurrPlayMusicId(musicId);
        this.musicHomeAdapter.notifyDataSetChanged();
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void mediaCollectChange(MediaCollectChangeEvent event) {
        super.mediaCollectChange(event);
        if (event == null || !event.isMusic()) {
            return;
        }
        updateLimitedGroupMusicCollectStatus(event.getId(), event.isCollect());
        updateRecommedGroupMusicCollectStatus(event.getId(), event.isCollect());
        updateRankingMusicCollectStatus(event.getId(), event.isCollect());
    }

    private void updateLimitedGroupMusicCollectStatus(long id, boolean isCollect) {
        MusicInfoBean musicInfoBean = this.hotMediaMusicBean;
        if (musicInfoBean == null || id != musicInfoBean.getMusicId()) {
            return;
        }
        this.hotMediaMusicBean.setCollection(isCollect);
    }

    private void updateRecommedGroupMusicCollectStatus(long id, boolean isCollect) {
        List<MusicGroupInfo> list = this.mediaMusicGroupBeanList;
        if (list == null || list.size() <= 0) {
            return;
        }
        boolean z = false;
        for (int i = 0; i < this.mediaMusicGroupBeanList.size(); i++) {
            MusicGroupInfo musicGroupInfo = this.mediaMusicGroupBeanList.get(i);
            if (musicGroupInfo != null && musicGroupInfo.getMusicList() != null && musicGroupInfo.getMusicList().size() > 0 && musicGroupInfo.getMusicList().get(0) != null && id == musicGroupInfo.getMusicList().get(0).getMusicId()) {
                List<MusicInfoBean> musicList = musicGroupInfo.getMusicList();
                MusicInfoBean musicInfoBean = musicList.get(0);
                musicInfoBean.setCollection(isCollect);
                musicList.set(0, musicInfoBean);
                musicGroupInfo.setMusicList(musicList);
                this.mediaMusicGroupBeanList.set(i, musicGroupInfo);
                z = true;
            }
        }
        if (z) {
            this.musicGroupAdapter.setData(this.mediaMusicGroupBeanList);
            this.musicGroupAdapter.notifyDataSetChanged();
        }
    }

    private void updateRankingMusicCollectStatus(long id, boolean isCollect) {
        if (this.mediaMusicBeanList != null) {
            boolean z = false;
            for (int i = 0; i < this.mediaMusicBeanList.size(); i++) {
                MusicInfoBean musicInfoBean = this.mediaMusicBeanList.get(i);
                if (musicInfoBean != null && musicInfoBean.getMusicId() == id) {
                    musicInfoBean.setCollection(isCollect);
                    this.mediaMusicBeanList.set(i, musicInfoBean);
                    z = true;
                }
            }
            if (z) {
                this.musicHomeAdapter.setData(this.mediaMusicBeanList);
                this.musicHomeAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void loginOrLogout(LoginOrLogoutEvent event) {
        super.loginOrLogout(event);
        if (!event.isLogin() || this.mPresenter == 0) {
            return;
        }
        ((MusicHomePresenter) this.mPresenter).requestMyMusicList();
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            updatePlayView();
        }
    }

    private void updatePlayView() {
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
        MusicHomeAdapter musicHomeAdapter = this.musicHomeAdapter;
        if (musicHomeAdapter != null) {
            musicHomeAdapter.setCallBackState(callBackState);
            this.musicHomeAdapter.notifyDataSetChanged();
        }
        if (MediaPlayEngine.getInstance().getMediaPlayerService() == null || MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo() == null || MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Music) {
            groupId = -100;
            mediaGroupType = -100;
        } else {
            groupId = MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo().getGroupId();
            mediaGroupType = MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo().getMediaGroupType();
        }
        if (groupId == -8) {
            if (callBackState == MediaPlayerEnum.CallBackState.PREPARING) {
                this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo.setVisibility(8);
                this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayTwo.setVisibility(0);
            } else if (callBackState == MediaPlayerEnum.CallBackState.PAUSING || callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
                this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo.setImageResource(R.drawable.group_card_pause);
                this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo.setVisibility(0);
                this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayTwo.setVisibility(8);
            } else if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
                GlideUtil.setImageGifData(R.drawable.ic_playing_2, this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo);
                this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo.setVisibility(0);
                this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayTwo.setVisibility(8);
            }
            this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayThree.setImageResource(R.drawable.group_card_pause);
            this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayThree.setVisibility(0);
            this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayThree.setVisibility(8);
            this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayTwo.setTag(callBackState);
            this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayThree.setTag(MediaPlayerEnum.CallBackState.PAUSE);
        } else if (groupId == -4) {
            if (callBackState == MediaPlayerEnum.CallBackState.PREPARING) {
                this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayThree.setVisibility(8);
                this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayTwo.setVisibility(0);
            } else if (callBackState == MediaPlayerEnum.CallBackState.PAUSING || callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
                this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayThree.setImageResource(R.drawable.group_card_pause);
                this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayThree.setVisibility(0);
                this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayTwo.setVisibility(8);
            } else if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
                GlideUtil.setImageGifData(R.drawable.ic_playing_2, this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayThree);
                this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayThree.setVisibility(0);
                this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayTwo.setVisibility(8);
            }
            this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo.setImageResource(R.drawable.group_card_pause);
            this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo.setVisibility(0);
            this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayTwo.setVisibility(8);
            this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayTwo.setTag(MediaPlayerEnum.CallBackState.PAUSE);
            this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayThree.setTag(callBackState);
        } else {
            this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo.setImageResource(R.drawable.group_card_pause);
            this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayTwo.setVisibility(0);
            this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayTwo.setVisibility(8);
            this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayThree.setImageResource(R.drawable.group_card_pause);
            this.layoutMusicHomeRecyclerviewHeaderBinding.ivMusicPlayThree.setVisibility(0);
            this.layoutMusicHomeRecyclerviewHeaderBinding.pbMusicPlayThree.setVisibility(8);
            this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayTwo.setTag(MediaPlayerEnum.CallBackState.PAUSE);
            this.layoutMusicHomeRecyclerviewHeaderBinding.btnMusicPlayThree.setTag(MediaPlayerEnum.CallBackState.PAUSE);
        }
        MusicGroupAdapter musicGroupAdapter = this.musicGroupAdapter;
        if (musicGroupAdapter != null) {
            musicGroupAdapter.setCurMusicGroupIdAndType(groupId, mediaGroupType);
            this.musicGroupAdapter.setCallBackState(callBackState);
            this.musicGroupAdapter.notifyDataSetChanged();
        }
    }
}
