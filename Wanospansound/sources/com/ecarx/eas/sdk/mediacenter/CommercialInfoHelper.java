package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.ecarx.eas.sdk.vr.channel.VrChannelInfo;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import com.wanos.media.viewmodel.CollectionDialogViewModel;
import ecarx.eas.xsf.mediacenter.IMediaEx;
import ecarx.eas.xsf.mediacenter.IMediaListEx;
import ecarx.eas.xsf.mediacenter.IMediaListsEx;
import ecarx.xsf.mediacenter.IContent;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMediaList;
import ecarx.xsf.mediacenter.IMediaLists;
import ecarx.xsf.mediacenter.session.MediaErrorBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CommercialInfoHelper {
    public static String TAG = "CommercialInfoHelper";

    public static MediaListInfo getMediaListInfo(IMediaList iMediaList) throws RemoteException {
        try {
            i iVar = new i();
            iVar.a(iMediaList.getTitle());
            iVar.f170a = iMediaList.getMediaListId();
            iVar.b(iMediaList.getMediaListType());
            iVar.a(iMediaList.getSourceType());
            iVar.a(d.d(iMediaList.getMediaList()));
            try {
                iVar.a(iMediaList.getPendingIntent());
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
            }
            return iVar;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static List<MediaListInfo> convertMediaListInfos(List<IMediaList> list) throws RemoteException {
        try {
            ArrayList arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                Iterator<IMediaList> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(getMediaListInfo(it.next()));
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<IMediaList> convertIMediaLists(List<MediaListInfo> list) throws RemoteException {
        try {
            Log.d(TAG, "convertIMediaLists");
            ArrayList arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                Iterator<MediaListInfo> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(getIMediaList(it.next()));
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONArray convertIMediaLists2Json(List<MediaListInfo> list) throws RemoteException {
        try {
            Log.d(TAG, "convertIMediaLists");
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                Iterator<MediaListInfo> it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put(getIMediaList2Json(it.next()));
                }
            }
            return jSONArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IMediaLists getIMediaLists(MediaListsInfo mediaListsInfo) throws RemoteException {
        if (mediaListsInfo == null) {
            return null;
        }
        try {
            Log.d(TAG, "getIMediaLists");
            List<IMediaList> listConvertIMediaLists = convertIMediaLists(mediaListsInfo.getMediaListsInfo());
            IMediaLists iMediaLists = new IMediaLists();
            iMediaLists.setMediaLists(listConvertIMediaLists);
            return iMediaLists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IExContent getMediaListsInfo2IExContent(MediaListsInfo mediaListsInfo) {
        if (mediaListsInfo == null) {
            return null;
        }
        IExContent iExContent = new IExContent();
        iExContent.setData(getMediaListsInfo2JsonStr(mediaListsInfo));
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < mediaListsInfo.getMediaListsInfo().size(); i++) {
            arrayList.add(mediaListsInfo.getMediaListsInfo().get(i).getPendingIntent());
        }
        iExContent.setPendingIntents(arrayList);
        return iExContent;
    }

    public static IExContent getMediaErrorBeanIExContent(MediaErrorBean mediaErrorBean) {
        if (mediaErrorBean == null) {
            return null;
        }
        IExContent iExContent = new IExContent();
        iExContent.setData(getMediaErrorBeanJsonStr(mediaErrorBean));
        ArrayList arrayList = new ArrayList();
        arrayList.add(mediaErrorBean.getPendingIntent());
        iExContent.setPendingIntents(arrayList);
        return iExContent;
    }

    public static String getMediaListsInfo2JsonStr(MediaListsInfo mediaListsInfo) {
        try {
            JSONObject iMediaLists2Json = getIMediaLists2Json(mediaListsInfo);
            if (iMediaLists2Json != null) {
                return iMediaLists2Json.toString();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMediaErrorBeanJsonStr(MediaErrorBean mediaErrorBean) {
        try {
            JSONObject mediaErrorBeanJson = getMediaErrorBeanJson(mediaErrorBean);
            if (mediaErrorBeanJson != null) {
                return mediaErrorBeanJson.toString();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject getIMediaLists2Json(MediaListsInfo mediaListsInfo) {
        if (mediaListsInfo == null) {
            return null;
        }
        try {
            Log.d(TAG, "getIMediaLists");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mediaLists", convertIMediaLists2Json(mediaListsInfo.getMediaListsInfo()));
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject getMediaErrorBeanJson(MediaErrorBean mediaErrorBean) {
        if (mediaErrorBean == null) {
            return null;
        }
        try {
            Log.d(TAG, "getMediaErrorBeanJson");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("packageName", mediaErrorBean.getPackageName());
            jSONObject.put("errorState", mediaErrorBean.getErrorState());
            jSONObject.put("sourceType", mediaErrorBean.getSourceType());
            jSONObject.put("errorMsg", mediaErrorBean.getErrorMsg());
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IMediaListsEx getIMediaListsExByIExContent(IExContent iExContent) {
        if (iExContent != null) {
            return getIMediaListsExByJson(iExContent.getData(), iExContent.getPendingIntents());
        }
        return null;
    }

    public static IMediaListsEx getIMediaListsExByJson(String str, List<PendingIntent> list) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        IMediaListsEx iMediaListsEx = new IMediaListsEx();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("mediaLists");
            if (jSONArrayOptJSONArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject != null) {
                        IMediaListEx iMediaListEx = new IMediaListEx();
                        iMediaListEx.setTitle(jSONObjectOptJSONObject.optString(CollectionDialogViewModel.KEY_TITLE));
                        iMediaListEx.setMediaListId(jSONObjectOptJSONObject.optString("mediaListId"));
                        iMediaListEx.setArtwork(Uri.parse(jSONObjectOptJSONObject.optString("artwork")));
                        iMediaListEx.setMediaListType(jSONObjectOptJSONObject.optInt("mediaListType"));
                        iMediaListEx.setSourceType(jSONObjectOptJSONObject.optInt("sourceType"));
                        if (list != null && list.size() > i) {
                            iMediaListEx.setPendingIntent(list.get(i));
                        }
                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("mediaList");
                        if (jSONArrayOptJSONArray2 != null) {
                            ArrayList arrayList2 = new ArrayList();
                            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                                IMediaEx iMediaEx = new IMediaEx();
                                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                                iMediaEx.setUuid(jSONObjectOptJSONObject2.optString("uuid"));
                                iMediaEx.setTitle(jSONObjectOptJSONObject2.optString(CollectionDialogViewModel.KEY_TITLE));
                                iMediaEx.setArtist(jSONObjectOptJSONObject2.optString("artist"));
                                iMediaEx.setAlbum(jSONObjectOptJSONObject2.optString("album"));
                                iMediaEx.setDuration(jSONObjectOptJSONObject2.optLong(TypedValues.TransitionType.S_DURATION));
                                iMediaEx.setPositionInQueue(jSONObjectOptJSONObject2.optInt("positionInQueue"));
                                iMediaEx.setSourceType(jSONObjectOptJSONObject2.optInt("sourceType"));
                                iMediaEx.setMediaPath(Uri.parse(jSONObjectOptJSONObject2.optString("mediaPath")));
                                iMediaEx.setLyricContent(jSONObjectOptJSONObject2.optString("lyricContent"));
                                iMediaEx.setLyric(Uri.parse(jSONObjectOptJSONObject2.optString("lyric")));
                                iMediaEx.setArtWork(Uri.parse(jSONObjectOptJSONObject2.optString("artWork")));
                                iMediaEx.setRadioFrequency(jSONObjectOptJSONObject2.optString("radioFrequency"));
                                iMediaEx.setRadioStationName(jSONObjectOptJSONObject2.optString("radioStationName"));
                                iMediaEx.setPlayingMediaListId(jSONObjectOptJSONObject2.optString("playingMediaListId"));
                                iMediaEx.setPlayingMediaListType(jSONObjectOptJSONObject2.optInt("playingMediaListType"));
                                iMediaEx.setVip(jSONObjectOptJSONObject2.optInt("vip"));
                                iMediaEx.setSupportCollect(jSONObjectOptJSONObject2.optInt("supportCollect"));
                                iMediaEx.setCollected(jSONObjectOptJSONObject2.optInt("collected"));
                                arrayList2.add(iMediaEx);
                            }
                            iMediaListEx.setMediaList(arrayList2);
                        }
                        arrayList.add(iMediaListEx);
                    }
                }
                iMediaListsEx.setMediaLists(arrayList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return iMediaListsEx;
    }

    public static IMediaLists getIMediaListsByJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        IMediaLists iMediaLists = new IMediaLists();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("mediaLists");
            if (jSONArrayOptJSONArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject != null) {
                        IMediaList iMediaList = new IMediaList();
                        iMediaList.setTitle(jSONObjectOptJSONObject.optString(CollectionDialogViewModel.KEY_TITLE));
                        iMediaList.setMediaListId(jSONObjectOptJSONObject.optString("mediaListId"));
                        iMediaList.setMediaListType(jSONObjectOptJSONObject.optInt("mediaListType"));
                        iMediaList.setSourceType(jSONObjectOptJSONObject.optInt("sourceType"));
                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("mediaList");
                        if (jSONArrayOptJSONArray2 != null) {
                            ArrayList arrayList2 = new ArrayList();
                            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                                IMedia iMedia = new IMedia();
                                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                                iMedia.setUuid(jSONObjectOptJSONObject2.optString("uuid"));
                                iMedia.setTitle(jSONObjectOptJSONObject2.optString(CollectionDialogViewModel.KEY_TITLE));
                                iMedia.setArtist(jSONObjectOptJSONObject2.optString("artist"));
                                iMedia.setAlbum(jSONObjectOptJSONObject2.optString("album"));
                                iMedia.setDuration(jSONObjectOptJSONObject2.optLong(TypedValues.TransitionType.S_DURATION));
                                iMedia.setPositionInQueue(jSONObjectOptJSONObject2.optInt("positionInQueue"));
                                iMedia.setSourceType(jSONObjectOptJSONObject2.optInt("sourceType"));
                                iMedia.setMediaPath(Uri.parse(jSONObjectOptJSONObject2.optString("mediaPath")));
                                iMedia.setLyricContent(jSONObjectOptJSONObject2.optString("lyricContent"));
                                iMedia.setLyric(Uri.parse(jSONObjectOptJSONObject2.optString("lyric")));
                                iMedia.setArtWork(Uri.parse(jSONObjectOptJSONObject2.optString("artWork")));
                                iMedia.setRadioFrequency(jSONObjectOptJSONObject2.optString("radioFrequency"));
                                iMedia.setRadioStationName(jSONObjectOptJSONObject2.optString("radioStationName"));
                                iMedia.setPlayingMediaListId(jSONObjectOptJSONObject2.optString("playingMediaListId"));
                                iMedia.setPlayingMediaListType(jSONObjectOptJSONObject2.optInt("playingMediaListType"));
                                iMedia.setVip(jSONObjectOptJSONObject2.optInt("vip"));
                                iMedia.setSupportCollect(jSONObjectOptJSONObject2.optInt("supportCollect"));
                                iMedia.setCollected(jSONObjectOptJSONObject2.optInt("collected"));
                                arrayList2.add(iMedia);
                            }
                            iMediaList.setMediaList(arrayList2);
                        }
                        arrayList.add(iMediaList);
                    }
                }
                iMediaLists.setMediaLists(arrayList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return iMediaLists;
    }

    public static String getIMediaListsEx2JsonStr(IMediaListsEx iMediaListsEx) {
        if (iMediaListsEx == null) {
            return null;
        }
        try {
            return getIMediaListsEx2Json(iMediaListsEx).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject getIMediaListsEx2Json(IMediaListsEx iMediaListsEx) {
        try {
            Log.d(TAG, "getIMediaLists");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mediaLists", convertIMediaListEx2Json(iMediaListsEx.getMediaLists()));
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONArray convertIMediaListEx2Json(List<IMediaListEx> list) {
        try {
            Log.d(TAG, "convertIMediaLists");
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                Iterator<IMediaListEx> it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put(getIMediaListsEx2Json(it.next()));
                }
            }
            return jSONArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IMediaListsEx changeIMediaLists2Ex(IMediaLists iMediaLists) {
        return getIMediaListsExByJson(getIMediaLists2JsonStr(iMediaLists), getPendingIntentsByIMediaLists(iMediaLists));
    }

    private static JSONObject getIMediaListsEx2Json(IMediaListEx iMediaListEx) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CollectionDialogViewModel.KEY_TITLE, iMediaListEx.getTitle());
            jSONObject.put("mediaListId", iMediaListEx.getMediaListId());
            jSONObject.put("artwork", iMediaListEx.getArtwork());
            jSONObject.put("mediaListType", iMediaListEx.getMediaListType());
            jSONObject.put("sourceType", iMediaListEx.getSourceType());
            jSONObject.put("mediaList", getIMediaBeanListEx2Json(iMediaListEx.getMediaList()));
            Log.d(TAG, "MediaListInfo: title = " + iMediaListEx.getTitle() + " , sourceType = " + iMediaListEx.getSourceType() + " listId = " + iMediaListEx.getMediaListId() + "list = " + iMediaListEx.getMediaList() + " , " + jSONObject);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getIMediaBeanListEx2Json(List<IMediaEx> list) {
        Log.d(TAG, "getIMediaBeanList2Json");
        JSONArray jSONArray = new JSONArray();
        if (list != null && list.size() > 0) {
            for (IMediaEx iMediaEx : list) {
                if (iMediaEx != null) {
                    jSONArray.put(getIMediaEx2Json(iMediaEx));
                }
            }
        }
        return jSONArray;
    }

    public static JSONObject getIMediaEx2Json(IMediaEx iMediaEx) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uuid", iMediaEx.getUuid());
            jSONObject.put(CollectionDialogViewModel.KEY_TITLE, iMediaEx.getTitle());
            jSONObject.put("artist", iMediaEx.getArtist());
            jSONObject.put("album", iMediaEx.getAlbum());
            jSONObject.put(TypedValues.TransitionType.S_DURATION, iMediaEx.getDuration());
            jSONObject.put("positionInQueue", iMediaEx.getPlayingItemPositionInQueue());
            jSONObject.put("sourceType", iMediaEx.getSourceType());
            jSONObject.put("mediaPath", iMediaEx.getMediaPath());
            jSONObject.put("lyricContent", iMediaEx.getLyricContent());
            jSONObject.put("lyric", iMediaEx.getLyric());
            jSONObject.put("artWork", iMediaEx.getArtwork());
            jSONObject.put("radioFrequency", iMediaEx.getRadioFrequency());
            jSONObject.put("radioStationName", iMediaEx.getRadioStationName());
            jSONObject.put("playingMediaListId", iMediaEx.getPlayingMediaListId());
            jSONObject.put("playingMediaListType", iMediaEx.getPlayingMediaListType());
            jSONObject.put("vip", iMediaEx.getVip());
            jSONObject.put("supportCollect", iMediaEx.getSupportCollect());
            jSONObject.put("collected", iMediaEx.getCollected());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private static List<PendingIntent> getPendingIntentsByIMediaLists(IMediaLists iMediaLists) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iMediaLists.getMediaLists().size(); i++) {
            try {
                arrayList.add(iMediaLists.getMediaLists().get(i).getPendingIntent());
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
        return arrayList;
    }

    private static String getIMediaLists2JsonStr(IMediaLists iMediaLists) {
        if (iMediaLists == null) {
            return null;
        }
        try {
            return getIMediaLists2Json(iMediaLists).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject getIMediaLists2Json(IMediaLists iMediaLists) {
        try {
            Log.d(TAG, "getIMediaLists");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mediaLists", convertIMediaList2Json(iMediaLists.getMediaLists()));
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray convertIMediaList2Json(List<IMediaList> list) {
        try {
            Log.d(TAG, "convertIMediaLists");
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                Iterator<IMediaList> it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put(getIMediaLists2Json(it.next()));
                }
            }
            return jSONArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getIMediaLists2Json(IMediaList iMediaList) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CollectionDialogViewModel.KEY_TITLE, iMediaList.getTitle());
            jSONObject.put("mediaListId", iMediaList.getMediaListId());
            jSONObject.put("mediaListType", iMediaList.getMediaListType());
            jSONObject.put("sourceType", iMediaList.getSourceType());
            jSONObject.put("mediaList", getIMediaBeanList2Json(iMediaList.getMediaList()));
            jSONObject.put("pendingIntent", object2String(iMediaList.getPendingIntent()));
            Log.d(TAG, "MediaListInfo: title = " + iMediaList.getTitle() + " , sourceType = " + iMediaList.getSourceType() + " listId = " + iMediaList.getMediaListId() + "list = " + iMediaList.getMediaList() + " , " + jSONObject);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getIMediaBeanList2Json(List<IMedia> list) {
        Log.d(TAG, "getIMediaBeanList2Json");
        JSONArray jSONArray = new JSONArray();
        if (list != null && list.size() > 0) {
            for (IMedia iMedia : list) {
                if (iMedia != null) {
                    jSONArray.put(getIMedia2Json(iMedia));
                }
            }
        }
        return jSONArray;
    }

    public static JSONObject getIMedia2Json(IMedia iMedia) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uuid", iMedia.getUuid());
            jSONObject.put(CollectionDialogViewModel.KEY_TITLE, iMedia.getTitle());
            jSONObject.put("artist", iMedia.getArtist());
            jSONObject.put("album", iMedia.getAlbum());
            jSONObject.put(TypedValues.TransitionType.S_DURATION, iMedia.getDuration());
            jSONObject.put("positionInQueue", iMedia.getPlayingItemPositionInQueue());
            jSONObject.put("sourceType", iMedia.getSourceType());
            jSONObject.put("mediaPath", iMedia.getMediaPath());
            jSONObject.put("lyricContent", iMedia.getLyricContent());
            jSONObject.put("lyric", iMedia.getLyric());
            jSONObject.put("artWork", iMedia.getArtwork());
            jSONObject.put("radioFrequency", iMedia.getRadioFrequency());
            jSONObject.put("radioStationName", iMedia.getRadioStationName());
            jSONObject.put("playingMediaListId", iMedia.getPlayingMediaListId());
            jSONObject.put("playingMediaListType", iMedia.getPlayingMediaListType());
            jSONObject.put("vip", iMedia.getVip());
            jSONObject.put("supportCollect", iMedia.getSupportCollect());
            jSONObject.put("collected", iMedia.getCollected());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static MediaListsInfo getMediaListsInfo(IMediaLists iMediaLists) throws RemoteException {
        try {
            List<MediaListInfo> listConvertMediaListInfos = convertMediaListInfos(iMediaLists.getMediaLists());
            MediaListsInfo mediaListsInfo = new MediaListsInfo();
            mediaListsInfo.setMediaListsInfo(listConvertMediaListInfos);
            return mediaListsInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IMediaList getIMediaList(MediaListInfo mediaListInfo) throws RemoteException {
        try {
            IMediaList iMediaList = new IMediaList();
            iMediaList.setTitle(mediaListInfo.getTitle());
            iMediaList.setMediaListId(mediaListInfo.getMediaListId());
            iMediaList.setMediaListType(mediaListInfo.getMediaListType());
            iMediaList.setSourceType(mediaListInfo.getSourceType());
            iMediaList.setMediaList(d.b(mediaListInfo.getMediaList()));
            try {
                iMediaList.setPendingIntent(mediaListInfo.getPendingIntent());
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
            }
            Log.d(TAG, "MediaListInfo: title = " + mediaListInfo.getTitle() + " , sourceType = " + mediaListInfo.getSourceType() + " listId = " + mediaListInfo.getMediaListId() + "list = " + mediaListInfo.getMediaList() + " , " + iMediaList);
            return iMediaList;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.w(TAG, "getIMediaList fail");
            return null;
        }
    }

    public static JSONObject getIMediaListToJson(IMediaList iMediaList) throws RemoteException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CollectionDialogViewModel.KEY_TITLE, iMediaList.getTitle());
            jSONObject.put("sourceType", iMediaList.getSourceType());
            jSONObject.put("mediaListId", iMediaList.getMediaListId());
            jSONObject.put("mediaListType", iMediaList.getMediaListType());
            jSONObject.put("mediaList", getIMediaBeanList2Json(iMediaList.getMediaList()));
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            Log.w(TAG, "getIMediaListToJson fail");
            return null;
        }
    }

    private static JSONObject getIMediaList2Json(MediaListInfo mediaListInfo) throws RemoteException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CollectionDialogViewModel.KEY_TITLE, mediaListInfo.getTitle());
            jSONObject.put("mediaListId", mediaListInfo.getMediaListId());
            jSONObject.put("artwork", mediaListInfo.getArtwork());
            jSONObject.put("mediaListType", mediaListInfo.getMediaListType());
            jSONObject.put("sourceType", mediaListInfo.getSourceType());
            jSONObject.put("mediaList", d.c(mediaListInfo.getMediaList()));
            Log.d(TAG, "MediaListInfo: title = " + mediaListInfo.getTitle() + " , sourceType = " + mediaListInfo.getSourceType() + " listId = " + mediaListInfo.getMediaListId() + "list = " + mediaListInfo.getMediaList() + " , " + jSONObject);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String object2String(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelable.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return Base64.encodeToString(bArrMarshall, 0);
    }

    public static Parcel unmarshall(byte[] bArr) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        return parcelObtain;
    }

    public static <T> T unmarshall(String str, Parcelable.Creator<T> creator) {
        Parcel parcelUnmarshall = unmarshall(Base64.decode(str, 0));
        try {
            return creator.createFromParcel(parcelUnmarshall);
        } finally {
            parcelUnmarshall.recycle();
        }
    }

    public static List<IContent> convertToIContent(List<ContentInfo> list) throws RemoteException {
        try {
            Log.d(TAG, "convertToIContent");
            ArrayList arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                Iterator<ContentInfo> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(getIContent(it.next()));
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ContentInfo> convertContentInfo(List<IContent> list) throws RemoteException {
        try {
            ArrayList arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                Iterator<IContent> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(getContentInfo(it.next()));
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ContentInfo getContentInfo(IContent iContent) throws RemoteException {
        try {
            ContentInfoBean contentInfoBean = new ContentInfoBean();
            contentInfoBean.setId(iContent.getId());
            contentInfoBean.setTitle(iContent.getTitle());
            contentInfoBean.setPendingIntent(iContent.getPendingIntent());
            contentInfoBean.setBackground(iContent.getBackground());
            return contentInfoBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IContent getIContent(ContentInfo contentInfo) throws RemoteException {
        try {
            IContent iContent = new IContent();
            iContent.setId(contentInfo.getId());
            iContent.setTitle(contentInfo.getTitle());
            iContent.setPendingIntent(contentInfo.getPendingIntent());
            iContent.setBackground(contentInfo.getBackground());
            Log.d(TAG, "IContent: " + iContent.toString() + ", ContentInfo: id = " + contentInfo.getId() + " title = " + contentInfo.getTitle() + ", pendingIntent = " + contentInfo.getPendingIntent() + ", url = " + contentInfo.getBackground());
            return iContent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getVrChannelInfo2Json(VrChannelInfo vrChannelInfo) throws RemoteException {
        if (vrChannelInfo == null) {
            return null;
        }
        try {
            Log.i(TAG, "VrChannelInfo  is null " + (vrChannelInfo == null));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mediaPackageName", vrChannelInfo.getMediaPackageName());
            jSONObject.put("mediaVersion", vrChannelInfo.getMediaVersion());
            jSONObject.put("mediaDescription", vrChannelInfo.getMediaDescription());
            jSONObject.put("channelDataType", vrChannelInfo.getChannelDataType());
            Log.d(TAG, "MediaListInfo: mediaPackageName = " + vrChannelInfo.getMediaPackageName() + " , mediaVersion = " + vrChannelInfo.getMediaVersion() + " mediaDescription = " + vrChannelInfo.getMediaDescription() + "channelDataType = " + vrChannelInfo.getChannelDataType() + " , " + jSONObject);
            return jSONObject;
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    public static int getTTSResultJson(String str) {
        try {
            return new JSONObject(str).optInt("status");
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static long getProgress(String str) {
        try {
            return new JSONObject(str).optLong("progress");
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }
}
