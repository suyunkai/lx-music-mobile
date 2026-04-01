package com.wanos.media.NetWork;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.URLConstan;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.commonlibrary.R;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.BellsEntity;
import com.wanos.media.entity.CollectCoveEntity;
import com.wanos.media.entity.CreateOrCollectUserThemeReply;
import com.wanos.media.entity.EmptyEntity;
import com.wanos.media.entity.GetAlarmAudioInfo;
import com.wanos.media.entity.GetAudioInfoResp;
import com.wanos.media.entity.GetMaterialAudioInfo;
import com.wanos.media.entity.GetShareCodeEntity;
import com.wanos.media.entity.ThemeInfoEntity;
import com.wanos.media.entity.ZeroThemeListEntity;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.viewmodel.SendShareCodeViewModel;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class HttpTools {
    private static final String TAG = "HttpTools";
    private static volatile HttpTools instance;
    private static final HashMap<String, Object> mParams = new HashMap<>();
    private final ApiService mService;

    public HttpTools() {
        ZeroLogcatTools.d(TAG, "初始化零压空间网络模块");
        this.mService = (ApiService) WanOSRetrofitUtil.getRetrofit(URLConstan.BASE_ZERO).create(ApiService.class);
    }

    public static HttpTools getInstance() {
        if (instance == null) {
            synchronized (HttpTools.class) {
                if (instance == null) {
                    instance = new HttpTools();
                }
            }
        }
        return instance;
    }

    public static <T> String formatErrorResult(BaseEntity<T> baseEntity) {
        if (baseEntity == null) {
            return StringUtils.getString(R.string.wanos_error_message);
        }
        return ResponseCallBack.formatErrorResult(ActivityUtils.getTopActivity(), baseEntity.getCode(), baseEntity.getMsg());
    }

    public void getTemplateThemeInfo(long j, HttpCallback<ThemeInfoEntity> httpCallback) {
        this.mService.getThemeInfo(j).enqueue(httpCallback);
    }

    public BaseEntity<ThemeInfoEntity> getTemplateThemeInfoSync(long j) throws IOException {
        return this.mService.getThemeInfo(j).execute().body();
    }

    public void getUserThemeInfo(long j, HttpCallback<ThemeInfoEntity> httpCallback) {
        this.mService.getUserThemeInfo(j).enqueue(httpCallback);
    }

    public BaseEntity<ThemeInfoEntity> getUserThemeInfoSync(long j) throws IOException {
        return this.mService.getUserThemeInfo(j).execute().body();
    }

    public void getAudioLibrary(HttpCallback<GetAudioInfoResp> httpCallback) {
        this.mService.getAudioLibrary().enqueue(httpCallback);
    }

    public BaseEntity<GetAudioInfoResp> getAudioLibrarySync() {
        try {
            return this.mService.getAudioLibrary().execute().body();
        } catch (IOException unused) {
            return null;
        }
    }

    public void getThemeList(int i, int i2, int i3, HttpCallback<ZeroThemeListEntity> httpCallback) {
        this.mService.getZeroThemeList(i, i2, i3).enqueue(httpCallback);
    }

    public void getUserThemeList(int i, int i2, int i3, HttpCallback<ZeroThemeListEntity> httpCallback) {
        this.mService.getZeroUserThemeList(i2, i3, i).enqueue(httpCallback);
    }

    public BaseEntity<ZeroThemeListEntity> getUserThemeListSync(int i, int i2, int i3) {
        try {
            return this.mService.getZeroUserThemeList(i2, i3, i).execute().body();
        } catch (IOException unused) {
            return null;
        }
    }

    public BaseEntity<ZeroThemeListEntity> getThemeListSync(int i, int i2, int i3) {
        try {
            return this.mService.getZeroThemeList(i, i2, i3).execute().body();
        } catch (Exception unused) {
            return null;
        }
    }

    public void getThemeShareCode(String str, HttpCallback<GetShareCodeEntity> httpCallback) {
        this.mService.getThemeShareCode(str).enqueue(httpCallback);
    }

    public void addThemeFromShareCode(String str, HttpCallback<EmptyEntity> httpCallback) {
        HashMap<String, Object> map = mParams;
        map.clear();
        map.put("shareCode", str);
        this.mService.addThemeFromShareCode(WanOSRetrofitUtil.mapToJsonRequestBody(map)).enqueue(httpCallback);
    }

    public void deleteTheme(long j, HttpCallback<EmptyEntity> httpCallback) {
        HashMap<String, Object> map = mParams;
        map.clear();
        map.put(SendShareCodeViewModel.KEY_THEME_ID, Long.valueOf(j));
        this.mService.deleteTheme(WanOSRetrofitUtil.mapToJsonRequestBody(map)).enqueue(httpCallback);
    }

    public void setTopTheme(long j, HttpCallback<EmptyEntity> httpCallback) {
        HashMap<String, Object> map = mParams;
        map.clear();
        map.put(SendShareCodeViewModel.KEY_THEME_ID, Long.valueOf(j));
        map.put("power", 1);
        this.mService.setTopTheme(WanOSRetrofitUtil.mapToJsonRequestBody(map)).enqueue(httpCallback);
    }

    public BaseEntity<BellsEntity> getBellsListSync() {
        try {
            return this.mService.getBellList().execute().body();
        } catch (IOException unused) {
            return null;
        }
    }

    public void getCollectCoveImageList(HttpCallback<CollectCoveEntity> httpCallback) {
        this.mService.getCollectCoveLis().enqueue(httpCallback);
    }

    public void saveAsUserTheme(long j, String str, String str2, String str3, HttpCallback<CreateOrCollectUserThemeReply> httpCallback) {
        HashMap<String, Object> map = mParams;
        map.clear();
        map.put("bindId", Long.valueOf(j));
        map.put("themeName", str);
        map.put("coverImg", str2);
        map.put("audioConfig", str3);
        this.mService.saveAsTheme(WanOSRetrofitUtil.mapToJsonRequestBody(map)).enqueue(httpCallback);
    }

    public void cancelCollectTheme(long j, HttpCallback<EmptyEntity> httpCallback) {
        HashMap<String, Object> map = mParams;
        map.clear();
        map.put("bindId", Long.valueOf(j));
        this.mService.cancelCollectAudio(WanOSRetrofitUtil.mapToJsonRequestBody(map)).enqueue(httpCallback);
    }

    public void getAlarAudioInfo(int i, HttpCallback<GetAlarmAudioInfo> httpCallback) {
        this.mService.getAlarmAudioInfo(i).enqueue(httpCallback);
    }

    public void getMaterialAudioInfo(int i, HttpCallback<GetMaterialAudioInfo> httpCallback) {
        this.mService.getMaterialAudioInfo(i).enqueue(httpCallback);
    }
}
