package ecarx.xsf.mediacenter.session.adapter;

import android.app.PendingIntent;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.MediaErrorBean;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;
import java.io.UnsupportedEncodingException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends m<MediaErrorBean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f723a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.e.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == MediaErrorBean.class) {
                return new e();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ MediaErrorBean a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(MediaErrorBean mediaErrorBean) {
        MediaErrorBean mediaErrorBean2 = mediaErrorBean;
        Mediabean.d dVar = new Mediabean.d();
        dVar.f765a = mediaErrorBean2.getPackageName();
        dVar.f766b = mediaErrorBean2.getErrorState();
        dVar.f767c = mediaErrorBean2.getSourceType();
        dVar.f768d = mediaErrorBean2.getErrorMsg();
        return MessageNano.toByteArray(dVar);
    }

    private static MediaErrorBean b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            Mediabean.d dVarA = Mediabean.d.a(bArr);
            MediaErrorBean mediaErrorBean = new MediaErrorBean();
            mediaErrorBean.setErrorState(dVarA.f766b);
            mediaErrorBean.setSourceType(dVarA.f767c);
            mediaErrorBean.setPackageName(dVarA.f765a);
            mediaErrorBean.setErrorMsg(dVarA.f768d);
            return mediaErrorBean;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // ecarx.xsf.mediacenter.session.adapter.m
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public MediaErrorBean a(IExContent iExContent) {
        MediaErrorBean mediaErrorBeanB = null;
        if (iExContent == null) {
            return null;
        }
        List<PendingIntent> pendingIntents = iExContent.getPendingIntents();
        try {
            if (iExContent.getData() == null) {
                return null;
            }
            mediaErrorBeanB = b(iExContent.getData().getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (mediaErrorBeanB != null && pendingIntents != null && pendingIntents.size() > 0) {
            mediaErrorBeanB.setPendingIntent(pendingIntents.get(0));
        }
        return mediaErrorBeanB;
    }
}
