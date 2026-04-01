package com.wanos.media.ui.search.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.service.Result;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.fragment.LoadingController;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentResultListBinding;
import com.wanos.media.ui.search.result.adapter.ResultSongAdapter;
import com.wanos.media.ui.search.viewmodel.ResultViewModel;
import com.wanos.media.util.LoadingUtil;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class ResultMusicFragment extends WanosBaseFragment {
    private FragmentResultListBinding binding;
    private LoadingController controller;
    private EditText et;
    private InputMethodManager imm;
    private LinearLayoutManager lManager;
    private ResultSongAdapter songAdapter;
    private ResultViewModel viewModel;

    @Override // com.wanos.media.base.WanosBaseFragment
    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return true;
    }

    public ResultMusicFragment() {
    }

    public ResultMusicFragment(EditText et) {
        this.et = et;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel = (ResultViewModel) new ViewModelProvider(getActivity()).get(ResultViewModel.class);
        this.binding = FragmentResultListBinding.inflate(inflater, container, false);
        initView();
        return this.binding.getRoot();
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setCurPlayMediaId(true);
        updatePlayView();
    }

    private void initView() {
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
        initLoadingController();
        initRefreshLayout();
        initRecyclerView();
        onHideSoftInput();
        this.viewModel.musicList.observe(this, new Observer() { // from class: com.wanos.media.ui.search.result.ResultMusicFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m548x659a5093((Result) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$1$com-wanos-media-ui-search-result-ResultMusicFragment, reason: not valid java name */
    /* synthetic */ void m548x659a5093(Result result) {
        if (result == null) {
            this.binding.resultListCy.scrollToPosition(0);
            this.binding.searchResultRefreshLayout.setLoadMore(false);
            return;
        }
        showLoadingView();
        if (result.status == Result.Status.ERROR) {
            hideLoading();
            showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.search.result.ResultMusicFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m547x4b7ed1f4(view);
                }
            });
        } else if (result.status == Result.Status.SUCCESS) {
            hideLoading();
            if (((List) result.data).isEmpty()) {
                this.controller.showEmpty(true);
                return;
            }
            if (((List) result.data).size() >= 50) {
                this.binding.searchResultRefreshLayout.setLoadMore(true);
            }
            this.songAdapter.setData((List) result.data);
            this.songAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-media-ui-search-result-ResultMusicFragment, reason: not valid java name */
    /* synthetic */ void m547x4b7ed1f4(View view) {
        showLoadingView();
        this.viewModel.requestData();
    }

    private void hideLoading() {
        closeLoadingView();
        this.controller.dismissLoading();
        this.binding.searchResultRefreshLayout.finishRefresh();
        this.binding.searchResultRefreshLayout.finishRefreshLoadMore();
    }

    private void initLoadingController() {
        this.controller = LoadingUtil.initCommonLoadingView(getActivity().getApplicationContext(), this.binding.searchResultRefreshLayout, R.string.loading_message, R.drawable.ic_search_empty, R.string.search_empty_message);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.lManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.binding.resultListCy.setLayoutManager(this.lManager);
        this.songAdapter = new ResultSongAdapter(getActivity(), new ArrayList());
        this.binding.resultListCy.setAdapter(this.songAdapter);
    }

    private void initRefreshLayout() {
        this.binding.searchResultRefreshLayout.setLoadMore(false);
        this.binding.searchResultRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.search.result.ResultMusicFragment.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                ResultMusicFragment.this.viewModel.searchMusic(false);
            }
        });
    }

    private void onHideSoftInput() {
        this.binding.resultListCy.setOnTouchListener(new View.OnTouchListener() { // from class: com.wanos.media.ui.search.result.ResultMusicFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m549xa50d9922(view, motionEvent);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onHideSoftInput$2$com-wanos-media-ui-search-result-ResultMusicFragment, reason: not valid java name */
    /* synthetic */ boolean m549xa50d9922(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || getActivity().getCurrentFocus() == null || getActivity().getCurrentFocus().getWindowToken() == null) {
            return false;
        }
        hideSoftInput();
        return false;
    }

    private void hideSoftInput() {
        if (this.et.hasFocus()) {
            this.imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
            this.et.clearFocus();
        }
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
                Result<List<MusicInfoBean>> value = this.viewModel.musicList.getValue();
                if (value.data != null && value.data.size() > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 < value.data.size()) {
                            MusicInfoBean musicInfoBean = value.data.get(i2);
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
            ResultSongAdapter resultSongAdapter = this.songAdapter;
            if (resultSongAdapter != null) {
                resultSongAdapter.setCurrPlayMusicId(musicId);
                if (isNotifyDataSetChanged) {
                    this.songAdapter.notifyDataSetChanged();
                }
            }
        }
        return i;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        super.onMediaInfoCallbackAppNext(type, mediaInfo);
        updateMediaPlayView(type, mediaInfo);
    }

    private void updateMediaPlayView(MediaPlayerEnum.MediaInfoCallbackType type, MediaInfo mediaInfo) {
        Result<List<MusicInfoBean>> value = this.viewModel.musicList.getValue();
        if (mediaInfo == null || value.data == null || value.data.size() <= 0 || this.songAdapter == null) {
            return;
        }
        MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
        long currPlayMusicId = this.songAdapter.getCurrPlayMusicId();
        long musicId = (mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || musicInfoBean == null) ? -1L : musicInfoBean.getMusicId();
        int i = -1;
        for (int i2 = 0; i2 < value.data.size(); i2++) {
            MusicInfoBean musicInfoBean2 = value.data.get(i2);
            if (musicInfoBean2 != null && mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && musicInfoBean2.getMusicId() == musicId) {
                if (type == MediaPlayerEnum.MediaInfoCallbackType.favorStatus) {
                    musicInfoBean2.setCollection(musicInfoBean.isCollection());
                    value.data.set(i2, musicInfoBean2);
                }
                i = i2;
            }
        }
        if (type == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
            if (currPlayMusicId != musicId) {
                this.songAdapter.setCurrPlayMusicId(musicId);
                this.songAdapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        if (type != MediaPlayerEnum.MediaInfoCallbackType.favorStatus || i <= -1) {
            return;
        }
        this.songAdapter.setData(value.data);
        this.songAdapter.notifyDataSetChanged();
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
        ResultSongAdapter resultSongAdapter = this.songAdapter;
        if (resultSongAdapter != null) {
            resultSongAdapter.setCallBackState(callBackState);
            this.songAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent event) {
        ResultViewModel resultViewModel = this.viewModel;
        if (resultViewModel != null) {
            resultViewModel.requestData();
        }
    }
}
