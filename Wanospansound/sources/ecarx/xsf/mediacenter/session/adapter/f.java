package ecarx.xsf.mediacenter.session.adapter;

import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.MediaItem;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends m<MediaItem> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f724a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.f.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == MediaItem.class) {
                return new f();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* bridge */ /* synthetic */ MediaItem a(IExContent iExContent) {
        return null;
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ MediaItem a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(MediaItem mediaItem) {
        return MessageNano.toByteArray(l.a(mediaItem));
    }

    private static MediaItem b(byte[] bArr) {
        Mediabean.e eVarA;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            eVarA = Mediabean.e.a(bArr);
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            eVarA = null;
        }
        if (eVarA == null) {
            return null;
        }
        return l.a(eVarA);
    }
}
