package com.ecarx.eas.sdk.mediacenter;

import android.content.Context;
import com.ecarx.eas.framework.sdk.ECarXAPIBase;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaCenterAPI extends ECarXAPIBase implements InternalMediaCenterAPI {
    public static MediaCenterAPI get(Context context) {
        return g.a();
    }

    @Deprecated
    public static MediaCenterAPI createMediaCenterAPI(Context context) {
        return g.a();
    }
}
