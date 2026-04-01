package com.wanos.media.juyihall.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.bean.BannerBean;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.ui.advertise.WanosJsBridge;
import com.wanos.media.ui.advertise.WebViewActivity;
import com.wanos.media.ui.widget.banner.adapter.BannerAdapter;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class RecBannerViewAdapter extends BannerAdapter<BannerBean, BannerViewHolder> {
    private Context mContext;

    public RecBannerViewAdapter(Context context, List<BannerBean> list) {
        super(list);
        this.mContext = context;
    }

    @Override // com.wanos.media.ui.widget.banner.holder.IViewHolder
    public BannerViewHolder onCreateHolder(ViewGroup viewGroup, int i) {
        ImageView imageView = new ImageView(viewGroup.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override // com.wanos.media.ui.widget.banner.holder.IViewHolder
    public void onBindView(BannerViewHolder bannerViewHolder, final BannerBean bannerBean, final int i, int i2) {
        if (bannerBean != null) {
            GlideUtil.loadScaleWebP(bannerBean.getCoverImg(), bannerViewHolder.imageView);
        }
        bannerViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.juyihall.adapter.RecBannerViewAdapter.1
            private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.wanos.media.juyihall.adapter.RecBannerViewAdapter.1.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    Bundle data = message.getData();
                    String string = data.getString(WanosJsBridge.METHOD_KEY_TOKEN);
                    UserInfo userInfo = (UserInfo) new Gson().fromJson(data.getString("userInfo"), UserInfo.class);
                    UserInfoUtil.saveUserInfo(userInfo);
                    EventBus.getDefault().post(new UserInfoChangeEvent(userInfo));
                    UserInfoUtil.getUserInfo().setToken(string);
                }
            };

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    if (TextUtils.isEmpty(bannerBean.getPath())) {
                        return;
                    }
                    Uri uri = Uri.parse(bannerBean.getPath() + "&isAppIn=true");
                    intent.putExtra(WebViewActivity.EXTRA_TOKEN, UserInfoUtil.getToken());
                    Messenger messenger = new Messenger(this.mHandler);
                    intent.setData(uri);
                    intent.putExtra("main_messenger", messenger);
                    RecBannerViewAdapter.this.mContext.startActivity(intent);
                    Log.i("zt", "用户点击banner的次数(按照位置记)");
                    MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_BANNER_PLACE, (i + 1) + "", "", "", "", 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(ImageView imageView) {
            super(imageView);
            this.imageView = imageView;
        }
    }
}
