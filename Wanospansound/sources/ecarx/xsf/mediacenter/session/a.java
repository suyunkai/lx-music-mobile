package ecarx.xsf.mediacenter.session;

import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.view.InputDeviceCompat;
import com.ecarx.eas.sdk.mediacenter.b;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.EasMediaController;
import ecarx.xsf.mediacenter.session.adapter.l;
import ecarx.xsf.mediacenter.session.proto.nano.Controller;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private EasMediaController.Callback f716a;

    @Override // com.ecarx.eas.sdk.mediacenter.b.a
    public final String a(int i, String str, String str2, IBinder iBinder) {
        return null;
    }

    public final void a(EasMediaController.Callback callback) {
        this.f716a = callback;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.ecarx.eas.sdk.mediacenter.b.a
    public final IExContent a(int i, String str, String str2, IExContent iExContent, IBinder iBinder) {
        Log.d("CallbackWrapper", "onExAction:" + i + "," + str + "," + str2 + "," + iBinder);
        EasMediaController.Callback callback = this.f716a;
        if (callback == null) {
            Log.d("CallbackWrapper", "callback is null");
            return null;
        }
        switch (i) {
            case InputDeviceCompat.SOURCE_DPAD /* 513 */:
                try {
                    callback.onPlaybackStateChanged((PlaybackState) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), PlaybackState.class));
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            case 514:
                try {
                    this.f716a.onPlayProgressUpdated(((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class)).j);
                    break;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return null;
            case 515:
                try {
                    callback.onPlaybackInfoChange((PlaybackInfo) ecarx.xsf.mediacenter.session.adapter.c.a().a(iExContent, PlaybackInfo.class));
                    break;
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                return null;
            case 516:
                try {
                    this.f716a.onPlayLyricSentenceUpdated(((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class)).f);
                    break;
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                return null;
            case 517:
                try {
                    callback.onMediaOperateStateChanged((MediaOperateState) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), MediaOperateState.class));
                    break;
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                return null;
            case 518:
                try {
                    callback.onMediaQueueChanged((MediaQueue) ecarx.xsf.mediacenter.session.adapter.c.a().a(iExContent, MediaQueue.class));
                    break;
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
                return null;
            case 519:
                try {
                    callback.onMediaQueueListUpdated(l.a(iExContent));
                    break;
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
                return null;
            case 520:
                try {
                    this.f716a.onMediaSourceTypeChanged(((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class)).f747b);
                    break;
                } catch (Exception e8) {
                    e8.printStackTrace();
                }
                return null;
            case 521:
                try {
                    this.f716a.onMediaSourceTypeListChanged(((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class)).k);
                    break;
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
                return null;
            case 522:
            case 523:
            case 524:
            case 525:
            default:
                return null;
            case 526:
                try {
                    callback.onAppSourceInfoChange(l.a((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class)));
                    break;
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
                return null;
            case 527:
                try {
                    callback.onCustomAppSourceInfoChange(l.a((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class)));
                    break;
                } catch (Exception e11) {
                    e11.printStackTrace();
                }
                return null;
            case 528:
                try {
                    Controller.a aVar = (Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class);
                    this.f716a.onMediaSourceTypeChanged(aVar.m, aVar.f747b);
                    break;
                } catch (Exception e12) {
                    e12.printStackTrace();
                }
                return null;
            case 529:
                try {
                    Controller.a aVar2 = (Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class);
                    this.f716a.onErrorMsgUpdate(aVar2.m, aVar2.q, aVar2.r);
                    break;
                } catch (Exception e13) {
                    e13.printStackTrace();
                }
                return null;
            case 530:
                try {
                    Controller.a aVar3 = (Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class);
                    this.f716a.onControlMediaSourceTypeChangedNotifity(aVar3.s, aVar3.m, aVar3.f747b);
                    break;
                } catch (Exception e14) {
                    e14.printStackTrace();
                }
                return null;
            case 531:
                try {
                    this.f716a.onZonesChanged(((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.a.class)).u);
                    break;
                } catch (Exception e15) {
                    e15.printStackTrace();
                }
                return null;
            case 532:
                try {
                    MediaErrorBean mediaErrorBean = (MediaErrorBean) ecarx.xsf.mediacenter.session.adapter.c.a().a(iExContent, MediaErrorBean.class);
                    this.f716a.onErrorStateUpdateWithPendingIntent(mediaErrorBean.getPackageName(), mediaErrorBean.getSourceType(), mediaErrorBean.getErrorState(), mediaErrorBean.getErrorMsg(), mediaErrorBean.getPendingIntent());
                    break;
                } catch (Exception e16) {
                    e16.printStackTrace();
                }
                return null;
            case 533:
                try {
                    d dVar = (d) ecarx.xsf.mediacenter.session.adapter.c.a().a(iExContent, d.class);
                    this.f716a.onMediaQueueChangedWithPagination(dVar.a(), dVar.b(), dVar.d(), dVar.c());
                    break;
                } catch (Exception e17) {
                    e17.printStackTrace();
                }
                return null;
            case 534:
                try {
                    Mediabean.m mVar = ((Controller.b) ecarx.xsf.mediacenter.session.adapter.c.a().a(str2.getBytes("ISO-8859-1"), Controller.b.class)).e;
                    ecarx.xsf.mediacenter.vr.a aVar4 = new ecarx.xsf.mediacenter.vr.a();
                    if (mVar != null) {
                        aVar4 = new ecarx.xsf.mediacenter.vr.a();
                        aVar4.a(mVar.f801a);
                        aVar4.b(mVar.f803c);
                        aVar4.a(!TextUtils.isEmpty(mVar.f802b) ? mVar.f802b : "");
                    }
                    Log.d("CallbackWrapper", "onVRControlReply vrResponse:" + aVar4);
                    this.f716a.onVRControlReply(aVar4);
                    break;
                } catch (Exception e18) {
                    e18.printStackTrace();
                }
                return null;
        }
    }
}
