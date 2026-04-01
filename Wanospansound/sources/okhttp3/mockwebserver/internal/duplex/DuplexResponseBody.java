package okhttp3.mockwebserver.internal.duplex;

import java.io.IOException;
import kotlin.Metadata;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.mockwebserver.RecordedRequest;

/* JADX INFO: compiled from: DuplexResponseBody.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lokhttp3/mockwebserver/internal/duplex/DuplexResponseBody;", "", "onRequest", "", "request", "Lokhttp3/mockwebserver/RecordedRequest;", "http2Stream", "Lokhttp3/internal/http2/Http2Stream;", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DuplexResponseBody {
    void onRequest(RecordedRequest request, Http2Stream http2Stream) throws IOException;
}
