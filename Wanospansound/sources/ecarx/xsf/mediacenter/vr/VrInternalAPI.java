package ecarx.xsf.mediacenter.vr;

import android.os.IBinder;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.vr.channel.VrChannelInfo;
import ecarx.xsf.mediacenter.IAllPlaybackInfo;
import ecarx.xsf.xiaoka.IXiaokaInternalApiSvc;

/* JADX INFO: loaded from: classes3.dex */
public abstract class VrInternalAPI {
    public abstract IAllPlaybackInfo getAllPlaybackInfo();

    public abstract VrChannelInfo getCurrentVRChannelInfo();

    public abstract boolean handleCtrlApp(int i, int i2);

    public abstract boolean handlePlayMusic(QMusicResult qMusicResult);

    public abstract boolean handlePlayNews(QNewsResult qNewsResult);

    public abstract boolean handlePlayRadio(QRadioResult qRadioResult);

    public abstract boolean handleSearchMusic(QMusicResult qMusicResult);

    public abstract boolean handleSearchNews(QNewsResult qNewsResult);

    public abstract boolean handleSearchRadio(QRadioResult qRadioResult);

    public abstract boolean hasPlayingMedia();

    public abstract int playCtrlCollect(int i, boolean z);

    public abstract int playCtrlDownload(int i, boolean z);

    public abstract int playCtrlFastForward();

    public abstract int playCtrlNext();

    public abstract int playCtrlPause();

    public abstract int playCtrlPlay();

    public abstract int playCtrlPlayType(int i);

    public abstract int playCtrlPrevious();

    public abstract int playCtrlQuality(int i);

    public abstract int playCtrlReplay();

    public abstract int playCtrlRewind();

    public abstract int playCtrlStop();

    public abstract boolean regVrChangeListener(VrTypeChangeListener vrTypeChangeListener);

    public abstract boolean sendTTSResult(String str, int i);

    public abstract void setXiaokaInternalApiSvc(IXiaokaInternalApiSvc iXiaokaInternalApiSvc);

    public abstract boolean unregVrChangeListener(VrTypeChangeListener vrTypeChangeListener);

    public abstract void updateIEASFrameworkService(IEASFrameworkService iEASFrameworkService);

    public abstract void updateMediaCenterSvc(IBinder iBinder);
}
