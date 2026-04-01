package com.wanos.media.ui.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.WanosCommunication.bean.OrderInfoBean;
import com.wanos.commonlibrary.base.BaseRecycleAdapter;
import com.wanos.media.R;
import java.text.SimpleDateFormat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class OrderInfoListAdapter extends BaseRecycleAdapter<OrderInfoBean, OrderViewHolder> {
    private final Context context;

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return position;
    }

    public OrderInfoListAdapter(Context context, List<OrderInfoBean> dataList) {
        super(dataList);
        this.context = context;
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order_info_item, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(OrderViewHolder holder, int position) {
        OrderInfoBean orderInfoBean = (OrderInfoBean) this.datas.get(position);
        StringBuilder sb = new StringBuilder();
        if (orderInfoBean != null) {
            String goodsName = orderInfoBean.getGoodsName();
            String orderNo = orderInfoBean.getOrderNo();
            String orderAmount = orderInfoBean.getOrderAmount();
            long createTime = orderInfoBean.getCreateTime();
            int orderStatus = orderInfoBean.getOrderStatus();
            int payStatus = orderInfoBean.getPayStatus();
            holder.title.setText(goodsName);
            holder.imVip.setImageResource(R.drawable.p_vip_info_pic);
            holder.cover.setImageResource(R.drawable.ic_order_cover);
            sb.append(this.context.getString(R.string.order_time, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(createTime * 1000))));
            if (OrderInfoBean.OrderStatus.STATUS_CREATE == orderStatus && OrderInfoBean.OrderPayStatus.STATUS_SUCCESS == payStatus) {
                StringBuilder sb2 = new StringBuilder(" 丨 ");
                Context context = this.context;
                sb.append(sb2.append(context.getString(R.string.order_status, context.getString(R.string.order_complete))).toString());
            }
            holder.desc.setText(this.context.getString(R.string.order_price, orderAmount) + " 丨 ");
            holder.number.setText(this.context.getString(R.string.order_no, orderNo) + " 丨 ");
            holder.behind.setText(sb.toString());
            holder.itemView.setSoundEffectsEnabled(false);
        }
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        private final TextView behind;
        private final ImageView cover;
        private final TextView desc;
        private final ImageView imVip;
        private final TextView number;
        private final TextView title;

        OrderViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.order_tv_title);
            this.cover = (ImageView) itemView.findViewById(R.id.order_img);
            this.imVip = (ImageView) itemView.findViewById(R.id.order_img_vip);
            this.desc = (TextView) itemView.findViewById(R.id.order_tv_price);
            this.number = (TextView) itemView.findViewById(R.id.order_tv_number);
            this.behind = (TextView) itemView.findViewById(R.id.order_tv_behind);
        }
    }
}
