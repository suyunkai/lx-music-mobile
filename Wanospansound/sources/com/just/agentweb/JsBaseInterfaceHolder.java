package com.just.agentweb;

import android.webkit.JavascriptInterface;
import com.just.agentweb.AgentWeb;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public abstract class JsBaseInterfaceHolder implements JsInterfaceHolder {
    private AgentWeb.SecurityType mSecurityType;
    private WebCreator mWebCreator;

    protected JsBaseInterfaceHolder(WebCreator webCreator, AgentWeb.SecurityType securityType) {
        this.mSecurityType = securityType;
        this.mWebCreator = webCreator;
    }

    @Override // com.just.agentweb.JsInterfaceHolder
    public boolean checkObject(Object obj) {
        if (this.mWebCreator.getWebViewType() == 2) {
            return true;
        }
        boolean z = false;
        for (Method method : obj.getClass().getMethods()) {
            Annotation[] annotations = method.getAnnotations();
            int length = annotations.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (annotations[i] instanceof JavascriptInterface) {
                    z = true;
                    break;
                }
                i++;
            }
            if (z) {
                break;
            }
        }
        return z;
    }

    protected boolean checkSecurity() {
        if (this.mSecurityType != AgentWeb.SecurityType.STRICT_CHECK) {
            return true;
        }
        this.mWebCreator.getWebViewType();
        return true;
    }
}
