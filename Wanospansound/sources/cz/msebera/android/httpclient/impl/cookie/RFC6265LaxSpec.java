package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.CommonCookieAttributeHandler;

/* JADX INFO: loaded from: classes3.dex */
public class RFC6265LaxSpec extends RFC6265CookieSpecBase {
    public String toString() {
        return "rfc6265-lax";
    }

    public RFC6265LaxSpec() {
        super(new BasicPathHandler(), new BasicDomainHandler(), new LaxMaxAgeHandler(), new BasicSecureHandler(), new LaxExpiresHandler());
    }

    RFC6265LaxSpec(CommonCookieAttributeHandler... commonCookieAttributeHandlerArr) {
        super(commonCookieAttributeHandlerArr);
    }
}
