package com.just.agentweb;

import android.webkit.WebView;
import androidx.collection.ArrayMap;
import com.just.agentweb.AgentWeb;

/* JADX INFO: loaded from: classes2.dex */
public class WebSecurityLogicImpl implements WebSecurityCheckLogic {
    private String TAG = getClass().getSimpleName();
    private int webviewType;

    public WebSecurityLogicImpl(int i) {
        this.webviewType = i;
    }

    public static WebSecurityLogicImpl getInstance(int i) {
        return new WebSecurityLogicImpl(i);
    }

    @Override // com.just.agentweb.WebSecurityCheckLogic
    public void dealHoneyComb(WebView webView) {
    }

    @Override // com.just.agentweb.WebSecurityCheckLogic
    public void dealJsInterface(ArrayMap<String, Object> arrayMap, AgentWeb.SecurityType securityType) {
        AgentWeb.SecurityType securityType2 = AgentWeb.SecurityType.STRICT_CHECK;
    }
}
