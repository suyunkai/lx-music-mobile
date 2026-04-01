package com.ecarx.eas.sdk.mediacenter.a;

import android.util.Log;
import com.ecarx.eas.sdk.vr.news.PlayNewsIntent;
import ecarx.xsf.mediacenter.vr.QNewsResult;

/* JADX INFO: loaded from: classes2.dex */
public class f extends PlayNewsIntent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f113a = "f";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private QNewsResult f114b;

    public f(QNewsResult qNewsResult) {
        this.f114b = qNewsResult;
    }

    @Override // com.ecarx.eas.sdk.vr.news.PlayNewsIntent
    public String getRawText() {
        try {
            return this.f114b.rawText;
        } catch (Exception e) {
            Log.e(f113a, " getRawText error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.news.NewsIntent
    public String getNewsId() {
        try {
            return this.f114b.newsId;
        } catch (Exception e) {
            Log.e(f113a, " getNewsId error : " + e.getMessage());
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.news.NewsIntent
    public String getNewsCategory() {
        try {
            return this.f114b.newsCategory;
        } catch (Exception e) {
            Log.e(f113a, " getNewsCategory error : " + e.getMessage());
            return null;
        }
    }
}
