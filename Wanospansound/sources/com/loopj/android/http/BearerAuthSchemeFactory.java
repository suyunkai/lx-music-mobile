package com.loopj.android.http;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthSchemeFactory;
import cz.msebera.android.httpclient.auth.AuthenticationException;
import cz.msebera.android.httpclient.auth.ContextAwareAuthScheme;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class BearerAuthSchemeFactory implements AuthSchemeFactory {
    @Override // cz.msebera.android.httpclient.auth.AuthSchemeFactory
    public AuthScheme newInstance(HttpParams httpParams) {
        return new BearerAuthScheme();
    }

    public static class BearerAuthScheme implements ContextAwareAuthScheme {
        private boolean complete = false;

        @Override // cz.msebera.android.httpclient.auth.AuthScheme
        public String getParameter(String str) {
            return null;
        }

        @Override // cz.msebera.android.httpclient.auth.AuthScheme
        public String getRealm() {
            return null;
        }

        @Override // cz.msebera.android.httpclient.auth.AuthScheme
        public String getSchemeName() {
            return "Bearer";
        }

        @Override // cz.msebera.android.httpclient.auth.AuthScheme
        public boolean isConnectionBased() {
            return false;
        }

        @Override // cz.msebera.android.httpclient.auth.AuthScheme
        public void processChallenge(Header header) throws MalformedChallengeException {
            this.complete = true;
        }

        @Override // cz.msebera.android.httpclient.auth.AuthScheme
        public Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
            return authenticate(credentials, httpRequest, null);
        }

        @Override // cz.msebera.android.httpclient.auth.ContextAwareAuthScheme
        public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) throws AuthenticationException {
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
            charArrayBuffer.append("Authorization");
            charArrayBuffer.append(": Bearer ");
            charArrayBuffer.append(credentials.getUserPrincipal().getName());
            return new BufferedHeader(charArrayBuffer);
        }

        @Override // cz.msebera.android.httpclient.auth.AuthScheme
        public boolean isComplete() {
            return this.complete;
        }
    }
}
