package ecarx.xsf.mediacenter.vr.a;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.mediacenter.b;
import com.ecarx.eas.sdk.vr.channel.VrChannelInfo;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.IAllPlaybackInfo;
import ecarx.xsf.mediacenter.vr.VrTypeChangeListener;
import ecarx.xsf.xiaoka.IXiaokaInternalApiSvc;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends IXiaokaInternalApiSvc.Stub implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private VrTypeChangeListener f817a;

    @Override // com.ecarx.eas.sdk.mediacenter.b.a
    public final IExContent a(int i, String str, String str2, IExContent iExContent, IBinder iBinder) {
        return null;
    }

    @Override // ecarx.xsf.xiaoka.IXiaokaInternalApiSvc
    public final void onMediaCenterMediaPlayingStatus(boolean z) throws RemoteException {
    }

    @Override // ecarx.xsf.xiaoka.IXiaokaInternalApiSvc
    public final void onMediaCenterPlayingMediaInfo(IAllPlaybackInfo iAllPlaybackInfo) throws RemoteException {
    }

    @Override // ecarx.xsf.xiaoka.IXiaokaInternalApiSvc
    public final void onSourceTypeListChanged(int[] iArr) throws RemoteException {
    }

    public final void a(VrTypeChangeListener vrTypeChangeListener) {
        this.f817a = vrTypeChangeListener;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.b.a
    public final String a(int i, String str, String str2, IBinder iBinder) {
        VrChannelInfo vrChannelInfo;
        Log.d("VrInternalWrapper", "onAction:" + i + "," + str + "," + str2 + "," + iBinder);
        if (i == 3 && this.f817a != null) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                String string = jSONObject.getString("mediaPackageName");
                String string2 = jSONObject.getString("mediaVersion");
                String string3 = jSONObject.getString("mediaVersion");
                int i2 = jSONObject.getInt("channelDataType");
                Log.d("VrInternalWrapper", "VrTypeChangeListener:" + string + "," + string + "channelDataType," + i2);
                vrChannelInfo = new VrChannelInfo(string, string2, string3, i2);
            } catch (JSONException e) {
                e.printStackTrace();
                vrChannelInfo = null;
            }
            this.f817a.onVrTypeChangeListener(vrChannelInfo);
        }
        return null;
    }
}
