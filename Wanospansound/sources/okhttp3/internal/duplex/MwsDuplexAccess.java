package okhttp3.internal.duplex;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.internal.duplex.DuplexResponseBody;

/* JADX INFO: compiled from: MwsDuplexAccess.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\n"}, d2 = {"Lokhttp3/internal/duplex/MwsDuplexAccess;", "", "()V", "setBody", "", "mockResponse", "Lokhttp3/mockwebserver/MockResponse;", "duplexResponseBody", "Lokhttp3/mockwebserver/internal/duplex/DuplexResponseBody;", "Companion", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class MwsDuplexAccess {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static MwsDuplexAccess instance;

    public abstract void setBody(MockResponse mockResponse, DuplexResponseBody duplexResponseBody);

    /* JADX INFO: compiled from: MwsDuplexAccess.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/duplex/MwsDuplexAccess$Companion;", "", "()V", "instance", "Lokhttp3/internal/duplex/MwsDuplexAccess;", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
