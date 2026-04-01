package com.ecarx.eas.sdk.mediacenter;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.mediacenter.b;
import com.ecarx.eas.sdk.vr.channel.SemanticData;
import com.ecarx.eas.sdk.vr.channel.VrChannelDataListener;
import com.ecarx.eas.sdk.vr.channel.VrTtsResultListener;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.IContent;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMediaLists;
import ecarx.xsf.mediacenter.IMusicClient;
import ecarx.xsf.mediacenter.IRecommend;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class j extends IMusicClient.Stub implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IMusicClient f174a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private MusicClient f175b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private VrChannelDataListener f176c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private VrTtsResultListener f177d;

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onMediaForward(boolean z) throws RemoteException {
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onMediaRewind(boolean z) throws RemoteException {
        return false;
    }

    public final void a(VrTtsResultListener vrTtsResultListener) {
        this.f177d = vrTtsResultListener;
    }

    j(IMusicClient iMusicClient) {
        this.f174a = iMusicClient;
    }

    j(MusicClient musicClient) {
        this.f175b = musicClient;
    }

    private IMusicClient a() {
        IMusicClient iMusicClient = this.f174a;
        if (iMusicClient == null) {
            return null;
        }
        return iMusicClient;
    }

    private MusicClient b() {
        MusicClient musicClient = this.f175b;
        if (musicClient == null) {
            return null;
        }
        return musicClient;
    }

    public final void a(VrChannelDataListener vrChannelDataListener) {
        this.f176c = vrChannelDataListener;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onPlay() throws RemoteException {
        if (a() != null) {
            return a().onPlay();
        }
        if (b() != null) {
            return b().onPlay();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onPause() throws RemoteException {
        if (a() != null) {
            return a().onPause();
        }
        if (b() != null) {
            return b().onPause();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onNext() throws RemoteException {
        if (a() != null) {
            return a().onNext();
        }
        if (b() != null) {
            return b().onNext();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onPrevious() throws RemoteException {
        if (a() != null) {
            return a().onPrevious();
        }
        if (b() != null) {
            return b().onPrevious();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onForward() throws RemoteException {
        if (a() != null) {
            return a().onForward();
        }
        if (b() != null) {
            return b().onForward();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onRewind() throws RemoteException {
        if (a() != null) {
            return a().onRewind();
        }
        if (b() != null) {
            return b().onRewind();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onCollect(int i, boolean z) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onCollect(i, z);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onDownload(int i, boolean z) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onDownload(i, z);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onLoopModeChange(int i) throws RemoteException {
        if (a() != null) {
            return a().onLoopModeChange(i);
        }
        if (b() != null) {
            return b().onLoopModeChange(i);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onSourceSelected(int i) throws RemoteException {
        if (a() != null) {
            return a().onSourceSelected(i);
        }
        if (b() != null) {
            return b().onSourceSelected(i);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onSourceChanged(int i, String str) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onSourceChanged(i, str);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onMediaSelected(IMedia iMedia) throws RemoteException {
        if (a() != null) {
            return a().onMediaSelected(d.a(iMedia));
        }
        if (b() != null) {
            return b().onMediaSelected(d.b(iMedia));
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final ecarx.xsf.mediacenter.IMusicPlaybackInfo getMusicPlaybackInfo() throws RemoteException {
        if (a() != null) {
            return new m(a().getMusicPlaybackInfo());
        }
        if (b() != null) {
            return new m(b().getMusicPlaybackInfo());
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final int[] getMediaSourceTypeList() throws RemoteException {
        if (a() != null) {
            return a().getMediaSourceTypeList();
        }
        return b() != null ? b().getMediaSourceTypeList() : new int[0];
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final int getCurrentSourceType() throws RemoteException {
        if (a() != null) {
            return a().getCurrentSourceType();
        }
        if (b() != null) {
            return b().getCurrentSourceType();
        }
        return 0;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final long getCurrentProgress() throws RemoteException {
        if (a() != null) {
            return a().getCurrentProgress();
        }
        if (b() != null) {
            return b().getCurrentProgress();
        }
        return 0L;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final List<IMedia> getPlaylist(int i) throws RemoteException {
        if (a() != null) {
            return d.a(a().getPlaylist(i));
        }
        if (b() != null) {
            return d.b(b().getPlaylist(i));
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onReplay() throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onReplay();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onPlayRecommend(IRecommend iRecommend) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onPlayRecommend(ecarx.xsf.mediacenter.session.adapter.l.a(iRecommend));
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onCancelRecommend(IRecommend iRecommend) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onCancelRecommend(ecarx.xsf.mediacenter.session.adapter.l.a(iRecommend));
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onMediaSelectedPlay(int i, String str) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() == null) {
            return false;
        }
        Log.e("MusicClientWrapper", "getOriginClazz  onMediaSelected");
        return b().onMediaSelected(i, str);
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onMediaQualityChange(int i) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onMediaQualityChange(i);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final void onMediaCenterFocusChanged(String str) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
        } else if (b() != null) {
            b().onMediaCenterFocusChanged(str);
        }
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean onExit() throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onExit();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean selectListMediaPlay(int i, int i2, String str) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().selectListMediaPlay(i, i2, str);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final List<IContent> getContentList() throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return null;
        }
        if (b() != null) {
            return CommercialInfoHelper.convertToIContent(b().getContentList());
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final IMediaLists getMultiMediaList(int[] iArr) throws RemoteException {
        MediaListsInfo multiMediaList;
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
        } else {
            if (b() == null || (multiMediaList = b().getMultiMediaList(iArr)) == null) {
                return null;
            }
            return CommercialInfoHelper.getIMediaLists(multiMediaList);
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean ctrlPlayMediaList(int i) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().ctrlPlayMediaList(i);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final boolean ctrlPauseMediaList(int i) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().ctrlPauseMediaList(i);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final int ctrlCollect(int i, boolean z) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
            return -1;
        }
        if (b() != null) {
            return b().ctrlCollect(i, z);
        }
        return -1;
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final void operationType(int i) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
        } else if (b() != null) {
            b().operationType(i);
        }
    }

    @Override // ecarx.xsf.mediacenter.IMusicClient
    public final void ctrlCollectByUUID(int i, String str, boolean z) throws RemoteException {
        if (a() != null) {
            Log.e("MusicClientWrapper", "old interface not support");
        } else if (b() != null) {
            b().ctrlCollect(i, str, z);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.b.a
    public final String a(int i, String str, String str2, IBinder iBinder) {
        MediaListsInfo multiMediaList;
        Log.d("MusicClientWrapper", "onAction:" + i + "," + str + "," + str2 + "," + iBinder);
        if (i == 1) {
            try {
                if (a() != null) {
                    Log.e("MusicClientWrapper", "old interface not support");
                    return null;
                }
                if (b() == null || (multiMediaList = b().getMultiMediaList(null)) == null) {
                    return null;
                }
                String mediaListsInfo2JsonStr = CommercialInfoHelper.getMediaListsInfo2JsonStr(multiMediaList);
                Log.d("MusicClientWrapper", "result:" + mediaListsInfo2JsonStr);
                return mediaListsInfo2JsonStr;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (i != 2) {
            if (i == 4) {
                if (this.f177d == null) {
                    return null;
                }
                this.f177d.handleTtsResponse(CommercialInfoHelper.getTTSResultJson(str2));
                return null;
            }
            if (i != 5 || b() == null) {
                return null;
            }
            b().onSeek(CommercialInfoHelper.getProgress(str2));
            return null;
        }
        if (this.f176c == null) {
            return null;
        }
        SemanticData semanticData = new SemanticData();
        try {
            JSONObject jSONObject = new JSONObject(str2);
            int iOptInt = jSONObject.optInt("semantic");
            String strOptString = jSONObject.optString("mediaSource");
            String strOptString2 = jSONObject.optString("mediaType");
            String strOptString3 = jSONObject.optString("titleName");
            String strOptString4 = jSONObject.optString("artistName");
            String strOptString5 = jSONObject.optString("albumName");
            String strOptString6 = jSONObject.optString("typeName");
            String strOptString7 = jSONObject.optString("mediaCtrl");
            String strOptString8 = jSONObject.optString("tunerFrequency");
            String strOptString9 = jSONObject.optString("subTypeName");
            int iOptInt2 = jSONObject.optInt("modeType");
            String strOptString10 = jSONObject.optString("originInfo");
            String strOptString11 = jSONObject.optString("errorCode");
            String strOptString12 = jSONObject.optString("errorMsg");
            String strOptString13 = jSONObject.optString("detailsIntent");
            String strOptString14 = jSONObject.optString("recogSlotTag");
            String strOptString15 = jSONObject.optString("mobileMusicName");
            String strOptString16 = jSONObject.optString("videoName");
            long jOptLong = jSONObject.optLong("timeSlot");
            String strOptString17 = jSONObject.optString("channelName");
            semanticData.setSemantic(iOptInt);
            semanticData.setMediaSource(strOptString);
            semanticData.setMediaType(strOptString2);
            semanticData.setTitleName(strOptString3);
            semanticData.setArtistName(strOptString4);
            semanticData.setAlbumName(strOptString5);
            semanticData.setTypeName(strOptString6);
            semanticData.setMediaCtrl(strOptString7);
            semanticData.setTunerFrequency(strOptString8);
            semanticData.setSubTypeName(strOptString9);
            semanticData.setModeType(iOptInt2);
            semanticData.setOriginInfo(strOptString10);
            semanticData.setErrorCode(strOptString11);
            semanticData.setErrorMsg(strOptString12);
            semanticData.setDetailsIntent(strOptString13);
            semanticData.setRecogSlotTag(strOptString14);
            semanticData.setMobileMusicName(strOptString15);
            semanticData.setTimeSlot(jOptLong);
            semanticData.setVideoName(strOptString16);
            semanticData.setChannelName(strOptString17);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.f176c.handleVrChannelData(1, semanticData);
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.b.a
    public final IExContent a(int i, String str, String str2, IExContent iExContent, IBinder iBinder) {
        Log.d("MusicClientWrapper", "onAction:" + i + "," + str + "," + str2 + "," + iBinder);
        if (i == 1) {
            try {
                if (a() != null) {
                    Log.e("MusicClientWrapper", "old interface not support");
                } else if (b() != null) {
                    return CommercialInfoHelper.getMediaListsInfo2IExContent(b().getMultiMediaList(null));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
