package com.wanos.media.juyihall.viewholder;

import android.util.Log;
import android.view.View;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.router.PageRouter;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.view.DailyMusicView;
import com.wanos.media.ui.widget.banner.Banner;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2HeaderVH extends Rec2BaseVH implements View.OnClickListener {
    public Banner banner;
    public DailyMusicView dailyMusicView;
    public View viewCollect;
    public View viewRecent;

    public Rec2HeaderVH(View view) {
        super(view);
        this.viewRecent = view.findViewById(R.id.tv_recent);
        this.viewCollect = view.findViewById(R.id.tv_collect);
        this.dailyMusicView = (DailyMusicView) view.findViewById(R.id.daily_view);
        this.banner = (Banner) view.findViewById(R.id.banner);
        this.viewRecent.setOnClickListener(this);
        this.viewCollect.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.tv_recent) {
            PageRouter.toRecentPlayedPage();
            Log.i("zt", "用户点击最近播放按钮");
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_RECENTLY_PLAYED_BUTTON, "", "", "", "", 0);
        } else if (view.getId() == R.id.tv_collect) {
            if (!UserInfoUtil.isLogin()) {
                ((WanosBaseActivity) this.itemView.getContext()).showLoginDialog();
                return;
            }
            PageRouter.toCollectionPage();
            Log.i("zt", "用户点击收藏订阅按钮");
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_COLLECT_SUBSCRIBE_PLAYED_BUTTON, "", "", "", "", 0);
        }
    }
}
