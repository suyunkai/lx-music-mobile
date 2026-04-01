package com.wanos.mediacenter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.media3.exoplayer.ExoPlayer;
import com.ecarx.eas.sdk.mediacenter.MediaCenterAPI;
import com.ecarx.eas.sdk.mediacenter.SemanticsType;
import com.ecarx.eas.sdk.mediacenter.exception.MediaCenterException;
import com.ecarx.eas.sdk.vr.channel.SemanticData;
import com.ecarx.eas.sdk.vr.channel.VrChannelDataListener;
import com.ecarx.eas.sdk.vr.channel.VrChannelInfo;
import com.ecarx.eas.sdk.vr.channel.VrTtsResultListener;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaCenter.MediaCenterServer;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.mediacenter.SemanticsManager;
import com.wanos.mediacenter.utils.AudioFocusUtils;
import com.wanos.mediacenter.utils.TTSUtil;

/* JADX INFO: loaded from: classes3.dex */
public class SemanticsManager {
    public static final String TAG = "wanos:[SemanticsManager]";
    private final MediaCenterServer action;
    AudioFocusUtils audioFocus;
    private Handler mHandler;
    private final MediaCenterAPI mMediaCenterAPI;
    private PlayCallback playCallback;
    private final Object token;
    private TTSUtil ttsUtil;
    boolean ttsOnceFlag = false;
    boolean ttsOnceFlagOther = false;
    Runnable runnable = null;
    String[] supportWordArray = {"我想听歌", "我要听歌", "播放歌曲", "打开歌曲", "打开音乐", "播放音乐", "想听音乐", "我要音乐", "来点歌曲", "来点音乐", "播放全景声", "播放全景声音乐", "打开全景声音乐", "我要听全景声", "继续播放", "播放", "来首歌", "放首歌", "来一首歌"};

    public interface PlayCallback {
        default String getReplayText() {
            return "好的";
        }

        void onCompleted();
    }

    public SemanticsManager(Context context, MediaCenterServer mediaCenterServer, MediaCenterAPI mediaCenterAPI, Object obj) {
        initTTS(context);
        this.action = mediaCenterServer;
        this.mMediaCenterAPI = mediaCenterAPI;
        this.token = obj;
        this.audioFocus = new AudioFocusUtils(context);
        this.mHandler = new Handler();
    }

    public boolean registerChannel() {
        Log.d(TAG, "registerChannel: ");
        MediaCenterAPI mediaCenterAPI = this.mMediaCenterAPI;
        if (mediaCenterAPI != null) {
            try {
                return mediaCenterAPI.declareVrSemanticsCapability(this.token, new VrChannelInfo("com.wanos.media", "1.0", "your app description", 0), new int[]{33554433, SemanticsType.CONTROL_START, SemanticsType.CONTROL_PAUSE, SemanticsType.CONTROL_NEXT, SemanticsType.CONTROL_PREVIOUS, SemanticsType.CONTROL_MODE, SemanticsType.CONTROL_COLLECT, SemanticsType.CONTROL_CANCEL_COLLECT, SemanticsType.MUSIC_PLAY, SemanticsType.CONTROL_CLOSE}, new AnonymousClass1());
            } catch (Throwable th) {
                Log.i(TAG, "th:" + th.getMessage());
                return false;
            }
        }
        Log.i(TAG, "mediaCenterAPI is null");
        return false;
    }

    /* JADX INFO: renamed from: com.wanos.mediacenter.SemanticsManager$1, reason: invalid class name */
    class AnonymousClass1 extends VrChannelDataListener {
        static /* synthetic */ void lambda$handleVrChannelData$5() {
        }

        static /* synthetic */ void lambda$handleVrChannelData$7() {
        }

        static /* synthetic */ void lambda$handleVrChannelData$8() {
        }

        AnonymousClass1() {
        }

