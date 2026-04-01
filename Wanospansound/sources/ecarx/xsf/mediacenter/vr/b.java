package ecarx.xsf.mediacenter.vr;

import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vr.channel.VrChannelInfo;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessage;
import ecarx.xsf.mediacenter.IAllPlaybackInfo;
import ecarx.xsf.mediacenter.IMediaCenterInternalApiSvc;
import ecarx.xsf.xiaoka.IXiaokaInternalApiSvc;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class b extends VrInternalAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile b f818a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IMediaCenterInternalApiSvc f819b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f820c = new byte[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IEASFrameworkService f821d;
    private com.ecarx.eas.sdk.mediacenter.b e;
    private ecarx.xsf.mediacenter.vr.a.a f;

    public static b a() {
        if (f818a == null) {
            synchronized (b.class) {
                if (f818a == null) {
                    f818a = new b();
                }
            }
        }
        return f818a;
    }

    private b() {
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public void updateIEASFrameworkService(IEASFrameworkService iEASFrameworkService) {
        if (this.f821d == null) {
            this.f821d = iEASFrameworkService;
            this.e = new com.ecarx.eas.sdk.mediacenter.b();
            this.f = new ecarx.xsf.mediacenter.vr.a.a();
            a("registerVrEx", "Vr", this.e.asBinder());
            this.e.a("vrAPI", this.f);
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public void updateMediaCenterSvc(IBinder iBinder) {
        if (iBinder != null) {
            try {
                Log.i("VrInternalAPIImplSDK", "updateMediaCenterSvc");
                this.f819b = IMediaCenterInternalApiSvc.Stub.asInterface(iBinder);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.f819b = null;
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean hasPlayingMedia() {
        if (this.f819b == null) {
            return false;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "hasPlayingMedia");
            return this.f819b.hasPlayingMedia();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public void setXiaokaInternalApiSvc(IXiaokaInternalApiSvc iXiaokaInternalApiSvc) {
        if (this.f819b != null) {
            try {
                Log.i("VrInternalAPIImplSDK", "setXiaokaInternalApiSvc");
                this.f819b.setXiaokaInternalApiSvc(iXiaokaInternalApiSvc);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public IAllPlaybackInfo getAllPlaybackInfo() {
        if (this.f819b == null) {
            return null;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "getAllPlaybackInfo");
            return this.f819b.getAllPlaybackInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlPlay() {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlPlay");
            return this.f819b.playCtrlPlay();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlPause() {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlPause");
            return this.f819b.playCtrlPause();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlStop() {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlStop");
            return this.f819b.playCtrlStop();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlFastForward() {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlFastForward");
            return this.f819b.playCtrlFastForward();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlRewind() {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlRewind");
            return this.f819b.playCtrlRewind();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlNext() {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlNext");
            return this.f819b.playCtrlNext();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlPrevious() {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlPrevious");
            return this.f819b.playCtrlPrevious();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlPlayType(int i) {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlPlayType");
            return this.f819b.playCtrlPlayType(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlReplay() {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlReplay");
            return this.f819b.playCtrlReplay();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlCollect(int i, boolean z) {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlCollect");
            return this.f819b.playCtrlCollect(i, z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlDownload(int i, boolean z) {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlDownload");
            return this.f819b.playCtrlDownload(i, z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean handleSearchMusic(QMusicResult qMusicResult) {
        if (this.f819b == null) {
            return false;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "handleSearchMusic");
            return this.f819b.handleSearchMusic(qMusicResult);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean handleSearchRadio(QRadioResult qRadioResult) {
        if (this.f819b == null) {
            return false;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "handleSearchRadio");
            return this.f819b.handleSearchRadio(qRadioResult);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean handleSearchNews(QNewsResult qNewsResult) {
        if (this.f819b == null) {
            return false;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "handleSearchNews");
            return this.f819b.handleSearchNews(qNewsResult);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean handlePlayMusic(QMusicResult qMusicResult) {
        if (this.f819b == null) {
            return false;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "handlePlayMusic");
            return this.f819b.handlePlayMusic(qMusicResult);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean handlePlayRadio(QRadioResult qRadioResult) {
        if (this.f819b == null) {
            return false;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "handlePlayRadio");
            return this.f819b.handlePlayRadio(qRadioResult);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean handlePlayNews(QNewsResult qNewsResult) {
        if (this.f819b == null) {
            return false;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "handlePlayNews");
            return this.f819b.handlePlayNews(qNewsResult);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean handleCtrlApp(int i, int i2) {
        if (this.f819b == null) {
            return false;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "handleCtrlApp");
            return this.f819b.handleCtrlApp(i, i2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public int playCtrlQuality(int i) {
        if (this.f819b == null) {
            return -1;
        }
        try {
            Log.i("VrInternalAPIImplSDK", "playCtrlQuality");
            return this.f819b.playCtrlQuality(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public VrChannelInfo getCurrentVRChannelInfo() {
        Log.i("VrInternalAPIImplSDK", "getCurrentVRChannelInfo");
        String strA = a("getCurrentVRChannelInfo", "", (IBinder) null);
        if (strA == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(strA);
            String str = (String) jSONObject.get("mediaPackageName");
            String str2 = (String) jSONObject.get("mediaVersion");
            String str3 = (String) jSONObject.get("mediaDescription");
            int iIntValue = ((Integer) jSONObject.get("channelDataType")).intValue();
            Log.i("VrInternalAPIImplSDK", "getCurrentVRChannelInfo  mediaPackageName" + str + "  channelDataType " + iIntValue);
            return new VrChannelInfo(str, str2, str3, iIntValue);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean regVrChangeListener(VrTypeChangeListener vrTypeChangeListener) {
        Log.i("VrInternalAPIImplSDK", "regVrChangeListener");
        if (a("regVrChangeListener", (String) null, (IBinder) null) == null) {
            return false;
        }
        this.f.a(vrTypeChangeListener);
        return true;
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean unregVrChangeListener(VrTypeChangeListener vrTypeChangeListener) {
        Log.i("VrInternalAPIImplSDK", "unregVrChangeListener");
        if (a("unregVrChangeListener", (String) null, (IBinder) null) == null) {
            return false;
        }
        this.f.a(null);
        return true;
    }

    @Override // ecarx.xsf.mediacenter.vr.VrInternalAPI
    public boolean sendTTSResult(String str, int i) {
        Log.i("VrInternalAPIImplSDK", "sendTTSResult ttsId " + str + " status " + i);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ttsId", str);
            jSONObject.put("status", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a("sendTTSResult", jSONObject.toString(), (IBinder) null) != null;
    }

    private String a(String str, String str2, IBinder iBinder) {
        if (this.f821d == null) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = "NoParam";
            }
            byte[] bArr = a(this.f821d, new EASFrameworkMessage(IServiceManager.SERVICE_MEDIACENTER, "MediaCenterAPI", str, str2.getBytes(), this.f820c), iBinder).mRetMsg.mData;
            if (bArr != null && bArr.length > 0) {
                return new String(bArr);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private static EASFrameworkRetMessage a(IEASFrameworkService iEASFrameworkService, EASFrameworkMessage eASFrameworkMessage, IBinder iBinder) {
        try {
            EASFrameworkRetMessage eASFrameworkRetMessageAsyncBinderCall = iEASFrameworkService.asyncBinderCall(eASFrameworkMessage, iBinder);
            if (eASFrameworkRetMessageAsyncBinderCall.mCode != 200) {
                Log.w("VrInternalAPIImplSDK", "sendMsg fail:" + eASFrameworkRetMessageAsyncBinderCall.mCode + "," + eASFrameworkRetMessageAsyncBinderCall.mMsg);
            }
            return eASFrameworkRetMessageAsyncBinderCall;
        } catch (RemoteException unused) {
            return null;
        }
    }
}
