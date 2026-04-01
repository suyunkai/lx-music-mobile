package ecarx.xsf.mediacenter.session.adapter;

import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.MediaOperateState;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;

/* JADX INFO: loaded from: classes3.dex */
public final class g extends m<MediaOperateState> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f725a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.g.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == MediaOperateState.class) {
                return new g();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* bridge */ /* synthetic */ MediaOperateState a(IExContent iExContent) {
        return null;
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ MediaOperateState a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(MediaOperateState mediaOperateState) {
        MediaOperateState mediaOperateState2 = mediaOperateState;
        Mediabean.f fVar = new Mediabean.f();
        if (mediaOperateState2 != null) {
            fVar.f774b = mediaOperateState2.getMediaId();
            fVar.f773a = mediaOperateState2.getQueueId();
            fVar.f775c = mediaOperateState2.getOperate();
            fVar.f776d = mediaOperateState2.getState();
            fVar.e = mediaOperateState2.getMessage();
        }
        return MessageNano.toByteArray(fVar);
    }

    private static MediaOperateState b(byte[] bArr) {
        Mediabean.f fVarA;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            fVarA = Mediabean.f.a(bArr);
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            fVarA = null;
        }
        if (fVarA == null) {
            return null;
        }
        MediaOperateState.Builder builder = new MediaOperateState.Builder();
        builder.mediaId(fVarA.f774b);
        builder.queueId(fVarA.f773a);
        builder.operate(fVarA.f775c);
        builder.state(fVarA.f776d);
        builder.message(fVarA.e);
        return builder.build();
    }
}
