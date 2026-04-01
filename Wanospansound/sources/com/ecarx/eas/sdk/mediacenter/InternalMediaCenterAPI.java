package com.ecarx.eas.sdk.mediacenter;

import com.ecarx.eas.sdk.mediacenter.callback.ApiCallback;
import ecarx.xsf.mediacenter.session.EasMediaController;
import ecarx.xsf.mediacenter.vr.VrInternalAPI;
import ecarx.xsf.widget.interfaces.IControl;

/* JADX INFO: loaded from: classes2.dex */
public interface InternalMediaCenterAPI extends IMediaCenterAPI {
    EasMediaController getEasMediaController();

    VrInternalAPI getVrInternalApi(ApiCallback<VrInternalAPI> apiCallback);

    IControl getWidgetApi(ApiCallback<IControl> apiCallback);
}
