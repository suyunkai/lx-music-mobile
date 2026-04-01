package com.wanos.media;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ecarx.eas.sdk.mediacenter.SemanticsLevelOneType;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.MediaMusicListBean;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.utils.ImageUtils;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.base.CarConstants;
import java.util.Collections;

/* JADX INFO: loaded from: classes3.dex */
public class WanosAppWidget extends AppWidgetProvider {
    public static final String TAG = "wanos:[WanosAppWidget]";
    public ImageView picView;
    RemoteViews views;

    protected RemoteViews updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        PendingIntent broadcast;
        Log.d(TAG, "updateAppWidget: " + appWidgetId);
        if (CarConstants.showWidgetSize[CarConstants.buildIndex]) {
            this.views = new RemoteViews(context.getPackageName(), R.layout.wanos_app_widget_371);
        } else if (CarConstants.buildIndex == 1) {
            this.views = new RemoteViews(context.getPackageName(), R.layout.wanos_app_widget_245);
        } else {
            this.views = new RemoteViews(context.getPackageName(), R.layout.wanos_app_widget);
        }
        Intent intent = new Intent("com.wanos.media.action.WIDGET_CLICK");
        intent.setPackage("com.wanos.media");
        intent.setClassName("com.wanos.media", "com.wanos.media.ui.WidgetClickReceiver");
        if (Build.VERSION.SDK_INT >= 31) {
            broadcast = PendingIntent.getBroadcast(context, 0, intent, SemanticsLevelOneType.NEWS);
        } else {
            broadcast = PendingIntent.getBroadcast(context, 0, intent, 67108864);
        }
        this.views.setOnClickPendingIntent(R.id.appwidget_container, broadcast);
        appWidgetManager.updateAppWidget(appWidgetId, this.views);
        return this.views;
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG, "onUpdate: appWidgetIds " + Collections.singletonList(appWidgetIds));
        if (appWidgetIds == null || appWidgetIds.length <= 0) {
            return;
        }
        for (int i : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, i);
        }
        if (CarConstants.buildIndex == 1 || CarConstants.buildIndex == 2) {
            requestLimitedFreeMusicList(context, appWidgetManager, appWidgetIds[0]);
        }
    }

    public void requestLimitedFreeMusicList(final Context context, final AppWidgetManager appWidgetManager, final int appWidgetId) {
        WanOSRetrofitUtil.getLimitedFreeMusicList(1, 1, false, new ResponseCallBack<GetMusicListResponse>(null) { // from class: com.wanos.media.WanosAppWidget.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicListResponse response) {
                MediaMusicListBean mediaMusicListBean = response.data;
                if (mediaMusicListBean == null || mediaMusicListBean.getMusicList() == null || mediaMusicListBean.getMusicList().isEmpty()) {
                    return;
                }
                try {
                    Glide.with(context).asBitmap().load(Uri.parse(ImageUtils.getUrlPath(mediaMusicListBean.getMusicList().get(0).getAvatar(), 100, 100))).into(new CustomTarget<Bitmap>() { // from class: com.wanos.media.WanosAppWidget.1.1
                        @Override // com.bumptech.glide.request.target.Target
                        public void onLoadCleared(Drawable drawable) {
                        }

                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object bitmap, Transition transition) {
                            onResourceReady((Bitmap) bitmap, (Transition<? super Bitmap>) transition);
                        }

                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                            try {
                                WanosAppWidget.this.views.setImageViewBitmap(R.id.appwidget_icon, bitmap);
                                appWidgetManager.updateAppWidget(appWidgetId, WanosAppWidget.this.views);
                            } catch (Exception e) {
                                Log.e(WanosAppWidget.TAG, e.getMessage());
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.e(WanosAppWidget.TAG, e.getMessage());
                }
            }
        });
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(TAG, "onDeleted: " + getClass().getSimpleName());
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(TAG, "onEnabled: " + getClass().getSimpleName());
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(TAG, "onDisabled: " + getClass().getSimpleName());
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG, "onReceive: " + intent.getAction());
        LogUtils.e(intent.getStringExtra(StatisticUtil.USER_CLICK_WIDGET));
        if (TextUtils.isEmpty(intent.getStringExtra(StatisticUtil.USER_CLICK_WIDGET))) {
            return;
        }
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_WIDGET, "" + UserInfoUtil.getUserInfo().getUserId(), "", "", "", 0);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Log.d(TAG, "onRestored: oldWidgetIds = " + Collections.singletonList(oldWidgetIds) + ", newWidgetIds = " + Collections.singletonList(oldWidgetIds));
    }
}
