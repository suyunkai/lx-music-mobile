package com.wanos.media.widget.video;

import android.graphics.Bitmap;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.entity.AudioSceneInfoEntity;
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
public class WanosVideoRepository {
    private static final String TAG = "WanosVideoRepository";
    private int mNowRequestThemeId = -1;
    private int mNowRequestSceneId = -1;
    private boolean isDestroy = false;

    public interface ISceneInfoLoaded {
        void onSceneInfoLoadFailed(String str);

        void onSceneInfoLoaded(AudioSceneInfoEntity audioSceneInfoEntity);
    }

    public interface IThemeInfoLoaded {
        void onThemeInfoLoadFailed(String str);

        void onThemeInfoLoaded(ThemeInfoEntity themeInfoEntity);
    }

    public enum LoadingState {
        LOADING,
        LOADED_SUCCESS,
        LOADED_ERROR
    }

    public void onActivityDestroy() {
        this.isDestroy = true;
    }

    /* JADX INFO: renamed from: com.wanos.media.widget.video.WanosVideoRepository$1, reason: invalid class name */
    class AnonymousClass1 extends ThreadUtils.SimpleTask<ThemeInfoEntity> {
        final /* synthetic */ IThemeInfoLoaded val$iThemeInfoLoaded;
        final /* synthetic */ ZeroPageMode val$initMode;
        final /* synthetic */ int val$themeId;

