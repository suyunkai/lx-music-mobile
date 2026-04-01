package com.wanos.media.presenter;

import android.app.Activity;
import android.content.Context;
import com.arcvideo.ivi.res.sdk.ArcResSdk;
import com.arcvideo.ivi.res.sdk.data.RegisterInfo;
import com.arcvideo.ivi.res.sdk.data.VideoInfo;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.commonlibrary.mediaCenter.CarInfo;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.BuildConfig;
import com.wanos.media.MainApplication;
import com.wanos.media.R;
import com.wanos.media.view.VideoBaseView;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class VideoPresenter extends BasePresenter<VideoBaseView> {
    private static final String GEERLY_UUID = "20220907114320423jUiYiXYdS102019";
    private static final String iqyResouceId = "44309853612";
    private final Context mContext;

    public VideoPresenter(Context context, VideoBaseView view) {
        this.mView = view;
        this.mContext = context;
    }

    public void getIQYList() {
        CarInfo carInfo = MainApplication.getInstance().carInfo;
        if (carInfo == null) {
            ((VideoBaseView) this.mView).showFailOrError("");
            return;
        }
        String vin = carInfo.getVin();
        if (StringUtils.isEmpty(vin)) {
            ((VideoBaseView) this.mView).showFailOrError("");
        } else {
            initIQY(vin);
        }
    }

    private void initIQY(String vin) {
        ArcResSdk.getInstance().register(vin, GEERLY_UUID, System.currentTimeMillis(), BuildConfig.VERSION_NAME, new AnonymousClass1());
    }

    /* JADX INFO: renamed from: com.wanos.media.presenter.VideoPresenter$1, reason: invalid class name */
    class AnonymousClass1 implements ArcResSdk.OnResultListener<RegisterInfo> {
        AnonymousClass1() {
        }

        @Override // com.arcvideo.ivi.res.sdk.ArcResSdk.OnResultListener
        public void onResult(RegisterInfo registerInfo) {
            ArcResSdk.getInstance().getResource(VideoPresenter.iqyResouceId, new ArcResSdk.OnResultListener<List<VideoInfo>>() { // from class: com.wanos.media.presenter.VideoPresenter.1.1
                @Override // com.arcvideo.ivi.res.sdk.ArcResSdk.OnResultListener
                public void onResult(List<VideoInfo> videoInfos) {
                    ((VideoBaseView) VideoPresenter.this.mView).updateView(videoInfos);
                }

                @Override // com.arcvideo.ivi.res.sdk.ArcResSdk.OnResultListener
                public void onError(int i, String s) {
                    ((Activity) VideoPresenter.this.mContext).runOnUiThread(new Runnable() { // from class: com.wanos.media.presenter.VideoPresenter.1.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ToastUtil.showMsg(R.string.get_iqy_error);
                            ((VideoBaseView) VideoPresenter.this.mView).showFailOrError("");
                        }
                    });
                }
            });
        }

        @Override // com.arcvideo.ivi.res.sdk.ArcResSdk.OnResultListener
        public void onError(int i, String s) {
            ((Activity) VideoPresenter.this.mContext).runOnUiThread(new Runnable() { // from class: com.wanos.media.presenter.VideoPresenter.1.2
                @Override // java.lang.Runnable
                public void run() {
                    ToastUtil.showMsg(R.string.get_iqy_error);
                    ((VideoBaseView) VideoPresenter.this.mView).showFailOrError("");
                }
            });
        }
    }
}
