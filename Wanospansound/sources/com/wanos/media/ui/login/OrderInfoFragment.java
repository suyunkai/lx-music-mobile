package com.wanos.media.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.WanosCommunication.bean.OrderInfoBean;
import com.wanos.commonlibrary.fragment.LoadingController;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentOrderInfoBinding;
import com.wanos.media.presenter.OrderInfoPresenter;
import com.wanos.media.ui.login.adapter.OrderInfoListAdapter;
import com.wanos.media.util.LoadingUtil;
import com.wanos.media.view.OrderInfoView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class OrderInfoFragment extends WanosBaseFragment<OrderInfoPresenter> implements OrderInfoView {
    private FragmentOrderInfoBinding binding;
    private LoadingController controller;
    private OrderInfoListAdapter listAdapter;
    private LinearLayoutManager manager;
    private OrderInfoPresenter presenter;
    private final List<OrderInfoBean> orderList = new ArrayList();
    private boolean isFirst = true;

    @Override // com.wanos.media.base.WanosBaseFragment
    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return true;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OrderInfoPresenter orderInfoPresenter = new OrderInfoPresenter(getContext());
        this.presenter = orderInfoPresenter;
        orderInfoPresenter.attachView((OrderInfoView) this);
        this.binding = FragmentOrderInfoBinding.inflate(getLayoutInflater());
        initView();
        return this.binding.getRoot();
    }

    private void initView() {
        initLoadingController();
        initRecyclerView();
        this.presenter.getOrderListData(true);
        initRefreshLayout();
    }

    private void initLoadingController() {
        this.controller = LoadingUtil.initCommonLoadingView(getActivity().getApplicationContext(), this.binding.orderRefreshLayout, 0, R.drawable.ic_order_empty, R.string.order_empty_message);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.manager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.binding.orderListCy.setLayoutManager(this.manager);
        this.listAdapter = new OrderInfoListAdapter(getActivity(), new ArrayList());
        this.binding.orderListCy.setAdapter(this.listAdapter);
    }

    private void initRefreshLayout() {
        this.binding.orderRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.ui.login.OrderInfoFragment.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                OrderInfoFragment.this.isFirst = true;
                OrderInfoFragment.this.presenter.getOrderListData(true);
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                OrderInfoFragment.this.isFirst = false;
                OrderInfoFragment.this.presenter.loadMore(false);
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
            showLoadErrorView(new View.OnClickListener() { // from class: com.wanos.media.ui.login.OrderInfoFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m504x4435ddf4(view);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$showFailOrError$0$com-wanos-media-ui-login-OrderInfoFragment, reason: not valid java name */
    /* synthetic */ void m504x4435ddf4(View view) {
        OrderInfoPresenter orderInfoPresenter = this.presenter;
        if (orderInfoPresenter != null) {
            this.isFirst = true;
            orderInfoPresenter.getOrderListData(true);
        }
    }

    private void refreshData() {
        if (this.orderList.size() != 0) {
            this.listAdapter.setData(this.orderList);
            this.listAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.wanos.media.view.OrderInfoView
    public void updateOrderData(boolean isRefresh, List<OrderInfoBean> list) {
        this.binding.orderRefreshLayout.finishRefresh();
        this.binding.orderRefreshLayout.finishRefreshLoadMore();
        if (isRefresh) {
            if (list.size() == 0) {
                this.controller.showEmpty(true);
            } else {
                this.orderList.clear();
                this.orderList.addAll(list);
                this.controller.dismissLoading();
            }
        } else {
            this.orderList.addAll(list);
        }
        this.binding.orderRefreshLayout.setLoadMore(this.orderList.size() >= 10);
        refreshData();
    }

    private static class MyCallback extends DiffUtil.Callback {
        private final List<String> mNewList;
        private final List<String> mOldList;

        public MyCallback(List<String> oldList, List<String> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.mOldList.size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return this.mNewList.size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return this.mOldList.get(oldItemPosition).equals(this.mNewList.get(newItemPosition));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return this.mOldList.get(oldItemPosition).equals(this.mNewList.get(newItemPosition));
        }
    }
}
