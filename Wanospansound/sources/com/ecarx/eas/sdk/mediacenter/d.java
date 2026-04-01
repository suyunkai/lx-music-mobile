package com.ecarx.eas.sdk.mediacenter;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.wanos.media.viewmodel.CollectionDialogViewModel;
import ecarx.xsf.mediacenter.IMedia;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f142a = "MediaBeanHelper";

    public static List<IMedia> a(List<IMediaInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (IMediaInfo iMediaInfo : list) {
                IMedia iMedia = new IMedia();
                iMedia.setTitle(iMediaInfo.getTitle());
                iMedia.setArtist(iMediaInfo.getArtist());
                iMedia.setAlbum(iMediaInfo.getAlbum());
                iMedia.setDuration(iMediaInfo.getDuration());
                iMedia.setPositionInQueue(iMediaInfo.getPlayingItemPositionInQueue());
                iMedia.setSourceType(iMediaInfo.getSourceType());
                iMedia.setMediaPath(iMediaInfo.getMediaPath());
                iMedia.setLyricContent(iMediaInfo.getLyricContent());
                iMedia.setLyric(iMediaInfo.getLyric());
                iMedia.setArtWork(iMediaInfo.getArtwork());
                iMedia.setRadioFrequency(iMediaInfo.getRadioFrequency());
                iMedia.setRadioStationName(iMediaInfo.getRadioStationName());
                arrayList.add(iMedia);
            }
        }
        return arrayList;
    }

    public static List<IMedia> b(List<MediaInfo> list) {
        Log.d(f142a, "getIMediaBeanList");
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (MediaInfo mediaInfo : list) {
                if (mediaInfo != null) {
                    arrayList.add(a(mediaInfo));
                }
            }
        }
        return arrayList;
    }

    public static JSONArray c(List<MediaInfo> list) {
        Log.d(f142a, "getIMediaBeanList2Json");
        JSONArray jSONArray = new JSONArray();
        if (list != null && list.size() > 0) {
            for (MediaInfo mediaInfo : list) {
                if (mediaInfo != null) {
                    jSONArray.put(b(mediaInfo));
                }
            }
        }
        return jSONArray;
    }

    public static List<MediaInfo> d(List<IMedia> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            Iterator<IMedia> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(b(it.next()));
            }
        }
        return arrayList;
    }

    private static IMedia a(MediaInfo mediaInfo) {
        IMedia iMedia = new IMedia();
        iMedia.setUuid(mediaInfo.getUuid());
        iMedia.setTitle(mediaInfo.getTitle());
        iMedia.setArtist(mediaInfo.getArtist());
        iMedia.setAlbum(mediaInfo.getAlbum());
        iMedia.setDuration(mediaInfo.getDuration());
        iMedia.setPositionInQueue(mediaInfo.getPlayingItemPositionInQueue());
        iMedia.setSourceType(mediaInfo.getSourceType());
        iMedia.setMediaPath(mediaInfo.getMediaPath());
        iMedia.setLyricContent(mediaInfo.getLyricContent());
        iMedia.setLyric(mediaInfo.getLyric());
        iMedia.setArtWork(mediaInfo.getArtwork());
        iMedia.setRadioFrequency(mediaInfo.getRadioFrequency());
        iMedia.setRadioStationName(mediaInfo.getRadioStationName());
        try {
            iMedia.setPlayingMediaListId(mediaInfo.getPlayingMediaListId());
            iMedia.setPlayingMediaListType(mediaInfo.getPlayingMediaListType());
            iMedia.setVip(mediaInfo.getVip());
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
        try {
            iMedia.setSupportCollect(mediaInfo.getSupportCollect());
            iMedia.setCollected(mediaInfo.getCollected());
        } catch (NoSuchMethodError e2) {
            e2.printStackTrace();
        }
        Log.d(f142a, " IMedia:" + iMedia.getUuid() + ", info:" + mediaInfo.getUuid());
        return iMedia;
    }

    private static JSONObject b(MediaInfo mediaInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uuid", mediaInfo.getUuid());
            jSONObject.put(CollectionDialogViewModel.KEY_TITLE, mediaInfo.getTitle());
            jSONObject.put("artist", mediaInfo.getArtist());
            jSONObject.put("album", mediaInfo.getAlbum());
            jSONObject.put(TypedValues.TransitionType.S_DURATION, mediaInfo.getDuration());
            jSONObject.put("positionInQueue", mediaInfo.getPlayingItemPositionInQueue());
            jSONObject.put("sourceType", mediaInfo.getSourceType());
            jSONObject.put("mediaPath", mediaInfo.getMediaPath());
            jSONObject.put("lyricContent", mediaInfo.getLyricContent());
            jSONObject.put("lyric", mediaInfo.getLyric());
            jSONObject.put("artWork", mediaInfo.getArtwork());
            jSONObject.put("radioFrequency", mediaInfo.getRadioFrequency());
            jSONObject.put("radioStationName", mediaInfo.getRadioStationName());
            jSONObject.put("playingMediaListId", mediaInfo.getPlayingMediaListId());
            jSONObject.put("playingMediaListType", mediaInfo.getPlayingMediaListType());
            jSONObject.put("vip", mediaInfo.getVip());
            jSONObject.put("supportCollect", mediaInfo.getSupportCollect());
            jSONObject.put("collected", mediaInfo.getCollected());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static IMediaInfo a(IMedia iMedia) {
        if (iMedia == null) {
            return null;
        }
        c cVar = new c();
        cVar.a(iMedia.getTitle());
        cVar.b(iMedia.getArtist());
        cVar.c(iMedia.getAlbum());
        cVar.a(iMedia.getDuration());
        cVar.a(iMedia.getPlayingItemPositionInQueue());
        cVar.b(iMedia.getSourceType());
        cVar.a(iMedia.getMediaPath());
        cVar.d(iMedia.getLyricContent());
        cVar.b(iMedia.getLyric());
        cVar.c(iMedia.getArtwork());
        cVar.e(iMedia.getRadioFrequency());
        cVar.f(iMedia.getRadioStationName());
        return cVar;
    }

    public static MediaInfo b(IMedia iMedia) {
        h hVar = new h();
        hVar.a(iMedia.getUuid());
        hVar.b(iMedia.getTitle());
        hVar.c(iMedia.getArtist());
        hVar.d(iMedia.getAlbum());
        hVar.a(iMedia.getDuration());
        hVar.a(iMedia.getPlayingItemPositionInQueue());
        hVar.b(iMedia.getSourceType());
        hVar.a(iMedia.getMediaPath());
        hVar.e(iMedia.getLyricContent());
        hVar.b(iMedia.getLyric());
        hVar.c(iMedia.getArtwork());
        hVar.f(iMedia.getRadioFrequency());
        hVar.g(iMedia.getRadioStationName());
        hVar.h(iMedia.getPlayingMediaListId());
        hVar.d(iMedia.getPlayingMediaListType());
        hVar.c(iMedia.getVip());
        iMedia.getSupportCollect();
        iMedia.getCollected();
        return hVar;
    }
}
