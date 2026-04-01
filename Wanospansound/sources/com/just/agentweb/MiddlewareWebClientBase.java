package com.just.agentweb;

/* JADX INFO: loaded from: classes2.dex */
public class MiddlewareWebClientBase extends WebViewClientDelegate {
    private static String TAG = "MiddlewareWebClientBase";
    private MiddlewareWebClientBase mMiddleWrareWebClientBase;

    protected MiddlewareWebClientBase() {
        super(null);
    }

    protected MiddlewareWebClientBase(android.webkit.WebViewClient webViewClient) {
        super(webViewClient);
    }

    MiddlewareWebClientBase(MiddlewareWebClientBase middlewareWebClientBase) {
        super(middlewareWebClientBase);
        this.mMiddleWrareWebClientBase = middlewareWebClientBase;
    }

    final MiddlewareWebClientBase enq(MiddlewareWebClientBase middlewareWebClientBase) {
        setDelegate(middlewareWebClientBase);
        this.mMiddleWrareWebClientBase = middlewareWebClientBase;
        return middlewareWebClientBase;
    }

    final MiddlewareWebClientBase next() {
        return this.mMiddleWrareWebClientBase;
    }

    @Override // com.just.agentweb.WebViewClientDelegate
    final void setDelegate(android.webkit.WebViewClient webViewClient) {
        super.setDelegate(webViewClient);
    }
}
