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
import androidx.recyclerview.widget.GridLayoutManager;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.service.Result;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.event.MediaCollectChangeEvent;
import com.wanos.commonlibrary.fragment.LoadingController;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentResultListBinding;
import com.wanos.media.ui.search.result.adapter.ResultSongListAdapter;
import com.wanos.media.ui.search.viewmodel.ResultViewModel;
import com.wanos.media.util.LoadingUtil;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ResultMusicGroupFragment extends WanosBaseFragment {
    private FragmentResultListBinding binding;
    private LoadingController controller;
    private EditText et;
    private GridLayoutManager gridManager;
    private InputMethodManager imm;
    private ResultSongListAdapter songListAdapter;
    private ResultViewModel viewModel;

    @Override // com.wanos.media.base.WanosBaseFragment
    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return true;
    }

    public ResultMusicGroupFragment() {
    }

    public ResultMusicGroupFragment(EditText et) {
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
        updateGroupPlayView(true);
    }

    private void initView() {
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
        initLoadingController();
        initRefreshLayout();
        initRecyclerView();
        onHideSoftInput();
        this.viewModel.musicGroupList.observe(this, new Observer() { // from class: com.wanos.media.ui.search.result.ResultMusicGroupFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m551xd9cd518c((Result) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$1$com-wanos-media-ui-search-result-ResultMusicGroupFragment, reason: not valid java name */
    /* synthetic */ void m551xd9cd518c(Result result) {
        if (result == null) {
            this.binding.resultListCy.scrollToPosition(0);
            this.binding.searchResultRefreshLayout.setLoadMore(false);
            return;
        }
        showLoadingView();
        if (result.status == Result.Status.ERROR) {
            hideLoading();
            showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.search.result.ResultMusicGroupFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m550xc91784cb(view);
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
            this.songListAdapter.setData((List) result.data);
            this.songListAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-media-ui-search-result-ResultMusicGroupFragment, reason: not valid java name */
    /* synthetic */ void m550xc91784cb(View view) {
        showLoadingView();
        this.viewModel.requestData();
    }

    private void initLoadingController() {
        this.controller = LoadingUtil.initCommonLoadingView(getActivity().getApplicationContext(), this.binding.searchResultRefreshLayout, R.string.loading_message, R.drawable.ic_search_empty, R.string.search_empty_message);
    }

    private void hideLoading() {
        closeLoadingView();
        this.controller.dismissLoading();
        this.binding.searchResultRefreshLayout.finishRefresh();
        this.binding.searchResultRefreshLayout.finishRefreshLoadMore();
    }

    private void initRecyclerView() {
        this.gridManager = new GridLayoutManager(getActivity(), 6);
        this.binding.resultListCy.setLayoutManager(this.gridManager);
        this.songListAdapter = new ResultSongListAdapter(getActivity(), new ArrayList());
        this.binding.resultListCy.setAdapter(this.songListAdapter);
    }

    private void initRefreshLayout() {
        this.binding.searchResultRefreshLayout.setLoadMore(false);
        this.binding.searchResultRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.search.result.ResultMusicGroupFragment.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                ResultMusicGroupFragment.this.viewModel.searchMusicGroup(false);
            }
        });
    }

    private void onHideSoftInput() {
        this.binding.resultListCy.setOnTouchListener(new View.OnTouchListener() { // from class: com.wanos.media.ui.search.result.ResultMusicGroupFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m552x1b2c0a5d(view, motionEvent);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onHideSoftInput$2$com-wanos-media-ui-search-result-ResultMusicGroupFragment, reason: not valid java name */
    /* synthetic */ boolean m552x1b2c0a5d(View view, MotionEvent motionEvent) {
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
        ResultSongListAdapter resultSongListAdapter = this.songListAdapter;
        if (resultSongListAdapter != null) {
            resultSongListAdapter.setCurMusicGroupIdAndType(groupId, mediaGroupType);
            this.songListAdapter.setCallBackState(callBackState);
            if (isNotifyDataSetChanged) {
                this.songListAdapter.notifyDataSetChanged();
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
        List<MusicGroupInfo> list = this.viewModel.musicGroupList.getValue().data;
        if (list == null || list.size() <= 0) {
            return;
        }
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            MusicGroupInfo musicGroupInfo = list.get(i);
            if (musicGroupInfo != null && musicGroupInfo.getMusicList() != null && musicGroupInfo.getMusicList().size() > 0 && musicGroupInfo.getMusicList().get(0) != null && id == musicGroupInfo.getMusicList().get(0).getMusicId()) {
                List<MusicInfoBean> musicList = musicGroupInfo.getMusicList();
                MusicInfoBean musicInfoBean = musicList.get(0);
                musicInfoBean.setCollection(isCollect);
                musicList.set(0, musicInfoBean);
                musicGroupInfo.setMusicList(musicList);
                list.set(i, musicGroupInfo);
                z = true;
            }
        }
        if (z) {
            this.songListAdapter.setData(list);
            this.songListAdapter.notifyDataSetChanged();
        }
    }
}
