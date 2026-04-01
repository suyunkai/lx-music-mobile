package ecarx.xsf.mediacenter.session.adapter;

import android.app.PendingIntent;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.MediaQueue;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;
import java.io.UnsupportedEncodingException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends m<MediaQueue> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f726a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.h.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == MediaQueue.class) {
                return new h();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ MediaQueue a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(MediaQueue mediaQueue) {
        return MessageNano.toByteArray(l.a(mediaQueue));
    }

    private static MediaQueue b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            return l.a(Mediabean.h.a(bArr));
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // ecarx.xsf.mediacenter.session.adapter.m
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public MediaQueue a(IExContent iExContent) {
        MediaQueue.Builder builder = null;
        if (iExContent == null) {
            return null;
        }
        try {
            if (iExContent.getData() == null) {
                return null;
            }
            builder = new MediaQueue.Builder(b(iExContent.getData().getBytes("ISO-8859-1")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<PendingIntent> pendingIntents = iExContent.getPendingIntents();
        if (pendingIntents != null && pendingIntents.size() > 0) {
            builder.queueIntent(pendingIntents.get(0));
        }
        return builder.build();
    }
}
