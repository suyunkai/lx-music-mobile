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
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.service.Result;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.fragment.LoadingController;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentResultListBinding;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.search.result.adapter.ResultAudioBookAdapter;
import com.wanos.media.ui.search.viewmodel.ResultViewModel;
import com.wanos.media.util.LoadingUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ResultAudioBookFragment extends WanosBaseFragment {
    private ResultAudioBookAdapter audioBookAdapter;
    private FragmentResultListBinding binding;
    private LoadingController controller;
    private EditText et;
    private GridLayoutManager gridManager;
    private InputMethodManager imm;
    private ResultViewModel viewModel;

    @Override // com.wanos.media.base.WanosBaseFragment
    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return true;
    }

    public ResultAudioBookFragment() {
    }

    public ResultAudioBookFragment(EditText et) {
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
        setCallBackState();
    }

    private void initView() {
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
        initLoadingController();
        initRefreshLayout();
        initRecyclerView();
        onHideSoftInput();
        setCallBackState();
        this.viewModel.audioBookList.observe(this, new Observer() { // from class: com.wanos.media.ui.search.result.ResultAudioBookFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m545x2fadc94d((Result) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$1$com-wanos-media-ui-search-result-ResultAudioBookFragment, reason: not valid java name */
    /* synthetic */ void m545x2fadc94d(Result result) {
        if (result == null) {
            this.binding.resultListCy.scrollToPosition(0);
            this.binding.searchResultRefreshLayout.setLoadMore(false);
            return;
        }
        showLoadingView();
        if (result.status == Result.Status.ERROR) {
            hideLoading();
            showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.search.result.ResultAudioBookFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m544xa2c0b22e(view);
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
            this.audioBookAdapter.setData((List) result.data);
            this.audioBookAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-media-ui-search-result-ResultAudioBookFragment, reason: not valid java name */
    /* synthetic */ void m544xa2c0b22e(View view) {
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
        this.audioBookAdapter = new ResultAudioBookAdapter(getActivity(), new ArrayList());
        this.binding.resultListCy.setAdapter(this.audioBookAdapter);
    }

    private void initRefreshLayout() {
        this.binding.searchResultRefreshLayout.setLoadMore(false);
        this.binding.searchResultRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.search.result.ResultAudioBookFragment.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                ResultAudioBookFragment.this.viewModel.searchAudioBook(false);
            }
        });
    }

    private void onHideSoftInput() {
        this.binding.resultListCy.setOnTouchListener(new View.OnTouchListener() { // from class: com.wanos.media.ui.search.result.ResultAudioBookFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m546x7c1c325c(view, motionEvent);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onHideSoftInput$2$com-wanos-media-ui-search-result-ResultAudioBookFragment, reason: not valid java name */
    /* synthetic */ boolean m546x7c1c325c(View view, MotionEvent motionEvent) {
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

    private void refreshData(List<AudioBookAlbumInfoBean> list) {
        if (list != null) {
            this.audioBookAdapter.setData(list);
            this.audioBookAdapter.notifyDataSetChanged();
        }
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
        this.audioBookAdapter.setCallBackState(AudioBookGlobalParams.getPlayingStatus(), playingItemBean != null ? playingItemBean.getRadioId() : -1L);
    }
}
