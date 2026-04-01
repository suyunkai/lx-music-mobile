package com.wanos.mediacenter;

import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo;
import com.wanos.commonlibrary.bean.MediaInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideOptions;
import com.wanos.groove.utils.GrooveSdkAppGlobal;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes3.dex */
public class WanosMusicPlaybackInfo extends MusicPlaybackInfo {
    public static final String TAG = "wanos:[MusicPlayInfo]";
    private static volatile WanosMusicPlaybackInfo mInstance;
    public boolean isPlaying;
    boolean mIsSupporFavor = true;
    public int mLoopMode;
    public String mLrc;
    public MediaInfoBean mMediaInfoBean;
    public int mSourceType;
    Uri mUri;
    public boolean misCollected;

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getAppIcon() {
        return "https://btest-gemini.mzres.com/contentsource/906af134-cc07-41c3-b8fa-77b02c8a9250.jpg";
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getAppName() {
        return "全景声";
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getPackageName() {
        return "com.wanos.media";
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public int getPlayingMediaListType() {
        return 6;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public int getRadioMode() {
        return 0;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public int getSourceType() {
        return 6;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public boolean isDownloaded() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public boolean isSupportCollect() {
        return true;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public boolean isSupportDownload() {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public boolean isSupportLoopModeSwitch() {
        return true;
    }

    public static WanosMusicPlaybackInfo getInstance() {
        if (mInstance == null) {
            synchronized (WanosMusicPlaybackInfo.class) {
                if (mInstance == null) {
                    mInstance = new WanosMusicPlaybackInfo();
                }
            }
        }
        return mInstance;
    }

    public final int getMSourceType() {
        return this.mSourceType;
    }

    public final void setMSourceType(int i) {
        this.mSourceType = i;
    }

    public final boolean isPlaying() {
        Log.i(TAG, "获取播放状态 " + this.isPlaying);
        return this.isPlaying;
    }

    public final void setPlaying(boolean z) {
        this.isPlaying = z;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getUuid() {
        MediaInfoBean mediaInfoBean = this.mMediaInfoBean;
        if (mediaInfoBean == null) {
            return CommonUtils.getUUID(getApplication());
        }
        return mediaInfoBean.getUuid();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getTitle() {
        MediaInfoBean mediaInfoBean = this.mMediaInfoBean;
        if (mediaInfoBean == null) {
            Log.e(TAG, "mMediaInfoBean is 空");
            return "";
        }
        return mediaInfoBean.getTitle();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getArtist() {
        MediaInfoBean mediaInfoBean = this.mMediaInfoBean;
        return mediaInfoBean == null ? "" : mediaInfoBean.getArtist();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getAlbum() {
        MediaInfoBean mediaInfoBean = this.mMediaInfoBean;
        return mediaInfoBean == null ? "" : mediaInfoBean.getAlbum();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public long getDuration() {
        MediaInfoBean mediaInfoBean = this.mMediaInfoBean;
        if (mediaInfoBean == null) {
            return 0L;
        }
        return mediaInfoBean.getDuration() * 1000;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public Uri getArtwork() {
        MediaInfoBean mediaInfoBean = this.mMediaInfoBean;
        if (mediaInfoBean == null) {
            return Uri.parse("");
        }
        return Uri.parse(GlideOptions.formatImagePaths(mediaInfoBean.getArtwork().toString(), 140, 140, true));
    }

    public void setArtwork(Uri uri) {
        this.mUri = uri;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public int getPlaybackStatus() {
        return this.isPlaying ? 1 : 0;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public PendingIntent getPlayerIntent() {
        ComponentName componentName = new ComponentName("com.wanos.media", "com.wanos.media.ui.actvity.MainActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(268435456);
        intent.putExtra("android.permission-group.LOCATION", "next location");
        if (Build.VERSION.SDK_INT >= 31) {
            return PendingIntent.getActivity(getApplication(), 9999, intent, 67108864);
        }
        return PendingIntent.getActivity(getApplication(), 9999, intent, 134217728);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public PendingIntent getLaunchIntent() {
        ComponentName componentName = new ComponentName("com.wanos.media", "com.wanos.media.ui.actvity.MainActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(268435456);
        intent.putExtra("android.permission-group.LOCATION", "your location");
        if (Build.VERSION.SDK_INT >= 31) {
            return PendingIntent.getActivity(getApplication(), 999, intent, 67108864);
        }
        return PendingIntent.getActivity(getApplication(), 999, intent, 134217728);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getPlayingMediaListId() {
        MediaInfoBean mediaInfoBean = this.mMediaInfoBean;
        if (mediaInfoBean == null) {
            return getInstance().getUuid();
        }
        mediaInfoBean.setUuid(getInstance().getUuid());
        return mediaInfoBean != null ? mediaInfoBean.getUuid() : "";
    }

    public static Application getApplication() {
        Application application;
        try {
            Method declaredMethod = Class.forName(GrooveSdkAppGlobal.CLASS_FOR_NAME).getDeclaredMethod(GrooveSdkAppGlobal.CURRENT_APPLICATION, new Class[0]);
            declaredMethod.setAccessible(true);
            application = (Application) declaredMethod.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            application = null;
        }
        if (application != null) {
            return application;
        }
        try {
            Method declaredMethod2 = Class.forName(GrooveSdkAppGlobal.CLASS_FOR_NAME).getDeclaredMethod(GrooveSdkAppGlobal.GET_INITIAL_APPLICATION, new Class[0]);
            declaredMethod2.setAccessible(true);
            return (Application) declaredMethod2.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused2) {
            return application;
        }
    }

    public void setMediaInfoBean(MediaInfoBean mediaInfoBean) {
        this.mMediaInfoBean = mediaInfoBean;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getLyricContent() {
        try {
            String strEncode = URLEncoder.encode("[00:00.00]正在加载歌词...", "UTF-8");
            try {
                MediaInfoBean mediaInfoBean = this.mMediaInfoBean;
                if (mediaInfoBean == null) {
                    return URLEncoder.encode("[00:00.00]正在加载歌词...", "UTF-8");
                }
                MediaPlayerEnum.MediaType mediaType = mediaInfoBean.getMediaType();
                if (mediaType != MediaPlayerEnum.MediaType.Music) {
                    return mediaType == MediaPlayerEnum.MediaType.Audiobook ? URLEncoder.encode("[00:00.00]暂无歌词", "UTF-8") : strEncode;
                }
                if (this.mLrc == null) {
                    this.mLrc = "[00:00.00]正在加载歌词...";
                }
                return URLEncoder.encode(this.mLrc, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public String getCurrentLyricSentence() {
        Log.i(TAG, "getCurrentLyricSentence");
        try {
            return URLEncoder.encode("[00:00.00]正在加载歌词...", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public boolean isCollected() {
        if (this.mIsSupporFavor) {
            return this.misCollected;
        }
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
    public int getLoopMode() {
        return this.mLoopMode;
    }

    public final void setLoopMode(int i) {
        this.mLoopMode = i;
    }

    public final void setCollected(boolean z) {
        this.misCollected = z;
    }

    public final void setLrc(String str) {
        this.mLrc = str;
    }

    public void setSupporFavor(boolean z) {
        this.mIsSupporFavor = z;
    }

    public static int LoopModeCenterToPlayMap(int i) {
        if (i == 0) {
            return MediaPlayerEnum.PlayMode.orderplay.ordinal();
        }
        if (i == 1) {
            return MediaPlayerEnum.PlayMode.singleloopplay.ordinal();
        }
        if (i != 2) {
            return -1;
        }
        return MediaPlayerEnum.PlayMode.randomplay.ordinal();
    }
}
