package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.AdDialogInfoBean;
import com.wanos.WanosCommunication.bean.GoodsInfo;
import com.wanos.WanosCommunication.bean.OrderCreatePayBean;
import com.wanos.WanosCommunication.bean.OrderInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MemberPayView extends IBaseView {
    void createVipMemberSuccess(String orderNo);

    void getAdDialogSuccess(AdDialogInfoBean data);

    void getOrderDetail(OrderInfoBean data);

    void getPayVipMember(OrderCreatePayBean data);

    void getVipMemberGoodsData(List<GoodsInfo> list);
}
