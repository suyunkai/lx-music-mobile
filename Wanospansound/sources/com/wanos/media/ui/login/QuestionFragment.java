package com.wanos.media.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.QAListItem;
import com.wanos.commonlibrary.fragment.LoadingController;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentQaListBinding;
import com.wanos.media.presenter.QuestionPresenter;
import com.wanos.media.ui.login.adapter.QAListAdapter;
import com.wanos.media.ui.login.dialog.FeedbackDialog;
import com.wanos.media.util.LoadingUtil;
import com.wanos.media.view.QuestionView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class QuestionFragment extends WanosBaseFragment<QuestionPresenter> implements QuestionView {
    private FragmentQaListBinding binding;
    private LoadingController controller;
    private QAListAdapter listAdapter;
    private final List<QAListItem> qaList = new ArrayList();
    private boolean isFirst = true;

    @Override // com.wanos.media.base.WanosBaseFragment
    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return true;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mPresenter = new QuestionPresenter();
        ((QuestionPresenter) this.mPresenter).attachView(this);
        this.binding = FragmentQaListBinding.inflate(getLayoutInflater());
        initView();
        return this.binding.getRoot();
    }

    private void initView() {
        initLoadingController();
        initRecyclerView();
        ((QuestionPresenter) this.mPresenter).requestData();
        initRefreshLayout();
    }

    private void initLoadingController() {
        this.controller = LoadingUtil.initCommonLoadingView(getActivity().getApplicationContext(), this.binding.refreshLayout, 0, R.drawable.ic_order_empty, R.string.order_empty_message);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        this.binding.recyclerView.setLayoutManager(linearLayoutManager);
        this.listAdapter = new QAListAdapter(new ArrayList());
        this.binding.recyclerView.setAdapter(this.listAdapter);
        this.binding.btnFeedback.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.login.QuestionFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FeedbackDialog feedbackDialog = new FeedbackDialog();
                feedbackDialog.setQRUrl(((QuestionPresenter) QuestionFragment.this.mPresenter).getFormatedFeedBackUrl());
                feedbackDialog.show(QuestionFragment.this.getChildFragmentManager(), "feedback");
            }
        });
    }

    private void initRefreshLayout() {
        this.binding.refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.login.QuestionFragment.2
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                QuestionFragment.this.isFirst = true;
                ((QuestionPresenter) QuestionFragment.this.mPresenter).requestData();
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                QuestionFragment.this.isFirst = false;
                ((QuestionPresenter) QuestionFragment.this.mPresenter).loadMore(false);
            }
        });
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
        if (this.isFirst) {
            showLoadingView();
        }
    }

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
        if (this.isFirst) {
            closeLoadingView();
        }
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
        if (this.isFirst) {
            showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.login.QuestionFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m505xeed3e4ee(view);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$showFailOrError$0$com-wanos-media-ui-login-QuestionFragment, reason: not valid java name */
    /* synthetic */ void m505xeed3e4ee(View view) {
        if (this.mPresenter != 0) {
            this.isFirst = true;
            ((QuestionPresenter) this.mPresenter).requestData();
        }
    }

    private void refreshData() {
        if (this.qaList.size() != 0) {
            this.listAdapter.setData(this.qaList);
            this.listAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.wanos.media.view.QuestionView
    public void updateQAList(boolean isRefresh, List<QAListItem> list) {
        this.binding.refreshLayout.finishRefresh();
        this.binding.refreshLayout.finishRefreshLoadMore();
        if (isRefresh) {
            if (list.size() != 0) {
                this.qaList.clear();
                this.qaList.addAll(list);
                this.controller.dismissLoading();
            }
        } else {
            this.qaList.addAll(list);
        }
        this.binding.refreshLayout.setLoadMore(this.qaList.size() >= 10);
        refreshData();
    }
}
