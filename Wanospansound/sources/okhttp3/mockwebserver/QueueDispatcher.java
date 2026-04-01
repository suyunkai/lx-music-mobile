package okhttp3.mockwebserver;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueDispatcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lokhttp3/mockwebserver/QueueDispatcher;", "Lokhttp3/mockwebserver/Dispatcher;", "()V", "failFastResponse", "Lokhttp3/mockwebserver/MockResponse;", "responseQueue", "Ljava/util/concurrent/BlockingQueue;", "getResponseQueue", "()Ljava/util/concurrent/BlockingQueue;", "dispatch", "request", "Lokhttp3/mockwebserver/RecordedRequest;", "enqueueResponse", "", "response", "peek", "setFailFast", "failFast", "", "shutdown", "Companion", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class QueueDispatcher extends Dispatcher {
    private static final MockResponse DEAD_LETTER;
    private static final Logger logger;
    private MockResponse failFastResponse;
    private final BlockingQueue<MockResponse> responseQueue = new LinkedBlockingQueue();

    protected final BlockingQueue<MockResponse> getResponseQueue() {
        return this.responseQueue;
    }

    @Override // okhttp3.mockwebserver.Dispatcher
    public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        Intrinsics.checkNotNullParameter(request, "request");
        String requestLine = request.getRequestLine();
        if (Intrinsics.areEqual(requestLine, "GET /favicon.ico HTTP/1.1")) {
            logger.info(Intrinsics.stringPlus("served ", requestLine));
            return new MockResponse().setResponseCode(404);
        }
        if (this.failFastResponse != null && this.responseQueue.peek() == null) {
            MockResponse mockResponse = this.failFastResponse;
            Intrinsics.checkNotNull(mockResponse);
            return mockResponse;
        }
        MockResponse result = this.responseQueue.take();
        MockResponse mockResponse2 = DEAD_LETTER;
        if (Intrinsics.areEqual(result, mockResponse2)) {
            this.responseQueue.add(mockResponse2);
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @Override // okhttp3.mockwebserver.Dispatcher
    public MockResponse peek() {
        MockResponse mockResponsePeek = this.responseQueue.peek();
        if (mockResponsePeek != null) {
            return mockResponsePeek;
        }
        MockResponse mockResponse = this.failFastResponse;
        return mockResponse == null ? super.peek() : mockResponse;
    }

    public void enqueueResponse(MockResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        this.responseQueue.add(response);
    }

    @Override // okhttp3.mockwebserver.Dispatcher
    public void shutdown() {
        this.responseQueue.add(DEAD_LETTER);
    }

    public void setFailFast(boolean failFast) {
        MockResponse responseCode;
        if (failFast) {
            responseCode = new MockResponse().setResponseCode(404);
        } else {
            responseCode = null;
        }
        setFailFast(responseCode);
    }

    public void setFailFast(MockResponse failFastResponse) {
        this.failFastResponse = failFastResponse;
    }

    static {
        MockResponse mockResponse = new MockResponse();
        mockResponse.status("HTTP/1.1 503 shutting down");
        DEAD_LETTER = mockResponse;
        logger = Logger.getLogger(QueueDispatcher.class.getName());
    }
}
