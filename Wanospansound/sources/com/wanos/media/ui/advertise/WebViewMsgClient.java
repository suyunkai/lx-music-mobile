package com.wanos.media.ui.advertise;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.google.gson.Gson;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.MainApplication;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class WebViewMsgClient implements OnStatusCallbackListener, MediaPlayerService.OnMediaInfoCallbackAppListener {
    static final int MSG_CLOSE_CONNECTION = 2;
    static final int MSG_CONTENT = 0;
    static final String MSG_CONTENT_KEY = "TO_CLIENT_CONTENT";
    static final int MSG_OP = 1;
    static final int MSG_WEB_CONTENT = 3;
    static final String TAG = "wanos:[WebViewMsgClient]";
    boolean mBind;
    Messenger mService;
    public WeChatLis weChatLis;
    final Messenger mMessenger = new Messenger(new ServiceMsgHandler());
    public WanosJsBridgeOfClient client = new WanosJsBridgeOfClient();
    private final ServiceConnection mConnection = new ServiceConnection() { // from class: com.wanos.media.ui.advertise.WebViewMsgClient.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName className, IBinder service) {
            MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
            if (mediaPlayerService != null) {
                mediaPlayerService.addOnStatusCallbackListener(WebViewMsgClient.this);
                mediaPlayerService.addOnMediaInfoCallbackAppListener(WebViewMsgClient.this);
            }
            WebViewMsgClient.this.mService = new Messenger(service);
            WebViewMsgClient.this.mBind = true;
            try {
                Message messageObtain = Message.obtain((Handler) null, 1);
                messageObtain.replyTo = WebViewMsgClient.this.mMessenger;
                WebViewMsgClient.this.mService.send(messageObtain);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e(WebViewMsgClient.TAG, "e:" + e.getMessage());
            }
            if (WebViewMsgClient.this.weChatLis != null) {
                WebViewMsgClient.this.weChatLis.onConnect();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
            if (mediaPlayerService != null) {
                mediaPlayerService.removeOnStatusCallbackListener(WebViewMsgClient.this);
                mediaPlayerService.removeOnMediaInfoCallbackAppListener(WebViewMsgClient.this);
            }
            WebViewMsgClient.this.mService = null;
            WebViewMsgClient.this.mBind = false;
        }
    };

    public interface WeChatLis {
        void onConnect();

        void onWechatCode(String code);

        void onWechatImgUrl(String url);
    }

    @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState status, Object... args) {
        if (status == MediaPlayerEnum.CallBackState.PREPARING || status == MediaPlayerEnum.CallBackState.PREPARE || status == MediaPlayerEnum.CallBackState.PAUSE || status == MediaPlayerEnum.CallBackState.ERROR || status == MediaPlayerEnum.CallBackState.EXCEPTION || status == MediaPlayerEnum.CallBackState.COMPLETE || status == MediaPlayerEnum.CallBackState.STARTED) {
            setCallBackState();
        }
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, MediaInfo mediaInfo) {
        if (mediaInfoCallbackType != MediaPlayerEnum.MediaInfoCallbackType.favorStatus || mediaInfo == null || mediaInfo.getMusicInfoBean() == null) {
            return;
        }
        this.client.collectCall(mediaInfo.getMusicInfoBean().getMusicId(), mediaInfo.getMusicInfoBean().isCollection() ? 1 : 0);
    }

    public void setCallBackState() {
        int i;
        long j;
        long j2;
        int i2;
        long radioId;
        long id;
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        if (mediaPlayerService == null || mediaPlayerService.getCurMediaInfo() == null) {
            i = -1;
            j = -1;
            j2 = -1;
        } else {
            if (mediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Music) {
                radioId = mediaPlayerService.getCurMediaInfo().getGroupId();
                id = mediaPlayerService.getCurMediaInfo().getMusicInfoBean().getMusicId();
                i = mediaPlayerService.getCurMediaInfo().getMediaGroupType() == -7 ? 1 : 2;
            } else {
                if (mediaPlayerService.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
                    AudioBookMineChapterItemBean audioBookInfoBean = mediaPlayerService.getCurMediaInfo().getAudioBookInfoBean();
                    radioId = audioBookInfoBean.getRadioId();
                    id = audioBookInfoBean.getId();
                    i = 3;
                }
                i = -1;
                j = -1;
                j2 = -1;
            }
            j2 = id;
            j = radioId;
        }
        if (j != -1) {
            MediaPlayerEnum.CallBackState playingStatus = AudioBookGlobalParams.getPlayingStatus();
            if (playingStatus == MediaPlayerEnum.CallBackState.PREPARING) {
                i2 = 1;
            } else {
                i2 = (playingStatus != MediaPlayerEnum.CallBackState.PAUSE && playingStatus == MediaPlayerEnum.CallBackState.STARTED) ? 2 : 0;
            }
            this.client.setPlayState(j, j2, i2, i);
        }
    }

    public void setLis(WeChatLis weChatLis) {
        this.weChatLis = weChatLis;
    }

    public void removeWeb() {
        this.weChatLis = null;
        try {
            MainApplication.getInstance().unbindService(this.mConnection);
        } catch (Exception e) {
            Log.e(TAG, "e:" + e.getMessage());
        }
    }

    class ServiceMsgHandler extends Handler {
        ServiceMsgHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 0) {
                String string = msg.getData().getString(WebViewMsgClient.MSG_CONTENT_KEY);
                WebViewCommunicationBase webViewCommunicationBase = (WebViewCommunicationBase) new Gson().fromJson(string, WebViewCommunicationBase.class);
                if (WebViewCommunicationBase.ACTION_LOGIN.equals(webViewCommunicationBase.action)) {
                    WebViewUserinfo webViewUserinfo = (WebViewUserinfo) new Gson().fromJson(string, WebViewUserinfo.class);
                    if (webViewUserinfo != null) {
                        UserInfoUtil.saveUserInfo(webViewUserinfo.userInfo);
                        EventBus.getDefault().post(new LoginOrLogoutEvent(true));
                        return;
                    }
                    return;
                }
                if (WebViewCommunicationBase.ACTION_WEB_INIT_SUCCESS.equals(webViewCommunicationBase.action)) {
                    WebViewMsgClient.this.setCallBackState();
                    return;
                }
                return;
            }
            if (i == 1) {
                WebViewMsgClient.this.client.sendH5Object(msg.getData().getString(WebViewMsgClient.MSG_CONTENT_KEY));
                return;
            }
            if (i == 2) {
                try {
                    MainApplication.getInstance().unbindService(WebViewMsgClient.this.mConnection);
                } catch (Exception e) {
                    Log.e(WebViewMsgClient.TAG, "e:" + e.getMessage());
                }
            } else if (i == 3) {
                WebViewCommunicationBase webViewCommunicationBase2 = (WebViewCommunicationBase) new Gson().fromJson(msg.getData().getString(WebViewMsgClient.MSG_CONTENT_KEY), WebViewCommunicationBase.class);
                if (webViewCommunicationBase2.action.equals(WebViewCommunicationBase.ACTION_WECHAT_URL)) {
                    if (WebViewMsgClient.this.weChatLis != null) {
                        WebViewMsgClient.this.weChatLis.onWechatImgUrl(webViewCommunicationBase2.para);
                        return;
                    }
                    return;
                } else {
                    if (!webViewCommunicationBase2.action.equals(WebViewCommunicationBase.ACTION_WECHAT_LOGIN_CODE) || WebViewMsgClient.this.weChatLis == null) {
                        return;
                    }
                    WebViewMsgClient.this.weChatLis.onWechatCode(webViewCommunicationBase2.para);
                    return;
                }
            }
            super.handleMessage(msg);
        }
    }

    public void bindService(Context context) {
        MainApplication.getInstance().bindService(new Intent(context, (Class<?>) WebViewMessengerService.class), this.mConnection, 1);
    }

    public void sendMessage(String content) {
        if (this.mService != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("TO_SERVICE_CONTENT", content);
                Message messageObtain = Message.obtain((Handler) null, 3);
                messageObtain.setData(bundle);
                this.mService.send(messageObtain);
            } catch (Exception e) {
                Log.e(TAG, "e:" + e.getMessage());
            }
        }
    }

    public void sendOpenWebMessage(String content) {
        if (this.mService != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("TO_SERVICE_CONTENT", content);
                Message messageObtain = Message.obtain((Handler) null, 4);
                messageObtain.setData(bundle);
                this.mService.send(messageObtain);
            } catch (Exception e) {
                Log.e(TAG, "e:" + e.getMessage());
            }
        }
    }
}
