package com.ecarx.eas.sdk.mediacenter.control;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.mediacenter.control.bean.Media;
import com.ecarx.eas.sdk.mediacenter.control.bean.MusicPlaybackInfo;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import ecarx.xsf.mediacenter.control.IMediaControllerApiSvc;
import ecarx.xsf.mediacenter.control.IMediaControllerToken;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements IMediaControllerAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IMediaControllerApiSvc f139a;

    /* synthetic */ c(byte b2) {
        this();
    }

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final c f140a = new c(0);
    }

    public static c a() {
        return a.f140a;
    }

    private c() {
    }

    public final c a(IMediaControllerApiSvc iMediaControllerApiSvc) {
        this.f139a = iMediaControllerApiSvc;
        return this;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final Object register(String str, MediaController mediaController) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return null;
        }
        try {
            return iMediaControllerApiSvc.register(str, new d(mediaController));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final boolean unregister(Object obj) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControllerApiSvc.unregister((IMediaControllerToken) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final boolean requestControl(Object obj) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControllerApiSvc.requestControl((IMediaControllerToken) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final List<IMediaContentType> getMediaContentTypeList(Object obj) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return null;
        }
        try {
            return iMediaControllerApiSvc.getMediaContentTypeList((IMediaControllerToken) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final boolean playCtrlPlayByMediaID(Object obj, int i, String str) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControllerApiSvc.play((IMediaControllerToken) obj, i, str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final boolean playCtrlPlay(Object obj) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControllerApiSvc.resume((IMediaControllerToken) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final boolean playCtlPlay(Object obj, int i, String str) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControllerApiSvc.playCtlPlay((IMediaControllerToken) obj, i, str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final boolean playCtrlPlayByContent(Object obj, int i, String str) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControllerApiSvc.playCtrlPlayByContent((IMediaControllerToken) obj, i, str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final boolean playCtrlPause(Object obj, int i) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControllerApiSvc.playCtrlPause((IMediaControllerToken) obj, i);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final boolean playCtrlPause(Object obj) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControllerApiSvc.pause((IMediaControllerToken) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final int getSourceType(Object obj) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return -1;
        }
        try {
            return iMediaControllerApiSvc.getSourceType((IMediaControllerToken) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final List<Media> getMediaList(Object obj) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return null;
        }
        try {
            return a(iMediaControllerApiSvc.getMediaList((IMediaControllerToken) obj));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI
    public final MusicPlaybackInfo getMusicPlaybackInfo(Object obj) {
        IMediaControllerApiSvc iMediaControllerApiSvc = this.f139a;
        if (iMediaControllerApiSvc == null) {
            Log.e("ControllerAPIImpl", "Please setApiSvc first");
            return null;
        }
        try {
            return a(iMediaControllerApiSvc.getMusicPlaybackInfo((IMediaControllerToken) obj));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static List<Media> a(List<IMedia> list) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (IMedia iMedia : list) {
            Media media = new Media();
            media.setAlbum(iMedia.getAlbum());
            media.setAlbumIndex(iMedia.getAlbumIndex());
            media.setArtist(iMedia.getArtist());
            media.setArtWork(iMedia.getArtwork());
            media.setAuthor(iMedia.getAuthor());
            media.setCategoryStr(iMedia.getCategoryStr());
            media.setComposer(iMedia.getComposer());
            media.setDescription(iMedia.getDescription());
            media.setDuration(iMedia.getDuration());
            media.setLyric(iMedia.getLyric());
            media.setLyricContent(iMedia.getLyricContent());
            media.setMediaCp(iMedia.getMediaCp());
            media.setMediaId(iMedia.getMediaId());
            media.setMediaPath(iMedia.getMediaPath());
            media.setPositionInQueue(iMedia.getPlayingItemPositionInQueue());
            media.setRadioFrequency(iMedia.getRadioFrequency());
            media.setRadioStationName(iMedia.getRadioStationName());
            media.setRating(iMedia.getRating());
            media.setSourceType(iMedia.getSourceType());
            media.setSubCategoryStr(iMedia.getCategoryStr());
            media.setSubtitle(iMedia.getSubtitle());
            media.setTargetType(iMedia.getTargetType());
            media.setTitle(iMedia.getTitle());
            media.setUuid(iMedia.getUuid());
            media.setYear(iMedia.getYear());
            try {
                media.setVip(iMedia.getVip());
                media.setPlayingMediaListId(iMedia.getPlayingMediaListId());
                media.setPlayingMediaListType(iMedia.getPlayingMediaListType());
                media.setSupportCollect(iMedia.getSupportCollect());
                media.setCollected(iMedia.getCollected());
            } catch (Throwable th) {
                th.printStackTrace();
            }
            arrayList.add(media);
        }
        return arrayList;
    }

    static MusicPlaybackInfo a(IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException {
        if (iMusicPlaybackInfo == null) {
            return null;
        }
        MusicPlaybackInfo musicPlaybackInfo = new MusicPlaybackInfo();
        musicPlaybackInfo.setAlbum(iMusicPlaybackInfo.getAlbum());
        musicPlaybackInfo.setAppIcon(iMusicPlaybackInfo.getAppIcon());
        musicPlaybackInfo.setAppName(iMusicPlaybackInfo.getAppName());
        musicPlaybackInfo.setArtist(iMusicPlaybackInfo.getArtist());
        musicPlaybackInfo.setArtwork(iMusicPlaybackInfo.getArtwork());
        musicPlaybackInfo.setCollected(iMusicPlaybackInfo.isCollected());
        musicPlaybackInfo.setCurrentLyricSentence(iMusicPlaybackInfo.getCurrentLyricSentence());
        musicPlaybackInfo.setDownloaded(iMusicPlaybackInfo.isDownloaded());
        musicPlaybackInfo.setDuration(iMusicPlaybackInfo.getDuration());
        musicPlaybackInfo.setLaunchIntent(iMusicPlaybackInfo.getLaunchIntent());
        musicPlaybackInfo.setLoopMode(iMusicPlaybackInfo.getLoopMode());
        musicPlaybackInfo.setLyric(iMusicPlaybackInfo.getLyric());
        musicPlaybackInfo.setLyricContent(iMusicPlaybackInfo.getLyricContent());
        musicPlaybackInfo.setMediaPath(iMusicPlaybackInfo.getMediaPath());
        musicPlaybackInfo.setNextArtwork(iMusicPlaybackInfo.getNextArtwork());
        musicPlaybackInfo.setPlaybackStatus(iMusicPlaybackInfo.getPlaybackStatus());
        musicPlaybackInfo.setPlayingItemPositionInQueue(iMusicPlaybackInfo.getPlayingItemPositionInQueue());
        musicPlaybackInfo.setPreviousArtwork(iMusicPlaybackInfo.getPreviousArtwork());
        musicPlaybackInfo.setRadioFrequency(iMusicPlaybackInfo.getRadioFrequency());
        musicPlaybackInfo.setRadioMode(iMusicPlaybackInfo.getRadioMode());
        musicPlaybackInfo.setRadioStationName(iMusicPlaybackInfo.getRadioStationName());
        musicPlaybackInfo.setSourceType(iMusicPlaybackInfo.getSourceType());
        musicPlaybackInfo.setSupportCollect(iMusicPlaybackInfo.isSupportCollect());
        musicPlaybackInfo.setSupportDownload(iMusicPlaybackInfo.isSupportDownload());
        musicPlaybackInfo.setTitle(iMusicPlaybackInfo.getTitle());
        musicPlaybackInfo.setUuid(iMusicPlaybackInfo.getUuid());
        musicPlaybackInfo.setPlayingMediaListId(iMusicPlaybackInfo.getPlayingMediaListId());
        musicPlaybackInfo.setPlayingMediaListType(iMusicPlaybackInfo.getPlayingMediaListType());
        musicPlaybackInfo.setVip(iMusicPlaybackInfo.getVip());
        musicPlaybackInfo.setPlayerIntent(iMusicPlaybackInfo.getPlayerIntent());
        return musicPlaybackInfo;
    }
}
