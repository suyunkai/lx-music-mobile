package com.wanos.media.ui.advertise;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.ecarx.eas.sdk.IServiceManager;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.R;
import com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter;
import com.wanos.media.util.AppManager;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class WanosJsBridgeOfClient {
    private static final String TAG = "wanos:[WanosJsBridgeOfClient]";
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private int playType = 0;

    public void setPlayState(long groupId, long id, int state, int type) {
        HashMap map = new HashMap();
        map.put("type", Integer.valueOf(type));
        map.put("groupId", Long.valueOf(groupId));
        map.put("id", Long.valueOf(id));
        map.put(IServiceManager.SERVICE_STATE, Integer.valueOf(state));
        send2Js(map, WanosJsBridge.METHOD_KEY_PLAY_STATUS);
    }

    public void collectCall(long id, int isCollect) {
        HashMap map = new HashMap();
        map.put(WanosJsBridge.PARA_KEY_SONG_IS_COLLECT, Integer.valueOf(isCollect));
        map.put(WanosJsBridge.PARA_KEY_SONG_ID, Long.valueOf(id));
        send2Js(map, WanosJsBridge.METHOD_KEY_COLLECT);
    }

    public void sendH5Object(String content) {
        try {
            H5Object h5Object = (H5Object) new Gson().fromJson(content, H5Object.class);
            if (WanosJsBridge.METHOD_KEY_PLAY.equals(h5Object.type)) {
                int i = Integer.parseInt(h5Object.params.get(WanosJsBridge.PARA_KEY_GROUP_TYPE).toString().split("\\.")[0]);
                this.playType = i;
                if (i == 1) {
                    play(new Gson().toJson(h5Object.params.get(WanosJsBridge.PARA_KEY_INFO)), Long.parseLong(h5Object.params.get("groupId").toString().split("\\.")[0]));
                } else {
                    playGroup(i, Long.parseLong(h5Object.params.get("groupId").toString().split("\\.")[0]), new Gson().toJson(h5Object.params.get(WanosJsBridge.PARA_KEY_INFO)));
                }
            } else if (WanosJsBridge.METHOD_KEY_PAUSE.equals(h5Object.type)) {
                pause();
            } else if (WanosJsBridge.METHOD_KEY_COLLECT.equals(h5Object.type)) {
                collect(Long.parseLong(h5Object.params.get(WanosJsBridge.PARA_KEY_SONG_ID).toString().split("\\.")[0]), Integer.parseInt(h5Object.params.get(WanosJsBridge.PARA_KEY_SONG_IS_COLLECT).toString().split("\\.")[0]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playGroup(int type, long groupId, String beanInfo) {
        try {
            if (type == 2) {
                MusicInfoBean musicInfoBean = (MusicInfoBean) new Gson().fromJson(beanInfo, MusicInfoBean.class);
                if (musicInfoBean != null) {
                    musicInfoBean.setPageSize(100);
                    final MediaInfo mediaInfo = new MediaInfo();
                    mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                    mediaInfo.setGroupId(groupId);
                    mediaInfo.setMediaGroupType(-6L);
                    mediaInfo.setMusicInfoBean(musicInfoBean);
                    this.mainHandler.post(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridgeOfClient$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
                        }
                    });
                }
            } else {
                if (type != 3) {
                    return;
                }
                final AudioBookAlbumInfoBean audioBookAlbumInfoBean = (AudioBookAlbumInfoBean) new Gson().fromJson(beanInfo, AudioBookAlbumInfoBean.class);
                this.mainHandler.post(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridgeOfClient$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioBookAlbumListBaseAdapter.clickPlayBtn(AppManager.getAppManager().currentActivity(), audioBookAlbumInfoBean);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(String bean, long groupId) {
        try {
            MusicInfoBean musicInfoBean = (MusicInfoBean) new Gson().fromJson(bean, MusicInfoBean.class);
            if (musicInfoBean != null) {
                musicInfoBean.setPageSize(100);
                final MediaInfo mediaInfo = new MediaInfo();
                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                mediaInfo.setMediaGroupType(-7L);
                mediaInfo.setGroupId(groupId);
                mediaInfo.setMusicInfoBean(musicInfoBean);
                Log.e(TAG, "client play musicInfoBean = " + musicInfoBean);
                this.mainHandler.post(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridgeOfClient$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        WanosJsBridgeOfClient.lambda$play$2(mediaInfo);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ void lambda$play$2(MediaInfo mediaInfo) {
        try {
            MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        this.mainHandler.post(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridgeOfClient$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MediaPlayEngine.getInstance().getMediaPlayerService().pause();
            }
        });
    }

    public void collect(final long id, int isCollect) {
        if (isCollect != 1) {
            WanOSRetrofitUtil.musicCollectCancel(id, 2, new ResponseCallBack<BaseResponse>(AppManager.getAppManager().currentActivity()) { // from class: com.wanos.media.ui.advertise.WanosJsBridgeOfClient.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    ToastUtil.showMsg(R.string.music_cancel_collect_complete);
                    WanosJsBridgeOfClient.this.collectCall(id, 0);
                    if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, id, false);
                    }
                }
            });
        } else {
            WanOSRetrofitUtil.musicCollect(id, 2, new ResponseCallBack<BaseResponse>(AppManager.getAppManager().currentActivity()) { // from class: com.wanos.media.ui.advertise.WanosJsBridgeOfClient.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    ToastUtil.showMsg(R.string.music_collect_complete);
                    WanosJsBridgeOfClient.this.collectCall(id, 1);
                    if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, id, true);
                    }
                }
            });
        }
    }

    public void send2Js(HashMap content, String method) {
        HashMap map = new HashMap();
        map.put("type", method);
        map.put(WanosJsBridge.H5_KEY_PARA, content);
        map.put(WanosJsBridge.H5_KEY_ACTIVITY, "jili-activity1");
        String str = "javascript:htmlApp.sendAPPObject(" + new Gson().toJson(map) + ");";
        if (WebViewActivity.webViewMsgClient != null) {
            WebViewActivity.webViewMsgClient.sendMessage(str);
        }
    }
}
