package com.ecarx.eas.sdk.mediacenter.control;

import android.util.Log;
import ecarx.xsf.mediacenter.control.IMediaControlClientApiSvc;
import ecarx.xsf.mediacenter.control.IMediaControlClientToken;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements IMediaControlClientAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IMediaControlClientApiSvc f136a;

    /* synthetic */ a(byte b2) {
        this();
    }

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.mediacenter.control.a$a, reason: collision with other inner class name */
    static class C0020a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final a f137a = new a(0);
    }

    private a() {
    }

    public static a a() {
        return C0020a.f137a;
    }

    public final a a(IMediaControlClientApiSvc iMediaControlClientApiSvc) {
        this.f136a = iMediaControlClientApiSvc;
        return this;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControlClientAPI
    public final Object register(String str, MediaControlClient mediaControlClient) {
        IMediaControlClientApiSvc iMediaControlClientApiSvc = this.f136a;
        if (iMediaControlClientApiSvc == null) {
            Log.e("ControlClientAPIImpl", "Please setApiSvc first");
            return null;
        }
        try {
            return iMediaControlClientApiSvc.register(str, new b(mediaControlClient));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControlClientAPI
    public final boolean unregister(Object obj) {
        IMediaControlClientApiSvc iMediaControlClientApiSvc = this.f136a;
        if (iMediaControlClientApiSvc == null) {
            Log.e("ControlClientAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControlClientApiSvc.unregister((IMediaControlClientToken) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.control.IMediaControlClientAPI
    public final boolean requestControlled(Object obj) {
        IMediaControlClientApiSvc iMediaControlClientApiSvc = this.f136a;
        if (iMediaControlClientApiSvc == null) {
            Log.e("ControlClientAPIImpl", "Please setApiSvc first");
            return false;
        }
        try {
            return iMediaControlClientApiSvc.requestControlled((IMediaControlClientToken) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
