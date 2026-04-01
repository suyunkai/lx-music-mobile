package ecarx.xsf.mediacenter.session.adapter;

import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.MediaEffect;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;

/* JADX INFO: loaded from: classes3.dex */
public final class d extends m<MediaEffect> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f722a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.d.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == MediaEffect.class) {
                return new d();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* bridge */ /* synthetic */ MediaEffect a(IExContent iExContent) {
        return null;
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ MediaEffect a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(MediaEffect mediaEffect) {
        MediaEffect mediaEffect2 = mediaEffect;
        Mediabean.c cVar = new Mediabean.c();
        if (mediaEffect2 != null) {
            cVar.f763a = mediaEffect2.getEffectId();
            cVar.f764b = mediaEffect2.getEffectName();
        }
        return MessageNano.toByteArray(cVar);
    }

    private static MediaEffect b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            Mediabean.c cVarA = Mediabean.c.a(bArr);
            MediaEffect.Builder builder = new MediaEffect.Builder();
            builder.effectId(cVarA.f763a);
            builder.effectName(cVarA.f764b);
            return builder.build();
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
