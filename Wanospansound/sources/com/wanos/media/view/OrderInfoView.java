package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.OrderInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface OrderInfoView extends IBaseView {
    void updateOrderData(boolean isRefresh, List<OrderInfoBean> list);
}
