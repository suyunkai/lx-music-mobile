package ecarx.xsf.mediacenter.session.adapter;

import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.proto.nano.Controller;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends m<Controller.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f717a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.a.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == Controller.a.class) {
                return new a();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* bridge */ /* synthetic */ Controller.a a(IExContent iExContent) {
        return null;
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ Controller.a a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(Controller.a aVar) {
        return MessageNano.toByteArray(aVar);
    }

    private static Controller.a b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new Controller.a();
        }
        try {
            return Controller.a.a(bArr);
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
