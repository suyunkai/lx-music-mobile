package com.wanos.media.ui.music.adapter;

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
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.bean.BannerBean;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.R;
import com.wanos.media.ui.advertise.WanosJsBridge;
import com.wanos.media.ui.advertise.WebViewActivity;
import com.wanos.media.ui.widget.banner.adapter.BannerAdapter;
import com.wanos.media.util.RequestParameterUtils;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class MusicBannerAdapter extends BannerAdapter<BannerBean, BannerViewHolder> {
    private final Context mContext;

    public MusicBannerAdapter(Context context, List<BannerBean> datas) {
        super(datas);
        this.mContext = context;
    }

    @Override // com.wanos.media.ui.widget.banner.holder.IViewHolder
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override // com.wanos.media.ui.widget.banner.holder.IViewHolder
    public void onBindView(BannerViewHolder holder, final BannerBean bannerBean, final int position, int size) {
        Glide.with(holder.itemView.getContext()).load(bannerBean != null ? bannerBean.getCoverImg() : "").into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.music.adapter.MusicBannerAdapter.1
            private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.wanos.media.ui.music.adapter.MusicBannerAdapter.1.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Bundle data = msg.getData();
                    String string = data.getString(WanosJsBridge.METHOD_KEY_TOKEN);
                    UserInfo userInfo = (UserInfo) new Gson().fromJson(data.getString("userInfo"), UserInfo.class);
                    UserInfoUtil.saveUserInfo(userInfo);
                    EventBus.getDefault().post(new UserInfoChangeEvent(userInfo));
                    UserInfoUtil.getUserInfo().setToken(string);
                }
            };

            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    BannerBean bannerBean2 = bannerBean;
                    if (bannerBean2 == null || TextUtils.isEmpty(bannerBean2.getPath())) {
                        ToastUtil.showMsg(MusicBannerAdapter.this.mContext.getString(R.string.no_content_yet));
                    } else {
                        Uri uri = Uri.parse(bannerBean.getPath() + "&isAppIn=true");
                        intent.putExtra(WebViewActivity.EXTRA_TOKEN, UserInfoUtil.getToken());
                        intent.putExtra(WebViewActivity.EXTRA_DEVICE, RequestParameterUtils.deviceIdValue);
                        intent.setData(uri);
                        intent.putExtra("main_messenger", new Messenger(this.mHandler));
                        MusicBannerAdapter.this.mContext.startActivity(intent);
                        LogUtils.e(bannerBean.getPath());
                        if (bannerBean.getPath().contains("audiobook")) {
                            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_AUDIO_BOOK_LEFT_BANNER, (position + 1) + "", "", "", "", 0);
                        } else {
                            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_MUSIC_LEFT_BANNER, (position + 1) + "", "", "", "", 0);
                        }
                    }
                } catch (Exception e) {
                    Log.e("TAG", "onClick: " + e.getMessage());
                }
            }
        });
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
