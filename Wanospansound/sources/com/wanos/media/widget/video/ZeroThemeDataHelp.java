package com.wanos.media.widget.video;

import android.graphics.Bitmap;
import androidx.media3.common.PlaybackException;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.ThemeAudioInfoEntity;
import com.wanos.media.entity.ThemeInfoEntity;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.util.ImageLoadTools;
import com.wanos.media.util.MusicCacheUtils;
import com.wanos.media.util.PictureCacheUtils;
import com.wanos.media.util.VideoCacheUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.CustomBlurTransformation;
import com.wanos.media.zero_p.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroThemeDataHelp {
    private static ZeroThemeDataHelp INSTANCE = null;
    private static final String TAG = "ZeroThemeDataHelp";
    private boolean mActivityDestroy = false;
    private int mRequestThemeId = -1;
    private int mRequestScreenId = -1;

    public void onLoadThemeSceneInfoData(List<AudioSceneInfoEntity> list, ISceneLoadedCallback iSceneLoadedCallback) {
    }

    public static ZeroThemeDataHelp getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ZeroThemeDataHelp();
        }
        return INSTANCE;
    }

    public void onActivityDestroy() {
        this.mActivityDestroy = true;
        this.mRequestThemeId = -1;
        this.mRequestScreenId = -1;
        onPauseDownTask();
        ZeroLogcatTools.d(TAG, "onActivityDestroy: mActivityDestroy=" + this.mActivityDestroy);
        INSTANCE = null;
    }

    public void onPauseDownTask() {
        MusicCacheUtils.onPauseTask();
    }

    public void onLoadThemeData(ZeroPageMode zeroPageMode, int i, IThemeLoadedCallback iThemeLoadedCallback) {
        this.mRequestThemeId = i;
        MusicCacheUtils.onPauseTask();
        HttpTools.getInstance().getTemplateThemeInfo(i, new AnonymousClass1(zeroPageMode, iThemeLoadedCallback));
    }

    /* JADX INFO: renamed from: com.wanos.media.widget.video.ZeroThemeDataHelp$1, reason: invalid class name */
    class AnonymousClass1 extends HttpCallback<ThemeInfoEntity> {
        final /* synthetic */ IThemeLoadedCallback val$iThemeLoadedCallback;
        final /* synthetic */ ZeroPageMode val$mode;

        AnonymousClass1(ZeroPageMode zeroPageMode, IThemeLoadedCallback iThemeLoadedCallback) {
            this.val$mode = zeroPageMode;
            this.val$iThemeLoadedCallback = iThemeLoadedCallback;
        }

        @Override // com.wanos.WanosCommunication.ResponseCallBack
        public void onResponseSuccessful(BaseEntity<ThemeInfoEntity> baseEntity) {
            if (ZeroThemeDataHelp.this.mActivityDestroy) {
                ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeData: Activity已销毁，中断数据传输-接口");
                return;
            }
            ThemeInfoEntity data = baseEntity.getData();
            if (data.getThemeId() != ZeroThemeDataHelp.this.mRequestThemeId) {
                ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeData: 主题已发生变化，最新请求主题ID=" + ZeroThemeDataHelp.this.mRequestThemeId + ",当前果ID=" + data.getThemeId());
                return;
            }
            ZeroLogcatTools.d(ZeroThemeDataHelp.TAG, "onLoadThemeData: 通过网络加载主题数据，主题名称=" + data.getThemeName());
            String[] strArrFindMingXiangDuration = ZeroThemeDataHelp.this.findMingXiangDuration(this.val$mode, data);
            if (strArrFindMingXiangDuration != null) {
                this.val$iThemeLoadedCallback.onThemeDurationInfo(strArrFindMingXiangDuration);
            }
            this.val$iThemeLoadedCallback.onThemeInfo(data);
            if (data.getBgType() != 2) {
                return;
            }
            String videoResource = data.getVideoResource();
            final IThemeLoadedCallback iThemeLoadedCallback = this.val$iThemeLoadedCallback;
            VideoCacheUtils.onLaunchTask(videoResource, new VideoCacheUtils.IVideoCacheListener() { // from class: com.wanos.media.widget.video.ZeroThemeDataHelp$1$$ExternalSyntheticLambda0
                @Override // com.wanos.media.util.VideoCacheUtils.IVideoCacheListener
                public final void onTaskSuccess(int i, String str, Object obj) {
                    this.f$0.m664xda089439(iThemeLoadedCallback, i, str, obj);
                }
            }, data);
            final String bgImg = data.getBgImg();
            if (FileUtils.isFileExists(PictureCacheUtils.getImageCachePath(bgImg))) {
                return;
            }
            ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<String>() { // from class: com.wanos.media.widget.video.ZeroThemeDataHelp.1.1
                @Override // com.blankj.utilcode.util.ThreadUtils.Task
                public String doInBackground() throws Throwable {
                    Bitmap bitmap = new ImageLoadTools.Builder().setWidth(1080).setHeight(720).setTransformation(new CustomBlurTransformation(20, 2, Utils.getApp())).setCovertDimens(false).getBitmap(bgImg);
                    if (bitmap == null) {
                        return "";
                    }
                    PictureCacheUtils.toLocalImage(bgImg, bitmap);
                    bitmap.recycle();
                    return bgImg;
                }

                @Override // com.blankj.utilcode.util.ThreadUtils.Task
                public void onSuccess(String str) {
                    ZeroLogcatTools.d(ZeroThemeDataHelp.TAG, "onLoadThemeData: " + str);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onResponseSuccessful$0$com-wanos-media-widget-video-ZeroThemeDataHelp$1, reason: not valid java name */
        /* synthetic */ void m664xda089439(IThemeLoadedCallback iThemeLoadedCallback, int i, String str, Object obj) {
            ThemeInfoEntity themeInfoEntity = (ThemeInfoEntity) obj;
            if (themeInfoEntity == null || themeInfoEntity.getThemeId() != ZeroThemeDataHelp.this.mRequestThemeId) {
                ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeData: 主题已发生变化，最新请求主题视频ID=" + ZeroThemeDataHelp.this.mRequestThemeId + ",当前ID=" + (themeInfoEntity == null ? "NULL" : Long.valueOf(themeInfoEntity.getThemeId())));
            } else if (ZeroThemeDataHelp.this.mActivityDestroy) {
                ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeData: Activity已销毁，中断数据传输-视频");
            } else {
                ZeroLogcatTools.d(ZeroThemeDataHelp.TAG, "onLoadThemeData: 主题视频缓存完成，主题名称=" + themeInfoEntity.getThemeName());
                iThemeLoadedCallback.onVideoInfo(themeInfoEntity);
            }
        }

        @Override // com.wanos.WanosCommunication.ResponseCallBack
        public void onResponseFailure(int i, String str) {
            if (ZeroThemeDataHelp.this.mActivityDestroy) {
                ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeData: Activity已销毁，中断数据传输-接口");
            } else {
                ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "getThemeInfo: 主题获取失败: 错误码=" + i + "| 错误信息=" + str);
                this.val$iThemeLoadedCallback.onThemeErrorInfo(str);
            }
        }
    }

    /* JADX INFO: renamed from: com.wanos.media.widget.video.ZeroThemeDataHelp$2, reason: invalid class name */
    class AnonymousClass2 extends ThreadUtils.SimpleTask<ThemeInfoEntity> {
        final /* synthetic */ IThemeLoadedCallback val$iThemeLoadedCallback;
        final /* synthetic */ ZeroPageMode val$initMode;
        final /* synthetic */ int val$themeId;

        AnonymousClass2(ZeroPageMode zeroPageMode, int i, IThemeLoadedCallback iThemeLoadedCallback) {
            this.val$initMode = zeroPageMode;
            this.val$themeId = i;
            this.val$iThemeLoadedCallback = iThemeLoadedCallback;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public ThemeInfoEntity doInBackground() throws Throwable {
            ThemeInfoEntity data;
            Bitmap bitmap;
            if (this.val$initMode == ZeroPageMode.MING_XIANG_PRO || this.val$initMode == ZeroPageMode.XIAO_QI_PRO) {
                ZeroLogcatTools.i(ZeroThemeDataHelp.TAG, "onLoadThemeInfoData: 获取自定义主题详情");
                data = HttpTools.getInstance().getUserThemeInfoSync(this.val$themeId).getData();
            } else {
                ZeroLogcatTools.i(ZeroThemeDataHelp.TAG, "onLoadThemeInfoData: 获取模板主题详情");
                data = HttpTools.getInstance().getTemplateThemeInfoSync(this.val$themeId).getData();
            }
            String bgImg = data.getBgImg();
            if (!FileUtils.isFileExists(PictureCacheUtils.getImageCachePath(bgImg)) && (bitmap = new ImageLoadTools.Builder().setWidth(1080).setHeight(720).setTransformation(new CustomBlurTransformation(20, 2, Utils.getApp())).setCovertDimens(false).getBitmap(bgImg)) != null) {
                PictureCacheUtils.toLocalImage(bgImg, bitmap);
                bitmap.recycle();
            }
            return data;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public void onSuccess(ThemeInfoEntity themeInfoEntity) {
            if (themeInfoEntity != null) {
                if (ZeroThemeDataHelp.this.mActivityDestroy) {
                    ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeInfoData: Activity已销毁，中断数据传输-接口");
                    return;
                }
                if (themeInfoEntity.getThemeId() != ZeroThemeDataHelp.this.mRequestThemeId) {
                    ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeInfoData: 主题已发生变化，最新请求主题ID=" + ZeroThemeDataHelp.this.mRequestThemeId + ",当前结果ID=" + themeInfoEntity.getThemeId());
                    return;
                }
                this.val$iThemeLoadedCallback.onThemeInfo(themeInfoEntity);
                String[] strArrFindMingXiangDuration = ZeroThemeDataHelp.this.findMingXiangDuration(this.val$initMode, themeInfoEntity);
                if (strArrFindMingXiangDuration != null) {
                    this.val$iThemeLoadedCallback.onThemeDurationInfo(strArrFindMingXiangDuration);
                }
                if (themeInfoEntity.getBgType() == 2) {
                    String videoResource = themeInfoEntity.getVideoResource();
                    final IThemeLoadedCallback iThemeLoadedCallback = this.val$iThemeLoadedCallback;
                    VideoCacheUtils.onLaunchTask(videoResource, new VideoCacheUtils.IVideoCacheListener() { // from class: com.wanos.media.widget.video.ZeroThemeDataHelp$2$$ExternalSyntheticLambda0
                        @Override // com.wanos.media.util.VideoCacheUtils.IVideoCacheListener
                        public final void onTaskSuccess(int i, String str, Object obj) {
                            this.f$0.m665xf96b046a(iThemeLoadedCallback, i, str, obj);
                        }
                    }, themeInfoEntity);
                    return;
                }
                return;
            }
            this.val$iThemeLoadedCallback.onThemeErrorInfo(StringUtils.getString(R.string.error_scene_load));
            ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeInfoData: 数据请求失败");
        }

        /* JADX INFO: renamed from: lambda$onSuccess$0$com-wanos-media-widget-video-ZeroThemeDataHelp$2, reason: not valid java name */
        /* synthetic */ void m665xf96b046a(IThemeLoadedCallback iThemeLoadedCallback, int i, String str, Object obj) {
            ThemeInfoEntity themeInfoEntity = (ThemeInfoEntity) obj;
            if (themeInfoEntity == null || themeInfoEntity.getThemeId() != ZeroThemeDataHelp.this.mRequestThemeId) {
                ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeInfoData: 主题已发生变化，最新请求主题视频ID=" + ZeroThemeDataHelp.this.mRequestThemeId + ",当前ID=" + (themeInfoEntity == null ? "NULL" : Long.valueOf(themeInfoEntity.getThemeId())));
            } else if (ZeroThemeDataHelp.this.mActivityDestroy) {
                ZeroLogcatTools.e(ZeroThemeDataHelp.TAG, "onLoadThemeInfoData: Activity已销毁，中断数据传输-视频");
            } else {
                iThemeLoadedCallback.onVideoInfo(themeInfoEntity);
            }
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
        public void onFail(Throwable th) {
            super.onFail(th);
            this.val$iThemeLoadedCallback.onThemeErrorInfo(th.getMessage() == null ? StringUtils.getString(R.string.error_scene_load) : th.getMessage());
        }
    }

    public void onLoadThemeInfoData(ZeroPageMode zeroPageMode, int i, IThemeLoadedCallback iThemeLoadedCallback) {
        this.mRequestThemeId = i;
        ThreadUtils.executeByIo(new AnonymousClass2(zeroPageMode, i, iThemeLoadedCallback));
    }

    public void onLoadSceneDataForDuration(ZeroPageMode zeroPageMode, ThemeInfoEntity themeInfoEntity, int i, ISceneLoadedCallback iSceneLoadedCallback) {
        int iFindSceneForDuration = findSceneForDuration(themeInfoEntity, i);
        if (iFindSceneForDuration < 0) {
            iSceneLoadedCallback.onSceneErrorInfo("当前主题配置错误，错误码: " + iFindSceneForDuration);
        } else {
            onLoadSceneData(zeroPageMode, themeInfoEntity, iFindSceneForDuration, iSceneLoadedCallback);
        }
    }

    private int findSceneForDuration(ThemeInfoEntity themeInfoEntity, int i) {
        if (themeInfoEntity == null) {
            ZeroLogcatTools.e(TAG, "findSceneForDuration: 通过时长查询场景失败，ThemeInfoEntity=NULL");
            return -101;
        }
        if (i <= 0) {
            ZeroLogcatTools.e(TAG, "findSceneForDuration: 通过时长查询场景失败，主题时长 duration <= 0");
            return PlaybackException.ERROR_CODE_AUTHENTICATION_EXPIRED;
        }
        List<AudioSceneInfoEntity> audioInfo = themeInfoEntity.getAudioInfo();
        if (audioInfo == null || audioInfo.isEmpty()) {
            ZeroLogcatTools.e(TAG, "findSceneForDuration: 通过时长查询场景失败，该主题下缺少场景数据");
            return PlaybackException.ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED;
        }
        ZeroLogcatTools.d(TAG, "findSceneForDuration: 主题名称 = " + themeInfoEntity.getThemeName() + ", 场景数量 = " + audioInfo.size() + ", 查询时长 = " + i);
        for (int i2 = 0; i2 < audioInfo.size(); i2++) {
            if (audioInfo.get(i2).getDurtion() == i) {
                return i2;
            }
        }
        ZeroLogcatTools.e(TAG, "findSceneForDuration: 通过时长查询场景失败，未找到对应场景");
        return -100;
    }

    public void onLoadSceneData(ZeroPageMode zeroPageMode, final ThemeInfoEntity themeInfoEntity, int i, final ISceneLoadedCallback iSceneLoadedCallback) {
        if (themeInfoEntity == null) {
            ZeroLogcatTools.e(TAG, "onLoadSceneData: 注意信息错误，ThemeInfoEntity = NULL");
            iSceneLoadedCallback.onSceneErrorInfo("主题信息错误");
            return;
        }
        List<AudioSceneInfoEntity> audioInfo = themeInfoEntity.getAudioInfo();
        if (audioInfo == null || audioInfo.isEmpty()) {
            ZeroLogcatTools.e(TAG, "onLoadSceneData: 当前主题缺少场景信息");
            iSceneLoadedCallback.onSceneErrorInfo("当前主题缺少场景信息");
            return;
        }
        ZeroLogcatTools.d(TAG, "onLoadSceneData: 当前选择场景Index=" + i + "，当前场景数量=" + audioInfo.size());
        if (i < 0 || i >= audioInfo.size()) {
            ZeroLogcatTools.e(TAG, "onLoadSceneData: 当前主题不包含当前选择场景，默认选择0");
            i = 0;
        }
        final AudioSceneInfoEntity audioSceneInfoEntity = audioInfo.get(i);
        this.mRequestScreenId = (int) audioSceneInfoEntity.getAudioId();
        ZeroLogcatTools.d(TAG, "onLoadSceneData: 模式 == " + zeroPageMode);
        MusicCacheUtils.onPauseMusicTask();
        List<ThemeAudioInfoEntity> detailInfo = audioSceneInfoEntity.getDetailInfo();
        if (detailInfo == null || detailInfo.isEmpty()) {
            ZeroLogcatTools.e(TAG, "onLoadSceneData: 场景音源信息错误，场景ID=" + audioSceneInfoEntity.getAudioId());
            iSceneLoadedCallback.onSceneErrorInfo("当前场景下暂无音源");
        } else {
            ZeroLogcatTools.d(TAG, "onLoadSceneData: 开始加载音源信息，待加载数量=" + detailInfo.size());
            MusicCacheUtils.onLaunchSceneTask(detailInfo, (int) audioSceneInfoEntity.getAudioId(), new MusicCacheUtils.IMultiAudioCacheCallback() { // from class: com.wanos.media.widget.video.ZeroThemeDataHelp$$ExternalSyntheticLambda0
                @Override // com.wanos.media.util.MusicCacheUtils.IMultiAudioCacheCallback
                public final void onTaskSuccess(ArrayList arrayList, int i2) {
                    this.f$0.m663xbd37b56f(audioSceneInfoEntity, iSceneLoadedCallback, themeInfoEntity, arrayList, i2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onLoadSceneData$0$com-wanos-media-widget-video-ZeroThemeDataHelp, reason: not valid java name */
    /* synthetic */ void m663xbd37b56f(AudioSceneInfoEntity audioSceneInfoEntity, ISceneLoadedCallback iSceneLoadedCallback, ThemeInfoEntity themeInfoEntity, ArrayList arrayList, int i) {
        if (this.mActivityDestroy) {
            ZeroLogcatTools.e(TAG, "onLoadSceneData: Activity已销毁，中断数据传输-音源");
        } else {
            if (i != this.mRequestScreenId) {
                ZeroLogcatTools.e(TAG, "onLoadSceneData: 场景已发生变化，最新请求场景ID=" + this.mRequestScreenId + ", 当前结果ID=" + i);
                return;
            }
            ZeroLogcatTools.d(TAG, "onLoadSceneData: 音源准备就绪数量=" + arrayList.size());
            audioSceneInfoEntity.setDetailInfo(arrayList);
            iSceneLoadedCallback.onSceneInfo(themeInfoEntity, audioSceneInfoEntity);
        }
    }

    public String[] findMingXiangDuration(ZeroPageMode zeroPageMode, ThemeInfoEntity themeInfoEntity) {
        if (zeroPageMode == ZeroPageMode.XIAO_QI_PRO || zeroPageMode == ZeroPageMode.XIAO_QI_STANDARD) {
            return null;
        }
        if (themeInfoEntity == null) {
            ZeroLogcatTools.e(TAG, "获取主题时长信息失败，ThemeInfoEntity == NULL");
            return null;
        }
        List<AudioSceneInfoEntity> audioInfo = themeInfoEntity.getAudioInfo();
        if (audioInfo == null || audioInfo.isEmpty()) {
            ZeroLogcatTools.e(TAG, "获取主题时长信息失败，主题:" + themeInfoEntity.getThemeName() + "下缺少场景数据");
            return new String[0];
        }
        int size = audioInfo.size();
        int[] iArr = new int[size];
        for (int i = 0; i < audioInfo.size(); i++) {
            iArr[i] = audioInfo.get(i).getDurtion();
        }
        Arrays.sort(iArr);
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = String.valueOf(iArr[i2]);
        }
        return strArr;
    }
}
