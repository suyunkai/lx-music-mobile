package ecarx.xsf.mediacenter.session.adapter;

import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.PlaybackState;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;

/* JADX INFO: loaded from: classes3.dex */
public final class k extends m<PlaybackState> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f729a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.k.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == PlaybackState.class) {
                return new k();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* bridge */ /* synthetic */ PlaybackState a(IExContent iExContent) {
        return null;
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ PlaybackState a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(PlaybackState playbackState) {
        PlaybackState playbackState2 = playbackState;
        Mediabean.k kVar = new Mediabean.k();
        if (playbackState2 != null) {
            kVar.f793a = playbackState2.getState();
            kVar.f794b = playbackState2.getLoopMode();
            kVar.f795c = playbackState2.getRadioMode();
            kVar.f796d = playbackState2.getQueueId();
            kVar.e = playbackState2.getItemId();
            kVar.f = playbackState2.getErrorCode();
            kVar.g = playbackState2.getErrorMessage();
        }
        return MessageNano.toByteArray(kVar);
    }

    private static PlaybackState b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            Mediabean.k kVarA = Mediabean.k.a(bArr);
            PlaybackState.Builder builder = new PlaybackState.Builder();
            builder.state(kVarA.f793a);
            builder.loopMode(kVarA.f794b);
            builder.radioMode(kVarA.f795c);
            builder.queueId(kVarA.f796d);
            builder.itemId(kVarA.e);
            builder.errorCode(kVarA.f);
            builder.errorMessage(kVarA.g);
            return builder.build();
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