        AnonymousClass1(ZeroPageMode zeroPageMode, int i, IThemeInfoLoaded iThemeInfoLoaded) {
            this.val$initMode = zeroPageMode;
            this.val$themeId = i;
            this.val$iThemeInfoLoaded = iThemeInfoLoaded;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public ThemeInfoEntity doInBackground() throws Throwable {
            ThemeInfoEntity data;
            Bitmap bitmap;
            if (this.val$initMode == ZeroPageMode.MING_XIANG_PRO || this.val$initMode == ZeroPageMode.XIAO_QI_PRO) {
                ZeroLogcatTools.i(WanosVideoRepository.TAG, "onLoadThemeInfoData: 获取自定义主题详情");
                data = HttpTools.getInstance().getUserThemeInfoSync(this.val$themeId).getData();
            } else {
                ZeroLogcatTools.i(WanosVideoRepository.TAG, "onLoadThemeInfoData: 获取模板主题详情");
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
            if (themeInfoEntity == null || WanosVideoRepository.this.isDestroy || themeInfoEntity.getThemeId() != WanosVideoRepository.this.mNowRequestThemeId) {
                this.val$iThemeInfoLoaded.onThemeInfoLoadFailed(StringUtils.getString(R.string.error_scene_load));
                ZeroLogcatTools.e(WanosVideoRepository.TAG, "onLoadThemeInfoData: 数据请求失败");
            } else if (themeInfoEntity.getBgType() == 2) {
                String videoResource = themeInfoEntity.getVideoResource();
                final IThemeInfoLoaded iThemeInfoLoaded = this.val$iThemeInfoLoaded;
                VideoCacheUtils.onLaunchTask(videoResource, new VideoCacheUtils.IVideoCacheListener() { // from class: com.wanos.media.widget.video.WanosVideoRepository$1$$ExternalSyntheticLambda0
                    @Override // com.wanos.media.util.VideoCacheUtils.IVideoCacheListener
                    public final void onTaskSuccess(int i, String str, Object obj) {
                        this.f$0.m661xc222eda(iThemeInfoLoaded, i, str, obj);
                    }
                }, themeInfoEntity);
            }
        }

        /* JADX INFO: renamed from: lambda$onSuccess$0$com-wanos-media-widget-video-WanosVideoRepository$1, reason: not valid java name */
        /* synthetic */ void m661xc222eda(IThemeInfoLoaded iThemeInfoLoaded, int i, String str, Object obj) {
            ThemeInfoEntity themeInfoEntity = (ThemeInfoEntity) obj;
            if (themeInfoEntity == null || themeInfoEntity.getThemeId() != WanosVideoRepository.this.mNowRequestThemeId || WanosVideoRepository.this.isDestroy) {
                ZeroLogcatTools.e(WanosVideoRepository.TAG, "onLoadThemeInfoData: 主题已发生变化，最新请求主题视频ID=" + WanosVideoRepository.this.mNowRequestThemeId + ",当前ID=" + (themeInfoEntity == null ? "NULL" : Long.valueOf(themeInfoEntity.getThemeId())));
            } else {
                iThemeInfoLoaded.onThemeInfoLoaded(themeInfoEntity);
            }
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
        public void onFail(Throwable th) {
            super.onFail(th);
            this.val$iThemeInfoLoaded.onThemeInfoLoadFailed(th.getMessage() == null ? StringUtils.getString(R.string.error_scene_load) : th.getMessage());
        }
    }

    public void onLoadThemeInfoData(ZeroPageMode zeroPageMode, int i, IThemeInfoLoaded iThemeInfoLoaded) {
        this.mNowRequestThemeId = i;
        ThreadUtils.executeByIo(new AnonymousClass1(zeroPageMode, i, iThemeInfoLoaded));
    }

    public void onLoadSceneInfoData(ThemeInfoEntity themeInfoEntity, int i, final ISceneInfoLoaded iSceneInfoLoaded) {
        List<AudioSceneInfoEntity> audioInfo = themeInfoEntity.getAudioInfo();
        if (audioInfo == null || audioInfo.isEmpty()) {
            ZeroLogcatTools.e(TAG, "onLoadSceneInfoData: 场景数据错误，主题<" + themeInfoEntity.getThemeName() + ">缺少场景数据");
            iSceneInfoLoaded.onSceneInfoLoadFailed(StringUtils.getString(R.string.unknown_state));
            return;
        }
        if (i >= audioInfo.size()) {
            ZeroLogcatTools.e(TAG, "onLoadSceneInfoData: 场景数据错误，请求位置 = " + i + ", 场景数量 = " + audioInfo.size());
            iSceneInfoLoaded.onSceneInfoLoadFailed(StringUtils.getString(R.string.unknown_state));
            return;
        }
        final AudioSceneInfoEntity audioSceneInfoEntity = audioInfo.get(i);
        List<ThemeAudioInfoEntity> detailInfo = audioSceneInfoEntity.getDetailInfo();
        if (detailInfo == null || detailInfo.isEmpty()) {
            ZeroLogcatTools.e(TAG, "onLoadSceneInfoData: 场景数据错误，场景<" + audioSceneInfoEntity.getAudioId() + ">缺少音源信息");
            iSceneInfoLoaded.onSceneInfoLoadFailed(StringUtils.getString(R.string.unknown_state));
        } else {
            this.mNowRequestSceneId = (int) audioSceneInfoEntity.getAudioId();
            MusicCacheUtils.onLaunchSceneTask(detailInfo, (int) audioSceneInfoEntity.getAudioId(), new MusicCacheUtils.IMultiAudioCacheCallback() { // from class: com.wanos.media.widget.video.WanosVideoRepository$$ExternalSyntheticLambda0
                @Override // com.wanos.media.util.MusicCacheUtils.IMultiAudioCacheCallback
                public final void onTaskSuccess(ArrayList arrayList, int i2) {
                    this.f$0.m660x38da9fe8(audioSceneInfoEntity, iSceneInfoLoaded, arrayList, i2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onLoadSceneInfoData$0$com-wanos-media-widget-video-WanosVideoRepository, reason: not valid java name */
    /* synthetic */ void m660x38da9fe8(AudioSceneInfoEntity audioSceneInfoEntity, ISceneInfoLoaded iSceneInfoLoaded, ArrayList arrayList, int i) {
        if (this.isDestroy || i != this.mNowRequestSceneId) {
            ZeroLogcatTools.e(TAG, "onLoadSceneInfoData: 界面已销毁或者请求ID同步。isDestroy = " + this.isDestroy + ", sceneId = " + i + ", mNowRequestSceneId = " + this.mNowRequestSceneId);
        } else {
            audioSceneInfoEntity.setDetailInfo(arrayList);
            iSceneInfoLoaded.onSceneInfoLoaded(audioSceneInfoEntity);
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
