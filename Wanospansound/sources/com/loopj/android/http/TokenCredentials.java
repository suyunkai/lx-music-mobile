package com.loopj.android.http;

import cz.msebera.android.httpclient.auth.BasicUserPrincipal;
import cz.msebera.android.httpclient.auth.Credentials;
import java.security.Principal;

/* JADX INFO: loaded from: classes3.dex */
public class TokenCredentials implements Credentials {
    private Principal userPrincipal;

    @Override // cz.msebera.android.httpclient.auth.Credentials
    public String getPassword() {
        return null;
    }

    public TokenCredentials(String str) {
        this.userPrincipal = new BasicUserPrincipal(str);
    }

    @Override // cz.msebera.android.httpclient.auth.Credentials
    public Principal getUserPrincipal() {
        return this.userPrincipal;
    }
}
