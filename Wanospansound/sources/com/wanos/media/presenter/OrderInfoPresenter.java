package com.wanos.media.presenter;

import android.content.Context;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.OrderInfoBean;
import com.wanos.WanosCommunication.response.GetOrderInfoListResponse;
import com.wanos.media.view.OrderInfoView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class OrderInfoPresenter extends BasePresenter<OrderInfoView> {
    private final Context context;
    private List<OrderInfoBean> orderList = new ArrayList();

    public OrderInfoPresenter(Context context) {
        this.context = context;
    }

    @Override // com.wanos.media.presenter.BasePresenter
    public void attachView(OrderInfoView baseView) {
        super.attachView(baseView);
    }

    public void loadMore(boolean isRefresh) {
        this.mPageIndex++;
        getOrderListData(isRefresh);
    }

    public void getOrderListData(final boolean isRefresh) {
        if (isViewAttached()) {
            if (isRefresh) {
                ((OrderInfoView) this.mView).showLoading();
            }
            WanOSRetrofitUtil.GetOrderInfoList(this.mPageIndex + "", this.orderPageSize + "", new ResponseCallBack<GetOrderInfoListResponse>(this.context) { // from class: com.wanos.media.presenter.OrderInfoPresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetOrderInfoListResponse response) {
                    if (response.data != null) {
                        OrderInfoPresenter.this.orderList = response.data.getOrderList();
                        OrderInfoPresenter.this.getView().updateOrderData(isRefresh, OrderInfoPresenter.this.orderList);
                    }
                    if (isRefresh) {
                        ((OrderInfoView) OrderInfoPresenter.this.mView).hideLoading();
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    if (isRefresh) {
                        ((OrderInfoView) OrderInfoPresenter.this.mView).hideLoading();
                    }
                    ((OrderInfoView) OrderInfoPresenter.this.mView).showFailOrError(msg);
                }
            });
        }
    }
}
