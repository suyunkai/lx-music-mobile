package ecarx.xsf.mediacenter.session.adapter;

import android.app.PendingIntent;
import android.net.Uri;
import com.ecarx.eas.framework.sdk.proto.InvalidProtocolBufferNanoException;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.xsf.mediacenter.session.PlaybackInfo;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;
import java.io.UnsupportedEncodingException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class j extends m<PlaybackInfo> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapterFactory f728a = new TypeAdapterFactory() { // from class: ecarx.xsf.mediacenter.session.adapter.j.1
        @Override // ecarx.xsf.mediacenter.session.adapter.TypeAdapterFactory
        public final <T> m<T> create(Class<T> cls) {
            if (cls == PlaybackInfo.class) {
                return new j();
            }
            return null;
        }
    };

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ PlaybackInfo a(byte[] bArr) {
        return b(bArr);
    }

    @Override // ecarx.xsf.mediacenter.session.adapter.m
    public final /* synthetic */ byte[] a(PlaybackInfo playbackInfo) {
        PlaybackInfo playbackInfo2 = playbackInfo;
        Mediabean.j jVar = new Mediabean.j();
        jVar.f789a = playbackInfo2.getPkgName();
        if (playbackInfo2.getIconUri() != null) {
            jVar.f790b = playbackInfo2.getIconUri().toString();
        }
        jVar.f791c = playbackInfo2.getAppName();
        jVar.f792d = playbackInfo2.getInitialProgress();
        jVar.e = l.a(playbackInfo2.getMediaItem());
        return MessageNano.toByteArray(jVar);
    }

    private static PlaybackInfo b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            Mediabean.j jVarA = Mediabean.j.a(bArr);
            PlaybackInfo.Builder builder = new PlaybackInfo.Builder();
            builder.pkgName(jVarA.f789a);
            if (jVarA.f790b != null) {
                builder.iconUri(Uri.parse(jVarA.f790b));
            }
            builder.appName(jVarA.f791c);
            builder.initialProgress(jVarA.f792d);
            builder.mediaItem(l.a(jVarA.e));
            return builder.build();
        } catch (InvalidProtocolBufferNanoException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // ecarx.xsf.mediacenter.session.adapter.m
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public PlaybackInfo a(IExContent iExContent) {
        PlaybackInfo.Builder builder = null;
        if (iExContent == null) {
            return null;
        }
        List<PendingIntent> pendingIntents = iExContent.getPendingIntents();
        try {
            if (iExContent.getData() == null) {
                return null;
            }
            builder = new PlaybackInfo.Builder(b(iExContent.getData().getBytes("ISO-8859-1")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (pendingIntents != null && pendingIntents.size() == 2) {
            builder.launchIntent(pendingIntents.get(0));
            builder.playerIntent(pendingIntents.get(1));
        }
        return builder.build();
    }
}
