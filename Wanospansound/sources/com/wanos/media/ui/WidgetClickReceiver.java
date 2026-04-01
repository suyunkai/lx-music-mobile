package com.wanos.media.ui;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.MainApplication;
import com.wanos.media.base.CarConstants;
import com.wanos.media.ui.advertise.WanosJsBridge;
import com.wanos.media.ui.music.activity.MusicListActivity;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetClickReceiver extends BroadcastReceiver {
    private static final String TAG = "wanos:[WidgetClickReceiver]";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        List<ActivityManager.AppTask> appTasks;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(WanosJsBridge.H5_KEY_ACTIVITY);
        if (activityManager != null && (appTasks = activityManager.getAppTasks()) != null && !appTasks.isEmpty()) {
            appTasks.get(0).moveToFront();
            Log.i(TAG, "onReceive: move to front");
        }
        if (CarConstants.buildIndex == 1 || CarConstants.buildIndex == 2) {
            Intent intent2 = new Intent(context, (Class<?>) MusicListActivity.class);
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(Uri.parse(AppConstants.MUSIC_GROUP_DETAIL_URI));
            intent2.putExtra("groupId", -8L);
            intent2.putExtra(MusicListActivity.FROM_WIDGET, true);
            if (MainApplication.topActivity != null) {
                Log.i(TAG, "topActivity != null" + intent2);
                MainApplication.topActivity.startActivity(intent2);
            } else {
                intent2.setFlags(268435456);
                Log.i(TAG, "topActivity == null" + intent2);
                context.startActivity(intent2);
            }
        } else {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            launchIntentForPackage.setFlags(268435456);
            Log.i(TAG, "非245/371车型卡片跳转" + launchIntentForPackage.toString());
            context.startActivity(launchIntentForPackage);
        }
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_WIDGET, "" + UserInfoUtil.getUserInfo().getUserId(), "", "", "", 0);
    }
}
