package ecarx.xsf.mediacenter.session.adapter;

import android.app.PendingIntent;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;
import java.io.UnsupportedEncodingException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class i extends m<ecarx.xsf.mediacenter.session.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f727a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.i.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == ecarx.xsf.mediacenter.session.d.class) {
                return new i();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ ecarx.xsf.mediacenter.session.d a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(ecarx.xsf.mediacenter.session.d dVar) {
        ecarx.xsf.mediacenter.session.d dVar2 = dVar;
        Mediabean.i iVar = new Mediabean.i();
        iVar.f785a = dVar2.a();
        iVar.f786b = dVar2.b();
        iVar.f788d = dVar2.d();
        iVar.f787c = l.a(dVar2.c());
        return MessageNano.toByteArray(iVar);
    }

    private static ecarx.xsf.mediacenter.session.d b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            Mediabean.i iVarA = Mediabean.i.a(bArr);
            ecarx.xsf.mediacenter.session.d dVar = new ecarx.xsf.mediacenter.session.d();
            dVar.a(iVarA.f785a);
            dVar.b(iVarA.f786b);
            dVar.c(iVarA.f788d);
            dVar.a(l.a(iVarA.f787c));
            return dVar;
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // ecarx.xsf.mediacenter.session.adapter.m
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ecarx.xsf.mediacenter.session.d a(IExContent iExContent) {
        ecarx.xsf.mediacenter.session.d dVarB = null;
        if (iExContent == null) {
            return null;
        }
        try {
            if (iExContent.getData() == null) {
                return null;
            }
            dVarB = b(iExContent.getData().getBytes("ISO-8859-1"));
            List<PendingIntent> pendingIntents = iExContent.getPendingIntents();
            if (pendingIntents != null && pendingIntents.size() > 0) {
                dVarB.c().setQueueIntent(pendingIntents.get(0));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return dVarB;
    }
}
