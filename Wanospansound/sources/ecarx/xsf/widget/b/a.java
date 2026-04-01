package ecarx.xsf.widget.b;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.mediacenter.CommercialInfoHelper;
import com.ecarx.eas.sdk.mediacenter.b;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.IContent;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMediaLists;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.IRecommend;
import ecarx.xsf.widget.IReceiveWidgetInfoCallback;
import ecarx.xsf.widget.IWidgetApiSvc;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends IWidgetApiSvc.Stub implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IReceiveWidgetInfoCallback f834a;

    public a(IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback) {
        this.f834a = iReceiveWidgetInfoCallback;
    }

    @Override // ecarx.xsf.widget.IWidgetApiSvc
    public final void updateMusicPlayInfo(IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException {
        IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback = this.f834a;
        if (iReceiveWidgetInfoCallback != null) {
            iReceiveWidgetInfoCallback.updateMusicPlayInfo(iMusicPlaybackInfo);
        }
    }

    @Override // ecarx.xsf.widget.IWidgetApiSvc
    public final void updateProgress(long j) throws RemoteException {
        IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback = this.f834a;
        if (iReceiveWidgetInfoCallback != null) {
            iReceiveWidgetInfoCallback.updateProgress(j);
        }
    }

    @Override // ecarx.xsf.widget.IWidgetApiSvc
    public final void updateMediaList(int i, int i2, List<IMedia> list) throws RemoteException {
        IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback = this.f834a;
        if (iReceiveWidgetInfoCallback != null) {
            iReceiveWidgetInfoCallback.updateMediaList(i, i2, list);
        }
    }

    @Override // ecarx.xsf.widget.IWidgetApiSvc
    public final void updateRecommendInfo(IRecommend iRecommend) throws RemoteException {
        IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback = this.f834a;
        if (iReceiveWidgetInfoCallback != null) {
            iReceiveWidgetInfoCallback.updateRecommendInfo(iRecommend);
        }
    }

    @Override // ecarx.xsf.widget.IWidgetApiSvc
    public final void updateMultiMediaList(IMediaLists iMediaLists) throws RemoteException {
        IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback = this.f834a;
        if (iReceiveWidgetInfoCallback != null) {
            iReceiveWidgetInfoCallback.updateMultiMediaListEx(CommercialInfoHelper.changeIMediaLists2Ex(iMediaLists));
        }
    }

    @Override // ecarx.xsf.widget.IWidgetApiSvc
    public final void updateMediaContent(List<IContent> list) throws RemoteException {
        IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback = this.f834a;
        if (iReceiveWidgetInfoCallback != null) {
            iReceiveWidgetInfoCallback.updateMediaContent(list);
        }
    }

    @Override // ecarx.xsf.widget.IWidgetApiSvc
    public final void updateCollectMsg(int i, String str) throws RemoteException {
        IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback = this.f834a;
        if (iReceiveWidgetInfoCallback != null) {
            iReceiveWidgetInfoCallback.updateCollectMsg(i, str);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.b.a
    public final String a(int i, String str, String str2, IBinder iBinder) {
        IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback;
        Log.d("WidgetApiSvcWrapper", "onAction:" + i + "," + str + "," + str2 + "," + iBinder);
        if (i == 1 && (iReceiveWidgetInfoCallback = this.f834a) != null) {
            iReceiveWidgetInfoCallback.updateMultiMediaListEx(CommercialInfoHelper.getIMediaListsExByJson(str2, null));
        }
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.b.a
    public final IExContent a(int i, String str, String str2, IExContent iExContent, IBinder iBinder) {
        IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback;
        Log.d("WidgetApiSvcWrapper", "onExAction:" + i + "," + str + "," + str2 + "," + iBinder);
        if (i != 1 || (iReceiveWidgetInfoCallback = this.f834a) == null || iExContent == null) {
            return null;
        }
        iReceiveWidgetInfoCallback.updateMultiMediaListEx(CommercialInfoHelper.getIMediaListsExByIExContent(iExContent));
        return null;
    }
}
