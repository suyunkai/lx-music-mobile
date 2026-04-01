package com.wanos.media.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.NetWork.HttpTools;
import com.wanos.media.base.HttpCallback;
import com.wanos.media.entity.AudioSceneInfoEntity;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.CreateOrCollectUserThemeReply;
import com.wanos.media.entity.ThemeAudioInfoEntity;
import com.wanos.media.util.ImageLoadTools;
import com.wanos.media.util.PictureCacheUtils;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.view.ZeroPreView;
import com.wanos.media.widget.CustomBlurTransformation;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroPrePresenter implements IPresenter {
    private static final String TAG = "ZeroPrePresenter";
    private ZeroPreView mView;

    @Override // com.wanos.media.presenter.IPresenter
    public void onStart() {
    }

    public ZeroPrePresenter(ZeroPreView zeroPreView) {
        this.mView = zeroPreView;
    }

    public void formatBackgroundImage(Context context, final String str) {
        if (context == null || StringUtils.isEmpty(str)) {
            ZeroLogcatTools.e(TAG, "个性化模式背景加载失败, 参数异常");
            return;
        }
        String imageCachePath = PictureCacheUtils.getImageCachePath(str);
        if (FileUtils.isFileExists(imageCachePath)) {
            ZeroPreView zeroPreView = this.mView;
            if (zeroPreView == null) {
                return;
            }
            zeroPreView.onViewBackground(imageCachePath);
            return;
        }
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<String>() { // from class: com.wanos.media.presenter.ZeroPrePresenter.1
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public String doInBackground() throws Throwable {
                Bitmap bitmap = new ImageLoadTools.Builder().setWidth(1080).setHeight(720).setTransformation(new CustomBlurTransformation(20, 2, Utils.getApp())).setCovertDimens(false).getBitmap(str);
                if (bitmap == null) {
                    return "";
                }
                String localImage = PictureCacheUtils.toLocalImage(str, bitmap);
                bitmap.recycle();
                return localImage;
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(String str2) {
                if (ZeroPrePresenter.this.mView == null) {
                    return;
                }
                ZeroPrePresenter.this.mView.onViewBackground(str2);
            }
        });
    }

    public boolean isEditScreen(AudioSceneInfoEntity audioSceneInfoEntity) {
        if (audioSceneInfoEntity == null) {
            ZeroLogcatTools.e(TAG, "isEditSceneBall: 场景数据错误");
            return false;
        }
        List<ThemeAudioInfoEntity> detailInfo = audioSceneInfoEntity.getDetailInfo();
        if (detailInfo == null || detailInfo.isEmpty()) {
            ZeroLogcatTools.e(TAG, "isEditSceneBall: 音源数据错误");
            return false;
        }
        if (detailInfo.size() != ZeroAudioBallTools.getInstance().getSceneSoundIds().size()) {
            ZeroLogcatTools.d(TAG, "isEditSceneBall: 场景音源发生变化，变化类型：添加或删除");
            return true;
        }
        for (int i = 0; i < detailInfo.size(); i++) {
            if (!isContainsAudio(detailInfo.get(i).getSoundId())) {
                return true;
            }
        }
        return false;
    }

    public void setSaveAsUserTheme(long j, String str, String str2, String str3) {
        HttpTools.getInstance().saveAsUserTheme(j, str, str2, str3, new HttpCallback<CreateOrCollectUserThemeReply>() { // from class: com.wanos.media.presenter.ZeroPrePresenter.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseEntity<CreateOrCollectUserThemeReply> baseEntity) {
                if (ZeroPrePresenter.this.mView == null) {
                    return;
                }
                ZeroPrePresenter.this.mView.onCollectSuccess(baseEntity.getData());
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str4) {
                ToastUtil.showMsg(str4);
            }
        });
    }

    private boolean isContainsAudio(long j) {
        List<Long> sceneSoundIds = ZeroAudioBallTools.getInstance().getSceneSoundIds();
        for (int i = 0; i < sceneSoundIds.size(); i++) {
            if (j == sceneSoundIds.get(i).longValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.wanos.media.presenter.IPresenter
    public void onDestroy() {
        this.mView = null;
    }
}
