package com.wanos.media.ui.audiobook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.recyclerview.widget.GridLayoutManager;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.bean.BannerBean;
import com.wanos.WanosCommunication.bean.ModuleCoverDetailsBean;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.NetworkUtils;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentAudioBookHomeBinding;
import com.wanos.media.databinding.LayoutAudioBookHomeRecyclerviewHeaderBinding;
import com.wanos.media.presenter.AudioBookHomePresenter;
import com.wanos.media.ui.audiobook.activity.AudioBookAlbumListActivity;
import com.wanos.media.ui.audiobook.activity.AudioBookChildrenAlbumListActivity;
import com.wanos.media.ui.audiobook.activity.AudioBookMineActivity;
import com.wanos.media.ui.audiobook.adapter.AudioBookHomeAdapter;
import com.wanos.media.ui.music.adapter.MusicBannerAdapter;
import com.wanos.media.ui.widget.banner.indicator.RectangleIndicator;
import com.wanos.media.view.AudioBookHomeView;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookHomeFragment extends WanosBaseFragment<AudioBookHomePresenter> implements AudioBookHomeView, View.OnClickListener {
    private AudioBookHomeAdapter audioBookHomeAdapter;
    private List<BannerBean> bannerBeanList;
    private FragmentAudioBookHomeBinding binding;
    private LayoutAudioBookHomeRecyclerviewHeaderBinding layoutAudioBookHomeRecyclerviewHeaderBinding;
    private MusicBannerAdapter musicBannerAdapter;
    private List<AudioBookAlbumInfoBean> audioBookAlbumInfoBeanList = new ArrayList();
    private boolean isFirstLoading = true;
    private String childrenCover = "";
    private String myAudioBookCover = "";
    private String childrenTitle = "";
    private String myAudioBookTitle = "";
    private String childrenContent = "";
    private String myAudioBookContent = "";

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentAudioBookHomeBinding fragmentAudioBookHomeBindingInflate = FragmentAudioBookHomeBinding.inflate(inflater, container, false);
        this.binding = fragmentAudioBookHomeBindingInflate;
        ConstraintLayout root = fragmentAudioBookHomeBindingInflate.getRoot();
        this.mPresenter = new AudioBookHomePresenter(getContext(), this);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding = LayoutAudioBookHomeRecyclerviewHeaderBinding.inflate(inflater, container, false);
        initData();
        initView();
        initListener();
        initVisibleIsSpoken();
        return root;
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.layoutAudioBookHomeRecyclerviewHeaderBinding.cdTopOne.setContentDescription(getResources().getString(R.string.open_audiobook_card_one));
            this.layoutAudioBookHomeRecyclerviewHeaderBinding.cdTopTwo.setContentDescription(getResources().getString(R.string.open_audiobook_card_two));
            this.layoutAudioBookHomeRecyclerviewHeaderBinding.cdTopThree.setContentDescription(getResources().getString(R.string.open_audiobook_card_three));
            this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvAlbumAll.setContentDescription(getResources().getString(R.string.open_audio_book_more));
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LayoutAudioBookHomeRecyclerviewHeaderBinding layoutAudioBookHomeRecyclerviewHeaderBinding = this.layoutAudioBookHomeRecyclerviewHeaderBinding;
        if (layoutAudioBookHomeRecyclerviewHeaderBinding != null) {
            layoutAudioBookHomeRecyclerviewHeaderBinding.abHomeBanner.stop();
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setCallBackState();
        LayoutAudioBookHomeRecyclerviewHeaderBinding layoutAudioBookHomeRecyclerviewHeaderBinding = this.layoutAudioBookHomeRecyclerviewHeaderBinding;
        if (layoutAudioBookHomeRecyclerviewHeaderBinding != null) {
            layoutAudioBookHomeRecyclerviewHeaderBinding.abHomeBanner.start();
        }
    }

    public void initData() {
        if (this.isFirstLoading) {
            showLoadingView();
            this.isFirstLoading = false;
        }
        ((AudioBookHomePresenter) this.mPresenter).requestData();
    }

    public void initView() {
        Util.setTextWeight(this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvChildren, 700);
        Util.setTextWeight(this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvMine, 700);
        Util.setTextWeight(this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvAlbumAll, 500);
        if (!NetworkUtils.isConnectedAvailableNetwork(getActivity())) {
            this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvAlbumAll.setVisibility(8);
        }
        initRefreshLayout();
        initRecyclerView();
        initBanner();
        initCardCover();
        setCallBackState();
    }

    private void initRefreshLayout() {
        this.binding.homeAudioBookRefreshLayout.setEnableRefresh(true);
        this.binding.homeAudioBookRefreshLayout.setEnableLoadMore(false);
        this.binding.homeAudioBookRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.wanos.media.ui.audiobook.AudioBookHomeFragment$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                this.f$0.m490xfee252a1(refreshLayout);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initRefreshLayout$0$com-wanos-media-ui-audiobook-AudioBookHomeFragment, reason: not valid java name */
    /* synthetic */ void m490xfee252a1(RefreshLayout refreshLayout) {
        if (this.mPresenter != 0) {
            ((AudioBookHomePresenter) this.mPresenter).requestData();
        }
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 6);
        final int spanCount = gridLayoutManager.getSpanCount();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.wanos.media.ui.audiobook.AudioBookHomeFragment.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                if (position == 0) {
                    return spanCount;
                }
                return 1;
            }
        });
        this.binding.recyclerviewAudioBookHome.setLayoutManager(gridLayoutManager);
        this.audioBookHomeAdapter = new AudioBookHomeAdapter(getActivity(), this.audioBookAlbumInfoBeanList);
        this.binding.recyclerviewAudioBookHome.setAdapter(this.audioBookHomeAdapter);
        this.audioBookHomeAdapter.setHeaderView(this.layoutAudioBookHomeRecyclerviewHeaderBinding.getRoot());
    }

    private void initBanner() {
        if (this.bannerBeanList == null) {
            this.bannerBeanList = new ArrayList();
        }
        this.musicBannerAdapter = new MusicBannerAdapter(getActivity(), this.bannerBeanList);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.abHomeBanner.setAdapter(this.musicBannerAdapter);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.abHomeBanner.setIndicator(new RectangleIndicator(getActivity()));
    }

    private void initCardCover() {
        GlideUtil.setHomeImageData(SizeMode.BANNER_HOME, this.childrenCover, this.layoutAudioBookHomeRecyclerviewHeaderBinding.ivTopTwo);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvChildren.setText(this.childrenTitle);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvChildrenContent.setText(this.childrenContent);
        GlideUtil.setHomeImageData(SizeMode.BANNER_HOME, this.myAudioBookCover, this.layoutAudioBookHomeRecyclerviewHeaderBinding.ivTopThree);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvMine.setText(this.myAudioBookTitle);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvMineContent.setText(this.myAudioBookContent);
    }

    @Override // com.wanos.media.view.AudioBookHomeView
    public void updateView(List<AudioBookAlbumInfoBean> beanList) {
        closeLoadingView();
        FragmentAudioBookHomeBinding fragmentAudioBookHomeBinding = this.binding;
        if (fragmentAudioBookHomeBinding != null && fragmentAudioBookHomeBinding.homeAudioBookRefreshLayout.isRefreshing()) {
            this.binding.homeAudioBookRefreshLayout.finishRefresh();
        }
        this.audioBookAlbumInfoBeanList = beanList;
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvAlbumAll.setVisibility(beanList.size() != 0 ? 0 : 8);
        if (beanList.size() > 12) {
            this.audioBookAlbumInfoBeanList = beanList.subList(0, 12);
        } else {
            this.audioBookAlbumInfoBeanList = beanList;
        }
        this.audioBookHomeAdapter.setData(beanList);
        this.audioBookHomeAdapter.notifyDataSetChanged();
    }

    @Override // com.wanos.media.view.AudioBookHomeView
    public void updateBanner(List<BannerBean> bannerBeanList) {
        this.bannerBeanList = bannerBeanList;
        this.musicBannerAdapter.setDatas(bannerBeanList);
        this.musicBannerAdapter.notifyDataSetChanged();
    }

    @Override // com.wanos.media.view.AudioBookHomeView
    public void updateChildrenCover(ModuleCoverDetailsBean childrenBean) {
        this.childrenCover = childrenBean.getCoverImage();
        this.childrenTitle = childrenBean.getTitle();
        this.childrenContent = childrenBean.getContent();
        GlideUtil.setHomeImageData(SizeMode.BANNER_HOME, this.childrenCover, this.layoutAudioBookHomeRecyclerviewHeaderBinding.ivTopTwo);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvChildren.setText(this.childrenTitle);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvChildrenContent.setText(this.childrenContent);
    }

    @Override // com.wanos.media.view.AudioBookHomeView
    public void updateMyAudioBookCover(ModuleCoverDetailsBean myAudioBookBean) {
        this.myAudioBookCover = myAudioBookBean.getCoverImage();
        this.myAudioBookTitle = myAudioBookBean.getTitle();
        this.myAudioBookContent = myAudioBookBean.getContent();
        GlideUtil.setHomeImageData(SizeMode.BANNER_HOME, this.myAudioBookCover, this.layoutAudioBookHomeRecyclerviewHeaderBinding.ivTopThree);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvMine.setText(this.myAudioBookTitle);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvMineContent.setText(this.myAudioBookContent);
    }

    public void initListener() {
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.tvAlbumAll.setOnClickListener(this);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.cdTopTwo.setOnClickListener(this);
        this.layoutAudioBookHomeRecyclerviewHeaderBinding.cdTopThree.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cd_top_three /* 2131362077 */:
                AudioBookMineActivity.build(getContext());
                break;
            case R.id.cd_top_two /* 2131362078 */:
                AudioBookChildrenAlbumListActivity.build(getContext());
                MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_AUDIO_BOOK_MID_BANNER, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "", "", "", 0);
                break;
            case R.id.tv_album_all /* 2131363056 */:
                AudioBookAlbumListActivity.build(getContext(), AudioBookAlbumListActivity.TYPE.ALL);
                break;
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
        showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.audiobook.AudioBookHomeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AudioBookHomeFragment.this.isFirstLoading = true;
                AudioBookHomeFragment.this.initData();
            }
        });
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        super.onStatusonStatusCallbackNext(status, args);
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSING || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            setCallBackState();
        }
    }

    public void setCallBackState() {
        AudioBookMineChapterItemBean playingItemBean = AudioBookGlobalParams.getPlayingItemBean();
        this.audioBookHomeAdapter.setCallBackState(AudioBookGlobalParams.getPlayingStatus(), playingItemBean != null ? playingItemBean.getRadioId() : -1L);
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent event) {
        initData();
    }
}
