package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Binder;
import com.ecarx.eas.sdk.mediacenter.control.IMediaControlClientAPI;
import com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI;
import com.ecarx.eas.sdk.mediacenter.exception.MediaCenterException;
import com.ecarx.eas.sdk.vr.channel.VrChannelDataListener;
import com.ecarx.eas.sdk.vr.channel.VrChannelInfo;
import com.ecarx.eas.sdk.vr.channel.VrTtsResultListener;
import com.ecarx.eas.sdk.vr.music.VrMusicCtrlAPI;
import com.ecarx.eas.sdk.vr.news.VrNewsCtrlAPI;
import com.ecarx.eas.sdk.vr.radio.VrLocalRadioCtrlAPI;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaCenterAPI {
    void abandonPlayFocus(Object obj);

    @Deprecated
    boolean asyncSendVrChannelResult(Object obj, VrChannelInfo vrChannelInfo, String str) throws MediaCenterException;

    boolean cancelSupportCollectTypes(Object obj, int[] iArr);

    boolean cancelSupportDownloadTypes(Object obj, int[] iArr);

    @Deprecated
    boolean cancelVrChannelCapability(Object obj, VrChannelInfo vrChannelInfo) throws MediaCenterException;

    boolean cancelVrSemanticsCapability(Object obj, VrChannelInfo vrChannelInfo) throws MediaCenterException;

    @Deprecated
    void connect(Object obj) throws IllegalArgumentException;

    void declareMediaCenterCapability(Object obj, int[] iArr);

    boolean declareSupportCollectTypes(Object obj, int[] iArr);

    boolean declareSupportDownloadTypes(Object obj, int[] iArr);

    @Deprecated
    boolean declareVrChannelCapability(Object obj, VrChannelInfo vrChannelInfo, VrChannelDataListener vrChannelDataListener) throws MediaCenterException;

    boolean declareVrSemanticsCapability(Object obj, VrChannelInfo vrChannelInfo, int[] iArr, VrChannelDataListener vrChannelDataListener) throws MediaCenterException;

    int getECarXAPIBaseVERSION_INT();

    IMediaControlClientAPI getMediaControlClientApi();

    IMediaControllerAPI getMediaControllerApi();

    MediaListInfo getRecoveryMediaList(Object obj) throws MediaCenterException;

    MusicPlaybackInfo getRecoveryMusicPlaybackInfo(Object obj) throws MediaCenterException;

    VrLocalRadioCtrlAPI getVrLocalRadioApi();

    VrMusicCtrlAPI getVrMusicApi();

    VrNewsCtrlAPI getVrNewsApi();

    void onMusicRecoveryComplete(Object obj) throws MediaCenterException;

    String queryCurrentFocusClient(Object obj) throws MediaCenterException;

    @Deprecated
    Object register(Binder binder);

    @Deprecated
    Object registerMusic(IMusicClient iMusicClient);

    Object registerMusic(MusicClient musicClient, RegisterRequest registerRequest);

    Object registerMusic(String str, MusicClient musicClient);

    Object registerMusic(String str, MusicClient musicClient, String str2);

    boolean registerMusicRecoveryIntent(Object obj, int i, Intent intent) throws MediaCenterException;

    Object registerMusicWithZoneId(int i, String str, MusicClient musicClient);

    @Deprecated
    Object registerNews(INewsClient iNewsClient);

    Object registerNews(String str, NewsClient newsClient);

    @Deprecated
    Object registerVideo(IVideoClient iVideoClient);

    Object registerVideo(String str, VideoClient videoClient);

    boolean requestPlay(Object obj);

    boolean sendVrTtsActionResult(Object obj, String str, String str2, VrTtsResultListener vrTtsResultListener) throws MediaCenterException;

    void setMusicRecoveryCallback(Object obj, IMusicRecoveryCallback iMusicRecoveryCallback) throws MediaCenterException;

    boolean unRegisterMusicRecoveryIntent(Object obj) throws MediaCenterException;

    boolean unregister(Object obj);

    void updateCollectMsg(Object obj, int i, int i2, String str, int i3, String str2) throws MediaCenterException;

    void updateCollectMsg(Object obj, int i, String str) throws MediaCenterException;

    void updateCurrentLyric(Object obj, String str);

    void updateCurrentPlaylist(Object obj, int i, List<MediaInfo> list);

    void updateCurrentProgress(Object obj, long j);

    boolean updateCurrentRecommendInfo(Object obj, RecommendInfo recommendInfo);

    void updateCurrentSourceType(Object obj, int i);

    void updateErrorMsg(Object obj, int i, String str);

    void updateErrorStateAndPendingIntent(Object obj, int i, int i2, String str, PendingIntent pendingIntent);

    boolean updateMediaContent(Object obj, List<ContentInfo> list) throws MediaCenterException;

    void updateMediaContentTypeList(Object obj, List<IMediaContentType> list);

    void updateMediaList(Object obj, MediaListInfo mediaListInfo);

    void updateMediaListWithPagination(Object obj, int i, int i2, int i3, MediaListInfo mediaListInfo);

    boolean updateMediaSourceTypeList(Object obj, int[] iArr);

    boolean updateMultiMediaList(Object obj, MediaListsInfo mediaListsInfo) throws MediaCenterException;

    @Deprecated
    boolean updateMusicPlaybackState(Object obj, IMusicPlaybackInfo iMusicPlaybackInfo);

    boolean updateMusicPlaybackState(Object obj, MusicPlaybackInfo musicPlaybackInfo);

    @Deprecated
    boolean updateNewsPlaybackState(Object obj, INewsPlaybackInfo iNewsPlaybackInfo);

    boolean updateNewsPlaybackState(Object obj, NewsPlaybackInfo newsPlaybackInfo);

    @Deprecated
    void updatePlaylist(Object obj, int i, List<IMediaInfo> list);

    @Deprecated
    boolean updateVideoPlaybackState(Object obj, IVideoPlaybackInfo iVideoPlaybackInfo);

    boolean updateVideoPlaybackState(Object obj, VideoPlaybackInfo videoPlaybackInfo);

    void updateZoneId(Object obj, int i);
}
