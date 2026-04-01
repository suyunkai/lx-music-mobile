package com.wanos.wanosplayermodule;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.alibaba.android.arouter.utils.Consts;
import com.baidubce.BceConfig;
import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;
import com.wanos.commonlibrary.manager.GlobalAudioFocusManager;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.wanosplayermodule.MediaPlayerService;
import cz.msebera.android.httpclient.HttpHost;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayerHelper implements GlobalAudioFocusManager.IPlayer {
    private static final long DEFAULT_MAX_CACHE_SIZE = 20971520;
    private String cacheDirPath;
    private CacheListener cacheListener;
    private MediaPlayerEnum.CallBackState callBackState;
    private String currentUrl;
    private int delaySecondTime;
    private String[] ext;
    private boolean isAudioFocusPause;
    private boolean isHolderCreate;
    private boolean isStopOrPause;
    private boolean isVideo;
    Context mContext;
    private int mDuration;
    private long maxCacheSize;
    private long mediaLoadingTime;
    private long mediaPlayTimeout;
    private OnHolderCreateListener onHolderCreateListener;
    private OnStatusCallbackListener onStatusCallbackListener;
    private HttpProxyCacheServer proxy;
    private Runnable refress_time_Thread;
    private Handler refress_time_handler;
    private MediaPlayerService.Holder uiHolder;

    /* JADX INFO: Access modifiers changed from: private */
    interface OnHolderCreateListener {
        void onHolderCreate();
    }

    public void setStopOrPause(boolean z) {
        this.isStopOrPause = z;
    }

    public MediaPlayer getMediaPlayer() {
        return this.uiHolder.player;
    }

    public boolean isPlaying() {
        if (this.uiHolder.player != null) {
            return this.uiHolder.player.isPlaying();
        }
        return false;
    }

    private File getAudioCacheDir(Context context) {
        return new File(context.getExternalFilesDir("music"), "audio-cache");
    }

    public File getVideoFile(Context context, boolean z) {
        String str;
        if (TextUtils.isEmpty(this.cacheDirPath)) {
            str = context.getCacheDir().getPath() + (z ? "/video" : "/audio");
        } else {
            str = this.cacheDirPath;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public MediaPlayerHelper setProgressInterval(int i) {
        this.delaySecondTime = i;
        return this;
    }

    public void playAsset(final Context context, final String str, boolean z) {
        if (!checkAvalable(str)) {
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.FORMATE_NOT_SURPORT, str);
            return;
        }
        if (z) {
            if (this.isHolderCreate) {
                m688lambda$playAsset$0$comwanoswanosplayermoduleMediaPlayerHelper(context, str);
                return;
            } else {
                setOnHolderCreateListener(new OnHolderCreateListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda0
                    @Override // com.wanos.wanosplayermodule.MediaPlayerHelper.OnHolderCreateListener
                    public final void onHolderCreate() {
                        this.f$0.m688lambda$playAsset$0$comwanoswanosplayermoduleMediaPlayerHelper(context, str);
                    }
                });
                return;
            }
        }
        m688lambda$playAsset$0$comwanoswanosplayermoduleMediaPlayerHelper(context, str);
    }

    public void setConnect(boolean z) {
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            httpProxyCacheServer.setConnect(z);
        }
    }

    public void playUrl(Context context, String str, boolean z, int i) {
        playUrl(context, str, z, i, 1);
    }

    public void playUrl(Context context, String str, boolean z, int i, int i2) {
        playUrl(context, str, z, i, null, i2);
    }

    public void setAiPlayUrl(Context context, String str) {
        this.isStopOrPause = false;
        this.isVideo = false;
        this.mContext = context;
        if (this.proxy == null) {
            this.proxy = new HttpProxyCacheServer.Builder(context).maxCacheFilesCount(5).maxCacheSize(this.maxCacheSize).cacheDirectory(getVideoFile(context, this.isVideo)).build();
        }
        registerCacheListener(str);
        this.proxy.setMediaInfo(this.isVideo ? 1 : 0, -1L);
        String proxyUrl = this.proxy.getProxyUrl(str, true);
        this.currentUrl = proxyUrl;
        beginPlayUrl(context, proxyUrl);
    }

    public void playUrl(final Context context, String str, boolean z, int i, SurfaceView surfaceView, int i2) {
        this.isStopOrPause = false;
        Log.i("playUrl", "playUrl: " + str);
        this.isVideo = z;
        if (!z && str.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            String[] strArrSplit = str.split("\\?");
            String str2 = strArrSplit.length > 1 ? strArrSplit[0] : str;
            Log.d("playUrl", "playUrl: " + str2 + ", path=" + str);
            this.mContext = context;
            if (this.proxy == null) {
                this.proxy = new HttpProxyCacheServer.Builder(context).maxCacheFilesCount(5).maxCacheSize(this.maxCacheSize).cacheDirectory(getVideoFile(context, z)).build();
            }
            registerCacheListener(str2);
            this.proxy.setMediaInfo(10, i, i2);
            this.currentUrl = this.proxy.getProxyUrl(str2, true);
        } else {
            this.currentUrl = str;
        }
        if (!z) {
            beginPlayUrl(context, this.currentUrl);
            return;
        }
        if (surfaceView == null) {
            Log.i("playUrl", "playUrl: surfaceView is null");
            if (this.isHolderCreate) {
                Log.i("playUrl", "playUrl: holder is create");
                beginPlayUrl(context, this.currentUrl);
                return;
            } else {
                Log.i("playUrl", "playUrl: holder is not create");
                setOnHolderCreateListener(new OnHolderCreateListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda1
                    @Override // com.wanos.wanosplayermodule.MediaPlayerHelper.OnHolderCreateListener
                    public final void onHolderCreate() {
                        this.f$0.m690lambda$playUrl$1$comwanoswanosplayermoduleMediaPlayerHelper(context);
                    }
                });
                return;
            }
        }
        Log.i("playUrl", "playUrl: surfaceView is not null");
        setSurfaceView(surfaceView);
    }

    /* JADX INFO: renamed from: lambda$playUrl$1$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ void m690lambda$playUrl$1$comwanoswanosplayermoduleMediaPlayerHelper(Context context) {
        beginPlayUrl(context, this.currentUrl);
    }

    public void playPath(Context context, String str, boolean z) {
        this.isStopOrPause = false;
        playPath(context, str, z, null);
    }

    public void playPath(final Context context, String str, boolean z, SurfaceView surfaceView) {
        Log.i("playUrl", "playUrl: " + str);
        this.isVideo = z;
        this.currentUrl = str;
        if (!z) {
            beginPlayUrl(context, str);
            return;
        }
        if (surfaceView == null) {
            Log.i("playUrl", "playUrl: surfaceView is null");
            if (this.isHolderCreate) {
                Log.i("playUrl", "playUrl: holder is create");
                beginPlayUrl(context, this.currentUrl);
                return;
            } else {
                Log.i("playUrl", "playUrl: holder is not create");
                setOnHolderCreateListener(new OnHolderCreateListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda2
                    @Override // com.wanos.wanosplayermodule.MediaPlayerHelper.OnHolderCreateListener
                    public final void onHolderCreate() {
                        this.f$0.m689lambda$playPath$2$comwanoswanosplayermoduleMediaPlayerHelper(context);
                    }
                });
                return;
            }
        }
        Log.i("playUrl", "playUrl: surfaceView is not null");
        setSurfaceView(surfaceView);
    }

    /* JADX INFO: renamed from: lambda$playPath$2$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ void m689lambda$playPath$2$comwanoswanosplayermoduleMediaPlayerHelper(Context context) {
        beginPlayUrl(context, this.currentUrl);
    }

    public void registerCacheListener(String str) {
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            CacheListener cacheListener = this.cacheListener;
            if (cacheListener != null) {
                httpProxyCacheServer.unregisterCacheListener(cacheListener);
            }
            this.proxy.registerCacheListener(this.cacheListener, str);
        }
    }

    @Override // com.wanos.commonlibrary.manager.GlobalAudioFocusManager.IPlayer
    public void onAudioFocusLost() {
        if (isPlaying()) {
            this.isAudioFocusPause = true;
            pause();
        }
    }

    @Override // com.wanos.commonlibrary.manager.GlobalAudioFocusManager.IPlayer
    public void onAudioFocusLostTransient() {
        if (isPlaying()) {
            this.isAudioFocusPause = true;
            pause();
        }
    }

    @Override // com.wanos.commonlibrary.manager.GlobalAudioFocusManager.IPlayer
    public void onAudioFocusGained() {
        if (this.isAudioFocusPause) {
            start();
        }
    }

    public void stop() {
        this.isStopOrPause = true;
        if (this.uiHolder.player != null) {
            this.uiHolder.player.stop();
        }
        GlobalAudioFocusManager.getInstance().abandonFocus(this);
    }

    public void pause() {
        this.isStopOrPause = true;
        if (this.uiHolder.player != null) {
            this.uiHolder.player.pause();
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.PAUSE, new Object[0]);
        }
    }

    public void start() {
        this.isStopOrPause = false;
        this.isAudioFocusPause = false;
        if (this.uiHolder.player != null) {
            GlobalAudioFocusManager.getInstance().requestFocus(this);
            this.uiHolder.player.start();
            if (isPlaying()) {
                onStatusCallbackNext(MediaPlayerEnum.CallBackState.STARTED, new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareAsync() {
        this.uiHolder.player.prepareAsync();
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.PREPARING, new Object[0]);
    }

    public int getCurrentPosition() {
        if (getMediaPlayer() != null) {
            return this.uiHolder.player.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (getMediaPlayer() != null) {
            return getMediaPlayer().getDuration();
        }
        return -1;
    }

    public void seekTo(long j) {
        seekTo(j, 0);
    }

    public void seekTo(long j, int i) {
        if (this.uiHolder.player != null) {
            int duration = getDuration();
            this.mDuration = duration;
            if (duration <= -1 || j < 0 || j > duration) {
                return;
            }
            this.uiHolder.player.seekTo(j, i);
        }
    }

    public void release() {
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            CacheListener cacheListener = this.cacheListener;
            if (cacheListener != null) {
                httpProxyCacheServer.unregisterCacheListener(cacheListener);
            }
            this.proxy.shutdown();
        }
        try {
            if (this.uiHolder.player != null) {
                if (this.uiHolder.player.isPlaying()) {
                    this.uiHolder.player.stop();
                }
                this.uiHolder.player.reset();
                this.uiHolder.player.release();
                this.uiHolder.player = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.refress_time_handler.removeCallbacks(this.refress_time_Thread);
    }

    public MediaPlayerHelper setSurfaceView(SurfaceView surfaceView) {
        Log.i("setSurfaceView", "setSurfaceView:1111 ");
        if (surfaceView == null) {
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.SURFACEVIEW_NULL, this.uiHolder.player);
        } else {
            this.uiHolder.surfaceView = surfaceView;
            MediaPlayerService.Holder holder = this.uiHolder;
            holder.surfaceHolder = holder.surfaceView.getHolder();
            this.uiHolder.surfaceHolder.addCallback(new AnonymousClass2());
        }
        return this;
    }

    /* JADX INFO: renamed from: com.wanos.wanosplayermodule.MediaPlayerHelper$2, reason: invalid class name */
    class AnonymousClass2 implements SurfaceHolder.Callback {
        AnonymousClass2() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(final SurfaceHolder surfaceHolder) {
            MediaPlayerHelper.this.isHolderCreate = true;
            if (MediaPlayerHelper.this.uiHolder.player != null && surfaceHolder != null) {
                if (MediaPlayerHelper.this.uiHolder.surfaceView != null) {
                    MediaPlayerHelper.this.uiHolder.surfaceView.post(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$2$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m691xfce58f78(surfaceHolder);
                        }
                    });
                }
                try {
                    Log.i("setSurfaceView", "setSurfaceView:222: " + MediaPlayerHelper.this.currentUrl);
                    MediaPlayerHelper.this.uiHolder.player.reset();
                    MediaPlayerHelper.this.uiHolder.player.setDataSource(MediaPlayerHelper.this.currentUrl);
                    MediaPlayerHelper.this.uiHolder.player.setSurface(surfaceHolder.getSurface());
                    MediaPlayerHelper.this.prepareAsync();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            MediaPlayerHelper.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.SURFACEVIEW_CREATE, surfaceHolder);
            MediaPlayerHelper.this.onHolderCreateNext();
        }

        /* JADX INFO: renamed from: lambda$surfaceCreated$0$com-wanos-wanosplayermodule-MediaPlayerHelper$2, reason: not valid java name */
        /* synthetic */ void m691xfce58f78(SurfaceHolder surfaceHolder) {
            surfaceHolder.setFixedSize(MediaPlayerHelper.this.uiHolder.surfaceView.getWidth(), MediaPlayerHelper.this.uiHolder.surfaceView.getHeight());
            MediaPlayerHelper.this.uiHolder.player.setDisplay(surfaceHolder);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            MediaPlayerHelper.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.SURFACEVIEW_CHANGE, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            MediaPlayerHelper.this.isHolderCreate = false;
            MediaPlayerHelper.this.uiHolder.player.pause();
            MediaPlayerHelper.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.SURFACEVIEW_DESTROY, surfaceHolder);
        }
    }

    public MediaPlayerHelper() {
        this(null, DEFAULT_MAX_CACHE_SIZE);
    }

    public MediaPlayerHelper(String str, long j) {
        this.ext = new String[]{".3gp", ".3GP", ".mp4", ".MP4", ".mp3", ".ogg", ".OGG", ".MP3", ".wav", ".WAV"};
        this.delaySecondTime = 1000;
        this.isHolderCreate = false;
        this.proxy = null;
        this.mDuration = 0;
        this.mContext = null;
        this.mediaPlayTimeout = -1L;
        this.mediaLoadingTime = 0L;
        this.isStopOrPause = true;
        this.maxCacheSize = DEFAULT_MAX_CACHE_SIZE;
        this.cacheListener = new CacheListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper.1
            @Override // com.danikula.videocache.CacheListener
            public void onCacheAvailable(File file, String str2, int i) {
                Log.d("glg", "onCacheAvailable url :" + str2 + ",percentsAvailable : " + i);
            }
        };
        this.isAudioFocusPause = false;
        this.refress_time_handler = new Handler();
        this.refress_time_Thread = new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper.3
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerHelper.this.refress_time_handler.removeCallbacks(MediaPlayerHelper.this.refress_time_Thread);
                try {
                    if (MediaPlayerHelper.this.uiHolder.player != null && MediaPlayerHelper.this.uiHolder.player.isPlaying()) {
                        int duration = MediaPlayerHelper.this.uiHolder.player.getDuration();
                        if (duration > 0) {
                            MediaPlayerHelper.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.PROGRESS, Integer.valueOf((int) (((MediaPlayerHelper.this.uiHolder.player.getCurrentPosition() * 1.0f) / duration) * 100.0f)));
                        } else {
                            MediaPlayerHelper.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.PROGRESS, 0);
                        }
                    }
                } catch (IllegalStateException e) {
                    MediaPlayerHelper.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.EXCEPTION, e.toString());
                }
                MediaPlayerHelper.this.refress_time_handler.postDelayed(MediaPlayerHelper.this.refress_time_Thread, MediaPlayerHelper.this.delaySecondTime);
            }
        };
        this.cacheDirPath = str;
        this.maxCacheSize = j;
        MediaPlayerService.Holder holder = new MediaPlayerService.Holder();
        this.uiHolder = holder;
        holder.player = new MediaPlayer();
        initPlayerListener();
    }

    private void initPlayerListener() {
        this.uiHolder.player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda4
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                this.f$0.m681xdf4d5817(mediaPlayer);
            }
        });
        this.uiHolder.player.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda5
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                return this.f$0.m682x54c77e58(mediaPlayer, i, i2);
            }
        });
        this.uiHolder.player.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda6
            @Override // android.media.MediaPlayer.OnInfoListener
            public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                return this.f$0.m683xca41a499(mediaPlayer, i, i2);
            }
        });
        this.uiHolder.player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda7
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                this.f$0.m685xb535f11b(mediaPlayer);
            }
        });
        this.uiHolder.player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda8
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public final void onSeekComplete(MediaPlayer mediaPlayer) {
                this.f$0.m686x2ab0175c(mediaPlayer);
            }
        });
        this.uiHolder.player.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda9
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                this.f$0.m687xa02a3d9d(mediaPlayer, i, i2);
            }
        });
        this.uiHolder.player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda10
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                this.f$0.m680xf45f2a47(mediaPlayer, i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$3$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ void m681xdf4d5817(MediaPlayer mediaPlayer) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.PROGRESS, 100);
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.COMPLETE, mediaPlayer);
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$4$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ boolean m682x54c77e58(MediaPlayer mediaPlayer, int i, int i2) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.ERROR, "what:" + i + " extra:" + i2);
        return true;
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$5$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ boolean m683xca41a499(MediaPlayer mediaPlayer, int i, int i2) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.INFO, mediaPlayer, Integer.valueOf(i), Integer.valueOf(i2));
        return false;
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$7$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ void m685xb535f11b(MediaPlayer mediaPlayer) {
        Log.i("initPlayerListener", "initPlayerListener: 准备完毕，开始播放");
        if (this.isStopOrPause) {
            Log.i("initPlayerListener", "initPlayerListener: 暂停播放");
            return;
        }
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.PREPARE, new Object[0]);
        Log.i("initPlayerListener", "initPlayerListener: 准备完毕，开始播放22");
        try {
            if (this.uiHolder.surfaceView != null) {
                this.uiHolder.surfaceView.post(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerHelper$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m684x3fbbcada();
                    }
                });
            }
            start();
            this.refress_time_handler.postDelayed(this.refress_time_Thread, this.delaySecondTime);
        } catch (Exception e) {
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.EXCEPTION, e.toString());
        }
        if (this.uiHolder.surfaceHolder != null) {
            String str = ("holder - height：" + this.uiHolder.surfaceHolder.getSurfaceFrame().height()) + " width：" + this.uiHolder.surfaceHolder.getSurfaceFrame().width();
        }
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$6$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ void m684x3fbbcada() {
        this.uiHolder.surfaceHolder.setFixedSize(this.uiHolder.surfaceView.getWidth(), this.uiHolder.surfaceView.getHeight());
        this.uiHolder.player.setDisplay(this.uiHolder.surfaceHolder);
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$8$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ void m686x2ab0175c(MediaPlayer mediaPlayer) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.SEEK_COMPLETE, mediaPlayer);
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$9$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ void m687xa02a3d9d(MediaPlayer mediaPlayer, int i, int i2) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.VIDEO_SIZE_CHANGE, Integer.valueOf(i), Integer.valueOf(i2));
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$10$com-wanos-wanosplayermodule-MediaPlayerHelper, reason: not valid java name */
    /* synthetic */ void m680xf45f2a47(MediaPlayer mediaPlayer, int i) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.BUFFER_UPDATE, mediaPlayer, Integer.valueOf(i));
    }

    private void beginPlayUrl(Context context, String str) {
        Log.i("beginPlayUrl", "beginPlayUrl: " + str);
        try {
            this.uiHolder.player.setDisplay(null);
            this.uiHolder.player.stop();
            this.uiHolder.player.reset();
            this.uiHolder.player.setDataSource(str);
            prepareAsync();
        } catch (Exception e) {
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.ERROR, e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: beginPlayAsset, reason: merged with bridge method [inline-methods] */
    public void m688lambda$playAsset$0$comwanoswanosplayermoduleMediaPlayerHelper(Context context, String str) {
        try {
            this.uiHolder.assetDescriptor = context.getAssets().openFd(str);
            this.uiHolder.player.setDisplay(null);
            this.uiHolder.player.stop();
            this.uiHolder.player.reset();
            this.uiHolder.player.setDataSource(str);
            prepareAsync();
        } catch (Exception e) {
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.ERROR, e.toString());
        }
    }

    private boolean checkAvalable(String str) {
        boolean z = false;
        for (String str2 : this.ext) {
            if (str.endsWith(str2)) {
                z = true;
            }
        }
        if (z) {
            return true;
        }
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.FORMATE_NOT_SURPORT, this.uiHolder.player);
        return false;
    }

    public MediaPlayerHelper setOnStatusCallbackListener(OnStatusCallbackListener onStatusCallbackListener) {
        this.onStatusCallbackListener = onStatusCallbackListener;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
        this.callBackState = callBackState;
        OnStatusCallbackListener onStatusCallbackListener = this.onStatusCallbackListener;
        if (onStatusCallbackListener != null) {
            onStatusCallbackListener.onStatusonStatusCallbackNext(callBackState, objArr);
        }
    }

    private void setOnHolderCreateListener(OnHolderCreateListener onHolderCreateListener) {
        this.onHolderCreateListener = onHolderCreateListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHolderCreateNext() {
        OnHolderCreateListener onHolderCreateListener = this.onHolderCreateListener;
        if (onHolderCreateListener != null) {
            onHolderCreateListener.onHolderCreate();
        }
    }

    public class CustomFileNameGenerator implements FileNameGenerator {
        public CustomFileNameGenerator() {
        }

        @Override // com.danikula.videocache.file.FileNameGenerator
        public String generate(String str) {
            String str2 = str.split("\\?")[0];
            try {
                String strMd5 = MD5Util.md5(str2);
                String str3 = strMd5 + Consts.DOT + str2.split(BceConfig.BOS_DELIMITER)[r3.length - 1].split("\\.")[r3.length - 1];
                Log.i("zzzzzz", "generate urlb=" + str3);
                return str3;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class MD5Util {
        public static String md5(String str) throws Exception {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArrDigest) {
                sb.append(String.format("%02x", Integer.valueOf(b2 & 255)));
            }
            return sb.toString();
        }
    }
}
