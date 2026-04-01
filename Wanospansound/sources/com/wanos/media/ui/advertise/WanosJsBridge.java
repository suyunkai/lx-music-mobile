package com.wanos.media.ui.advertise;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
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
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.R;
import com.wanos.media.ui.audiobook.activity.AudioBookAlbumActivity;
import com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter;
import com.wanos.media.ui.login.dialog.LoginDialog;
import com.wanos.media.ui.music.activity.MusicListActivity;
import com.wanos.media.ui.music.activity.MusicPlayActivity;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class WanosJsBridge implements IWanosJsBridge {
    public static final String H5_KEY_ACTIVITY = "activity";
    public static final String H5_KEY_PARA = "params";
    public static final String H5_KEY_TYPE = "type";
    public static final String METHOD_KEY_CLOSE_HTML = "closeHtml";
    public static final String METHOD_KEY_COLLECT = "collect";
    public static final String METHOD_KEY_OPEN_DETAIL = "cover";
    public static final String METHOD_KEY_PAUSE = "pause";
    public static final String METHOD_KEY_PLAY = "play";
    public static final String METHOD_KEY_PLAY_STATUS = "playStatus";
    public static final String METHOD_KEY_RECHARGE = "recharge";
    public static final String METHOD_KEY_SONG_DETAIL = "songDetail";
    public static final String METHOD_KEY_THEME = "theme";
    public static final String METHOD_KEY_TOKEN = "token";
    public static final String METHOD_KEY_TO_LOGIN = "toLogin";
    public static final String PARA_KEY_GROUP_ID = "groupId";
    public static final String PARA_KEY_GROUP_TYPE = "groupType";
    public static final String PARA_KEY_INFO = "info";
    public static final String PARA_KEY_SONG_ID = "songId";
    public static final String PARA_KEY_SONG_IS_COLLECT = "isCollect";
    public static final String PARA_KEY_TYPE = "type";
    private static final String TAG = "wanos:[WanosJsBridge]";
    public static String jsName = "wanosApp";
    private final WebViewActivity mContext;
    private final WebView mWebView;
    private final int playType = 0;

    public WanosJsBridge(WebViewActivity context, WebView webView) {
        this.mContext = context;
        this.mWebView = webView;
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    public void setNightMode(int nightMode) {
        HashMap map = new HashMap();
        map.put("type", Character.valueOf(nightMode == 32 ? '0' : '1'));
        send2Js(map, METHOD_KEY_THEME);
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    public void setPlayState(long groupId, long id, int state, int type) {
        HashMap map = new HashMap();
        map.put("type", Integer.valueOf(type));
        map.put("groupId", Long.valueOf(groupId));
        map.put("id", Long.valueOf(id));
        map.put(IServiceManager.SERVICE_STATE, Integer.valueOf(state));
        send2Js(map, METHOD_KEY_PLAY_STATUS);
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    public void collectCall(long id, int isCollect) {
        HashMap map = new HashMap();
        map.put(PARA_KEY_SONG_IS_COLLECT, Integer.valueOf(isCollect));
        map.put(PARA_KEY_SONG_ID, Long.valueOf(id));
        send2Js(map, METHOD_KEY_COLLECT);
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    @JavascriptInterface
    public String testjs(int a2, String b2) {
        return b2 + "_" + a2;
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    @JavascriptInterface
    public void openPayVipPage(int type) {
        this.mContext.openWeChatPayActivity(type, 2);
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    @JavascriptInterface
    public void sendH5Object(String content) {
        try {
            Log.i(TAG, "get h5 data:" + content);
            final H5Object h5Object = (H5Object) new Gson().fromJson(content, H5Object.class);
            if (METHOD_KEY_PLAY.equals(h5Object.type)) {
                if (WebViewMessengerService.messengerService != null) {
                    WebViewMessengerService.messengerService.sendOpToClient(content);
                }
            } else if (METHOD_KEY_OPEN_DETAIL.equals(h5Object.type)) {
                openGroupPage(Integer.parseInt(h5Object.params.get(PARA_KEY_GROUP_TYPE).toString().split("\\.")[0]), Long.parseLong(h5Object.params.get("groupId").toString().split("\\.")[0]));
            } else if (METHOD_KEY_PAUSE.equals(h5Object.type)) {
                if (WebViewMessengerService.messengerService != null) {
                    WebViewMessengerService.messengerService.sendOpToClient(content);
                }
            } else if (METHOD_KEY_COLLECT.equals(h5Object.type)) {
                collect(Long.parseLong(h5Object.params.get(PARA_KEY_SONG_ID).toString().split("\\.")[0]), Integer.parseInt(h5Object.params.get(PARA_KEY_SONG_IS_COLLECT).toString().split("\\.")[0]));
            } else if (METHOD_KEY_RECHARGE.equals(h5Object.type)) {
                this.mContext.runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridge$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m485lambda$sendH5Object$0$comwanosmediauiadvertiseWanosJsBridge(h5Object);
                    }
                });
            } else if (!METHOD_KEY_SONG_DETAIL.equals(h5Object.type)) {
                if (METHOD_KEY_CLOSE_HTML.equals(h5Object.type)) {
                    this.mContext.runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridge$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m486lambda$sendH5Object$1$comwanosmediauiadvertiseWanosJsBridge();
                        }
                    });
                } else if (METHOD_KEY_TO_LOGIN.equals(h5Object.type)) {
                    this.mContext.runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridge$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m487lambda$sendH5Object$2$comwanosmediauiadvertiseWanosJsBridge();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: lambda$sendH5Object$0$com-wanos-media-ui-advertise-WanosJsBridge, reason: not valid java name */
    /* synthetic */ void m485lambda$sendH5Object$0$comwanosmediauiadvertiseWanosJsBridge(H5Object h5Object) {
        try {
            openPayVipPage(Integer.parseInt(h5Object.params.get("type").toString().split("\\.")[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: lambda$sendH5Object$1$com-wanos-media-ui-advertise-WanosJsBridge, reason: not valid java name */
    /* synthetic */ void m486lambda$sendH5Object$1$comwanosmediauiadvertiseWanosJsBridge() {
        try {
            this.mContext.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: lambda$sendH5Object$2$com-wanos-media-ui-advertise-WanosJsBridge, reason: not valid java name */
    /* synthetic */ void m487lambda$sendH5Object$2$comwanosmediauiadvertiseWanosJsBridge() {
        try {
            this.mContext.showLoginDialog();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "error:" + e.getMessage());
        }
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    @JavascriptInterface
    public void openGroupPage(int type, long groupId) {
        if (type == 2) {
            MusicListActivity.startMusicListActivity(this.mContext, groupId);
        } else if (type == 3) {
            AudioBookAlbumActivity.buildAlbumPage(this.mContext, groupId, false);
        }
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    @JavascriptInterface
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
                    this.mContext.runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridge$$ExternalSyntheticLambda3
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
                this.mContext.runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridge$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m484lambda$playGroup$4$comwanosmediauiadvertiseWanosJsBridge(audioBookAlbumInfoBean);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: lambda$playGroup$4$com-wanos-media-ui-advertise-WanosJsBridge, reason: not valid java name */
    /* synthetic */ void m484lambda$playGroup$4$comwanosmediauiadvertiseWanosJsBridge(AudioBookAlbumInfoBean audioBookAlbumInfoBean) {
        AudioBookAlbumListBaseAdapter.clickPlayBtn(this.mContext, audioBookAlbumInfoBean);
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    @JavascriptInterface
    public void collect(final long id, int isCollect) {
        if (!UserInfoUtil.isLogin()) {
            this.mContext.runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridge.1
                @Override // java.lang.Runnable
                public void run() {
                    LoginDialog loginDialog = new LoginDialog(WanosJsBridge.this.mContext);
                    loginDialog.setCancelable(true);
                    loginDialog.show();
                }
            });
        } else if (isCollect != 1) {
            WanOSRetrofitUtil.musicCollectCancel(id, 2, new ResponseCallBack<BaseResponse>(this.mContext) { // from class: com.wanos.media.ui.advertise.WanosJsBridge.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    WanosJsBridge.this.showMsg(R.string.music_cancel_collect_complete);
                    WanosJsBridge.this.collectCall(id, 0);
                    if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, id, false);
                    }
                }
            });
        } else {
            WanOSRetrofitUtil.musicCollect(id, 2, new ResponseCallBack<BaseResponse>(this.mContext) { // from class: com.wanos.media.ui.advertise.WanosJsBridge.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    WanosJsBridge.this.showMsg(R.string.music_collect_complete);
                    WanosJsBridge.this.collectCall(id, 1);
                    if (MediaPlayEngine.getInstance().getMediaPlayerService() != null) {
                        MediaPlayEngine.getInstance().getMediaPlayerService().onCollect(MediaPlayerEnum.MediaType.Music, id, true);
                    }
                }
            });
        }
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    @JavascriptInterface
    public void openPlayPage(String bean, long groupId) {
        try {
            MusicInfoBean musicInfoBean = (MusicInfoBean) new Gson().fromJson(bean, MusicInfoBean.class);
            if (musicInfoBean != null) {
                musicInfoBean.setPageSize(100);
                MediaInfo mediaInfo = new MediaInfo();
                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                mediaInfo.setGroupId(groupId);
                mediaInfo.setMediaGroupType(-7L);
                mediaInfo.setMusicInfoBean(musicInfoBean);
                MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
                MusicPlayActivity.startMusicPlayActivity(this.mContext);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.wanos.media.ui.advertise.IWanosJsBridge
    public void updataToken(String token) {
        HashMap map = new HashMap();
        map.put(METHOD_KEY_TOKEN, token);
        send2Js(map, METHOD_KEY_TOKEN);
    }

    public void send2Js(HashMap content, String method) {
        HashMap map = new HashMap();
        map.put("type", method);
        map.put(H5_KEY_PARA, content);
        map.put(H5_KEY_ACTIVITY, "jili-activity1");
        String json = new Gson().toJson(map);
        Log.i(TAG, "send 2 js content:" + json);
        callJs("javascript:htmlApp.sendAPPObject(" + json + ");");
    }

    public void callJs(final String jsCode) {
        this.mContext.runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.advertise.WanosJsBridge.4
            @Override // java.lang.Runnable
            public void run() {
                WanosJsBridge.this.mWebView.evaluateJavascript(jsCode, new ValueCallback<String>() { // from class: com.wanos.media.ui.advertise.WanosJsBridge.4.1
                    @Override // android.webkit.ValueCallback
                    public void onReceiveValue(String s) {
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMsg(int msg) {
        showMsg(this.mContext.getString(msg));
    }

    private void showMsg(String msg) {
        ToastUtil.showMsg(msg);
    }
}
