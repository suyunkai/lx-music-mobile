package ecarx.xsf.mediacenter.session.adapter;

import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.proto.nano.Controller;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends m<Controller.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f718a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.b.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == Controller.b.class) {
                return new b();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* bridge */ /* synthetic */ Controller.b a(IExContent iExContent) {
        return null;
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ Controller.b a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(Controller.b bVar) {
        return MessageNano.toByteArray(bVar);
    }

    private static Controller.b b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new Controller.b();
        }
        try {
            return Controller.b.a(bArr);
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
