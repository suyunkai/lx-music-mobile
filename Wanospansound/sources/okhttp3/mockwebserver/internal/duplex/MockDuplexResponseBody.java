package okhttp3.mockwebserver.internal.duplex;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Utf8;
import org.junit.Assert;

/* JADX INFO: compiled from: MockDuplexResponseBody.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\nJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0000J\u0006\u0010\u0014\u001a\u00020\u0000J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\tH\u0016J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0000J\u001a\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001a2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007J\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\tH\u0002J\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%R6\u0010\u0003\u001a*\u0012&\u0012$\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lokhttp3/mockwebserver/internal/duplex/MockDuplexResponseBody;", "Lokhttp3/mockwebserver/internal/duplex/DuplexResponseBody;", "()V", "actions", "Ljava/util/concurrent/LinkedBlockingQueue;", "Lkotlin/Function4;", "Lokhttp3/mockwebserver/RecordedRequest;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "Lokhttp3/internal/http2/Http2Stream;", "", "Lokhttp3/mockwebserver/internal/duplex/Action;", "results", "Ljava/util/concurrent/FutureTask;", "Ljava/lang/Void;", "awaitSuccess", "cancelStream", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "exhaustRequest", "exhaustResponse", "onRequest", "request", "http2Stream", "receiveRequest", "expected", "", "requestIOException", "sendResponse", CmcdData.Factory.STREAMING_FORMAT_SS, "responseSent", "Ljava/util/concurrent/CountDownLatch;", "serviceStreamTask", "sleep", TypedValues.TransitionType.S_DURATION, "", "unit", "Ljava/util/concurrent/TimeUnit;", "mockwebserver"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MockDuplexResponseBody implements DuplexResponseBody {
    private final LinkedBlockingQueue<Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit>> actions = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<FutureTask<Void>> results = new LinkedBlockingQueue<>();

    public final MockDuplexResponseBody sendResponse(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        return sendResponse$default(this, s, null, 2, null);
    }

    public final MockDuplexResponseBody receiveRequest(final String expected) {
        Intrinsics.checkNotNullParameter(expected, "expected");
        this.actions.add(new Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit>() { // from class: okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody$receiveRequest$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(RecordedRequest recordedRequest, BufferedSource bufferedSource, BufferedSink bufferedSink, Http2Stream http2Stream) {
                invoke2(recordedRequest, bufferedSource, bufferedSink, http2Stream);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RecordedRequest noName_0, BufferedSource requestBody, BufferedSink noName_2, Http2Stream noName_3) {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                Intrinsics.checkNotNullParameter(requestBody, "requestBody");
                Intrinsics.checkNotNullParameter(noName_2, "$noName_2");
                Intrinsics.checkNotNullParameter(noName_3, "$noName_3");
                String str = expected;
                Assert.assertEquals(str, requestBody.readUtf8(Utf8.size$default(str, 0, 0, 3, null)));
            }
        });
        return this;
    }

    public final MockDuplexResponseBody exhaustRequest() {
        this.actions.add(new Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit>() { // from class: okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody$exhaustRequest$1$1
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(RecordedRequest recordedRequest, BufferedSource bufferedSource, BufferedSink bufferedSink, Http2Stream http2Stream) {
                invoke2(recordedRequest, bufferedSource, bufferedSink, http2Stream);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RecordedRequest noName_0, BufferedSource requestBody, BufferedSink noName_2, Http2Stream noName_3) {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                Intrinsics.checkNotNullParameter(requestBody, "requestBody");
                Intrinsics.checkNotNullParameter(noName_2, "$noName_2");
                Intrinsics.checkNotNullParameter(noName_3, "$noName_3");
                Assert.assertTrue(requestBody.exhausted());
            }
        });
        return this;
    }

    public final MockDuplexResponseBody cancelStream(final ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        this.actions.add(new Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit>() { // from class: okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody$cancelStream$1$1
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(RecordedRequest recordedRequest, BufferedSource bufferedSource, BufferedSink bufferedSink, Http2Stream http2Stream) {
                invoke2(recordedRequest, bufferedSource, bufferedSink, http2Stream);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RecordedRequest noName_0, BufferedSource noName_1, BufferedSink noName_2, Http2Stream stream) {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
                Intrinsics.checkNotNullParameter(noName_2, "$noName_2");
                Intrinsics.checkNotNullParameter(stream, "stream");
                stream.closeLater(errorCode);
            }
        });
        return this;
    }

    public final MockDuplexResponseBody requestIOException() {
        this.actions.add(new Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit>() { // from class: okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody$requestIOException$1$1
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(RecordedRequest recordedRequest, BufferedSource bufferedSource, BufferedSink bufferedSink, Http2Stream http2Stream) {
                invoke2(recordedRequest, bufferedSource, bufferedSink, http2Stream);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RecordedRequest noName_0, BufferedSource requestBody, BufferedSink noName_2, Http2Stream noName_3) {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                Intrinsics.checkNotNullParameter(requestBody, "requestBody");
                Intrinsics.checkNotNullParameter(noName_2, "$noName_2");
                Intrinsics.checkNotNullParameter(noName_3, "$noName_3");
                try {
                    requestBody.exhausted();
                    Assert.fail();
                } catch (IOException unused) {
                }
            }
        });
        return this;
    }

    public static /* synthetic */ MockDuplexResponseBody sendResponse$default(MockDuplexResponseBody mockDuplexResponseBody, String str, CountDownLatch countDownLatch, int i, Object obj) {
        if ((i & 2) != 0) {
            countDownLatch = new CountDownLatch(0);
        }
        return mockDuplexResponseBody.sendResponse(str, countDownLatch);
    }

    public final MockDuplexResponseBody sendResponse(final String s, final CountDownLatch responseSent) {
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(responseSent, "responseSent");
        this.actions.add(new Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit>() { // from class: okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody$sendResponse$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(RecordedRequest recordedRequest, BufferedSource bufferedSource, BufferedSink bufferedSink, Http2Stream http2Stream) throws IOException {
                invoke2(recordedRequest, bufferedSource, bufferedSink, http2Stream);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RecordedRequest noName_0, BufferedSource noName_1, BufferedSink responseBody, Http2Stream noName_3) throws IOException {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
                Intrinsics.checkNotNullParameter(responseBody, "responseBody");
                Intrinsics.checkNotNullParameter(noName_3, "$noName_3");
                responseBody.writeUtf8(s);
                responseBody.flush();
                responseSent.countDown();
            }
        });
        return this;
    }

    public final MockDuplexResponseBody exhaustResponse() {
        this.actions.add(new Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit>() { // from class: okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody$exhaustResponse$1$1
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(RecordedRequest recordedRequest, BufferedSource bufferedSource, BufferedSink bufferedSink, Http2Stream http2Stream) {
                invoke2(recordedRequest, bufferedSource, bufferedSink, http2Stream);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RecordedRequest noName_0, BufferedSource noName_1, BufferedSink responseBody, Http2Stream noName_3) {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
                Intrinsics.checkNotNullParameter(responseBody, "responseBody");
                Intrinsics.checkNotNullParameter(noName_3, "$noName_3");
                responseBody.close();
            }
        });
        return this;
    }

    public final MockDuplexResponseBody sleep(final long duration, final TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.actions.add(new Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit>() { // from class: okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody$sleep$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(RecordedRequest recordedRequest, BufferedSource bufferedSource, BufferedSink bufferedSink, Http2Stream http2Stream) throws InterruptedException {
                invoke2(recordedRequest, bufferedSource, bufferedSink, http2Stream);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RecordedRequest noName_0, BufferedSource noName_1, BufferedSink noName_2, Http2Stream noName_3) throws InterruptedException {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
                Intrinsics.checkNotNullParameter(noName_2, "$noName_2");
                Intrinsics.checkNotNullParameter(noName_3, "$noName_3");
                Thread.sleep(unit.toMillis(duration));
            }
        });
        return this;
    }

    @Override // okhttp3.mockwebserver.internal.duplex.DuplexResponseBody
    public void onRequest(RecordedRequest request, Http2Stream http2Stream) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(http2Stream, "http2Stream");
        FutureTask<Void> futureTaskServiceStreamTask = serviceStreamTask(request, http2Stream);
        this.results.add(futureTaskServiceStreamTask);
        futureTaskServiceStreamTask.run();
    }

    private final FutureTask<Void> serviceStreamTask(final RecordedRequest request, final Http2Stream http2Stream) {
        return new FutureTask<>(new Callable() { // from class: okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MockDuplexResponseBody.m2447serviceStreamTask$lambda9(http2Stream, this, request);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: serviceStreamTask$lambda-9, reason: not valid java name */
    public static final Void m2447serviceStreamTask$lambda9(Http2Stream http2Stream, MockDuplexResponseBody this$0, RecordedRequest request) {
        BufferedSource bufferedSource;
        BufferedSink bufferedSink;
        Intrinsics.checkNotNullParameter(http2Stream, "$http2Stream");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        BufferedSink bufferedSinkBuffer = Okio.buffer(http2Stream.getSource());
        try {
            bufferedSource = bufferedSinkBuffer;
            bufferedSinkBuffer = Okio.buffer(http2Stream.getSink());
            try {
                bufferedSink = bufferedSinkBuffer;
            } finally {
            }
        } finally {
        }
        while (true) {
            Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit> function4Poll = this$0.actions.poll();
            if (function4Poll != null) {
                function4Poll.invoke(request, bufferedSource, bufferedSink, http2Stream);
            } else {
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(bufferedSinkBuffer, null);
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(bufferedSinkBuffer, null);
                return null;
            }
        }
    }

    public final void awaitSuccess() throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<Void> futureTaskPoll = this.results.poll(5L, TimeUnit.SECONDS);
        if (futureTaskPoll == null) {
            throw new AssertionError("no onRequest call received");
        }
        futureTaskPoll.get(5L, TimeUnit.SECONDS);
    }
}
