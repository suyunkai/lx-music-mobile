package com.wanos.wanosplayermodule;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.DeviceInfoResponse;
import com.wanos.WanosCommunication.response.GetAudioBookStatistic;
import com.wanos.WanosCommunication.response.GetMusicStatistic;

/* JADX INFO: loaded from: classes3.dex */
public class MediaStatistic {
    private static MediaStatistic mInstance;

    public static MediaStatistic getInstance() {
        if (mInstance == null) {
            mInstance = new MediaStatistic();
        }
        return mInstance;
    }

    public void saveRecordMediaMusic(long j, String str, int i) {
        WanOSRetrofitUtil.saveRecordMediaMusic(j, str, i, new ResponseCallBack<GetMusicStatistic>(null) { // from class: com.wanos.wanosplayermodule.MediaStatistic.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str2) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicStatistic getMusicStatistic) {
            }
        });
    }

    public void saveRecordMediaGroup(long j, String str) {
        WanOSRetrofitUtil.saveRecordMediaGroup(j, str, new ResponseCallBack<GetMusicStatistic>(null) { // from class: com.wanos.wanosplayermodule.MediaStatistic.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str2) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicStatistic getMusicStatistic) {
            }
        });
    }

    public void saveRecordDevice(String str) {
        WanOSRetrofitUtil.saveRecordDevice(str, new ResponseCallBack<DeviceInfoResponse>(null) { // from class: com.wanos.wanosplayermodule.MediaStatistic.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str2) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(DeviceInfoResponse deviceInfoResponse) {
            }
        });
    }

    public void saveRecordAudioBookChapter(long j, String str, int i) {
        WanOSRetrofitUtil.saveRecordAudioBookChapter(j, str, i, new ResponseCallBack<GetAudioBookStatistic>(null) { // from class: com.wanos.wanosplayermodule.MediaStatistic.4
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str2) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetAudioBookStatistic getAudioBookStatistic) {
            }
        });
    }

    public void saveRecordWidgetMeidaMusic(long j) {
        WanOSRetrofitUtil.saveRecordWidgetMeida(1, 0, j, new ResponseCallBack<BaseResponse>(null) { // from class: com.wanos.wanosplayermodule.MediaStatistic.5
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseResponse baseResponse) {
            }
        });
    }

    public void saveRecordWidgetMeidaMusicGroup(long j) {
        WanOSRetrofitUtil.saveRecordWidgetMeida(1, 1, j, new ResponseCallBack<BaseResponse>(null) { // from class: com.wanos.wanosplayermodule.MediaStatistic.6
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseResponse baseResponse) {
            }
        });
    }

    public void saveRecordWidgetMeidaAudioBookAlbum(long j) {
        WanOSRetrofitUtil.saveRecordWidgetMeida(1, 2, j, new ResponseCallBack<BaseResponse>(null) { // from class: com.wanos.wanosplayermodule.MediaStatistic.7
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseResponse baseResponse) {
            }
        });
    }

    public void saveRecordWidgetMeidaAudioBookBookChapter(long j) {
        WanOSRetrofitUtil.saveRecordWidgetMeida(1, 3, j, new ResponseCallBack<BaseResponse>(null) { // from class: com.wanos.wanosplayermodule.MediaStatistic.8
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseResponse baseResponse) {
            }
        });
    }
}