        @Override // com.ecarx.eas.sdk.vr.channel.VrChannelDataListener, com.ecarx.eas.sdk.vr.channel.VrChannelDataHandling
        public void handleVrChannelData(int i, SemanticData semanticData) {
            Log.i(SemanticsManager.TAG, "wanos收到语音指令 " + semanticData.getModeType());
            Log.i(SemanticsManager.TAG, "wanos getSemantic() " + semanticData.getSemantic());
            switch (semanticData.getSemantic()) {
                case 33554433:
                    SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda2
                        @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                        public final void onCompleted() {
                            this.f$0.m675xecfca73c();
                        }
                    });
                    break;
                case SemanticsType.CONTROL_PAUSE /* 33554434 */:
                case SemanticsType.CONTROL_CLOSE /* 33556480 */:
                    SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager.1.1
                        @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                        public void onCompleted() {
                            SemanticsManager.this.pause();
                        }

                        @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                        public String getReplayText() {
                            Log.i(SemanticsManager.TAG, "getReplayText: " + MediaCenterManager.getInstance().isPlaying());
                            boolean zIsPlaying = MediaCenterManager.getInstance().isPlaying();
                            boolean zIsPreparing = MediaCenterManager.getInstance().isPreparing();
                            Log.i(SemanticsManager.TAG, "getReplayText: playing =" + zIsPlaying + ",preparing=" + zIsPreparing);
                            return (zIsPlaying || zIsPreparing) ? super.getReplayText() : "当前没有正在播放的音乐";
                        }
                    });
                    break;
                case SemanticsType.CONTROL_NEXT /* 33554436 */:
                    SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda0
                        @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                        public final void onCompleted() {
                            this.f$0.m673xde323cfe();
                        }
                    });
                    break;
                case SemanticsType.CONTROL_PREVIOUS /* 33554440 */:
                    SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda1
                        @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                        public final void onCompleted() {
                            this.f$0.m674xe597721d();
                        }
                    });
                    break;
                case SemanticsType.CONTROL_COLLECT /* 33554448 */:
                    Log.i(SemanticsManager.TAG, "语音 收藏");
                    if (UserInfoUtil.isLogin()) {
                        if (SemanticsManager.this.mediaIsCllection()) {
                            SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda5
                                @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                                public final void onCompleted() {
                                    SemanticsManager.AnonymousClass1.lambda$handleVrChannelData$5();
                                }
                            }, "已经收藏了");
                        } else {
                            SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda6
                                @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                                public final void onCompleted() {
                                    this.f$0.m678xa917bb8();
                                }
                            });
                        }
                    } else {
                        SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda7
                            @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                            public final void onCompleted() {
                                SemanticsManager.AnonymousClass1.lambda$handleVrChannelData$7();
                            }
                        }, "请先登录");
                    }
                    break;
                case SemanticsType.CONTROL_CANCEL_COLLECT /* 33554464 */:
                    if (UserInfoUtil.isLogin()) {
                        if (!SemanticsManager.this.mediaIsCllection()) {
                            SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda8
                                @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                                public final void onCompleted() {
                                    SemanticsManager.AnonymousClass1.lambda$handleVrChannelData$8();
                                }
                            }, "已经取消收藏了");
                        } else {
                            SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda9
                                @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                                public final void onCompleted() {
                                    this.f$0.m679x20c11b15();
                                }
                            });
                        }
                    }
                    break;
                case SemanticsType.CONTROL_MODE /* 33555456 */:
                    int modeType = semanticData.getModeType();
                    final int iLoopModeCenterToPlayMap = CustomMusicClient.LoopModeCenterToPlayMap(modeType);
                    Log.i(SemanticsManager.TAG, "收到语音指令 " + modeType);
                    Log.i(SemanticsManager.TAG, "收到语音指令 类型" + iLoopModeCenterToPlayMap);
                    SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda4
                        @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                        public final void onCompleted() {
                            this.f$0.m677xfbc7117a(iLoopModeCenterToPlayMap);
                        }
                    });
                    break;
                case SemanticsType.CONTROL_START /* 33558528 */:
                    SemanticsManager.this.sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager$1$$ExternalSyntheticLambda3
                        @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
                        public final void onCompleted() {
                            this.f$0.m676xf461dc5b();
                        }
                    });
                    break;
                case SemanticsType.MUSIC_PLAY /* 67108865 */:
                    SemanticsManager.this.musicPlay(semanticData);
                    break;
            }
        }

        /* JADX INFO: renamed from: lambda$handleVrChannelData$0$com-wanos-mediacenter-SemanticsManager$1, reason: not valid java name */
        /* synthetic */ void m673xde323cfe() {
            SemanticsManager.this.next();
        }

        /* JADX INFO: renamed from: lambda$handleVrChannelData$1$com-wanos-mediacenter-SemanticsManager$1, reason: not valid java name */
        /* synthetic */ void m674xe597721d() {
            SemanticsManager.this.pre();
        }

        /* JADX INFO: renamed from: lambda$handleVrChannelData$2$com-wanos-mediacenter-SemanticsManager$1, reason: not valid java name */
        /* synthetic */ void m675xecfca73c() {
            SemanticsManager.this.play();
        }

        /* JADX INFO: renamed from: lambda$handleVrChannelData$3$com-wanos-mediacenter-SemanticsManager$1, reason: not valid java name */
        /* synthetic */ void m676xf461dc5b() {
            SemanticsManager.this.play();
        }

        /* JADX INFO: renamed from: lambda$handleVrChannelData$4$com-wanos-mediacenter-SemanticsManager$1, reason: not valid java name */
        /* synthetic */ void m677xfbc7117a(int i) {
            SemanticsManager.this.setPlayMode(i);
        }

        /* JADX INFO: renamed from: lambda$handleVrChannelData$6$com-wanos-mediacenter-SemanticsManager$1, reason: not valid java name */
        /* synthetic */ void m678xa917bb8() {
            SemanticsManager.this.addFavor();
        }

        /* JADX INFO: renamed from: lambda$handleVrChannelData$9$com-wanos-mediacenter-SemanticsManager$1, reason: not valid java name */
        /* synthetic */ void m679x20c11b15() {
            SemanticsManager.this.cancelFavor();
        }
    }

    void musicPlay(SemanticData semanticData) {
        String strQueryCurrentFocusClient;
        Log.i(TAG, "语音 play ");
        String originInfo = semanticData.getOriginInfo();
        Log.i(TAG, "musicPlay: originInfo = " + originInfo);
        Log.i(TAG, "musicPlay: MediaCenterManager.isVoiceIgnoreFocus = " + MediaCenterManager.isVoiceIgnoreFocus);
        if (MediaCenterManager.isVoiceIgnoreFocus || (originInfo != null && originInfo.contains("全景声"))) {
            Runnable runnable = new Runnable() { // from class: com.wanos.mediacenter.SemanticsManager.2
                @Override // java.lang.Runnable
                public void run() {
                    Log.i(SemanticsManager.TAG, "run: 使用Handler执行的一次性任务");
                    if (SemanticsManager.this.action.getCurrentMusicInfo() != null) {
                        SemanticsManager.this.action.play();
                    } else {
                        SemanticsManager.this.action.requestRankMusicListAndPlayFirst();
                    }
                }
            };
            this.runnable = runnable;
            this.mHandler.postDelayed(runnable, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        } else {
            Log.i(TAG, "musicPlay: 焦点不在wanos的情况，只响应 全景声");
            if (TextUtils.isEmpty(originInfo) || !originInfo.contains("全景声")) {
                try {
                    strQueryCurrentFocusClient = this.mMediaCenterAPI.queryCurrentFocusClient(this.token);
                } catch (MediaCenterException unused) {
                    Log.e(TAG, "获取媒体焦点失败");
                    strQueryCurrentFocusClient = null;
                }
                if (!TextUtils.isEmpty(strQueryCurrentFocusClient) && !TextUtils.equals(strQueryCurrentFocusClient, "com.wanos.media")) {
                    Log.i(TAG, "musicPlay: return >>");
                    return;
                }
            }
        }
        sendVrTts(new PlayCallback() { // from class: com.wanos.mediacenter.SemanticsManager.3
            @Override // com.wanos.mediacenter.SemanticsManager.PlayCallback
            public void onCompleted() {
                if (SemanticsManager.this.action.getCurrentMusicInfo() != null) {
                    SemanticsManager.this.action.play();
                } else {
                    SemanticsManager.this.action.requestRankMusicListAndPlayFirst();
                }
                if (SemanticsManager.this.runnable != null) {
                    SemanticsManager.this.mHandler.removeCallbacks(SemanticsManager.this.runnable);
                }
            }
        });
    }

    public boolean ttsWordContains(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    boolean mediaIsCllection() {
        MediaInfo currentMusicInfo;
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer == null || (currentMusicInfo = mediaCenterServer.getCurrentMusicInfo()) == null) {
            return false;
        }
        MediaPlayerEnum.MediaType mediaType = currentMusicInfo.getMediaType();
        if (mediaType == MediaPlayerEnum.MediaType.Music) {
            return currentMusicInfo.getMusicInfoBean().isCollection();
        }
        return mediaType == MediaPlayerEnum.MediaType.Audiobook && currentMusicInfo.getAudioBookInfoBean().getIsCollect() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelFavor() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer != null) {
            mediaCenterServer.cancelFavor();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFavor() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer != null) {
            mediaCenterServer.addFavor();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayMode(int i) {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer != null) {
            mediaCenterServer.setPlayMode(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pause() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer != null) {
            mediaCenterServer.pause();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void play() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer != null) {
            if (mediaCenterServer.getCurrentMusicInfo() != null) {
                this.action.play();
            } else {
                this.action.requestRankMusicListAndPlayFirst();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pre() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer != null) {
            mediaCenterServer.pre();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void next() {
        MediaCenterServer mediaCenterServer = this.action;
        if (mediaCenterServer != null) {
            mediaCenterServer.next();
        }
    }

    public final void sendAudioFocusVrTts(PlayCallback playCallback, String str) {
        this.playCallback = playCallback;
    }

    public final void sendVrTts(final PlayCallback playCallback) {
        if (MediaCenterManager.isUseNativeTTS) {
            this.playCallback = playCallback;
            TTSUtil tTSUtil = this.ttsUtil;
            if (tTSUtil != null) {
                tTSUtil.speak("好的");
            }
            Log.i(TAG, "sendVrTts: MediaCenterManager.isUseNativeTTS == true");
            return;
        }
        this.ttsOnceFlag = true;
        this.mHandler.postDelayed(new Runnable() { // from class: com.wanos.mediacenter.SemanticsManager.4
            @Override // java.lang.Runnable
            public void run() {
                if (SemanticsManager.this.ttsOnceFlag) {
                    SemanticsManager.this.ttsOnceFlag = false;
                    playCallback.onCompleted();
                }
            }
        }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        try {
            this.ttsOnceFlag = true;
            this.mMediaCenterAPI.sendVrTtsActionResult(this.token, "", playCallback.getReplayText(), new VrTtsResultListener() { // from class: com.wanos.mediacenter.SemanticsManager.5
                @Override // com.ecarx.eas.sdk.vr.channel.VrTtsResultListener, com.ecarx.eas.sdk.vr.channel.VrTtsResultHandling
                public void handleTtsResponse(int i) {
                    Log.i(SemanticsManager.TAG, "handleTtsResponse: i = " + i);
                    if (SemanticsManager.this.ttsOnceFlag) {
                        if (i == 4) {
                            Log.i(SemanticsManager.TAG, "语音 播放完成");
                            playCallback.onCompleted();
                            SemanticsManager.this.ttsOnceFlag = false;
                            Log.i(SemanticsManager.TAG, "handleTtsResponse: i == TTS_STATE_PLAY_END");
                            return;
                        }
                        if (i == 3) {
                            playCallback.onCompleted();
                            SemanticsManager.this.ttsOnceFlag = false;
                            Log.i(SemanticsManager.TAG, "handleTtsResponse: i == TTS_STATE_PLAY_INTERRUPTED");
                        } else if (i == 2) {
                            playCallback.onCompleted();
                            Log.i(SemanticsManager.TAG, "handleTtsResponse: i == TTS_STATE_PLAY_PAUSE");
                        } else {
                            Log.i(SemanticsManager.TAG, "handleTtsResponse: i = 其他情况");
                        }
                    }
                }
            });
        } catch (Throwable unused) {
            playCallback.onCompleted();
        }
    }

    public final void sendVrTts(final PlayCallback playCallback, String str) {
        long length;
        Log.i(TAG, "sendVrTts: =====111");
        if (MediaCenterManager.isUseNativeTTS) {
            this.playCallback = playCallback;
            TTSUtil tTSUtil = this.ttsUtil;
            if (tTSUtil != null) {
                tTSUtil.speak(str);
                return;
            }
            return;
        }
        if (str != null) {
            try {
                length = (((long) str.length()) * 333) + 500;
            } catch (MediaCenterException e) {
                throw new RuntimeException(e);
            }
        } else {
            length = ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS;
        }
        Log.i(TAG, "sendVrTts: timeDelay = " + length);
        this.ttsOnceFlagOther = true;
        this.mHandler.postDelayed(new Runnable() { // from class: com.wanos.mediacenter.SemanticsManager.6
            @Override // java.lang.Runnable
            public void run() {
                if (SemanticsManager.this.ttsOnceFlagOther) {
                    Log.i(SemanticsManager.TAG, "run: ttsOnceFlagOther=true");
                    SemanticsManager.this.ttsOnceFlagOther = false;
                    playCallback.onCompleted();
                }
            }
        }, length);
        this.mMediaCenterAPI.sendVrTtsActionResult(this.token, "", str, new VrTtsResultListener() { // from class: com.wanos.mediacenter.SemanticsManager.7
            @Override // com.ecarx.eas.sdk.vr.channel.VrTtsResultListener, com.ecarx.eas.sdk.vr.channel.VrTtsResultHandling
            public void handleTtsResponse(int i) {
                Log.i(SemanticsManager.TAG, "handleTtsResponse: i = " + i);
                if (SemanticsManager.this.ttsOnceFlagOther) {
                    if (i == 4) {
                        Log.i(SemanticsManager.TAG, "语音 播放完成");
                        playCallback.onCompleted();
                        SemanticsManager.this.ttsOnceFlagOther = false;
                    } else if (i == 3) {
                        playCallback.onCompleted();
                        SemanticsManager.this.ttsOnceFlagOther = false;
                    } else if (i == 2) {
                        playCallback.onCompleted();
                    }
                }
            }
        });
    }

    private void initTTS(Context context) {
        this.ttsUtil = new TTSUtil(context, new TTSUtil.TTSListener() { // from class: com.wanos.mediacenter.SemanticsManager.8
            @Override // com.wanos.mediacenter.utils.TTSUtil.TTSListener
            public void onSpeechError(String str) {
            }

            @Override // com.wanos.mediacenter.utils.TTSUtil.TTSListener
            public void onSpeechStart() {
            }

            @Override // com.wanos.mediacenter.utils.TTSUtil.TTSListener
            public void onInitSuccess() {
                Log.i(SemanticsManager.TAG, "init success...");
            }

            @Override // com.wanos.mediacenter.utils.TTSUtil.TTSListener
            public void onInitFailure() {
                Log.i(SemanticsManager.TAG, "onInitFailure...");
            }

            @Override // com.wanos.mediacenter.utils.TTSUtil.TTSListener
            public void onSpeechDone() {
                Log.i(SemanticsManager.TAG, "onSpeechDone...");
                if (SemanticsManager.this.playCallback != null) {
                    SemanticsManager.this.playCallback.onCompleted();
                }
            }
        });
    }
}
